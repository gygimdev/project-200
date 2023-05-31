CREATE TABLE household (
  household_id BIGINT NOT NULL PRIMARY KEY,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  name VARCHAR(25) NOT NULL
);