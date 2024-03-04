CREATE TABLE photos
(
    id    BIGINT PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    type  VARCHAR(255) NOT NULL,
    photo BYTEA        NOT NULL
);

CREATE TABLE cv_files
(
    id   BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    cv   BYTEA        NOT NULL
);

ALTER TABLE candidates
DROP
COLUMN photo,
    DROP
COLUMN cv;

ALTER TABLE candidates
    ADD COLUMN cv_id BIGINT,
    ADD COLUMN photo_id BIGINT,
    ADD FOREIGN KEY (cv_id) REFERENCES cv_files(id),
    ADD FOREIGN KEY (photo_id) REFERENCES photos(id);