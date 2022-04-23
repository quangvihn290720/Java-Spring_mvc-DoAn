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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.khoaluantotnghiep.dto.PaginateDTO;
import com.khoaluantotnghiep.entity.NoteEntity;
import com.khoaluantotnghiep.entity.ProductImageEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;
import com.khoaluantotnghiep.service.impl.ProductImageServiceImpl;
import com.khoaluantotnghiep.service.impl.ProductServiceImpl;

@Controller(value = "prodImgControllerOfAdmin")
public class ProductImageController extends BaseController {
	@Autowired
	ProductImageServiceImpl prodImgService;
	@Autowired
	ProductServiceImpl prodService;
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	NoteServiceImpl noteService;
	private int totalDataPage = 5;

	@GetMapping("/quan-tri/hinh-anh-san-pham")
	public ModelAndView viewProImg() {
		Map<Integer, String> mapProd = prodService.mapProd();
		_mvShare.addObject("mapProd", mapProd);
		int totalData = prodImgService.findAllProductImage().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("prodimgPaginate",
				prodImgService.GetDataProductImagePaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/prodimg/prodimg");
		return _mvShare;
	}

	@GetMapping("/quan-tri/hinh-anh-san-pham/{page}")
	public ModelAndView viewProImg(@PathVariable String page) {
		Map<Integer, String> mapProd = prodService.mapProd();
		_mvShare.addObject("mapProd", mapProd);
		int totalData = prodImgService.findAllProductImage().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, Integer.parseInt(page));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("prodimgPaginate",
				prodImgService.GetDataProductImagePaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/prodimg/prodimg");
		return _mvShare;
	}

	@GetMapping("/quan-tri/hinh-anh-san-pham/thung-rac")
	public ModelAndView viewProImgTrash() {
		Map<Integer, String> mapProd = prodService.mapProd();
		_mvShare.addObject("mapProd", mapProd);
		int totalData = prodImgService.findTrashProductImage().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("prodimgTrashPaginate",
				prodImgService.GetDataProductImageTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/prodimg/trashprodimg");
		return _mvShare;
	}

	@GetMapping("/quan-tri/hinh-anh-san-pham/thung-rac/{page}")
	public ModelAndView viewProImgTrash(@PathVariable String page) {
		Map<Integer, String> mapProd = prodService.mapProd();
		_mvShare.addObject("mapProd", mapProd);
		int totalData = prodImgService.findTrashProductImage().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, Integer.parseInt(page));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("prodimgTrashPaginate",
				prodImgService.GetDataProductImageTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/prodimg/trashprodimg");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/hinh-anh-san-pham/add")
	public ModelAndView add(@ModelAttribute("prodimg") ProductImageEntity prodimg) {
		if (prodService.findAllProduct() != null && prodService.findAllProduct().size() != 0) {
			_mvShare.addObject("products", prodService.findAllProduct());
		}
		_mvShare.setViewName("admin/prodimg/addprodimg");
		return _mvShare;
	}

	@PostMapping(value = "/quan-tri/hinh-anh-san-pham/save", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String saveadd(HttpSession session, HttpServletRequest request,
			@ModelAttribute("prodimg") ProductImageEntity prodimg, ModelMap modelMap,
			@RequestParam(value = "image", required = false) MultipartFile photo,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		try {
			prodimg.setImg(saveFile(photo));
			prodimg.setCreated_by(loginInfo.getUser_id());
			prodimg.setUpdated_by(loginInfo.getUser_id());
			prodimg.setCreated_at(new Date());
			prodimg.setUpdated_at(new Date());
			prodImgService.add(prodimg);
			redirectAttributes.addFlashAttribute("msg", "Thêm thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã thêm hình ảnh sản phẩm cho sản phẩm" + prodimg.getProduct_id());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thêm không thành công");
		}
		return "redirect:/quan-tri/hinh-anh-san-pham";
	}

	@RequestMapping(value = "/quan-tri/hinh-anh-san-pham/status/{id}", method = RequestMethod.GET)
	public String onOff(@PathVariable int id, final RedirectAttributes redirectAttributes, HttpSession session) {
		try {

			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			prodImgService.onOffProductImage(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã thay đổi trạng thái hình ảnh sản phẩm cho sản phẩm" + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công");
		}
		return "redirect:/quan-tri/hinh-anh-san-pham";
	}

	@RequestMapping(value = "/quan-tri/hinh-anh-san-pham/trash/{id}", method = RequestMethod.GET)
	public String delTrash(@PathVariable int id, final RedirectAttributes redirectAttributes, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			prodImgService.deltrashProductImage(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã xóa tạm thời hình ảnh sản phẩm " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công");
		}
		return "redirect:/quan-tri/hinh-anh-san-pham";
	}

	@RequestMapping(value = "/quan-tri/hinh-anh-san-pham/retrash/{id}", method = RequestMethod.GET)
	public String reTrash(@PathVariable int id, final RedirectAttributes redirectAttributes, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			prodImgService.retrashProductImage(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã bỏ xóa tạm thời hình ảnh sản phẩm " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công");
		}
		return "redirect:/quan-tri/hinh-anh-san-pham/thung-rac";
	}

	@RequestMapping(value = "/quan-tri/hinh-anh-san-pham/delete/{id}", method = RequestMethod.GET)
	public String deleta(@PathVariable int id, final RedirectAttributes redirectAttributes, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			prodImgService.deleteProductImage(id);
			redirectAttributes.addFlashAttribute("msg", "Xóa thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã xóa vĩnh viễn hình ảnh sản phẩm " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Xóa không thành công");
		}
		return "redirect:/quan-tri/hinh-anh-san-pham/thung-rac";
	}
}
