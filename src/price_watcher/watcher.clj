(ns price-watcher.watcher
  (:require [clj-time.core :as t]
            [clj-time.coerce :as tc]
            [price-watcher.history :as history]))

(defn timestamp [] (tc/to-long (t/now)))

(defn with-timestamp [data] {(timestamp) data})

(defn add-price-to-history
  [store price]
  (history/with store (with-timestamp price)))
