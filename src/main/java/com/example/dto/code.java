package com.example.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class code {

	LocalDateTime timeout = LocalDateTime.now();
	String code;
	String email;
	
	public boolean isTimeout() {
		return timeout.plusMinutes(5).compareTo(LocalDateTime.now())>0;
	}
}
