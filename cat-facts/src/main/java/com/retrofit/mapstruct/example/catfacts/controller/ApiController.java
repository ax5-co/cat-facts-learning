package com.retrofit.mapstruct.example.catfacts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retrofit.mapstruct.example.catfacts.dto.BreedsResponse;
import com.retrofit.mapstruct.example.catfacts.service.ApiService;

@RestController
@RequestMapping("api/")
public class ApiController {

	@Autowired
	private ApiService apiService;

	@GetMapping("breeds")
	public ResponseEntity<BreedsResponse> getAllCatBreeds(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int limit) {
		try {
		      HttpHeaders headers = new HttpHeaders();
		      headers.setContentType(MediaType.APPLICATION_JSON);
		      return new ResponseEntity<>(apiService.getAllBreeds(page, limit),
		    		  headers, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null,
		    		  HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}

	@GetMapping("breeds/search")
	public ResponseEntity<BreedsResponse> searchCatBreeds(
			@RequestParam(required = false, name = "term") String searchTerm, 
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int limit) {
		BreedsResponse response = new BreedsResponse();
		try { 
		      if (searchTerm == null)
		    	  response = apiService.getAllBreeds(page, limit);
		      else
		    	  response = apiService
		        	.getAllBreedsContaining(searchTerm, limit);
		      HttpHeaders headers = new HttpHeaders();
		      headers.setContentType(MediaType.APPLICATION_JSON);
		      return new ResponseEntity<>(response, headers, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null,
		    		  HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
}