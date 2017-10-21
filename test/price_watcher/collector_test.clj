(ns price-watcher.collector-test
  (:require [clojure.test :refer :all]
            [price-watcher.collector :as collector]))

(def response {:status 200,
               :body {:data {:currency "ETH",
                             :rates {:GBP "200.2",}}}})

(deftest retrieves-currency
  (is (= "200.2" (collector/select-currency-from response :GBP)))
  )