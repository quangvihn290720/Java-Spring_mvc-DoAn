package com.khoaluantotnghiep.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.BilldetailEntity;
import com.khoaluantotnghiep.entity.BillsEntity;
import com.khoaluantotnghiep.entity.ReportColumn;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.BilldetailMapper;
import com.khoaluantotnghiep.mapper.BillsMapper;

@Transactional
@Repository
public class BillsDAO extends BaseDAO {
	String uuid = UUID.randomUUID().toString();
	// Remove dashes
	String uuid2 = uuid.replaceAll("-", "");

	public BillsEntity findBillsConfirm(String code) {
		String sql = "SELECT * FROM bills where code = '" + code + "'";
		List<BillsEntity> result = jdbcTemplate.query(sql, new BillsMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public int AddBills(BillsEntity bill, double priceafterpromotion) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO bills ");
		sql.append("( ");
		sql.append(
				"`email`,`display_name`,`phone`,`address`,`province`,`district`,`city`,`note`,`total`,`quanty`,`status`,`created_at`,`updated_at`,`coupon`,`code`,`coupon_id`");
		sql.append(") VALUES ( ");
		sql.append(" '" + bill.getEmail() + "', ");
		sql.append(" '" + bill.getDisplay_name() + "', ");
		sql.append(" '" + bill.getPhone() + "', ");
		sql.append(" '" + bill.getAddress() + "', ");
		sql.append(" '" + bill.getProvince() + "', ");
		sql.append(" '" + bill.getDistrict() + "', ");
		sql.append(" '" + bill.getCity() + "', ");
		sql.append(" '" + bill.getNote() + "', ");
		sql.append(" '" + (bill.getTotal() - (bill.isCoupon() ? priceafterpromotion : 0)) + "', ");
		sql.append(" '" + bill.getQuanty() + "', ");
		sql.append(" '" + bill.getStatus() + "', ");
		sql.append(" '" + bill.getCreated_at() + "', ");
		sql.append(" '" + bill.getUpdated_at() + "', ");
		sql.append("" + bill.isCoupon() + ",");
		sql.append(" '" + bill.getCode() + "', ");
		sql.append(" '" + bill.getCoupon_id() + "' ");
		sql.append(" );");
		int insert = jdbcTemplate.update(sql.toString());
		return insert;
	}

	public int GetIdLastBills() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MAX(id) FROM bills ");
		int id = jdbcTemplate.queryForObject(sql.toString(), new Object[] {}, Integer.class);
		return id;
	}

	public int AddBillsDetail(BilldetailEntity billdetail) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO billdetail ");
		sql.append("( ");
		sql.append("`product_id`,`bills_id`,`quanty`,`total`");
		sql.append(") VALUES ( ");
		sql.append(" '" + billdetail.getProduct_id() + "', ");
		sql.append(" '" + billdetail.getBills_id() + "', ");
		sql.append(" '" + billdetail.getQuanty() + "', ");
		sql.append(" '" + billdetail.getTotal() + "' ");
		sql.append(" );");
		int insert = jdbcTemplate.update(sql.toString());
		return insert;
	}

	public List<BillsEntity> getAllBill() {
		String sql = "SELECT * FROM bills ";
		List<BillsEntity> list = jdbcTemplate.query(sql, new BillsMapper());
		return list;
	}

	public BillsEntity findBillById(int id) {
		String sql = "SELECT * FROM bills where id = " + id;
		List<BillsEntity> result = jdbcTemplate.query(sql, new BillsMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public List<BilldetailEntity> getBillsDetail(int id) {
		String sql = "SELECT * FROM billdetail where bills_id = " + id;
		List<BilldetailEntity> result = jdbcTemplate.query(sql, new BilldetailMapper());
		return result;
	}

	public void deleteBill(int id) {
		String sql = "DELETE FROM bills WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void changeStatus(int id, UserEntity loginInfo) {
		String sql = "UPDATE bills set status = status + 1,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW() where status < 4 and id = " + id;
//		String sql = "UPDATE bills SET status = case when  status =1 then 0 when  status =0 then 1 end where  id ="
//				+ id;
		jdbcTemplate.update(sql);
	}

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * from bills ");
		return sql;
	}

	private StringBuffer SqlBillSeach(String display_name) {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND display_name like  '%" + display_name + "%' ");
		return sql;
	}

	public List<BillsEntity> SearchBill(String display_name) {
		String sql = "SELECT * FROM bills WHERE display_name like '%" + display_name + "%' ";
		return jdbcTemplate.query(sql, new BillsMapper());
	}

	private String SqlBillSeachPaginate(int start, int totalPage, String display_name) {
		StringBuffer sql = SqlBillSeach(display_name);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlBillPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append(" order by created_at desc LIMIT " + start + ", " + totalPage + "  ");
		return sql.toString();
	}

	private StringBuffer SqlBillByEmail(String email) {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND email =  '" + email + "' order by created_at desc ");
		return sql;
	}

	private String SqlBillByEmailPaginate(int start, int totalPage, String email) {
		StringBuffer sql = SqlBillByEmail(email);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<BillsEntity> GetDataBillPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<BillsEntity> listbill = jdbcTemplate.query(sqlGetData.toString(), new BillsMapper());
		List<BillsEntity> listbills = new ArrayList<BillsEntity>();
		if (listbill.size() > 0) {
			String sql = SqlBillPaginate(start, totalPage);
			listbills = jdbcTemplate.query(sql, new BillsMapper());
		}
		return listbills;
	}

	public List<BillsEntity> GetDataBillByEmailPaginate(int start, int totalPage, String email) {
		StringBuffer sqlGetData = SqlBillByEmail(email);
		List<BillsEntity> listbill = jdbcTemplate.query(sqlGetData.toString(), new BillsMapper());
		List<BillsEntity> listbills = new ArrayList<BillsEntity>();
		if (listbill.size() > 0) {
			String sql = SqlBillByEmailPaginate(start, totalPage, email);
			listbills = jdbcTemplate.query(sql, new BillsMapper());
		}
		return listbills;
	}

	public List<BillsEntity> GetDataBillSeachPaginate(int start, int totalPage, String display_name) {
		StringBuffer sqlGetData = SqlBillSeach(display_name);
		List<BillsEntity> listbill = jdbcTemplate.query(sqlGetData.toString(), new BillsMapper());
		List<BillsEntity> listbills = new ArrayList<BillsEntity>();
		if (listbill.size() > 0) {
			String sql = SqlBillSeachPaginate(start, totalPage, display_name);
			listbills = jdbcTemplate.query(sql, new BillsMapper());
		}
		return listbills;
	}

	public List<BillsEntity> findBillsByEmail(String email) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * from bills ");
		sql.append(" WHERE email = '" + email + "'");
		List<BillsEntity> result = jdbcTemplate.query(sql.toString(), new BillsMapper());
		return result;
	}

	public List<ReportColumn> reportReceiptDay(Date date, int limit) {
		List<ReportColumn> list = new ArrayList<>();
		for (int i = limit - 1; i >= 0; i--) {
			Date d = subDays(date, i);
			ReportColumn rp = new ReportColumn();
			rp.setTime(covertD2S(d));
			rp.setValue(countItemByDate(rp.getTime()));
			list.add(rp);
		}
		return list;
	}

	private int countItemByDate(String date) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM bills WHERE DATE_FORMAT(created_at,'%d/%m/%Y') = '" + date + "'");
		List<BillsEntity> result = jdbcTemplate.query(sql.toString(), new BillsMapper());
		return result.size();
	}

	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date subDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
		return cal.getTime();
	}

	private String covertD2S(Date date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyy");
		return df.format(date);
	}

	public List<BillsEntity> GetDataCustomerPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlCustomer();
		List<BillsEntity> listbill = jdbcTemplate.query(sqlGetData.toString(), new BillsMapper());
		List<BillsEntity> listbills = new ArrayList<BillsEntity>();
		if (listbill.size() > 0) {
			String sql = SqlCustomerPaginate(start, totalPage);
			listbills = jdbcTemplate.query(sql, new BillsMapper());
		}
		return listbills;
	}

	private StringBuffer SqlCustomer() {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("GROUP BY `email` ");
		return sql;
	}

	private String SqlCustomerPaginate(int start, int totalPage) {
		StringBuffer sql = SqlCustomer();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<BillsEntity> GetDataCustomer() {
		String sql = SqlCustomer().toString();
		List<BillsEntity> list = jdbcTemplate.query(sql, new BillsMapper());
		return list;
	}

	public void cancelBill(int id, UserEntity loginInfo) {
		String sql = "UPDATE bills set status = -1,updated_by ="
				+ loginInfo.getUser_id() + ", updated_at = NOW() where  id = " + id;
		jdbcTemplate.update(sql);
	}
	public void cancelBillforUser(int id, UserEntity loginInfo) {
		String sql = "UPDATE bills set status = -1,updated_by ="
				+ loginInfo.getUser_id() + ", updated_at = NOW() where  id = " + id + " and status = 0"; 
		jdbcTemplate.update(sql);
	}
}
