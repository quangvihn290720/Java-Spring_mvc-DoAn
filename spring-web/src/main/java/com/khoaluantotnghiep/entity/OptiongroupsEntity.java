package com.khoaluantotnghiep.entity;

import java.util.Date;

public class OptiongroupsEntity {
	private int optiongroups_id;
	private String optiongroupname;
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

	public int getOptiongroups_id() {
		return optiongroups_id;
	}

	public void setOptiongroups_id(int optiongroups_id) {
		this.optiongroups_id = optiongroups_id;
	}

	public String getOptiongroupname() {
		return optiongroupname;
	}

	public void setOptiongroupname(String optiongroupname) {
		this.optiongroupname = optiongroupname;
	}

}
