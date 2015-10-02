(ns swagger-service.component.db
  (:require [yesql.core :refer [defqueries]]
            [com.stuartsierra.component :as component]
            [crypto.password.bcrypt :as password]
            [environ.core :refer [env]]))

(defqueries "sql/queries.sql")

(defn create-user-account! [user db]
  (create-user! (update user :pass password/encrypt) db))

(defn authenticate [user db]
  (boolean
   (when-let [db-user (-> user (get-user db) first)]
     (password/check (:pass user) (:pass db-user)))))

(defrecord DbComponent [connection]
  component/Lifecycle
  (start [component]
         (assoc component :connection connection))
  (stop [component]
        (dissoc component :connection)))

(defn db-component [connection]
  (->DbComponent connection))


