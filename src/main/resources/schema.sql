DROP TABLE IF EXISTS cat_exhibition;
DROP TABLE IF EXISTS cat;
DROP TABLE IF EXISTS exhibition;
DROP TABLE IF EXISTS member;

CREATE TABLE member (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    is_breeder BOOLEAN DEFAULT FALSE
);

CREATE TABLE exhibition (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    location VARCHAR(150) NOT NULL,
    date DATE NOT NULL
);

CREATE TABLE cat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birthdate DATE,
    deathdate DATE,
    ems_code VARCHAR(30),
    breeder_name VARCHAR(100),
    image_path VARCHAR(255),
    member_id INT NOT NULL,
    mother_id INT,
    father_id INT,
    FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
    FOREIGN KEY (mother_id) REFERENCES cat(id) ON DELETE SET NULL,
    FOREIGN KEY (father_id) REFERENCES cat(id) ON DELETE SET NULL
);

CREATE TABLE cat_exhibition (
    cat_id INT,
    exhibition_id INT,
    placement VARCHAR(50),
    PRIMARY KEY (cat_id, exhibition_id),
    FOREIGN KEY (cat_id) REFERENCES cat(id) ON DELETE CASCADE,
    FOREIGN KEY (exhibition_id) REFERENCES exhibition(id) ON DELETE CASCADE
);