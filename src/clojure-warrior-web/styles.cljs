(ns clojure-warrior-web.styles
  (:require
    [garden.core :refer [css]]))

(defn level-styles []
  [:.level
   {}

   [:.space
    {:display "inline-block"
     :width "64px"
     :height "64px"
     :background-image "url(./sprites/floor.png)"}

    [:.sprite
     {:width "64px"
      :height "64px"
      :background-repeat "no-repeat"
      :background-position-x "center"}]

    [:.health-bar
     {:height "2px"
      :background "#e5e6c7"
      :margin "-60px auto 0"}

     [:.health
      {:height "100%"}

      [:&.high
       {:background "#11ec11"}]

      [:&.medium
       {:background "#e9ec11"}]

      [:&.low
       {:background "#ec1111"}]]]]])

(defn styles-view []
  [:style
   {:type "text/css"
    :dangerouslySetInnerHTML
    {:__html (css
               (level-styles))}}])
