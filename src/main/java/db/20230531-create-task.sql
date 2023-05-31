CREATE TABLE task (
  task_id BIGINT NOT NULL AUTO_INCREMENT,
  content VARCHAR(255),
  due_date TIMESTAMP(6) NOT NULL,
  name VARCHAR(25) NOT NULL,
  status VARCHAR(255) NOT NULL,
  created_at TIMESTAMP(6),
  updated_at TIMESTAMP(6),
  household_id BIGINT,
  member_id BIGINT,
  PRIMARY KEY (task_id),
  FOREIGN KEY (household_id) REFERENCES household (household_id),
  FOREIGN KEY (member_id) REFERENCES member (member_id)
);