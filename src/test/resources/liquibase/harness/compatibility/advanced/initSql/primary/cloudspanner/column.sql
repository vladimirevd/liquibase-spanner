CREATE TABLE test_table (test_column INT64 PRIMARY KEY (test_table);
ALTER TABLE test_table ADD varcharColumn STRING(25) NULL, ADD intColumn INT NULL, ADD dateColumn date NULL;
UPDATE test_table SET varcharColumn = 'INITIAL_VALUE';
UPDATE test_table SET intColumn = 5;
UPDATE test_table SET dateColumn = '2020-09-21';