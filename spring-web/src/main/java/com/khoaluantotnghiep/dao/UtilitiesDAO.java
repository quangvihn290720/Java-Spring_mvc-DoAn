package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.entity.UtilitiesEntity;
import com.khoaluantotnghiep.mapper.UtilitiesMapper;

@Transactional
@Repository
public class UtilitiesDAO extends BaseDAO {

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM utilities where status != 0 ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM utilities where status = 0 ");
		return sql;
	}

	private String SqlTopicsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlTopicTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<UtilitiesEntity> GetDataUtilitiesPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<UtilitiesEntity> listUtilities = jdbcTemplate.query(sqlGetData.toString(), new UtilitiesMapper());
		List<UtilitiesEntity> listUtilitiess = new ArrayList<UtilitiesEntity>();
		if (listUtilities.size() > 0) {
			String sql = SqlTopicsPaginate(start, totalPage);
			listUtilitiess = jdbcTemplate.query(sql, new UtilitiesMapper());
		}
		return listUtilitiess;
	}

	public List<UtilitiesEntity> GetDataUtilitiesTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<UtilitiesEntity> listUtilitiesTrash = jdbcTemplate.query(sqlGetData.toString(), new UtilitiesMapper());
		List<UtilitiesEntity> listUtilitiesTrashs = new ArrayList<UtilitiesEntity>();
		if (listUtilitiesTrash.size() > 0) {
			String sql = SqlTopicTrashsPaginate(start, totalPage);
			listUtilitiesTrashs = jdbcTemplate.query(sql, new UtilitiesMapper());
		}
		return listUtilitiesTrashs;
	}

	public List<UtilitiesEntity> findAllUtilitiesShow() {
		List<UtilitiesEntity> list = new ArrayList<UtilitiesEntity>();
		String sql = "SELECT * FROM utilities where status = 1 ";
		list = jdbcTemplate.query(sql, new UtilitiesMapper());
		return list;
	}

	public List<UtilitiesEntity> findAllUtilities() {
		List<UtilitiesEntity> list = new ArrayList<UtilitiesEntity>();
		String sql = "SELECT * FROM utilities where status != 0 ";
		list = jdbcTemplate.query(sql, new UtilitiesMapper());
		return list;
	}

	public List<UtilitiesEntity> findAllTrashUtilities() {
		List<UtilitiesEntity> list = new ArrayList<UtilitiesEntity>();
		String sql = "SELECT * FROM utilities where status = 0 ";
		list = jdbcTemplate.query(sql, new UtilitiesMapper());
		return list;
	}

	public UtilitiesEntity findUtilById(UtilitiesEntity util) {
		String sql = "SELECT * FROM utilities WHERE utilities_id = " + util.getUtilities_id();
		List<UtilitiesEntity> result = jdbcTemplate.query(sql, new UtilitiesMapper());
		return result.isEmpty() ? null : result.get(0);

	}

	public void addUtilities(UtilitiesEntity util) {
		String sql = "INSERT INTO utilities (`product_id`, `utilitiesname`, `metadesc`, `metakey`, `status`, `created_by`, `updated_by`, `created_at`, `updated_at`) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, util.getProduct_id(), util.getUtilitiesname(), util.getMetadesc(), util.getMetakey(),
				util.getStatus(), util.getCreated_by(), util.getUpdated_by(), util.getCreated_at(),
				util.getUpdated_at());
	}

	public void updateUtilities(UtilitiesEntity util) {
		String sql = "UPDATE utilities set utilitiesname= ?, metadesc = ?, metakey = ?, status = ?, updated_by = ?, updated_at = ? "
				+ "WHERE utilities_id = ?";
		jdbcTemplate.update(sql, util.getUtilitiesname(), util.getMetadesc(), util.getMetakey(), util.getStatus(),
				util.getUpdated_by(), util.getUpdated_at(), util.getUtilities_id());
	}

	public void deleteUtilities(int utilities_id) {
		String sql = "DELETE FROM utilities WHERE utilities_id = " + utilities_id;
		jdbcTemplate.update(sql);
	}

	public void deltrash(int utilities_id, UserEntity loginInfo) {
		String sql = "UPDATE utilities SET status = 0,updated_by = " + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE utilities_id = " + utilities_id;
		jdbcTemplate.update(sql);
	}

	public void retrash(int utilities_id, UserEntity loginInfo) {
		String sql = "UPDATE utilities SET status = 2,updated_by = " + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE utilities_id = " + utilities_id;
		jdbcTemplate.update(sql);
	}

	public void onOffTopic(int utilities_id, UserEntity loginInfo) {
		String sql = "UPDATE utilities SET status = case WHEN status =1 then 2 when status=2 then 1 end,updated_by = "
				+ loginInfo.getUser_id() + ", updated_at = NOW() WHERE utilities_id = " + utilities_id;
		jdbcTemplate.update(sql);
	}

	public UtilitiesEntity findUtilityByName(UtilitiesEntity utility) {
		String sql = "SELECT * FROM utilities WHERE utilitiesname = '" + utility.getUtilitiesname()
				+ "' AND product_id = " + utility.getProduct_id();
		List<UtilitiesEntity> result = jdbcTemplate.query(sql, new UtilitiesMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public UtilitiesEntity findOtherUtilityByName(UtilitiesEntity utility) {
		String sql = "SELECT * FROM utilities options WHERE utilitiesname = '" + utility.getUtilitiesname()
				+ "' AND product_id = " + utility.getProduct_id() + " and utilities_id <>" + utility.getUtilities_id();
		List<UtilitiesEntity> result = jdbcTemplate.query(sql, new UtilitiesMapper());
		return result.isEmpty() ? null : result.get(0);
	}
}
