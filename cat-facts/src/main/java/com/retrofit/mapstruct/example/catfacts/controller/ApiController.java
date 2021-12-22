package com.retrofit.mapstruct.example.catfacts.controller;

import com.retrofit.mapstruct.example.catfacts.dto.BreedsResponse;
import com.retrofit.mapstruct.example.catfacts.dto.User;
import com.retrofit.mapstruct.example.catfacts.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class ApiController {

	@Autowired
	private ApiService apiService;

	private final Map<Integer, User> users = new HashMap<>();

	@GetMapping("breeds")
	@Validated
	public ResponseEntity<BreedsResponse> getAllCatBreeds(
			@RequestParam(defaultValue = "0") @Min(0) int page,
			@RequestParam(defaultValue = "3") @Min(1) int limit) {
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
	@Validated
	public ResponseEntity<BreedsResponse> searchCatBreeds(
			@RequestParam(required = false, name = "term") String searchTerm, 
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") @Min(1)  int limit) {
		//"page" parameter has no effect at all on the result if "term" is fed.
		BreedsResponse response;
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
	
	@GetMapping("secret")
	@ApiIgnore
	public String secretMethodFromDoc() {
		return "This should not be appearing in Api Documentation!";
	}

	@PostMapping("user")
	public ResponseEntity<User> simpleRegister(@Valid @RequestBody User userData){
		User newUser = new User(userData.getName(), userData.getAge(), userData.getEmail(),
				userData.getBirthday());
		users.put(users.size()+1, newUser);
		return ResponseEntity.ok(newUser);
	}

	@GetMapping("user")
	public ResponseEntity<User> getUser(@RequestParam int id){
		if (users.containsKey(id))
			return ResponseEntity.ok(users.get(id));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("users")
	public ResponseEntity<Map<Integer, User>> getUsers(){
		return ResponseEntity.ok(users);
	}
}
