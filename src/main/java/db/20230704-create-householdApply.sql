CREATE TABLE household_apply (
  household_apply_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  household_id BIGINT,
  member_id BIGINT,
  is_approved BOOLEAN NOT NULL DEFAULT FALSE,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  FOREIGN KEY (household_id) REFERENCES household (household_id),
  FOREIGN KEY (member_id) REFERENCES member (member_id)
);