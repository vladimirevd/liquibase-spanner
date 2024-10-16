CREATE TABLE lbcat.test_table (id INT64, test_column INT NULL) PRIMARY KEY (id);
CREATE INDEX idx_first_name ON lbcat.test_table(id);