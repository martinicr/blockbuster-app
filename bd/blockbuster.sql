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