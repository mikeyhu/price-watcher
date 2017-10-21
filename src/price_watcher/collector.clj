(ns price-watcher.collector
  (:require [clj-http.client :as client]))

(defn retrieve-from-api
  [type]
  (client/get "https://api.coinbase.com/v2/exchange-rates?currency=ETH" {:as :json}))

(defn select-currency-from
  [json currency]
  (-> json :body :data :rates currency))

(defn collect-price
  [from to]
  (Double/parseDouble
    (select-currency-from (retrieve-from-api from) to)))