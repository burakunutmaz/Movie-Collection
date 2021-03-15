package com.unutmaz.moviecollection.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="t_actors")
@JsonIgnoreProperties(value = { "playedIn" })
public class Actor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long actor_id;
	
	@Column(name="actor_name")
	private String actor_name;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy= "cast")
	private Set<Movie> playedIn = new HashSet<>();

	public Long getActor_id() {
		return actor_id;
	}

	public void setActor_id(Long actor_id) {
		this.actor_id = actor_id;
	}

	public String getActor_name() {
		return actor_name;
	}

	public void setActor_name(String actor_name) {
		this.actor_name = actor_name;
	}

	public Set<Movie> getPlayedIn() {
		return playedIn;
	}

	public void setPlayedIn(Set<Movie> playedIn) {
		this.playedIn = playedIn;
	}
	
	public void addMovie(Movie movie) {
		this.playedIn.add(movie);
	}

	@Override
	public String toString() {
		return "Actor [actor_id=" + actor_id + ", actor_name=" + actor_name + "]";
	}
	
}
