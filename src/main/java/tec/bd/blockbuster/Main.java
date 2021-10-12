package tec.bd.blockbuster;

import tec.bd.blockbuster.entity.Movie;

import java.util.List;

public class Main {

    public static void main(String... args) {

        System.out.println("Blockbuster");

        ApplicationContext appContext = new ApplicationContext();
        Blockbuster blockbuster = appContext.getBlockbuster();
        List<Movie> movies = blockbuster.getAllMovies();
        System.out.println("Codigo \t\t Titulo");
        for(Movie m : movies) {
            System.out.println(m.getMovieId() +"\t\t" + m.getTitle());
        }
    }

}
