package com.khoaluantotnghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.ProductOptionDAO;
import com.khoaluantotnghiep.entity.ProductOptionsEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.IProductOptionsService;

@Service
public class ProductOptionsServiceImpl implements IProductOptionsService {
	@Autowired
	ProductOptionDAO prodOptionDAO;

	@Override
	public List<ProductOptionsEntity> findAllProdOption() {
		return prodOptionDAO.findAllProdOption();
	}

	@Override
	public List<ProductOptionsEntity> GetDataProdOptionPaginate(int start, int totalPage) {
		return prodOptionDAO.GetDataProdOptionPaginate(start, totalPage);
	}

	@Override
	public List<ProductOptionsEntity> GetDataProdOptionTrashPaginate(int start, int totalPage) {
		return prodOptionDAO.GetDataProdOptionTrashPaginate(start, totalPage);
	}

	@Override
	public List<ProductOptionsEntity> findAllProdOptionShow() {
		return prodOptionDAO.findAllProdOptionShow();
	}

	@Override
	public List<ProductOptionsEntity> findAllTrashProdOption() {
		return prodOptionDAO.findAllTrashProdOption();
	}

	@Override
	public ProductOptionsEntity findProdOptionById(ProductOptionsEntity prodop) {
		return prodOptionDAO.findProdOptionById(prodop);
	}

	@Override
	public void addProdOption(ProductOptionsEntity prodop) {
		prodOptionDAO.addProdOption(prodop);
	}

	@Override
	public void updateProdOption(ProductOptionsEntity prodop) {
		prodOptionDAO.updateProdOption(prodop);
	}

	@Override
	public void deleteProdOption(int productoptions_id) {
		prodOptionDAO.deleteProdOption(productoptions_id);
	}

	@Override
	public void deltrash(int productoptions_id,UserEntity loginInfo) {
		prodOptionDAO.deltrash(productoptions_id, loginInfo);
	}

	@Override
	public void retrash(int productoptions_id,UserEntity loginInfo) {
		prodOptionDAO.retrash(productoptions_id, loginInfo);
	}

	@Override
	public void onOffProdOption(int productoptions_id,UserEntity loginInfo) {
		prodOptionDAO.onOffProdOption(productoptions_id, loginInfo);
	}

}
