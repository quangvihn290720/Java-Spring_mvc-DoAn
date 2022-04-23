package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.SocialNetWorkEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.SocialNetWorkMapper;

@Repository
@Transactional
public class SocialNetWorkDAO extends BaseDAO {

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM socialnetwork where status != 0 ORDER BY status ASC ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM socialnetwork where status = 0 ");
		return sql;
	}

	private String SqlSocialNetWorkPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<SocialNetWorkEntity> GetDataSocialNetWorkPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<SocialNetWorkEntity> listSocialNetWork = jdbcTemplate.query(sqlGetData.toString(),
				new SocialNetWorkMapper());
		List<SocialNetWorkEntity> listSocialNetWorks = new ArrayList<SocialNetWorkEntity>();
		if (listSocialNetWork.size() > 0) {
			String sql = SqlSocialNetWorkPaginate(start, totalPage);
			listSocialNetWorks = jdbcTemplate.query(sql, new SocialNetWorkMapper());
		}
		return listSocialNetWorks;
	}

	private String SqlSocialNetWorkTrashPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<SocialNetWorkEntity> GetDataSocialNetWorkTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<SocialNetWorkEntity> listSocialNetWorkTrash = jdbcTemplate.query(sqlGetData.toString(),
				new SocialNetWorkMapper());
		List<SocialNetWorkEntity> listSocialNetWorkTrashs = new ArrayList<SocialNetWorkEntity>();
		if (listSocialNetWorkTrash.size() > 0) {
			String sql = SqlSocialNetWorkTrashPaginate(start, totalPage);
			listSocialNetWorkTrashs = jdbcTemplate.query(sql, new SocialNetWorkMapper());
		}
		return listSocialNetWorkTrashs;
	}

	public List<SocialNetWorkEntity> findAllSocialNetWork() {
		String sql = "SELECT * FROM socialnetwork where status != 0 ";
		List<SocialNetWorkEntity> list = jdbcTemplate.query(sql, new SocialNetWorkMapper());
		return list;
	}

	public List<SocialNetWorkEntity> findAllSocialNetWorkShow() {
		String sql = "SELECT * FROM socialnetwork where status = 1";
		List<SocialNetWorkEntity> list = jdbcTemplate.query(sql, new SocialNetWorkMapper());
		return list;
	}

	public SocialNetWorkEntity findSocialNetWorkById(SocialNetWorkEntity SocialNetWork) {
		String sql = "SELECT * FROM socialnetwork where id = " + SocialNetWork.getId();
		List<SocialNetWorkEntity> result = jdbcTemplate.query(sql, new SocialNetWorkMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public List<SocialNetWorkEntity> findTrashSocialNetWork() {
		String sql = "SELECT * FROM socialnetwork where status = 0";
		List<SocialNetWorkEntity> list = jdbcTemplate.query(sql, new SocialNetWorkMapper());
		return list;
	}

	public void deltrashSocialNetWork(int id,UserEntity loginInfo) {
		String sql = "UPDATE socialnetwork SET status = 0,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void retrashSocialNetWork(int id,UserEntity loginInfo) {
		String sql = "UPDATE socialnetwork SET status = 2,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void addSocialNetWork(SocialNetWorkEntity socialnetwork) {
		String sql = "INSERT INTO socialnetwork (`name`, `img`, `address`,`icon`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) "
				+ " VALUES (?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, socialnetwork.getName(), socialnetwork.getImg(), socialnetwork.getAddress(),
				socialnetwork.getIcon(), socialnetwork.getStatus(), socialnetwork.getCreated_at(),
				socialnetwork.getCreated_by(), socialnetwork.getUpdated_at(), socialnetwork.getUpdated_by());
	}

	public void updateSocialNetWork(SocialNetWorkEntity socialnetwork) {
		String sql = "";
		if (socialnetwork.getImg() != null && !socialnetwork.getImg().isEmpty()) {
			sql = "UPDATE SocialNetWork SET name = ?, img = ?,address= ?,icon = ?, status = ?, updated_by = ?,  updated_at = ?  WHERE id = ?";
			jdbcTemplate.update(sql, socialnetwork.getName(), socialnetwork.getImg(), socialnetwork.getAddress(),
					socialnetwork.getIcon(), socialnetwork.getStatus(), socialnetwork.getUpdated_by(),
					socialnetwork.getUpdated_at(), socialnetwork.getId());
		} else {
			sql = "UPDATE SocialNetWork SET name = ?,address= ?,icon = ?,status = ?, updated_by = ?,  updated_at = ?  WHERE id = ?";
			jdbcTemplate.update(sql, socialnetwork.getName(), socialnetwork.getAddress(), socialnetwork.getIcon(),
					socialnetwork.getStatus(), socialnetwork.getUpdated_by(), socialnetwork.getUpdated_at(),
					socialnetwork.getId());
		}
	}

	public void deleteSocialNetWork(int id) {
		String sql = "DELETE FROM socialnetwork WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void onOffSocialNetWork(int id,UserEntity loginInfo) {
		String sql = "UPDATE socialnetwork SET status = case WHEN status =1 then 2 when status=2 then 1 end,updated_by ="
				+ loginInfo.getUser_id() + ", updated_at = NOW() where id = " + id;
		jdbcTemplate.update(sql);
	}

	public boolean isNameExists(String name) {
		int count = 0;
		String sql = "SELECT count(*) FROM socialnetwork WHERE name = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);
		return count > 0;
	}

	public boolean isNameExists(String name, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM socialnetwork WHERE name = ? and id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name, id }, Integer.class);
		return count > 0;
	}
}
