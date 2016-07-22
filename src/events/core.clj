(ns events.core)

(use '[clojure.string :only (join)])
(use 'cascalog.api)
(require '[cascalog.logic.ops :as c])

(def events
  [
    [1 "p" 1468978529 "/products/1"]
    [1 "t" 1468978583 "/products/1"]
    [1 "f" 1468978576 "/products/1"]
    [1 "/products/1" 1468978596 "/thank_you"]
    [2 "t" 1468978583 "/products/1"]
    [2 "f" 1468978529 "/products/1"]
    [2 "/products/1" 1468978596 "/thank_you"]
    [3 "t" 1468978583 "/products/1"]
    [3 "f" 1468978529 "/products/1"]
    [3 "/products/1" 1468978596 "/thank_you"]
    [4 "t" 1468978587 "/products/1"]
    [4 "f" 1468978531 "/products/1"]
    [4 "/products/1" 1468978602 "/thank_you"]
    [5 "t" 1468978531 "/products/1"]
    [5 "f" 1468978587 "/products/1"]
    [5 "/products/1" 1468978602 "/thank_you"]
  ]
  )

(defaggregateop agg-string
  "Aggregate a vector of strings into a string separated by pipes |"
  ([] "") ; initial value of an empty string
  ([state val] [(clojure.string/join "|" [(first state) val])]) ; aggregate by joining state with the next val using pipes
  ([state] [state]) ; return the sate to inject into the next pass
  )


; Main function; this is defined in project.clj
(defn -main [& args]
  ; Holy canoli, what's going on here
  (let [
        user-refpaths ; use the 'let' form to create a subquery 'user-refpaths'
        (<- [?refpath ?uid] ; group by ?uid, return the fields ?refpath and ?uid
          
          (events ?uid ?ref ?ts ?path) ; extract ?uid ?ref ?ts ?path from the 'events' generator
          (agg-string ?ref :> ?refpath) ; bind the aggregate ?ref to ?refpath (within the group ?uid, define ?refpath as the agg-string of ?ref)
          (:sort ?ts) ; sort all fields by ?ts
        )

        refpath-counts ; create another subquery 'refpath-counts'
        (<- [?refpath ?count] ; group by ?refpath and return ?refpath and ?count
          (user-refpaths ?refpath _) ; call the user-refpaths subquery? macro? wtf is it? defining ?refpath; ignore ?uid
          (c/count :> ?count) ; bind ?count to the count of (ignored) ?uid subgroups from user-refpaths
        )
      ]

    (?- (stdout) ; now we get to the query
      refpath-counts ; bind refpath-counts to the stdout tap
    )
  ))
