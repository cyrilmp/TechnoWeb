DROP TABLE IF EXISTS Element;
DROP TABLE IF EXISTS List;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS ListUser;

CREATE TABLE Element(id int primary key AUTO_INCREMENT, title varchar(255), description varchar(255),tag varchar(50),status varchar(50), idList int, creation_date DATE, updating_date DATE);
CREATE TABLE List(id int primary key AUTO_INCREMENT, title varchar(255), description varchar(255),FOREIGN KEY (id) REFERENCES public.Element(idList));
CREATE TABLE User(id int primary key AUTO_INCREMENT, name varchar(50), firstname varchar(50), role varchar(50));
CREATE TABLE ListUser(id int primary key AUTO_INCREMENT, idList int, idUser int,FOREIGN KEY (idList) REFERENCES public.List(id),FOREIGN KEY (idUser) REFERENCES public.User(id));
