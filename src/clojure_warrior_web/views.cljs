(ns clojure-warrior-web.views
  (:require
    [clojure-warrior-web.views.level :refer [level-view]]
    [clojure-warrior-web.views.sidebar :refer [sidebar-view]]
    [clojure-warrior-web.views.editor :refer [editor-view]]
    [clojure-warrior-web.styles :refer [styles-view]]))

(defn app-view []
  [:div.app
   [styles-view]
   [sidebar-view]
   [editor-view]
   [level-view]])
