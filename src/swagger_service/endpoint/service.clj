(ns swagger-service.endpoint.service
  (:require [clojure.java.io :as io]
            [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [swagger-service.component.db :as db]))

(s/defschema User
  {:id String
   (s/optional-key :first_name) String
   (s/optional-key :last_name) String
   (s/optional-key :email) String
   (s/optional-key :pass) String})

(defn service-endpoint [config]
  (api
    (ring.swagger.ui/swagger-ui
     "/swagger-ui")
    (swagger-docs
     {:info {:title "User API"}})
    (context* "/api" []
              :tags ["users"]

              (GET* "/users" []
                    :return  [User]
                    :summary "returns the list of users"
                    (ok (db/get-users {} (:db config))))

              (GET* "/user/:id"   []
                    :return       User
                    :path-params [id :- String]
                    :summary      "returns the user with a given id"
                    (ok (db/get-user {:id id} (:db config))))

              (POST* "/authenticate" []
                     :return      Boolean
                     :body-params [user :- User]
                     :summary     "authenticates the user using the id and pass."
                     (ok (db/authenticate user (:db config))))

              (POST* "/user"      []
                     :return      Long
                     :body-params [user :- User]
                     :summary     "creates a new user record."
                     (ok (db/create-user-account! user (:db config))))

              (DELETE* "/user"    []
                     :return      Long
                     :body-params [id :- String]
                     :summary     "deletes the user record with the given id."
                     (ok (db/delete-user! {:id id} (:db config)))))))
