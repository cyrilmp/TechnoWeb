CREATE TABLE Element(id int primary key, title varchar(255), description varchar(255),tag varchar(50),status varchar(50), idList int);
CREATE TABLE List(id int primary key, title varchar(255), description varchar(255),FOREIGN KEY (id) REFERENCES public.Element(idList));
CREATE TABLE User(id int primary key, name varchar(50), firstname varchar(50), role varchar(50));
CREATE TABLE ListUser(id int primary key, idList int, idUser int,FOREIGN KEY (idList) REFERENCES public.List(id),FOREIGN KEY (idUser) REFERENCES public.User(id));
