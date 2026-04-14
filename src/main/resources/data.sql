SET SQL_SAFE_UPDATES = 0;
DELETE FROM cat_exhibition;
DELETE FROM cat;
DELETE FROM exhibition;
DELETE FROM member;

-- Passwords er plain text til testdata
INSERT INTO member (name, email, password, role, is_breeder) VALUES
('Anders Nielsen', 'anders@mail.dk', '$2a$10$dyP3gXQFSDuHPxXWxhmod.SGFZGWsGneD0lalz2UpgywiVfiJKPMu', 'ADMIN', FALSE),
('Birgitte Hansen', 'birgitte@mail.dk', '$2a$10$dyP3gXQFSDuHPxXWxhmod.SGFZGWsGneD0lalz2UpgywiVfiJKPMu', 'USER', TRUE),
('Carsten Madsen', 'carsten@mail.dk', '$2a$10$dyP3gXQFSDuHPxXWxhmod.SGFZGWsGneD0lalz2UpgywiVfiJKPMu', 'USER', FALSE),
('Dorthe Larsen', 'dorthe@mail.dk', '$2a$10$dyP3gXQFSDuHPxXWxhmod.SGFZGWsGneD0lalz2UpgywiVfiJKPMu', 'USER', TRUE);

INSERT INTO exhibition (name, location, date) VALUES
('Dansk Racekat Show 2024', 'Odense Congress Center', '2024-03-10'),
('Nordisk Katteudstilling 2024', 'Bella Center, København', '2024-09-22'),
('Sjælland Cat Cup 2025', 'Ringsted Kongrescenter', '2025-02-15');

INSERT INTO cat (name, birthdate, deathdate, ems_code, breeder_name, member_id, mother_id, father_id) VALUES
('Muffin', '2020-03-15', NULL, 'MCO ns 22', 'Birgitte Hansen', 2, NULL, NULL),
('Thor', '2019-06-01', NULL, 'MCO as 09', 'Birgitte Hansen', 2, NULL, NULL),
('Kleo', '2021-11-20', NULL, 'MCO fs 22', 'Dorthe Larsen', 4, NULL, NULL),
('Simba', '2018-04-10', '2024-01-05', 'MCO n 22', 'Birgitte Hansen', 3, NULL, NULL);

UPDATE cat SET mother_id = 3, father_id = 2 WHERE id = 1;

INSERT INTO cat_exhibition (cat_id, exhibition_id, placement) VALUES
(1, 1, '1. plads'),
(1, 2, '3. plads'),
(2, 1, '2. plads'),
(3, 2, NULL),
(3, 3, '1. plads'),
(4, 1, NULL);

SET SQL_SAFE_UPDATES = 1;
