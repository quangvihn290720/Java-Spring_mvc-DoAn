package com.khoaluantotnghiep.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.CartDAO;
import com.khoaluantotnghiep.dto.CartDTO;
import com.khoaluantotnghiep.service.ICartService;

@Service
public class CartServiceImpl implements ICartService {
	@Autowired
	private CartDAO cartDAO = new CartDAO();

	public HashMap<Integer, CartDTO> addCart(int id, HashMap<Integer, CartDTO> cart) {
		return cartDAO.addCart(id, cart);
	}

	public HashMap<Integer, CartDTO> editCart(int id, int quanty, HashMap<Integer, CartDTO> cart) {
		return cartDAO.editCart(id, quanty, cart);
	}

	public HashMap<Integer, CartDTO> deleteCart(int id, HashMap<Integer, CartDTO> cart) {
		return cartDAO.deleteCart(id, cart);
	}

	public int totalQuanty(HashMap<Integer, CartDTO> cart) {
		return cartDAO.totalQuanty(cart);
	}

	public double totalPrice(HashMap<Integer, CartDTO> cart) {
		return cartDAO.totalPrice(cart);
	}

}
