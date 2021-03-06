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
import com.khoaluantotnghiep.entity.SlideEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;
import com.khoaluantotnghiep.service.impl.SlideServiceImpl;

@Controller(value = "slideControllerOfAdmin")
public class SlideController extends BaseController {
	@Autowired
	SlideServiceImpl slideService;
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	NoteServiceImpl noteService;
	private int totalDataPage = 5;

	@RequestMapping(value = "/quan-tri/web/trinh-chieu", method = RequestMethod.GET)
	public ModelAndView Slide() {
		int totalData = slideService.findAllSlide().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("slidePaginate", slideService.GetDataSlidePaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/slide/slide");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/web/trinh-chieu/{currentPage}", method = RequestMethod.GET)
	public ModelAndView Slide(@PathVariable String currentPage) {
		int totalData = slideService.findAllSlide().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("slidePaginate", slideService.GetDataSlidePaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/slide/slide");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/web/trinh-chieu/add", method = RequestMethod.GET)
	public ModelAndView addSlide(@ModelAttribute("slide") SlideEntity slide) {
		_mvShare.setViewName("admin/slide/addslide");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/web/trinh-chieu/save", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String saveSlide(@ModelAttribute("slide") SlideEntity slide, HttpSession session, ModelMap modelMap,
			@RequestParam(value = "image", required = false) MultipartFile photo,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		boolean check = true;
		if (slideService.isNameExists(slide.getSlide_caption())) {
			redirectAttributes.addFlashAttribute("msgName", "T??n trang tr??nh chi???u ???? t???n t???i");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", slide);
			return "redirect:/quan-tri/web/trinh-chieu/add";
		}
		try {
			slide.setSlide_img(saveFile(photo));
			slide.setCreated_by(loginInfo.getUser_id());
			slide.setUpdated_by(loginInfo.getUser_id());
			slide.setCreated_at(new Date());
			slide.setUpdated_at(new Date());
			slideService.addSlide(slide);
			redirectAttributes.addFlashAttribute("msg", "Th??m trang tr??nh chi???u th??nh c??ng");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? th??m trang tr??nh chi???u: " + slide.getSlide_caption());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Th??m trang tr??nh chi???u kh??ng th??nh c??ng");
		}
		return "redirect:/quan-tri/web/trinh-chieu";
	}

	@RequestMapping(value = "/quan-tri/web/trinh-chieu/edit/{slide_id}", method = RequestMethod.GET)
	public ModelAndView editSlide(@ModelAttribute("slide") SlideEntity slide, @PathVariable int slide_id) {
		SlideEntity slideitem = slideService.findSlideById(slide);
		_mvShare.addObject("slideitem", slideitem);
		_mvShare.setViewName("admin/slide/editslide");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/web/trinh-chieu/thung-rac", method = RequestMethod.GET)
	public ModelAndView trashSlide() {
		int totalData = slideService.findTrashSlide().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("slideTrashPaginate",
				slideService.GetDataSlideTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/slide/trashslide");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/web/trinh-chieu/thung-rac/{currentPage}", method = RequestMethod.GET)
	public ModelAndView trashSlide(@PathVariable String currentPage) {
		int totalData = slideService.findTrashSlide().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("slideTrashPaginate",
				slideService.GetDataSlideTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/slide/trashslide");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/web/trinh-chieu/editsave", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String editsaveSlide(@ModelAttribute("slide") SlideEntity slide, HttpSession session, ModelMap modelMap,
			@RequestParam(value = "image", required = false) MultipartFile photo,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		boolean check = true;
		if (slideService.isNameExists(slide.getSlide_caption(), slide.getSlide_id())) {
			redirectAttributes.addFlashAttribute("msgName", "T??n trang tr??nh chi???u ???? t???n t???i");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", slide);
			return "redirect:/quan-tri/web/trinh-chieu/edit/" + slide.getSlide_id();
		}
		try {
			slide.setSlide_img(saveFile(photo));
			slide.setUpdated_at(new Date());
			slide.setUpdated_by(loginInfo.getUser_id());
			slideService.updateSlide(slide);
			redirectAttributes.addFlashAttribute("msg", "C???p nh???t th??nh c??ng");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? ch???nh s???a trang tr??nh chi???u: " + slide.getSlide_id());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "C???p nh???t kh??ng th??nh c??ng");
		}
		return "redirect:/quan-tri/web/trinh-chieu";
	}

	@RequestMapping(value = "/quan-tri/web/trinh-chieu/deleteslide/{id}", method = RequestMethod.GET)
	public String deleteSlide(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			slideService.deleteSlide(id);
			redirectAttributes.addFlashAttribute("msg", "X??a th??nh c??ng");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? x??a v??nh vi???n trang tr??nh chi???u: " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "X??a kh??ng th??nh c??ng");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/web/trinh-chieu/trash/{id}", method = RequestMethod.GET)
	public String delSlide(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			slideService.deltrash(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao t??c th??nh c??ng");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? x??a t???m th???i trang tr??nh chi???u: " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao t??c kh??ng th??nh c??ng");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/web/trinh-chieu/retrash/{id}", method = RequestMethod.GET)
	public String retrashSlide(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			slideService.retrash(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao t??c th??nh c??ng");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? b??? x??a t???m th???i trang tr??nh chi???u: " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao t??c kh??ng th??nh c??ng");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/web/trinh-chieu/status/{id}", method = RequestMethod.GET)
	public String onOffSlide(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			slideService.onOffSlide(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao t??c th??nh c??ng");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? thay ?????i t??nh tr???ng trang tr??nh chi???u: " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao t??c kh??ng th??nh c??ng");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
