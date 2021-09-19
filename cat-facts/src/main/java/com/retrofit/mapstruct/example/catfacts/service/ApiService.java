package com.retrofit.mapstruct.example.catfacts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.retrofit.mapstruct.example.catfacts.TheCatApiConfiguration;
import com.retrofit.mapstruct.example.catfacts.dao.TheCatApiRetrofitServiceRepo;
import com.retrofit.mapstruct.example.catfacts.dto.BreedDTO;
import com.retrofit.mapstruct.example.catfacts.dto.BreedsResponse;
import com.retrofit.mapstruct.example.catfacts.dto.TheCatApiBreedDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

@Slf4j
@Service
@AllArgsConstructor
public class ApiService implements TheCatApiConfiguration{
	

	private final TheCatApiRetrofitServiceRepo catApiService;

	private final BreedMappingService breedMappingService;
	
	public BreedsResponse getAllBreeds(int page, int limit) {
		
		BreedsResponse responseDto =new BreedsResponse();
		try {
			Response<List<TheCatApiBreedDto>> allBreedsResponse =
					catApiService.getAllBreeds(API_KEY, page, limit);
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
					catApiService.getSearchedBreeds(API_KEY, searchTerm, limit);
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
