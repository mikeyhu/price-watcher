(ns price-watcher.collector
  (:require [clj-http.client :as client]))

(defn select-currency-from [json currency]
  (-> json :body :data :rates currency)
  )