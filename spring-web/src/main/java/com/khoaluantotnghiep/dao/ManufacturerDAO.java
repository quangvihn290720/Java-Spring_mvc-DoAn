package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.ManufacturerEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.ManufacturerMapper;

@Repository
@Transactional
public class ManufacturerDAO extends BaseDAO {
	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM manufacturer where status != 0 ORDER BY status ASC ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM manufacturer where status = 0 ");
		return sql;
	}

	public List<ManufacturerEntity> findAllManufacturer() {
		String sql = "SELECT * FROM manufacturer where status != 0 ORDER BY status ASC ";
		List<ManufacturerEntity> list = jdbcTemplate.query(sql, new ManufacturerMapper());
		return list;
	}

	public List<ManufacturerEntity> findAllManufacturerShow() {
		String sql = "SELECT * FROM manufacturer where status = 1";
		List<ManufacturerEntity> list = jdbcTemplate.query(sql, new ManufacturerMapper());
		return list;
	}

	public void addManufacturer(ManufacturerEntity manufacturer) {
		String sql = "INSERT INTO manufacturer (`manufacturer_name`, `manufacturer_slug`, `manufacturer_img`, `status`,"
				+ " `created_at`, `updated_at`, `created_by`, `updated_by`) VALUES (?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, manufacturer.getManufacturer_name(), manufacturer.getManufacturer_slug(),
				manufacturer.getManufacturer_img(), manufacturer.getStatus(), manufacturer.getCreated_at(),
				manufacturer.getUpdated_at(), manufacturer.getCreated_by(), manufacturer.getUpdated_by());
	}

	public void updateManufacturer(ManufacturerEntity manufacturer) {
		String sql = "";
		if (manufacturer.getManufacturer_img() != null && !manufacturer.getManufacturer_img().isEmpty()) {
			sql = "UPDATE manufacturer SET manufacturer_name = ?, manufacturer_slug = ?,manufacturer_img = ?,status = ?, updated_by = ?, updated_at = ? WHERE manufacturer_id = ?";
			jdbcTemplate.update(sql, manufacturer.getManufacturer_name(), manufacturer.getManufacturer_slug(),
					manufacturer.getManufacturer_img(), manufacturer.getStatus(), manufacturer.getUpdated_by(),
					manufacturer.getUpdated_at(), manufacturer.getManufacturer_id());
		} else {
			sql = "UPDATE manufacturer SET manufacturer_name = ?, manufacturer_slug = ?,status = ?, updated_by = ?, updated_at = ? WHERE manufacturer_id = ?";
			jdbcTemplate.update(sql, manufacturer.getManufacturer_name(), manufacturer.getManufacturer_slug(),
					manufacturer.getStatus(), manufacturer.getUpdated_by(), manufacturer.getUpdated_at(),
					manufacturer.getManufacturer_id());
		}

	}

	public void deleteManufacturer(int manufacturer_id) {
		String sql = "DELETE FROM manufacturer WHERE manufacturer_id = " + manufacturer_id;
		jdbcTemplate.update(sql);
	}

	public ManufacturerEntity findManufacturerById(ManufacturerEntity manufacturer) {
		String sql = "SELECT * FROM manufacturer where manufacturer_id = " + manufacturer.getManufacturer_id();
		List<ManufacturerEntity> result = jdbcTemplate.query(sql, new ManufacturerMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public ManufacturerEntity findAllManufacturerBySlug(String manufacturer_slug) {
		String sql = "SELECT * FROM manufacturer where manufacturer_slug = '" + manufacturer_slug + "'";
		List<ManufacturerEntity> result = jdbcTemplate.query(sql, new ManufacturerMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public List<ManufacturerEntity> findTrashManufacturer() {
		String sql = "SELECT * FROM manufacturer where status = 0";
		List<ManufacturerEntity> list = jdbcTemplate.query(sql, new ManufacturerMapper());
		return list;
	}

	public void deltrash(int manufacturer_id,UserEntity loginInfo) {
		String sql = "UPDATE manufacturer SET status = 0,updated_by = " + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE manufacturer_id = " + manufacturer_id;
		jdbcTemplate.update(sql);
	}

	public void retrash(int manufacturer_id,UserEntity loginInfo) {
		String sql = "UPDATE manufacturer SET status = 2,updated_by = " + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE manufacturer_id = " + manufacturer_id;
		jdbcTemplate.update(sql);
	}

	public void onOffManufacturer(int manufacturer_id,UserEntity loginInfo) {
		String sql = "UPDATE manufacturer SET status = case WHEN status =1 then 2 when status=2 then 1 end,updated_by = "
					+ loginInfo.getUser_id() + ", updated_at = NOW() WHERE manufacturer_id = "
				+ manufacturer_id;
		jdbcTemplate.update(sql);
	}

	private String SqlManufacturersPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlManufacturerTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ManufacturerEntity> GetDataManufacturerPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<ManufacturerEntity> listManufacturer = jdbcTemplate.query(sqlGetData.toString(), new ManufacturerMapper());
		List<ManufacturerEntity> listManufacturers = new ArrayList<ManufacturerEntity>();
		if (listManufacturer.size() > 0) {
			String sql = SqlManufacturersPaginate(start, totalPage);
			listManufacturers = jdbcTemplate.query(sql, new ManufacturerMapper());
		}
		return listManufacturers;
	}

	public List<ManufacturerEntity> GetDataManufacturerTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<ManufacturerEntity> listManufacturerTrash = jdbcTemplate.query(sqlGetData.toString(),
				new ManufacturerMapper());
		List<ManufacturerEntity> listManufacturerTrashs = new ArrayList<ManufacturerEntity>();
		if (listManufacturerTrash.size() > 0) {
			String sql = SqlManufacturerTrashsPaginate(start, totalPage);
			listManufacturerTrashs = jdbcTemplate.query(sql, new ManufacturerMapper());
		}
		return listManufacturerTrashs;
	}

	public boolean isNameExists(String title) {
		int count = 0;
		String sql = "SELECT count(*) FROM manufacturer WHERE manufacturer_name = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { title }, Integer.class);

		return count > 0;
	}

	public boolean isNameExists(String title, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM manufacturer WHERE manufacturer_name = ? and manufacturer_id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { title, id }, Integer.class);

		return count > 0;
	}

	public boolean isSlugExists(String slug) {
		int count = 0;
		String sql = "SELECT count(*) FROM manufacturer WHERE manufacturer_slug = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { slug }, Integer.class);

		return count > 0;
	}

	public boolean isSlugExists(String slug, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM manufacturer WHERE manufacturer_slug = ? and manufacturer_id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { slug, id }, Integer.class);

		return count > 0;
	}
}
