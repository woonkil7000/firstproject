--CREATE TABLE article (
--                         id int NOT NULL AUTO_INCREMENT,
--                         title VARCHAR(50) NOT NULL ,
--                         content VARCHAR(200) NOT NULL,
--                         PRIMARY KEY (id));
--
--- article dummy data
insert into article(title,content) values ('영화','1111');
insert into article(title,content) values ('나나나','1111');
insert into article(title,content) values ('음식','1111');
insert into article(title,content) values ('라라라','1111');
insert into article(title,content) values ('마마마','1111');
insert into article(title,content) values ('바바바','1111');

--- comment dummy  data
insert into comment(nickname,body,article_id) values ('Park','굳 윌 헌팅',1);
insert into comment(nickname,body,article_id) values ('Park','쇼생크 탈출',1);
insert into comment(nickname,body,article_id) values ('Park','아이 엠 샘',1);
insert into comment(nickname,body,article_id) values ('Park','치킨',3);
insert into comment(nickname,body,article_id) values ('Park','샤브샤브',3);
insert into comment(nickname,body,article_id) values ('Park','초밥',3);
