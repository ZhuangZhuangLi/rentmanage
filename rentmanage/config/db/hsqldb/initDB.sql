drop TABLE house IF EXISTS ;
drop TABLE owners IF EXISTS ;
drop TABLE  admin IF EXISTS ;
CREATE TABLE  owners (
  id INTEGER IDENTITY PRIMARY KEY,
  login_name VARCHAR(30),
  password VARCHAR(30),
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  address VARCHAR(255),
  city VARCHAR(80),
  telephone VARCHAR(20)
) ;

CREATE INDEX owners_last_name ON owners (last_name);

CREATE TABLE  house (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(30),
  createdate DATE, 
  address VARCHAR(30),
  price INTEGER NOT NULL,
  owner_id INTEGER NOT NULL
) ;

CREATE TABLE  admin (
  id INTEGER IDENTITY PRIMARY KEY,
  login_name VARCHAR(30),
  password VARCHAR(30),
  name VARCHAR(30)
) ;


ALTER TABLE house ADD CONSTRAINT fk_pets_owners FOREIGN KEY (owner_id) REFERENCES owners (id);