package com.retrofit.mapstruct.example.catfacts.dao;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.retrofit.mapstruct.example.catfacts.TheCatApiConfiguration;

public class RetrofitClientInstance implements TheCatApiConfiguration{

	private static Retrofit retrofit;
	
	public static Retrofit getRetrofitInstance() {
		if (retrofit == null) {
			retrofit = new retrofit2.Retrofit.Builder()
					.baseUrl(API_BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}
		return retrofit;
	}
}
