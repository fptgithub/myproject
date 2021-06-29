package com.example.servic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.dao.ProductDAO;
import com.example.dto.PaginationRequestDTO;
import com.example.entity.Product;

@Service
public class ProductServic {

	@Autowired
	ProductDAO productDAO;
	
	public Page<Product> search(String name,PaginationRequestDTO pRequest,int defaultLimit){
		Pageable pageable = PageRequest.of(pRequest.getPage().orElse(0), pRequest.getLimit().orElse(defaultLimit),
				Sort.by(pRequest.getSort(), pRequest.getSortBy().orElse("name")));
		return productDAO.search(name,pageable);
	}

	public List<Integer> caculatorPage(Page<Product> p) {
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

	public void saveProduct(Product product) {
		productDAO.save(product);
	}

	public Page<Product> paginationAndSort(PaginationRequestDTO pRequest,int defaultLimit) {
		Pageable pageable = PageRequest.of(pRequest.getPage().orElse(0), pRequest.getLimit().orElse(defaultLimit),
				Sort.by(pRequest.getSort(), pRequest.getSortBy().orElse("name")));
		return productDAO.findAll(pageable);
	}

	public void updateProduct(Product product) {
		productDAO.save(product);
	}

	public void deleteProductById(Optional<Integer> id) {
		productDAO.deleteById(id.orElseThrow());
	}

	public Product findById(Optional<Integer> id) {
		return productDAO.findById(id.orElseThrow()).get();
	}

	public Page<Product> pagination(PaginationRequestDTO pRequest,int defaultLimit) {
		Pageable pageable = PageRequest.of(
				pRequest.getPage().orElse(0), pRequest.getLimit().orElse(defaultLimit));
		return productDAO.findAll(pageable);
	}

	public List<Product> latestProduct() {
		Pageable pageable = PageRequest.of(0, 8);
		return productDAO.findByLatest(pageable);
	}

	public List<Product> comingProduct() {
		Pageable pageable = PageRequest.of(0, 8);
		return productDAO.findByComing(pageable);
	}

	public Page<Product> findAllProductByCustomtypeAndPageable(String customtypeSlug, PaginationRequestDTO pRequest,int defaultLimit) {
		Pageable pageable = PageRequest.of(
				pRequest.getPage().orElse(0), pRequest.getLimit().orElse(defaultLimit)
				,Sort.by(pRequest.getSort(), pRequest.getSortBy().orElse("name")));
		return productDAO.findAllProductByCustomtypeAndPageable(customtypeSlug, pageable);
	}

	public Page<Product> findAllProductByCategoryAndPageable(String customtypeSlug, String categorySlug,
			PaginationRequestDTO pRequest,int defaultLimit) {
		Pageable pageable = PageRequest.of(
				pRequest.getPage().orElse(0), pRequest.getLimit().orElse(defaultLimit)
				,Sort.by(pRequest.getSort(), pRequest.getSortBy().orElse("name")));
		return productDAO.findAllProductByCategoryAndPageable(customtypeSlug, categorySlug, pageable);
	}

	public Product findProductBySlug(String customtypeSlug, String categorySlug, String productSlug) {
		return productDAO.findProductBySlug(customtypeSlug, categorySlug, productSlug);
	}

	public List<Product> dealOfWeek() {
		return productDAO.dealOfWeek(PageRequest.of(0, 9));
	}

	public List<Product> exclusiveProduct() {
		return productDAO.exclusiveProduct(PageRequest.of(0, 7));
	}
}
