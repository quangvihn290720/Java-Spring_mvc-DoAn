package com.khoaluantotnghiep.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import com.khoaluantotnghiep.entity.ReportColumn;

@Service
public interface ReportService {

	public List<ReportColumn> reportReceiptDay(Date date, int limit);

	public List<ReportColumn> reportReceiptMonth(Date date, int limit);

	public int getNumberAccounts();

	public double getTotalInMonth();
	
	public double getTotalLastMonth();

	public int getNumberOfCancledBillsinMonth();
}
