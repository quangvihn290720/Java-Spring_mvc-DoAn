package com.khoaluantotnghiep.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.ProductEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface IProductService {
	public List<ProductEntity> findAllProduct();

	public List<ProductEntity> findAllProductByCateMenu(int manufacturer_id, int product_catid);

	public List<ProductEntity> GetDataProductByCateMenuPaginate(int start, int totalPage, int manufacturer_id,
			int product_catid);

	public List<ProductEntity> GetDataProductByCateMenuSortByDESCPaginate(int start, int totalPage, int manufacturer_id,
			int product_catid);

	public List<ProductEntity> GetDataProductByCateMenuSortByASCPaginate(int start, int totalPage, int manufacturer_id,
			int product_catid);

	public List<ProductEntity> findAllProductByCateMenuSortByDESC(int manufacturer_id, int product_catid);

	public List<ProductEntity> findAllProductByCateMenuSortByASC(int manufacturer_id, int product_catid);

	public List<ProductEntity> findAllProductMenu();

	public List<ProductEntity> findProductNew();

	public ProductEntity findProductById(int product_id);

	public ProductEntity findProductByIdObj(ProductEntity product);

	public List<ProductEntity> GetDataProductsPaginate(int start, int totalPage);

	public List<ProductEntity> SearchProduct(String productname);

	public List<ProductEntity> GetDataProductsSearchPaginate(int start, int totalPage, String productname);

	public List<ProductEntity> GetDataProductTrashPaginate(int start, int totalPage);

	public void addProduct(ProductEntity product);

	public void deleteProduct(int product_id);

	public void updateProduct(ProductEntity product);

	public void onOffProduct(int product_id,UserEntity loginInfo);

	public List<ProductEntity> findTrashProduct();

	public void delTrash(int product_id,UserEntity loginInfo);

	public void reTrash(int product_id,UserEntity loginInfo);

	public Map<Integer, String> mapProd();

	/// product catemenu sortby time

	public List<ProductEntity> findAllProductByCateMenuSortByTime(int manufacturer_id, int product_catid);

	public List<ProductEntity> GetDataProductByCateMenuSortByTimePaginate(int start, int totalPage, int manufacturer_id,
			int product_catid);

	/// all product asc

	public List<ProductEntity> findAllProductSortByASC();

	public List<ProductEntity> GetDataProductSortByASCPaginate(int start, int totalPage);

	/// all product desc
	public List<ProductEntity> GetDataProductSortByDESCPaginate(int start, int totalPage);

	public List<ProductEntity> findAllProductSortByDESC();

	// all product show

	public List<ProductEntity> findAllProductShow();

	public List<ProductEntity> GetDataProductShowPaginate(int start, int totalPage);

	// all product show time desc

	public List<ProductEntity> findAllProductShowSortByTime();

	public List<ProductEntity> GetDataProductShowSortByTimePaginate(int start, int totalPage);

	// all product show time asc

	public List<ProductEntity> findAllProductShowSortByTimeASC();

	public List<ProductEntity> GetDataProductShowSortByTimeASCPaginate(int start, int totalPage);

	// all product show sortby alpha DESC(z-a)

	public List<ProductEntity> findAllProductShowSortByAlphaDESC();

	public List<ProductEntity> GetDataProductShowSortByAlphaDESCPaginate(int start, int totalPage);

	// all product show sortby alpha asc(a-z)
	public List<ProductEntity> findAllProductShowSortByAlphaASC();

	public List<ProductEntity> GetDataProductShowSortByAlphaASCPaginate(int start, int totalPage);

	// prod search sortby price asc

	public List<ProductEntity> SearchProductSortByASC(String productname);

	public List<ProductEntity> GetDataProductsSearchSortByASCPaginate(int start, int totalPage, String productname);

	// prod search sortby price desc

	public List<ProductEntity> SearchProductSortByDESC(String productname);

	public List<ProductEntity> GetDataProductsSearchSortByDESCPaginate(int start, int totalPage, String productname);

	// prod search sortby ctime desc

	public List<ProductEntity> SearchProductSortByTime(String productname);

	public List<ProductEntity> GetDataProductsSearchSortByTimePaginate(int start, int totalPage, String productname);

	// prod search sortby ctime asc

	public List<ProductEntity> SearchProductSortByTimeASC(String productname);

	public List<ProductEntity> GetDataProductsSearchSortByTimeASCPaginate(int start, int totalPage, String productname);

	// product search sortby alpha a-z acs

	public List<ProductEntity> SearchProductSortByAlphaASC(String productname);

	public List<ProductEntity> GetDataProductsSearchSortByAlphaASCPaginate(int start, int totalPage,
			String productname);

	// product search sortby alpha a-z DESC

	public List<ProductEntity> SearchProductSortByAlphaDESC(String productname);

	public List<ProductEntity> GetDataProductsSearchSortByAlphaDESCPaginate(int start, int totalPage,
			String productname);

	// product catemenu sortby time asc

	public List<ProductEntity> findAllProductByCateMenuSortByTimeASC(int manufacturer_id, int product_catid);

	public List<ProductEntity> GetDataProductByCateMenuSortByTimeASCPaginate(int start, int totalPage,
			int manufacturer_id, int product_catid);

	// product catemenu sortby alpha asc

	public List<ProductEntity> findAllProductByCateMenuSortByAphaASC(int manufacturer_id, int product_catid);

	public List<ProductEntity> GetDataProductByCateMenuSortByAphaASCPaginate(int start, int totalPage,
			int manufacturer_id, int product_catid);

	// product catemenu sortby alpha desc

	public List<ProductEntity> findAllProductByCateMenuSortByAphaDESC(int manufacturer_id, int product_catid);

	public List<ProductEntity> GetDataProductByCateMenuSortByAphaDESCPaginate(int start, int totalPage,
			int manufacturer_id, int product_catid);

	/// product cate
	public List<ProductEntity> findAllProductByCate(int product_catid);

	public List<ProductEntity> GetDataProductByCatePaginate(int start, int totalPage, int product_catid);

	/// product cate sortby price asc
	public List<ProductEntity> findAllProductByCateSortByPriceASC(int product_catid);

	public List<ProductEntity> GetDataProductByCateSortByPriceASCPaginate(int start, int totalPage, int product_catid);

	/// product cate sortby price desc
	public List<ProductEntity> findAllProductByCateSortByPriceDESC(int product_catid);

	public List<ProductEntity> GetDataProductByCateSortByPriceDESCPaginate(int start, int totalPage, int product_catid);

	/// product cate sortby alpha asc
	public List<ProductEntity> findAllProductByCateSortByAlphaASC(int product_catid);

	public List<ProductEntity> GetDataProductByCateSortByAlphaASCPaginate(int start, int totalPage, int product_catid);

	/// product cate sortby alpha desc
	public List<ProductEntity> findAllProductByCateSortByAlphaDESC(int product_catid);

	public List<ProductEntity> GetDataProductByCateSortByAlphaDESCPaginate(int start, int totalPage, int product_catid);

	/// product cate sortby ctime asc
	public List<ProductEntity> findAllProductByCateSortByTimeASC(int product_catid);

	public List<ProductEntity> GetDataProductByCateSortByTimeASCPaginate(int start, int totalPage, int product_catid);

	/// product cate sortby ctime desc
	public List<ProductEntity> findAllProductByCateSortByTimeDESC(int product_catid);

	public List<ProductEntity> GetDataProductByCateSortByTimeDESCPaginate(int start, int totalPage, int product_catid);

	public boolean isNameExists(String name);

	public boolean isNameExists(String name, int id);

	public ProductEntity findProductBySlug(String slug);

	public boolean isSlugExists(String product_slug, int product_id);

	public boolean isSlugExists(String product_slug);
}
