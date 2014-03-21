Date hashing is broken (uses goog/getUID) in ClojureScript, which has the nasty side effect of breaking sets badly, for one.

Context:
http://dev.clojure.org/jira/browse/CLJS-523
http://dev.clojure.org/jira/browse/CLJS-523

```clojure
;; This is one possible fix:

(extend-type js/Date
  IHash
  (-hash [o]
    (hash (pr-str o))))
```
