CREATE TABLE `ingredient` (
  `ingredient_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `expire_date` datetime(6) NOT NULL,
  `ingredient_type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `name` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `quantity` varchar(5) COLLATE utf8mb4_general_ci NOT NULL,
  `household_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`ingredient_id`),
  KEY `FK5p6lr6p1ovwa6a29avu0slkwq` (`household_id`),
  KEY `FKlicjlje3ruecgcs3u364qhvke` (`member_id`),
  CONSTRAINT `FK5p6lr6p1ovwa6a29avu0slkwq` FOREIGN KEY (`household_id`) REFERENCES `household` (`household_id`),
  CONSTRAINT `FKlicjlje3ruecgcs3u364qhvke` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
;