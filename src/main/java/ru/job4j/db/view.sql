CREATE VIEW student_reading_stats AS
SELECT
    s.name AS student_name,
    COUNT(DISTINCT b.id) AS total_books,
    COUNT(DISTINCT a.id) AS total_authors,
    COUNT(DISTINCT g.id) AS total_genres
FROM students s
JOIN orders o ON s.id = o.student_id
JOIN books b ON o.book_id = b.id
JOIN authors a ON b.author_id = a.id
JOIN genres g ON b.genre_id = g.id
WHERE o.order_date >= CURRENT_DATE - INTERVAL '90 days'
GROUP BY s.id, s.name
HAVING COUNT(DISTINCT a.id) >= 2;
