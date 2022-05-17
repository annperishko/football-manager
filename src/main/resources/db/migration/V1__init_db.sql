CREATE SCHEMA IF NOT EXISTS `football_manager`;
USE `football_manager` ;

CREATE TABLE IF NOT EXISTS `team` (
                                      `id` INT NOT NULL AUTO_INCREMENT,
                                      `name` VARCHAR(50),
                                      `account` DOUBLE,
                                      `commission` INT,
                                      PRIMARY KEY (`id`));
CREATE TABLE IF NOT EXISTS `players` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50),
    `age`INT,
    `experience` DOUBLE,
    `team_id` INT NOT NULL,
    PRIMARY KEY (`id`),
        FOREIGN KEY (`team_id`)
        REFERENCES `team` (`id`));

