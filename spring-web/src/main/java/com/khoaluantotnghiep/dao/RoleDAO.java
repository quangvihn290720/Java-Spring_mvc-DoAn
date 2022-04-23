package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.RoleEntity;
import com.khoaluantotnghiep.mapper.RoleMapper;

@Repository
@Transactional
public class RoleDAO extends BaseDAO {
	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM role where status != 0 ORDER BY status ASC ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM role where status = 0 ");
		return sql;
	}

	private String SqlRolePaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlRoleTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<RoleEntity> GetDataRolePaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<RoleEntity> listRole = jdbcTemplate.query(sqlGetData.toString(), new RoleMapper());
		List<RoleEntity> listRoles = new ArrayList<RoleEntity>();
		if (listRole.size() > 0) {
			String sql = SqlRolePaginate(start, totalPage);
			listRoles = jdbcTemplate.query(sql, new RoleMapper());
		}
		return listRoles;
	}

	public List<RoleEntity> GetDataRoleTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<RoleEntity> listRoleTrash = jdbcTemplate.query(sqlGetData.toString(), new RoleMapper());
		List<RoleEntity> listRoleTrashs = new ArrayList<RoleEntity>();
		if (listRoleTrash.size() > 0) {
			String sql = SqlRoleTrashsPaginate(start, totalPage);
			listRoleTrashs = jdbcTemplate.query(sql, new RoleMapper());
		}
		return listRoleTrashs;
	}

	public List<RoleEntity> findAllRole() {
		String sql = "SELECT * FROM role where status != 0";
		List<RoleEntity> list = jdbcTemplate.query(sql, new RoleMapper());
		return list;
	}

	public List<RoleEntity> findAllRoleShow() {
		String sql = "SELECT * FROM role where status = 1 ";
		List<RoleEntity> list = jdbcTemplate.query(sql, new RoleMapper());
		return list;
	}

	public List<RoleEntity> findTrashRole() {
		String sql = SqlTrash().toString();
		List<RoleEntity> list = jdbcTemplate.query(sql, new RoleMapper());
		return list;
	}

	public RoleEntity findRoleById(int id) {
		String sql = "SELECT * FROM role where id = " + id;
		List<RoleEntity> result = jdbcTemplate.query(sql, new RoleMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public void onOff(int id) {
		String sql = "UPDATE role SET status = case when  status =1 then 2 when  status =2 then 1 end where  id =" + id;
		jdbcTemplate.update(sql);
	}

	public void delTrash(int id) {
		String sql = "UPDATE role SET status = 0  where id =" + id;
		jdbcTemplate.update(sql);
	}

	public void reTrash(int id) {
		String sql = "UPDATE role SET status = 2  where  id =" + id;
		jdbcTemplate.update(sql);
	}

	public void update(RoleEntity role) {
		String sql = "UPDATE `role` SET `name` = ? , `code`=?, `status`=?,`updated_at`=?, `updated_by`= ? where id = ? ";
		jdbcTemplate.update(sql, role.getName(), role.getCode(), role.getStatus(), role.getUpdated_at(),
				role.getUpdated_by(), role.getId());
	}

	public void delete(int id) {
		String sql = "DELETE FROM role WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void add(RoleEntity role) {
		String sql = "INSERT INTO `role`(`name`, `code`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) "
				+ "VALUES (?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, role.getName(), role.getCode(), role.getStatus(), role.getCreated_at(),
				role.getCreated_by(), role.getUpdated_at(), role.getUpdated_by());
	}

	public boolean isNameExists(String name) {
		int count = 0;
		String sql = "SELECT count(*) FROM role WHERE name = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);

		return count > 0;
	}

	public boolean isNameExists(String name, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM role WHERE name = ? and id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name, id }, Integer.class);

		return count > 0;
	}

	public boolean isCodeExists(String code) {
		int count = 0;
		String sql = "SELECT count(*) FROM role WHERE code = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { code }, Integer.class);
		return count > 0;
	}

	public boolean isCodeExists(String code, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM role WHERE code = ? and id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { code, id }, Integer.class);
		return count > 0;
	}
}
