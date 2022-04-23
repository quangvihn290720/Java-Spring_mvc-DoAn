package com.khoaluantotnghiep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.ProductImageEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface IProductImageService {
	public List<ProductImageEntity> GetDataProductImagePaginate(int start, int totalPage);

	public List<ProductImageEntity> GetDataProductImageTrashPaginate(int start, int totalPage);

	public List<ProductImageEntity> findAllProductImage();

	public List<ProductImageEntity> findAllProductImageShow();

	public ProductImageEntity findProductImageById(ProductImageEntity prodimg);

	public List<ProductImageEntity> findTrashProductImage();

	public void deltrashProductImage(int id,UserEntity loginInfo);

	public void retrashProductImage(int id,UserEntity loginInfo);

	public void deleteProductImage(int id);

	public void onOffProductImage(int id,UserEntity loginInfo);

	public void add(ProductImageEntity prodimg);
	
	public void update(ProductImageEntity prodimg);
}
