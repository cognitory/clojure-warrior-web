(ns clojure-warrior-web.views.level)

(def board
  [{:type :warrior
    :state (rand-nth [:base :rest :attack :walk :shoot :dead])
    :max-health 20
    :health (rand-int 20)}
   {:type :sludge
    :state (rand-nth [:base :attack :dead])
    :max-health 12
    :health (rand-int 12)}
   {:type :thick-sludge
    :state (rand-nth [:base :attack :dead])
    :max-health 24
    :health (rand-int 24)}
   {:type :archer
    :state (rand-nth [:base :attack :dead])
    :max-health 7
    :health (rand-int 7)}
   {:type :wizard
    :state (rand-nth [:base :shoot :dead])
    :max-health 3
    :health (rand-int 3)}
   {:type :stairs}
   {:type :captive
    :state (rand-nth [:base :free :dead])
    :max-health 1
    :health (rand-int 2)}])

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
                 ".png)")}}])

(defn level-view []
  [:div.level
   (for [entity board]
     [:div.space
      [entity-view entity]
      [health-bar-view entity]])])
