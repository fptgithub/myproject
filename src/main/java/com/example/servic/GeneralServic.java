package com.example.servic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.EvaluateDAO;
import com.example.dao.OderDAO;
import com.example.dao.OderDetailDAO;
import com.example.dao.UserDAO;
import com.example.dto.DashBoardDTO;

@Service
public class GeneralServic {

	@Autowired
	UserDAO userDAO;
	@Autowired
	OderDetailDAO oderDetailDAO;
	@Autowired
	OderDAO oderDAO;
	@Autowired
	EvaluateDAO evaluateDAO;
	
	public DashBoardDTO dashBoard() {
		return DashBoardDTO.builder()
				.totalUser((long)userDAO.findAll().size())
				.totalSale(oderDAO.totalSale())
				.totalProductSold(oderDetailDAO.totalProductSold())
				.totalOrder((long)oderDAO.findAll().size())
				.totalEvaluate((long)evaluateDAO.findAll().size())
				.avgStar(evaluateDAO.avgStar())
				.build();
	}
}
