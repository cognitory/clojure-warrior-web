(ns clojure-warrior-web.views.code
  (:require
    [clojure.string :as string]
    [zprint.core :refer [zprint-str]]))

(defn code-view [code]
  [:div {:class (string/join " "  ["CodeMirror" "cm-s-default" "cm-s-railscasts"])
         :ref (fn [el]
                (when (not (nil? el))
                  (js/CodeMirror.runMode
                    (zprint-str code 25 {:style :community
                                         :parse-string-all? true
                                         :parse {:interpose "\n"}
                                         :map {:comma? false
                                               :force-nl? true}})
                    "clojure"
                    el)))}])

