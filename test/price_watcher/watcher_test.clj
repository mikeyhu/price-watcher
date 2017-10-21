(ns price-watcher.watcher-test
  (:require [clojure.test :refer :all]
            [clj-time.core :as t]
            [price-watcher.watcher :as watcher]))

(deftest create-timestamp
  (with-redefs [t/now (fn [] (t/date-time 2017 10 21))]
    (is (= 1508544000000 (watcher/timestamp)))
    ))

(deftest key-by-timestamp
  (with-redefs [t/now (fn [] (t/date-time 2017 10 21))]
    (let [kv (watcher/with-timestamp 7)]
      (is (= 1508544000000 (nth kv 0)))
      (is (= 7 (nth kv 1)))
      )))