DROP TABLE IF EXISTS movies;

CREATE TABLE movies (
	movie_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(100),
	image VARCHAR(300),
	year INTEGER(4),
);

INSERT INTO movies VALUES (1, 'System', './img/movies/system.jpg', 2020);
INSERT INTO movies VALUES (2, 'Piraci z Krzemowej Doliny', './img/movies/pirates.jpg', 2005);
INSERT INTO movies VALUES (3, 'Kontrola absolutna', './img/movies/controll.jpg', 2003);
INSERT INTO movies VALUES (4, 'Kod nieśmiertelności', './img/movies/sourcecode.jpg', 2010);
INSERT INTO movies VALUES (5, 'Ex Machina', './img/movies/exmachina.jpg', 2015);
INSERT INTO movies VALUES (6, 'Hakerzy', './img/movies/hackers.jpg', 2019);
INSERT INTO movies VALUES (7, 'Tron', './img/movies/tronclassic.jpg', 2021);
INSERT INTO movies VALUES (8, 'Tron: Dziedzictwo', './img/movies/tron.jpg', 2022);
INSERT INTO movies VALUES (9, 'Ja, robot', './img/movies/robot.jpg', 2023);
