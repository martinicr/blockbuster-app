package tec.bd.blockbuster;

import tec.bd.blockbuster.dao.MovieDAO;
import tec.bd.blockbuster.entity.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Blockbuster {

    private List<Movie> movieDB;
    private MovieDAO movieDAO;

    public Blockbuster() {
        this.movieDB = new ArrayList<>();
    }

    public Blockbuster(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }


    public List<Movie> getAllMovies() {
        return this.movieDAO.findAll();
    }

    public void addNewMovie(Movie movie) {
        this.movieDAO.save(movie);
    }

    public Movie getMovie(String movieName) {
        return this.movieDB
                .stream()
                .filter(m -> m.getTitle().equals(movieName))
                .findFirst()
                .orElse(null);
    }

    public Movie getMovie(long movieId) {
        return this.movieDB
                .stream()
                .filter(m -> m.getMovieId() == movieId)
                .findFirst()
                .orElse(null);
    }

    public void editMovieTitle(String currentMovieName, String newMovieName) {
        var movie = this.getMovie(currentMovieName);
        movie.setTitle(newMovieName);
    }

}
