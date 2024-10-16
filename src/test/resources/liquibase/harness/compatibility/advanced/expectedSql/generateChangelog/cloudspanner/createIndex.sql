CREATE TABLE test_table (id INT64, test_column INT DEFAULT NULL NULL) PRIMARY KEY (id)
CREATE INDEX idx_first_name ON test_table(id)