package com.khoaluantotnghiep.controller.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.khoaluantotnghiep.entity.CategoryEntity;
import com.khoaluantotnghiep.entity.ConfigwebEntity;
import com.khoaluantotnghiep.entity.ManufacturerEntity;
import com.khoaluantotnghiep.entity.MenuEntity;
import com.khoaluantotnghiep.entity.ProductEntity;
import com.khoaluantotnghiep.service.impl.CategoryServiceImpl;
import com.khoaluantotnghiep.service.impl.ConfigwebServiceImpl;
import com.khoaluantotnghiep.service.impl.ManufacturerServiceImpl;
import com.khoaluantotnghiep.service.impl.MenuServiceImpl;
import com.khoaluantotnghiep.service.impl.PageServiceImpl;
import com.khoaluantotnghiep.service.impl.PostServiceImpl;
import com.khoaluantotnghiep.service.impl.ProductServiceImpl;
import com.khoaluantotnghiep.service.impl.ServiceServiceImpl;
import com.khoaluantotnghiep.service.impl.SlideServiceImpl;
import com.khoaluantotnghiep.service.impl.SocialNetWorkServiceImpl;
import com.khoaluantotnghiep.service.impl.TopbarServiceImpl;
import com.khoaluantotnghiep.service.impl.TopicServiceImpl;

@Controller(value = "baseControllerOfWeb")
public class BaseController {
	public ModelAndView _mvShare = new ModelAndView();

	@Autowired
	PageServiceImpl pageService;
	@Autowired
	SlideServiceImpl slideService;
	@Autowired
	ConfigwebServiceImpl configwebService;
	@Autowired
	TopicServiceImpl topicService;
	@Autowired
	CategoryServiceImpl categoryService;
	@Autowired
	ProductServiceImpl productService;
	@Autowired
	ManufacturerServiceImpl manufacturerService;
	@Autowired
	PostServiceImpl postServiceImpl;
	@Autowired
	MenuServiceImpl menuService;
	@Autowired
	TopbarServiceImpl topbarService;
	@Autowired
	ServiceServiceImpl serviceService;
	@Autowired
	SocialNetWorkServiceImpl socialnetworkService;
	@PostConstruct
	public ModelAndView Init() {

		ConfigwebEntity configweb = configwebService.findConfigweb();
//		if(configweb.getStatus() == 0) {
//			return new ModelAndView("redirect:/bao-tri");
//		}
		List<ProductEntity> listProMenu = productService.findAllProductMenu();
		List<CategoryEntity> listCatMenu = categoryService.findAllCategoryShow();
		List<ManufacturerEntity> listmanu = manufacturerService.findAllManufacturer();
		List<MenuEntity> listmenu = menuService.GetDataMenuShow();
		Map<Integer, List<ManufacturerEntity>> mapCatProducts = new HashMap<Integer, List<ManufacturerEntity>>();
		for (CategoryEntity catIt : listCatMenu) {
			List<ManufacturerEntity> me = new ArrayList<>();
			for (ProductEntity pi : listProMenu) {
				if (catIt.getId() == pi.getProduct_catid()) {
					for (ManufacturerEntity manui : listmanu) {
						if (manui.getManufacturer_id() == pi.getManufacturer_id()) {
							me.add(manui);
						}
					}
				}
			}
			mapCatProducts.put(catIt.getId(), me);
		}
		Map<Integer, List<MenuEntity>> listcmenu = menuService.mapchildMenu();
//		for (Map.Entry<Integer, List<MenuEntity>> entry : listcmenu.entrySet()) {
//			System.out.println(entry.getKey() + "/" + entry.getValue().toString());
//		}
		
		_mvShare.addObject("socialnetworks", socialnetworkService.findAllSocialNetWorkShow());
		_mvShare.addObject("topbar", topbarService.findAllTopbarShow().get(0));
		_mvShare.addObject("listcmenu", listcmenu);
		_mvShare.addObject("menu", listmenu);
		_mvShare.addObject("slides", slideService.findAllSlideShow());
		_mvShare.addObject("pages", pageService.findAllPageShow());
		_mvShare.addObject("configweb", configweb);
		_mvShare.addObject("listtopic", topicService.findAllTopicShowFooter());
		_mvShare.addObject("listcate", listCatMenu);
		_mvShare.addObject("listproductmenu", listProMenu);
		_mvShare.addObject("listmanu", listmanu);
		_mvShare.addObject("listservice", serviceService.findAllServiceShow());
		_mvShare.addObject("listpost", postServiceImpl.findAllPostShow());
		_mvShare.addObject("mapcatproducts", mapCatProducts);
		return _mvShare;
	}

}
