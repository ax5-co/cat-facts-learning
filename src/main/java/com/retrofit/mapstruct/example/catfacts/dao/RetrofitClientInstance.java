package com.retrofit.mapstruct.example.catfacts.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@PropertySource("classpath:application.yaml")
@Slf4j
public class RetrofitClientInstance {

	private static Retrofit retrofit;
	
	@Value("${api.base-url}")	//not useful at all with a static field.
	private final String API_BASE_URL ;

	//Now, I need to pass the same value to this constructor from the
	//...Repo class(more code!)
	public RetrofitClientInstance(String aPI_BASE_URL) {
		super();
		API_BASE_URL = aPI_BASE_URL;
	}
	
	public Retrofit getRetrofitInstance() {
		log.info("API_BASE_URL = "+API_BASE_URL);
		if (retrofit == null) {
			retrofit = new retrofit2.Retrofit.Builder()
					.baseUrl(API_BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}
		return retrofit;
	}
}
