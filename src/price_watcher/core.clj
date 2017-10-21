(ns price-watcher.core
  (:gen-class)
  (:require [price-watcher.collector :as collector]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (collector/collect-price :ETH :GBP)))
