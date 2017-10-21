(ns price-watcher.history)

(defn init [] {:recent (sorted-map-by >)})

(defn with [db data]
  (merge
    db
    {:recent (merge (:recent db) data)}))

(defn most-recent [db]
  (first (:recent db))
  )
