CREATE TABLE `recipe` (
  `recipe_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`recipe_id`),
  KEY `FKsoo4pxu79yt7nethdmbiks3pe` (`member_id`),
  CONSTRAINT `FKsoo4pxu79yt7nethdmbiks3pe` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
;