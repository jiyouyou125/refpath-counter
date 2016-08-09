(ns events.core)

(def events
  [
    [1 1468978529 "p" "/products/1"]
    [1 1468978583 "t" "/products/1"]
    [1 1468978576 "f" "/products/1"]
    [1 1468978596 "f" "/thank_you"]
    [2 1468978583 "t" "/products/1"]
    [2 1468978529 "f" "/products/1"]
    [2 1468978596 "f" "/thank_you"]
    [3 1468978583 "t" "/products/1"]
    [3 1468978529 "f" "/products/1"]
    [3 1468978596 "f" "/thank_you"]
    [4 1468978587 "t" "/products/1"]
    [4 1468978531 "f" "/products/1"]
    [4 1468978602 "t" "/thank_you"]
    [5 1468978531 "t" "/products/1"]
    [5 1468978587 "f" "/products/1"]
    [5 1468978602 "f" "/thank_you"]

  ]
)

(def conversion-paths
  [
    "/thank-you"
    "/thank_you"
    "/thanks_you"
    "/thanks"
    "/c"
    "/conversion"
    "/done"
  ]
)

(defn conversion? 
  [event]
  (true? (some (fn [v] (= v (last event)) ) conversion-paths))
)

(defn not-conversion?
  [event]
  (not(conversion? event))
)

(defn seq-has-conversion?
  [seq]
  (some conversion? seq) 
)