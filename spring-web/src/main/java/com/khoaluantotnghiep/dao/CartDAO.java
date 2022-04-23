package com.khoaluantotnghiep.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.khoaluantotnghiep.dto.CartDTO;
import com.khoaluantotnghiep.entity.ProductEntity;

@Repository
public class CartDAO extends BaseDAO {
	@Autowired
	ProductDAO productDAO = new ProductDAO();

	public HashMap<Integer, CartDTO> addCart(int id, HashMap<Integer, CartDTO> cart) {
		CartDTO itemCart = new CartDTO();
		ProductEntity product = productDAO.findProductById(id);
		if (product != null) {
			if (cart.containsKey(id)) {
				itemCart = cart.get(id);
				itemCart.setQuanty(itemCart.getQuanty() + 1);
				itemCart.setTotalprice(itemCart.getQuanty() * itemCart.getProductEntity().getProductpricesale());
			} else {
				itemCart.setProductEntity(product);
				itemCart.setQuanty(1);
				itemCart.setTotalprice(product.getProductpricesale());
			}
			cart.put(id, itemCart);
		}
		return cart;
	}

	public HashMap<Integer, CartDTO> editCart(int id, int quanty, HashMap<Integer, CartDTO> cart) {
		if (cart == null) {
			return cart;
		}
		CartDTO itemCart = new CartDTO();
		if (cart.containsKey(id)) {
			itemCart = cart.get(id);
			itemCart.setQuanty(quanty);
			double totalPrice = quanty * itemCart.getProductEntity().getProductpricesale();
			itemCart.setTotalprice(totalPrice);
		}
		cart.put(id, itemCart);
		return cart;
	}

	public HashMap<Integer, CartDTO> deleteCart(int id, HashMap<Integer, CartDTO> cart) {
		if (cart == null) {
			return cart;
		}
		if (cart.containsKey(id)) {
			cart.remove(id);
		}
		return cart;
	}

	public int totalQuanty(HashMap<Integer, CartDTO> cart) {
		int totalQuanty = 0;
		for (Map.Entry<Integer, CartDTO> itemCart : cart.entrySet()) {
			totalQuanty += itemCart.getValue().getQuanty();
		}
		return totalQuanty;
	}

	public double totalPrice(HashMap<Integer, CartDTO> cart) {
		double totalPrice = 0;
		for (Map.Entry<Integer, CartDTO> itemCart : cart.entrySet()) {
			totalPrice += itemCart.getValue().getTotalprice();
		}
		return totalPrice;
	}
}
