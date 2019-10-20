CREATE SCHEMA `map` DEFAULT CHARACTER SET utf8;

CREATE TABLE `map`.`position` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `latitude` REAL NOT NULL,
  `longitude` REAL NOT NULL,
  `country` VARCHAR(14) NULL,
  `type` VARCHAR(22) NULL,
  `name` VARCHAR(150) NULL,
  `osm_id` BIGINT NULL,
  `osm_type` VARCHAR(1) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UN_COORDS` (`latitude` ASC, `longitude` ASC) INVISIBLE,
  INDEX `IN_COUNTRY` (`country` ASC) INVISIBLE,
  INDEX `IN_NAME` (`name` ASC) VISIBLE);