package com.khoaluantotnghiep.controller.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.khoaluantotnghiep.service.impl.AccountServiceImpl;
import com.khoaluantotnghiep.service.impl.PageServiceImpl;

@Controller(value = "pageControllerOfWeb")
public class PageController extends BaseController {
	@Autowired
	PageServiceImpl pageService;
	@Autowired
	AccountServiceImpl accoutService;

	@RequestMapping(value = "/trang-don/{slug}", method = RequestMethod.GET)
	public ModelAndView Page(@PathVariable String slug) {
		_mvShare.addObject("page", pageService.findPageBySlug(slug));
		Map<Integer, String> mapUser = accoutService.mapUser();
		_mvShare.addObject("mapUser", mapUser);
		_mvShare.setViewName("web/news/page");
		return _mvShare;
	}
}
