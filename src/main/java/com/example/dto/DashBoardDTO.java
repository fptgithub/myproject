package com.example.dto;

import java.text.DecimalFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashBoardDTO {

	private Long totalUser;
	private Long totalOrder;
	private Double totalSale;
	private Long totalProductSold;
	private Long totalEvaluate;
	private Double avgStar;
	
	public String avgStar() {
		return new DecimalFormat(".##").format(this.avgStar);
	}
}
