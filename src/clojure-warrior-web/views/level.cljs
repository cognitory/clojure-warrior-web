(ns clojure-warrior-web.views.level)

(def board
  [{:type :warrior
    :state (rand-nth [:base :rest :attack :walk :shoot :dead]) }
   {:type :sludge
    :state (rand-nth [:base :attack :dead])}
   {:type :thick-sludge
    :state (rand-nth [:base :attack :dead])}
   {:type :archer
    :state (rand-nth [:base :attack :dead])}
   {:type :wizard
    :state (rand-nth [:base :shoot :dead])}
   {:type :stairs}
   {:type :captive
    :state (rand-nth [:base :free :dead])}])

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
   (for [space board]
     [:div.space
      [entity-view space]])])
