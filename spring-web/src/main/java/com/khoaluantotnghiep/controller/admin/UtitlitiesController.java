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
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.entity.UtilitiesEntity;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;
import com.khoaluantotnghiep.service.impl.ProductServiceImpl;
import com.khoaluantotnghiep.service.impl.UtilitiesServiceImpl;

@Controller(value = "utitlitiesControllerOfAdmin")
public class UtitlitiesController extends BaseController {
	private int totalDataPage = 5;
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	UtilitiesServiceImpl utilitiesService;
	@Autowired
	ProductServiceImpl productService;
	@Autowired
	NoteServiceImpl noteService;

	@GetMapping(value = "/quan-tri/tien-ich")
	public ModelAndView Utilities() {
		Map<Integer, String> mapProd = productService.mapProd();
		_mvShare.addObject("mapProd", mapProd);
		int totalData = utilitiesService.findAllUtilities().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("utilPaginate",
				utilitiesService.GetDataUtilitiesPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/ultilities/ultilities");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/tien-ich/{currentPage}")
	public ModelAndView Utilities(@PathVariable String currentPage) {
		Map<Integer, String> mapProd = productService.mapProd();
		_mvShare.addObject("mapProd", mapProd);
		int totalData = utilitiesService.findAllUtilities().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("utilPaginate",
				utilitiesService.GetDataUtilitiesPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/ultilities/ultilities");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/tien-ich/add")
	public ModelAndView addUtil(@ModelAttribute("utilities") UtilitiesEntity utilities) {
		_mvShare.addObject("product", productService.findAllProduct());
		_mvShare.setViewName("admin/ultilities/addultilities");
		return _mvShare;
	}

	@PostMapping(value = "/quan-tri/tien-ich/save", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String saveAddUtil(HttpSession session, HttpServletRequest request,
			@ModelAttribute("utilities") UtilitiesEntity utilities, final RedirectAttributes redirectAttributes,
			ModelMap modelMap) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		try {
			utilities.setCreated_at(new Date());
			utilities.setUpdated_at(new Date());
			utilities.setUpdated_by(loginInfo.getUser_id());
			utilities.setCreated_by(loginInfo.getUser_id());
			utilities.setMetadesc(request.getParameter("metadesc"));
			System.out.println(utilities.getProduct_id());
			System.out.println(utilities.getMetadesc());
			if (utilitiesService.findUtilityByName(utilities) != null) {
				redirectAttributes.addFlashAttribute("msgName", "Tên tiện ích đã tồn tại");
				redirectAttributes.addFlashAttribute("oldvalue", utilities);
				return "redirect:/quan-tri/tien-ich/add";
			}
			utilitiesService.addUtilities(utilities);
			redirectAttributes.addFlashAttribute("msg", "Thêm thành công!");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã thêm tiện ích " + utilities.getUtilitiesname() + " cho sản phẩm "
					+ utilities.getProduct_id());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thêm không thành công!");
		}
		return "redirect:/quan-tri/tien-ich";
	}

	@GetMapping(value = "/quan-tri/tien-ich/thung-rac")
	public ModelAndView trashUtilities() {
		int totalData = utilitiesService.findAllTrashUtilities().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("utilTrashPaginate",
				utilitiesService.GetDataUtilitiesTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/ultilities/trashultilities");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/tien-ich/thung-rac/{currentPage}")
	public ModelAndView trashUtilities(@PathVariable String currentPage) {
		int totalData = utilitiesService.findAllTrashUtilities().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("utilTrashPaginate",
				utilitiesService.GetDataUtilitiesTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/ultilities/trashultilities");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/tien-ich/delete/{id}", method = RequestMethod.GET)
	public String deleteUtilities(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			utilitiesService.deleteUtilities(id);
			redirectAttributes.addFlashAttribute("msg", "Xóa thành công!");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã xóa vĩnh viễn tiện ích " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/tien-ich/trash/{id}", method = RequestMethod.GET)
	public String delUtilities(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			utilitiesService.deltrash(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tácthành công!");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã xóa tạm thời tiện ích " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công!");
		}

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/tien-ich/retrash/{id}", method = RequestMethod.GET)
	public String retrashUtilities(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			utilitiesService.retrash(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công!");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã bỏ xóa tạm thời tiện ích " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/tien-ich/status/{id}", method = RequestMethod.GET)
	public String onOffUtilities(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			utilitiesService.onOffTopic(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công!");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã thay đổi trạng thái tiện ích " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/tien-ich/edit/{id}", method = RequestMethod.GET)
	public String editUtilities(@ModelAttribute("utilities") UtilitiesEntity utilities, @PathVariable int id,
			ModelMap modelMap) {
		utilities.setUtilities_id(id);
		UtilitiesEntity util = utilitiesService.findUtilById(utilities);
		modelMap.put("utilEntity", util);
		Map<Integer, String> mapProd = productService.mapProd();
		modelMap.put("mapProd", mapProd);
		modelMap.put("product", productService.findAllProduct());
		return "admin/ultilities/editultilities";
	}

	@RequestMapping(value = "/quan-tri/tien-ich/editsave", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String editsaveSlide(HttpServletRequest request, @ModelAttribute("utilities") UtilitiesEntity utilities,
			ModelMap modelMap, final RedirectAttributes redirectAttributes, HttpSession session) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		try {
			utilities.setMetadesc(request.getParameter("metadesc"));
//			utilities.setProduct_id(Integer.parseInt(request.getParameter("product_id")));
			System.out.println(utilities.getProduct_id());
			utilities.setUpdated_at(new Date());
			utilities.setUpdated_by(loginInfo.getUser_id());
			if (utilitiesService.findOtherUtilityByName(utilities) != null) {
				redirectAttributes.addFlashAttribute("msgName", "Tên tùy chọn đã tồn tại");
				redirectAttributes.addFlashAttribute("oldvalue", utilities);
				return "redirect:/quan-tri/tien-ich/edit/" + utilities.getUtilities_id();
			}
			utilitiesService.updateUtilities(utilities);
			redirectAttributes.addFlashAttribute("msg", "Cập nhật thành công");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã thay đổi trạng thái tiện ích " + utilities.getUtilities_id());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Cập nhật không thành công");
		}
		return "redirect:/quan-tri/tien-ich";
	}

}
