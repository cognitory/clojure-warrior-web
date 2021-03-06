(ns clojure-warrior-web.events
  (:require
    [clojure.string :as string]
    [re-frame.core :refer [reg-fx reg-event-db reg-event-fx dispatch]]
    [zprint.core :refer [zprint-str]]
    [clojure-warrior-web.eval :refer [eval-code]]
    [clojure-warrior-web.url-store :as url-store]
    [clojure-warrior.levels :as levels]
    [clojure-warrior.core :as cljw]))

(reg-fx
  :eval
  (fn [code]
    (eval-code
      [(str '(ns clojure-warrior-web.user
               (:require
                 [re-frame.core :as r]
                 [clojure-warrior.core :as cljw]
                 [clojure-warrior.levels :as levels])))
       (str '(set! *print-fn*
                   (fn [& args]
                     (r/dispatch [:console-log args]))))
       (str '(set! *print-err-fn*
                   (fn [& args]
                     (r/dispatch [:console-error args]))))
       ; manually aliasing because :refer is not working with eval-str
       (str '(do
               (def look cljw/look)
               (def feel cljw/feel)
               (def listen cljw/listen)
               (def stairs cljw/stairs)
               (def say cljw/say)
               (def warrior cljw/warrior)
               (def distance-to cljw/distance-to)
               (def inspect cljw/inspect)))
       (str '(defn enter-the-tower! [user-code]
               (r/dispatch [:set-history (cljw/play-levels! levels/levels user-code)])))
       code
       (str '(enable-console-print!))]
      (fn [{:keys [error value] :as x}]
        (if error
          (do
            (dispatch [:console-error error])
            (js/console.error (str error))))))))

(defn format-code [code]
  (zprint-str code 50 {:style :community
                       :parse-string-all? true
                       :parse {:interpose "\n\n"}
                       :fn-map {"defn" :fn}
                       :map {:comma? false
                             :force-nl? true}}))

(def sample-code
  (->> ['(defn warrior-code [state]
           (say (:health (warrior state)))
           [:walk :forward])
        '(enter-the-tower! warrior-code)]
       (map pr-str)
       (string/join "\n")
       format-code))

(reg-event-fx
  :initialize
  (fn [_ _]
    (let [code (or (url-store/fetch) sample-code)]
      {:db {:log []
            :code code
            :turn 0
            :history []}
       :dispatch [:store-in-url code]})))

(reg-event-fx
  :set-history
  (fn [{state :db} [_ history]]
    {:db (assoc state
           :history history
           :turn 0)}))

(reg-event-fx
  :update-code
  (fn [{state :db} [_ code]]
    {:db (assoc state :code code)
     :dispatch [:store-in-url code]}))

(reg-event-fx
  :run-code
  (fn [{state :db}]
    {:db (assoc state :log [])
     :eval (state :code)}))

(reg-event-fx
  :store-in-url
  (fn [_ [_ code]]
    (url-store/save! code)
    {}))

(reg-event-db
  :console-log
  (fn [state [_ args]]
    (update state :log conj {:log args})))

(reg-event-db
  :console-error
  (fn [state [_ error]]
    (update state :log conj {:error error})))

(reg-event-db
  :set-turn
  (fn [state [_ turn]]
    (assoc state :turn turn)))

