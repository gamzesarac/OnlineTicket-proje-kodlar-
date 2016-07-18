CREATE SCHEMA IF NOT EXISTS `myBiletix` DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `myBiletix`.`region` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `myBiletix`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `birth_date` DATE NULL,
  `type` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `myBiletix`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `region_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `city_region`
    FOREIGN KEY (`region_id`)
    REFERENCES `myBiletix`.`region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `myBiletix`.`adress` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `customer_id` INT NOT NULL,
  `country` VARCHAR(45) NULL,
  `city_id` INT NOT NULL,
  `post_code` INT NULL,
  `adress` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `user_adress`
    FOREIGN KEY (`customer_id`)
    REFERENCES `myBiletix`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
	CONSTRAINT `address_city`
    FOREIGN KEY (`city_id`)
    REFERENCES `myBiletix`.`city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `myBiletix`.`place` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `orginizer_id` INT NOT NULL,
  `city_id` INT NOT NULL,
  `post_code` INT NULL,
  `adress` VARCHAR(1000) NULL,
  `geo_cordinate` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `place_user`
    FOREIGN KEY (`orginizer_id`)
    REFERENCES `myBiletix`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   CONSTRAINT `place_city`
    FOREIGN KEY (`city_id`)
    REFERENCES `myBiletix`.`city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `detail` varchar(45) DEFAULT NULL,
  `event_date` date DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `event_type` varchar(45) DEFAULT NULL,
  `place_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `place_event` (`place_id`),
  CONSTRAINT `place_event` FOREIGN KEY (`place_id`) REFERENCES `place` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `myBiletix`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `category_event`
    FOREIGN KEY (`event_id`)
    REFERENCES `myBiletix`.`event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `myBiletix`.`seat` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` INT NULL,
  `category_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `seat_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `myBiletix`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


INSERT INTO `mybiletix`.`user`(`user_name`,`email`,`password`,`name`,`surname`,`birth_date`,`type`)
VALUES('customer1','customer1@gmail.com','1234','customer1','surname','1991-06-01',1);
INSERT INTO `mybiletix`.`user`(`user_name`,`email`,`password`,`name`,`surname`,`birth_date`,`type`)
VALUES('customer2','customer2@gmail.com','1234','customer2','surname','1991-06-01',1);

INSERT INTO `mybiletix`.`user`(`user_name`,`email`,`password`,`name`,`surname`,`birth_date`,`type`)
VALUES('admin1','admin1@gmail.com','1234','admin1','surname','1991-06-01',2);
INSERT INTO `mybiletix`.`user`(`user_name`,`email`,`password`,`name`,`surname`,`birth_date`,`type`)
VALUES('admin2','admin2@gmail.com','1234','admin2','surname','1991-06-01',2);

INSERT INTO `mybiletix`.`user`(`user_name`,`email`,`password`,`name`,`surname`,`birth_date`,`type`)
VALUES('orginizer1','orginizer1@gmail.com','1234','orginizer1','surname','1991-06-01',3);
INSERT INTO `mybiletix`.`user`(`user_name`,`email`,`password`,`name`,`surname`,`birth_date`,`type`)
VALUES('orginizer2','orginizer2@gmail.com','1234','orginizer2','surname','1991-06-01',3);


INSERT INTO `mybiletix`.`region` (`name`) VALUES ('Ege');
INSERT INTO `mybiletix`.`region` (`name`) VALUES ('Marmara');
INSERT INTO `mybiletix`.`region` (`name`) VALUES ('Akdeniz');
INSERT INTO `mybiletix`.`region` (`name`) VALUES ('Ýç Anadolu');


INSERT INTO `mybiletix`.`city` (`name`, `region_id`) VALUES ('Ýzmir', (select id from `mybiletix`.`region` where name='Ege'));
INSERT INTO `mybiletix`.`city` (`name`, `region_id`) VALUES ('Muðla', (select id from `mybiletix`.`region` where name='Ege'));
INSERT INTO `mybiletix`.`city` (`name`, `region_id`) VALUES ('Kuþadasý', (select id from `mybiletix`.`region` where name='Ege'));

INSERT INTO `mybiletix`.`city` (`name`, `region_id`) VALUES ('Ýstanbul', (select id from `mybiletix`.`region` where name='Marmara'));
INSERT INTO `mybiletix`.`city` (`name`, `region_id`) VALUES ('Bursa', (select id from `mybiletix`.`region` where name='Marmara'));
INSERT INTO `mybiletix`.`city` (`name`, `region_id`) VALUES ('Edirne', (select id from `mybiletix`.`region` where name='Marmara'));
INSERT INTO `mybiletix`.`city` (`name`, `region_id`) VALUES ('Çanakkale', (select id from `mybiletix`.`region` where name='Marmara'));
INSERT INTO `mybiletix`.`city` (`name`, `region_id`) VALUES ('Kocaeli', (select id from `mybiletix`.`region` where name='Marmara'));
INSERT INTO `mybiletix`.`city` (`name`, `region_id`) VALUES ('Yalova', (select id from `mybiletix`.`region` where name='Marmara'));

INSERT INTO `mybiletix`.`city` (`name`, `region_id`) VALUES ('Antalya', (select id from `mybiletix`.`region` where name='Akdeniz'));
INSERT INTO `mybiletix`.`city` (`name`, `region_id`) VALUES ('Alanya', (select id from `mybiletix`.`region` where name='Akdeniz'));
INSERT INTO `mybiletix`.`city` (`name`, `region_id`) VALUES ('Side', (select id from `mybiletix`.`region` where name='Akdeniz'));

INSERT INTO `mybiletix`.`city` (`name`, `region_id`) VALUES ('Ankara', (select id from `mybiletix`.`region` where name='Ýç Anadolu'));
INSERT INTO `mybiletix`.`city` (`name`, `region_id`) VALUES ('Eskiþehir', (select id from `mybiletix`.`region` where name='Ýç Anadolu'));
