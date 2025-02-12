CREATE TABLE vdt_create_table_test (id INT64 NOT NULL, col_json JSON, col_float32 FLOAT32, col_array_bool ARRAY<BOOL>, col_array_string ARRAY<STRING(100)>, col_array_byte ARRAY<BYTES(100)>, col_array_date ARRAY<DATE>, col_array_timestamp ARRAY<TIMESTAMP>, col_array_float64 ARRAY<FLOAT64>, col_array_float32 ARRAY<FLOAT32>, col_array_int ARRAY<INT64>, col_array_json ARRAY<JSON>) PRIMARY KEY (id)