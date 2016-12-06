(ns clojure-warrior-web.views.sidebar
  (:require
    [reagent.core :as r]))

(def commands
  [(symbol "; helper functions")
   ""
   ; return the warrior
   '(warrior state)

   ; return the stairs
   '(stairs state)

   ; return list of all enemies and captives
   '(listen state)

   ; returns the space at the position
   '(inspect state [4 0])

   ; return space 1 unit in given direction from warrior
   '(feel state :forward)

   ; returns first non-empty space in given direction from warrior
   '(look state :forward)

   ; returns numbers of steps from the warriror to a position
   '(distance-to state [4 0])
   ""
   (symbol "; warrior commands")
   ""
   '[:walk :forward]     ; Move in given direction
   '[:attack :forward]   ; Attack the unit in given direction
   '[:rest]              ; Gain 10% of max health back, but do nothing more
   '[:rescue :forward]   ; Rescue a captive from his chains (earning 50 points) in given direction
   '[:pivot]             ; Turn around
   '[:shoot :forward]    ; Shoot your bow & arrow in a given direction
   ""
   (symbol "; clojure functions")
   ""
   '(print ...)
   '(defn name [args] ...)])

(defn sidebar-view []
  (r/create-class
    {:component-did-mount
     (fn []
       (js/CodeMirror (.. js/document (getElementById "command-list"))
                      (clj->js {:theme "railscasts"
                                :mode "clojure"
                                :readOnly true
                                :value (clojure.string/join "\n" commands)})))
     :reagent-render
     (fn []
       [:div.sidebar {:id "command-list"}])}))
