package com.retrofit.mapstruct.example.catfacts.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.retrofit.mapstruct.example.catfacts.TheCatApiConfiguration;
import com.retrofit.mapstruct.example.catfacts.dto.TheCatApiBreedDto;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
@Repository
@Slf4j
public class TheCatApiRetrofitServiceRepo  implements TheCatApiConfiguration{

	public Response<List<TheCatApiBreedDto>> getAllBreeds(String apiKey,
			int page, int limit) throws IOException {
		Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
		TheCatApiRetrofitDAO breedRetrofitDAO = retrofit
								.create(TheCatApiRetrofitDAO.class);
		Call<List<TheCatApiBreedDto>> call= breedRetrofitDAO
				.getAllBreeds(API_KEY, page, limit);
		Response<List<TheCatApiBreedDto>> response = call.execute();
		return response;
	}

	public Response<List<TheCatApiBreedDto>> getSearchedBreeds(String apiKey,
			String q, int limit) throws IOException {
		Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
		TheCatApiRetrofitDAO breedRetrofitDAO = retrofit
								.create(TheCatApiRetrofitDAO.class);
		Call<List<TheCatApiBreedDto>> call= breedRetrofitDAO
				.getSearchedBreeds(API_KEY, q, limit);
		return call.execute();
	}

}
