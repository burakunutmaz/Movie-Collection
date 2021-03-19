package com.unutmaz.moviecollection.controller;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.unutmaz.moviecollection.model.Actor;
import com.unutmaz.moviecollection.model.Movie;
import com.unutmaz.moviecollection.service.AppService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties= {"spring.profiles.active=dev"})
public class ControllerTest {	
	
	@Autowired
	private AppService appService;
	
	@Test
	public void testFindMovies() {
		// There are 10 movie entries in the database at the start. This test is for assuring this.
		List<Movie> movies = appService.findMovies();
		MatcherAssert.assertThat(movies.size(), Matchers.equalTo(10));
	}
	
	@Test
	public void testFindMovieById() {
		// The movie with the ID 1 is The Shawshank Redemption, this test is for assuring this.
		Movie movie = appService.findMovie(1L);
		MatcherAssert.assertThat(movie.getName(), Matchers.equalTo("The Shawshank Redemption"));
	}
	
	@Test
	public void testCreateAndUpdateMovie() {
		// This test makes sure there is no problem with creating a movie, adding it to the database,
		// Creating an actor, adding it to the database,
		// Setting the actor as the cast of the movie and updating the movie_stars database.
		Movie movie = new Movie();
		movie.setName("Test-0001");
		movie.setCategory(null);
		movie.setDate(null);
		movie.setDescription(null);
		movie.setRating(5.0f);
		appService.createMovie(movie);
		MatcherAssert.assertThat(appService.findMoviesByName("Test-0001").get(0).getName(), Matchers.equalTo("Test-0001"));
		
		Actor actor = new Actor();
		actor.setActor_name("Burak Unutmaz");
		appService.createActor(actor);
		MatcherAssert.assertThat(appService.findActor(actor.getActor_id()).getActor_name(), Matchers.equalTo(actor.getActor_name()));
		
		movie.addActor(actor);
		actor.addMovie(movie);
		
		appService.update(actor);
		appService.update(movie);
		MatcherAssert.assertThat(appService.findActorsByMovie(movie).size(), Matchers.equalTo(1));
		
		appService.deleteMovie(movie.getId());
		appService.deleteActor(actor.getActor_id());
	}
}
