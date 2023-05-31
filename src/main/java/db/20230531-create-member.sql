CREATE TABLE member (
  member_id BIGINT NOT NULL AUTO_INCREMENT,
  email VARCHAR(254) NOT NULL,
  password VARCHAR(255) NOT NULL,
  timezone VARCHAR(255) NOT NULL,
  username VARCHAR(25) NOT NULL,
  created_at TIMESTAMP(6),
  updated_at TIMESTAMP(6),
  household_id BIGINT,
  PRIMARY KEY (member_id),
  FOREIGN KEY (household_id) REFERENCES household (household_id)
);