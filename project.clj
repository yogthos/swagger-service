(defproject swagger-service "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [com.stuartsierra/component "0.3.0"]
                 [compojure "1.4.0"]
                 [duct "0.4.2"]
                 [environ "1.0.1"]
                 [meta-merge "0.1.1"]
                 [ring "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring-jetty-component "0.3.0"]
                 [crypto-password "0.1.3"]
                 [metosin/compojure-api "0.23.1"]
                 [org.xerial/sqlite-jdbc "3.8.11.1"]
                 [yesql "0.5.0"]
                 [migratus "0.8.4"]]
  :plugins [[lein-environ "1.0.1"]
            [lein-gen "0.2.2"]
            [migratus-lein "0.1.7"]]

  :migratus {:store :database
             :db {:classname "org.sqlite.JDBC"
                  :connection-uri "jdbc:sqlite:service-store.db"}}

  :generators [[duct/generators "0.4.2"]]
  :duct {:ns-prefix swagger-service}
  :main ^:skip-aot swagger-service.main
  :target-path "target/%s/"
  :aliases {"gen"   ["generate"]
            "setup" ["do" ["generate" "locals"]]}
  :profiles
  {:dev  [:project/dev  :profiles/dev]
   :test [:project/test :profiles/test]
   :uberjar {:aot :all}
   :profiles/dev  {}
   :profiles/test {}
   :project/dev   {:source-paths ["dev"]
                   :repl-options {:init-ns user}
                   :dependencies [[reloaded.repl "0.2.0"]
                                  [org.clojure/tools.namespace "0.2.11"]
                                  [eftest "0.1.0"]
                                  [kerodon "0.7.0"]]
                   :env {:port 3000}}
   :project/test  {}})
