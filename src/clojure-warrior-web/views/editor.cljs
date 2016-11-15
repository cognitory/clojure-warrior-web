(ns clojure-warrior-web.views.editor
  (:require
    [reagent.core :as r]
    [re-frame.core :refer [subscribe dispatch]]))

(defn better-tab [cm]
  ; based on https://github.com/codemirror/CodeMirror/issues/988#issuecomment-14921785
  (if (.somethingSelected cm)
    (.indentSelection cm "add")
    (.replaceSelection cm
                       (apply str (repeat (.getOption cm "indentUnit") " "))
                       "end"
                       "+input")))

(defn console-view []
  (let [logs (subscribe [:log])]
    (fn []
      (into [:div.console]
            (for [log @logs]
              (cond
                (log :error) [:div.error.message
                              (.. (log :error) -cause -message)]
                (log :log) [:div.log.message
                            (log :log)]))))))

(defn editor-view []
  (let [code (subscribe [:code])]
    (r/create-class
      {:component-did-mount
       (fn []
         (doto (js/CodeMirror (.. js/document (getElementById "editor"))
                              (clj->js {:theme "railscasts"
                                        :mode "clojure"
                                        :extraKeys {"Tab" better-tab}
                                        :autofocus true
                                        :matchBrackets true
                                        :value @code}))
           (.on "change" (fn [editor]
                           (dispatch [:update-code (.getValue editor)])))))
       :reagent-render
       (fn []
         [:div.editor {:on-key-down (fn [e]
                                      (when (and e.ctrlKey (= 13 e.keyCode))
                                        (dispatch [:run-code])))}
          [:button {:on-click (fn []
                                (dispatch [:run-code]))}
           "RUN"]
          [console-view]
          [:div {:id "editor"}]])})))
