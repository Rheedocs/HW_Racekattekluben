SET SQL_SAFE_UPDATES = 0;
DELETE FROM cat_exhibition;
DELETE FROM cat;
DELETE FROM exhibition;
DELETE FROM member;
ALTER TABLE cat AUTO_INCREMENT = 1;
ALTER TABLE member AUTO_INCREMENT = 1;
ALTER TABLE exhibition AUTO_INCREMENT = 1;

INSERT INTO member (name, email, password, role, is_breeder) VALUES
('Anders Nielsen', 'anders@mail.dk', '$2a$10$dyP3gXQFSDuHPxXWxhmod.SGFZGWsGneD0lalz2UpgywiVfiJKPMu', 'ADMIN', FALSE),
('Birgitte Hansen', 'birgitte@mail.dk', '$2a$10$dyP3gXQFSDuHPxXWxhmod.SGFZGWsGneD0lalz2UpgywiVfiJKPMu', 'USER', TRUE),
('Carsten Madsen', 'carsten@mail.dk', '$2a$10$dyP3gXQFSDuHPxXWxhmod.SGFZGWsGneD0lalz2UpgywiVfiJKPMu', 'USER', FALSE),
('Dorthe Larsen', 'dorthe@mail.dk', '$2a$10$dyP3gXQFSDuHPxXWxhmod.SGFZGWsGneD0lalz2UpgywiVfiJKPMu', 'USER', TRUE),
('Erik Sørensen', 'erik@mail.dk', '$2a$10$dyP3gXQFSDuHPxXWxhmod.SGFZGWsGneD0lalz2UpgywiVfiJKPMu', 'USER', TRUE),
('Freja Christensen', 'freja@mail.dk', '$2a$10$dyP3gXQFSDuHPxXWxhmod.SGFZGWsGneD0lalz2UpgywiVfiJKPMu', 'USER', FALSE),
('Gunnar Pedersen', 'gunnar@mail.dk', '$2a$10$dyP3gXQFSDuHPxXWxhmod.SGFZGWsGneD0lalz2UpgywiVfiJKPMu', 'USER', TRUE);

INSERT INTO cat (name, birthdate, deathdate, ems_code, breeder_name, image_path, member_id, mother_id, father_id) VALUES
('Muffin', '2020-03-15', NULL, 'MCO n 22', 'Birgitte Hansen', '/images/cats/muffin.jpg', 2, NULL, NULL),
('Thor', '2019-06-01', NULL, 'MCO es 09', 'Birgitte Hansen', '/images/cats/thor.jpg', 2, NULL, NULL),
('Kleo', '2021-11-20', NULL, 'MCO ns 22', 'Dorthe Larsen', '/images/cats/kleo.jpg', 4, NULL, NULL),
('Simba', '2018-04-10', NULL, 'MCO f 22', 'Birgitte Hansen', '/images/cats/simba.jpg', 3, NULL, NULL),
('Luna', '2022-01-05', NULL, 'PER f 22', 'Erik Sørensen', '/images/cats/luna.jpg', 5, NULL, NULL),
('Aslan', '2021-07-12', NULL, 'PER n 22', 'Erik Sørensen', '/images/cats/aslan.jpg', 5, NULL, NULL),
('Freyja', '2023-03-20', NULL, 'MCO n 22', 'Dorthe Larsen', '/images/cats/freyja.jpg', 4, NULL, NULL),
('Odin', '2020-09-14', NULL, 'NOR a 22', 'Gunnar Pedersen', '/images/cats/odin.jpg', 7, NULL, NULL),
('Bella', '2022-05-30', NULL, 'NOR ns 22', 'Gunnar Pedersen', '/images/cats/bella.jpg', 7, NULL, NULL),
('Loke', '2019-11-03', NULL, 'NOR a 22', 'Birgitte Hansen', '/images/cats/loke.jpg', 2, NULL, NULL),
('Chad', '2020-01-01', NULL, 'XSH w', 'Anders Nielsen', '/images/cats/chad.gif', 1, NULL, NULL),
('Bogos',  '0001-01-01', NULL, 'ETSH g ??', 'Anders Nielsen', '/images/cats/bogos.jpg', 1, NULL, NULL);

UPDATE cat SET mother_id = 3, father_id = 2 WHERE id = 1;
UPDATE cat SET mother_id = 1, father_id = 6 WHERE id = 7;
UPDATE cat SET mother_id = 5, father_id = 8 WHERE id = 9;

INSERT INTO exhibition (name, location, date) VALUES
('Dansk Racekat Show 2024', 'Odense Congress Center', '2024-03-10'),
('Nordisk Katteudstilling 2024', 'Bella Center, København', '2024-09-22'),
('Sjælland Cat Cup 2025', 'Ringsted Kongrescenter', '2025-02-15'),
('Forårsudstilling 2026', 'Odense Congress Center', '2026-05-10'),
('Sommer Cat Show 2026', 'Bella Center, København', '2026-07-20'),
('Efterårsudstilling 2026', 'Ringsted Kongrescenter', '2026-10-05');

INSERT INTO cat_exhibition (cat_id, exhibition_id, placement) VALUES
-- Dansk Racekat Show 2024
(1, 1, '1. plads'),
(2, 1, '2. plads'),
(3, 1, '3. plads'),
(4, 1, 'Ingen placering'),
(8, 1, 'Ingen placering'),
-- Nordisk Katteudstilling 2024
(1, 2, '2. plads'),
(3, 2, '1. plads'),
(4, 2, '3. plads'),
(2, 2, 'Ingen placering'),
(5, 2, 'Ingen placering'),
-- Sjælland Cat Cup 2025
(4, 3, '1. plads'),
(3, 3, '2. plads'),
(8, 3, '3. plads'),
(1, 3, 'Ingen placering'),
(6, 3, 'Ingen placering'),
-- Forårsudstilling 2026 (kommende)
(1, 4, NULL),
(2, 4, NULL),
(5, 4, NULL),
-- Sommer Cat Show 2026 (kommende)
(3, 5, NULL),
(6, 5, NULL),
(1, 5, NULL),
-- Efterårsudstilling 2026 (kommende)
(8, 6, NULL),
(9, 6, NULL);

SET SQL_SAFE_UPDATES = 1;
