package tec.bd.blockbuster.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;
import tec.bd.blockbuster.cli.movie.CreateMovieCommand;
import tec.bd.blockbuster.cli.movie.DeleteMovieCommand;
import tec.bd.blockbuster.cli.movie.GetMoviesCommand;
import tec.bd.blockbuster.cli.movie.UpdateMovieCommand;

@Command(
        name = "BlockbusterAPP",
        subcommands = {
                GetMoviesCommand.class,
                CreateMovieCommand.class,
                DeleteMovieCommand.class,
                UpdateMovieCommand.class,
                HelpCommand.class
        },
        description = "Manage Movies and Customers")
public class MainCommand implements Runnable {

    @Override
    public void run() {

    }
}