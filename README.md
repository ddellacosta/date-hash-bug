Date hashing is broken (uses goog/getUID) in ClojureScript, which has the nasty side effect of breaking sets badly, for one.

Context:
* [IHash not extended to js/Date](http://dev.clojure.org/jira/browse/CLJS-523)
* [Allow hashtable lookup used for numbers and strings to be extended to other built-in types](http://dev.clojure.org/jira/browse/CLJS-525)


```clojure
;; This is one possible fix:

(extend-type js/Date
  IHash
  (-hash [o]
    (hash (pr-str o))))
```
