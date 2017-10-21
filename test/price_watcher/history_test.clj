(ns price-watcher.history-test
  (:require [clojure.test :refer :all]
            [price-watcher.history :as history]))


(def empty-store (history/init))

(deftest creates-empty-store
  (is (empty? (:recent empty-store))))

(deftest stores-price-by-time
  (testing
    (let [data {1449088876591 1}
          updated-store (history/with empty-store data)]
      (is (= 1 (count updated-store))))
    ))

(deftest retrieves-most-recent
  (testing "data inserted in order"
    (let [data {1449088876591 1 1449088876592 2}
          store (history/with empty-store data)]
      (is (= [1449088876592 2] (history/most-recent store)))))

  (testing "data inserted out of order"
    (let [data {1449088876592 2 1449088876591 1}
          store (history/with empty-store data)]
      (is (= [1449088876592 2] (history/most-recent store)))))
  )