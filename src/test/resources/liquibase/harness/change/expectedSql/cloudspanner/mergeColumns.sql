CREATE TABLE full_name_table (first_name STRING(50), last_name STRING(50)) PRIMARY KEY (first_name)
INSERT INTO full_name_table (first_name) VALUES ('John')
UPDATE full_name_table SET last_name = 'Doe' WHERE first_name='John'
INSERT INTO full_name_table (first_name) VALUES ('Jane')
UPDATE full_name_table SET last_name = 'Doe' WHERE first_name='Jane'
ALTER TABLE full_name_table ADD COLUMN full_name STRING(255)
UPDATE full_name_table SET full_name = first_name || ' ' || last_name WHERE 1 = 1
ALTER TABLE full_name_table DROP COLUMN first_name
ALTER TABLE full_name_table DROP COLUMN last_name