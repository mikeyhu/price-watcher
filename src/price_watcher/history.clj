(ns price-watcher.history
  (:require [clojure.java.io :as io])
  (:import (java.io PushbackReader)))

(defn init []
  {:recent (sorted-map-by >)})

(defn with
  [db data]
  (merge
    db
    {:recent (merge (:recent db) data)}))

(defn most-recent
  [db]
  (first (:recent db)))


;saving and loading
(defn save-to
  [location data]
  (with-open [w (clojure.java.io/writer location)]
    (binding [*out* w]
      (pr data))))

(defn file-exists
  [location]
  (.exists (io/as-file location)))

(defn history-as-sorted
  [db]
  (with (init) (:recent db)))

(defn load-from
  [location]
  (if (file-exists location)
    (with-open [r (PushbackReader. (clojure.java.io/reader location))]
      (binding [*read-eval* false]
        (history-as-sorted (read r))))
    (init)))

