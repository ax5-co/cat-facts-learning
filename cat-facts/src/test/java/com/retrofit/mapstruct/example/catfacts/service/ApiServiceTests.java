package com.retrofit.mapstruct.example.catfacts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.retrofit.mapstruct.example.catfacts.dao.TheCatApiRetrofitServiceRepo;
import com.retrofit.mapstruct.example.catfacts.dto.BreedDTO;
import com.retrofit.mapstruct.example.catfacts.dto.BreedsResponse;
import com.retrofit.mapstruct.example.catfacts.dto.TheCatApiBreedDto;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import retrofit2.Response;
@Slf4j
@ExtendWith(MockitoExtension.class)
class ApiServiceTests {

	//class under test
	private ApiService apiService;
	private static Response<List<TheCatApiBreedDto>> expectedResponse;
	
	@Mock
	private TheCatApiRetrofitServiceRepo mockCatApiRetrofitServiceRepo;
	@Mock
	private BreedMappingService mockBreedMappingService;
	
	@BeforeEach
	void init() throws IOException{
		List<TheCatApiBreedDto> apiBreedsList = new ArrayList<>();
		apiBreedsList.add(TheCatApiBreedDto.generateDefaultTheCatApiBreedDto());
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Pagination-Page", "0");
		headers.put("Pagination-Count", "1");
		expectedResponse = Response
				.success(apiBreedsList, Headers.of(headers));
		List<BreedDTO> breedDTOs = new ArrayList<>();
		breedDTOs.add(BreedDTO.generateDefaultBreedDTO());
		//default Mock behavior:
		when(mockBreedMappingService.mapApiBreedsToBreedDtos(apiBreedsList))
		.thenReturn(breedDTOs);
		apiService = new ApiService(mockCatApiRetrofitServiceRepo,
				mockBreedMappingService);
	}
	
	@Test
	void getAllBreedsTest() throws IOException {
		int page = 0;
		int limit = 1;
		
		when(mockCatApiRetrofitServiceRepo
				.getAllBreeds(Mockito.anyString(), 
						Mockito.anyInt(), Mockito.anyInt()))
		.thenReturn(expectedResponse);
		
		BreedsResponse actualReturn = apiService.getAllBreeds(page, limit);
		List<BreedDTO> expectedBreedDtos = new ArrayList<>();
		expectedBreedDtos.add(BreedDTO.generateDefaultBreedDTO());
		BreedsResponse expectedReturn = BreedsResponse.builder()
		.breeds(expectedBreedDtos)
		.currentPage(page)
		.totalPages(1)
		.totalItems(1)
		.build();
	
		List<String> actualList = new ArrayList<String>();
		List<String> expectedList = new ArrayList<String>();
		for (Object o : actualReturn.getBreeds()) 
			actualList.add(o.toString());
		for (Object o : expectedReturn.getBreeds()) 
			expectedList.add(o.toString());
		assertEquals(expectedList.size(), actualList.size());
		for(int i=0; i<expectedList.size(); i++) {
			assertEquals(expectedList.get(i), actualList.get(i));
		}
	}
	
	@Test
	void getAllBreedsContainingTest() throws IOException {
		String searchTerm = "term";
		int limit = 1;
		when(mockCatApiRetrofitServiceRepo
				.getSearchedBreeds(Mockito.anyString(), Mockito.anyString(),
						Mockito.anyInt()))
		.thenReturn(expectedResponse);
		
		BreedsResponse actualReturn = apiService
				.getAllBreedsContaining(searchTerm, limit);
		BreedsResponse expectedReturn = new BreedsResponse();
		List<BreedDTO> expectedBreedDtos = new ArrayList<>();
		expectedBreedDtos.add(BreedDTO.generateDefaultBreedDTO());
		expectedReturn.setBreeds(expectedBreedDtos);
		expectedReturn.setDisplayedElementsCount(limit);
		expectedReturn.setTotalElementsCount(expectedBreedDtos.size());
		
		List<String> actualList = new ArrayList<String>();
		List<String> expectedList = new ArrayList<String>();
		for (Object o : actualReturn.getBreeds()) 
			actualList.add(o.toString());
		for (Object o : expectedReturn.getBreeds()) 
			expectedList.add(o.toString());
		assertEquals(expectedList.size(), actualList.size());
		for(int i=0; i<expectedList.size(); i++) {
			assertEquals(expectedList.get(i), actualList.get(i));
		}
	}

}
