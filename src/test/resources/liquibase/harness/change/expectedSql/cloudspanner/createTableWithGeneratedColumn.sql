CREATE TABLE table_test_generated_column (id INT64 NOT NULL, FirstName STRING(200), LastName STRING(200), FullName STRING(400) AS (FirstName || ' ' || LastName) STORED) PRIMARY KEY (id)