(ns clojure-warrior-web.views.sidebar
  (:require
    [clojure.string :as string]
    [reagent.core :as r]
    [clojure-warrior-web.views.code :refer [code-view]]))

(def commands
  [[(symbol "; warrior commands")
    '[:walk :forward]     ; Move in given direction
    '[:attack :forward]   ; Attack the unit in given direction
    '[:rest]              ; Gain 10% of max health back, but do nothing more
    '[:rescue :forward]   ; Rescue a captive from his chains (earning 50 points) in given direction
    '[:pivot]             ; Turn around
    '[:shoot :forward]    ; Shoot your bow & arrow in a given direction
    ]

   [(symbol "; sample location state")
   '{:type :enemy
     :health 20 
     :enemy? true 
     :melee? true 
     :ranged? true
     :direction :west}]

   [(symbol "; helper functions")
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
    '(distance-to state [4 0])]

   [(symbol "; clojure functions")
    '(defn fn-name [args] ...)
    '(get {} :key)
    '(get-in {} [:foo :bar])
    '(if condition
       true-result
       false-result)
    '(cond
       condition-1
       result-1
       condition-2
       result-2
       ...)]])

(defn sidebar-view []
  (into [:div.sidebar]
        (for [group commands]
          [code-view (string/join "\n" group)])))

