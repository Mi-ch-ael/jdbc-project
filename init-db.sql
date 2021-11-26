CREATE TABLE menu (
	dishId INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  	name VARCHAR(100) NOT NULL,
  	price DECIMAL(7, 2) UNSIGNED NOT NULL,
  	serveTimeMinutes SMALLINT UNSIGNED NULL 
);

CREATE TABLE client (
	clientId INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  	lastName VARCHAR(50) NOT NULL,
  	firstName VARCHAR(50) NOT NULL,
  	middleName VARCHAR(50) NULL,
  	discount DECIMAL(2,2) UNSIGNED NOT NULL DEFAULT 0
);

CREATE TABLE clientOrder (
	orderId INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  	clientId INT UNSIGNED NOT NULL,
  	FOREIGN KEY (clientId) REFERENCES client (clientId),
  	issuedAt DATETIME NOT NULL
);

CREATE TABLE dishInOrder (
	dishId INT UNSIGNED NOT NULL,
  	orderId INT UNSIGNED NOT NULL,
  	FOREIGN KEY (orderId) REFERENCES clientOrder (orderId),
  	FOREIGN KEY (dishId) REFERENCES menu (dishId)
);

INSERT INTO menu
(name, price, serveTimeMinutes)
VALUES
("Baked duck", 1200.00, 40),
("Greek salad", 250.00, 10),
("Asian flatcake", 80.00, null),
("Cappuccino", 120.00, 5);

INSERT INTO client
(lastName, firstName, middleName, discount)
VALUES
("Ivanov", "Petr", "Ivanovich", 0),
("Smith", "John", "Ronald", 0),
("Petrov", "Sergey", "Ivanovich", 0.10),
("Geiger", "Otto", null, 0.05);

INSERT INTO clientOrder
(clientId, issuedAt)
VALUES
(1, "2021-09-02 13:46:00"),
(4, "2021-09-02 20:14:00"),
(4, "2021-09-02 21:51:00");

INSERT INTO dishInOrder 
(dishId, orderId)
VALUES
(2, 1),
(4, 1),
(3, 1),
(1, 2),
(4, 3);
