DROP TABLE IF EXISTS convention_attendances;
DROP TABLE IF EXISTS vehicles;
DROP TABLE IF EXISTS visitors;
DROP TABLE IF EXISTS conventions;

CREATE TABLE conventions (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  location VARCHAR(255) NOT NULL,
  convention_number INT NOT NULL,
  start_date DATETIME NOT NULL,
  end_date DATETIME NOT NULL,
  PRIMARY KEY (id)
);

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

CREATE TABLE vehicles (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  type VARCHAR(255),
  license_plate_number VARCHAR(255),
  date_of_manufacture DATETIME,
  PRIMARY KEY (id)
);

CREATE TABLE convention_attendances (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  convention_id BIGINT(20) NOT NULL,
  visitor_id BIGINT(20) NOT NULL,
  vehicle_id BIGINT(20),
  CONSTRAINT FK_convention_attendances_convention_id FOREIGN KEY (convention_id) REFERENCES conventions (id),
  CONSTRAINT FK_convention_attendances_visitor_id FOREIGN KEY (visitor_id) REFERENCES visitors (id),
  CONSTRAINT FK_convention_attendances_vehicle_id FOREIGN KEY (vehicle_id) REFERENCES vehicles (id),
  PRIMARY KEY (id)
);
