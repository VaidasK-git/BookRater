--Insert data in to books table

INSERT INTO book_ratings (book_id, rating)
SELECT
    (random() * 99 + 1)::INTEGER AS book_id,
    CASE
        WHEN random() < 0.2 THEN NULL
        ELSE (random() * 4 + 1)::INTEGER
    END AS rating
FROM
    generate_series(1, 1000);