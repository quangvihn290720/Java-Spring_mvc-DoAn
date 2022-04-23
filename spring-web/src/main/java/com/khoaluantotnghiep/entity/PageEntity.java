package com.khoaluantotnghiep.entity;

import java.util.Date;

public class PageEntity {
	private int page_id;
	private String page_title;
	private String page_slug;
	private String page_detail;
	private String page_img;
	private String page_metakey;
	private String page_metadesc;
	private int page_status;
	private int page_topic;
	private int created_by;
	private int updated_by;
	private Date created_at;
	private Date updated_at;

	public PageEntity() {
		super();
	}

	public int getPage_topic() {
		return page_topic;
	}

	public void setPage_topic(int page_topic) {
		this.page_topic = page_topic;
	}

	public PageEntity(int page_id) {
		super();
		this.page_id = page_id;
	}

	public int getPage_id() {
		return page_id;
	}

	public void setPage_id(int page_id) {
		this.page_id = page_id;
	}

	public String getPage_title() {
		return page_title;
	}

	public void setPage_title(String page_title) {
		this.page_title = page_title;
	}

	public String getPage_slug() {
		return page_slug;
	}

	public void setPage_slug(String page_slug) {
		this.page_slug = page_slug;
	}

	public String getPage_detail() {
		return page_detail;
	}

	public void setPage_detail(String page_detail) {
		this.page_detail = page_detail;
	}

	public String getPage_img() {
		return page_img;
	}

	public void setPage_img(String page_img) {
		this.page_img = page_img;
	}

	public String getPage_metakey() {
		return page_metakey;
	}

	public void setPage_metakey(String page_metakey) {
		this.page_metakey = page_metakey;
	}

	public String getPage_metadesc() {
		return page_metadesc;
	}

	public void setPage_metadesc(String page_metadesc) {
		this.page_metadesc = page_metadesc;
	}

	public int getPage_status() {
		return page_status;
	}

	public void setPage_status(int page_status) {
		this.page_status = page_status;
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
