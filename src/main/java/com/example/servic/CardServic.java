package com.example.servic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.dao.CardDAO;
import com.example.dao.ProductDAO;
import com.example.dao.UserDAO;
import com.example.entity.Card;

@Service
public class CardServic {

	@Autowired
	CardDAO cardDAO;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	UserDAO userDAO;

	public Card addTocard(User user, Integer productId,Integer quantity) {
		Card c = cardDAO.findCardByUserAndProduct(user.getUsername(), productId);
		if (c != null) {
			if(quantity!=null&&quantity>0) {
				c.setQuantity(c.getQuantity()+quantity);
				cardDAO.save(c);
				return c;
			}
			c.setQuantity(c.getQuantity() + 1);
			cardDAO.save(c);
			return c;
		} else {
			Card card = Card.builder().product(productDAO.findById(productId).get()).quantity(1)
					.user(userDAO.findByUsername(user.getUsername()).get()).build();
			if(quantity!=null&&quantity>0) {
				cardDAO.save(card);
				card.setQuantity(quantity);
				return card;
			}
			cardDAO.save(card);
			return card;
		}
	}

	public Card reduce(User user, Integer productId) {
		Card c = cardDAO.findCardByUserAndProduct(user.getUsername(), productId);
		if (c != null && c.getQuantity() > 1) {
			c.setQuantity(c.getQuantity() - 1);
			cardDAO.save(c);
			return c;
		}
		return c;
	}

	public void delete(User user, Integer productId) {
		Card c = cardDAO.findCardByUserAndProduct(user.getUsername(), productId);
		if (c != null) {
			cardDAO.delete(c);
		}
	}

	public List<Card> findAllByUsername(String username) {
		return cardDAO.findAllByUsername(username);
	}
}
