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
@RequestMapping("")
public class MovieController {
    
    private MovieService movieService;
	private FilesStorageService filesStorageService;

	@Autowired
	public MovieController(MovieService movieService, FilesStorageService filesStorageService){
		this.movieService= movieService;
		this.filesStorageService= filesStorageService;
	}


    @GetMapping("/") 
	public ModelAndView showAll() {
		List<Movie> movies = movieService.findAll();
		return new ModelAndView("billboard")
						.addObject("movies",movies);
	}

	@GetMapping("/movies/{id}")
	public ModelAndView showInfo(@PathVariable Integer id) {
		Movie movie = movieService.findById(id);
		return new ModelAndView("movie-info").addObject("movie",movie);
	}

    @GetMapping("/admin/listMovies")
	public ModelAndView showAdminPanel() {
		List<Movie> movies = movieService.findAll();
		return new ModelAndView("admin/billboard").addObject("movies", movies);
	}

	@GetMapping("/admin/movies/add-movie")
	public String showFormToRegister(Model theModel) {
		theModel.addAttribute("movie", new MovieValidation());
		return "admin/movie-form";
	}
    
    @PostMapping("/admin/processRegistrationForm")
	public String register(@Validated @ModelAttribute("movie") MovieValidation movieValidation,
		BindingResult bindingResult, HttpSession session, Model theModel) {

		if(bindingResult.hasErrors() || movieValidation.getPortada().isEmpty()) {
			return "admin/movie-form";
		}
		movieService.save(movieValidation);
		session.setAttribute("movie", movieValidation);
		return "redirect:/admin/listMovies";
	}

	@GetMapping("/admin/movies/update-movie/{movieId}")
	public String showFormToUpdate(@PathVariable("movieId") int movieId, Model theModel){
		Movie theMovie = movieService.findById(movieId);
        theModel.addAttribute("movie", theMovie);
		return "admin/movie-form-update";
	}


	@PostMapping("/admin/movies/update-movie/{movieId}")
	public String update(@PathVariable("movieId") int movieId, @Validated @ModelAttribute("movie") MovieValidation movieValidation,
	Model model, BindingResult bindingResult){

		if(bindingResult.hasErrors()){
			model.addAttribute("movie", movieValidation);
			return "admin/update-movie";
		}
		Movie movieToUpdate = movieService.findById(movieId);
        movieToUpdate.setName(movieValidation.getName());
		movieToUpdate.setDuration(movieValidation.getDuration());
		movieToUpdate.setSynopsis(movieValidation.getSynopsis());
		movieToUpdate.setTrailer(movieValidation.getTrailer());

		if(!movieValidation.getPortada().isEmpty()) {
			filesStorageService.deleteFile(movieToUpdate.getImagePath());
			String rutaPortada = filesStorageService.saveFile(movieValidation.getPortada());
			movieToUpdate.setImagePath(rutaPortada);
		}

		movieService.save(movieToUpdate);

		return "redirect:/admin/listMovies";
	}

	@PostMapping("/admin/movies/delete-movie/{id}")
	public String delete(@PathVariable("id") int movieId){
		Movie movieToDelete = movieService.findById(movieId);
		movieService.deleteMovie(movieToDelete);
		filesStorageService.deleteFile(movieToDelete.getImagePath());
		return "redirect:/admin/listMovies";
	}


}

