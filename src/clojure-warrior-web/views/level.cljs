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

(defn entity-view [entity]
  [:div.sprite
   {:class (str (name (:type entity)) " "
                (name (or (:state entity) :nil)))
    :style {:background-image
            (str "url(./sprites/"
                 (name (:type entity))
                 (when (:state entity)
                   (str "_" (name (:state entity))))
                 ".png)")}}
   [health-bar-view entity]])

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

(defn level-view []
  (let [history (subscribe [:history])
        turn (subscribe [:turn])]
    (fn []
      [:div
       [navigator-view]
       [:div.level
        (for [entity (-> (get @history @turn)
                         :board)]
          ^{:key (gensym)}
          [:div.space
           [entity-view entity]])]])))
