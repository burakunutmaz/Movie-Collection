package com.unutmaz.moviecollection.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unutmaz.moviecollection.model.Actor;
import com.unutmaz.moviecollection.model.Movie;
import com.unutmaz.moviecollection.service.AppService;
import com.unutmaz.moviecollection.util.MovieFormWrapper;
import com.unutmaz.moviecollection.util.TextForm;

@Controller
public class AppController {

	@Autowired
	private AppService appService;

	@GetMapping("")
	public String redirectToMain() {
		return "redirect:/movies/list";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/movies/update/{id}")
	public String updateMovie(@PathVariable("id") Long id, Model model) {
		Movie movie = appService.findMovie(id);
		model.addAttribute("movie", movie);
		return "edit";
	}
	
	@PostMapping("/movies//edit/cast/{id}")
	public String movieUpdating(@PathVariable("id") Long id, @ModelAttribute Movie movie, Model model) {
		appService.update(movie);
		model.addAttribute("movie_id", movie.getId());
		model.addAttribute("actor", new Actor());
		model.addAttribute("actors", appService.findActors());
		model.addAttribute("textForm", new TextForm());
		return "updateCast";
	}
	
	@PostMapping("/movies//edit/confirm/{id}")
	public String confirmUpdate(@PathVariable("id") Long id, @ModelAttribute TextForm textForm) {
		Movie movie = appService.findMovie(id);
		List<Actor> actors = new ArrayList<>();
		for (Long actor_id : textForm.StrToIds()) {
			actors.add(appService.findActor(actor_id));
		}
		movie.getCast().addAll(actors);
		appService.update(movie);
		return "redirect:/movies/list";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/movies/delete/{id}")
	public String deleteMovie(@PathVariable("id") Long id) {
		appService.deleteMovie(id);
		return "redirect:/movies/list";
	}
		
	@PostMapping("/movies/new")
	public String creatingMovie(@ModelAttribute TextForm textForm, Model model) {
		model.addAttribute("movieWrapper", new MovieFormWrapper(textForm.getText()));
		model.addAttribute("stars", textForm.getText());
		return "newMovie";
	}
	
	@PostMapping("/movies/confirm")
	public String confirmCreation(@ModelAttribute MovieFormWrapper mfw, Model model) {
		Movie movie = new Movie();
		movie.setName(mfw.getName());
		movie.setDate(mfw.getDate());
		movie.setCategory(mfw.getCategory());
		movie.setDescription(mfw.getDescription());
		movie.setRating(mfw.getRating());
		List<Actor> cast = new ArrayList<>();
		for (String elem : mfw.getCastRef().split(",")) {
			cast.add(appService.findActor(Long.parseLong(elem)));
		}
		movie.getCast().addAll(cast);
		appService.createMovie(movie);
		return "redirect:/movies/list";
	}
	
	@GetMapping("/movies/cast")
	public ModelAndView castControl() {
		ModelAndView mav = new ModelAndView();
		List<Actor> actors = appService.findActors();
		mav.addObject("actors", actors);
		mav.addObject("actor", new Actor());
		mav.addObject("textForm", new TextForm());
		mav.setViewName("castEdit");
		return mav;
	}
	
	@PostMapping("/movies/new/actor")
	public String actorcreation(@ModelAttribute Actor actor) {
		appService.createActor(actor);
		return "redirect:/movies/cast";
	}
	
	@RequestMapping("/movies/list")
	public ModelAndView getMovies(
			@RequestParam(name="orderBy", required=false) String orderBy,
			@RequestParam(name="name", required=false) String name,
			@RequestParam(name="category", required=false) String category,
			@RequestParam(name="actor", required=false) String actor) {
		
		ModelAndView mav = new ModelAndView();
		
		List<Movie> moviesList = appService.findMovies();
		
		if (name != null) {
			moviesList = appService.findMoviesByName(name);
		}
		else if (category != null) {
			moviesList = appService.findMoviesByCategory(category);
		}
		else if (actor != null) {
			moviesList = appService.findMoviesByActor(actor);
		}
		else {
			moviesList = appService.findMovies();
		}
		
		if (orderBy != null) {
			if (orderBy.equals("rating")) {
				moviesList.sort(Comparator.comparing(Movie::getRating).reversed());
			}
			else if (orderBy.equals("name")) {
				moviesList.sort(Comparator.comparing(Movie::getName));
			}
			else if(orderBy.equals("date")) {
				moviesList.sort(Comparator.comparing(Movie::getDate).reversed());
			}
		}
		mav.addObject("movies", moviesList);
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/movies/{id}")
	public ModelAndView getMovieInfo(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView();
		Movie movie = appService.findMovie(id);
		List<Actor> actors = new ArrayList<Actor>(movie.getCast());
		mav.addObject("movie", movie);
		mav.addObject("cast", actors);
		mav.setViewName("info");
		return mav;
	}
}
