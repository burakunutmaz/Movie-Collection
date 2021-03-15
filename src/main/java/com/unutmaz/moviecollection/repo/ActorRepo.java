package com.unutmaz.moviecollection.repo;

import java.util.List;

import com.unutmaz.moviecollection.model.Actor;
import com.unutmaz.moviecollection.model.Movie;
import com.unutmaz.moviecollection.model.User;

public interface ActorRepo {
	List<Actor> findActorsByMovie(Movie movie);
	List<Actor> findActors();
	Actor findActorById(Long id);
	void create(Actor actor);
	Actor update(Actor actor);
	void delete(Long actor_id);
	void registerUser(User user);
}
