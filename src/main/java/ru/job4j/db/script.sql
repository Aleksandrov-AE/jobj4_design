CREATE TABLE author(
  id SERIAL INT PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE book(
  id SERIAL INT PRIMARY KEY,
  title VARCHAR(200),
  author_id INT,
  FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE person(
  id SERIAL PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE passport(
  id SERIAL PRIMARY KEY,
  number VARCHAR(20) UNIQUE,
  person_id INT UNIQUE,
  FOREIGN KEY (person_id) REFERENCES person(id)
);

CREATE TABLE student(
  id SERIAL PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE course (
  id SERIAL PRIMARY KEY,
  title VARCHAR(100)
);

-- Промежуточная таблица для связи многие-ко-многим:
CREATE TABLE student_course(
  student_id INT,
  course_id INT,
  PRIMARY KEY(student_id, course_id),
  FOREIGN KEY (student_id) REFERENCES student(id),
  FOREIGN KEY (course_id) REFERENCES course(id)
);

