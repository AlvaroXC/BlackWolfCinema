package com.uady.blackWolfCinema.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserValidation {

	@NotNull(message = "es requerido")
	@Size(min = 4, message = "no es valido")
	private String userName;

	@NotNull(message = "es requerido")
	@Size(min = 4, message = "no es valido")
	private String password;

	@NotNull(message = "es requerido")
	@Size(min = 4, message = "no es valido")
	private String firstName;

	@NotNull(message = "es requerido")
	@Size(min = 4, message = "no es valido")
	private String lastName;

	@NotNull(message = "es requerido")
	@Size(min = 4, message = "no es valido")
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "no es válido")
	private String email;

	public UserValidation() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
