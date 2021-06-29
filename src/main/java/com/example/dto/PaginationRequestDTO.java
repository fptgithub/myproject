package com.example.dto;

import java.util.Optional;

import org.springframework.data.domain.Sort.Direction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PaginationRequestDTO {

	private Optional<Integer> page = Optional.empty();
	private Optional<Integer> limit = Optional.empty();
	private Optional<String> sortBy = Optional.empty();
	private Optional<String> sortName = Optional.empty();
	private String search;
	
	public Direction getSort() {
		return sortName.orElse("asc").equalsIgnoreCase("asc")?Direction.ASC:Direction.DESC;
	}
	
}
