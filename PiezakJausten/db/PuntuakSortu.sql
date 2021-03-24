DROP TABLE Puntuak;

CREATE TABLE Puntuak (
	Izena TEXT,
	Puntuazioa INTEGER,
	Ordua INTEGER
);

INSERT INTO Puntuak(Izena, Puntuazioa, Ordua) VALUES
('Juan', 3, strftime('%s', 'now')),
('Peilo', 6, strftime('%s', 'now')),
('Martha', 9, strftime('%s', 'now'));