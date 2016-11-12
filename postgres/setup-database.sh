#!/bin/bash

TEST=`gosu postgres postgres --single <<- EOSQL
   SELECT 1 FROM pg_database WHERE datname='$DB_NAME';
EOSQL`

echo "******CREATING DOCKER DATABASE******"
if [[ $TEST == "1" ]]; then
    # database exists
    # $? is 0
    exit 0
else
gosu postgres postgres --single -jE <<- EOSQL
   CREATE ROLE $DB_USER WITH LOGIN ENCRYPTED PASSWORD '${DB_PASS}' CREATEDB;
EOSQL

gosu postgres postgres --single -jE <<- EOSQL
   CREATE DATABASE $DB_NAME WITH OWNER $DB_USER TEMPLATE template0 ENCODING 'UTF8';
EOSQL
echo "******DATABASE DONE******"

gosu postgres postgres --single -jE $DB_NAME <<- EOSQL
   GRANT ALL PRIVILEGES ON DATABASE $DB_NAME TO $DB_USER;
   CREATE SCHEMA $SCHEMA AUTHORIZATION postgres;
   CREATE TABLE IF NOT EXISTS $SCHEMA.contactsdemo 
(
  id bigserial NOT NULL,
  name character varying(36),
  phone character varying(10),
  email character varying(100),
  CONSTRAINT contacts_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE $SCHEMA.contactsdemo OWNER TO $DB_USER;
INSERT INTO $SCHEMA.contactsdemo(id, name, phone, email)
    VALUES (1, 'john smith', 1234567890, 'jsmith@yahoo.com');

INSERT INTO $SCHEMA.contactsdemo(id, name, phone, email)
    VALUES (2, 'alpha beta', 1234567890, 'abeta@yahoo.com');
    
INSERT INTO $SCHEMA.contactsdemo(id, name, phone, email)
    VALUES (3, 'tom hanks', 1234567890, 'thanks@yahoo.com'); 
EOSQL
echo "******GRANT DONE******"




echo "******TABLE DONE******"

fi

echo ""
echo "******DOCKER DATABASE CREATED******"
