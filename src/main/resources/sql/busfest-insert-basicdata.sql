INSERT INTO visitors (first_name, last_name, created_at, updated_at) VALUES ('Fritz', 'Müller', now(), now());
INSERT INTO visitors (first_name, last_name, created_at, updated_at) VALUES ('Franz', 'Maier', now(), now());
INSERT INTO visitors (first_name, last_name, created_at, updated_at) VALUES ('Leif', 'Bladt', now(), now());

INSERT INTO vehicles (id, type, created_at, updated_at) VALUES (1, 'T3', now(), now());

INSERT INTO conventions (id, location, convention, starts_on, ends_on, overnight_cost_bus, overnight_cost_caravan, day_visitor_cost, created_at, updated_at) VALUES (6, 'Kirchzarten', '10. Schwarzwälder VW-Bustreffen', '2013-04-12', '2013-04-14', 18.00, 5.00, 5.00, now(), now());
INSERT INTO conventions (id, location, convention, starts_on, ends_on, overnight_cost_bus, overnight_cost_caravan, day_visitor_cost, created_at, updated_at) VALUES (7, 'Kirchzarten', '11. Schwarzwälder VW-Bustreffen', '2014-04-04', '2014-04-06', 18.00, 5.00, 5.00, now(), now());

INSERT INTO provisions (id, description, cost, convention_id, created_at, updated_at) VALUES (1, 'Roggenweck', 0.55, 6, now(), now());
INSERT INTO provisions (id, description, cost, convention_id, created_at, updated_at) VALUES (2, 'Kaiser-/Spitzweck', 0.40, 6, now(), now());
INSERT INTO provisions (id, description, cost, convention_id, created_at, updated_at) VALUES (3, 'Brezel', 0.80, 6, now(), now());
INSERT INTO provisions (id, description, cost, convention_id, created_at, updated_at) VALUES (4, 'Roggenweck', 0.55, 7, now(), now());
INSERT INTO provisions (id, description, cost, convention_id, created_at, updated_at) VALUES (5, 'Kaiser-/Spitzweck', 0.40, 7, now(), now());
INSERT INTO provisions (id, description, cost, convention_id, created_at, updated_at) VALUES (6, 'Brezel', 0.80, 7, now(), now());

INSERT INTO convention_attendances (id, convention_id, visitor_id, vehicle_id, created_at, updated_at) VALUES (1, 6, 3, 1, now(), now());
INSERT INTO convention_attendances (id, convention_id, visitor_id, vehicle_id, created_at, updated_at) VALUES (2, 7, 3, 1, now(), now());
