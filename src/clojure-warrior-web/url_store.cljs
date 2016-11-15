(ns clojure-warrior-web.url-store
  (:require
    [clojure.string :as string]
    [goog.crypt.base64 :as base64]))

(defn save! [code]
  (js/history.replaceState nil "" (str "#" (base64/encodeString code))))

(defn fetch []
  (let [encoded-code (.-hash js/window.location)]
    (when (not (string/blank? encoded-code))
      (base64/decodeString (.substr encoded-code 1)))))
