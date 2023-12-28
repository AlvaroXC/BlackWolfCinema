package com.uady.blackWolfCinema.controller;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.uady.blackWolfCinema.model.User;
import com.uady.blackWolfCinema.service.UserService;
import com.uady.blackWolfCinema.validation.UserValidation;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/register")
public class RegisterController {

	/*	
		Logger instance to record events and messages during the controller's execution.
		It's initialized with the instance associated with the current class name.
	*/
	private Logger logger = Logger.getLogger(getClass().getName());

    private UserService userService;

	@Autowired
    public RegisterController(UserService userService){
        this.userService=userService;
    }


	/* 
		Configures data binding rules for incoming request data to the controller.
		This method, annotated with @InitBinder, preprocesses web request data 
		before it is sent to the controller methods for handling.
	*/
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		// Trims white spaces from incoming Strings to empty ones.
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		// Registers the StringTrimmerEditor to the data binder.
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

		
	
	@GetMapping("/showRegistrationForm")
	public String showFormToRegister(Model theModel) {
		
		theModel.addAttribute("newUser", new UserValidation());
		
		return "register/signup";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegister(
			@Valid @ModelAttribute("newUser") UserValidation theNewUser,
			BindingResult theBindingResult,
			HttpSession session, Model theModel) {

		String userName = theNewUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			return "register/signup";
		 }

		// check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("newUser", new UserValidation());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "register/signup";
        }
        
        // create user account and store in the databse
        userService.save(theNewUser);
        
        logger.info("Successfully created user: " + userName);

		// place user in the web http session for later use
		session.setAttribute("user", theNewUser);

        return "register/registration-confirmation";
	}

}
