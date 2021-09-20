package com.retrofit.mapstruct.example.catfacts.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.retrofit.mapstruct.example.catfacts.dto.TheCatApiBreedDto;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
@Repository
@Slf4j
public class TheCatApiRetrofitServiceRepo {

	@Value("${api.base-url}")
	private String API_BASE_URL;
	
	public Response<List<TheCatApiBreedDto>> getAllBreeds(@Value("${api.key}") String apiKey,
			int page, int limit) throws IOException {
		Retrofit retrofit = new RetrofitClientInstance(API_BASE_URL).getRetrofitInstance();
		TheCatApiRetrofitDAO breedRetrofitDAO = retrofit
								.create(TheCatApiRetrofitDAO.class);
		Call<List<TheCatApiBreedDto>> call= breedRetrofitDAO
				.getAllBreeds(apiKey, page, limit);
		Response<List<TheCatApiBreedDto>> response = call.execute();
		return response;
	}

	public Response<List<TheCatApiBreedDto>> getSearchedBreeds(@Value("${api.key}") String apiKey,
			String q, int limit) throws IOException {
		Retrofit retrofit = new RetrofitClientInstance(API_BASE_URL).getRetrofitInstance();
		TheCatApiRetrofitDAO breedRetrofitDAO = retrofit
								.create(TheCatApiRetrofitDAO.class);
		Call<List<TheCatApiBreedDto>> call= breedRetrofitDAO
				.getSearchedBreeds(apiKey, q, limit);
		return call.execute();
	}

}
