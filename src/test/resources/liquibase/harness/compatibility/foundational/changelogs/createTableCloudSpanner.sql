--liquibase formatted sql

--changeset spanner-liquibase-tests:1 -context:test_context -labels:test_label
--comment: test_comment
CREATE TABLE test_table_sql (test_column INT64) PRIMARY KEY (test_column);

--rollback DROP TABLE test_table_sql;