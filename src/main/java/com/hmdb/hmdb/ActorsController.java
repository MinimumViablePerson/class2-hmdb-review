package com.hmdb.hmdb;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

@RestController
public class ActorsController {

  @GetMapping("/actors")
  public ArrayList<Actor> getActors() {
    return Actor.actors;
  }

  @PostMapping("/actors")
  public Actor createActor(@RequestBody Actor actor) {
    return actor;
  }
}

class Actor {
  public static ArrayList<Actor> actors = new ArrayList<>();
  private static Integer currentId = 1;

  public Integer id;
  public String firstName;
  public String lastName;
  public Integer yearOfBirth;
  public Integer movieId;

  static {
    new Actor("Russell", "Crowe", 1964, 1);
    new Actor("Daniel", "Radcliffe", 1989, 2);
    new Actor("Vigo", "Mortensen", 1958, 3);
    new Actor("Keanu", "Reeves", 1964, 4);
  }

  public Actor(String firstName, String lastName, Integer yearOfBirth, Integer movieId) {
    this.id = Actor.currentId++;
    this.firstName = firstName;
    this.lastName = lastName;
    this.yearOfBirth = yearOfBirth;
    this.movieId = movieId;

    Actor.actors.add(this);
  }

  @JsonIgnore
  public Movie getMovie() {
    Movie match = null;

    for (Movie movie : Movie.movies) {
      if (movie.id == this.movieId) {
        match = movie;
      }
    }

    return match;
  }

}