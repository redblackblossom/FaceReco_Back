CREATE TABLE `FaceReco`.`customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(32) NOT NULL,
  `pwd` VARCHAR(128) NOT NULL,
  `role` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `userid_UNIQUE` (`userid` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `FaceReco`.`customerDetail` (
  `name` varchar(16) NOT NULL,
  `email` varchar(64) NOT NULL,
  `id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `userId` FOREIGN KEY (`id`) REFERENCES `customer` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3
