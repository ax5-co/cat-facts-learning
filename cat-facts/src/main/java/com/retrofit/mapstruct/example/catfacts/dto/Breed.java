package com.retrofit.mapstruct.example.catfacts.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Breed {
	private Integer breedId;
	private Integer adaptabilityScore;
	private Integer affectionScore;
	private String alternativeNames;
	private String catFanciersAssociationUrl;
	private Integer childFriendly;
	private String countryCode;
	private String countryCodes;
	private String description;
	private Integer dogFriendly;
	private Integer energyLevel;
	private Integer experimental;
	private Integer grooming;
	private Integer hairless;
	private Integer healthIssues;
	private Integer hypoallergenic;
	private String id;
	private Integer indoor;
	private Integer intelligence;
	private Integer lap;
	private String lifeSpan;
	private String name;
	private Integer natural;
	private String origin;
	private Integer rare;
	private String referenceImageId;
	private Integer rex;
	private Integer sheddingLevel;
	private Integer shortLegs;
	private Integer socialNeeds;
	private Integer strangerFriendly;
	private Integer suppressedTail;
	private String temperament;
	private String vcahospitalsUrl;
	private String vetstreetUrl;
	private Integer vocalisation;
	private WeightObject weight;
	private String wikipediaUrl;
	private Integer adoptionSuggestScore;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public Integer calculateAdoptionSuggestScore() {
		try {
			int countOfImportantScores = 3;
			int score = (this.adaptabilityScore + this.affectionScore 
					+ this.childFriendly)/countOfImportantScores;
			return score;
		} catch (Exception e) {
			log.error("Breed.calculateAdoptionSuggestScore threw an Exception: "+e.getMessage());
			return 0;
		}
	}
	@Data
	public class WeightObject{
		private String weightImperial;
		private String weightMetric;
	}
	
	public static Breed generateDefaultBreed() {
		Breed breed = new Breed(1, 5, 5, "Breed def alNames",
				"Breed def cfaUrl", 5, "Breed def cc", "Breed def ccs",
				"Breed def desc", 5, 5, 5, 5, 5, 5, 5, "Breed def id",
				5, 5, 5, "Breed def lifeSpan", "Breed def name", 5,
				"Breed def origin", 5, "Breed def refImgId", 5, 5, 5, 5, 5, 5,
				"Breed def temp", "Breed def vcahUrl", "Breed def vetstUrl", 5,
				null, "Breed def wikiUrl", 5, null);
		WeightObject w = breed.new WeightObject();
		w.setWeightImperial("Breed W def weightImperial");
		w.setWeightMetric("Breed W def weightMetric");
		breed.setWeight(w);
		return breed;
	}
}
