package tec.bd.blockbuster.dao.mysql;

import tec.bd.blockbuster.dao.MovieDAO;
import tec.bd.blockbuster.entity.Movie;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MovieDAOImpl extends GenericMySqlDAOImpl<Movie, Long> implements MovieDAO {

    private static final String SQL_FIND_ALL_MOVIES = "select codigo, titulo, fecha_lanzamiento, categoria from pelicula";
    private static final String SQL_INSERT_MOVIE = "insert into pelicula(titulo, fecha_lanzamiento, categoria) values (?, ?, ?)";

    private final DataSource dataSource;

    public MovieDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        Connection dbConnection = null;
        try {
            dbConnection = this.dataSource.getConnection();
            var stmt = dbConnection.prepareStatement(SQL_FIND_ALL_MOVIES);
            var resultSet = stmt.executeQuery();
            return resultSetToList(resultSet);
        } catch (Exception e) {
            try {
                System.out.println(e.getMessage());
                dbConnection.rollback();
            } catch (SQLException sqlEx) {
                throw new RuntimeException(sqlEx);
            }
        }
        return movies;
    }

    @Override
    public Optional<Movie> findById(Long movieId) {
        return null;
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return null;
    }

    @Override
    public void save(Movie movie) {
        Connection dbConnection = null;
        try {
            dbConnection = this.dataSource.getConnection();
            PreparedStatement insertMovie = dbConnection.prepareStatement(SQL_INSERT_MOVIE);
            insertMovie.setString(1, movie.getTitle());
            var releaseDate = new java.sql.Date(movie.getReleaseDate().getTime());
            insertMovie.setDate(2, releaseDate);
            insertMovie.setString(3, movie.getCategory());
            insertMovie.executeUpdate();
        } catch (Exception e) {
            try {
                dbConnection.rollback();
            } catch (SQLException sqlEx) {
                throw new RuntimeException(sqlEx);
            }
        }
    }

    @Override
    public void delete(Long movieId) {

    }

    @Override
    public Optional<Movie> update(Movie movie) {
        return null;
    }

    @Override
    protected Movie resultSetToEntity(ResultSet resultSet) throws SQLException {
        var movieId = resultSet.getInt("codigo");
        var title = resultSet.getString("titulo");
        var releaseDate = resultSet.getDate("fecha_lanzamiento");
        var category = resultSet.getString("categoria");
        var movie = new Movie(movieId, title, new Date(releaseDate.getTime()), category);
        return movie;
    }

    @Override
    protected List<Movie> resultSetToList(ResultSet resultSet) throws SQLException {
        List<Movie> movies = new ArrayList<>();
        while(resultSet.next()) {
            movies.add(resultSetToEntity(resultSet));
        }
        return movies;
    }
}
