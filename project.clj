(defproject price-watcher "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.8.0"]
                 [clj-http "3.7.0"]
                 [cheshire "5.8.0"]
                 [clj-time "0.12.2"]
                 [environ "1.1.0"]
                 ]
  :main ^:skip-aot price-watcher.core
  :target-path "target/%s"
  :profiles {:dev {:plugins [[com.jakemccrary/lein-test-refresh "0.21.1"]]}
             :uberjar {:aot :all}})
