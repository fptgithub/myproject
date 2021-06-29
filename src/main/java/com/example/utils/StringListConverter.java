package com.example.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String>{

	private static final String SPLIT_CHAR = ";";
	
	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		return attribute != null ? String.join(SPLIT_CHAR, attribute) : "";
	}
	
	@Override
	public List<String> convertToEntityAttribute(String string) {
		return string != null ? Arrays.asList(string.split(SPLIT_CHAR)) : new ArrayList<String>();
	}
}
