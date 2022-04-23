package com.khoaluantotnghiep.entity;

import java.util.Date;

public class TopicEntity {
	private int topic_id;
	private String topic_name;
	private String topic_slug;
	private String topic_metakey;
	private String topic_metadesc;
	private int showfooter;
	private int topic_status;
	private int created_by;
	private int updated_by;
	private Date created_at;
	private Date updated_at;

	public TopicEntity() {
		super();
	}

	public int getShowfooter() {
		return showfooter;
	}

	public void setShowfooter(int showfooter) {
		this.showfooter = showfooter;
	}

	public int getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}

	public String getTopic_name() {
		return topic_name;
	}

	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}

	public String getTopic_slug() {
		return topic_slug;
	}

	public void setTopic_slug(String topic_slug) {
		this.topic_slug = topic_slug;
	}

	public String getTopic_metakey() {
		return topic_metakey;
	}

	public void setTopic_metakey(String topic_metakey) {
		this.topic_metakey = topic_metakey;
	}

	public String getTopic_metadesc() {
		return topic_metadesc;
	}

	public void setTopic_metadesc(String topic_metadesc) {
		this.topic_metadesc = topic_metadesc;
	}

	public int getTopic_status() {
		return topic_status;
	}

	public void setTopic_status(int topic_status) {
		this.topic_status = topic_status;
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
