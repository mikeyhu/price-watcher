(ns price-watcher.watcher
  (:require [clj-time.core :as t]
            [clj-time.coerce :as tc]))

(defn timestamp [] (tc/to-long (t/now)))

(defn with-timestamp [data] [(timestamp) data])