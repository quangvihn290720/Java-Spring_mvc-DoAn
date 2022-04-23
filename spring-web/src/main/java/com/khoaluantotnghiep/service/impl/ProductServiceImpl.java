package com.khoaluantotnghiep.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.ProductDAO;
import com.khoaluantotnghiep.entity.ProductEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	ProductDAO productDAO;

	@Override
	public List<ProductEntity> findAllProduct() {
		return productDAO.findAllProduct();
	}

	@Override
	public List<ProductEntity> findProductNew() {
		return productDAO.findProductNew();
	}

	@Override
	public ProductEntity findProductByIdObj(ProductEntity product) {
		return productDAO.findProductByIdObj(product);
	}

	@Override
	public List<ProductEntity> GetDataProductsPaginate(int start, int totalPage) {
		return productDAO.GetDataProductsPaginate(start, totalPage);
	}

	@Override
	public List<ProductEntity> findAllProductShow() {
		return productDAO.findAllProductShow();
	}

	@Override
	public List<ProductEntity> SearchProduct(String productname) {
		return productDAO.SearchProduct(productname);
	}

	@Override
	public List<ProductEntity> GetDataProductsSearchPaginate(int start, int totalPage, String productname) {
		return productDAO.GetDataProductsSearchPaginate(start, totalPage, productname);
	}

	@Override
	public List<ProductEntity> GetDataProductTrashPaginate(int start, int totalPage) {
		return productDAO.GetDataProductTrashPaginate(start, totalPage);
	}

	@Override
	public void addProduct(ProductEntity product) {
		productDAO.addProduct(product);
	}

	@Override
	public void deleteProduct(int product_id) {
		productDAO.deleteProduct(product_id);
	}

	@Override
	public void updateProduct(ProductEntity product) {
		productDAO.updateProduct(product);
	}

	@Override
	public void onOffProduct(int product_id,UserEntity loginInfo) {
		productDAO.onOffProduct(product_id, loginInfo);
	}

	@Override
	public List<ProductEntity> findTrashProduct() {
		return productDAO.findTrashProduct();
	}

	@Override
	public void delTrash(int product_id,UserEntity loginInfo) {
		productDAO.delTrash(product_id, loginInfo);
	}

	@Override
	public void reTrash(int product_id,UserEntity loginInfo) {
		productDAO.reTrash(product_id, loginInfo);
	}

	@Override
	public ProductEntity findProductById(int product_id) {
		return productDAO.findProductById(product_id);
	}

	@Override
	public Map<Integer, String> mapProd() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<ProductEntity> list = productDAO.findAllProduct();
		for (ProductEntity tp : list) {
			map.put(tp.getProduct_id(), tp.getProductname());
		}
		return map;
	}

	@Override
	public List<ProductEntity> findAllProductMenu() {
		return productDAO.findAllProductMenu();
	}

	@Override
	public List<ProductEntity> findAllProductByCateMenu(int manufacturer_id, int product_catid) {
		return productDAO.findAllProductByCateMenu(manufacturer_id, product_catid);
	}

	@Override
	public List<ProductEntity> GetDataProductByCateMenuPaginate(int start, int totalPage, int manufacturer_id,
			int product_catid) {
		return productDAO.GetDataProductByCateMenuPaginate(start, totalPage, manufacturer_id, product_catid);
	}

	@Override
	public List<ProductEntity> GetDataProductByCateMenuSortByASCPaginate(int start, int totalPage, int manufacturer_id,
			int product_catid) {
		return productDAO.GetDataProductByCateMenuSortByASCPaginate(start, totalPage, manufacturer_id, product_catid);
	}

	@Override
	public List<ProductEntity> findAllProductByCateMenuSortByASC(int manufacturer_id, int product_catid) {
		return productDAO.findAllProductByCateMenuSortByASC(manufacturer_id, product_catid);
	}

	@Override
	public List<ProductEntity> GetDataProductByCateMenuSortByDESCPaginate(int start, int totalPage, int manufacturer_id,
			int product_catid) {
		return productDAO.GetDataProductByCateMenuSortByDESCPaginate(start, totalPage, manufacturer_id, product_catid);
	}

	@Override
	public List<ProductEntity> findAllProductByCateMenuSortByDESC(int manufacturer_id, int product_catid) {
		return productDAO.findAllProductByCateMenuSortByDESC(manufacturer_id, product_catid);
	}

	@Override
	public List<ProductEntity> findAllProductByCate(int product_catid) {
		return productDAO.findAllProductByCate(product_catid);
	}

	@Override
	public List<ProductEntity> GetDataProductByCatePaginate(int start, int totalPage, int product_catid) {
		return productDAO.GetDataProductByCatePaginate(start, totalPage, product_catid);
	}

	@Override
	public List<ProductEntity> findAllProductSortByASC() {
		return productDAO.findAllProductSortByASC();
	}

	@Override
	public List<ProductEntity> GetDataProductSortByASCPaginate(int start, int totalPage) {
		return productDAO.GetDataProductSortByASCPaginate(start, totalPage);
	}

	@Override
	public List<ProductEntity> GetDataProductSortByDESCPaginate(int start, int totalPage) {
		return productDAO.GetDataProductSortByDESCPaginate(start, totalPage);
	}

	@Override
	public List<ProductEntity> findAllProductSortByDESC() {
		return productDAO.findAllProductSortByDESC();
	}

	@Override
	public List<ProductEntity> GetDataProductShowPaginate(int start, int totalPage) {
		return productDAO.GetDataProductShowPaginate(start, totalPage);
	}

	@Override
	public List<ProductEntity> findAllProductShowSortByTime() {
		return productDAO.findAllProductShowSortByTime();
	}

	@Override
	public List<ProductEntity> GetDataProductShowSortByTimePaginate(int start, int totalPage) {
		return productDAO.GetDataProductShowSortByTimePaginate(start, totalPage);
	}

	@Override
	public List<ProductEntity> findAllProductByCateMenuSortByTime(int manufacturer_id, int product_catid) {
		return productDAO.findAllProductByCateMenuSortByTime(manufacturer_id, product_catid);
	}

	@Override
	public List<ProductEntity> GetDataProductByCateMenuSortByTimePaginate(int start, int totalPage, int manufacturer_id,
			int product_catid) {
		return productDAO.GetDataProductByCateMenuSortByTimePaginate(start, totalPage, manufacturer_id, product_catid);
	}

	@Override
	public List<ProductEntity> SearchProductSortByASC(String productname) {
		return productDAO.SearchProductSortByASC(productname);
	}

	@Override
	public List<ProductEntity> GetDataProductsSearchSortByASCPaginate(int start, int totalPage, String productname) {
		return productDAO.GetDataProductsSearchSortByASCPaginate(start, totalPage, productname);
	}

	@Override
	public List<ProductEntity> SearchProductSortByDESC(String productname) {
		return productDAO.SearchProductSortByDESC(productname);
	}

	@Override
	public List<ProductEntity> GetDataProductsSearchSortByDESCPaginate(int start, int totalPage, String productname) {
		return productDAO.GetDataProductsSearchSortByDESCPaginate(start, totalPage, productname);
	}

	@Override
	public List<ProductEntity> SearchProductSortByTime(String productname) {
		return productDAO.SearchProductSortByTime(productname);
	}

	@Override
	public List<ProductEntity> GetDataProductsSearchSortByTimePaginate(int start, int totalPage, String productname) {
		return productDAO.GetDataProductsSearchSortByTimePaginate(start, totalPage, productname);
	}

	@Override
	public boolean isNameExists(String name) {
		return productDAO.isNameExists(name);
	}

	@Override
	public boolean isNameExists(String name, int id) {
		return productDAO.isNameExists(name, id);
	}

	@Override
	public List<ProductEntity> findAllProductShowSortByTimeASC() {
		return productDAO.findAllProductShowSortByTimeASC();
	}

	@Override
	public List<ProductEntity> GetDataProductShowSortByTimeASCPaginate(int start, int totalPage) {
		return productDAO.GetDataProductShowSortByTimeASCPaginate(start, totalPage);
	}

	@Override
	public List<ProductEntity> findAllProductShowSortByAlphaDESC() {
		return productDAO.findAllProductShowSortByAlphaDESC();
	}

	@Override
	public List<ProductEntity> GetDataProductShowSortByAlphaDESCPaginate(int start, int totalPage) {
		return productDAO.GetDataProductShowSortByAlphaDESCPaginate(start, totalPage);
	}

	@Override
	public List<ProductEntity> findAllProductShowSortByAlphaASC() {
		return productDAO.findAllProductShowSortByAlphaASC();
	}

	@Override
	public List<ProductEntity> GetDataProductShowSortByAlphaASCPaginate(int start, int totalPage) {
		return productDAO.GetDataProductShowSortByAlphaASCPaginate(start, totalPage);
	}

	@Override
	public List<ProductEntity> SearchProductSortByTimeASC(String productname) {
		return productDAO.SearchProductSortByTimeASC(productname);
	}

	@Override
	public List<ProductEntity> GetDataProductsSearchSortByTimeASCPaginate(int start, int totalPage,
			String productname) {
		return productDAO.GetDataProductsSearchSortByTimeASCPaginate(start, totalPage, productname);
	}

	@Override
	public List<ProductEntity> SearchProductSortByAlphaASC(String productname) {
		return productDAO.SearchProductSortByAlphaASC(productname);
	}

	@Override
	public List<ProductEntity> GetDataProductsSearchSortByAlphaASCPaginate(int start, int totalPage,
			String productname) {
		return productDAO.GetDataProductsSearchSortByAlphaASCPaginate(start, totalPage, productname);
	}

	@Override
	public List<ProductEntity> SearchProductSortByAlphaDESC(String productname) {
		return productDAO.SearchProductSortByAlphaDESC(productname);
	}

	@Override
	public List<ProductEntity> GetDataProductsSearchSortByAlphaDESCPaginate(int start, int totalPage,
			String productname) {
		return productDAO.GetDataProductsSearchSortByAlphaDESCPaginate(start, totalPage, productname);
	}

	@Override
	public List<ProductEntity> findAllProductByCateMenuSortByTimeASC(int manufacturer_id, int product_catid) {
		return productDAO.findAllProductByCateMenuSortByTimeASC(manufacturer_id, product_catid);
	}

	@Override
	public List<ProductEntity> GetDataProductByCateMenuSortByTimeASCPaginate(int start, int totalPage,
			int manufacturer_id, int product_catid) {
		return productDAO.GetDataProductByCateMenuSortByTimeASCPaginate(start, totalPage, manufacturer_id,
				product_catid);
	}

	@Override
	public List<ProductEntity> findAllProductByCateMenuSortByAphaASC(int manufacturer_id, int product_catid) {
		return productDAO.findAllProductByCateMenuSortByAphaASC(manufacturer_id, product_catid);
	}

	@Override
	public List<ProductEntity> GetDataProductByCateMenuSortByAphaASCPaginate(int start, int totalPage,
			int manufacturer_id, int product_catid) {
		return productDAO.GetDataProductByCateMenuSortByAphaASCPaginate(start, totalPage, manufacturer_id,
				product_catid);
	}

	@Override
	public List<ProductEntity> findAllProductByCateMenuSortByAphaDESC(int manufacturer_id, int product_catid) {
		return productDAO.findAllProductByCateMenuSortByAphaDESC(manufacturer_id, product_catid);
	}

	@Override
	public List<ProductEntity> GetDataProductByCateMenuSortByAphaDESCPaginate(int start, int totalPage,
			int manufacturer_id, int product_catid) {
		return productDAO.GetDataProductByCateMenuSortByAphaDESCPaginate(start, totalPage, manufacturer_id,
				product_catid);
	}

	@Override
	public List<ProductEntity> findAllProductByCateSortByPriceASC(int product_catid) {
		return productDAO.findAllProductByCateSortByPriceASC(product_catid);
	}

	@Override
	public List<ProductEntity> GetDataProductByCateSortByPriceASCPaginate(int start, int totalPage, int product_catid) {
		return productDAO.GetDataProductByCateSortByPriceASCPaginate(start, totalPage, product_catid);
	}

	@Override
	public List<ProductEntity> findAllProductByCateSortByPriceDESC(int product_catid) {
		return productDAO.findAllProductByCateSortByPriceDESC(product_catid);
	}

	@Override
	public List<ProductEntity> GetDataProductByCateSortByPriceDESCPaginate(int start, int totalPage,
			int product_catid) {
		return productDAO.GetDataProductByCateSortByPriceDESCPaginate(start, totalPage, product_catid);
	}

	@Override
	public List<ProductEntity> findAllProductByCateSortByAlphaASC(int product_catid) {
		return productDAO.findAllProductByCateSortByAlphaASC(product_catid);
	}

	@Override
	public List<ProductEntity> GetDataProductByCateSortByAlphaASCPaginate(int start, int totalPage, int product_catid) {
		return productDAO.GetDataProductByCateSortByAlphaASCPaginate(start, totalPage, product_catid);
	}

	@Override
	public List<ProductEntity> findAllProductByCateSortByAlphaDESC(int product_catid) {
		return productDAO.findAllProductByCateSortByAlphaDESC(product_catid);
	}

	@Override
	public List<ProductEntity> GetDataProductByCateSortByAlphaDESCPaginate(int start, int totalPage,
			int product_catid) {
		return productDAO.GetDataProductByCateSortByAlphaDESCPaginate(start, totalPage, product_catid);
	}

	@Override
	public List<ProductEntity> findAllProductByCateSortByTimeASC(int product_catid) {
		return productDAO.findAllProductByCateSortByTimeASC(product_catid);
	}

	@Override
	public List<ProductEntity> GetDataProductByCateSortByTimeASCPaginate(int start, int totalPage, int product_catid) {
		return productDAO.GetDataProductByCateSortByTimeASCPaginate(start, totalPage, product_catid);
	}

	@Override
	public List<ProductEntity> findAllProductByCateSortByTimeDESC(int product_catid) {
		return productDAO.findAllProductByCateSortByTimeDESC(product_catid);
	}

	@Override
	public List<ProductEntity> GetDataProductByCateSortByTimeDESCPaginate(int start, int totalPage, int product_catid) {
		return productDAO.GetDataProductByCateSortByTimeDESCPaginate(start, totalPage, product_catid);
	}
	@Override
	public ProductEntity findProductBySlug(String slug) {
		return productDAO.findProductBySlug(slug);
	}
	@Override
	public boolean isSlugExists(String product_slug) {
		return productDAO.isSlugExists(product_slug);
	}
	public boolean isSlugExists(String product_slug, int product_id) {
		return productDAO.isSlugExists(product_slug,product_id);
	}
}
