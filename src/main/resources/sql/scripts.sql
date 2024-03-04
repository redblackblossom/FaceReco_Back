CREATE TABLE `FaceReco`.`customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(32) NOT NULL,
  `pwd` VARCHAR(128) NOT NULL,
  `role` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `userid_UNIQUE` (`userid` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `FaceReco`.`customerDetails` (
  `id` INT NOT NULL,
  `name` VARCHAR(16) NOT NULL,
  `emial` VARCHAR(64) NOT NULL,
  `joinDate` DATE NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `id`
    FOREIGN KEY (`id`)
    REFERENCES `FaceReco`.`customer` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `FaceReco`.`folder` (
  `folderId` INT NOT NULL AUTO_INCREMENT,
  `ownerId` INT NOT NULL,
  `parentFolder` INT NOT NULL,
  `folderName` VARCHAR(64) NOT NULL,
  `createDate` DATE NOT NULL,
  PRIMARY KEY (`folderId`),
  INDEX `ownerId_idx` (`ownerId` ASC) VISIBLE,
  CONSTRAINT `ownerId`
    FOREIGN KEY (`ownerId`)
    REFERENCES `FaceReco`.`customer` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
    FOREIGN KEY (`parentFolder`)
    REFERENCES `FaceReco`.`folder` (`folderId`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION;
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `FaceReco`.`album` (
  `albumId` INT NOT NULL AUTO_INCREMENT,
  `ownerId` INT NOT NULL,
  `ownerFoler` INT NOT NULL,
  `folderName` VARCHAR(64) NOT NULL,
  `createDate` DATE NOT NULL,
  PRIMARY KEY (`albumId`),
  INDEX `ownderId_idx` (`ownerId` ASC) VISIBLE,
  INDEX `parentFolder_idx` (`ownerFoler` ASC) VISIBLE,
  CONSTRAINT `ownderId`
    FOREIGN KEY (`ownerId`)
    REFERENCES `FaceReco`.`customer` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `ownerFolder`
    FOREIGN KEY (`ownerFoler`)
    REFERENCES `FaceReco`.`folder` (`folderId`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
