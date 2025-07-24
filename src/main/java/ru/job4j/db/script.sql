REATE TABLE author (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100)
);

CREATE TABLE book (
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(200),
  author_id INT,
  FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE person (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100)
);

CREATE TABLE passport (
  id INT PRIMARY KEY AUTO_INCREMENT,
  number VARCHAR(20) UNIQUE,
  person_id INT UNIQUE,
  FOREIGN KEY (person_id) REFERENCES person(id)
);

CREATE TABLE student (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100)
);

CREATE TABLE course (
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100)
);

-- Промежуточная таблица для связи многие-ко-многим:
CREATE TABLE student_course (
  student_id INT,
  course_id INT,
  PRIMARY KEY(student_id, course_id),
  FOREIGN KEY (student_id) REFERENCES student(id),
  FOREIGN KEY (course_id) REFERENCES course(id)
);
