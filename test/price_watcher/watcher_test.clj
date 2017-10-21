(ns price-watcher.watcher-test
  (:require [clojure.test :refer :all]
            [clj-time.core :as t]
            [price-watcher.watcher :as w]
            [price-watcher.history :as h]))

(deftest create-timestamp
  (with-redefs [t/now (fn [] (t/date-time 2017 10 21))]
    (is (= 1508544000000 (w/timestamp)))
    ))

(deftest key-by-timestamp
  (with-redefs [t/now (fn [] (t/date-time 2017 10 21))]
    (let [kv (w/with-timestamp 7)]
      (is (= 7 (get kv 1508544000000)))
      )))

(deftest adds-price-to-history
  (with-redefs [t/now (fn [] (t/date-time 2017 10 21))]
    (let [store (h/init)
          value 7
          updated-store (w/add-price-to-history store value)]
      (is (= 7 (val (h/most-recent updated-store))))
      )))

