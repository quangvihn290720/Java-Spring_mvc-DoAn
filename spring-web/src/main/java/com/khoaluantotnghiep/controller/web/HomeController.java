package com.khoaluantotnghiep.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.khoaluantotnghiep.dto.PaginateDTO;
import com.khoaluantotnghiep.entity.CategoryEntity;
import com.khoaluantotnghiep.entity.ManufacturerEntity;
import com.khoaluantotnghiep.service.impl.BannerServiceImpl;
import com.khoaluantotnghiep.service.impl.CategoryServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;
import com.khoaluantotnghiep.service.impl.ProductServiceImpl;

@Controller(value = "homeControllerOfWeb")
public class HomeController extends BaseController {
	@Autowired
	ProductServiceImpl productService;
	@Autowired
	CategoryServiceImpl categoryService;
	@Autowired
	BannerServiceImpl bannerService;
	@Autowired
	PaginatesServiceImpl paginateService;
	private int totalProductPage = 8;

	@RequestMapping(value = { "/", "/trang-chu" }, method = RequestMethod.GET)
	public ModelAndView homePage() {
		_mvShare.addObject("listproduct", productService.findAllProduct());
		_mvShare.addObject("listprodnew", productService.findProductNew());
		_mvShare.addObject("listbanner", bannerService.findAllBannerShow());
		_mvShare.setViewName("web/home");
		return _mvShare;
	}

	@GetMapping(value = "/{slug}")
	public ModelAndView viewManaCate(@PathVariable String slug, HttpServletRequest request,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "sortBy", required = false) String sortBy,
			@RequestParam(value = "order", required = false) String order) {
		int totalData = 0;
		String url = request.getQueryString();
		String[] parts = url.split("(?<=page=)");
		String part1 = parts[0];
		String part2 = parts[1].substring(1);

		if (categoryService.findAllCateBySlug(slug) != null) {
			CategoryEntity categoryEntity = categoryService.findAllCateBySlug(slug);
			int catid = categoryEntity.getId();
			if (sortBy == null) {
				totalData = productService.findAllProductByCateSortByTimeDESC(catid).size();
				PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
						Integer.parseInt(page));
				_mvShare.addObject("paginateInfo", paginateInfo);
				_mvShare.addObject("totalData", totalData);
				_mvShare.addObject("prodCatePaginate", productService
						.GetDataProductByCateSortByTimeDESCPaginate(paginateInfo.getStart(), totalProductPage, catid));
//				totalData = productService.findAllProductByCate(catid).size();
//				PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
//						Integer.parseInt(page));
//				_mvShare.addObject("paginateInfo", paginateInfo);
//				_mvShare.addObject("totalData", totalData);
//				_mvShare.addObject("prodCatePaginate",
//						productService.GetDataProductByCatePaginate(paginateInfo.getStart(), totalProductPage, catid));
			} else if (sortBy.equals("price") && order.equals("asc")) {
				totalData = productService.findAllProductByCateSortByPriceASC(catid).size();
				PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
						Integer.parseInt(page));
				_mvShare.addObject("paginateInfo", paginateInfo);
				_mvShare.addObject("totalData", totalData);
				_mvShare.addObject("prodCatePaginate", productService
						.GetDataProductByCateSortByPriceASCPaginate(paginateInfo.getStart(), totalProductPage, catid));
			} else if (sortBy.equals("price") && order.equals("desc")) {
				totalData = productService.findAllProductByCateSortByPriceDESC(catid).size();
				PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
						Integer.parseInt(page));
				_mvShare.addObject("paginateInfo", paginateInfo);
				_mvShare.addObject("totalData", totalData);
				_mvShare.addObject("prodCatePaginate", productService
						.GetDataProductByCateSortByPriceDESCPaginate(paginateInfo.getStart(), totalProductPage, catid));
			} else if (sortBy.equals("ctime") && order.equals("asc")) {
				totalData = productService.findAllProductByCateSortByTimeASC(catid).size();
				PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
						Integer.parseInt(page));
				_mvShare.addObject("paginateInfo", paginateInfo);
				_mvShare.addObject("totalData", totalData);
				_mvShare.addObject("prodCatePaginate", productService
						.GetDataProductByCateSortByTimeASCPaginate(paginateInfo.getStart(), totalProductPage, catid));
			} else if (sortBy.equals("ctime") && order.equals("desc")) {
				totalData = productService.findAllProductByCateSortByTimeDESC(catid).size();
				PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
						Integer.parseInt(page));
				_mvShare.addObject("paginateInfo", paginateInfo);
				_mvShare.addObject("totalData", totalData);
				_mvShare.addObject("prodCatePaginate", productService
						.GetDataProductByCateSortByTimeDESCPaginate(paginateInfo.getStart(), totalProductPage, catid));
			} else if (sortBy.equals("alpha") && order.equals("asc")) {
				totalData = productService.findAllProductByCateSortByAlphaASC(catid).size();
				PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
						Integer.parseInt(page));
				_mvShare.addObject("paginateInfo", paginateInfo);
				_mvShare.addObject("totalData", totalData);
				_mvShare.addObject("prodCatePaginate", productService
						.GetDataProductByCateSortByAlphaASCPaginate(paginateInfo.getStart(), totalProductPage, catid));
			} else if (sortBy.equals("alpha") && order.equals("desc")) {
				totalData = productService.findAllProductByCateSortByAlphaDESC(catid).size();
				PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
						Integer.parseInt(page));
				_mvShare.addObject("paginateInfo", paginateInfo);
				_mvShare.addObject("totalData", totalData);
				_mvShare.addObject("prodCatePaginate", productService
						.GetDataProductByCateSortByAlphaDESCPaginate(paginateInfo.getStart(), totalProductPage, catid));
			}
			_mvShare.setViewName("web/product/category");
			_mvShare.addObject("slug", slug);
			_mvShare.addObject("categoryEntity", categoryEntity);
//			int availPage = (totalData + totalProductPage - 1) / totalProductPage;
//			if (Integer.parseInt(page) > availPage) {
//				page = availPage + "";
//				return new ModelAndView("redirect:/" + slug + "?" + part1 + page + part2);
//			}
		} else {
			int i = slug.lastIndexOf("-");
			String[] a = { slug.substring(0, i), slug.substring(i + 1) };
			CategoryEntity categoryEntity = categoryService.findAllCateBySlug(a[0]);
			ManufacturerEntity manuEntity = manufacturerService.findAllManufacturerBySlug(a[1]);
			if (categoryEntity != null && manuEntity != null) {
				int catid = categoryEntity.getId();
				int manuid = manuEntity.getManufacturer_id();
				//
				if (sortBy == null) {
//					totalData = productService.findAllProductByCateMenu(manuid, catid).size();
//					PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
//							Integer.parseInt(page));
//					_mvShare.addObject("paginateInfo", paginateInfo);
//					_mvShare.addObject("totalData", totalData);
//					_mvShare.addObject("prodManuCatePaginate", productService.GetDataProductByCateMenuPaginate(
//							paginateInfo.getStart(), totalProductPage, manuid, catid));
					totalData = productService.findAllProductByCateMenuSortByTime(manuid, catid).size();
					PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
							Integer.parseInt(page));
					_mvShare.addObject("paginateInfo", paginateInfo);
					_mvShare.addObject("totalData", totalData);
					_mvShare.addObject("prodManuCatePaginate",
							productService.GetDataProductByCateMenuSortByTimePaginate(paginateInfo.getStart(),
									totalProductPage, manuid, catid));
				} else {
					if (sortBy.equals("price") && order.equals("asc")) {
						totalData = productService.findAllProductByCateMenuSortByASC(manuid, catid).size();
						PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
								Integer.parseInt(page));
						_mvShare.addObject("paginateInfo", paginateInfo);
						_mvShare.addObject("totalData", totalData);
						_mvShare.addObject("prodManuCatePaginate",
								productService.GetDataProductByCateMenuSortByASCPaginate(paginateInfo.getStart(),
										totalProductPage, manuid, catid));
					} else if (sortBy.equals("price") && order.equals("desc")) {
						totalData = productService.findAllProductByCateMenuSortByDESC(manuid, catid).size();
						PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
								Integer.parseInt(page));
						_mvShare.addObject("paginateInfo", paginateInfo);
						_mvShare.addObject("totalData", totalData);
						_mvShare.addObject("prodManuCatePaginate",
								productService.GetDataProductByCateMenuSortByDESCPaginate(paginateInfo.getStart(),
										totalProductPage, manuid, catid));
					} else if (sortBy.equals("ctime") && order.equals("desc")) {
						totalData = productService.findAllProductByCateMenuSortByTime(manuid, catid).size();
						PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
								Integer.parseInt(page));
						_mvShare.addObject("paginateInfo", paginateInfo);
						_mvShare.addObject("totalData", totalData);
						_mvShare.addObject("prodManuCatePaginate",
								productService.GetDataProductByCateMenuSortByTimePaginate(paginateInfo.getStart(),
										totalProductPage, manuid, catid));
					} else if (sortBy.equals("ctime") && order.equals("asc")) {
						totalData = productService.findAllProductByCateMenuSortByTimeASC(manuid, catid).size();
						PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
								Integer.parseInt(page));
						_mvShare.addObject("paginateInfo", paginateInfo);
						_mvShare.addObject("totalData", totalData);
						_mvShare.addObject("prodManuCatePaginate",
								productService.GetDataProductByCateMenuSortByTimeASCPaginate(paginateInfo.getStart(),
										totalProductPage, manuid, catid));
					} else if (sortBy.equals("alpha") && order.equals("asc")) {
						totalData = productService.findAllProductByCateMenuSortByAphaASC(manuid, catid).size();

						PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
								Integer.parseInt(page));
						_mvShare.addObject("paginateInfo", paginateInfo);
						_mvShare.addObject("totalData", totalData);
						_mvShare.addObject("prodManuCatePaginate",
								productService.GetDataProductByCateMenuSortByAphaASCPaginate(paginateInfo.getStart(),
										totalProductPage, manuid, catid));
					} else if (sortBy.equals("alpha") && order.equals("desc")) {
						totalData = productService.findAllProductByCateMenuSortByAphaDESC(manuid, catid).size();
						PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductPage,
								Integer.parseInt(page));
						_mvShare.addObject("paginateInfo", paginateInfo);
						_mvShare.addObject("totalData", totalData);
						_mvShare.addObject("prodManuCatePaginate",
								productService.GetDataProductByCateMenuSortByAphaDESCPaginate(paginateInfo.getStart(),
										totalProductPage, manuid, catid));
					}
				}
			}
			_mvShare.setViewName("web/product/categorymanu");
			_mvShare.addObject("categoryEntity", categoryEntity);
			_mvShare.addObject("manuEntity", manuEntity);
			_mvShare.addObject("slug", slug);
		}
		int availPage = (totalData + totalProductPage - 1) / totalProductPage;
		if (Integer.parseInt(page) > availPage) {
			page = availPage + "";
			return new ModelAndView("redirect:/" + slug + "?" + part1 + page + part2);
		}
		_mvShare.addObject("sort", sortBy);
		_mvShare.addObject("order", order);
		_mvShare.addObject("rppart1", part1);
		_mvShare.addObject("rppart2", part2);
		_mvShare.addObject("currentPage", page);
		return _mvShare;
	}
}
