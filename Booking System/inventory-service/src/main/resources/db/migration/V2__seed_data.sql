-- V2__seed_data.sql

INSERT INTO venue (name, address, total_capacity) VALUES
('Beogradska Arena', 'Bulevar Arsenija Čarnojevića 58, Beograd', 20000),
('Niška Hala Čair', 'Stanoja Bunuševca 2a, Niš', 5000),
('Spens Novi Sad', 'Maksima Gorkog 1, Novi Sad', 7000);

INSERT INTO event (name, venue_id, total_capacity, left_capacity) VALUES
('Zvezda vs Partizan', 1, 20000, 18750),
('Balet Labudovo jezero', 2, 5000, 4700),
('Koncert Gibonni', 3, 7000, 6990);
