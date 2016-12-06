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
    :min-width "20em"
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
   {:display "flex"
    :flex-direction "column"
    :min-width "20em"
    :height "100vh"}

   [:.messages
    {:overflow "scroll"}

    [:.message
     [:&:last-child
      {:font-weight "bold"}]]]

   [:.board

    [:.space
     {:display "inline-block"
      :width "64px"
      :height "64px"
      :background-image "url(./sprites/floor.png)"
      :position "relative"}

     [:.sprite
      {:width "64px"
       :height "64px"
       :background-repeat "no-repeat"
       :background-position-x "center"
       :position "absolute"
       :z-index 100}

      [:&.floor
       {:display "none"}]

      [:&.walk.warrior
       {:left "32px"}]

      [:&.attack.sludge
       {:left "0px"}]

      [:&.attack.warrior::after
       {:content "\"\""
        :display "block"
        :width "64px"
        :height "64px"
        :background "url(./sprites/warrior_attack-receive.png)"
        :position "absolute"
        :top 0
        :left "64px"
        :z-index 102}]]

     [:.health-bar
      {:height "2px"
       :background "#e5e6c7"
       :margin "4px auto 0"}

      [:.health
       {:height "100%"}

       [:&.high
        {:background "#11ec11"}]

       [:&.medium
        {:background "#ec8f11"}]

       [:&.low
        {:background "#ec1111"}]]]]]])

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
