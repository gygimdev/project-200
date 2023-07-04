ALTER TABLE
    household
ADD COLUMN
    member_id BIGINT
;

ALTER TABLE
    household
ADD FOREIGN KEY
    (member_id)
REFERENCES
    Member(member_id)
;
