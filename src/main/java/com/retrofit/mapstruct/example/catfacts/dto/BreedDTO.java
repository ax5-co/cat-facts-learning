package com.retrofit.mapstruct.example.catfacts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BreedDTO {
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Adaptability-Score")
	private Integer adaptabilityScore;
	@JsonProperty("Affection-Score")
	private Integer affectionScore;
	@JsonProperty("Alternative-Names")
	private String alternativeNames;
	@JsonProperty("Child-Friendly-Score")
	private Integer childFriendly;
	@JsonProperty("Description")
	private String description;
	@JsonProperty("Health-Issues-Score")
	private Integer healthIssues;
	@JsonProperty("Shedding-Level")
	private Integer sheddingLevel;
	@JsonProperty("Wikipedia-Url")
	private String wikipediaUrl;
	@JsonProperty("Adoption-Suggest-Score")
	private Integer adoptionSuggestScore;
	
	public static BreedDTO generateDefaultBreedDTO() {
		return new BreedDTO("BreedDTO def name", 5, 5, "BreedDTO def altNames",
				5, "BreedDTO def desc", 5, 5, "BreedDTO def wikipediaUrl", 5);
	}
}
