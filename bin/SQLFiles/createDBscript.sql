CREATE DATABASE IF NOT EXISTS cbscalendar;
use cbscalendar;
SET SESSION FOREIGN_KEY_CHECKS=0;

/* Create Tables */

CREATE TABLE IF NOT EXISTS Calendar
(
	CalendarID int NOT NULL AUTO_INCREMENT,
	Name varchar(255) NOT NULL,
	Active tinyint,
	CreatedBy varchar(255) NOT NULL,
	-- 1 = public
	-- 2 = private
	PrivatePublic tinyint NOT NULL COMMENT '1 = public
	2 = private',
	PRIMARY KEY (CalendarID)
);


CREATE TABLE IF NOT EXISTS dailyupdate
(
	date datetime NOT NULL UNIQUE,
	apparentTemperature double,
	summary text,
	windspeed double,
	qotd varchar(300) NOT NULL,
	msg_type varchar (100) NOT NULL,
	update_timestamp TIMESTAMP DEFAULT NOW() ON UPDATE NOW(),
	PRIMARY KEY (date)
);


CREATE TABLE IF NOT EXISTS events
(
	eventid int NOT NULL AUTO_INCREMENT,
	type int NOT NULL,
	location int,
	createdby int NOT NULL,
	start datetime NOT NULL,
	end datetime NOT NULL,
	name varchar(0) NOT NULL,
	text text NOT NULL,
	active boolean NOT NULL DEFAULT '1',
	customevent boolean COMMENT 'Decides wether the event is an import-event or user created
',
	CalendarID int NOT NULL,
	PRIMARY KEY (eventid)
);


CREATE TABLE IF NOT EXISTS locationdata
(
	locationdataid int NOT NULL AUTO_INCREMENT,
	longitude int NOT NULL,
	latitude int UNIQUE,
	PRIMARY KEY (locationdataid)
);


CREATE TABLE IF NOT EXISTS notes
(
	noteId int NOT NULL AUTO_INCREMENT,
	eventId int NOT NULL,
	createdBy varchar(255) NOT NULL,
	text text,
	created datetime NOT NULL,
	active bit,
	PRIMARY KEY (noteid)
);



CREATE TABLE IF NOT EXISTS roles
(
	roleid int NOT NULL AUTO_INCREMENT,
	userid int NOT NULL,
	type varchar(200) NOT NULL,
	PRIMARY KEY (roleid)
);


CREATE TABLE IF NOT EXISTS userevents
(
	userid int NOT NULL,
	CalendarID int NOT NULL
);


CREATE TABLE IF NOT EXISTS users
(
	userid int NOT NULL AUTO_INCREMENT,
	email varchar(40) NOT NULL,
	active boolean NOT NULL DEFAULT '1',
	created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	password varchar(200) NOT NULL,
	PRIMARY KEY (userid)
);

/* Create Dummy Account */

INSERT INTO `cbscalendar`.`users`(`email`,`active`,`password`)VALUES("admin@admin.dk",true,"d6YSr320JnLXlp8YYxUcNQ==");
INSERT INTO `cbscalendar`.`users`(`email`,`active`,`password`)VALUES("mavi13ae@student.cbs.dk",true,"d6YSr320JnLXlp8YYxUcNQ==");
INSERT INTO `cbscalendar`.`users`(`email`,`active`,`password`)VALUES("akgu13ab@student.cbs.dk",true,"d6YSr320JnLXlp8YYxUcNQ==");

INSERT INTO `cbscalendar`.`calendar`(`Name`,`Active`,`CreatedBy`,`PrivatePublic`)VALUES("Skole",1,"mavi13ae@student.cbs.dk",1);
INSERT INTO `cbscalendar`.`calendar`(`Name`,`Active`,`CreatedBy`,`PrivatePublic`)VALUES("Familie",1,"akgu13ab@student.cbs.dk",1);

INSERT INTO `cbscalendar`.`events`(`CalendarID`,`type`,`start`,`end`,`name`,`text`)VALUES("1","1","2013-02-09","2014-02-09","Julefrokost","Juhuu Mad");
INSERT INTO `cbscalendar`.`events`(`CalendarID`,`type`,`start`,`end`,`name`,`text`)VALUES("1","1","2013-02-09","2014-02-09","F�dselsdag","Hip Hura");
/* Create Foreign Keys */

ALTER TABLE events
	ADD FOREIGN KEY (CalendarID)
	REFERENCES Calendar (CalendarID)
	ON UPDATE RESTRICT
;


ALTER TABLE userevents
	ADD FOREIGN KEY (CalendarID)
	REFERENCES Calendar (CalendarID)
	ON UPDATE RESTRICT
;


ALTER TABLE notes
	ADD FOREIGN KEY (eventid)
	REFERENCES events (eventid)
	ON UPDATE RESTRICT
;


ALTER TABLE events
	ADD FOREIGN KEY (location)
	REFERENCES locationdata (locationdataid)
	ON UPDATE RESTRICT
;


ALTER TABLE events
	ADD FOREIGN KEY (createdby)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;


ALTER TABLE roles
	ADD FOREIGN KEY (userid)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;


ALTER TABLE userevents
	ADD FOREIGN KEY (userid)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;


ALTER TABLE notes
	ADD FOREIGN KEY (createdby)
	REFERENCES users (userid)
	ON UPDATE RESTRICT
;