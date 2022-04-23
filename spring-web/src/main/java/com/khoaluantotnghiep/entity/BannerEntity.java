package com.khoaluantotnghiep.entity;

import java.util.Date;

public class BannerEntity {
	private int id;
	private String banner_name;
	private String banner_img;
	private int banner_status;
	private int created_by;
	private int updated_by;
	private Date created_at;
	private Date updated_at;

	public BannerEntity() {
		super();
	}

	public BannerEntity(String banner_name, String banner_img, int banner_status, int created_by, int updated_by,
			Date created_at, Date updated_at) {
		super();
		this.banner_name = banner_name;
		this.banner_img = banner_img;
		this.banner_status = banner_status;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public BannerEntity(int id, String banner_name, String banner_img, int banner_status, int created_by,
			int updated_by, Date created_at, Date updated_at) {
		super();
		this.id = id;
		this.banner_name = banner_name;
		this.banner_img = banner_img;
		this.banner_status = banner_status;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBanner_name() {
		return banner_name;
	}

	public void setBanner_name(String banner_name) {
		this.banner_name = banner_name;
	}

	public String getBanner_img() {
		return banner_img;
	}

	public void setBanner_img(String banner_img) {
		this.banner_img = banner_img;
	}

	public int getBanner_status() {
		return banner_status;
	}

	public void setBanner_status(int banner_status) {
		this.banner_status = banner_status;
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

}
