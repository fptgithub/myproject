package com.example.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OderDTO {

	@NotBlank(message = "address is not empty")
	private String address;
	@NotBlank(message = "phone is not empty")
	@Size(min = 9,max = 11,message = "phone number between 9 to 11 letter")
	private String phone;
	private String description;
}
