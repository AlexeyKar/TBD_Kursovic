-- Создаем таблицу с документами

create table Document (
document_id serial PRIMARY KEY,
name varchar(20) UNIQUE NOT NULL,
dateOfSupply date);

-- Создаем таблицу умных людей - оппонетов, научных руководителей и визирующих

create table CleverPeople (
cleverpeople_id serial primary key,
name varchar(20) NOT NULL,
surname varchar(20),
middlename varchar(20));

-- Создаем список статусов готовности студентов

create table Status (
status_id serial PRIMARY KEY,
name varchar(30) UNIQUE NOT NULL);

-- Добавим несколько значений в таблицу

insert into status (name) values ('Starting');
insert into status (name) values ('In progress');
insert into status (name) values ('Finishing');

-- Дообавим несколько значений в таблицу с умными людьми

insert into cleverpeople (name, surname, middlename) values ('Ivan', 'Ivanov', 'Ivanovich');
insert into cleverpeople (name, surname, middlename) values ('Vasiliy', 'Vasiliev', 'Vasilivich');
insert into cleverpeople (name, surname, middlename) values ('Petr', 'Petrov', 'Petrovich');

-- Создаем таблицу студентов

create table Student (
student_id serial PRIMARY KEY,
name varchar(20) NOT NULL,
surname varchar(20),
middlename varchar(20),
degree integer CHECK (degree < 2 AND degree >= 0),
status_id integer REFERENCES Status (status_id),
dateOfAdmission date,
dateOfPlanDiss date, 
dateOfFactDiss date,
nameOfDiss varchar(50),
codeOfDiss varchar(20),
cleverpeople_id integer REFERENCES CleverPeople (cleverpeople_id),
organization varchar(20));

-- Таблица задач

create table Task(
task_id serial PRIMARY KEY,
title varchar(30) UNIQUE NOT NULL, 
dateOfStartPlan date,
dateOfStartFact date,
dateOfFinishPlan date,
dateOfFinishFact date,
durration integer,
complishion integer CHECK (complishion <= 100 AND complishion >= 0),
document_id integer REFERENCES Document (document_id));

-- Таблица связи - задачи к предварительным задачам

create table Task_Task(
task_id_main integer REFERENCES Task (task_id),
task_id_secondary integer REFERENCES Task (task_id));

-- Таблица связи - студенты к задачам

create table Student_Task(
student_id integer REFERENCES Student (student_id),
task_id integer REFERENCES Task (task_id));

-- Таблица связи - документы к визировавшим

create table Document_CleverPeople(
document_id integer REFERENCES Document (document_id),
cleverpeople_id integer REFERENCES CleverPeople (cleverpeople_id));

-- Таблица связи - студенты к оппонентам

create table Student_CleverPeople (
student_id integer REFERENCES Student (student_id),
cleverpeople_id integer REFERENCES CleverPeople (cleverpeople_id));

-- Триггер для удаления студентов. Сначала удаляем из таблицы связей с задачами и оппонентами, потом удаляем студента

CREATE OR REPLACE FUNCTION trigger_student_before_del () RETURNS trigger AS ' 
BEGIN 
if (select count(*) from student_cleverpeople a where a.student_id=OLD.student_id)>0
then delete from student_cleverpeople where student_cleverpeople.student_id=OLD.student_id; 
end if;
if (select count(*) from student_task a where a.student_id=OLD.student_id)>0
then delete from student_task where student_task.student_id=OLD.student_id; 
end if;
return OLD;
END; 
' LANGUAGE  plpgsql;

CREATE TRIGGER tr_student_del_before
BEFORE DELETE ON student FOR EACH ROW 
EXECUTE PROCEDURE trigger_student_before_del();

-- Триггер для удаления документов - сначала удаляем из таблицы связей с визировавшими, потом удалем докукумент

CREATE OR REPLACE FUNCTION trigger_document_before_del () RETURNS trigger AS ' 
BEGIN 
if (select count(*) from document_cleverpeople a where a.document_id=OLD.document_id)>0
then delete from document_cleverpeople where document_cleverpeople.document_id=OLD.document_id; 
end if;
return OLD;
END; 
' LANGUAGE  plpgsql;

CREATE TRIGGER tr_document_del_before
BEFORE DELETE ON document FOR EACH ROW 
EXECUTE PROCEDURE trigger_document_before_del();

-- Триггер для удаления задач. Сначала удаляем из таблицы связей с пердварительными задачами, потом удаляем саму задачу

CREATE OR REPLACE FUNCTION trigger_task_before_del () RETURNS trigger AS ' 
BEGIN 
if (select count(*) from student_task a where a.task_id=OLD.task_id)>0
then delete from student_task where student_task.task_id=OLD.task_id; 
end if;
if (select count(*) from task_task a where a.task_id_main=OLD.task_id)>0
then delete from task_task where task_task.task_id_main=OLD.task_id; 
end if;
if (select count(*) from task_task a where a.task_id_secondary=OLD.task_id)>0
then delete from task_task where task_task.task_id_secondary=OLD.task_id; 
end if;
return OLD;
END; 
' LANGUAGE  plpgsql;

CREATE TRIGGER tr_task_del_before
BEFORE DELETE ON task FOR EACH ROW 
EXECUTE PROCEDURE trigger_task_before_del();