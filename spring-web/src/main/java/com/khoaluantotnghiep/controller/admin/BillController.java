package com.khoaluantotnghiep.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.khoaluantotnghiep.dto.PaginateDTO;
import com.khoaluantotnghiep.entity.BilldetailEntity;
import com.khoaluantotnghiep.entity.BillsEntity;
import com.khoaluantotnghiep.entity.CouponEntity;
import com.khoaluantotnghiep.entity.NoteEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.BillsServiceImpl;
import com.khoaluantotnghiep.service.impl.CouponServiceImpl;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;
import com.khoaluantotnghiep.ultis.ExportExcel;

@Controller(value = "billControllerOfAdmin")
public class BillController extends BaseController {

	@Autowired
	BillsServiceImpl billsService;
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	CouponServiceImpl couponService;
	@Autowired
	NoteServiceImpl noteService;
	private int totalProductPage = 8;

	@RequestMapping(value = "/quan-tri/hoa-don", method = RequestMethod.GET)
	public ModelAndView bill() {
		int totalData = billsService.getAllBill().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("billPaginate", billsService.GetDataBillPaginate(paginateInfo.getStart(), totalProductPage));
		_mvShare.setViewName("admin/bill/bill");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/hoa-don/{currentPage}", method = RequestMethod.GET)
	public ModelAndView bill(@PathVariable String currentPage) {
		int totalData = billsService.getAllBill().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("billPaginate", billsService.GetDataBillPaginate(paginateInfo.getStart(), totalProductPage));
		_mvShare.setViewName("admin/bill/bill");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/hoa-don/chi-tiet/{id}", method = RequestMethod.GET)
	public ModelAndView billDetail(@ModelAttribute("bill") BillsEntity bill, @PathVariable int id) {
		BillsEntity billitem = billsService.findBillById(id);
		List<BilldetailEntity> billdetail = billsService.getBillsDetail(id);
		double total = 0;
		for (BilldetailEntity it : billdetail) {
			total += it.getTotal();
		}
		CouponEntity couponEntity = couponService.findCouponById(billitem.getCoupon_id());
		_mvShare.addObject("couponEntity", couponEntity);
		_mvShare.addObject("bill", billitem);
		_mvShare.addObject("billdetail", billdetail);
		_mvShare.addObject("total", total);
		_mvShare.setViewName("admin/bill/billdetail");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/hoa-don/status/{id}", method = RequestMethod.GET)
	public String changeStatus(@PathVariable int id, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			billsService.changeStatus(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã thay đổi trạng thái đơn hàng " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/hoa-don/cancel/{id}", method = RequestMethod.GET)
	public String cancelBill(@PathVariable int id, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			billsService.cancelBill(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã hủy đơn hàng đơn hàng " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/hoa-don/delete/{id}", method = RequestMethod.GET)
	public String deleteBill(@PathVariable int id, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			billsService.deleteBill(id);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã xóa đơn hàng đơn hàng " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/search")
	public ModelAndView SearchBill(@RequestParam String q,
			@RequestParam(value = "currentPage", required = false) String currentPage) {
		int totalData = billsService.SearchBill(q).size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("searchPaginate",
				billsService.GetDataBillSeachPaginate(paginateInfo.getStart(), totalProductPage, q));
		_mvShare.addObject("q", q);
		_mvShare.setViewName("admin/search/searchbill");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/hoa-don/exportExcel")
	@ResponseBody
	public void exportExcel(HttpServletResponse response) throws Exception {
		String title = "Thống kê hóa đơn khách hàng";
		String[] rowsName = new String[] { "No", "Id Bill", "Name", "Email", "Phone", "Province", "District", "City",
				"Quanty", "Total", "Status", "Created at" };
		List<BillsEntity> bills = billsService.getAllBill();
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		for (int i = 0; i < bills.size(); i++) {
			BillsEntity bill = bills.get(i);
			objs = new Object[rowsName.length];
			objs[0] = i;
			objs[1] = bill.getId();
			objs[2] = bill.getDisplay_name();
			objs[3] = bill.getEmail();
			objs[4] = bill.getPhone();
			objs[5] = bill.getProvince();
			objs[6] = bill.getDistrict();
			objs[7] = bill.getCity();
			objs[8] = bill.getQuanty();
			objs[9] = bill.getTotal();
			objs[10] = bill.getStatus() == 0 ? "Chưa hoàn thành" : "Đã hoàn thành";
			objs[11] = bill.getCreated_at();
			dataList.add(objs);
		}
		ExportExcel ex = new ExportExcel(title, rowsName, dataList, response);
		ex.export();
	}

}
