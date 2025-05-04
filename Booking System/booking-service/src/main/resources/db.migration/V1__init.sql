CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    address VARCHAR(255)
);

INSERT INTO customer (name, email, address) VALUES
('Pera Perić', 'pera@example.com', 'Knez Mihailova 1'),
('Mika Mikić', 'mika@example.com', 'Nemanjina 10');
