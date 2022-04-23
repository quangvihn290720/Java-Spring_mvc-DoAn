package com.khoaluantotnghiep.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "maintenanceControllerOfWeb")
public class MaintenanceController {
	public ModelAndView _mvShare = new ModelAndView();

	@RequestMapping(value = { "/bao-tri" }, method = RequestMethod.GET)
	public ModelAndView maintenancePage() {

		_mvShare.setViewName("web/maintenance/maintenance");
		return _mvShare;
	}
}
