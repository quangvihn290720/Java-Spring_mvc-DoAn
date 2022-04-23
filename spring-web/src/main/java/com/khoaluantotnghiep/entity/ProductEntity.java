package com.khoaluantotnghiep.entity;

import java.util.Date;

public class ProductEntity {
	private int product_id;
	private String productname;
	private String product_slug;
	private float productprice;
	private float productpricesale;
	private float productweight;
	private String productdetail;
	private String productshortdesc;
	private String productimg;
	private String product_guarantee;
	private int manufacturer_id;
	private int product_catid;
	private int product_status;
	private int product_available;
	private Date created_at;
	private Date updated_at;
	private int created_by;
	private int updated_by;
	private String 	product_condition;

	public ProductEntity() {
		super();
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public float getProductprice() {
		return productprice;
	}

	public void setProductprice(float productprice) {
		this.productprice = productprice;
	}

	public float getProductpricesale() {
		return productpricesale;
	}

	public void setProductpricesale(float productpricesale) {
		this.productpricesale = productpricesale;
	}

	public float getProductweight() {
		return productweight;
	}

	public void setProductweight(float productweight) {
		this.productweight = productweight;
	}

	public String getProductdetail() {
		return productdetail;
	}

	public void setProductdetail(String productdetail) {
		this.productdetail = productdetail;
	}

	public String getProductshortdesc() {
		return productshortdesc;
	}

	public void setProductshortdesc(String productshortdesc) {
		this.productshortdesc = productshortdesc;
	}

	public String getProductimg() {
		return productimg;
	}

	public void setProductimg(String productimg) {
		this.productimg = productimg;
	}

	public String getProduct_guarantee() {
		return product_guarantee;
	}

	public void setProduct_guarantee(String product_guarantee) {
		this.product_guarantee = product_guarantee;
	}

	public int getManufacturer_id() {
		return manufacturer_id;
	}

	public void setManufacturer_id(int manufacturer_id) {
		this.manufacturer_id = manufacturer_id;
	}


	public int getProduct_catid() {
		return product_catid;
	}

	public void setProduct_catid(int product_catid) {
		this.product_catid = product_catid;
	}

	public int getProduct_status() {
		return product_status;
	}

	public void setProduct_status(int product_status) {
		this.product_status = product_status;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public String getProduct_condition() {
		return product_condition;
	}

	public void setProduct_condition(String product_condition) {
		this.product_condition = product_condition;
	}

	public String getProduct_slug() {
		return product_slug;
	}

	public void setProduct_slug(String product_slug) {
		this.product_slug = product_slug;
	}

	public int getProduct_available() {
		return product_available;
	}

	public void setProduct_available(int product_available) {
		this.product_available = product_available;
	}

}
