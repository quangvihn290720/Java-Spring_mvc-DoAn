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
import com.khoaluantotnghiep.entity.NoteEntity;
import com.khoaluantotnghiep.entity.SmartpayEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;
import com.khoaluantotnghiep.service.impl.SmartpayServiceImpl;

@Controller
public class SmartpayController extends BaseController {
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	SmartpayServiceImpl smartpayService;
	@Autowired
	NoteServiceImpl noteService;
	private int totalDataPage = 5;

	@RequestMapping(value = "/quan-tri/web/nha-thanh-toan", method = RequestMethod.GET)
	public ModelAndView Smarpay(ModelMap modelMap) {
		int totalData = smartpayService.findAllSmartpay().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("smartpayPaginate",
				smartpayService.GetDataSmartpayPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/smartpay/smartpay");
		return _mvShare;
	}

	@GetMapping("/quan-tri/web/nha-thanh-toan/{currentPage}")
	public ModelAndView Smarpay(@PathVariable String currentPage) {
		int totalData = smartpayService.findAllSmartpay().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("smartpayPaginate",
				smartpayService.GetDataSmartpayPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/smartpay/smartpay");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/web/nha-thanh-toan/add", method = RequestMethod.GET)
	public ModelAndView add(@ModelAttribute("smartpay") SmartpayEntity smartpay) {
		_mvShare.setViewName("admin/smartpay/addsmartpay");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/web/nha-thanh-toan/save", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String save(@ModelAttribute("smartpay") SmartpayEntity smartpay, HttpSession session, ModelMap modelMap,
			@RequestParam(value = "image", required = false) MultipartFile photo,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		boolean check = true;
		if (smartpayService.isNameExists(smartpay.getName())) {
			redirectAttributes.addFlashAttribute("msgName", "T??n nh?? thanh to??n ???? t???n t???i");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", smartpay);
			return "redirect:/quan-tri/web/nha-thanh-toan/add";
		}
		try {
			smartpay.setImg(saveFile(photo));
			smartpay.setCreated_at(new Date());
			smartpay.setUpdated_at(new Date());
			smartpay.setCreated_by(loginInfo.getUser_id());
			smartpay.setUpdated_by(loginInfo.getUser_id());
			smartpayService.addSmartpay(smartpay);
			redirectAttributes.addFlashAttribute("msg", "Th??m th??nh c??ng!");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? th??m nh?? thanh to??n m???i: " + smartpay.getName());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Th??m kh??ng th??nh c??ng!");
		}
		return "redirect:/quan-tri/web/nha-thanh-toan";
	}

	@RequestMapping(value = "/quan-tri/web/nha-thanh-toan/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editSmarpay(@ModelAttribute("smartpay") SmartpayEntity smartpay, @PathVariable int id) {
		SmartpayEntity smartpayitem = smartpayService.findSmartpayById(smartpay);
		_mvShare.addObject("smartpayitem", smartpayitem);
		_mvShare.setViewName("admin/smartpay/editsmartpay");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/web/nha-thanh-toan/thung-rac", method = RequestMethod.GET)
	public ModelAndView trashSmarpay(@ModelAttribute("smartpay") SmartpayEntity smartpay) {
		int totalData = smartpayService.findTrashSmartpay().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("smartpayTrashPaginate",
				smartpayService.GetDataSmartpayTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/smartpay/trashsmartpay");
		return _mvShare;
	}

	@GetMapping("/quan-tri/web/nha-thanh-toan/thung-rac/{currentPage}")
	public ModelAndView trashSmarpay(@PathVariable String currentPage,
			@ModelAttribute("smartpay") SmartpayEntity smartpay) {
		int totalData = smartpayService.findTrashSmartpay().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("smartpayTrashPaginate",
				smartpayService.GetDataSmartpayTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/smartpay/trashsmartpay");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/web/nha-thanh-toan/editsave", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String editsaveSmarpay(@ModelAttribute("smartpay") SmartpayEntity smartpay, HttpSession session,
			ModelMap modelMap, @RequestParam(value = "image", required = false) MultipartFile photo,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		boolean check = true;
		if (smartpayService.isNameExists(smartpay.getName(), smartpay.getId())) {
			redirectAttributes.addFlashAttribute("msgName", "T??n nh?? thanh to??n ???? t???n t???i");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", smartpay);
			return "redirect:/quan-tri/web/nha-thanh-toan/edit/" + smartpay.getId();
		}
		try {
			smartpay.setImg(saveFile(photo));
			smartpay.setUpdated_at(new Date());
			smartpay.setUpdated_by(loginInfo.getUser_id());
			smartpayService.updateSmartpay(smartpay);
			redirectAttributes.addFlashAttribute("msg", "C???p nh???t th??nh c??ng!");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? th??m nh?? thanh to??n m???i: " + smartpay.getName());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "C???p nh???t kh??ng th??nh c??ng!");
		}
		return "redirect:/quan-tri/web/nha-thanh-toan";
	}

	@RequestMapping(value = "/quan-tri/web/nha-thanh-toan/delete/{id}", method = RequestMethod.GET)
	public String deleteSmarpay(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			smartpayService.deleteSmartpay(id);
			redirectAttributes.addFlashAttribute("msg", "X??a th??nh c??ng!");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? x??a v??nh vi???n nh?? thanh to??n m???i: " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "X??a kh??ng th??nh c??ng!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/web/nha-thanh-toan/deltrash/{id}", method = RequestMethod.GET)
	public String delSmarpay(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			smartpayService.deltrashSmartpay(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "X??a v??o th??ng r??c th??nh c??ng!");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? x??a t???m th???i nh?? thanh to??n: " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "X??a v??o th??ng r??c kh??ng th??nh c??ng!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/web/nha-thanh-toan/retrash/{id}", method = RequestMethod.GET)
	public String retrashSmarpay(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			smartpayService.retrashSmartpay(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao t??c th??nh c??ng!");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? b??? x??a t???m th???i th??m nh?? thanh to??n: " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao t??c kh??ng th??nh c??ng!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/web/nha-thanh-toan/status/{id}", method = RequestMethod.GET)
	public String onOffSmarpay(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			smartpayService.onOffSmartpay(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao t??c th??nh c??ng!");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? thay ?????i tr???ng th??i nh?? thanh to??n m???i: " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao t??c kh??ng th??nh c??ng!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
