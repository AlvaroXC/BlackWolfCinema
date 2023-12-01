package com.uady.blackWolfCinema.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uady.blackWolfCinema.model.CinemaRoom;
import com.uady.blackWolfCinema.model.Movie;
import com.uady.blackWolfCinema.model.Show;
import com.uady.blackWolfCinema.service.CinemaRoomService;
import com.uady.blackWolfCinema.service.MovieService;
import com.uady.blackWolfCinema.service.ShowService;

@Controller
@RequestMapping("/admin")
public class ShowController {

    // Services for Show, Movie, and CinemaRoom
    private ShowService showService;
    private MovieService movieService;
    private CinemaRoomService cinemaRoomService;

    // Constructor for dependency injection
    public ShowController(ShowService theShowService, MovieService theMovieService, CinemaRoomService theCinemaRoomService) {
        cinemaRoomService = theCinemaRoomService;
        movieService = theMovieService;
        showService = theShowService;
    }

    // Retrieves details of a specific show
    @GetMapping("/show/{showId}")
    public Show getShowDetails(@PathVariable int showId){
        Show theShow = showService.findById(showId);
        return theShow;
    }


    // Displays the show schedule (cartelera)
    @GetMapping("/listShows")
    public String showAdminPanelShow(Model model) {
        // Fetch all shows
        List<Show> shows = showService.findAll();
        // Add shows to the model
        model.addAttribute("shows", shows);
        return "shows/list-shows";
    }

    // Displays the form for adding a new show
    @GetMapping("/shows/add-show")
    public String showFormForAddShow(Model theModel) {
        // Create a new show
        Show theShow = new Show();
        // Add the show to the Spring model
        theModel.addAttribute("show", theShow);
        // Fetch all movies and cinema rooms for dropdowns
        List<Movie> movies = movieService.findAll();
        theModel.addAttribute("movies", movies);
        List<CinemaRoom> cinemaRooms = cinemaRoomService.getAllRooms();
        theModel.addAttribute("cinemaRooms", cinemaRooms);

        return "shows/show-form";
    }

    // Displays the form for updating an existing show
    @GetMapping("shows/update-show")
    public String showFormForUpdateShow(@RequestParam("showId") int theId, Model theModel) {
        // Get the show from the service
        Show theShow = showService.findById(theId);
        // Add the show to the model to populate the form
        theModel.addAttribute("show", theShow);
        return "shows/show-form";
    }

    // Deletes a show
    @GetMapping("shows/delete")
    public String delete(@RequestParam("showId") int theId, Model theModel) {
        showService.deleteById(theId);
        return "redirect:/admin/listShows";
    }

    // Saves a show (add or update)
    @PostMapping("shows/save")
    public String save(@ModelAttribute("show") Show theShow,
                       @RequestParam("cinemaRooms") int cinemaRoomId,
                       @RequestParam("movies") int idmovie) {
        // Fetch cinema room and movie based on IDs
        CinemaRoom cinemaRoom = cinemaRoomService.findRoomById(cinemaRoomId);
        Movie movie = movieService.findById(idmovie);
        // Set movie and cinema room for the show
        theShow.setMovie(movie);
        theShow.setCinemaRoom(cinemaRoom);
        // Save the show
        showService.save(theShow);
        return "redirect:/admin/listShows";
    }

}

