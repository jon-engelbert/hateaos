--SET MODE Postgresql;
-- -----------------------------------------------------
-- Table `customer`
-- -----------------------------------------------------

--drop table if exists customer_flights;
--drop table if exists customer ;
--drop table if exists flight ;

create table if not exists customer (
  id SERIAL PRIMARY KEY,
  name varchar(45) not null
  );
--COMMENT = 'maintains customers';

-- -----------------------------------------------------
-- Table `flight`
-- -----------------------------------------------------


create table if not exists flight (
  id SERIAL PRIMARY KEY,
  name varchar(45) not null
  );
--COMMENT = 'maintains flight details';


--create table customer_flights (
--  `customer_id` INT UNSIGNE NOT NULL ,
--  `flight_id` INT UNSIGNED NOT NULL ,
--  PRIMARY KEY (`customer_id`, `flight_id`) ,
--  foreign key (customer_id) references customer(id),
--  foreign key (flight_id) references flight(id)
--);
create table if not exists customer_flights (
  customer_id INT references customer(id) ON UPDATE CASCADE ON DELETE CASCADE ,
  flight_id INT references flight(id) ON UPDATE CASCADE,
  constraint customer_flight_pkey PRIMARY KEY (customer_id, flight_id) 
);


