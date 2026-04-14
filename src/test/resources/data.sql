INSERT INTO member (name, email, password, role, is_breeder) VALUES
('Anders Nielsen', 'anders@mail.dk', 'password', 'ADMIN', FALSE),
('Birgitte Hansen', 'birgitte@mail.dk', 'password', 'USER', TRUE),
('Carsten Madsen', 'carsten@mail.dk', 'password', 'USER', FALSE),
('Dorthe Larsen', 'dorthe@mail.dk', 'password', 'USER', FALSE);

INSERT INTO cat (name, birthdate, ems_code, breeder_name, image_path, member_id) VALUES
('Muffin', '2020-03-15', 'MCO n 22', 'Birgitte Hansen', '/images/cats/muffin.jpg', 2),
('Thor', '2019-06-01', 'MCO es 09', 'Birgitte Hansen', '/images/cats/thor.jpg', 2),
('Kleo', '2021-11-20', 'MCO ns 22', 'Dorthe Larsen', '/images/cats/kleo.jpg', 4),
('Simba', '2018-04-10', 'MCO f 22', 'Birgitte Hansen', '/images/cats/simba.jpg', 3);

INSERT INTO exhibition (name, location, date) VALUES
('Dansk Racekat Show 2024', 'Odense Congress Center', '2024-03-10'),
('Nordisk Katteudstilling 2024', 'Bella Center, København', '2024-09-22'),
('Sjælland Cat Cup 2025', 'Ringsted Kongrescenter', '2025-02-15');