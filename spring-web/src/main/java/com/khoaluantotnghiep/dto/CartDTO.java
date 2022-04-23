package com.khoaluantotnghiep.dto;

import com.khoaluantotnghiep.entity.ProductEntity;

public class CartDTO {
	private int quanty;
	private double totalprice;
	private ProductEntity productEntity;

	public CartDTO() {
		super();
	}

	public CartDTO(int quanty, double totalprice, ProductEntity productEntity) {
		super();
		this.quanty = quanty;
		this.totalprice = totalprice;
		this.productEntity = productEntity;
	}

	public int getQuanty() {
		return quanty;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

}
