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

(reg-sub
  :history
  (fn [state]
    (get-in state [:history])))

(reg-sub
  :turn-count
  (fn [state]
    (count (state :history))))

(reg-sub
  :turn
  (fn [state]
    (state :turn)))


