(ns refpath.core)


; If b is the same as the last element of a, don't push b into a, otherwise return (conj a b)
(defn push-uniq 
  [a b] 
  (if 
    (= (last a) b)
      a 
      (conj a b)
    )
)

; Take an array like ["a" "a" "b" "b" "a"] => ["a" "b" "a"]
(defn collapse-sequence
  [seq]
  (reduce push-uniq [] seq)
)
