
INSERT INTO kategorija (naziv_kategorije, opis_kategorije) VALUES
('Tehnika', 'Elektronski uređaji i dodatna oprema'),
('Kancelarijski materijal', 'Materijal za kancelarijsko poslovanje'),
('Piće', 'Bezalkoholna i alkoholna pića'),
('Hrana', 'Prehrambeni proizvodi');


INSERT INTO proizvod (naziv_proizvoda, opis_proizvoda, broj_dostupnih, cena, id_kategorije) VALUES
('Laptop Lenovo', 'Lenovo ThinkPad sa 16GB RAM i SSD diskom', 5, 950.00, 1),
('Monitor Samsung', '24-inčni LED monitor', 3, 180.50, 1),
('Hemijska olovka', 'Plava hemijska olovka', 100, 0.30, 2),
('Sveska A4', 'Spirala, 96 listova', 45, 1.20, 2),
('Sok narandža', '1L prirodni sok od narandže', 20, 1.80, 3),
('Voda gazirana', '0.5L flaširana gazirana voda', 50, 0.70, 3),
('Čips paprika', '250g pikantni čips', 25, 2.10, 4),
('Keks', 'Čokoladni keks sa lešnicima 150g', 15, 1.50, 4);


INSERT INTO proizvod (naziv_proizvoda, opis_proizvoda, broj_dostupnih, cena, id_kategorije) VALUES
('Miš bežični', 'Bežični optički miš', 0, 12.99, 1);
