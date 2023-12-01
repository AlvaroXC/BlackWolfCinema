package com.uady.blackWolfCinema.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uady.blackWolfCinema.model.CinemaRoom;
import com.uady.blackWolfCinema.model.Movie;
import com.uady.blackWolfCinema.model.Show;
import com.uady.blackWolfCinema.service.CinemaRoomService;
import com.uady.blackWolfCinema.service.MovieService;
import com.uady.blackWolfCinema.service.ShowService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private MovieService movieService;
	@Autowired
	private ShowService showService;
	@Autowired
	private CinemaRoomService cinemaRoomService;

    @GetMapping("") 
	public ModelAndView showMovieswithShows() {
		List<Movie> movies = movieService.findAll();
		return new ModelAndView("billboard")
						.addObject("movies",movies);
	}
	
    @GetMapping("/movies/{id}")
	public ModelAndView showAllMovieInfo(@PathVariable Integer id) {
		Movie movie = movieService.findById(id);
		return new ModelAndView("movie-info").addObject("movie",movie);
	}

	@GetMapping("/show/{movieId}")
    public String getMovieShows(@PathVariable int movieId, Model model){
        List<Show> shows = showService.findShowsByMovieId(movieId);
        model.addAttribute("shows", shows);
        Movie movie = movieService.findById(movieId);
		model.addAttribute("movie", movie);
        return "show-movie";
    }

	// Initiates the ticket purchase process
    @GetMapping("/comprarBoletos")
    public String comprarBoletos(@RequestParam("showId") int showId,
                                 @RequestParam("cinemaRoomId") int cinemaRoomId,
                                 Model theModel) {
        // Get the show and cinema room for ticket purchase
        Show show = showService.findById(showId);
        CinemaRoom cinemaRoom = cinemaRoomService.findRoomById(cinemaRoomId);
        // Add show details to the model
        theModel.addAttribute("show", show);
        // Redirect to the seat selection page
        return "asientos";
    }


}
