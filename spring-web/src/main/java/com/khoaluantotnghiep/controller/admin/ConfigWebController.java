package com.khoaluantotnghiep.controller.admin;

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

import com.khoaluantotnghiep.entity.ConfigwebEntity;
import com.khoaluantotnghiep.service.impl.ConfigwebServiceImpl;

@Controller(value = "ConfigWebControllerOfAdmin")
public class ConfigWebController extends BaseController {
	@Autowired
	ConfigwebServiceImpl configwebService;

	@GetMapping(value = "/quan-tri/web/cau-hinh-web")
	public ModelAndView ViewConfig() {
		_mvShare.addObject("config", configwebService.findConfigweb());
		_mvShare.setViewName("admin/config/configweb");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/web/cau-hinh-web/edit/{id}")
	public ModelAndView ViewEditConfig(@ModelAttribute("config") ConfigwebEntity config, @PathVariable int id) {
		_mvShare.addObject("config", configwebService.findConfigweb());
		_mvShare.setViewName("admin/config/updateconfigweb");
		return _mvShare;
	}

	@PostMapping(value = "/quan-tri/web/cau-hinh-web/editsave")
	public String EditSaveConfig(HttpServletRequest request, @ModelAttribute("config") ConfigwebEntity config,
			final RedirectAttributes redirectAttributes, ModelMap modelMap,
			@RequestParam(value = "image", required = false) MultipartFile photo,
			@RequestParam(value = "image1", required = false) MultipartFile photo1,
			@RequestParam(value = "image2", required = false) MultipartFile photo2) {
		config.setLogo(saveFile(photo));
		config.setLogomobile(saveFile(photo1));
		config.setIcon(saveFile(photo2));
		try {
			configwebService.update(config);
			redirectAttributes.addFlashAttribute("msg", "Cập nhật thành công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Cập nhật không thành công");
		}
		return "redirect:/quan-tri/web/cau-hinh-web";
	}
	
	@RequestMapping(value = "/quan-tri/web/cau-hinh-web/status/{id}", method = RequestMethod.GET)
	public String changeStatus(@PathVariable int id, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			configwebService.changeStatus(id);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
