package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.MenuEntity;

public class MenuMapper implements RowMapper<MenuEntity> {

	@Override
	public MenuEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		MenuEntity menu = new MenuEntity();
		menu.setMenu_id(rs.getInt("menu_id"));
		menu.setMenu_name(rs.getString("menu_name"));
		menu.setMenu_slug(rs.getString("menu_slug"));
		menu.setMenu_status(rs.getInt("menu_status"));
		menu.setParent_id(rs.getInt("parent_id"));
		menu.setOrders(rs.getInt("orders"));
		menu.setCreated_by(rs.getInt("created_by"));
		menu.setUpdated_by(rs.getInt("updated_by"));
		menu.setCreated_at(rs.getDate("created_at"));
		menu.setUpdated_at(rs.getDate("updated_at"));
		return menu;
	}

}
