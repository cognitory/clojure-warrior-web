(ns clojure-warrior-web.views
  (:require
    [clojure-warrior-web.views.level :refer [level-view]]
    [clojure-warrior-web.styles :refer [styles-view]]))

(defn app-view []
  [:div
   [level-view]
   [styles-view]])
