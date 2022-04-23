package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.BannerEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.BannerMapper;

@Transactional
@Repository
public class BannerDAO extends BaseDAO {
	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM banner where banner_status != 0 ORDER BY banner_status ASC ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM banner where banner_status = 0 ");
		return sql;
	}

	private String SqlBannerPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlBannerTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<BannerEntity> GetDataBannerPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<BannerEntity> listBanner = jdbcTemplate.query(sqlGetData.toString(), new BannerMapper());
		List<BannerEntity> listBanners = new ArrayList<BannerEntity>();
		if (listBanner.size() > 0) {
			String sql = SqlBannerPaginate(start, totalPage);
			listBanners = jdbcTemplate.query(sql, new BannerMapper());
		}
		return listBanners;
	}

	public List<BannerEntity> GetDataBannerTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<BannerEntity> listBannerTrash = jdbcTemplate.query(sqlGetData.toString(), new BannerMapper());
		List<BannerEntity> listBannerTrashs = new ArrayList<BannerEntity>();
		if (listBannerTrash.size() > 0) {
			String sql = SqlBannerTrashsPaginate(start, totalPage);
			listBannerTrashs = jdbcTemplate.query(sql, new BannerMapper());
		}
		return listBannerTrashs;
	}

	public List<BannerEntity> findAllBanner() {
		String sql = "SELECT * FROM banner where banner_status != 0 ";
		List<BannerEntity> list = jdbcTemplate.query(sql, new BannerMapper());
		return list;
	}

	public List<BannerEntity> findAllBannerShow() {
		String sql = "SELECT * FROM banner where banner_status = 1";
		List<BannerEntity> list = jdbcTemplate.query(sql, new BannerMapper());
		return list;
	}

	public BannerEntity findBannerById(BannerEntity banner) {
		String sql = "SELECT * FROM banner where id = " + banner.getId();
		List<BannerEntity> result = jdbcTemplate.query(sql, new BannerMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public List<BannerEntity> findTrashBanner() {
		String sql = "SELECT * FROM banner where banner_status = 0";
		List<BannerEntity> list = jdbcTemplate.query(sql, new BannerMapper());
		return list;
	}

	public void deltrashBanner(int id,UserEntity loginInfo) {
		String sql = "UPDATE banner SET banner_status = 0,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void retrashBanner(int id,UserEntity loginInfo) {
		String sql = "UPDATE banner SET banner_status = 2,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void addBanner(BannerEntity banner) {
		String sql = "INSERT INTO banner (`banner_name`,`banner_img`,`banner_status`,`created_by`,`updated_by`,`created_at`,`updated_at`)"
				+ " VALUES (?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, banner.getBanner_name(), banner.getBanner_img(), banner.getBanner_status(),
				banner.getCreated_by(), banner.getUpdated_by(), banner.getCreated_at(), banner.getUpdated_at());
	}

	public void updateBanner(BannerEntity banner) {
		String sql = "";
		if (banner.getBanner_img() != null && !banner.getBanner_img().isEmpty()) {
			sql = "UPDATE banner SET banner_name = ?, banner_img = ?, banner_status = ?, updated_by = ?,  updated_at = ?  WHERE id = ?";
			jdbcTemplate.update(sql, banner.getBanner_name(), banner.getBanner_img(), banner.getBanner_status(),
					banner.getUpdated_by(), banner.getUpdated_at(), banner.getId());
		} else {
			sql = "UPDATE banner SET banner_name = ?, banner_status = ?, updated_by = ?,  updated_at = ?  WHERE id = ?";
			jdbcTemplate.update(sql, banner.getBanner_name(), banner.getBanner_status(), banner.getUpdated_by(),
					banner.getUpdated_at(), banner.getId());
		}
	}

	public void deleteBanner(int id) {
		String sql = "DELETE FROM banner WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void onOffBanner(int id,UserEntity loginInfo) {
		String sql = "UPDATE banner SET banner_status = case WHEN banner_status =1 then 2 when banner_status=2 then 1 end,updated_by ="
				+ loginInfo.getUser_id() + ", updated_at = NOW() where id = " + id;
		jdbcTemplate.update(sql);
	}

	public boolean isNameExists(String name) {
		int count = 0;
		String sql = "SELECT count(*) FROM banner WHERE banner_name = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);

		return count > 0;
	}

	public boolean isNameExists(String name, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM banner WHERE banner_name = ? and id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name, id }, Integer.class);

		return count > 0;
	}

}
