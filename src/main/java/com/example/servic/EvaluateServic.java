package com.example.servic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.dao.EvaluateDAO;
import com.example.dao.ProductDAO;
import com.example.dao.UserDAO;
import com.example.dto.EvaluateAvgDTO;
import com.example.entity.Evaluate;

@Service
public class EvaluateServic {

	@Autowired
	EvaluateDAO evaluateDAO;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	UserDAO userDAO;
	
	public EvaluateAvgDTO findAvgOfEvaluate(Integer productId) {
		return evaluateDAO.findAvg(productId);
	}
	
	public void createEvaluate(String username,Evaluate evaluate,Optional<Integer> productId) {
		evaluate.setProduct(productDAO.findById(productId.get()).orElseThrow());
		evaluate.setUser(userDAO.findByUsername(username).orElseThrow());
		evaluateDAO.save(evaluate);
	}
	
	public List<Evaluate> findThreeEvaluateAndUserEvaluate(String username,Integer productId){
		List<Evaluate> list = evaluateDAO.findAllExcludeUsername(username, productId, PageRequest.of(0, 3));
		list.addAll(evaluateDAO.findAllByUsername(username,productId));
		return list;
	}
	public List<Evaluate> findThreeEvaluate(Integer productId){
		return evaluateDAO.findAllEvaluates(productId,PageRequest.of(0, 3));
	}
	
	public void deleteEvaluate(Integer evaluateId) {
		evaluateDAO.deleteById(evaluateId);
	}
}
