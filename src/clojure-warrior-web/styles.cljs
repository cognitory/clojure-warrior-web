(ns clojure-warrior-web.styles
  (:require
    [garden.core :refer [css]]))

(defn code-area []
  {:padding "1em"
   :height "100vh"
   :box-sizing "border-box"})

(defn editor-styles []
  [:.editor
   {:width "40%"
    :flex-grow 1
    :overflow "hidden"
    :position "relative"}

   [:button
    {:position "absolute"
     :top "1em"
     :right "1em"
     :z-index 1000}]

   [:.console
    {:position "absolute"
     :z-index 1000
     :bottom "1em"
     :right "1em"
     :left "1em"
     :overflow "scroll"
     :max-height "20vh"}

    [:.message
     {:padding "0.5em"
      :color "white"
      :font-family "monospace"
      :margin-top "2px"
      :border-radius "2px"}

     [:&.log
      {:background "#424155"}]

     [:&.error
      {:background "#da4939"}]]]

   [:.CodeMirror
    (code-area)
    {:background "black"}]])

(defn sidebar-styles []
  [:.sidebar
   {:width "20%"
    :overflow "hidden"}

   [:.CodeMirror
    (code-area)]])

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

(defn main-styles []
  [:body
   {:margin 0
    :padding 0}

   [:#app
    {:background "#eee"}

    [:.app
     {:display "flex"
      :height "100vh"}]]])

(defn styles-view []
  [:style
   {:type "text/css"
    :dangerouslySetInnerHTML
    {:__html (css
               (main-styles)
               (sidebar-styles)
               (editor-styles)
               (level-styles))}}])
