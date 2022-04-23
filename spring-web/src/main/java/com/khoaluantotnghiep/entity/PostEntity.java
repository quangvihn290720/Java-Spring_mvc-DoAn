package com.khoaluantotnghiep.entity;

import java.util.Date;

public class PostEntity {
	private int post_id;
	private int post_topicid;
	private String post_title;
	private String post_slug;
	private String post_detail;
	private String post_img;
	private int post_status;
	private String post_metakey;
	private String post_metadesc;
	private int created_by;
	private int updated_by;
	private Date created_at;
	private Date updated_at;

	public PostEntity() {
		super();
	}

	public PostEntity(int post_topicid, String post_title, String post_slug, String post_detail, String post_img,
			int post_status, String post_metakey, String post_metadesc, int created_by, int updated_by, Date created_at,
			Date updated_at) {
		super();
		this.post_topicid = post_topicid;
		this.post_title = post_title;
		this.post_slug = post_slug;
		this.post_detail = post_detail;
		this.post_img = post_img;
		this.post_status = post_status;
		this.post_metakey = post_metakey;
		this.post_metadesc = post_metadesc;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public PostEntity(int post_id, int post_topicid, String post_title, String post_slug, String post_detail,
			String post_img, int post_status, String post_metakey, String post_metadesc, int created_by, int updated_by,
			Date created_at, Date updated_at) {
		super();
		this.post_id = post_id;
		this.post_topicid = post_topicid;
		this.post_title = post_title;
		this.post_slug = post_slug;
		this.post_detail = post_detail;
		this.post_img = post_img;
		this.post_status = post_status;
		this.post_metakey = post_metakey;
		this.post_metadesc = post_metadesc;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getPost_topicid() {
		return post_topicid;
	}

	public void setPost_topicid(int post_topicid) {
		this.post_topicid = post_topicid;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_slug() {
		return post_slug;
	}

	public void setPost_slug(String post_slug) {
		this.post_slug = post_slug;
	}

	public String getPost_detail() {
		return post_detail;
	}

	public void setPost_detail(String post_detail) {
		this.post_detail = post_detail;
	}

	public String getPost_img() {
		return post_img;
	}

	public void setPost_img(String post_img) {
		this.post_img = post_img;
	}

	public int getPost_status() {
		return post_status;
	}

	public void setPost_status(int post_status) {
		this.post_status = post_status;
	}

	public String getPost_metakey() {
		return post_metakey;
	}

	public void setPost_metakey(String post_metakey) {
		this.post_metakey = post_metakey;
	}

	public String getPost_metadesc() {
		return post_metadesc;
	}

	public void setPost_metadesc(String post_metadesc) {
		this.post_metadesc = post_metadesc;
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
