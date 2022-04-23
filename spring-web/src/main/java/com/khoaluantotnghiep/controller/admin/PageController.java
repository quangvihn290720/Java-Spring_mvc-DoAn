package com.khoaluantotnghiep.controller.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.khoaluantotnghiep.dto.PaginateDTO;
import com.khoaluantotnghiep.entity.NoteEntity;
import com.khoaluantotnghiep.entity.PageEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.PageServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;
import com.khoaluantotnghiep.service.impl.TopicServiceImpl;

@Controller(value = "pageControllerOfAdmin")
public class PageController extends BaseController {
	@Autowired
	PageServiceImpl pageService;
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	TopicServiceImpl topicService;
	@Autowired
	NoteServiceImpl noteService;

	private int totalDataPage = 5;

	@RequestMapping(value = "/quan-tri/trang-don", method = RequestMethod.GET)
	public ModelAndView page() {
		int totalData = pageService.findAllPage().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("pagePaginate", pageService.GetDataPagePaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/page/page");
		return _mvShare;

	}

	@RequestMapping(value = "/quan-tri/trang-don/{currentPage}", method = RequestMethod.GET)
	public ModelAndView page(@PathVariable String currentPage) {
		int totalData = pageService.findAllPage().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("pagePaginate", pageService.GetDataPagePaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/page/page");
		return _mvShare;

	}

	@RequestMapping(value = "/quan-tri/trang-don/add", method = RequestMethod.GET)
	public ModelAndView addPage(@ModelAttribute("page") PageEntity page) {
		_mvShare.addObject("listtopic", topicService.findAllTopicShow());
		_mvShare.setViewName("admin/page/addpage");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/trang-don/save", method = RequestMethod.POST)
	public String savePage(HttpSession session, HttpServletRequest request, @ModelAttribute("page") PageEntity page,
			ModelMap modelMap, @RequestParam(value = "image", required = false) MultipartFile photo,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		page.setPage_detail(request.getParameter("page_detail"));
		page.setPage_metadesc(request.getParameter("page_metadesc"));
		boolean check = true;
		if (pageService.isTitledExists(page.getPage_title())) {
			redirectAttributes.addFlashAttribute("msgTitle", "Tiêu đề đã tồn tại");
			check = false;
		}
		if (pageService.isSlugExists(page.getPage_slug())) {
			redirectAttributes.addFlashAttribute("msgSlug", "Slug đã tồn tại");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", page);
			return "redirect:/quan-tri/trang-don/add";
		}
		try {
			page.setPage_img(saveFile(photo));
			page.setCreated_by(loginInfo.getUser_id());
			page.setUpdated_by(loginInfo.getUser_id());
			page.setCreated_at(new Date());
			page.setUpdated_at(new Date());
			pageService.addPage(page);
			redirectAttributes.addFlashAttribute("msg", "Thêm thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã thêm trang đơn " + page.getPage_title());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thêm không thành công");
		}
		return "redirect:/quan-tri/trang-don";
	}

	@RequestMapping(value = "quan-tri/trang-don/get", method = RequestMethod.GET)
	public ModelAndView getPage(@RequestParam int page_id) {
		PageEntity page = pageService.findPageById(page_id);
		_mvShare.addObject("page", page);
		_mvShare.setViewName("admin/page/addpage");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/trang-don/edit/{page_id}", method = RequestMethod.GET)
	public ModelAndView editPage(@ModelAttribute("page") PageEntity page, @PathVariable int page_id) {
		PageEntity pageitem = pageService.findPageById(page_id);
		_mvShare.addObject("pageitem", pageitem);
		_mvShare.addObject("listtopic", topicService.findAllTopicShow());
		_mvShare.setViewName("admin/page/editpage");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/trang-don/editsave", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String editsavePage(HttpSession session, HttpServletRequest request, @ModelAttribute("page") PageEntity page,
			ModelMap modelMap, @RequestParam(value = "image", required = false) MultipartFile photo,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");

		boolean check = true;
		if (pageService.isTitledExists(page.getPage_title(), page.getPage_id())) {

			redirectAttributes.addFlashAttribute("msgTitle", "Tiêu đề đã tồn tại");
			check = false;
		}
		if (pageService.isSlugExists(page.getPage_slug(), page.getPage_id())) {
			redirectAttributes.addFlashAttribute("msgSlug", "Slug đã tồn tại");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", page);
			return "redirect:/quan-tri/trang-don/edit/" + page.getPage_id();
		}
		try {
			page.setPage_detail(request.getParameter("page_detail"));
			page.setPage_metadesc(request.getParameter("page_metadesc"));
			page.setPage_img(saveFile(photo));
			page.setUpdated_at(new Date());
			page.setUpdated_by(loginInfo.getUser_id());
			pageService.updatePage(page);
			redirectAttributes.addFlashAttribute("msg", "Cập nhật thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã chỉnh sửa trang đơn " + page.getPage_id());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Cập nhật Không thành công");
		}

		return "redirect:/quan-tri/trang-don";
	}

	@RequestMapping(value = "/quan-tri/trang-don/status/{page_id}", method = RequestMethod.GET)
	public String onOffPage(@PathVariable int page_id, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			pageService.onOffPage(page_id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã thay đổi trang đơn " + page_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/trang-don/trash/{page_id}", method = RequestMethod.GET)
	public String delTrashPage(@PathVariable int page_id, HttpServletRequest request,
			final RedirectAttributes redirectAttributes, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			pageService.delTrash(page_id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã xóa tạm thời trang đơn " + page_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công");
		}

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/trang-don/thung-rac", method = RequestMethod.GET)
	public ModelAndView trashPage() {
		int totalData = pageService.findTrashPage().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("pageTrashPaginate",
				pageService.GetDataPageTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/page/trashpage");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/trang-don/thung-rac/{currentPage}", method = RequestMethod.GET)
	public ModelAndView trashPage(@PathVariable String currentPage) {
		int totalData = pageService.findTrashPage().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("pageTrashPaginate",
				pageService.GetDataPageTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/page/trashpage");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/trang-don/retrash/{page_id}", method = RequestMethod.GET)
	public String reTrash(@PathVariable int page_id, HttpServletRequest request,
			final RedirectAttributes redirectAttributes, HttpSession session) {

		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			pageService.reTrash(page_id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã bỏ xóa tạm thời trang đơn " + page_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/trang-don/deletepage/{page_id}", method = RequestMethod.GET)
	public String deletaPage(@PathVariable int page_id, HttpServletRequest request,
			final RedirectAttributes redirectAttributes, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			pageService.deletePage(page_id);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã bỏ xóa tạm thời trang đơn " + page_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
