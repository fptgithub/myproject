package com.example.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryMenuDTO {
	private String customTypeName;
	private String customTypeSlug;
	//index 0 is name,1 is slug
	private List<String[]> categorys = new ArrayList<String[]>();
	private categoryIndex indexName = categoryIndex.name;
	private categoryIndex indexSlug = categoryIndex.slug;
	
	public static enum categoryIndex{
		name(0),slug(1);
		public final int val;
		
		private categoryIndex(int val) {
			this.val = val;
		}

		public int getVal() {
			return val;
		}
		
	}
	public CategoryMenuDTO(String customTypeName, String customTypeSlug) {
		super();
		this.customTypeName = customTypeName;
		this.customTypeSlug = customTypeSlug;
	}
	
	
}
