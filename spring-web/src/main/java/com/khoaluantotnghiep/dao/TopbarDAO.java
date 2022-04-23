package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.TopbarEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.TopbarMapper;

@Repository
@Transactional
public class TopbarDAO extends BaseDAO {
	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM topbar where status != 0  order by status asc ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM topbar where status = 0 ");
		return sql;
	}

	private String SqlTopbarPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlTopbarTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<TopbarEntity> GetDataTopbarPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<TopbarEntity> listTopbar = jdbcTemplate.query(sqlGetData.toString(), new TopbarMapper());
		List<TopbarEntity> listTopbars = new ArrayList<TopbarEntity>();
		if (listTopbar.size() > 0) {
			String sql = SqlTopbarPaginate(start, totalPage);
			listTopbars = jdbcTemplate.query(sql, new TopbarMapper());
		}
		return listTopbars;
	}

	public List<TopbarEntity> GetDataTopbarTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<TopbarEntity> listTopbarTrash = jdbcTemplate.query(sqlGetData.toString(), new TopbarMapper());
		List<TopbarEntity> listTopbarTrashs = new ArrayList<TopbarEntity>();
		if (listTopbarTrash.size() > 0) {
			String sql = SqlTopbarTrashsPaginate(start, totalPage);
			listTopbarTrashs = jdbcTemplate.query(sql, new TopbarMapper());
		}
		return listTopbarTrashs;
	}

	public List<TopbarEntity> findAllTopbar() {
		String sql = "SELECT * FROM topbar where status != 0 order by status asc ";
		List<TopbarEntity> list = jdbcTemplate.query(sql, new TopbarMapper());
		return list;
	}

	public List<TopbarEntity> findAllTopbarShow() {
		String sql = "SELECT * FROM topbar where status = 1";
		List<TopbarEntity> list = jdbcTemplate.query(sql, new TopbarMapper());
		return list;
	}

	public TopbarEntity findTopbarrById(TopbarEntity topbar) {
		String sql = "SELECT * FROM topbar where id = " + topbar.getId();
		List<TopbarEntity> result = jdbcTemplate.query(sql, new TopbarMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public void addTopbar(TopbarEntity topbar) {
		String sql = "INSERT INTO topbar (`name`, `img`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES(?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, topbar.getName(), topbar.getImg(), topbar.getStatus(), topbar.getCreated_at(),
				topbar.getCreated_by(), topbar.getUpdated_at(), topbar.getUpdated_by());
	}

	public void deleteTopbar(int id) {
		String sql = "DELETE FROM topbar WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void updateTopbar(TopbarEntity topbar) {
		String sql = "";
		if (topbar.getImg() != null && !topbar.getImg().isEmpty()) {
			sql = "UPDATE topbar SET name = ?, img = ?, status = ?, updated_by = ?, updated_at = ? WHERE id = ?";
			jdbcTemplate.update(sql, topbar.getName(), topbar.getImg(), topbar.getStatus(), topbar.getUpdated_by(),
					topbar.getUpdated_at(), topbar.getId());
		} else {
			sql = "UPDATE topbar SET name = ?,status = ?, updated_by = ?, updated_at = ? WHERE id = ?";
			jdbcTemplate.update(sql, topbar.getName(), topbar.getStatus(), topbar.getUpdated_by(),
					topbar.getUpdated_at(), topbar.getId());
		}
	}

	public List<TopbarEntity> findTrashTopbar() {
		String sql = "SELECT * FROM topbar where status = 0";
		List<TopbarEntity> list = jdbcTemplate.query(sql, new TopbarMapper());
		return list;
	}

	public void deltrash(int id,UserEntity loginInfo) {
		String sql = "UPDATE topbar SET status = 0,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void retrash(int id,UserEntity loginInfo) {
		String sql = "UPDATE topbar SET status = 2,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void onOffTopbar(int id,UserEntity loginInfo) {
		String sql = "UPDATE topbar SET status = case WHEN status =1 then 2 when status=2 then 1 end,updated_by ="
				+ loginInfo.getUser_id() + ", updated_at = NOW() WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public boolean isNameExists(String name) {
		int count = 0;
		String sql = "SELECT count(*) FROM topbar WHERE name = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);

		return count > 0;
	}

	public boolean isNameExists(String name, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM topbar WHERE name = ? and id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name, id }, Integer.class);

		return count > 0;
	}
}
