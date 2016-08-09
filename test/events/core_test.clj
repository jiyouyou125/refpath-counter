; (ns events.core-test
;   (:use clojure.test)
;   )

(ns events.core-test
  (:use clojure.test
    [midje sweet cascalog]
    cascalog.logic.testing
    cascalog.api
    events.core
  )
)

(deftest conversion?-with-non-conversion
  (let [
    event [2 "p" 1 "/p/1"]
    ]
    (fact (conversion? event) => false)
  )
)

(deftest conversion?-with-conversion
  (let [
    event [2 "p" 1 "/thank-you"]
    ]
    (fact (conversion? event) => true)
  )
)

(deftest not-conversion?-with-conversion
  (let [
    event [2 "p" 1 "/thank-you"]
    ]
    (fact (not-conversion? event) => false)
  )
)

(deftest not-conversion?-with-not-conversion
  (let [
    event [2 "p" 1 "/p1"]
    ]
    (fact (not-conversion? event) => true)
  )
)

