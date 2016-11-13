

-- Table: iot.contactsdemo

-- DROP TABLE iot.contactsdemo;

CREATE TABLE iot.contactsdemo
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
ALTER TABLE iot.contactsdemo
  OWNER TO postgres;

  
  
  
  INSERT INTO iot.contactsdemo(id, name, phone, email)
    VALUES (1, 'john smith', 1234567890, 'jsmith@yahoo.com');

INSERT INTO iot.contactsdemo(id, name, phone, email)
    VALUES (2, 'alpha beta', 1234567890, 'abeta@yahoo.com');
    
INSERT INTO iot.contactsdemo(id, name, phone, email)
    VALUES (3, 'tom hanks', 1234567890, 'thanks@yahoo.com');