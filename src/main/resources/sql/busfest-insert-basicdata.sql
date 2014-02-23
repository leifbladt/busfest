INSERT INTO visitors (first_name, last_name, created_at, updated_at) VALUES ('Fritz', 'Müller', now(), now());
INSERT INTO visitors (first_name, last_name, created_at, updated_at) VALUES ('Franz', 'Maier', now(), now());
INSERT INTO visitors (first_name, last_name, created_at, updated_at) VALUES ('Leif', 'Bladt', now(), now());

INSERT INTO conventions (id, location, convention, starts_on, ends_on, overnight_cost_bus, overnight_cost_caravan, day_visitor_cost, created_at, updated_at) VALUES (6, 'Kirchzarten', '10. Schwarzwälder VW-Bustreffen', '2013-04-12', '2013-04-14', 18, 5, 5, now(), now());
INSERT INTO conventions (id, location, convention, starts_on, ends_on, overnight_cost_bus, overnight_cost_caravan, day_visitor_cost, created_at, updated_at) VALUES (7, 'Kirchzarten', '11. Schwarzwälder VW-Bustreffen', '2014-04-04', '2014-04-06', 18, 5, 5, now(), now());

INSERT INTO provisions (id, description, cost, convention_id, created_at, updated_at) VALUES (1, 'Semmel', 1, 7, now(), now());
INSERT INTO provisions (id, description, cost, convention_id, created_at, updated_at) VALUES (2, 'Brezel', 2, 7, now(), now());
