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
import com.khoaluantotnghiep.entity.ProductOptionsEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.OptiongroupsServiceImpl;
import com.khoaluantotnghiep.service.impl.OptionsServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;
import com.khoaluantotnghiep.service.impl.ProductOptionsServiceImpl;
import com.khoaluantotnghiep.service.impl.ProductServiceImpl;

@Controller(value = "productOptionControllerOfAdmin")
public class ProductOptionController extends BaseController {
	@Autowired
	ProductOptionsServiceImpl prodoptionService;
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	ProductServiceImpl productService;
	@Autowired
	OptionsServiceImpl optionService;
	@Autowired
	OptiongroupsServiceImpl optiongroupsService;
	@Autowired
	NoteServiceImpl noteService;
	private int totalDataPage = 5;

	@GetMapping(value = "/quan-tri/tuy-chon-san-pham")
	public ModelAndView prodOptionView() {
		Map<Integer, String> mapProd = productService.mapProd();
		_mvShare.addObject("mapProd", mapProd);
		Map<Integer, String> mapOpGroup = optiongroupsService.mapOpgroup();
		_mvShare.addObject("mapOpGroup", mapOpGroup);
		Map<Integer, String> mapOption = optionService.mapOption();
		_mvShare.addObject("mapOption", mapOption);
		int totalData = prodoptionService.findAllProdOption().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("prodoptionPaginate",
				prodoptionService.GetDataProdOptionPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/prodoption/prodoption");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/tuy-chon-san-pham/{currentPage}")
	public ModelAndView prodOptionView(@PathVariable String currentPage) {
		Map<Integer, String> mapProd = productService.mapProd();
		_mvShare.addObject("mapProd", mapProd);
		Map<Integer, String> mapOpGroup = optiongroupsService.mapOpgroup();
		_mvShare.addObject("mapOpGroup", mapOpGroup);
		Map<Integer, String> mapOption = optionService.mapOption();
		_mvShare.addObject("mapOption", mapOption);
		int totalData = prodoptionService.findAllProdOption().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("prodoptionPaginate",
				prodoptionService.GetDataProdOptionPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/prodoption/prodoption");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/tuy-chon-san-pham/thung-rac")
	public ModelAndView trashProdOption() {
		Map<Integer, String> mapProd = productService.mapProd();
		_mvShare.addObject("mapProd", mapProd);
		Map<Integer, String> mapOpGroup = optiongroupsService.mapOpgroup();
		_mvShare.addObject("mapOpGroup", mapOpGroup);
		Map<Integer, String> mapOption = optionService.mapOption();
		_mvShare.addObject("mapOption", mapOption);
		int totalData = prodoptionService.findAllTrashProdOption().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("prodoptionTrashPaginate",
				prodoptionService.GetDataProdOptionTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/prodoption/trashprodoption");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/tuy-chon-san-pham/thung-rac/{currentPage}")
	public ModelAndView trashProdOption(@PathVariable String currentPage) {
		Map<Integer, String> mapProd = productService.mapProd();
		_mvShare.addObject("mapProd", mapProd);
		Map<Integer, String> mapOpGroup = optiongroupsService.mapOpgroup();
		_mvShare.addObject("mapOpGroup", mapOpGroup);
		Map<Integer, String> mapOption = optionService.mapOption();
		_mvShare.addObject("mapOption", mapOption);
		int totalData = prodoptionService.findAllTrashProdOption().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("prodoptionTrashPaginate",
				prodoptionService.GetDataProdOptionTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/prodoption/trashprodoption");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/tuy-chon-san-pham/delete/{id}")
	public String deleteProdOption(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			prodoptionService.deleteProdOption(id);
			redirectAttributes.addFlashAttribute("msg", "Xóa thành công!");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã xóa vĩnh viễn tùy chọn sản phẩm " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/tuy-chon-san-pham/trash/{id}")
	public String delProdOption(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			prodoptionService.deltrash(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tácthành công!");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã xóa tạm thời tùy chọn sản phẩm " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công!");
		}

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/tuy-chon-san-pham/retrash/{id}")
	public String retrashProdOption(@PathVariable int id, ModelMap modelMap,
			final RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			prodoptionService.retrash(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công!");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã bỏ xóa tạm thời tùy chọn sản phẩm " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/tuy-chon-san-pham/status/{id}")
	public String onOffProdOption(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			prodoptionService.onOffProdOption(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công!");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã sửa trạng thái tùy chọn sản phẩm " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/tuy-chon-san-pham/add")
	public ModelAndView addProductOption(@ModelAttribute("prodop") ProductOptionsEntity prodop) {
		_mvShare.addObject("product", productService.findAllProduct());
		_mvShare.addObject("option", optionService.findAllOption());
		_mvShare.setViewName("admin/prodoption/addprodoption");
		return _mvShare;
	}

	@PostMapping(value = "/quan-tri/tuy-chon-san-pham/save", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String saveAddProductOption(HttpSession session, HttpServletRequest request,
			@ModelAttribute("prodop") ProductOptionsEntity prodop, final RedirectAttributes redirectAttributes,
			ModelMap modelMap) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		prodop.setCreated_at(new Date());
		prodop.setUpdated_at(new Date());
		prodop.setUpdated_by(loginInfo.getUser_id());
		prodop.setCreated_by(loginInfo.getUser_id());
		int op = prodop.getOption_id();
		OptionsEntity optionsEntity = optionService.findOptionId(op);
		prodop.setOptiongroup_id(optionsEntity.getOptiongroup_id());
		try {
			prodoptionService.addProdOption(prodop);
			redirectAttributes.addFlashAttribute("msg", "Thêm thành công!");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã sửa trạng thái tùy chọn sản phẩm cho sản phẩm " + prodop.getProduct_id());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thêm không thành công!");
		}
		return "redirect:/quan-tri/tuy-chon-san-pham";
	}

	@RequestMapping(value = "/quan-tri/tuy-chon-san-pham/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editProductOption(@ModelAttribute("prodop") ProductOptionsEntity prodop, @PathVariable int id) {
		try {
			prodop.setProductoptions_id(id);
			ProductOptionsEntity prodopitem = prodoptionService.findProdOptionById(prodop);
			_mvShare.addObject("prodopitem", prodopitem);
			_mvShare.addObject("product", productService.findAllProduct());
			_mvShare.addObject("optiongroup", optiongroupsService.findAllOptionGroup());
			_mvShare.addObject("option", optionService.findAllOption());
		} catch (Exception e) {
			_mvShare.addObject("msgfail", "Không tồn tại!");
			_mvShare.setViewName("admin/prodoption/prodoption");
			return _mvShare;
		}
		_mvShare.setViewName("admin/prodoption/editprodoption");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/tuy-chon-san-pham/editsave", method = RequestMethod.POST)
	public String editsaveProductOption(HttpServletRequest request, HttpSession session,
			@ModelAttribute("prodop") ProductOptionsEntity prodop, ModelMap modelMap,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		prodop.setUpdated_at(new Date());
		prodop.setUpdated_by(loginInfo.getUser_id());
		prodop.setMetadesc(request.getParameter("metadesc"));
		int op = prodop.getOption_id();
		OptionsEntity optionsEntity = optionService.findOptionId(op);
		prodop.setOptiongroup_id(optionsEntity.getOptiongroup_id());
		try {
			prodoptionService.updateProdOption(prodop);
			redirectAttributes.addFlashAttribute("msg", "Cập nhật thành công");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã chỉnh sửa tùy chọn sản phẩm " + prodop.getProductoptions_id());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Cập nhật không thành công");
		}
		return "redirect:/quan-tri/tuy-chon-san-pham";
	}
}
