(ns clojure-warrior-web.events
  (:require
    [re-frame.core :refer [reg-fx reg-event-db reg-event-fx dispatch]]))

(reg-event-fx
  :initialize
  (fn [_ _]
    {}))

