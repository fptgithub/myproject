package com.example.dto;

import java.text.DecimalFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvaluateAvgDTO {
	
	private Double avgStar;
	private Long totalEvaluate;
	
	public String formatAvg() {
		return new DecimalFormat("#.0").format(this.avgStar);
	}
}
