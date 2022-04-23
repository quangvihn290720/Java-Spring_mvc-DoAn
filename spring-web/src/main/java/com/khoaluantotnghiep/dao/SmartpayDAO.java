package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.SmartpayEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.SmartpayMapper;

@Repository
@Transactional
public class SmartpayDAO extends BaseDAO {

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM smartpay where status != 0 ORDER BY status ASC ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM smartpay where status = 0 ");
		return sql;
	}

	private String SqlSmartpayPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<SmartpayEntity> GetDataSmartpayPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<SmartpayEntity> listSmartpay = jdbcTemplate.query(sqlGetData.toString(), new SmartpayMapper());
		List<SmartpayEntity> listSmartpays = new ArrayList<SmartpayEntity>();
		if (listSmartpay.size() > 0) {
			String sql = SqlSmartpayPaginate(start, totalPage);
			listSmartpays = jdbcTemplate.query(sql, new SmartpayMapper());
		}
		return listSmartpays;
	}

	private String SqlSmartpayTrashPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<SmartpayEntity> GetDataSmartpayTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<SmartpayEntity> listSmartpayTrash = jdbcTemplate.query(sqlGetData.toString(), new SmartpayMapper());
		List<SmartpayEntity> listSmartpayTrashs = new ArrayList<SmartpayEntity>();
		if (listSmartpayTrash.size() > 0) {
			String sql = SqlSmartpayTrashPaginate(start, totalPage);
			listSmartpayTrashs = jdbcTemplate.query(sql, new SmartpayMapper());
		}
		return listSmartpayTrashs;
	}

	public List<SmartpayEntity> findAllSmartpay() {
		String sql = "SELECT * FROM smartpay where status != 0 ";
		List<SmartpayEntity> list = jdbcTemplate.query(sql, new SmartpayMapper());
		return list;
	}

	public List<SmartpayEntity> findAllSmartpayShow() {
		String sql = "SELECT * FROM smartpay where status = 1";
		List<SmartpayEntity> list = jdbcTemplate.query(sql, new SmartpayMapper());
		return list;
	}

	public SmartpayEntity findSmartpayById(SmartpayEntity Smartpay) {
		String sql = "SELECT * FROM smartpay where id = " + Smartpay.getId();
		List<SmartpayEntity> result = jdbcTemplate.query(sql, new SmartpayMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public List<SmartpayEntity> findTrashSmartpay() {
		String sql = "SELECT * FROM smartpay where status = 0";
		List<SmartpayEntity> list = jdbcTemplate.query(sql, new SmartpayMapper());
		return list;
	}

	public void deltrashSmartpay(int id,UserEntity loginInfo) {
		String sql = "UPDATE smartpay SET status = 0,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void retrashSmartpay(int id,UserEntity loginInfo) {
		String sql = "UPDATE smartpay SET status = 2,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void addSmartpay(SmartpayEntity smartpay) {
		String sql = "INSERT INTO smartpay (`name`, `img`, `metakey`, `metadesc`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) "
				+ " VALUES (?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, smartpay.getName(), smartpay.getImg(), smartpay.getMetakey(), smartpay.getMetadesc(),
				smartpay.getStatus(), smartpay.getCreated_at(), smartpay.getCreated_by(), smartpay.getUpdated_at(),
				smartpay.getUpdated_by());
	}

	public void updateSmartpay(SmartpayEntity smartpay) {
		String sql = "";
		if (smartpay.getImg() != null && !smartpay.getImg().isEmpty()) {
			sql = "UPDATE Smartpay SET name = ?, img = ?,metakey= ?,metadesc = ?, status = ?, updated_by = ?,  updated_at = ?  WHERE id = ?";
			jdbcTemplate.update(sql, smartpay.getName(), smartpay.getImg(), smartpay.getMetakey(),
					smartpay.getMetadesc(), smartpay.getStatus(), smartpay.getUpdated_by(), smartpay.getUpdated_at(),
					smartpay.getId());
		} else {
			sql = "UPDATE Smartpay SET name = ?,metakey= ?,metadesc = ?, status = ?, updated_by = ?,  updated_at = ?  WHERE id = ?";
			jdbcTemplate.update(sql, smartpay.getName(), smartpay.getMetakey(), smartpay.getMetadesc(),
					smartpay.getStatus(), smartpay.getUpdated_by(), smartpay.getUpdated_at(), smartpay.getId());
		}
	}

	public void deleteSmartpay(int id) {
		String sql = "DELETE FROM smartpay WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void onOffSmartpay(int id,UserEntity loginInfo) {
		String sql = "UPDATE smartpay SET status = case WHEN status =1 then 2 when status=2 then 1 end,updated_by ="
				+ loginInfo.getUser_id() + ", updated_at = NOW() where id = " + id;
		jdbcTemplate.update(sql);
	}

	public boolean isNameExists(String name) {
		int count = 0;
		String sql = "SELECT count(*) FROM smartpay WHERE name = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);

		return count > 0;
	}

	public boolean isNameExists(String name, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM smartpay WHERE name = ? and id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name, id }, Integer.class);

		return count > 0;
	}
}
