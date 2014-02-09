DROP TABLE IF EXISTS visitors;

CREATE TABLE visitors (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255),
  last_name VARCHAR(255) NOT NULL,
  address VARCHAR(255),
  zip_code VARCHAR(10),
  city VARCHAR(255),
  country VARCHAR(255),
  date_of_birth DATETIME,
  PRIMARY KEY (id)
);