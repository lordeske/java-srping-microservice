CREATE TABLE kategorija (
    id SERIAL PRIMARY KEY,
    naziv_kategorije VARCHAR(255),
    opis_kategorije VARCHAR(255)
);

CREATE TABLE proizvod (
    id SERIAL PRIMARY KEY,
    naziv_proizvoda VARCHAR(255),
    opis_proizvoda VARCHAR(255),
    broj_dostupnih DOUBLE PRECISION,
    cena NUMERIC(15, 2),
    id_kategorije INTEGER,
    CONSTRAINT fk_proizvod_kategorija FOREIGN KEY (id_kategorije) REFERENCES kategorija(id) ON DELETE SET NULL
);
