package com.khoaluantotnghiep.controller.admin;

import java.util.Date;
import java.util.Map;

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

import com.khoaluantotnghiep.dto.PaginateDTO;
import com.khoaluantotnghiep.entity.NoteEntity;
import com.khoaluantotnghiep.entity.OptionsEntity;
import com.khoaluantotnghiep.entity.ProductEntity;
import com.khoaluantotnghiep.entity.ProductOptionsEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.entity.UtilitiesEntity;
import com.khoaluantotnghiep.service.impl.CategoryServiceImpl;
import com.khoaluantotnghiep.service.impl.ManufacturerServiceImpl;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.OptiongroupsServiceImpl;
import com.khoaluantotnghiep.service.impl.OptionsServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;
import com.khoaluantotnghiep.service.impl.ProductOptionsServiceImpl;
import com.khoaluantotnghiep.service.impl.ProductServiceImpl;
import com.khoaluantotnghiep.service.impl.UtilitiesServiceImpl;

@Controller(value = "productControllerOfAdmin")
public class ProductController extends BaseController {
	@Autowired
	CategoryServiceImpl categoryService;
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
	ManufacturerServiceImpl manufacturerService;
	@Autowired
	NoteServiceImpl noteService;
	private int totalDataPage = 5;

	@GetMapping("/quan-tri/san-pham")
	public ModelAndView product() {
		Map<Integer, String> mapCate = categoryService.mapCate();
		_mvShare.addObject("mapCate", mapCate);
		int totalData = productService.findAllProduct().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("productPaginate",
				productService.GetDataProductsPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/product/product");
		return _mvShare;
	}

	@GetMapping("/quan-tri/san-pham/{currentPage}")
	public ModelAndView product(@PathVariable String currentPage) {
		Map<Integer, String> mapCate = categoryService.mapCate();
		_mvShare.addObject("mapCate", mapCate);
		int totalData = productService.findAllProduct().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("productPaginate",
				productService.GetDataProductsPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/product/product");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/chi-tiet-san-pham/{product_id}")
	public ModelAndView detailProduct(@PathVariable int product_id) {
		_mvShare.addObject("product", productService.findProductById(product_id));
		Map<Integer, String> mapCate = categoryService.mapCate();
		_mvShare.addObject("mapCate", mapCate);
		Map<Integer, String> mapManu = manufacturerService.mapManu();
		_mvShare.addObject("mapManu", mapManu);
		_mvShare.addObject("listutilities", utilitiesService.findAllUtilitiesShow());
		_mvShare.addObject("listprodoption", prodOptionService.findAllProdOptionShow());
		_mvShare.addObject("listoption", optionsService.findAllOptionShow());
		_mvShare.addObject("listoptiongroup", optiongroupsService.findAllOptionGroupShow());
		_mvShare.setViewName("admin/product/productdetail");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/san-pham/add")
	public ModelAndView addProduct(@ModelAttribute("product") ProductEntity product) {
		_mvShare.addObject("cate", categoryService.findAllCate());
		_mvShare.addObject("manu", manufacturerService.findAllManufacturer());
		_mvShare.setViewName("admin/product/addproduct");
		return _mvShare;
	}

	@PostMapping(value = "/quan-tri/san-pham/save", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String saveAddProduct(HttpSession session, @ModelAttribute("product") ProductEntity product,
			@RequestParam(value = "image", required = false) MultipartFile photo,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		boolean check = true;
		if (productService.isNameExists(product.getProductname())) {
			redirectAttributes.addFlashAttribute("msgTitle", "T??n s???n ph???m ???? t???n t???i");
			check = false;
		}
		if (productService.isSlugExists(product.getProduct_slug())) {
			redirectAttributes.addFlashAttribute("msgSlug", "Slug s???n ph???m ???? t???n t???i");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", product);
			return "redirect:/quan-tri/san-pham/add";
		}
		try {
			product.setProductimg(saveFile(photo));
			product.setCreated_at(new Date());
			product.setUpdated_at(new Date());
			product.setUpdated_by(loginInfo.getUser_id());
			product.setCreated_by(loginInfo.getUser_id());
			productService.addProduct(product);
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? th??m s???n ph???m " + product.getProductname());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
			redirectAttributes.addFlashAttribute("msg", "Th??m th??nh c??ng!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Th??m kh??ng th??nh c??ng");
		}
		return "redirect:/quan-tri/san-pham";
	}

	@GetMapping(value = "/quan-tri/san-pham/thung-rac")
	public ModelAndView trashSlide() {
		int totalData = productService.findTrashProduct().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("prodTrashPaginate",
				productService.GetDataProductTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/product/trashproduct");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/san-pham/thung-rac/{currentPage}")
	public ModelAndView trashProduct(@PathVariable String currentPage) {
		int totalData = productService.findTrashProduct().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("prodTrashPaginate",
				productService.GetDataProductTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/product/trashproduct");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/san-pham/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editProduct(@ModelAttribute("product") ProductEntity product, @PathVariable int id) {
		_mvShare.addObject("prodItem", productService.findProductById(id));
		_mvShare.addObject("cate", categoryService.findAllCate());
		_mvShare.addObject("manu", manufacturerService.findAllManufacturer());
		_mvShare.setViewName("admin/product/editproduct");
		return _mvShare;
	}

	@PostMapping(value = "/quan-tri/san-pham/editsave", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String editsaveProduct(HttpSession session, @ModelAttribute("product") ProductEntity product,
			ModelMap modelMap, @RequestParam(value = "image", required = false) MultipartFile photo,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		boolean check = true;
		if (productService.isNameExists(product.getProductname(), product.getProduct_id())) {
			redirectAttributes.addFlashAttribute("msgTitle", "T??n s???n ph???m ???? t???n t???i");
			check = false;
		}
		if (productService.isSlugExists(product.getProduct_slug(), product.getProduct_id())) {
			redirectAttributes.addFlashAttribute("msgSlug", "Slug s???n ph???m ???? t???n t???i");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", product);
			return "redirect:/quan-tri/san-pham/edit/" + product.getProduct_id();
		}
		try {
			product.setProductimg(saveFile(photo));
			product.setUpdated_at(new Date());
			product.setProductdetail(request.getParameter("productdetail"));
			product.setUpdated_by(loginInfo.getUser_id());
			productService.updateProduct(product);
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? ch???nh s???a s???n ph???m " + product.getProduct_id());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
			redirectAttributes.addFlashAttribute("msg", "C???p nh???t th??nh c??ng");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "C???p nh???t kh??ng th??nh c??ng");
		}
		return "redirect:/quan-tri/san-pham";
	}

	@GetMapping(value = "/quan-tri/san-pham/delete/{id}")
	public String deleteProduct(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			productService.deleteProduct(id);
			redirectAttributes.addFlashAttribute("msg", "X??a th??nh c??ng");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? x??a s???n ph???m " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "X??a kh??ng th??nh c??ng");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/san-pham/trash/{id}")
	public String delProduct(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			productService.delTrash(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao t??c th??nh c??ng");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? x??a t???m th???i s???n ph???m " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao t??c kh??ng th??nh c??ng");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/san-pham/retrash/{id}")
	public String retrashProduct(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			productService.reTrash(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao t??c th??nh c??ng");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin b??? x??a t???m th???i s???n ph???m " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao t??c kh??ng th??nh c??ng");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/san-pham/status/{id}")
	public String onOffProduct(@PathVariable int id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			productService.onOffProduct(id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao t??c th??nh c??ng");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin thay ?????i tr???ng th??i s???n ph???m " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao t??c kh??ng th??nh c??ng");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping(value = "/quan-tri/san-pham/them-tien-ich/{id}")
	public ModelAndView AddUtil(@ModelAttribute("utilities") UtilitiesEntity utilities, @PathVariable int id,
			final RedirectAttributes redirectAttributes) {
		_mvShare.addObject("prodItem", productService.findProductById(id));
		_mvShare.setViewName("admin/product/addutilprod");
		return _mvShare;
	}

	@PostMapping(value = "/quan-tri/san-pham/them-tien-ich/{id}")
	public String SaveUtil(HttpSession session, ModelMap modelMap,
			@ModelAttribute("utilities") UtilitiesEntity utilities, @PathVariable int id,
			final RedirectAttributes redirectAttributes) {
		modelMap.put("prodItem", productService.findProductById(id));
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		try {
			utilities.setCreated_at(new Date());
			utilities.setUpdated_at(new Date());
			utilities.setUpdated_by(loginInfo.getUser_id());
			utilities.setCreated_by(loginInfo.getUser_id());
			utilities.setProduct_id(id);
			utilitiesService.addUtilities(utilities);
			redirectAttributes.addFlashAttribute("msg", "Th??m h??nh c??ng");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin th??m ti???n ??ch s???n ph???m " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Th??m kh??ng th??nh c??ng");
		}
		return "redirect:/quan-tri/chi-tiet-san-pham/{id}";
	}

	@GetMapping(value = "/quan-tri/san-pham/them-lua-chon/{id}")
	public ModelAndView AddOption(@ModelAttribute("prodop") ProductOptionsEntity prodop, @PathVariable int id,
			final RedirectAttributes redirectAttributes) {
		_mvShare.addObject("prodItem", productService.findProductById(id));
		_mvShare.addObject("option", optionsService.findAllOption());
		_mvShare.setViewName("admin/product/addoptionprod");
		return _mvShare;
	}

	@PostMapping(value = "/quan-tri/san-pham/them-lua-chon/{id}")
	public String saveOption(HttpSession session, ModelMap modelMap,
			@ModelAttribute("prodop") ProductOptionsEntity prodop, @PathVariable int id,
			final RedirectAttributes redirectAttributes) {
		modelMap.put("prodItem", productService.findProductById(id));
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		try {
			prodop.setCreated_at(new Date());
			prodop.setUpdated_at(new Date());
			prodop.setUpdated_by(loginInfo.getUser_id());
			prodop.setCreated_by(loginInfo.getUser_id());
			prodop.setProduct_id(id);
			int op = prodop.getOption_id();
			OptionsEntity optionsEntity = optionsService.findOptionId(op);
			prodop.setOptiongroup_id(optionsEntity.getOptiongroup_id());
			prodOptionService.addProdOption(prodop);
			redirectAttributes.addFlashAttribute("msg", "Th??m h??nh c??ng");
			// them note ????? qu???n l??
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin th??m l???a ch???n s???n ph???m " + id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Th??m kh??ng th??nh c??ng");
		}
		return "redirect:/quan-tri/chi-tiet-san-pham/{id}";
	}
}
