package com.example.servic;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomTypeDAO;
import com.example.entity.CustomType;

@Service
public class CustomTypeServic {
	@Autowired
	CustomTypeDAO customTypeDAO;
	
	public Map<CustomType, String> findAllCustomType(){
		Map<CustomType, String> mapCustom = new HashMap<CustomType, String>();
		for(CustomType c:customTypeDAO.findAll()) {
			mapCustom.put(c, c.getName());
		}
		return mapCustom;
	}
}
