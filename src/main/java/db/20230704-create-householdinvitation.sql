CREATE TABLE household_invitation (
  household_invitation_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  household_id BIGINT,
  member_id BIGINT,
  is_approved BOOLEAN NOT NULL DEFAULT FALSE,
  created_at TIMESTAMP,
  created_by VARCHAR(255),
  updated_at TIMESTAMP,
  updated_by VARCHAR(255),
  FOREIGN KEY (household_id) REFERENCES household (household_id),
  FOREIGN KEY (member_id) REFERENCES member (member_id)
);