package com.khoaluantotnghiep.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dto.CartDTO;

@Service
public interface ICartService {
	@Autowired
	public HashMap<Integer, CartDTO> addCart(int id, HashMap<Integer, CartDTO> cart);

	@Autowired
	public HashMap<Integer, CartDTO> editCart(int id, int quanty, HashMap<Integer, CartDTO> cart);

	@Autowired
	public HashMap<Integer, CartDTO> deleteCart(int id, HashMap<Integer, CartDTO> cart);

	public int totalQuanty(HashMap<Integer, CartDTO> cart);

	public double totalPrice(HashMap<Integer, CartDTO> cart);
}
