CREATE TABLE secondarydb.test_table (id INT64, test_column INT NULL) PRIMARY KEY (id);
CREATE INDEX idx_secondary ON secondarydb.test_table(test_column);