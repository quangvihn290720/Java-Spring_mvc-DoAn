package com.khoaluantotnghiep.controller.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.khoaluantotnghiep.dto.PaginateDTO;
import com.khoaluantotnghiep.service.impl.OptiongroupsServiceImpl;
import com.khoaluantotnghiep.service.impl.OptionsServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;
import com.khoaluantotnghiep.service.impl.ProductImageServiceImpl;
import com.khoaluantotnghiep.service.impl.ProductOptionsServiceImpl;
import com.khoaluantotnghiep.service.impl.ProductServiceImpl;
import com.khoaluantotnghiep.service.impl.SmartpayServiceImpl;
import com.khoaluantotnghiep.service.impl.UtilitiesServiceImpl;

@Controller(value = "productControllerOfWeb")
public class ProductController extends BaseController {
	@Autowired
	ProductServiceImpl productService;
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	UtilitiesServiceImpl utilitiesService;
	@Autowired
	ProductOptionsServiceImpl prodOptionService;
	@Autowired
	OptionsServiceImpl optionsService;
	@Autowired
	OptiongroupsServiceImpl optiongroupsService;
	@Autowired
	ProductImageServiceImpl prodImgService;
	@Autowired
	SmartpayServiceImpl smartpayService;
	private int totalProductPage = 8;

//	@RequestMapping(value = "/chi-tiet-san-pham/{product_id}", method = RequestMethod.GET)
//	public ModelAndView DetailProduct(@PathVariable int product_id) {
//		Map<Integer, String> mapCate = categoryService.mapCate();
//		_mvShare.addObject("mapCate", mapCate);
//		Map<Integer, String> mapManu = manufacturerService.mapManu();
//		_mvShare.addObject("mapManu", mapManu);
//		_mvShare.addObject("product", productService.findProductById(product_id));
//		_mvShare.addObject("listproduct", productService.findAllProductShow());
//		_mvShare.addObject("listutilities", utilitiesService.findAllUtilitiesShow());
//		_mvShare.addObject("listprodoption", prodOptionService.findAllProdOption());
//		_mvShare.addObject("listoption", optionsService.findAllOption());
//		_mvShare.addObject("listoptiongroup", optiongroupsService.findAllOptionGroup());
//		_mvShare.addObject("listimgprod", prodImgService.findAllProductImageShow());
//		_mvShare.setViewName("web/product/product");
//		return _mvShare;
//	}
	@RequestMapping(value = "/chi-tiet-san-pham/{slug}", method = RequestMethod.GET)
	public ModelAndView DetailProduct(@PathVariable String slug) {
		Map<Integer, String> mapCate = categoryService.mapCate();
		_mvShare.addObject("mapCate", mapCate);
		Map<Integer, String> mapManu = manufacturerService.mapManu();
		_mvShare.addObject("mapManu", mapManu);
//		_mvShare.addObject("product", productService.findProductById(product_id));
		_mvShare.addObject("product", productService.findProductBySlug(slug));
		_mvShare.addObject("listproduct", productService.findAllProductShow());
		_mvShare.addObject("listutilities", utilitiesService.findAllUtilitiesShow());
		_mvShare.addObject("listprodoption", prodOptionService.findAllProdOption());
		_mvShare.addObject("listoption", optionsService.findAllOption());
		_mvShare.addObject("listoptiongroup", optiongroupsService.findAllOptionGroup());
		_mvShare.addObject("listimgprod", prodImgService.findAllProductImageShow());
		_mvShare.addObject("listsmartpay", smartpayService.findAllSmartpayShow());
		_mvShare.setViewName("web/product/product");
		return _mvShare;
	}

	@RequestMapping(value = "/san-pham", method = RequestMethod.GET)
	public ModelAndView Product(@RequestParam(value = "page", required = true) String page, HttpServletRequest request,
			@RequestParam(value = "sortBy", required = false) String sortBy,
			@RequestParam(value = "order", required = false) String order) {
		int totalData = 0;
		String url = request.getQueryString();
		String[] parts = url.split("(?<=page=)");
		String part1 = parts[0];
		String part2 = parts[1].substring(1);
		if (sortBy == null) {
			totalData = productService.findAllProductShowSortByTime().size();
			PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
					Integer.parseInt(page));
			_mvShare.addObject("paginateInfo", paginateInfo);
			_mvShare.addObject("totalData", totalData);
			_mvShare.addObject("prodShowPaginate",
					productService.GetDataProductShowSortByTimePaginate(paginateInfo.getStart(), totalProductPage));

		} else {
			if (sortBy.equals("price") && order.equals("asc")) {
				totalData = productService.findAllProductSortByASC().size();
				PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
						Integer.parseInt(page));
				_mvShare.addObject("paginateInfo", paginateInfo);
				_mvShare.addObject("totalData", totalData);
				_mvShare.addObject("prodShowPaginate",
						productService.GetDataProductSortByASCPaginate(paginateInfo.getStart(), totalProductPage));
			} else if (sortBy.equals("price") && order.equals("desc")) {
				totalData = productService.findAllProductSortByDESC().size();
				PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
						Integer.parseInt(page));
				_mvShare.addObject("paginateInfo", paginateInfo);
				_mvShare.addObject("totalData", totalData);
				_mvShare.addObject("prodShowPaginate",
						productService.GetDataProductSortByDESCPaginate(paginateInfo.getStart(), totalProductPage));
			} else if (sortBy.equals("ctime") && order.equals("desc")) {
				totalData = productService.findAllProductShowSortByTime().size();
				PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
						Integer.parseInt(page));
				_mvShare.addObject("paginateInfo", paginateInfo);
				_mvShare.addObject("totalData", totalData);
				_mvShare.addObject("prodShowPaginate",
						productService.GetDataProductShowSortByTimePaginate(paginateInfo.getStart(), totalProductPage));
			} else if (sortBy.equals("ctime") && order.equals("asc")) {
				totalData = productService.findAllProductShowSortByTimeASC().size();
				PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
						Integer.parseInt(page));
				_mvShare.addObject("paginateInfo", paginateInfo);
				_mvShare.addObject("totalData", totalData);
				_mvShare.addObject("prodShowPaginate", productService
						.GetDataProductShowSortByTimeASCPaginate(paginateInfo.getStart(), totalProductPage));
			} else if (sortBy.equals("alpha") && order.equals("asc")) {
				totalData = productService.findAllProductShowSortByAlphaASC().size();
				PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
						Integer.parseInt(page));
				_mvShare.addObject("paginateInfo", paginateInfo);
				_mvShare.addObject("totalData", totalData);
				_mvShare.addObject("prodShowPaginate", productService
						.GetDataProductShowSortByAlphaASCPaginate(paginateInfo.getStart(), totalProductPage));
			} else if (sortBy.equals("alpha") && order.equals("desc")) {
				totalData = productService.findAllProductShowSortByAlphaDESC().size();
				PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
						Integer.parseInt(page));
				_mvShare.addObject("paginateInfo", paginateInfo);
				_mvShare.addObject("totalData", totalData);
				_mvShare.addObject("prodShowPaginate", productService
						.GetDataProductShowSortByAlphaDESCPaginate(paginateInfo.getStart(), totalProductPage));
			}
		}
		_mvShare.setViewName("web/product/allproduct");
		int availPage = (totalData + totalProductPage - 1) / totalProductPage;
		if (Integer.parseInt(page) > availPage) {
			page = availPage + "";
			return new ModelAndView("redirect:/san-pham?" + part1 + page + part2);
		}
		_mvShare.addObject("sort", sortBy);
		_mvShare.addObject("order", order);
		_mvShare.addObject("rppart1", part1);
		_mvShare.addObject("rppart2", part2);
		_mvShare.addObject("currentPage", page);
		return _mvShare;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public ModelAndView SearchProductpage(@RequestParam String q, HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) String currentPage,
			@RequestParam(value = "sortBy", required = false) String sortBy,
			@RequestParam(value = "order", required = false) String order) {
		int totalData = 0;
		String url = request.getQueryString();
		String[] parts = url.split("(?<=currentPage=)");
		String part1 = parts[0];
		String part2 = parts[1].substring(1);
		if (sortBy == null && order == null) {
			totalData = productService.SearchProductSortByTime(q).size();
			PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
					Integer.parseInt(currentPage));
			_mvShare.addObject("paginateInfo", paginateInfo);
			_mvShare.addObject("totalData", totalData);
			_mvShare.addObject("searchPaginate", productService
					.GetDataProductsSearchSortByTimePaginate(paginateInfo.getStart(), totalProductPage, q));
		} else if (sortBy.equals("price") && order.equals("asc")) {
			totalData = productService.SearchProductSortByASC(q).size();
			PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
					Integer.parseInt(currentPage));
			_mvShare.addObject("paginateInfo", paginateInfo);
			_mvShare.addObject("totalData", totalData);
			_mvShare.addObject("searchPaginate", productService
					.GetDataProductsSearchSortByASCPaginate(paginateInfo.getStart(), totalProductPage, q));
		} else if (sortBy.equals("price") && order.equals("desc")) {
			totalData = productService.SearchProductSortByDESC(q).size();
			PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
					Integer.parseInt(currentPage));
			_mvShare.addObject("paginateInfo", paginateInfo);
			_mvShare.addObject("totalData", totalData);
			_mvShare.addObject("searchPaginate", productService
					.GetDataProductsSearchSortByDESCPaginate(paginateInfo.getStart(), totalProductPage, q));
		} else if (sortBy.equals("ctime") && order.equals("desc")) {
			totalData = productService.SearchProductSortByTime(q).size();
			PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
					Integer.parseInt(currentPage));
			_mvShare.addObject("paginateInfo", paginateInfo);
			_mvShare.addObject("totalData", totalData);
			_mvShare.addObject("searchPaginate", productService
					.GetDataProductsSearchSortByTimePaginate(paginateInfo.getStart(), totalProductPage, q));
		} else if (sortBy.equals("ctime") && order.equals("asc")) {
			totalData = productService.SearchProductSortByTimeASC(q).size();
			PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
					Integer.parseInt(currentPage));
			_mvShare.addObject("paginateInfo", paginateInfo);
			_mvShare.addObject("totalData", totalData);
			_mvShare.addObject("searchPaginate", productService
					.GetDataProductsSearchSortByTimeASCPaginate(paginateInfo.getStart(), totalProductPage, q));
		} else if (sortBy.equals("alpha") && order.equals("asc")) {
			totalData = productService.SearchProductSortByAlphaASC(q).size();
			PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
					Integer.parseInt(currentPage));
			_mvShare.addObject("paginateInfo", paginateInfo);
			_mvShare.addObject("totalData", totalData);
			_mvShare.addObject("searchPaginate", productService
					.GetDataProductsSearchSortByAlphaASCPaginate(paginateInfo.getStart(), totalProductPage, q));
		} else if (sortBy.equals("alpha") && order.equals("desc")) {
			totalData = productService.SearchProductSortByAlphaDESC(q).size();
			PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
					Integer.parseInt(currentPage));
			_mvShare.addObject("paginateInfo", paginateInfo);
			_mvShare.addObject("totalData", totalData);
			_mvShare.addObject("searchPaginate", productService
					.GetDataProductsSearchSortByAlphaDESCPaginate(paginateInfo.getStart(), totalProductPage, q));
		}

		_mvShare.addObject("q", q);
		_mvShare.setViewName("web/product/searchproduct");
		int availPage = (totalData + totalProductPage - 1) / totalProductPage;
		if (Integer.parseInt(currentPage) > availPage) {
			currentPage = availPage + "";
			return new ModelAndView("redirect:/search?" + part1 + currentPage + part2);
		}
		_mvShare.addObject("sort", sortBy);
		_mvShare.addObject("order", order);
		_mvShare.addObject("rppart1", part1);
		_mvShare.addObject("rppart2", part2);
		_mvShare.addObject("currentPage", currentPage);
		return _mvShare;
	}
}
