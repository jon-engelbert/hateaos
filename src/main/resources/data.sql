insert into customer(name) SELECT 'jon'
WHERE NOT EXISTS (SELECT name FROM customer WHERE name = 'deron');
insert into customer(name) SELECT 'deron'
WHERE NOT EXISTS (SELECT name FROM customer WHERE name = 'deron');
insert into customer(name) SELECT 'avi'
WHERE NOT EXISTS (SELECT name FROM customer WHERE name = 'avi');
insert into customer(name) SELECT 'carol'
WHERE NOT EXISTS (SELECT name FROM customer WHERE name = 'carol');
insert into flight(name) SELECT ('A')
WHERE NOT EXISTS (SELECT name FROM flight WHERE name = 'A');
insert into flight(name) SELECT ('B')
WHERE NOT EXISTS (SELECT name FROM flight WHERE name = 'B');
insert into customer_flights(flight_id, customer_id) SELECT 1, 1
WHERE NOT EXISTS (SELECT * FROM customer_flights WHERE flight_id = 1 AND customer_id = 1);
insert into customer_flights(flight_id, customer_id) SELECT 1, 2
WHERE NOT EXISTS (SELECT * FROM customer_flights WHERE flight_id = 1 AND customer_id = 2);
insert into customer_flights(flight_id, customer_id) SELECT 2, 3
WHERE NOT EXISTS (SELECT * FROM customer_flights WHERE flight_id = 2 AND customer_id = 3);
insert into customer_flights(flight_id, customer_id) SELECT 2, 4
WHERE NOT EXISTS (SELECT * FROM customer_flights WHERE flight_id = 2 AND customer_id = 4);
