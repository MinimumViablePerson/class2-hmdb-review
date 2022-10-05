package com.hmdb.hmdb;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoviesController {

  @GetMapping("/movies")
  public ArrayList<Movie> getMovies() {
    return Movie.movies;
  }

  @PostMapping("/movies")
  public Movie createMovie(@RequestBody Movie movie) {
    movie.getActors();
    return movie;
  }
}

class Movie {
  public static ArrayList<Movie> movies = new ArrayList<>();
  private static Integer currentId = 1;

  public Integer id;
  public String title;
  public String description;
  public Integer year;

  static {
    new Movie("Gladiator", "Gladiators will gladiate.", 2000);
    new Movie("Harry Potter", "Yer a wizerd herry.", 2001);
    new Movie("Lord of the Rings: The Two Towers", "Oh lord.", 2002);
    new Movie("The Matrix Reloaded", "Deja vu.", 2003);
  }

  public Movie(String title, String description, Integer year) {
    this.id = Movie.currentId++;
    this.title = title;
    this.description = description;
    this.year = year;

    Movie.movies.add(this);
  }

  public ArrayList<Actor> getActors() {
    ArrayList<Actor> actorsForThisMovie = new ArrayList<>();

    for (Actor actor : Actor.actors) {
      if (actor.movieId == this.id) {
        actorsForThisMovie.add(actor);
      }
    }

    return actorsForThisMovie;
  }
}