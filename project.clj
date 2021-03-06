(defproject clojure-warrior-web "0.0.1"

  :dependencies [[org.clojure/clojure "1.8.0"]

                 [org.clojure/clojurescript "1.9.293"]
                 [re-frame "0.8.0"]
                 [garden "1.3.2"]
                 [clojure-warrior "0.0.1"]
                 [zprint "0.3.2"]]

  :plugins [[lein-figwheel "0.5.8"]
            [lein-cljsbuild "1.1.4"]]

  :figwheel {:server-port 3987
             :reload-clj-files {:clj false
                                :cljc true}}

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :figwheel {:on-jsload "clojure-warrior-web.core/reload"}
                        :compiler {:main "clojure-warrior-web.core"
                                   :asset-path "js/dev/out"
                                   :output-to "resources/public/js/dev/clojure-warrior-web.js"
                                   :output-dir "resources/public/js/dev/out"}}
                       {:id "prod"
                        :source-paths ["src"]
                        :compiler {:main "clojure-warrior-web.core"
                                   :asset-path "./js/prod/out"
                                   :output-to "resources/public/js/prod/clojure-warrior-web.js"
                                   :output-dir "resources/public/js/prod/out"
                                   :optimizations :simple
                                   :pretty-print false}}]})
