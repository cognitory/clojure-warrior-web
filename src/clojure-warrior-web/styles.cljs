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
      :background-position-x "center"}]]])

(defn styles-view []
  [:style
   {:type "text/css"
    :dangerouslySetInnerHTML
    {:__html (css
               (level-styles))}}])
