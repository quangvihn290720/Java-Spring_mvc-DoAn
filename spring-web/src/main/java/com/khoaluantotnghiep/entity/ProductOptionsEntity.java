package com.khoaluantotnghiep.entity;

import java.util.Date;

public class ProductOptionsEntity {
	private int productoptions_id;
	private int product_id;
	private int optiongroup_id;
	private int option_id;
	private String metakey;
	private String metadesc;
	private int status;
	private int created_by;
	private int updated_by;
	private Date created_at;
	private Date updated_at;

	public String getMetakey() {
		return metakey;
	}

	public void setMetakey(String metakey) {
		this.metakey = metakey;
	}

	public String getMetadesc() {
		return metadesc;
	}

	public void setMetadesc(String metadesc) {
		this.metadesc = metadesc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getProductoptions_id() {
		return productoptions_id;
	}

	public void setProductoptions_id(int productoptions_id) {
		this.productoptions_id = productoptions_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getOptiongroup_id() {
		return optiongroup_id;
	}

	public void setOptiongroup_id(int optiongroup_id) {
		this.optiongroup_id = optiongroup_id;
	}

	public int getOption_id() {
		return option_id;
	}

	public void setOption_id(int option_id) {
		this.option_id = option_id;
	}

}
