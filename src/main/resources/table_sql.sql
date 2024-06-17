CREATE TABLE member (
  member_id INT PRIMARY KEY AUTO_INCREMENT,
  passwd VARCHAR(255),
  email VARCHAR(255) unique ,
  pno VARCHAR(255),
    role VARCHAR(255)
);

CREATE TABLE txt (
  txt_id int PRIMARY KEY,
  lat FLOAT,
  lng FLOAT,
  written_date DATETIME,
  recommend INT,
  scope INT,
  member_id INT,
  FOREIGN KEY (member_id) REFERENCES member(member_id)
);
