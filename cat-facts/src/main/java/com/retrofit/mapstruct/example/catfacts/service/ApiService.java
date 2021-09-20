package com.retrofit.mapstruct.example.catfacts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.retrofit.mapstruct.example.catfacts.dao.TheCatApiRetrofitServiceRepo;
import com.retrofit.mapstruct.example.catfacts.dto.BreedDTO;
import com.retrofit.mapstruct.example.catfacts.dto.BreedsResponse;
import com.retrofit.mapstruct.example.catfacts.dto.TheCatApiBreedDto;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

@Slf4j
@Service
public class ApiService {
	
	private final java.lang.String key;

	private final TheCatApiRetrofitServiceRepo catApiService;

	private final BreedMappingService breedMappingService;
	
	public ApiService(@Value("${api.key}") String aPI_KEY, 
			TheCatApiRetrofitServiceRepo catApiService,
			BreedMappingService breedMappingService) {
		super();
		key = aPI_KEY;
		this.catApiService = catApiService;
		this.breedMappingService = breedMappingService;
	}

	public BreedsResponse getAllBreeds(int page, int limit) {
		
		log.info("API_KEY= " + key);
		
		BreedsResponse responseDto =new BreedsResponse();
		try {
			Response<List<TheCatApiBreedDto>> allBreedsResponse =
					catApiService.getAllBreeds(key, page, limit);
			List<TheCatApiBreedDto> apiBreeds = allBreedsResponse.body();
			List<BreedDTO> breedDTOs =breedMappingService
					.mapApiBreedsToBreedDtos(apiBreeds);
			if(limit == 0)
				limit = 1;
			responseDto = BreedsResponse.builder()
					.breeds(breedDTOs)
					.currentPage(Integer.parseInt(allBreedsResponse.headers()
					.get("Pagination-Page")))
					.displayedElementsCount(limit)
					.totalElementsCount(Integer.parseInt(allBreedsResponse
							.headers().get("Pagination-Count")))
					.totalItems(Integer.parseInt(allBreedsResponse
							.headers().get("Pagination-Count")))
					.totalPages(Math.floorDiv(Integer
					.parseInt(allBreedsResponse.headers()
							.get("Pagination-Count")) , limit))
					.build();
			
		} catch (IOException e) {
			log.error("retrofit.execute(): "
					+ "a problem occurred talking to the server");
			e.printStackTrace();
		}catch(RuntimeException e) {
			log.error("retrofit.execute(): "
					+ "an unexpected error occurs creating the request"
					+ " or decoding the response.");
			e.printStackTrace();
		}
		return responseDto;
	}

	public BreedsResponse getAllBreedsContaining(String searchTerm,
			int limit) {
		
		BreedsResponse responseDto = new BreedsResponse();
		try {
			Response<List<TheCatApiBreedDto>> allBreedsResponse =
					catApiService.getSearchedBreeds(key, searchTerm, limit);
			List<TheCatApiBreedDto> apiBreeds = allBreedsResponse.body();
			List<BreedDTO> breedDTOs =breedMappingService
					.mapApiBreedsToBreedDtos(apiBreeds);
			if(breedDTOs.size() < limit || 0 > limit)
				limit = breedDTOs.size();
			
			responseDto = BreedsResponse.builder()
					.breeds(breedDTOs.subList(0, limit))
					.totalElementsCount( breedDTOs.size())
					.displayedElementsCount(limit)
					.currentPage(0)
					.totalPages(1)
					.totalItems(limit)
					.build();
			//currentPage, totalPages & totalItems are set here as defaults.
			
		} catch (IOException e) {
			log.error("retrofit.execute(): "
					+ "a problem occurred talking to the server");
			e.printStackTrace();
		}catch(RuntimeException e) {
			log.error("retrofit.execute(): "
					+ "an unexpected error occurs creating the request"
					+ " or decoding the response.");
			e.printStackTrace();
		}
		return responseDto;
	}
}
