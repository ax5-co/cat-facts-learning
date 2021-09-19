package com.retrofit.mapstruct.example.catfacts.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//this is what the dto corresponding to json of 
//https://api.thecatapi.com/v1/breeds
public class TheCatApiBreedDto {
	@SerializedName("adaptability")
	private Integer adaptability;
	@SerializedName("affection_level")
	private Integer affectionLevel;
	@SerializedName("alt_names")
	private String altNames;
	@SerializedName("cfa_url")
	private String cfaUrl;
	@SerializedName("child_friendly")
	private Integer childFriendly;
	@SerializedName("country_code")
	private String countryCode;
	@SerializedName("country_codes")
	private String countryCodes;
	@SerializedName("description")
	private String description;
	@SerializedName("dog_friendly")
	private Integer dogFriendly;
	@SerializedName("energy_level")
	private Integer energyLevel;
	@SerializedName("experimental")
	private Integer experimental;
	@SerializedName("grooming")
	private Integer grooming;
	@SerializedName("hairless")
	private Integer hairless;
	@SerializedName("health_issues")
	private Integer healthIssues;
	@SerializedName("hypoallergenic")
	private Integer hypoallergenic;
	@SerializedName("id")
	private String id;
	@SerializedName("indoor")
	private Integer indoor;
	@SerializedName("intelligence")
	private Integer intelligence;
	@SerializedName("lap")
	private Integer lap;
	@SerializedName("life_span")
	private String lifeSpan;
	@SerializedName("name")
	private String name;
	@SerializedName("natural")
	private Integer natural;
	@SerializedName("origin")
	private String origin;
	@SerializedName("rare")
	private Integer rare;
	@SerializedName("reference_image_id")
	private String referenceImageId;
	@SerializedName("rex")
	private Integer rex;
	@SerializedName("shedding_level")
	private Integer sheddingLevel;
	@SerializedName("short_legs")
	private Integer shortLegs;
	@SerializedName("social_needs")
	private Integer socialNeeds;
	@SerializedName("stranger_friendly")
	private Integer strangerFriendly;
	@SerializedName("suppressed_tail")
	private Integer suppressedTail;
	@SerializedName("temperament")
	private String temperament;
	@SerializedName("vcahospitals_url")
	private String vcahospitalsUrl;
	@SerializedName("vetstreet_url")
	private String vetstreetUrl;
	@SerializedName("vocalisation")
	private Integer vocalisation;
	@SerializedName("weight")
	private WeightObject weight;
	@SerializedName("wikipedia_url")
	private String wikipediaUrl;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@Data
	public class WeightObject {
		@SerializedName("imperial")
		private String weightImperial;
		@SerializedName("metric")
		private String weightMetric;
	}
	
	public static TheCatApiBreedDto generateDefaultTheCatApiBreedDto() {
		TheCatApiBreedDto b = new TheCatApiBreedDto( 5, 5,
				"ApiBreed def alNames",	"ApiBreed def cfaUrl", 5,
				"ApiBreed def cc", "ApiBreed def ccs", "ApiBreed def desc",
				5, 5, 5, 5, 5, 5, 5, "ApiBreed def id", 5, 5, 5,
				"ApiBreed def lifeSpan", "ApiBreed def name", 5,
				"ApiBreed def origin", 5, "ApiBreed def refImgId", 5, 5, 5, 5,
				5, 5, "ApiBreed def temp", "ApiBreed def vcahUrl",
				"ApiBreed def vetstUrl", 5, null, "ApiBreed def wikiUrl", null);
		WeightObject w = b.new WeightObject();
		w.setWeightImperial("ApiBreed W def weightImperial");
		w.setWeightMetric("ApiBreed W def weightMetric");
		b.setWeight(w);
		return b;
		}
}
