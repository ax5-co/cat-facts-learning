package com.retrofit.mapstruct.example.catfacts.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retrofit.mapstruct.example.catfacts.dto.Breed;
import com.retrofit.mapstruct.example.catfacts.dto.BreedDTO;
import com.retrofit.mapstruct.example.catfacts.dto.TheCatApiBreedDto;
import com.retrofit.mapstruct.example.catfacts.mapper.BreedMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class BreedMappingService {
	
	private final BreedMapper breedMapper;

	public BreedDTO mapBreedToBreedDto(Breed breed) {
		BreedDTO breedDto = new BreedDTO();
		try {
			return breedMapper.breedToBreedDto(breed);
		}catch(Exception e) {
		log.error("Error mapping Breed to BreedDTO "+e.getMessage());	
		return breedDto;
		}
	}
	
	public List<BreedDTO> mapBreedsToBreedDtos(List<Breed> breeds) {
		List<BreedDTO> breedDtos = Arrays.asList(new BreedDTO());
		try {
			return breedMapper.breedsToBreedDtos(breeds);
		}catch(Exception e) {
		log.error("Error mapping Breed to BreedDTO "+e.getMessage());	
		return breedDtos;
		}
	}
	
	public Breed mapApiBreedToBreed(TheCatApiBreedDto apiBreed) {
		Breed breed = new Breed();
		try {
			return breedMapper.apiBreedToBreed(apiBreed);
		}catch(Exception e) {
		log.error("Error mapping The Cat Api Breed to Breed "+e.getMessage());	
		return breed;
		}
	}
	
	public List<Breed> mapApiBreedsToBreeds(List<TheCatApiBreedDto> apiBreeds) {
		List<Breed> breed = Arrays.asList(new Breed());
		try {
			return breedMapper.apiBreedsToBreeds(apiBreeds);
		}catch(Exception e) {
		log.error("Error mapping The Cat Api Breed to Breed "+e.getMessage());	
		return breed;
		}
	}
	
	public List<BreedDTO> mapApiBreedsToBreedDtos (List<TheCatApiBreedDto> apiBreeds){
		return mapBreedsToBreedDtos(mapApiBreedsToBreeds(apiBreeds));
	}
}
