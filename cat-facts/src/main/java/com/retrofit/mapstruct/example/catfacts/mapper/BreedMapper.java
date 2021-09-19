package com.retrofit.mapstruct.example.catfacts.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.retrofit.mapstruct.example.catfacts.dto.Breed;
import com.retrofit.mapstruct.example.catfacts.dto.BreedDTO;
import com.retrofit.mapstruct.example.catfacts.dto.TheCatApiBreedDto;
import com.retrofit.mapstruct.example.catfacts.dto.TheCatApiBreedDto.WeightObject;

@Mapper(componentModel = "spring",
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BreedMapper {

	BreedMapper INSTANCE = Mappers.getMapper(BreedMapper.class);
	
	BreedDTO breedToBreedDto(Breed breed);
	
	@Mapping(target = "breedId", ignore = true)
	@Mapping(source = "adaptability", target = "adaptabilityScore")
	@Mapping(source = "affectionLevel", target = "affectionScore")
	@Mapping(source = "altNames", target = "alternativeNames")
	@Mapping(source = "cfaUrl", target = "catFanciersAssociationUrl")
	@Mapping(source = "apiBreed", target = "adoptionSuggestScore",
			qualifiedByName  = "calculatedAdoptionScore")
	@Mapping(target = "weight", qualifiedByName="generatedWeightObject")
	Breed apiBreedToBreed(TheCatApiBreedDto apiBreed);

	List<BreedDTO> breedsToBreedDtos(List<Breed> breeds);
	
	List<Breed> apiBreedsToBreeds(List<TheCatApiBreedDto> apiBreeds);
	
	@Named("calculatedAdoptionScore")
	default int calculateAdoptionScore(TheCatApiBreedDto apiBreed) {
		try {
			int countOfImportantScores = 3;
			return (apiBreed.getAdaptability() + apiBreed.getAffectionLevel()
					+ apiBreed.getChildFriendly())/countOfImportantScores;
		} catch (Exception e) {
			throw new RuntimeException("Mapper threw: "+e.getMessage());
		}
	}
	
	@Named("generatedWeightObject")
	default Breed.WeightObject weightObjectToWeightObject(WeightObject weightObject) {
        if ( weightObject == null ) {
            return null;
        }

        Breed b = new Breed();
        Breed.WeightObject weightObject1 = b.new WeightObject();

        weightObject1.setWeightImperial( weightObject.getWeightImperial() );
        weightObject1.setWeightMetric( weightObject.getWeightMetric() );

        return weightObject1;
    }
}
