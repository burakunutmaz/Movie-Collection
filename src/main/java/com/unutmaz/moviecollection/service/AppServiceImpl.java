package com.unutmaz.moviecollection.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unutmaz.moviecollection.exception.MovieNotFoundException;
import com.unutmaz.moviecollection.model.Actor;
import com.unutmaz.moviecollection.model.Movie;
import com.unutmaz.moviecollection.model.User;
import com.unutmaz.moviecollection.repo.ActorRepo;
import com.unutmaz.moviecollection.repo.MovieRepo;

@Service
@Transactional
public class AppServiceImpl implements AppService {

	private MovieRepo movieRepo;
	private ActorRepo actorRepo;
	
	@Autowired
	public void setMovieRepo(MovieRepo movieRepo) {
		this.movieRepo = movieRepo;
	}
	
	@Autowired
	public void setActorRepo(ActorRepo actorRepo) {
		this.actorRepo = actorRepo;
	}
	
	@Override
	public List<Movie> findMovies() {
		return movieRepo.findAll();
	}

	@Override
	public List<Movie> findMoviesByName(String name) {
		return movieRepo.findByName(name);
	}

	@Override
	public Movie findMovie(Long id) throws MovieNotFoundException {
		Movie movie = movieRepo.findById(id);
		if (movie == null) throw new MovieNotFoundException("Owner not found with id: " + id);
		return movie;
	}
	
	@Override
	public void createMovie(Movie movie) {
		movieRepo.create(movie);
	}
	
	@Override
	public void createActor(Actor actor) {
		actorRepo.create(actor);
	}

	@Override
	public void update(Movie movie) {
		movieRepo.update(movie);
	}
	
	@Override
	public void update(Actor actor) {
		actorRepo.update(actor);
	}

	@Override
	public void deleteMovie(Long id) {
		movieRepo.delete(id);
	}
	
	@Override
	public void deleteActor(Long actor_id) {
		actorRepo.delete(actor_id);
	}

	@Override
	public List<Movie> findMoviesByCategory(String category) {
		return movieRepo.findByCategory(category);
	}

	@Override
	public List<Movie> findMoviesByActor(String actor) {
		return movieRepo.findByActor(actor);
	}

	@Override
	public List<Actor> findActors() {
		return actorRepo.findActors();
	}

	@Override
	public Actor findActor(Long id) {
		return actorRepo.findActorById(id);
	}

	@Override
	public List<Actor> findActorsByMovie(Movie movie) {
		return actorRepo.findActorsByMovie(movie);
	}
	
	@Override
	public void registerUser(User user) {
		actorRepo.registerUser(user);
	}

}
