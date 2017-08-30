-- name: create-user!
-- creates a new user record
INSERT INTO users
(id, first_name, last_name, email, pass)
VALUES (:id, :first_name, :last_name, :email, :pass)

-- name: get-user
-- retrieve a user given the id.
SELECT id, first_name, last_name, email FROM users
WHERE id = :id

-- name: get-users
-- retrieve a user given the id.
SELECT id, first_name, last_name, email FROM users

-- name: delete-user!
-- delete a user given the id
DELETE FROM users
WHERE id = :id
