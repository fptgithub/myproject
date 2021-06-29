package com.example.utils;

public class IndexDefine {
	public static enum CategoryAndCustomType{
		CustomTypeName(0),
		CustomTypeSlug(1),
		CategoryName(2),
		CategorySlug(3);
		
		public int index;
		private CategoryAndCustomType(int index) {
			this.index = index;
		}
	}
}
