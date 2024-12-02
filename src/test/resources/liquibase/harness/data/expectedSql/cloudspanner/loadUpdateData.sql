INSERT INTO authors (id, first_name, last_name, email, birthdate, added) SELECT 1, 'Adam', 'Gods', 'test1@example.com', DATE '1000-02-27', TIMESTAMP '2000-02-04T13:45:01.001' FROM UNNEST([1]) WHERE NOT EXISTS (SELECT id FROM authors WHERE id = 1);
UPDATE authors SET added = TIMESTAMP '2000-02-04T13:45:01.001', birthdate = DATE '1000-02-27', email = 'test1@example.com', first_name = 'Adam', last_name = 'Gods' WHERE id = 1;
INSERT INTO authors (id, first_name, last_name, email, birthdate, added) SELECT 7, 'Noah', 'Lamekhs', 'test2@example.com', DATE '2000-02-27', TIMESTAMP '1994-12-10T14:10:01.050001' FROM UNNEST([1]) WHERE NOT EXISTS (SELECT id FROM authors WHERE id = 7);
UPDATE authors SET added = TIMESTAMP '1994-12-10T14:10:01.050001', birthdate = DATE '2000-02-27', email = 'test2@example.com', first_name = 'Noah', last_name = 'Lamekhs' WHERE id = 7;
INSERT INTO authors (id, first_name, last_name, email, birthdate, added) SELECT 8, 'Muhammad', 'Ibn Abdullah', 'test3@example.com', DATE '3000-02-27', TIMESTAMP '2000-12-10T01:03:05.5' FROM UNNEST([1]) WHERE NOT EXISTS (SELECT id FROM authors WHERE id = 8);
UPDATE authors SET added = TIMESTAMP '2000-12-10T01:03:05.5', birthdate = DATE '3000-02-27', email = 'test3@example.com', first_name = 'Muhammad', last_name = 'Ibn Abdullah' WHERE id = 8;