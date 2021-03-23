DROP TABLE Puntuak;

CREATE TABLE Puntuak (
	Izena VARCHAR(20),
	Puntuazioa NUMBER(5,0),
	Ordua TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO Puntuak(Izena, Puntuazioa) VALUES
('Juan', 3),
('Peilo', 6),
('Oier', 9);