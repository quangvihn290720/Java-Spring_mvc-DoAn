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
import com.khoaluantotnghiep.entity.ManufacturerEntity;
import com.khoaluantotnghiep.entity.NoteEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.ManufacturerServiceImpl;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;

@Controller(value = "manufacturerControllerOfAdmin")
public class ManufacturerController extends BaseController {
	@Autowired
	ManufacturerServiceImpl manufacturerService;
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	NoteServiceImpl noteService;
	private int totalDataPage = 5;

	@RequestMapping(value = "/quan-tri/hang", method = RequestMethod.GET)
	public ModelAndView Manufacturer() {
		int totalData = manufacturerService.findAllManufacturer().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("manuPaginate",
				manufacturerService.GetDataManufacturerPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/manufacturer/manufacturer");
		return _mvShare;

	}

	@RequestMapping(value = "/quan-tri/hang/{currentPage}", method = RequestMethod.GET)
	public ModelAndView Manufacturer(@PathVariable String currentPage) {
		int totalData = manufacturerService.findAllManufacturer().size();
		int availPage = (totalData + totalDataPage - 1) / totalDataPage;
		if (Integer.parseInt(currentPage) > availPage && availPage > 0) {
			return new ModelAndView("redirect:/quan-tri/hang/" + availPage);
		}
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("manuPaginate",
				manufacturerService.GetDataManufacturerPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/manufacturer/manufacturer");
		return _mvShare;

	}

	@RequestMapping(value = "/quan-tri/hang/add", method = RequestMethod.GET)
	public ModelAndView addPage(@ModelAttribute("manufacturer") ManufacturerEntity manufacturer) {
		_mvShare.setViewName("admin/manufacturer/addmanu");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/hang/save", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String saveManu(HttpSession session, @ModelAttribute("manufacturer") ManufacturerEntity manufacturer,
			ModelMap modelMap, @RequestParam(value = "image", required = false) MultipartFile photo,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		boolean check = true;
		if (manufacturerService.isNameExists(manufacturer.getManufacturer_name())) {
			redirectAttributes.addFlashAttribute("msgTitle", "Tên hãng đã tồn tại");
			check = false;
		}
		if (manufacturerService.isSlugExists(manufacturer.getManufacturer_slug())) {
			redirectAttributes.addFlashAttribute("msgSlug", "Slug đã tồn tại");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", manufacturer);
			return "redirect:/quan-tri/hang/add";
		}
		try {
			manufacturer.setManufacturer_img(saveFile(photo));
			manufacturer.setCreated_by(loginInfo.getUser_id());
			manufacturer.setUpdated_by(loginInfo.getUser_id());
			manufacturer.setCreated_at(new Date());
			manufacturer.setUpdated_at(new Date());
			manufacturerService.addManufacturer(manufacturer);
			redirectAttributes.addFlashAttribute("msg", "Thêm thành công");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã thêm hãng " + manufacturer.getManufacturer_name());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thêm không thành công");
		}
		return "redirect:/quan-tri/hang";
	}

	@RequestMapping(value = "quan-tri/hang/edit/{manufacturer_id}", method = RequestMethod.GET)
	public ModelAndView getPage(@ModelAttribute("manufacturer") ManufacturerEntity manufacturer,
			@PathVariable int manufacturer_id) {
		ManufacturerEntity manufactureritem = manufacturerService.findManufacturerById(manufacturer);
		_mvShare.addObject("manufactureritem", manufactureritem);
		_mvShare.setViewName("admin/manufacturer/editmanu");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/hang/editsave", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String editsavePage(HttpSession session, @ModelAttribute("manufacturer") ManufacturerEntity manufacturer,
			@RequestParam(value = "image", required = false) MultipartFile photo,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		boolean check = true;
		if (manufacturerService.isNameExists(manufacturer.getManufacturer_name(), manufacturer.getManufacturer_id())) {
			redirectAttributes.addFlashAttribute("msgTitle", "Tên hãng đã tồn tại");
			check = false;
		}
		if (manufacturerService.isSlugExists(manufacturer.getManufacturer_slug(), manufacturer.getManufacturer_id())) {
			System.out.println("Sai slug");
			redirectAttributes.addFlashAttribute("msgSlug", "Slug đã tồn tại");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", manufacturer);
			return "redirect:/quan-tri/hang/edit/" + manufacturer.getManufacturer_id();
		}

		try {
			manufacturer.setManufacturer_img(saveFile(photo));
			manufacturer.setUpdated_at(new Date());
			manufacturer.setUpdated_by(loginInfo.getUser_id());
			manufacturerService.updateManufacturer(manufacturer);
			redirectAttributes.addFlashAttribute("msg", "Cập nhật thành công");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã chỉnh sửa hãng " + manufacturer.getManufacturer_id());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Cập nhật Không thành công");
		}
		return "redirect:/quan-tri/hang";
	}

	@RequestMapping(value = "/quan-tri/hang/status/{manufacturer_id}", method = RequestMethod.GET)
	public String onOffPage(@PathVariable int manufacturer_id, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			manufacturerService.onOffManufacturer(manufacturer_id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã thay đổi trạng thái " + manufacturer_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/hang/trash/{manufacturer_id}", method = RequestMethod.GET)
	public String delTrashPage(@PathVariable int manufacturer_id, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		try {
			manufacturerService.deltrash(manufacturer_id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Xóa thành công");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã xóa tạm thời hãng " + manufacturer_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/hang/thung-rac", method = RequestMethod.GET)
	public ModelAndView trashPage() {
		int totalData = manufacturerService.findTrashManufacturer().size();

		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("manuTrashPaginate",
				manufacturerService.GetDataManufacturerTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/manufacturer/trashmanu");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/hang/thung-rac/{currentPage}", method = RequestMethod.GET)
	public ModelAndView trashPage(@PathVariable String currentPage) {
		int totalData = manufacturerService.findTrashManufacturer().size();
		int availPage = (totalData + totalDataPage - 1) / totalDataPage;
		if (Integer.parseInt(currentPage) > availPage && availPage > 0) {
			return new ModelAndView("redirect:/quan-tri/hang/thung-rac/" + availPage);
		}
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("manuTrashPaginate",
				manufacturerService.GetDataManufacturerTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/manufacturer/trashmanu");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/hang/retrash/{manufacturer_id}", method = RequestMethod.GET)
	public String reTrash(@PathVariable int manufacturer_id, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			manufacturerService.retrash(manufacturer_id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã bỏ xóa tạm thời hãng " + manufacturer_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/hang/delete/{manufacturer_id}", method = RequestMethod.GET)
	public String deletaPage(@PathVariable int manufacturer_id, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			manufacturerService.deleteManufacturer(manufacturer_id);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
			// them note để quản lý
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin đã xóa vĩnh viễn hãng " + manufacturer_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
