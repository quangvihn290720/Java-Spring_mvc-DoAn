package com.khoaluantotnghiep.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.khoaluantotnghiep.dto.PaginateDTO;
import com.khoaluantotnghiep.service.impl.BillsServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController extends BaseController {

	@Autowired
	BillsServiceImpl billsService;
	@Autowired
	PaginatesServiceImpl paginateService;
	private int totalDataPage = 5;

	@GetMapping("/quan-tri/khach-hang")
	public ModelAndView viewCustomer() {
		int totalData = billsService.GetDataCustomer().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("customerPaginate",
				billsService.GetDataCustomerPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/user/customer");
		return _mvShare;
	}

	@GetMapping("/quan-tri/khach-hang/{page}")
	public ModelAndView viewCustomer(@PathVariable String page) {
		int totalData = billsService.GetDataCustomer().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, Integer.parseInt(page));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("customerPaginate",
				billsService.GetDataCustomerPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/user/customer");
		return _mvShare;
	}

}