package com.khoaluantotnghiep.controller.admin;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.khoaluantotnghiep.dto.PaginateDTO;
import com.khoaluantotnghiep.entity.NoteEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.AccountServiceImpl;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;
import com.khoaluantotnghiep.service.impl.RoleServiceImpl;

@Controller(value = "userControllerOfAdmin")
public class UserController extends BaseController {
	@Autowired
	RoleServiceImpl roleService;
	@Autowired
	AccountServiceImpl accountService;
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	NoteServiceImpl noteService;
	private int totalDataPage = 10;

	@GetMapping("/quan-tri/web/tai-khoan")
	public ModelAndView viewAccount() {
		Map<Integer, String> mapRole = roleService.mapRole();
		_mvShare.addObject("mapRole", mapRole);
		int totalData = accountService.findAllUser().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("userPaginate", accountService.GetDataUserPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/user/user");
		return _mvShare;
	}

	@GetMapping("/quan-tri/web/tai-khoan/{page}")
	public ModelAndView viewAccount(@PathVariable String page) {
		int totalData = accountService.findAllUser().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, Integer.parseInt(page));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("userPaginate", accountService.GetDataUserPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/user/user");
		return _mvShare;
	}

	@GetMapping("/quan-tri/web/chi-tiet-tai-khoan/{id}")
	public ModelAndView viewDetailAccount(@PathVariable int id) {
		Map<Integer, String> mapRole = roleService.mapRole();
		_mvShare.addObject("mapRole", mapRole);
		_mvShare.addObject("user", accountService.findAccountById(id));
		_mvShare.setViewName("admin/user/detailuser");
		return _mvShare;
	}

	@GetMapping("/quan-tri/web/tai-khoan/cap-nhat-quyen/{id}")
	public ModelAndView viewchangerole(@ModelAttribute("user") UserEntity user, @PathVariable int id) {
		_mvShare.addObject("useritem", accountService.findAccountById(id));
		_mvShare.addObject("listrole", roleService.findAllRoleShow());
		_mvShare.setViewName("admin/user/changeroleuser");
		return _mvShare;
	}

	@PostMapping("/quan-tri/web/tai-khoan/cap-nhat-quyen/editsave")
	public String savechangerole(@ModelAttribute("user") UserEntity user, final RedirectAttributes redirectAttributes,
			HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			accountService.setRoleMain(user);
			redirectAttributes.addFlashAttribute("msg", "Cập nhật thành công");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã chỉnh sửa quyền tài khoản: " + user.getUser_id());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Cập nhật không thành công");
		}
		return "redirect:/quan-tri/web/tai-khoan";
	}

	@GetMapping(value = "/quan-tri/thong-tin-tai-khoan")
	public ModelAndView viewAccountLogin(HttpSession session) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		_mvShare.addObject("loginInfo", loginInfo);
		_mvShare.setViewName("admin/user/viewuserlogin");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/web/tai-khoan/xoa-tai-khoan/{id}")
	public String savechangerole(@PathVariable int id, final RedirectAttributes redirectAttributes,
			HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			accountService.delete(id);
			redirectAttributes.addFlashAttribute("msg", "Cập nhật thành công");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã xóa tài khoản: " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Cập nhật không thành công");
		}
		return "redirect:/quan-tri/web/tai-khoan";
	}
}
