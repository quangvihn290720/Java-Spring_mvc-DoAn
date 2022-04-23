package com.khoaluantotnghiep.entity;

import java.util.Date;

public class SlideEntity {
	private int slide_id;
	private String slide_caption;
	private String slide_img;
	private int slide_status;
	private int created_by;
	private int updated_by;
	private Date created_at;
	private Date updated_at;

	public SlideEntity() {
		super();
	}

	public SlideEntity(String slide_caption, String slide_img, int slide_status, int created_by, int updated_by,
			Date created_at, Date updated_at) {
		super();
		this.slide_caption = slide_caption;
		this.slide_img = slide_img;
		this.slide_status = slide_status;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public SlideEntity(int slide_id, String slide_caption, String slide_img, int slide_status, int created_by,
			int updated_by, Date created_at, Date updated_at) {
		super();
		this.slide_id = slide_id;
		this.slide_caption = slide_caption;
		this.slide_img = slide_img;
		this.slide_status = slide_status;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
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

	public int getSlide_id() {
		return slide_id;
	}

	public void setSlide_id(int slide_id) {
		this.slide_id = slide_id;
	}

	public String getSlide_caption() {
		return slide_caption;
	}

	public void setSlide_caption(String slide_caption) {
		this.slide_caption = slide_caption;
	}

	public String getSlide_img() {
		return slide_img;
	}

	public void setSlide_img(String slide_img) {
		this.slide_img = slide_img;
	}

	public int getSlide_status() {
		return slide_status;
	}

	public void setSlide_status(int slide_status) {
		this.slide_status = slide_status;
	}
}
