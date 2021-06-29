package com.example.servic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CategoryDAO;
import com.example.dto.CategoryMenuDTO;
import com.example.entity.Category;
import com.example.utils.IndexDefine;

@Service
public class CategoryServic {
	@Autowired
	CategoryDAO categoryDAO;
	
	public Map<Category, String> findAllCategory(){
		Map<Category,String> mapCategorys = new HashMap<Category, String>();
		for(Category c:categoryDAO.findAll()) {
			mapCategorys.put(c, c.getName());
		}
		return mapCategorys;
	}

	public List<CategoryMenuDTO> CategorysByCustomType() {
		List<CategoryMenuDTO> list = new ArrayList<CategoryMenuDTO>();
		Integer index = null;
		for (Object[] object : categoryDAO.findAllCategoryAndCustomType()) {
			if ((index = checkList(list,
					(String) object[IndexDefine.CategoryAndCustomType.CustomTypeName.index])) != null) {
				addCateTolist(list.get(index), object);
			} else {
				CategoryMenuDTO cate = new CategoryMenuDTO(
						(String)object[IndexDefine.CategoryAndCustomType.CustomTypeName.index],
						(String)object[IndexDefine.CategoryAndCustomType.CustomTypeSlug.index]
				);
				addCateTolist(cate, object);
				list.add(cate);
			}
		}
		return list;
	}

	public void addCateTolist(CategoryMenuDTO cate,Object[] object) {
		String[] strArr = {(String)object[IndexDefine.CategoryAndCustomType.CategoryName.index]
				,(String)object[IndexDefine.CategoryAndCustomType.CategorySlug.index]};
		cate.getCategorys().add(strArr);
	}

	public Integer checkList(List<CategoryMenuDTO> list, String customName) {
		int index = 0;
		for (CategoryMenuDTO cate : list) {
			if (cate.getCustomTypeName().equalsIgnoreCase(customName)) return index;
			index++;
		}
		return null;
	}
}
