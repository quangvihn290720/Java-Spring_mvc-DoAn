package com.khoaluantotnghiep.entity;

import java.util.Date;

public class OptionsEntity {
	private int options_id;
	private int optiongroup_id;
	private String optionname;
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

	public int getOptions_id() {
		return options_id;
	}

	public void setOptions_id(int options_id) {
		this.options_id = options_id;
	}

	public int getOptiongroup_id() {
		return optiongroup_id;
	}

	public void setOptiongroup_id(int optiongroup_id) {
		this.optiongroup_id = optiongroup_id;
	}

	public String getOptionname() {
		return optionname;
	}

	public void setOptionname(String optionname) {
		this.optionname = optionname;
	}

}
