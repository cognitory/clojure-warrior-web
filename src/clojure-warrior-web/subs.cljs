(ns clojure-warrior-web.subs
  (:require
    [re-frame.core :refer [reg-sub]]))

(reg-sub
  :code
  (fn [state]
    (state :code)))

(reg-sub
  :log
  (fn [state]
    (state :log)))
