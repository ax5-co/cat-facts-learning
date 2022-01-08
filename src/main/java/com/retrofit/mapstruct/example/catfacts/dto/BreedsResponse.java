package com.retrofit.mapstruct.example.catfacts.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BreedsResponse {

	private List<BreedDTO> breeds;
	private Integer totalElementsCount;
	private Integer displayedElementsCount;
	private Integer currentPage;
	private Integer totalItems;
	private Integer totalPages;
}
