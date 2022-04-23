package com.khoaluantotnghiep.dao;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.khoaluantotnghiep.entity.ReportColumn;

@Repository
public class ReportDAO extends BaseDAO {
	public List<ReportColumn> reportReceiptMonth(Date date, int limit) {
		List<ReportColumn> list = new ArrayList<>();
		for (int i = limit - 1; i >= 0; i--) {
			Date d = subMonths(date, i);
			ReportColumn rp = new ReportColumn();
			rp.setTime(covertD2MYS(d));
			rp.setMapValue(totaltItemByMonth(d));
			list.add(rp);
		}
		return list;
	}

	public Map<Integer, Integer> totaltItemByMonth(Date d) {
		System.out.println("Ngay gio hien tai: " + d.toString());
		int month = covert2M(d);
		int year = covert2Y(d);
		System.out.println("Thang:  " + month);
		System.out.println("Nam:  " + year);
		String sql = "SELECT product.product_catid,COALESCE(sum(detail.quanty),0) as tongsanpham FROM product "
				+ "left JOIN (select billdetail.id, billdetail.product_id,billdetail.bills_id,billdetail.quanty,bills.created_at from"
				+ " billdetail, bills where billdetail.bills_id = bills.id" + " and  MONTH(bills.created_at) = " + month
				+ " and YEAR(bills.created_at) = " + year + ") as detail"
				+ " on product.product_id = detail.product_id " + "GROUP BY product.product_catid";
		Map<Integer, Integer> total = jdbcTemplate.query(sql, (ResultSet rs) -> {
			Map<Integer, Integer> results = new HashMap<>();
			while (rs.next()) {
				results.put(rs.getInt("product_catid"), rs.getInt("tongsanpham"));
			}
			return results;
		});
		return total;
	}

	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date subMonths(Date date, int month) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -month);
		return cal.getTime();
	}

	public String covertD2MYS(Date date) {
		DateFormat df = new SimpleDateFormat("MM/yyy");
		return df.format(date);
	}

	public int covert2M(Date date) {
		DateFormat df = new SimpleDateFormat("M");
		return Integer.parseInt(df.format(date));
	}

	public int covert2Y(Date date) {
		DateFormat df = new SimpleDateFormat("yyy");
		return Integer.parseInt(df.format(date));
	}
	public int getNumberAccounts() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(user_id) FROM user ");
		int rs = jdbcTemplate.queryForObject(sql.toString(), new Object[] {}, Integer.class);
		return rs;
	}
	public double getTotalInMonth() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COALESCE(sum(total),0) FROM bills where status = 4 and MONTH(created_at) = MONTH(CURRENT_DATE()) and year(created_at)  = year(CURRENT_DATE())");
		double rs = jdbcTemplate.queryForObject(sql.toString(), new Object[] {}, double.class);
		return rs;
	}
	public double getTotalLastMonth() {
		StringBuffer sql = new StringBuffer();	
		sql.append("SELECT COALESCE(sum(total),0) FROM bills WHERE status = 4 and YEAR(created_at) = YEAR(CURRENT_DATE() - INTERVAL 1 MONTH) AND MONTH(created_at) = MONTH(created_at - INTERVAL 1 MONTH) ");
		double rs = jdbcTemplate.queryForObject(sql.toString(), new Object[] {}, double.class);
		return rs;
	}
	public int getNumberOfCancledBillsinMonth() {
		StringBuffer sql = new StringBuffer();	
		sql.append("SELECT count(id) FROM bills WHERE status = -1 and MONTH(created_at) = MONTH(CURRENT_DATE()) and year(created_at)  = year(CURRENT_DATE()) ");
		int rs = jdbcTemplate.queryForObject(sql.toString(), new Object[] {}, Integer.class);
		return rs;
	}
}
