(ns price-watcher.history-test
  (:require [clojure.test :refer :all]
            [price-watcher.history :as history])
  (:import (java.util UUID)))

(defn uuid [] (str (UUID/randomUUID)))

(def empty-store (history/init))

(deftest creates-empty-store
  (is (empty? (:recent empty-store))))

(deftest stores-price-by-time
  (testing
    (let [data {1449088876591 1}
          updated-store (history/with empty-store data)]
      (is (= 1 (count updated-store))))))

(deftest retrieves-most-recent
  (testing "data inserted in order"
    (let [data {1449088876591 1 1449088876592 2}
          store (history/with empty-store data)]
      (is (= [1449088876592 2] (history/most-recent store)))))

  (testing "data inserted out of order"
    (let [data {1449088876592 2 1449088876591 1}
          store (history/with empty-store data)]
      (is (= [1449088876592 2] (history/most-recent store))))))

(deftest checks-file-for-existance
  (testing
    (is (history/file-exists "/etc"))))

(deftest checks-file-for-non-existance
  (testing
    (is (not (history/file-exists "/bob")))))

(deftest can-save-and-load-some-data
  (testing "saving and loading"
    (let [test-file (str "/tmp/" (uuid) ".cljdata")
          test-data `(1 2 3)]
      (history/save-to test-file test-data)
      (is (= test-data (history/load-from test-file))))))

(deftest loads-initialized-store-from-not-existing-file
  (testing "saving and loading"
    (let [test-file (str "/tmp/" (uuid) ".cljdata")]
      (is (= (history/init) (history/load-from test-file))))))