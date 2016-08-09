(ns main.core)

(require '[cascalog.logic.ops :as c])
(use '[clojure.string :only (join)])
(use 'cascalog.api)
(use 'refpath.core
     'events.core)


(defbufferfn buffer-seqs
  [tuples]

  ; Holy canoli, what's going on here
  [[
      ; 4. remove any remaining conversion seqs - these shouldn't be included in the conversion paths, 
      ; since they terminate them
      (remove seq-has-conversion?
        ; 3. create separate seqs of conversions and not-conversions
        ; the not-conversion seqs will be groups of events leading up to a conversion,
        ; and the conversion seqs will just be bookends between non-conversion seqs
        (partition-by conversion? 
          ; 2. reverse the events back into normal order
          (reverse 
            ; 1. remove any events at the end that aren't conversions;
            ; the removed events won't be significant since they didn't lead to conversion.
            ; `tuples` has been ordered reverse already
            (drop-while not-conversion? tuples))))]]
)

(defn refpath
  [seq]
  (clojure.string/join " > " (collapse-sequence (map #(nth %1 1) seq)))
)

(defmapcatfn make-refpaths
  [paths]
  (map refpath paths)
)

(defn user-consideration-paths 
  [src]
  (<-
    [?uid ?seqs-string]
    (src ?uid ?ts ?ref ?path)
    (:sort ?ts)
    (:reverse true)
    (buffer-seqs ?ts ?ref ?path :> ?seqs-string)
  )
)

(defn refpath-counts
  [src]
  (<-
    [?refpath ?count]
    (src _ ?seqs-string)
    (make-refpaths ?seqs-string :> ?refpath)
    (c/count ?count)
  )
)

; Main function; this is defined in project.clj
(defn -main 
  [& args]
  (?- (stdout) ; now we get to the query
    (refpath-counts (user-consideration-paths events))
  )
)