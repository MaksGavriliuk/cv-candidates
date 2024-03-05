DROP TABLE candidate_directions;
DROP TABLE candidate_tests;
DROP TABLE tests;
DROP TABLE directions;



CREATE TABLE tests
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE directions
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE test_direction
(
    test_id     BIGINT,
    direction_id BIGINT,
    PRIMARY KEY (test_id, direction_id),
    FOREIGN KEY (test_id) REFERENCES tests (id),
    FOREIGN KEY (direction_id) REFERENCES directions (id)
);

CREATE TABLE candidate_directions
(
    candidate_id BIGINT NOT NULL,
    direction_id BIGINT NOT NULL,
    PRIMARY KEY (candidate_id, direction_id),
    FOREIGN KEY (candidate_id) REFERENCES candidates (id),
    FOREIGN KEY (direction_id) REFERENCES directions (id)
);

CREATE TABLE candidate_tests
(
    id           BIGSERIAL PRIMARY KEY,
    candidate_id BIGINT NOT NULL,
    test_id      BIGINT NOT NULL,
    score        BIGINT,
    test_date    DATE,
    FOREIGN KEY (candidate_id) REFERENCES candidates (id),
    FOREIGN KEY (test_id) REFERENCES tests (id)
);