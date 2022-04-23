package com.khoaluantotnghiep.entity;

import java.util.Date;

public class MenuEntity {
	private int menu_id;
	private String menu_name;
	private String menu_slug;
	private int menu_status;
	private int orders;
	private int parent_id;
	private int created_by;
	private int updated_by;
	private Date created_at;
	private Date updated_at;

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

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_slug() {
		return menu_slug;
	}

	public void setMenu_slug(String menu_slug) {
		this.menu_slug = menu_slug;
	}

	public int getMenu_status() {
		return menu_status;
	}

	public void setMenu_status(int menu_status) {
		this.menu_status = menu_status;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}
}
