(ns price-watcher.collector-test
  (:require [clojure.test :refer :all]
            [price-watcher.collector :as collector]))

(def response {:status 200,
               :body   {:data {:currency "ETH",
                               :rates    {:GBP "200.2" :USD "100.1"}}}})

(deftest retrieves-currency
  (testing "gbp"
    (is (= "200.2" (collector/select-currency-from response :GBP)))
    )
  (testing "usd"
    (is (= "100.1" (collector/select-currency-from response :USD)))
    )
  (testing "eur"
    (is (= nil (collector/select-currency-from response :EUR)))
    )
  )