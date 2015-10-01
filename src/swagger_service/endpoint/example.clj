(ns swagger-service.endpoint.example
  (:require [compojure.core :refer :all]))

(defn example-endpoint [config]
  (routes
   (GET "/" [] "Hello World")))
