package com.unutmaz.moviecollection.repo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.unutmaz.moviecollection.model.Movie;

@Repository("movieRepository")
public class MovieRepoJPA implements MovieRepo {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Movie> findAll() {
		return entityManager.createQuery("from Movie", Movie.class).getResultList();
	}

	@Override
	public Movie findById(Long id) {
		return entityManager.find(Movie.class, id);
	}

	@Override
	public List<Movie> findByName(String name) {
		String queryString = "from Movie WHERE upper(name) LIKE :name";
		return entityManager.createQuery(queryString, Movie.class)
				.setParameter("name", "%" + name.toUpperCase() + "%")
				.getResultList();
	}

	@Override
	public void create(Movie movie) {
		entityManager.persist(movie);

	}

	@Override
	public Movie update(Movie movie) {
		return entityManager.merge(movie);
	}

	@Override
	public void delete(Long id) {
		entityManager.remove(entityManager.getReference(Movie.class, id));
	}

	@Override
	public List<Movie> findByCategory(String category) {
		return entityManager.createQuery("from Movie WHERE upper(category) = :category", Movie.class)
				.setParameter("category", category.toUpperCase()).getResultList();
	}

	@Override
	public List<Movie> findByActor(String actor) {
		List<Movie> movies = entityManager.createQuery("from Movie", Movie.class).getResultList();
		return movies.stream().filter(m -> m.getCast().stream().anyMatch(a -> a.getActor_name().toUpperCase().contains(actor.toUpperCase()))).collect(Collectors.toList());
	}

}
