DROP TABLE IF EXISTS visitors;
DROP TABLE IF EXISTS conventions;

CREATE TABLE visitors (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255),
  last_name VARCHAR(255) NOT NULL,
  street VARCHAR(255),
  zip_code VARCHAR(10),
  city VARCHAR(255),
  country VARCHAR(255),
  date_of_birth DATETIME,
  telephone_number VARCHAR(255),
  email_address VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE conventions (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  location VARCHAR(255) NOT NULL,
  convention_number INT NOT NULL,
  start_date DATETIME NOT NULL,
  end_date DATETIME NOT NULL,
  PRIMARY KEY (id)
);
