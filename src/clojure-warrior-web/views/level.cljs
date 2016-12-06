(ns clojure-warrior-web.views.level
  (:require
    [re-frame.core :refer [subscribe dispatch]]))

(defn health-bar-view [entity]
  (when (entity :max-health)
    [:div.health-bar
     {:style {:width (* 2 (entity :max-health))}}
     [:div.health
      {:style {:width (* 2 (entity :health))}
       :class (cond
                (< (entity :health) 5) "low"
                (< (entity :health) 10) "medium"
                :else "high")}]]))

(defn annotate-entity [entity]
  (case (:type entity)
    :warrior
    (cond
      (= 0 (:health entity))
      (assoc entity :state :dead)

      (= :walk (first (:action entity)))
      (assoc entity :state :walk)

      (= :attack (first (:action entity)))
      (assoc entity :state :attack)

      :else
      (assoc entity :state :base))

    :archer
    (cond
      (= 0 (:health entity))
      (assoc entity :state :dead)

      :else
      (assoc entity :state :base))

    entity))

(defn entity-view [entity]
  (let [entity (annotate-entity entity)]
    [:div.sprite
     {:class (str (name (:type entity)) " "
                  (name (or (:state entity) :nil)))
      :style {:background-image
              (str "url(./sprites/"
                   (name (:type entity))
                   (when (:state entity)
                       (str "_" (name (:state entity))))
                   ".png)")}}
     [health-bar-view entity]]))

(defn navigator-view []
  (let [turn (subscribe [:turn])
        turn-count (subscribe [:turn-count])]
    (fn []
      [:div.navigator
       [:button {:disabled (when (= @turn 0) "disabled")
                 :on-click (fn []
                             (dispatch [:set-turn (dec @turn)]))} "<"]
       [:input {:type "range"
                :min 0
                :max (dec @turn-count)
                :step 1
                :value @turn
                :on-change (fn [e]
                             (dispatch [:set-turn (js/parseInt (.. e -target -value) 10)]))}]
       [:button {:disabled (when (= @turn (dec @turn-count)) "disabled")
                 :on-click (fn []
                             (dispatch [:set-turn (inc @turn)]))} ">"]])))

(defn messages-view [messages]
  (into [:div.messages]
        (for [message messages]
          [:div.message message])))

(defn board-view [board]
  (into [:div.board]
        (for [row board]
          (into [:div.row]
                (for [entity row]
                  [:div.space
                   [entity-view entity]])))))

(defn level-view []
  (let [history (subscribe [:history])
        turn (subscribe [:turn])]
    (fn []
      [:div.level
       [navigator-view]
       [board-view (get-in @history [@turn :board])]
       [messages-view (get-in @history [@turn :messages])]])))

