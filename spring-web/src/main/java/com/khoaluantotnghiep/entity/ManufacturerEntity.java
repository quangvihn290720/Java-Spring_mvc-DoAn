package com.khoaluantotnghiep.entity;

import java.util.Date;

public class ManufacturerEntity {
	private int manufacturer_id;
	private String manufacturer_name;
	private String manufacturer_slug;
	private String manufacturer_img;
	private int status;
	private Date created_at;
	private Date updated_at;
	private int created_by;
	private int updated_by;

	public ManufacturerEntity() {
		super();
	}

	public int getManufacturer_id() {
		return manufacturer_id;
	}

	public void setManufacturer_id(int manufacturer_id) {
		this.manufacturer_id = manufacturer_id;
	}

	public String getManufacturer_name() {
		return manufacturer_name;
	}

	public void setManufacturer_name(String manufacturer_name) {
		this.manufacturer_name = manufacturer_name;
	}

	public String getManufacturer_slug() {
		return manufacturer_slug;
	}

	public void setManufacturer_slug(String manufacturer_slug) {
		this.manufacturer_slug = manufacturer_slug;
	}

	public String getManufacturer_img() {
		return manufacturer_img;
	}

	public void setManufacturer_img(String manufacturer_img) {
		this.manufacturer_img = manufacturer_img;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

}
