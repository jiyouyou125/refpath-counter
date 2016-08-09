(defproject hello-world "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories {"conjars" "http://conjars.org/repo"}
  :dependencies [
      [org.clojure/clojure "1.8.0"]
      [cascalog/cascalog-core "3.0.0"]
    ]
  :profiles { :dev 
    {
      :dependencies [
          [org.apache.hadoop/hadoop-core "1.2.1"]
          [cascalog/midje-cascalog "3.0.0"]
        ]
      }
      :plugins [
        [lein-midje "3.0.1"]
      ]
  }
  :jvm-opts ["-Xms768m" "-Xmx768m"]
  :main main.core
  )
