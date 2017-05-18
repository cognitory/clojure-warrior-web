(ns clojure-warrior-web.views.level
  (:require
    [clojure.string :as string]
    [zprint.core :refer [zprint-str]]
    [reagent.core :as r]
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
  (cond
    (= 0 (:health entity))
    (assoc entity :state :dead)

    (:at-stairs entity)
    (assoc entity :state :walk-stairs)

    (contains? #{:walk :attack :shoot :rest} (first (:action entity)))
    (assoc entity :state (first (:action entity)))

    (contains? #{:warrior :sludge :thick-sludge :archer :wizard}
               (:type entity))
    (assoc entity :state :base)

    :else
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

(defn code-view [code]
  [:div {:class (string/join " "  ["CodeMirror" "cm-s-default" "cm-s-railscasts"])
         :ref (fn [el]
                (when (not (nil? el))
                  (js/CodeMirror.runMode
                    (zprint-str code 25 {:style :community
                                         :parse-string? true
                                         :map {:comma? false
                                               :force-nl? true}})
                    "clojure"
                    el)))}])

(defn message-view [message]
  (if (string/starts-with? message ">")
    [:div.message.say
     (let [out (string/replace-first message "> " "")]
       [code-view out])]
    [:div.message.system
     message]))

(defn messages-view [messages]
  (r/create-class
    {:component-did-update
     (fn [this]
       (let [dom-node (r/dom-node this)]
         ; scroll to bottom:
         (set! (.-scrollTop dom-node)
               (.-scrollHeight dom-node))))
     :reagent-render
     (fn [messages]
       [:div.messages
        (map-indexed
          (fn [i message]
            ^{:key i}
            [message-view message])
          messages)])}))

(defn board-view [board]
  (into [:div.board]
        (for [row board]
          (into [:div.row]
                (for [entity (-> row
                                 ; remove walls at leftmost and rightmost
                                 rest butlast)]
                  [:div.space
                   [entity-view entity]])))))

(defn level-view []
  (let [history (subscribe [:history])
        turn (subscribe [:turn])]
    (fn []
      (if (seq @history)
        [:div.level
         [navigator-view]
         [board-view (get-in @history [@turn :board])]
         [messages-view (get-in @history [@turn :messages])]]
        [:div.level]))))

