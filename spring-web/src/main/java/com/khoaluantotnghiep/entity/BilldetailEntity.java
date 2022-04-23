package com.khoaluantotnghiep.entity;

public class BilldetailEntity {
	private int id;
	private int product_id;
	private int bills_id;
	private int quanty;
	private double total;
	private ProductEntity productinfo;

	public ProductEntity getProductinfo() {
		return productinfo;
	}

	public void setProductinfo(ProductEntity productinfo) {
		this.productinfo = productinfo;
	}

	public BilldetailEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getBills_id() {
		return bills_id;
	}

	public void setBills_id(int bills_id) {
		this.bills_id = bills_id;
	}

	public int getQuanty() {
		return quanty;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
