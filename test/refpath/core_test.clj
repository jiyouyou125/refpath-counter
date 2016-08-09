(ns refpath.core-test
  (:use clojure.test
    [midje sweet cascalog]
    cascalog.logic.testing
    cascalog.api
    refpath.core
  )
)

(deftest uniquely-push-successfully
  (let [
      seq ["a" "b" "c"]
      element "b"
      expected ["a" "b" "c" "b"]
    ]

    (fact (push-uniq seq element) => expected)
  )
)

(deftest uniquely-push-unsuccessfully
  (let [
      seq ["a" "b" "c"]
      element "c"
      expected ["a" "b" "c"]
    ]

    (fact (push-uniq seq element) => expected)
  )
)

(deftest uniquely-push-empty
  (let [
      seq []
      element "c"
      expected ["c"]
    ]

    (fact (push-uniq seq element) => expected)
  )
)

(deftest collapsing-a-sequence
  (let [
      seq ["a" "b" "b" "c"]
      expected ["a" "b" "c"]
    ]

    (fact (collapse-sequence seq) => expected)
  )

)