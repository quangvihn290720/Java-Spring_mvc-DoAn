package com.khoaluantotnghiep.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.BillsDAO;
import com.khoaluantotnghiep.dao.ReportDAO;
import com.khoaluantotnghiep.entity.ReportColumn;
import com.khoaluantotnghiep.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private BillsDAO billsDAO;
	@Autowired
	private ReportDAO rpDAO;

	@Override
	public List<ReportColumn> reportReceiptDay(Date date, int limit) {

		return billsDAO.reportReceiptDay(date, limit);
	}

	@Override
	public List<ReportColumn> reportReceiptMonth(Date date, int limit) {
		return rpDAO.reportReceiptMonth(date, limit);
	}

	@Override
	public int getNumberAccounts() {
		return rpDAO.getNumberAccounts();
	}

	@Override
	public double getTotalInMonth() {
		return rpDAO.getTotalInMonth();
	}

	@Override
	public double getTotalLastMonth() {
		return rpDAO.getTotalLastMonth();
	}

	@Override
	public int getNumberOfCancledBillsinMonth() {
		return rpDAO.getNumberOfCancledBillsinMonth();
	}
}
