package com.retrofit.mapstruct.example.catfacts.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.retrofit.mapstruct.example.catfacts.TheCatApiConfiguration;
import com.retrofit.mapstruct.example.catfacts.dto.TheCatApiBreedDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
@Repository
public interface TheCatApiRetrofitDAO extends TheCatApiConfiguration {

	@GET("/v1/breeds")
	Call<List<TheCatApiBreedDto>> getAllBreeds(
			@Header("x-api-key")String apiKey,
			@Query("page") int page,
			@Query("limit") int limit);
	
	@GET("/v1/breeds/search")
	Call<List<TheCatApiBreedDto>> getSearchedBreeds(
			@Header("x-api-key")String apiKey,
			@Query("q") String q, 
			@Query("limit") int limit);
}
