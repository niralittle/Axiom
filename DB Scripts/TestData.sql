﻿
INSERT INTO FACULTY (ID,  NAME) VALUES (FACULTY_ID.nextval, 'ФІ');
INSERT INTO FACULTY (ID,  NAME) VALUES (FACULTY_ID.nextval, 'ФЕН');
INSERT INTO FACULTY (ID,  NAME) VALUES (FACULTY_ID.nextval, 'ФГН');
INSERT INTO FACULTY (ID,  NAME) VALUES (FACULTY_ID.nextval, 'ФСНСТ');
INSERT INTO FACULTY (ID,  NAME) VALUES (FACULTY_ID.nextval, 'ФПвН');
INSERT INTO FACULTY (ID,  NAME) VALUES (FACULTY_ID.nextval, 'ФПрН');

INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Програмна інженерія',  1);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Прикладна математика',  1);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Економічна теорія',  2);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Фінанси і кредит', 2);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Маркетинг', 2);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Історія', 3);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Філологія', 3);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Філософія', 3);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Культурологія', 3);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Соціологія', 4);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Політологія', 4);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Соціальна робота', 4);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Право', 5);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Фізика', 6);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Хімія', 6);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Біологія', 6);
INSERT INTO MAJOR (ID,  NAME,  FACULTYID) VALUES (MAJOR_ID.nextval, 'Екологія', 6);

INSERT INTO PROFILESTATE (ID,  DESCRIPTION) VALUES (PROFILESTATE_ID.nextval, 'READONLY');
INSERT INTO PROFILESTATE (ID,  DESCRIPTION) VALUES (PROFILESTATE_ID.nextval, 'ACTIVE');
INSERT INTO PROFILESTATE (ID,  DESCRIPTION) VALUES (PROFILESTATE_ID.nextval, 'BLOCKED');

INSERT INTO STARTUPSTATE (ID,  DESCRIPTION) VALUES (STARTUPSTATE_ID.nextval, 'Запланований');
INSERT INTO STARTUPSTATE (ID,  DESCRIPTION) VALUES (STARTUPSTATE_ID.nextval, 'Активний');
INSERT INTO STARTUPSTATE (ID,  DESCRIPTION) VALUES (STARTUPSTATE_ID.nextval, 'Завершений'); 
INSERT INTO STARTUPSTATE (ID,  DESCRIPTION) VALUES (STARTUPSTATE_ID.nextval, 'Заморожений');


INSERT INTO PROJECTTYPE (ID,  DESCRIPTION) VALUES (PROJECTTYPE_ID.nextval, 'ІТ');
INSERT INTO PROJECTTYPE (ID,  DESCRIPTION) VALUES (PROJECTTYPE_ID.nextval, 'Волонтерство');
INSERT INTO PROJECTTYPE (ID,  DESCRIPTION) VALUES (PROJECTTYPE_ID.nextval, 'Web');
INSERT INTO PROJECTTYPE (ID,  DESCRIPTION) VALUES (PROJECTTYPE_ID.nextval, 'Мистецтво');
INSERT INTO PROJECTTYPE (ID,  DESCRIPTION) VALUES (PROJECTTYPE_ID.nextval, 'Економіка');
INSERT INTO PROJECTTYPE (ID,  DESCRIPTION) VALUES (PROJECTTYPE_ID.nextval, 'Реклама');
INSERT INTO PROJECTTYPE (ID,  DESCRIPTION) VALUES (PROJECTTYPE_ID.nextval, 'Торгівля');
INSERT INTO PROJECTTYPE (ID,  DESCRIPTION) VALUES (PROJECTTYPE_ID.nextval, 'Спорт');
INSERT INTO PROJECTTYPE (ID,  DESCRIPTION) VALUES (PROJECTTYPE_ID.nextval, 'Медицина');
INSERT INTO PROJECTTYPE (ID,  DESCRIPTION) VALUES (PROJECTTYPE_ID.nextval, 'Сфера розваг');

INSERT INTO STARTUP (ID,  NAME,  DESCRIPTION,  OWNERID,  PROJECTTYPE,  STARTUPSTATEID) VALUES (STARTUP_ID.nextval, 'AXIOM',  'Студентський проект',  
	1,  1,  1);

INSERT INTO SYSUSER (ID,  FIRSTNAME,  LASTNAME,  LOGIN,  MAJORID,  PROFILESTATE,  PASSWORD,  REGISTRATIONDATE,    FACULTYID,  EMAIL)
VALUES(SYSUSER_ID.nextval,  'Катерина',  'Атаманчук',  'niralittle',  1,  2,  '11111',   sysdate,    1,  'ua.katerynka@gmail.com';
INSERT INTO SYSUSER (ID,  FIRSTNAME,  LASTNAME,  LOGIN,  MAJORID,  PROFILESTATE,  PASSWORD,  REGISTRATIONDATE,  FACULTYID,  EMAIL)
VALUES(SYSUSER_ID.nextval,  'Ксенія',  'Іванченко',  'ariyana', 1,  2,  '22222',  sysdate,   1,  'arriyana@gmail.com');

 