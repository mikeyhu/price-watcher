(ns price-watcher.collector-test
  (:require [clojure.test :refer :all]
            [price-watcher.collector :as collector]))

(def response {:status 200,
               :body   {:data {:currency "ETH",
                               :rates    {:GBP "200.2" :USD "100.1"}}}})

(deftest retrieves-currency
  (testing "GBP"
    (is (= "200.2" (collector/select-currency-from response :GBP)))
    )
  (testing "USD"
    (is (= "100.1" (collector/select-currency-from response :USD)))
    )
  (testing "EUR"
    (is (= nil (collector/select-currency-from response :EUR)))
    ))

(deftest collects-price
  (with-redefs [collector/retrieve-from-api (fn [to] response)]
    (testing
      (is (= 200.2 (collector/collect-price :ETH :GBP))))))
