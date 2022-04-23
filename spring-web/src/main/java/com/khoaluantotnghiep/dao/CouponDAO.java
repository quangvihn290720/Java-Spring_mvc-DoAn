package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.CouponEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.CouponMapper;

@Repository
@Transactional
public class CouponDAO extends BaseDAO {

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM coupon where status != 0 ORDER BY status ASC ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM coupon where status = 0 ");
		return sql;
	}

	public List<CouponEntity> findAllCoupon() {
		String sql = "SELECT * FROM coupon where status != 0 ORDER BY status ASC ";
		List<CouponEntity> list = jdbcTemplate.query(sql, new CouponMapper());
		return list;
	}

	public List<CouponEntity> findAllCouponShow() {
		String sql = "SELECT * FROM coupon where status = 1";
		List<CouponEntity> list = jdbcTemplate.query(sql, new CouponMapper());
		return list;
	}

	public void add(CouponEntity coupon) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		String sql = "INSERT INTO `coupon`(`code`, `available`,`pricesale`, `start`, `end`, `status`,"
				+ " `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, coupon.getCode(), coupon.getAvailable(), coupon.getPricesale(), coupon.getStart(),
				coupon.getEnd(), coupon.getStatus(), coupon.getCreated_at(), coupon.getCreated_by(),
				coupon.getUpdated_at(), coupon.getUpdated_by());
	}

	public void update(CouponEntity coupon) {
		String sql = "UPDATE `coupon` SET `code`=?, `available`=?,`pricesale`=?, `start`=?, `end`=?, `status`=?, "
				+ " `updated_at` = ?, `updated_by` = ? WHERE id= ?";
		jdbcTemplate.update(sql, coupon.getCode(), coupon.getAvailable(), coupon.getPricesale(), coupon.getStart(),
				coupon.getEnd(), coupon.getStatus(), coupon.getUpdated_at(), coupon.getUpdated_by(), coupon.getId());
	}

	public void delete(int id) {
		String sql = "DELETE FROM coupon WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public CouponEntity findCouponById(CouponEntity coupon) {
		String sql = "SELECT * FROM coupon where id = " + coupon.getId();
		List<CouponEntity> result = jdbcTemplate.query(sql, new CouponMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public CouponEntity findCouponById(int id) {
		String sql = "SELECT * FROM coupon where id = " + id;
		List<CouponEntity> result = jdbcTemplate.query(sql, new CouponMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public List<CouponEntity> findTrashCoupon() {
		String sql = "SELECT * FROM coupon where status = 0";
		List<CouponEntity> list = jdbcTemplate.query(sql, new CouponMapper());
		return list;
	}

	public void deltrash(int id, UserEntity loginInfo) {
		String sql = "UPDATE coupon SET status = 0,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void retrash(int id, UserEntity loginInfo) {
		String sql = "UPDATE coupon SET status = 2,updated_by =" + loginInfo.getUser_id() + 
", updated_at = NOW() WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void onOffCoupon(int id, UserEntity loginInfo) {
		String sql = "UPDATE coupon SET status = case WHEN status =1 then 2 when status=2 then 1 end,updated_by ="
				+ loginInfo.getUser_id() + ", updated_at = NOW() WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	private String SqlCouponsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlCouponTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<CouponEntity> GetDataCouponPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<CouponEntity> listCoupon = jdbcTemplate.query(sqlGetData.toString(), new CouponMapper());
		List<CouponEntity> listCoupons = new ArrayList<CouponEntity>();
		if (listCoupon.size() > 0) {
			String sql = SqlCouponsPaginate(start, totalPage);
			listCoupons = jdbcTemplate.query(sql, new CouponMapper());
		}
		return listCoupons;
	}

	public List<CouponEntity> GetDataCouponTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<CouponEntity> listCouponTrash = jdbcTemplate.query(sqlGetData.toString(), new CouponMapper());
		List<CouponEntity> listCouponTrashs = new ArrayList<CouponEntity>();
		if (listCouponTrash.size() > 0) {
			String sql = SqlCouponTrashsPaginate(start, totalPage);
			listCouponTrashs = jdbcTemplate.query(sql, new CouponMapper());
		}
		return listCouponTrashs;
	}

	public boolean isNameExists(String name) {
		int count = 0;
		String sql = "SELECT count(*) FROM coupon WHERE code = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);

		return count > 0;
	}

	public boolean isNameExists(String name, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM coupon WHERE code = ? and id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name, id }, Integer.class);

		return count > 0;
	}

	public List<CouponEntity> findCouponByCode(String code) {
		String sql = "SELECT * FROM coupon where code = '" + code
				+ "' and available > 0 and CURDATE() BETWEEN start AND end";
		List<CouponEntity> result = jdbcTemplate.query(sql, new CouponMapper());
		return result;
	}

	public CouponEntity checkAvailCode(String code) {
		String sql = "SELECT * FROM coupon WHERE code = '" + code
				+ "' and available > 0 and CURDATE() BETWEEN start AND end";
		List<CouponEntity> result = jdbcTemplate.query(sql, new CouponMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public void subVailable(String code) {
		String sql = "UPDATE coupon set available = available - 1 WHERE code = '" + code + "'";
		jdbcTemplate.update(sql);
	}
}
