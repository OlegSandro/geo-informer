CREATE SCHEMA `map` DEFAULT CHARACTER SET utf8;

CREATE TABLE `map`.`position` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `osm_type` VARCHAR(1) NULL,
  `osm_id` BIGINT NULL,
  `latitude` REAL NOT NULL,
  `longitude` REAL NOT NULL,
  `country` VARCHAR(2) NULL,
  `type` VARCHAR(22) NULL,
  `name` VARCHAR(128) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UN_COORDS` (`latitude` ASC, `longitude` ASC) INVISIBLE,
  INDEX `IN_COUNTRY` (`country` ASC) INVISIBLE,
  INDEX `IN_NAME` (`name` ASC) VISIBLE);