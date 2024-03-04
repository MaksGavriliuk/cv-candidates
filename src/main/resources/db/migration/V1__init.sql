CREATE TABLE directions
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE tests
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    description  TEXT,
    direction_id BIGINT       NOT NULL,
    FOREIGN KEY (direction_id) REFERENCES directions (id)
);