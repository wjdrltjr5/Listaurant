drop table recommend_check;
drop table txt;
drop table member;

CREATE TABLE member (
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    passwd VARCHAR(255),
    email VARCHAR(255) unique ,
    nickname VARCHAR(255) unique,
    pno VARCHAR(255),
    status VARCHAR(255),
    certification_code VARCHAR(255),
    role VARCHAR(255)
);

CREATE TABLE txt (
  txt_id int PRIMARY KEY AUTO_INCREMENT,
  place_name varchar(255),
  written_date DATETIME,
  recommend INT,
  scope INT,
  lat double,
  lng double,
  member_id INT,
  text varchar(8000),
  nickname VARCHAR(255),
  FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE
);

create table recommend_check(
    txt_id Int,
    member_id INT,
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE,
    FOREIGN KEY (txt_id) REFERENCES txt(txt_id) ON DELETE CASCADE
);

select * from member;
select * from recommend_check;
select * from txt;