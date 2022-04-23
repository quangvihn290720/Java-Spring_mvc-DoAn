package com.khoaluantotnghiep.controller.admin;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.khoaluantotnghiep.entity.ReportColumn;
import com.khoaluantotnghiep.service.impl.BillsServiceImpl;
import com.khoaluantotnghiep.service.impl.CategoryServiceImpl;
import com.khoaluantotnghiep.service.impl.ReportServiceImpl;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {
	@Autowired
	BillsServiceImpl billsService;
	@Autowired
	CategoryServiceImpl catService;
	@Autowired
	ReportServiceImpl reportService;

	@RequestMapping(value = "/quan-tri", method = RequestMethod.GET)

	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("admin/home");
		Date date = new Date();
		List<ReportColumn> listItem = reportService.reportReceiptDay(date, 7);
		Map<Integer, String> mapCate = catService.mapCate();
		List<ReportColumn>  listRpCat = reportService.reportReceiptMonth(date, 6);
		for(ReportColumn i:listItem) {
			System.out.println("Date: " + i.getTime() +", Value: " + i.getValue());
		}
		for(ReportColumn i:listRpCat) {
			System.out.println("M/y:" + i.getTime() +", Value: " + i.getMapValue()) ;
		}
		mav.addObject("listReceipt",listItem);
		mav.addObject("mapCate",mapCate);
		mav.addObject("rpCate",listRpCat);
		mav.addObject("rpAccount",reportService.getNumberAccounts());
		mav.addObject("rpTotalInMonth",reportService.getTotalInMonth());
		mav.addObject("rpTotalLastMonth",reportService.getTotalLastMonth());
		mav.addObject("rpCancelBill",reportService.getNumberOfCancledBillsinMonth());
		return mav;
	}
}
