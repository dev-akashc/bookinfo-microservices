CREATE DATABASE reviews;

CREATE TABLE reviews.reviews (
    id INT AUTO_INCREMENT,
    product_id INT,
    reviewer TEXT,
    text TEXT,
    stars INT,
    color TEXT,
    PRIMARY KEY (id)
);

INSERT INTO reviews.reviews (
    product_id,
    reviewer,
    text,
    stars,
    color
) values (
    0,
    'Alice',
    'An extremely entertaining play by Shakespeare. The slapstick humour is refreshing!',
    5,
    'red'
);

INSERT INTO reviews.reviews (
    product_id,
    reviewer,
    text,
    stars,
    color
) values (
    0,
    'Bob',
    'Absolutely fun and entertaining. The play lacks thematic depth when compared to other plays by Shakespeare.',
    4,
    'red'
);
