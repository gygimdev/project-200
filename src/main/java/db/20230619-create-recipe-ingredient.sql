CREATE TABLE `recipe_ingredient` (
  `recipe_ingredient_id` bigint NOT NULL AUTO_INCREMENT,
  `ingredient_id` bigint DEFAULT NULL,
  `recipe_id` bigint DEFAULT NULL,
  PRIMARY KEY (`recipe_ingredient_id`),
  KEY `FK9b3oxoskt0chwqxge0cnlkc29` (`ingredient_id`),
  KEY `FKgu1oxq7mbcgkx5dah6o8geirh` (`recipe_id`),
  CONSTRAINT `FK9b3oxoskt0chwqxge0cnlkc29` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`ingredient_id`),
  CONSTRAINT `FKgu1oxq7mbcgkx5dah6o8geirh` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
;