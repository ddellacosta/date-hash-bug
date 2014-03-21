(ns date-hash-bug.core
  (:require
   [cljs.reader :as reader]))

(enable-console-print!)

(def test-data "({:date #inst \"2013-11-17T05:14:11.730-00:00\", :id 310} {:date #inst \"2013-09-29T05:26:33.891-00:00\", :id 239} {:date #inst \"2013-09-26T02:10:46.209-00:00\", :id 229} {:date #inst \"2013-05-25T02:32:00.594-00:00\", :id 106} {:date #inst \"2013-09-25T07:02:19.186-00:00\", :id 222} {:date #inst \"2013-09-07T20:38:40.230-00:00\", :id 185} {:date #inst \"2013-09-06T05:11:23.431-00:00\", :id 184} {:date #inst \"2013-05-17T04:06:25.391-00:00\", :id 89} {:date #inst \"2013-09-11T04:44:47.945-00:00\", :id 190} {:date #inst \"2013-09-11T04:44:47.945-00:00\", :id 190} {:date #inst \"2013-09-11T04:44:47.945-00:00\", :id 190} {:date #inst \"2013-09-11T04:44:47.945-00:00\", :id 190})")

;;
;; Uncomment this to fix.
;;
;; (extend-type js/Date
;;   IHash
;;   (-hash [o]
;;     (hash (pr-str o))))
;;

(defn load!
  []
  (let [read-data (into [] (reader/read-string test-data))
        test-set  (set read-data)
        all190s   (filter #(= (:id %) 190) test-set)]
    (println "Count of identical ({:date #inst \"2013-09-11T04:44:47.945-00:00\", :id 190}) hash-maps: " (count all190s))
    (doseq [a all190s] (println a))))

(set! (.-onload js/window) load!)
