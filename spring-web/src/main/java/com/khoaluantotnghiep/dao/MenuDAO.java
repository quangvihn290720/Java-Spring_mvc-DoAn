package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.MenuEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.MenuMapper;

@Transactional
@Repository
public class MenuDAO extends BaseDAO {

	public List<MenuEntity> GetDataMenuShow() {
		List<MenuEntity> list = new ArrayList<MenuEntity>();
		String sql = "SELECT * FROM menu where menu_status = 1 and parent_id = 0 ORDER BY orders asc";
		list = jdbcTemplate.query(sql, new MenuMapper());
		return list;
	}

	public List<MenuEntity> findAllMenu() {
		String sql = "SELECT * FROM menu where menu_status != 0 ";
		List<MenuEntity> list = jdbcTemplate.query(sql, new MenuMapper());
		return list;
	}

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM menu where menu_status != 0 ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM menu where menu_status = 0 ");
		return sql;
	}

	private String SqlMenuPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlMenuTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<MenuEntity> GetDataMenuPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<MenuEntity> listMenu = jdbcTemplate.query(sqlGetData.toString(), new MenuMapper());
		List<MenuEntity> listMenus = new ArrayList<MenuEntity>();
		if (listMenu.size() > 0) {
			String sql = SqlMenuPaginate(start, totalPage);
			listMenus = jdbcTemplate.query(sql, new MenuMapper());
		}
		return listMenus;
	}

	public List<MenuEntity> GetDataMenuTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<MenuEntity> listMenuTrash = jdbcTemplate.query(sqlGetData.toString(), new MenuMapper());
		List<MenuEntity> listMenuTrashs = new ArrayList<MenuEntity>();
		if (listMenuTrash.size() > 0) {
			String sql = SqlMenuTrashsPaginate(start, totalPage);
			listMenuTrashs = jdbcTemplate.query(sql, new MenuMapper());
		}
		return listMenuTrashs;
	}

	public void addMenu(MenuEntity menu) {
		String sql = "INSERT INTO menu (menu_name,menu_slug,menu_status,parent_id,orders"
				+ ",created_by,updated_by,created_at,updated_at) " + "VALUES (?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, menu.getMenu_name(), menu.getMenu_slug(), menu.getMenu_status(), menu.getParent_id(),
				menu.getOrders(), menu.getCreated_by(), menu.getUpdated_by(), menu.getCreated_at(),
				menu.getUpdated_at());
	}

	public boolean isNameExists(String name) {
		int count = 0;
		String sql = "SELECT count(*) FROM menu WHERE menu_name = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);

		return count > 0;
	}

	public boolean isNameExists(String name, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM menu WHERE menu_name = ? and menu_id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name, id }, Integer.class);

		return count > 0;
	}

	public boolean isSlugExists(String slug) {
		int count = 0;
		String sql = "SELECT count(*) FROM menu WHERE menu_slug = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { slug }, Integer.class);

		return count > 0;
	}

	public boolean isSlugExists(String slug, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM menu WHERE menu_slug = ? and menu_id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { slug, id }, Integer.class);

		return count > 0;
	}

	public MenuEntity findMenuById(int menu_id) {
		String sql = "SELECT * FROM menu where menu_id = " + menu_id;
		List<MenuEntity> result = jdbcTemplate.query(sql, new MenuMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public void updateMenu(MenuEntity menu) {
		String sql = "UPDATE menu SET `parent_id` = ?, `menu_name` = ?, `menu_slug` = ?, `menu_status` = ?, orders = ?,"
				+ "`updated_by` = ?, `updated_at` = ? WHERE menu_id = ? ";
		jdbcTemplate.update(sql, menu.getParent_id(), menu.getMenu_name(), menu.getMenu_slug(), menu.getMenu_status(),
				menu.getOrders() + 1, menu.getUpdated_by(), menu.getUpdated_at(), menu.getMenu_id());

	}

	public void onOffTPost(int post_id,UserEntity loginInfo) {
		String sql = "UPDATE menu SET menu_status = case when  menu_status =1 then 2 when  menu_status =2 then 1 end,updated_by ="
				+ loginInfo.getUser_id() + ", updated_at = NOW() where  menu_id =" + post_id;
		jdbcTemplate.update(sql);
	}

	public List<MenuEntity> findTrashMenu() {
		String sql = "SELECT * FROM menu where menu_status = 0";
		List<MenuEntity> list = jdbcTemplate.query(sql, new MenuMapper());
		return list;
	}

	public void delTrash(int menu_id,UserEntity loginInfo) {
		String sql = "UPDATE menu SET menu_status = 0,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW()  where  menu_id =" + menu_id;
		jdbcTemplate.update(sql);
	}

	public void reTrash(int menu_id,UserEntity loginInfo) {
		String sql = "UPDATE menu SET menu_status = 2,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW()  where  menu_id =" + menu_id;
		jdbcTemplate.update(sql);
	}

	public void deleteMenu(int menu_id) {
		String sql = "DELETE FROM menu WHERE menu_id = " + menu_id;
		jdbcTemplate.update(sql);
	}

	public List<MenuEntity> listMenuByParent(int parent_id) {
		String sql = "SELECT * FROM menu where parent_id = " + parent_id + " and menu_status = 1";
		List<MenuEntity> result = jdbcTemplate.query(sql, new MenuMapper());
		return result;
	}

}
