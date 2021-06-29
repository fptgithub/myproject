package com.example.servic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.dao.CardDAO;
import com.example.dao.OderDAO;
import com.example.dao.OderDetailDAO;
import com.example.dao.UserDAO;
import com.example.dto.OderDTO;
import com.example.dto.PaginationRequestDTO;
import com.example.entity.Card;
import com.example.entity.Oder;
import com.example.entity.OderDetail;
import com.example.entity.User;
import com.example.utils.EOrderState;
import com.example.utils.EmailUtils;

@Service
public class OrderServic {

	@Autowired
	OderDAO oderDAO;
	@Autowired
	CardDAO cardDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	OderDetailDAO detailDAO;
	@Autowired
	EmailUtils emailUtils;
	
	public static final String EMAILSUBJECT = "your order";
	public static final int LENGTH_OF_RANDOM_ORDER_ID = 24;
	
	public List<OderDetail> findAllByOrderId(Integer orderid) {
		return detailDAO.findAllByOderId(orderid);
	}
	
	public Page<Oder> search(String name,PaginationRequestDTO pRequest,int defaultLimit){
		Pageable pageable = PageRequest.of(pRequest.getPage().orElse(0), pRequest.getLimit().orElse(defaultLimit),
				Sort.by(pRequest.getSort(), pRequest.getSortBy().orElse("orderIdStr")));
		return oderDAO.search(name,pageable);
	}
	
	public Page<Oder> paginationAndSort(PaginationRequestDTO pRequest,int defaultLimit) {
		Pageable pageable = PageRequest.of(pRequest.getPage().orElse(0), pRequest.getLimit().orElse(defaultLimit),
				Sort.by(pRequest.getSort(), pRequest.getSortBy().orElse("orderIdStr")));
		return oderDAO.findAll(pageable);
	}
	
	public List<Integer> caculatorPage(Page<Oder> p) {
		List<Integer> list = new ArrayList<Integer>();
		// neu page la 1 hoac 2 thi bat dau tu 1 cho den 5
		// neu page lon hon 2 thi lay page hien tai lam trung tam, lay 2 page trai,phai
		// hai ben
		for (int i = p.getNumber() - 2 >= 1 ? p.getNumber() - 2 : 1,
				j = 1; i <= (p.getNumber() - 2 >= 1 ? p.getNumber() + 2 : 5); i++) {
			// neu page hien tai vuot qua totalpage thi chuyen nguoc page ve vi tri dau tien
			if (i > p.getTotalPages()-1) {
				if (p.getNumber() - j <= 0)
					break;
				if ((p.getNumber() - j - 2) >= 1) {
					list.add(0, p.getNumber() - j - 2);
				}
				j++;
			} else if (i >= 1) {
				list.add(i);
			}
		}
		return list;
	}
	
	public List<OderDetail> findByOrderId(Integer id){
		return detailDAO.findAllByOderId(id);
	}
	
	public Oder findByOrderIdStr(String id) {
		return oderDAO.findByOrderIdStr(id);
	}
	
	public Oder create(String username,OderDTO dto) {
		List<Card> list = cardDAO.findAllByUsername(username);
		if(list.isEmpty()||list.size()==0) {
			return null;
		}
		Oder oder = dtoToOrder(username, dto,totalPrice(list));
		detailDAO.saveAll(listCardToListOrderDetail(list,oder));
		cardDAO.deleteAll(list);
		emailUtils.putMimeMessage(userDAO.findByUsername(username).get().getEmail(), EMAILSUBJECT, renderTestEmail(oder));
		return oder;
	};
	
	public String renderTestEmail(Oder oder) {
		return "<h3>your order Id : "+oder.getOrderIdStr()+"</h3>";
	}
	
	public Double totalPrice(List<Card> list) {
		Double total = 0.0;
		for(Card c:list) {
			total+=c.getProduct().getSalePrice()*c.getQuantity();
		}
		return total;
	}
	
	public Oder dtoToOrder(String username,OderDTO dto,Double total) {
		Oder oder = new Oder();
		oder.setAddress(dto.getAddress());
		oder.setUser(userDAO.findByUsername(username).get());
		oder.setDescription(dto.getDescription());
		oder.setCreateDate(LocalDate.now());
		oder.setOrderIdStr(RandomStringUtils.randomAlphanumeric(LENGTH_OF_RANDOM_ORDER_ID));
		oder.setState(EOrderState.RECEIVE);
		oder.setPhone(dto.getPhone());
		oder.setTotalPrice(total);
		return oderDAO.save(oder);
	}
	
	public List<OderDetail> listCardToListOrderDetail(List<Card> listCard,Oder oder){
		List<OderDetail> listOrder = new ArrayList<OderDetail>();
		for(Card c:listCard) {
			listOrder.add(OderDetail.builder()
					.product(c.getProduct())
					.oder(oder)
					.quantity(c.getQuantity())
					.build()
					);
		}
		return listOrder;
	}
}
