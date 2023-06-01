CREATE TABLE `task` (
  `task_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `due_date` datetime(6) NOT NULL,
  `name` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `status` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `household_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  KEY `FK5rgd30ji8lexkvdw5p5u3mpb2` (`household_id`),
  KEY `FKtisaouhsp1pjc613txc886xfh` (`member_id`),
  CONSTRAINT `FK5rgd30ji8lexkvdw5p5u3mpb2` FOREIGN KEY (`household_id`) REFERENCES `household` (`household_id`),
  CONSTRAINT `FKtisaouhsp1pjc613txc886xfh` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
;