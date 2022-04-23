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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.khoaluantotnghiep.dto.PaginateDTO;
import com.khoaluantotnghiep.entity.BannerEntity;
import com.khoaluantotnghiep.entity.NoteEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.BannerServiceImpl;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;

@Controller(value = "bannerControllerOfAdmin")
public class BannerController extends BaseController {
	@Autowired
	BannerServiceImpl bannerService;
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	NoteServiceImpl noteService;
	private int totalDataPage = 5;

	@RequestMapping(value = "/quan-tri/web/anh-bia", method = RequestMethod.GET)
	public ModelAndView Banner(ModelMap modelMap) {
		int totalData = bannerService.findAllBanner().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("bannerPaginate",
				bannerService.GetDataBannerPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/banner/banner");
		return _mvShare;
	}

	@GetMapping("/quan-tri/web/anh-bia/{currentPage}")
	public ModelAndView BannerPage(@PathVariable String currentPage) {
		int totalData = bannerService.findAllBanner().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("bannerPaginate",
				bannerService.GetDataBannerPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/banner/banner");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/web/anh-bia/add", method = RequestMethod.GET)
	public ModelAndView addBanner(@ModelAttribute("banner") BannerEntity banner) {
		_mvShare.setViewName("admin/banner/addbanner");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/web/anh-bia/save", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String saveBanner(@ModelAttribute("banner") BannerEntity banner, HttpSession session, ModelMap modelMap,
			@RequestParam(value = "image", required = false) MultipartFile photo,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		boolean check = true;
		if (bannerService.isNameExists(banner.getBanner_name())) {
			redirectAttributes.addFlashAttribute("msgName", "Tên banner đã tồn tại");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", banner);
			return "redirect:/quan-tri/web/anh-bia/add";
		}
		try {
			banner.setBanner_img(saveFile(photo));
			banner.setCreated_at(new Date());
			banner.setUpdated_at(new Date());
			banner.setCreated_by(loginInfo.getUser_id());
			banner.setUpdated_by(loginInfo.getUser_id());
			bannerService.addBanner(banner);
			redirectAttributes.addFlashAttribute("msg", "Thêm thành công!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thêm không thành công!");
		}
		NoteEntity noteEntity = new NoteEntity();
		noteEntity.setContent("Admin đã thêm ảnh bìa " + banner.getBanner_name());
		noteEntity.setCreated_at(new Date());
		noteEntity.setCreated_by(loginInfo.getUser_id());
		noteService.addNote(noteEntity);
		return "redirect:/quan-tri/web/anh-bia";
	}

	@RequestMapping(value = "/quan-tri/web/anh-bia/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editBanner(@ModelAttribute("banner") BannerEntity banner, @PathVariable int id) {
		BannerEntity banneritem = bannerService.findBannerById(banner);
		_mvShare.addObject("banneritem", banneritem);
		_mvShare.setViewName("admin/banner/editbanner");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/web/anh-bia/thung-rac", method = RequestMethod.GET)
	public ModelAndView trashBanner(@ModelAttribute("banner") BannerEntity banner) {
		int totalData = bannerService.findTrashBanner().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("bannerTrashPaginate",
				bannerService.GetDataBannerTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/banner/trashbanner");
		return _mvShare;
	}

	@GetMapping("/quan-tri/web/anh-bia/thung-rac/{currentPage}")
	public ModelAndView trashBannerPage(@PathVariable String currentPage,
			@ModelAttribute("banner") BannerEntity banner) {
		int totalData = bannerService.findTrashBanner().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("bannerTrashPaginate",
				bannerService.GetDataBannerTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/banner/trashbanner");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/web/anh-bia/editsave", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String editsaveBanner(@ModelAttribute("banner") BannerEntity banner, HttpSession session, ModelMap modelMap,
			@RequestParam(value = "image", required = false) MultipartFile photo,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		boolean check = true;
		if (bannerService.isNameExists(banner.getBanner_name(), banner.getId())) {
			redirectAttributes.addFlashAttribute("msgName", "Tên banner đã tồn tại");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", banner);
			return "redirect:/quan-tri/web/anh-bia/edit/" + banner.getId();
		}
		try {
			banner.setBanner_img(saveFile(photo));
			banner.setUpdated_at(new Date());
			banner.setUpdated_by(loginInfo.getUser_id());
			bannerService.updateBanner(banner);
			redirectAttributes.addFlashAttribute("msg", "Cập nhật thành công!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Cập nhật không thành công!");
		}
		NoteEntity noteEntity = new NoteEntity();
		noteEntity.setContent("Admin chỉnh sửa ảnh bìa " + banner.getId());
		noteEntity.setCreated_at(new Date());
		noteEntity.setCreated_by(loginInfo.getUser_id());
		noteService.addNote(noteEntity);
		return "redirect:/quan-tri/web/anh-bia";
	}

	@RequestMapping(value = "/quan-tri/web/anh-bia/deletebanner/{id}", method = RequestMethod.GET)
	public String deleteTopic(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		try {
			bannerService.deleteBanner(id);
			redirectAttributes.addFlashAttribute("msg", "Xóa thành công!");
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin xóa vĩnh viễn ảnh bìa " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Xóa không thành công!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/web/anh-bia/trash/{id}", method = RequestMethod.GET)
	public String delBanner(@PathVariable int id, ModelMap modelMap, HttpSession session,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			bannerService.deltrashBanner(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Xóa vào thùng rác thành công!");
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin xóa tạm thời ảnh bìa " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Xóa vào thùng rác không thành công!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/web/anh-bia/retrash/{id}", method = RequestMethod.GET)
	public String retrashBanner(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			bannerService.retrashBanner(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công!");
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin bỏ xóa tạm thời ảnh bìa " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/web/anh-bia/status/{id}", method = RequestMethod.GET)
	public String onOffBanner(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			bannerService.onOffBanner(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công!");
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin thay đổi trạng thái ảnh bìa " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

}
