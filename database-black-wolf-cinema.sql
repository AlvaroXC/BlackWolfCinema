Create database black_wolf_cinema;
Use black_wolf_cinema;

CREATE TABLE `role` (
  `idrole` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idrole`)
) ENGINE=InnoDB;

INSERT INTO role(name) VALUES ('ROLE_ADMIN');

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `idrole` int DEFAULT NULL,
  PRIMARY KEY (`username`),
  FOREIGN KEY (`idrole`) REFERENCES `role` (`idrole`)
) ENGINE=InnoDB;

CREATE TABLE `movie` (
  `idmovie` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `synopsis` varchar(600) NOT NULL,
  `duration_min` int NOT NULL,
  `trailer` varchar(100) NOT NULL,
  `image_path` varchar(255) NOT NULL,
  PRIMARY KEY (`idmovie`)
) ENGINE=InnoDB;

CREATE TABLE `cinema_room` (
  `idcinema_room` int NOT NULL AUTO_INCREMENT,
  `total_rows` int NOT NULL,
  `total_columns` int NOT NULL,
  `total_seats` int NOT NULL,
  `seat_price` int NOT NULL,
  PRIMARY KEY (`idcinema_room`)
) ENGINE=InnoDB;

CREATE TABLE `receipt` (
  `Receipt_Id` int NOT NULL AUTO_INCREMENT,
  `Receipt_Date` date NOT NULL,
  `Total` float NOT NULL,
  PRIMARY KEY (`Receipt_Id`)
) ENGINE=InnoDB;

CREATE TABLE `movie_show` (
  `idshow` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `hour` time(6) DEFAULT NULL,
  `idcinema_room` int DEFAULT NULL,
  `idmovie` int DEFAULT NULL,
  PRIMARY KEY (`idshow`),
  FOREIGN KEY (`idmovie`) REFERENCES `movie` (`idmovie`),
  FOREIGN KEY (`idcinema_room`) REFERENCES `cinema_room` (`idcinema_room`)
) ENGINE=InnoDB;

CREATE TABLE `ticket` (
  `idticket` int NOT NULL AUTO_INCREMENT,
  `seat` varchar(45) NOT NULL,
  `idshow` int NOT NULL,
  `idreceipt` int NOT NULL,
  PRIMARY KEY (`idticket`),
  FOREIGN KEY (`idreceipt`) REFERENCES `receipt` (`Receipt_Id`),
  FOREIGN KEY (`idshow`) REFERENCES `movie_show` (`idshow`)
) ENGINE=InnoDB;
