package com.khoaluantotnghiep.controller.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.khoaluantotnghiep.dto.PaginateDTO;
import com.khoaluantotnghiep.entity.NoteEntity;
import com.khoaluantotnghiep.entity.OptiongroupsEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.OptiongroupsServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;

@Controller(value = "optionGroupControllerOfAdmin")
public class OptionGroupController extends BaseController {
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	OptiongroupsServiceImpl optiongroupsService;
	@Autowired
	NoteServiceImpl noteService;
	private int totalDataPage = 5;

	@GetMapping("/quan-tri/nhom-tuy-chon")
	public ModelAndView optionGroup() {
		int totalData = optiongroupsService.findAllOptionGroup().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("opGroupPaginate",
				optiongroupsService.GetDataOptionGroupPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/optiongroup/optiongroup");
		return _mvShare;
	}

	@GetMapping("/quan-tri/nhom-tuy-chon/{currentPage}")
	public ModelAndView optionGroup(@PathVariable String currentPage) {
		int totalData = optiongroupsService.findAllOptionGroup().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("opGroupPaginate",
				optiongroupsService.GetDataOptionGroupPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/optiongroup/optiongroup");
		return _mvShare;
	}

	@GetMapping("/quan-tri/nhom-tuy-chon/thung-rac")
	public ModelAndView optionGroupTrash() {
		int totalData = optiongroupsService.findAllTrashOptionGroup().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("opGroupPaginate",
				optiongroupsService.GetDataOptionGroupTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/optiongroup/trashoptiongroup");
		return _mvShare;
	}

	@GetMapping("/quan-tri/nhom-tuy-chon/thung-rac/{currentPage}")
	public ModelAndView optionGroupTrash(@PathVariable String currentPage) {
		int totalData = optiongroupsService.findAllTrashOptionGroup().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("opGroupPaginate",
				optiongroupsService.GetDataOptionGroupTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/optiongroup/trashoptiongroup");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/nhom-tuy-chon/delete/{id}")
	public String deleteoptionGroup(@PathVariable int id, ModelMap modelMap,
			final RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			optiongroupsService.deleteOptionGroup(id);
			redirectAttributes.addFlashAttribute("msg", "Xóa thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin xóa vĩnh viễn nhóm tùy chọn " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Xóa không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/nhom-tuy-chon/trash/{id}")
	public String deltrashoptionGroup(@PathVariable int id, ModelMap modelMap,
			final RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			optiongroupsService.deltrash(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin xóa tạm thời nhóm tùy chọn " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/nhom-tuy-chon/retrash/{id}")
	public String retrashoptionGroup(@PathVariable int id, ModelMap modelMap,
			final RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			optiongroupsService.retrash(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã bỏ xóa tạm thời nhóm tùy chọn " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/nhom-tuy-chon/status/{id}")
	public String onOffoptionGroup(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			optiongroupsService.onOff(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã thay đổi trạng thái nhóm tùy chọn " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/nhom-tuy-chon/add")
	public ModelAndView viewAdd(@ModelAttribute("opgroup") OptiongroupsEntity opgroup) {
		_mvShare.setViewName("admin/optiongroup/addoptiongroup");
		return _mvShare;
	}

	@PostMapping(value = "/quan-tri/nhom-tuy-chon/save", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String saveoptionGroup(HttpSession session, HttpServletRequest request,
			@ModelAttribute("opgroup") OptiongroupsEntity opgroup, ModelMap modelMap,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		boolean check = true;
		if (optiongroupsService.isNameExists(opgroup.getOptiongroupname())) {
			redirectAttributes.addFlashAttribute("msgName", "Tên trang trình chiếu đã tồn tại");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", opgroup);
			return "redirect:/quan-tri/nhom-tuy-chon/add";
		}
		try {
			opgroup.setCreated_at(new Date());
			opgroup.setUpdated_at(new Date());
			opgroup.setUpdated_by(loginInfo.getUser_id());
			opgroup.setCreated_by(loginInfo.getUser_id());
			opgroup.setMetadesc(request.getParameter("metadesc"));
			optiongroupsService.addOptionGroup(opgroup);
			redirectAttributes.addFlashAttribute("msg", "Thêm thành công!");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã thêm nhóm tùy chọn " + opgroup.getOptiongroupname());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thêm không thành công");
		}
		return "redirect:/quan-tri/nhom-tuy-chon";
	}

	@RequestMapping(value = "/quan-tri/nhom-tuy-chon/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editoptionGroup(@ModelAttribute("opgroup") OptiongroupsEntity opgroup, @PathVariable int id) {
		opgroup.setOptiongroups_id(id);
		OptiongroupsEntity optiongroupitem = optiongroupsService.findOptionGroupById(opgroup);
		_mvShare.addObject("optiongroupitem", optiongroupitem);
		_mvShare.setViewName("admin/optiongroup/editoptiongroup");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/nhom-tuy-chon/editsave", method = RequestMethod.POST)
	public String editsaveoptionGroup(HttpServletRequest request, HttpSession session,
			@ModelAttribute("opgroup") OptiongroupsEntity opgroup, ModelMap modelMap,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		boolean check = true;
		if (optiongroupsService.isNameExists(opgroup.getOptiongroupname(), opgroup.getOptiongroups_id())) {
			redirectAttributes.addFlashAttribute("msgName", "Tên trang trình chiếu đã tồn tại");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", opgroup);
			return "redirect:/quan-tri/nhom-tuy-chon/edit/" + opgroup.getOptiongroups_id();
		}
		try {
			opgroup.setUpdated_at(new Date());
			opgroup.setUpdated_by(loginInfo.getUser_id());
			opgroup.setMetadesc(request.getParameter("metadesc"));
			optiongroupsService.updateOptionGroup(opgroup);
			redirectAttributes.addFlashAttribute("msg", "Cập nhật thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã thêm nhóm tùy chọn " + opgroup.getOptiongroups_id());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Cập nhật không thành công");
		}
		return "redirect:/quan-tri/nhom-tuy-chon";
	}

}
