(ns clojure-warrior-web.styles
  (:require
    [garden.core :refer [css]]))

(defn editor-styles []
  [:.editor
   {:min-width "20em"
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
    {:padding "1em"
     :height "100vh"
     :box-sizing "border-box"
     :white-space "pre"
     :background "black"}]])

(defn sidebar-styles []
  [:.sidebar
   {:overflow "hidden"
    :height "100vh"
    :background "#2b2b2b" }

   [:.CodeMirror
    {:white-space "pre"
     :padding "1em"
     :height "initial"
     :display "block"}

    [:.cm-builtin
     {:color "#da4939"}]]])

(defn level-styles []
  [:.level
   {:display "flex"
    :flex-direction "column"
    :height "100vh"
    :width (str (* 9 64) "px")
    :overflow "hidden"}

   [:.navigator
    {:display "flex"
     :min-height "2em"}

    [:input
     {:flex-basis "100%"}]]

   [:.messages
    {:overflow "scroll"
     :font-family "monospace"
     :flex-basis "50%"
     :flex-grow 2}

    [:.message
     {:line-height "1.25em"
      :padding "0 0.5em"}

     [:&.system
      {:white-space "nowrap"}]

     [:&.say
      {:padding "0.75em 1em"
       :border-radius "3px"
       :margin "0.5em 0.5em"
       :display "inline-block"
       :background "#2b2b2b"}

      [:>.CodeMirror
       {:white-space "pre"
        :height "inherit"}]]

     [:&:last-child
      {:font-weight "bold"}]]]

   [:.board
    {:height "64px"
     :width "100%"
     :overflow "hidden"
     :white-space "nowrap"}

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
        :z-index 102}]

      [:&.warrior.walk-stairs
       {:background-image "url(./sprites/stairs.png) !important"}]

      [:&.warrior.walk-stairs::after
       {:content "\"\""
        :display "block"
        :width "64px"
        :height "64px"
        :background "url(./sprites/warrior_walk-stairs.png)"
        :position "absolute"
        :top 0
        :left 0
        :z-index 100}]]

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
