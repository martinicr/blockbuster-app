drop procedure if exists delete_movie;
delimiter $$
create procedure delete_movie(in movie_id int)
begin
start transaction;
-- region critica
delete from pelicula where codigo = movie_id;

commit;
end
$$
delimiter ;



-- Ejemplo de procedimiento almanenado que agrega un registro a la BD

drop procedure if exists add_course;
delimiter $$
create procedure add_course(in course_id int, in course_name varchar(50), in course_credits int, in course_dept varchar(50))
begin
insert into curso(id, nombre, creditos, departamento) values(course_id, course_name, course_credits, course_dept);
end
$$
delimiter ;