(ns price-watcher.watcher
  (:require [clj-time.core :as t]
            [clj-time.coerce :as tc]
            [price-watcher.history :as history]))

(defn timestamp [] (tc/to-long (t/now)))

(defn with-timestamp [data] {(timestamp) data})

(defn add-price-to-history
  [db price]
  (history/with db (with-timestamp price)))

(defn last-24hours
  [db]
  (let [yesterday (tc/to-long (t/minus (t/now) (t/days 1)))]
    (history/after db yesterday)
    ))