package com.khoaluantotnghiep.controller.admin;

import java.util.Date;
import java.util.Map;

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
import com.khoaluantotnghiep.entity.OptionsEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.OptiongroupsServiceImpl;
import com.khoaluantotnghiep.service.impl.OptionsServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;

@Controller(value = "optionControllerOfAdmin")
public class OptionController extends BaseController {
	@Autowired
	OptionsServiceImpl optionService;
	@Autowired
	OptiongroupsServiceImpl opgroupService;
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	NoteServiceImpl noteService;
	private int totalDataPage = 5;

	@GetMapping(value = "/quan-tri/tuy-chon")
	public ModelAndView viewoption() {
		Map<Integer, String> mapOpGroup = opgroupService.mapOpgroup();
		_mvShare.addObject("mapOpGroup", mapOpGroup);
		int totalData = optionService.findAllOption().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("optionPaginate",
				optionService.GetDataOptionPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/option/option");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/tuy-chon/{currentPage}")
	public ModelAndView viewoption(@PathVariable String currentPage) {
		Map<Integer, String> mapOpGroup = opgroupService.mapOpgroup();
		_mvShare.addObject("mapOpGroup", mapOpGroup);
		int totalData = optionService.findAllOption().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("optionPaginate",
				optionService.GetDataOptionPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/option/option");
		return _mvShare;
	}

	@GetMapping("/quan-tri/tuy-chon/thung-rac")
	public ModelAndView optionTrash() {
		Map<Integer, String> mapOpGroup = opgroupService.mapOpgroup();
		_mvShare.addObject("mapOpGroup", mapOpGroup);
		int totalData = optionService.findAllTrashOption().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("optionyTrashPaginate",
				optionService.GetDataOptionTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/option/trashoption");
		return _mvShare;
	}

	@GetMapping("/quan-tri/tuy-chon/thung-rac/{currentPage}")
	public ModelAndView optionTrash(@PathVariable String currentPage) {
		Map<Integer, String> mapOpGroup = opgroupService.mapOpgroup();
		_mvShare.addObject("mapOpGroup", mapOpGroup);
		int totalData = optionService.findAllTrashOption().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("optionyTrashPaginate",
				optionService.GetDataOptionTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/option/trashoption");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/tuy-chon/delete/{id}")
	public String deleteoptionGroup(@PathVariable int id, ModelMap modelMap,
			final RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			optionService.deleteOption(id);
			redirectAttributes.addFlashAttribute("msg", "Xóa thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã xóa tùy chọn " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Xóa không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/tuy-chon/trash/{id}")
	public String deltrashoptionGroup(@PathVariable int id, ModelMap modelMap,
			final RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			optionService.deltrash(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã xóa tạm thời tùy chọn " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/tuy-chon/retrash/{id}")
	public String retrashoptionGroup(@PathVariable int id, ModelMap modelMap,
			final RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			optionService.retrash(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã bỏ xóa tạm thời tùy chọn " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/tuy-chon/status/{id}")
	public String onOffoptionGroup(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			optionService.onOff(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã thay đổi trạng thái tùy chọn " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/tuy-chon/add")
	public ModelAndView viewAdd(@ModelAttribute("option") OptionsEntity option) {
		_mvShare.addObject("opgroup", opgroupService.findAllOptionGroup());
		_mvShare.setViewName("admin/option/addoption");
		return _mvShare;
	}

	@PostMapping(value = "/quan-tri/tuy-chon/save", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String saveOption(HttpSession session, HttpServletRequest request,
			@ModelAttribute("option") OptionsEntity option, ModelMap modelMap,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		try {
			option.setMetadesc(request.getParameter("metadesc"));
			option.setCreated_at(new Date());
			option.setUpdated_at(new Date());
			option.setUpdated_by(loginInfo.getUser_id());
			option.setCreated_by(loginInfo.getUser_id());
			if (optionService.findOptionByName(option) != null) {
				redirectAttributes.addFlashAttribute("msgName", "Tên tùy chọn đã tồn tại");
				redirectAttributes.addFlashAttribute("oldvalue", option);
				return "redirect:/quan-tri/tuy-chon/add";
			}
			option.setMetadesc(request.getParameter("metadesc"));
			optionService.addOption(option);
			redirectAttributes.addFlashAttribute("msg", "Thêm thành công!");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã thêm tùy chọn " + option.getOptionname());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thêm không thành công");
		}
		return "redirect:/quan-tri/tuy-chon";
	}

	@RequestMapping(value = "/quan-tri/tuy-chon/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editOption(@ModelAttribute("option") OptionsEntity option, @PathVariable int id) {
		try {
			option.setOptions_id(id);
			OptionsEntity options = optionService.findOptionById(option);
			_mvShare.addObject("options", options);
			_mvShare.addObject("opgroup", opgroupService.findAllOptionGroup());
		} catch (Exception e) {
			_mvShare.addObject("msgfail", "Không tồn tại!");
			_mvShare.setViewName("admin/option/option");
			return _mvShare;
		}
		_mvShare.setViewName("admin/option/editoption");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/tuy-chon/editsave", method = RequestMethod.POST)
	public String editsaveOption(HttpServletRequest request, HttpSession session,
			@ModelAttribute("option") OptionsEntity option, ModelMap modelMap,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		try {
			option.setUpdated_at(new Date());
			option.setUpdated_by(loginInfo.getUser_id());
			option.setMetadesc(request.getParameter("metadesc"));
			if (optionService.findOtherOptionByName(option) != null) {
				redirectAttributes.addFlashAttribute("msgName", "Tên tùy chọn đã tồn tại");
				redirectAttributes.addFlashAttribute("oldvalue", option);
				return "redirect:/quan-tri/tuy-chon/edit/" + option.getOptions_id();
			}
			optionService.updateOption(option);
			redirectAttributes.addFlashAttribute("msg", "Cập nhật thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã chỉnh sửa tùy chọn " + option.getOptions_id());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Cập nhật không thành công");
		}
		return "redirect:/quan-tri/tuy-chon";
	}

}
