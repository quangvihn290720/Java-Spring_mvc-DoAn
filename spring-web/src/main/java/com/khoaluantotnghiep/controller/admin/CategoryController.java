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
import com.khoaluantotnghiep.entity.CategoryEntity;
import com.khoaluantotnghiep.entity.NoteEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.CategoryServiceImpl;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;

@Controller(value = "cateControllerOfAdmin")
public class CategoryController extends BaseController {
	@Autowired
	CategoryServiceImpl categoryService;
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	NoteServiceImpl noteService;
	private int totalDataPage = 5;

	@GetMapping("/quan-tri/danh-muc")
	public ModelAndView cate() {
		int totalData = categoryService.findAllCate().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("catePaginate",
				categoryService.GetDataCategoryPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/category/category");
		return _mvShare;
	}

	@GetMapping("/quan-tri/danh-muc/{currentPage}")
	public ModelAndView cate(@PathVariable String currentPage) {
		int totalData = categoryService.findAllCate().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("catePaginate",
				categoryService.GetDataCategoryPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/category/category");
		return _mvShare;
	}

	@GetMapping("/quan-tri/danh-muc/thung-rac")
	public ModelAndView cateTrash() {
		int totalData = categoryService.findTrashCategory().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("cateTrashPaginate",
				categoryService.GetDataCategoryTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/category/trashcate");
		return _mvShare;
	}

	@GetMapping("/quan-tri/danh-muc/thung-rac/{currentPage}")
	public ModelAndView cateTrash(@PathVariable String currentPage) {
		int totalData = categoryService.findTrashCategory().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("cateTrashPaginate",
				categoryService.GetDataCategoryTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/category/trashcate");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/danh-muc/delete/{id}")
	public String deleteCate(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			categoryService.deleteCategory(id);
			redirectAttributes.addFlashAttribute("msg", "X??a th??nh c??ng!");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? x??a v??nh vi???n danh m???c " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "X??a kh??ng th??nh c??ng!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/danh-muc/trash/{id}")
	public String delCate(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			categoryService.deltrash(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "X??a v??o th??ng r??c th??nh c??ng!");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin x??a t???m th???i danh m???c " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "X??a v??o th??ng r??c kh??ng th??nh c??ng!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/danh-muc/retrash/{id}")
	public String retrashCate(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			categoryService.retrash(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao t??c th??nh c??ng!");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin b??? x??a t???m th???i danh m???c " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao t??c kh??ng th??nh c??ng!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping("/quan-tri/danh-muc/status/{id}")
	public String onOffCate(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			categoryService.onOffCategory(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao t??c th??nh c??ng!");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin thay ?????i tr???ng th??i danh m???c " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao t??c kh??ng th??nh c??ng!");
		}

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/danh-muc/add")
	public ModelAndView addBanner(@ModelAttribute("category") CategoryEntity category) {
		_mvShare.setViewName("admin/category/addcate");
		return _mvShare;
	}

	@PostMapping(value = "/quan-tri/danh-muc/save", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String saveCate(HttpSession session, HttpServletRequest request,
			@ModelAttribute("category") CategoryEntity category, ModelMap modelMap,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		boolean check = true;
		if (categoryService.isNameExists(category.getName())) {
			redirectAttributes.addFlashAttribute("msgName", "T??n lo???i s???n ph???m ???? t???n t???i");
			check = false;
		}
		if (categoryService.isSlugExists(category.getSlug())) {
			redirectAttributes.addFlashAttribute("msgSlug", "Slug ???? t???n t???i");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", category);
			return "redirect:/quan-tri/danh-muc/add";
		}

		try {
			category.setCreated_at(new Date());
			category.setUpdated_at(new Date());
			category.setCreated_by(loginInfo.getUser_id());
			category.setUpdated_by(loginInfo.getUser_id());
			category.setMetadesc(request.getParameter("metadesc"));
			categoryService.addCategory(category);
			redirectAttributes.addFlashAttribute("msg", "Th??m th??nh c??ng!");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin th??m danh m???c m???i: " + category.getName());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Th??m kh??ng th??nh c??ng!");
		}
		return "redirect:/quan-tri/danh-muc";
	}

	@RequestMapping(value = "/quan-tri/danh-muc/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editCate(@ModelAttribute("category") CategoryEntity category, @PathVariable int id) {
		CategoryEntity categoryEntity = categoryService.findCategoryById(category);
		_mvShare.addObject("cateitem", categoryEntity);
		_mvShare.setViewName("admin/category/editcate");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/danh-muc/editsave", method = RequestMethod.POST)
	public String editsaveCate(HttpSession session, HttpServletRequest request,
			@ModelAttribute("category") CategoryEntity category, ModelMap modelMap,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		boolean check = true;
		if (categoryService.isNameExists(category.getName(), category.getId())) {
			redirectAttributes.addFlashAttribute("msgName", "T??n lo???i s???n ph???m ???? t???n t???i");
			check = false;
		}
		if (categoryService.isSlugExists(category.getSlug(), category.getId())) {
			redirectAttributes.addFlashAttribute("msgSlug", "Slug ???? t???n t???i");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", category);
			return "redirect:/quan-tri/danh-muc/edit/" + category.getId();
		}

		try {
			category.setMetadesc(request.getParameter("metadesc"));
			category.setUpdated_at(new Date());
			category.setUpdated_by(loginInfo.getUser_id());
			categoryService.updateCategory(category);
			redirectAttributes.addFlashAttribute("msg", "C???p nh???t th??nh c??ng");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ch???nh s???a danh m???c " + category.getId());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "C???p nh???t kh??ng th??nh c??ng");
		}
		return "redirect:/quan-tri/danh-muc";
	}

}
