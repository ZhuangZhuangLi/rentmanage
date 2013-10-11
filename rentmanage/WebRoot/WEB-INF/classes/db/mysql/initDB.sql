CREATE DATABASE IF NOT EXISTS rentmanage;
GRANT ALL PRIVILEGES ON rentmanage.* TO pc@localhost IDENTIFIED BY 'pc';

USE rentmanage;

drop TABLE IF EXISTS house;
drop TABLE IF EXISTS owners;
drop TABLE IF EXISTS admin;
CREATE TABLE IF NOT EXISTS owners (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login_name VARCHAR(30),
  password VARCHAR(30),
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  address VARCHAR(255),
  city VARCHAR(80),
  telephone VARCHAR(20),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS house (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  createdate DATE, 
  address VARCHAR(30),
  price INT(4) UNSIGNED NOT NULL,
  owner_id INT(4) UNSIGNED NOT NULL,
  INDEX(name),
  FOREIGN KEY (owner_id) REFERENCES owners(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS admin (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login_name VARCHAR(30),
  password VARCHAR(30),
  name VARCHAR(30)
) engine=InnoDB;