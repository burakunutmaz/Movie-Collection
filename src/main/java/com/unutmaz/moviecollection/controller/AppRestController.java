package com.unutmaz.moviecollection.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.unutmaz.moviecollection.exception.MovieNotFoundException;
import com.unutmaz.moviecollection.model.Movie;
import com.unutmaz.moviecollection.service.AppService;

@RestController
@RequestMapping("/rest")
public class AppRestController {

	@Autowired
	private AppService appService;
	
	@RequestMapping(method=RequestMethod.GET, value="/movies")
	public ResponseEntity<List<Movie>> getMovies(){
		List<Movie> movies = appService.findMovies();
		return ResponseEntity.ok(movies);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/movie/{id}")
	public ResponseEntity<Movie> getMovie(@PathVariable("id") Long id){
		try {
			Movie movie = appService.findMovie(id);
			return ResponseEntity.ok(movie);
		} catch (MovieNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
