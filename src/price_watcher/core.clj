(ns price-watcher.core
  (:gen-class)
  (:require [price-watcher.collector :as collector]
            [price-watcher.history :as history]
            [price-watcher.watcher :as watcher]))

(def location (str (System/getProperty "user.dir") "/.price-watcher.cljdata"))

(defn -main
  [& args]
  (let
    [current-data (history/load-from location)
     latest-price (collector/collect-price :ETH :GBP)]
    (history/save-to
      location
      (watcher/add-price-to-history current-data latest-price)
      )))