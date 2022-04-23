package com.khoaluantotnghiep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.ProductOptionsEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface IProductOptionsService {
	public List<ProductOptionsEntity> findAllProdOption();

	public List<ProductOptionsEntity> GetDataProdOptionPaginate(int start, int totalPage);

	public List<ProductOptionsEntity> GetDataProdOptionTrashPaginate(int start, int totalPage);

	public List<ProductOptionsEntity> findAllProdOptionShow();

	public List<ProductOptionsEntity> findAllTrashProdOption();

	public ProductOptionsEntity findProdOptionById(ProductOptionsEntity prodop);

	public void addProdOption(ProductOptionsEntity prodop);

	public void updateProdOption(ProductOptionsEntity prodop);

	public void deleteProdOption(int productoptions_id);

	public void deltrash(int productoptions_id,UserEntity loginInfo);

	public void retrash(int productoptions_id,UserEntity loginInfo);

	public void onOffProdOption(int productoptions_id,UserEntity loginInfo);
}
