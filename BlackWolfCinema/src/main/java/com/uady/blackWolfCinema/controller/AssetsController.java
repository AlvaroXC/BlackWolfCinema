package com.uady.blackWolfCinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uady.blackWolfCinema.service.FileStorageServiceImpl;



@RestController
@RequestMapping("/assets")
public class AssetsController {

	@Autowired
	private FileStorageServiceImpl fileStorageService;
	
	@GetMapping("/{filename:.+}")
	public Resource getResource(@PathVariable("filename") String filename) {
		return fileStorageService.loadAsResource(filename);
	}
	
}
