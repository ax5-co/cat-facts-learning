package com.retrofit.mapstruct.example.catfacts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.retrofit.mapstruct.example.catfacts.dto.Breed;
import com.retrofit.mapstruct.example.catfacts.dto.BreedDTO;
import com.retrofit.mapstruct.example.catfacts.mapper.BreedMapper;
@ExtendWith(MockitoExtension.class)
class BreedMappingServiceTests {
	//class under test
	private BreedMappingService mappingService;
	
	@Mock
	private BreedMapper mapperMock;
	
	@BeforeEach
	void init(){
			mappingService = new BreedMappingService(mapperMock);
		//default Mock behavior:
		when(mapperMock.breedToBreedDto(Breed.generateDefaultBreed()))
		.thenReturn(BreedDTO.generateDefaultBreedDTO());
	}
	@Test
	@DisplayName("Test mapBreedToBreedDto(Breed b), pass when returning the"
			+ " default BreedDto upon getting the Default Breed as the"
			+ " parameter.")
	void mapBreedToBreedDtoTest() {
		BreedDTO actualBreedDto = mappingService
				.mapBreedToBreedDto(Breed.generateDefaultBreed());
		assertEquals(BreedDTO.generateDefaultBreedDTO().getAdaptabilityScore(),
				actualBreedDto.getAdaptabilityScore());
		assertEquals(BreedDTO.generateDefaultBreedDTO().getAdoptionSuggestScore(),
				actualBreedDto.getAdoptionSuggestScore());
		assertEquals(BreedDTO.generateDefaultBreedDTO().getAffectionScore(),
				actualBreedDto.getAffectionScore());
		assertEquals(BreedDTO.generateDefaultBreedDTO().getAlternativeNames(),
				actualBreedDto.getAlternativeNames());
		assertEquals(BreedDTO.generateDefaultBreedDTO().getChildFriendly(),
				actualBreedDto.getChildFriendly());
		assertEquals(BreedDTO.generateDefaultBreedDTO().getDescription(),
				actualBreedDto.getDescription());
		assertEquals(BreedDTO.generateDefaultBreedDTO().getHealthIssues(),
				actualBreedDto.getHealthIssues());
		assertEquals(BreedDTO.generateDefaultBreedDTO().getName(),
				actualBreedDto.getName());
		assertEquals(BreedDTO.generateDefaultBreedDTO().getSheddingLevel(),
				actualBreedDto.getSheddingLevel());
		assertEquals(BreedDTO.generateDefaultBreedDTO().getWikipediaUrl(),
				actualBreedDto.getWikipediaUrl());
	}

}
