DROP TABLE IF EXISTS Element;
DROP TABLE IF EXISTS List;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS ListUser;


CREATE TABLE List(id int primary key AUTO_INCREMENT, 
					title varchar(255) NOT NULL, 
					description varchar(255) NOT NULL);

CREATE TABLE Element(id int primary key AUTO_INCREMENT, 
					title varchar(255) NOT NULL, 
					description varchar(255) NOT NULL,
					tag varchar(50) NULL,
					status int NOT NULL,
					creation_date DATE NOT NULL,
					updating_date DATE NOT NULL, 
					idList int NOT NULL,
					CONSTRAINT chk_status CHECK (status IN (0,1,2)),
					FOREIGN KEY (idList) REFERENCES public.List(id)
);
CREATE TABLE User(id int primary key AUTO_INCREMENT, 
					name varchar(50) NOT NULL,
					firstname varchar(50) NOT NULL,
					role varchar(50) NOT NULL, 
					CONSTRAINT chk_role CHECK (role IN ('Administrateur', 'Utilisateur'))
);
CREATE TABLE ListUser(id int primary key AUTO_INCREMENT,
					idList int NOT NULL, 
					idUser int NOT NULL,
					FOREIGN KEY (idList) REFERENCES public.List(id),
					FOREIGN KEY (idUser) REFERENCES public.User(id)
);

insert into User(name ,firstname,role) values ('Mahon-Puget','Cyril','Administrateur');
insert into User(name ,firstname,role) values ('Talabard','Jérémy','Administrateur');
insert into User(name ,firstname,role) values ('Toto','titi','Utilisateur');
insert into User(name ,firstname,role) values ('Tata','yoyo','Utilisateur');

insert into List(title,description) values ('Ma liste de course','Eh oui, il faut encore faire les courses');
insert into List(title,description) values ('Ma liste de cadeaux de Noel','J''ai envie de plein de cadeaux! ');

insert into Element(title,description,status,tag,creation_date,updating_date,idList) values ('Pain','En bon français opn achète du pain.',0,'NA', now() ,now(), (select id from List order by id asc limit 1));
insert into Element(title,description,status,tag,creation_date,updating_date,idList) values ('Oeuf','Pour faire un gateau',0,'NA', now() ,now(), (select id from List order by id asc limit 1));
insert into Element(title,description,status,tag,creation_date,updating_date,idList) values ('Farine','Pour faire un gateau',0,'NA', now() ,now(), (select id from List order by id asc limit 1));

insert into Element(title,description,status,tag,creation_date,updating_date,idList) values ('ActionMan','Le plus grand de tout les héros.',0,'NA', now() ,now(), (select id from List order by id desc limit 1));
insert into Element(title,description,status,tag,creation_date,updating_date,idList) values ('PS4','J''arrète de bosser, j''ai une PS4',0,'NA', now() ,now(), (select id from List order by id desc limit 1));
insert into Element(title,description,status,tag,creation_date,updating_date,idList) values ('Voiture télécommandé','Pour faire la course avec le robot aspirateur.',0,'NA', now() ,now(), (select id from List order by id desc limit 1));

INSERT INTO PUBLIC.LISTUSER (IDLIST, IDUSER) VALUES((select id from List order by id asc limit 1),(select id from user WHERE name='Mahon-Puget'order by id asc limit 1) );
INSERT INTO PUBLIC.LISTUSER (IDLIST, IDUSER) VALUES((select id from List order by id asc limit 1),(select id from user WHERE name='Talabard'order by id asc limit 1) );
INSERT INTO PUBLIC.LISTUSER (IDLIST, IDUSER) VALUES((select id from List order by id asc limit 1),(select id from user WHERE name='Toto'order by id asc limit 1) );
INSERT INTO PUBLIC.LISTUSER (IDLIST, IDUSER) VALUES((select id from List order by id desc limit 1),(select id from user WHERE name='Mahon-Puget'order by id asc limit 1) );
INSERT INTO PUBLIC.LISTUSER (IDLIST, IDUSER) VALUES((select id from List order by id desc limit 1),(select id from user WHERE name='Tata'order by id asc limit 1) );


SELECT * FROM LISTUSER 
INNER JOIN public.USER ON LISTUSER.IDUSER = USER.ID
INNER JOIN public.LIST ON LISTUSER.IDLIST = LIST.ID
WHERE USER.NAME = 'Talabard';
