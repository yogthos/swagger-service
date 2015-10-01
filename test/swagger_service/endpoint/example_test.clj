(ns swagger-service.endpoint.example-test
  (:require [com.stuartsierra.component :as component]
            [clojure.test :refer :all]
            [kerodon.core :refer :all]
            [kerodon.test :refer :all]
            [swagger-service.endpoint.example :as example]))

(def handler
  (example/example-endpoint {}))

(deftest smoke-test
  (testing "index page exists"
    (-> (session handler)
        (visit "/")
        (has (status? 200) "page exists"))))
