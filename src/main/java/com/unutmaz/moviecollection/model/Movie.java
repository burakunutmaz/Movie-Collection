package com.unutmaz.moviecollection.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="t_movies")
@JsonIgnoreProperties(value = { "castRef" })
public class Movie {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="release_date")
	private Date date;
	
	@Column(name="category")
	private String category;
	
	@Column(name="description")
	private String description;
	
	@Column(name="image")
	private String image;
	
	@Column(name="rating")
	private float rating;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "movie_actors",
			joinColumns = @JoinColumn(name="movie_id"),
			inverseJoinColumns = @JoinColumn(name="actor_id")
	)
	private Set<Actor> cast = new HashSet<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	public Set<Actor> getCast() {
		return cast;
	}
	public void setCast(Set<Actor> cast) {
		this.cast = cast;
	}
	
	public void addActor(Actor actor) {
		this.cast.add(actor);
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		String castString = "";
		for (Actor actor : this.cast) {
			castString += actor.toString();
		}
		
		return "Movie [id=" + id + ", name=" + name + ", date=" + date + ", category=" + category + ", description="
				+ description + ", rating=" + rating + ", cast=" + castString + "]";
	}
	

}
