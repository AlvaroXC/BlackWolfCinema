package com.uady.blackWolfCinema.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uady.blackWolfCinema.model.Movie;
import com.uady.blackWolfCinema.service.FilesStorageService;
import com.uady.blackWolfCinema.service.MovieService;
import com.uady.blackWolfCinema.validation.MovieValidation;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class MovieController {
    
    MovieService movieService;
	FilesStorageService filesStorageService;

	@Autowired
	public MovieController(MovieService movieService, FilesStorageService filesStorageService){
		this.movieService= movieService;
		this.filesStorageService= filesStorageService;
	}

    @GetMapping("/listMovies")
	public ModelAndView showAdminPanelMovie() {
		List<Movie> movies = movieService.findAll();
		return new ModelAndView("admin/billboard").addObject("movies", movies);
	}

	@GetMapping("/movies/add-movie")
	public String showMovieFormToRegisterMovie(Model theModel) {
		theModel.addAttribute("movie", new MovieValidation());
		return "admin/movie-form";
	}
    
    @PostMapping("/processRegistrationForm")
	public String registerMovie(@Validated @ModelAttribute("movie") MovieValidation movieValidation,
		BindingResult bindingResult, HttpSession session, Model theModel) {
		
		System.out.println("validacion de pelicula");
		System.out.println(movieValidation.getTrailer());

		if(bindingResult.hasErrors() || movieValidation.getPortada().isEmpty()) {
			return "admin/movie-form";
		}
		System.out.println("Antes de guardar");
		movieService.save(movieValidation);
		System.out.println("Despues de guardar");
		session.setAttribute("movie", movieValidation);
		return "billboard";
	}

	@GetMapping("movies/update-movie/{movieId}")
	public String showFormToUpdateMovie(@PathVariable("movieId") int movieId, Model theModel){
		Movie theMovie = movieService.findById(movieId);
        theModel.addAttribute("movie", theMovie);
		return "admin/movie-form-update";
	}


	@PostMapping("movies/update-movie/{movieId}")
	public String updateMovie(@PathVariable("movieId") int movieId, @Validated @ModelAttribute("movie") MovieValidation movieValidation,
	Model model, BindingResult bindingResult){
		System.out.println("dentro de actualizar pelicula post");

		if(bindingResult.hasErrors()){
			model.addAttribute("movie", movieValidation);
			return "admin/update-movie";
		}
		Movie theMovie = movieService.findById(movieId);
        theMovie.setName(movieValidation.getName());
		theMovie.setDuration(movieValidation.getDuration());
		theMovie.setSynopsis(movieValidation.getSynopsis());
		theMovie.setTrailer(movieValidation.getTrailer());

		if(!movieValidation.getPortada().isEmpty()) {
			filesStorageService.deleteFile(theMovie.getImagePath());
			String rutaPortada = filesStorageService.saveFile(movieValidation.getPortada());
			theMovie.setImagePath(rutaPortada);
		}

		movieService.save(theMovie);

		return "redirect:/admin";
	}

	@PostMapping("/movies/delete-movie/{id}")
	public String deleteMovie(@PathVariable("id") int movieId){
		Movie movie = movieService.findById(movieId);
		movieService.deleteMovie(movie);
		filesStorageService.deleteFile(movie.getImagePath());
		return "redirect:/admin";
	}


}

