CREATE TABLE `item` (
  `item_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `expire_date` datetime(6) NOT NULL,
  `name` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `quantity` varchar(5) COLLATE utf8mb4_general_ci NOT NULL,
  `household_id` bigint DEFAULT NULL,
  `ingredient_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FK7rv92qnpkd0klvkwul9clwlg4` (`household_id`),
  KEY `FKgwuf29cwokp4qc7kh6dd0fjwn` (`ingredient_id`),
  KEY `FKpuyun1nwd8fupsib8ekn7vrpm` (`member_id`),
  CONSTRAINT `FK7rv92qnpkd0klvkwul9clwlg4` FOREIGN KEY (`household_id`) REFERENCES `household` (`household_id`),
  CONSTRAINT `FKgwuf29cwokp4qc7kh6dd0fjwn` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`ingredient_id`),
  CONSTRAINT `FKpuyun1nwd8fupsib8ekn7vrpm` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
;