(ns price-watcher.watcher-test
  (:require [clojure.test :refer :all]
            [clj-time.core :as t]
            [clj-time.coerce :as tc]
            [price-watcher.watcher :as w]
            [price-watcher.history :as h]))

(def ts tc/to-long)

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

(deftest finds-last-24hours
  (let [amoment (t/date-time 2017 10 21)
        less-than-a-day-ago (t/minus amoment (t/hours 23))
        more-than-a-day-ago (t/minus amoment (t/hours 25))]
    (with-redefs [t/now (fn [] amoment)]

      (testing "returns a subset of the last 24 hours"
        (let [store (h/with (h/init) {(ts amoment)             100
                                      (ts less-than-a-day-ago) 110
                                      (ts more-than-a-day-ago) 120})]
          (is (= {(ts amoment)             100
                  (ts less-than-a-day-ago) 110}
                 (w/last-24hours store)))
          )))))

(deftest finds-difference-over-24-hours
  (let [amoment (t/date-time 2017 10 21)
        oldest (t/minus amoment (t/hours 23))
        newest (t/minus amoment (t/hours 1))]

    (testing "finds a percentage difference between oldest and newest"
      (let [period {(ts newest) 180 (ts oldest) 200}]
        (is (= 0.9 (w/difference-over-time period)))
        ))))