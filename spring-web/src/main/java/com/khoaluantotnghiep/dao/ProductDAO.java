package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.ProductEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.ProductMapper;

@Repository
@Transactional
public class ProductDAO extends BaseDAO {

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * ");
		sql.append("FROM ");
		sql.append("product ");
		return sql;
	}

	private StringBuffer SqlProducts() {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND product_status != 0 ORDER BY product_status ASC ");
		return sql;
	}

	private String SqlProductNew() {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND product_status = 1 ORDER BY created_at desc ");
		return sql.toString();
	}

	private String SqlProductsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlProducts();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductsPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlProducts();
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductsPaginate(start, totalPage);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	public List<ProductEntity> findAllProduct() {
		String sql = SqlProducts().toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	public List<ProductEntity> findAllProductMenu() {
		String sql = "SELECT * FROM `product` GROUP BY `manufacturer_id`,`product_catid` ";
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product where product_status = 0 ");
		return sql;
	}

	private String SqlProductTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductTrashsPaginate(start, totalPage);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	public List<ProductEntity> findProductNew() {
		String sql = SqlProductNew();
		List<ProductEntity> listProductNew = jdbcTemplate.query(sql, new ProductMapper());
		return listProductNew;
	}

	public ProductEntity findProductById(int product_id) {
		String sql = "SELECT * FROM product WHERE product_id = " + product_id + " LIMIT 1 ";
		ProductEntity product = jdbcTemplate.queryForObject(sql, new ProductMapper());
		return product;
	}

	public ProductEntity findProductByIdObj(ProductEntity product) {
		String sql = "SELECT * FROM product where product_id = " + product.getProduct_id();
		List<ProductEntity> result = jdbcTemplate.query(sql, new ProductMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public void addProduct(ProductEntity product) {
		String sql = "INSERT INTO product (productname,`productprice`,`productpricesale`,`productweight`,`productdetail`,`productshortdesc`,`productimg`,"
				+ "product_guarantee,`product_condition`,`product_catid`,`manufacturer_id`,`product_slug`,`product_status`,`created_at`,`updated_at`,`created_by`,`updated_by`,`available`) VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, product.getProductname(), product.getProductprice(), product.getProductpricesale(),
				product.getProductweight(), product.getProductdetail(), product.getProductshortdesc(),
				product.getProductimg(), product.getProduct_guarantee(), product.getProduct_condition(),
				product.getProduct_catid(), product.getManufacturer_id(), product.getProduct_slug(),
				product.getProduct_status(), product.getCreated_at(), product.getUpdated_at(), product.getCreated_by(),
				product.getUpdated_by(), product.getProduct_available());
	}

	public void deleteProduct(int product_id) {
		String sql = "DELETE FROM product WHERE product_id = " + product_id;
		jdbcTemplate.update(sql);
	}

	public void updateProduct(ProductEntity product) {
		String sql = "";
		if (product.getProductimg() != null && !product.getProductimg().isEmpty()) {
			sql = "UPDATE product SET productname = ?, productprice = ?, productpricesale = ?, productweight = ?, productdetail = ?, productshortdesc = ?, productimg = ?,"
					+ " product_guarantee = ?,`product_condition` = ?, product_catid = ?, manufacturer_id = ?, product_slug = ?, product_status = ?, updated_at = ?, updated_by = ?, available = ? WHERE product_id = ?";
			jdbcTemplate.update(sql, product.getProductname(), product.getProductprice(), product.getProductpricesale(),
					product.getProductweight(), product.getProductdetail(), product.getProductshortdesc(),
					product.getProductimg(), product.getProduct_guarantee(), product.getProduct_condition(),
					product.getProduct_catid(), product.getManufacturer_id(), product.getProduct_slug(),
					product.getProduct_status(), product.getUpdated_at(), product.getUpdated_by(),
					product.getProduct_available(), product.getProduct_id());
		} else {
			sql = "UPDATE product SET productname = ?, productprice = ?, productpricesale = ?, productweight = ?, productdetail = ?, productshortdesc = ?,"
					+ " product_guarantee = ?,product_condition = ?, product_catid = ?, manufacturer_id = ?, product_slug = ?, product_status = ?, updated_at = ?, updated_by = ?, available = ? WHERE product_id = ?";
			jdbcTemplate.update(sql, product.getProductname(), product.getProductprice(), product.getProductpricesale(),
					product.getProductweight(), product.getProductdetail(), product.getProductshortdesc(),
					product.getProduct_guarantee(), product.getProduct_condition(), product.getProduct_catid(),
					product.getManufacturer_id(), product.getProduct_slug(), product.getProduct_status(),
					product.getUpdated_at(), product.getUpdated_by(), product.getProduct_available(),
					product.getProduct_id());
		}
	}

	public void onOffProduct(int product_id, UserEntity loginInfo) {
		String sql = "UPDATE product SET product_status = case when  product_status =1 then 2 when  product_status =2 then 1 end,updated_by = "
				 +loginInfo.getUser_id() + ", updated_at = NOW() where  product_id =" + product_id;
		jdbcTemplate.update(sql);
	}

	public List<ProductEntity> findTrashProduct() {
		StringBuffer sql = SqlTrash();
		List<ProductEntity> list = jdbcTemplate.query(sql.toString(), new ProductMapper());
		return list;
	}

	public void delTrash(int product_id, UserEntity loginInfo) {
		String sql = "UPDATE product SET product_status = 0,updated_by = " + +loginInfo.getUser_id()
				+ ", updated_at = NOW()  where  product_id =" + product_id;
		jdbcTemplate.update(sql);
	}

	public void reTrash(int product_id, UserEntity loginInfo) {
		String sql = "UPDATE product SET product_status = 2,updated_by = " + +loginInfo.getUser_id()
				+ ", updated_at = NOW()  where  product_id =" + product_id;
		jdbcTemplate.update(sql);
	}

	/// product sortby asc

	private StringBuffer sqlfindAllProductSortByASC() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product WHERE product_status = 1  ORDER BY productpricesale ASC ");
		return sql;
	}

	private String SqlProductSortByASCPaginate(int start, int totalPage) {
		StringBuffer sql = sqlfindAllProductSortByASC();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> findAllProductSortByASC() {
		String sql = sqlfindAllProductSortByASC().toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	public List<ProductEntity> GetDataProductSortByASCPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = sqlfindAllProductSortByASC();
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductSortByASCPaginate(start, totalPage);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	/// product sortby desc

	private StringBuffer sqlfindAllProductSortByDESC() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product WHERE product_status = 1  ORDER BY productpricesale DESC ");
		return sql;
	}

	private String SqlProductSortByDESCPaginate(int start, int totalPage) {
		StringBuffer sql = sqlfindAllProductSortByDESC();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> findAllProductSortByDESC() {
		String sql = sqlfindAllProductSortByDESC().toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	public List<ProductEntity> GetDataProductSortByDESCPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = sqlfindAllProductSortByDESC();
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductSortByDESCPaginate(start, totalPage);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	/// all product show

	private StringBuffer SqlProductShow() {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND product_status = 1 ");
		return sql;
	}

	private String SqlProductShowPaginate(int start, int totalPage) {
		StringBuffer sql = SqlProductShow();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> findAllProductShow() {
		String sql = SqlProductShow().toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	public List<ProductEntity> GetDataProductShowPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlProductShow();
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductShowPaginate(start, totalPage);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	// all product show sortby time DESC

	private StringBuffer SqlProductShowSortByTime() {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND product_status = 1 ORDER BY created_at DESC ");
		return sql;
	}

	private String SqlProductShowSortByTimePaginate(int start, int totalPage) {
		StringBuffer sql = SqlProductShowSortByTime();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> findAllProductShowSortByTime() {
		String sql = SqlProductShowSortByTime().toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	public List<ProductEntity> GetDataProductShowSortByTimePaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlProductShowSortByTime();
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductShowSortByTimePaginate(start, totalPage);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}
	// all product show sortby alpha asc(a-z)

	private StringBuffer SqlProductShowSortByAlphaASC() {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND product_status = 1 ORDER BY productname ASC ");
		return sql;
	}

	private String SqlProductShowSortByAlphaASCPaginate(int start, int totalPage) {
		StringBuffer sql = SqlProductShowSortByAlphaASC();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> findAllProductShowSortByAlphaASC() {
		String sql = SqlProductShowSortByAlphaASC().toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	public List<ProductEntity> GetDataProductShowSortByAlphaASCPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlProductShowSortByAlphaASC();
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductShowSortByAlphaASCPaginate(start, totalPage);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}
	// all product show sortby alpha DESC(z-a)

	private StringBuffer SqlProductShowSortByAlphaDESC() {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND product_status = 1 ORDER BY productname DESC ");
		return sql;
	}

	private String SqlProductShowSortByAlphaDESCPaginate(int start, int totalPage) {
		StringBuffer sql = SqlProductShowSortByAlphaDESC();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> findAllProductShowSortByAlphaDESC() {
		String sql = SqlProductShowSortByAlphaDESC().toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	public List<ProductEntity> GetDataProductShowSortByAlphaDESCPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlProductShowSortByAlphaDESC();
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductShowSortByAlphaDESCPaginate(start, totalPage);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}
	// all product show sortby time ASC

	private StringBuffer SqlProductShowSortByTimeASC() {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND product_status = 1 ORDER BY created_at ASC ");
		return sql;
	}

	private String SqlProductShowSortByTimeASCPaginate(int start, int totalPage) {
		StringBuffer sql = SqlProductShowSortByTimeASC();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> findAllProductShowSortByTimeASC() {
		String sql = SqlProductShowSortByTimeASC().toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	public List<ProductEntity> GetDataProductShowSortByTimeASCPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlProductShowSortByTimeASC();
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductShowSortByTimeASCPaginate(start, totalPage);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	// product search

	private StringBuffer SqlProductseach(String productname) {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND product_status = 1 ");
		sql.append("AND productname like  '%" + productname + "%' ");
		return sql;
	}

	public List<ProductEntity> SearchProduct(String productname) {
		String sql = SqlProductseach(productname).toString();
		return jdbcTemplate.query(sql, new ProductMapper());
	}

	private String SqlProductsearchPaginate(int start, int totalPage, String productname) {
		StringBuffer sql = SqlProductseach(productname);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductsSearchPaginate(int start, int totalPage, String productname) {
		StringBuffer sqlGetData = SqlProductseach(productname);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductsearchPaginate(start, totalPage, productname);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	// product search sortby price asc

	private StringBuffer SqlProductseachSortByASC(String productname) {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND product_status = 1 ");
		sql.append("AND productname like  '%" + productname + "%' ORDER BY productpricesale ASC ");
		return sql;
	}

	public List<ProductEntity> SearchProductSortByASC(String productname) {
		String sql = SqlProductseachSortByASC(productname).toString();
		return jdbcTemplate.query(sql, new ProductMapper());
	}

	private String SqlProductsearchSortByASCPaginate(int start, int totalPage, String productname) {
		StringBuffer sql = SqlProductseachSortByASC(productname);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductsSearchSortByASCPaginate(int start, int totalPage, String productname) {
		StringBuffer sqlGetData = SqlProductseachSortByASC(productname);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductsearchSortByASCPaginate(start, totalPage, productname);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	// product search sortby price desc

	private StringBuffer SqlProductseachSortByDESC(String productname) {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND product_status = 1 ");
		sql.append("AND productname like  '%" + productname + "%' ORDER BY productpricesale DESC ");
		return sql;
	}

	public List<ProductEntity> SearchProductSortByDESC(String productname) {
		String sql = SqlProductseachSortByDESC(productname).toString();
		return jdbcTemplate.query(sql, new ProductMapper());
	}

	private String SqlProductsearchSortByDESCPaginate(int start, int totalPage, String productname) {
		StringBuffer sql = SqlProductseachSortByDESC(productname);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductsSearchSortByDESCPaginate(int start, int totalPage, String productname) {
		StringBuffer sqlGetData = SqlProductseachSortByDESC(productname);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductsearchSortByDESCPaginate(start, totalPage, productname);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	// product search sortby ctime desc

	private StringBuffer SqlProductseachSortByTime(String productname) {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND product_status = 1 ");
		sql.append("AND productname like  '%" + productname + "%' ORDER BY created_at DESC ");
		return sql;
	}

	public List<ProductEntity> SearchProductSortByTime(String productname) {
		String sql = SqlProductseachSortByTime(productname).toString();
		return jdbcTemplate.query(sql, new ProductMapper());
	}

	private String SqlProductsearchSortByTimePaginate(int start, int totalPage, String productname) {
		StringBuffer sql = SqlProductseachSortByTime(productname);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductsSearchSortByTimePaginate(int start, int totalPage, String productname) {
		StringBuffer sqlGetData = SqlProductseachSortByTime(productname);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductsearchSortByTimePaginate(start, totalPage, productname);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}
	// product search sortby ctime acs

	private StringBuffer SqlProductseachSortByTimeASC(String productname) {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND product_status = 1 ");
		sql.append("AND productname like  '%" + productname + "%' ORDER BY created_at ASC ");
		return sql;
	}

	public List<ProductEntity> SearchProductSortByTimeASC(String productname) {
		String sql = SqlProductseachSortByTimeASC(productname).toString();
		return jdbcTemplate.query(sql, new ProductMapper());
	}

	private String SqlProductsearchSortByTimeASCPaginate(int start, int totalPage, String productname) {
		StringBuffer sql = SqlProductseachSortByTimeASC(productname);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductsSearchSortByTimeASCPaginate(int start, int totalPage,
			String productname) {
		StringBuffer sqlGetData = SqlProductseachSortByTimeASC(productname);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductsearchSortByTimeASCPaginate(start, totalPage, productname);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	// product search sortby alpha a-z acs

	private StringBuffer SqlProductseachSortByAlphaASC(String productname) {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND product_status = 1 ");
		sql.append("AND productname like  '%" + productname + "%' ORDER BY productname ASC ");
		return sql;
	}

	public List<ProductEntity> SearchProductSortByAlphaASC(String productname) {
		String sql = SqlProductseachSortByAlphaASC(productname).toString();
		return jdbcTemplate.query(sql, new ProductMapper());
	}

	private String SqlProductsearchSortByAlphaASCPaginate(int start, int totalPage, String productname) {
		StringBuffer sql = SqlProductseachSortByAlphaASC(productname);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductsSearchSortByAlphaASCPaginate(int start, int totalPage,
			String productname) {
		StringBuffer sqlGetData = SqlProductseachSortByAlphaASC(productname);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductsearchSortByAlphaASCPaginate(start, totalPage, productname);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	// product search sortby alpha a-z DESC

	private StringBuffer SqlProductseachSortByAlphaDESC(String productname) {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND product_status = 1 ");
		sql.append("AND productname like  '%" + productname + "%' ORDER BY productname DESC ");
		return sql;
	}

	public List<ProductEntity> SearchProductSortByAlphaDESC(String productname) {
		String sql = SqlProductseachSortByAlphaDESC(productname).toString();
		return jdbcTemplate.query(sql, new ProductMapper());
	}

	private String SqlProductsearchSortByAlphaDESCPaginate(int start, int totalPage, String productname) {
		StringBuffer sql = SqlProductseachSortByAlphaDESC(productname);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductsSearchSortByAlphaDESCPaginate(int start, int totalPage,
			String productname) {
		StringBuffer sqlGetData = SqlProductseachSortByAlphaDESC(productname);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductsearchSortByAlphaDESCPaginate(start, totalPage, productname);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	// AllProductByCateMenu

	private StringBuffer sqlfindAllProductByCateMenu(int manufacturer_id, int product_catid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product WHERE product_status = 1 AND manufacturer_id = ");
		sql.append(" '" + manufacturer_id + "' ");
		sql.append(" AND product_catid = ");
		sql.append(" '" + product_catid + "' ");
		return sql;
	}

	public List<ProductEntity> findAllProductByCateMenu(int manufacturer_id, int product_catid) {
		String sql = sqlfindAllProductByCateMenu(manufacturer_id, product_catid).toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	private String SqlProductByCateMenuPaginate(int start, int totalPage, int manufacturer_id, int product_catid) {
		StringBuffer sql = sqlfindAllProductByCateMenu(manufacturer_id, product_catid);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductByCateMenuPaginate(int start, int totalPage, int manufacturer_id,
			int product_catid) {
		StringBuffer sqlGetData = sqlfindAllProductByCateMenu(manufacturer_id, product_catid);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductByCateMenuPaginate(start, totalPage, manufacturer_id, product_catid);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	/// catemenu sortby price asc

	private StringBuffer sqlfindAllProductByCateMenuSortByASC(int manufacturer_id, int product_catid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product WHERE product_status = 1 AND  manufacturer_id = ");
		sql.append(" '" + manufacturer_id + "' ");
		sql.append(" AND product_catid = ");
		sql.append(" '" + product_catid + "' ORDER BY productpricesale ASC ");
		return sql;
	}

	public List<ProductEntity> findAllProductByCateMenuSortByASC(int manufacturer_id, int product_catid) {
		String sql = sqlfindAllProductByCateMenuSortByASC(manufacturer_id, product_catid).toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	private String SqlProductByCateMenuSortByASCPaginate(int start, int totalPage, int manufacturer_id,
			int product_catid) {
		StringBuffer sql = sqlfindAllProductByCateMenuSortByASC(manufacturer_id, product_catid);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductByCateMenuSortByASCPaginate(int start, int totalPage, int manufacturer_id,
			int product_catid) {
		StringBuffer sqlGetData = sqlfindAllProductByCateMenuSortByASC(manufacturer_id, product_catid);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductByCateMenuSortByASCPaginate(start, totalPage, manufacturer_id, product_catid);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	/// catemenu sortby price DESC

	private StringBuffer sqlfindAllProductByCateMenuSortByDESC(int manufacturer_id, int product_catid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product  WHERE product_status = 1 AND  manufacturer_id = ");
		sql.append(" '" + manufacturer_id + "' ");
		sql.append(" AND product_catid = ");
		sql.append(" '" + product_catid + "' ORDER BY productpricesale DESC ");
		return sql;
	}

	public List<ProductEntity> findAllProductByCateMenuSortByDESC(int manufacturer_id, int product_catid) {
		String sql = sqlfindAllProductByCateMenuSortByDESC(manufacturer_id, product_catid).toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	private String SqlProductByCateMenuSortByDESCPaginate(int start, int totalPage, int manufacturer_id,
			int product_catid) {
		StringBuffer sql = sqlfindAllProductByCateMenuSortByDESC(manufacturer_id, product_catid);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductByCateMenuSortByDESCPaginate(int start, int totalPage, int manufacturer_id,
			int product_catid) {
		StringBuffer sqlGetData = sqlfindAllProductByCateMenuSortByDESC(manufacturer_id, product_catid);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductByCateMenuSortByDESCPaginate(start, totalPage, manufacturer_id, product_catid);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}
	/// catemenu sortby ctime desc

	private StringBuffer sqlfindAllProductByCateMenuSortByTime(int manufacturer_id, int product_catid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product  WHERE product_status = 1 AND  manufacturer_id = ");
		sql.append(" '" + manufacturer_id + "' ");
		sql.append(" AND product_catid = ");
		sql.append(" '" + product_catid + "' ORDER BY created_at DESC ");
		return sql;
	}

	private String SqlProductByCateMenuSortByTimePaginate(int start, int totalPage, int manufacturer_id,
			int product_catid) {
		StringBuffer sql = sqlfindAllProductByCateMenuSortByTime(manufacturer_id, product_catid);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> findAllProductByCateMenuSortByTime(int manufacturer_id, int product_catid) {
		String sql = sqlfindAllProductByCateMenuSortByTime(manufacturer_id, product_catid).toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	public List<ProductEntity> GetDataProductByCateMenuSortByTimePaginate(int start, int totalPage, int manufacturer_id,
			int product_catid) {
		StringBuffer sqlGetData = sqlfindAllProductByCateMenuSortByTime(manufacturer_id, product_catid);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductByCateMenuSortByTimePaginate(start, totalPage, manufacturer_id, product_catid);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	/// catemenu sortby ctime acs

	private StringBuffer sqlfindAllProductByCateMenuSortByTimeASC(int manufacturer_id, int product_catid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product  WHERE product_status = 1 AND  manufacturer_id = ");
		sql.append(" '" + manufacturer_id + "' ");
		sql.append(" AND product_catid = ");
		sql.append(" '" + product_catid + "' ORDER BY created_at ASC ");
		return sql;
	}

	private String SqlProductByCateMenuSortByTimeASCPaginate(int start, int totalPage, int manufacturer_id,
			int product_catid) {
		StringBuffer sql = sqlfindAllProductByCateMenuSortByTimeASC(manufacturer_id, product_catid);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> findAllProductByCateMenuSortByTimeASC(int manufacturer_id, int product_catid) {
		String sql = sqlfindAllProductByCateMenuSortByTimeASC(manufacturer_id, product_catid).toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	public List<ProductEntity> GetDataProductByCateMenuSortByTimeASCPaginate(int start, int totalPage,
			int manufacturer_id, int product_catid) {
		StringBuffer sqlGetData = sqlfindAllProductByCateMenuSortByTimeASC(manufacturer_id, product_catid);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductByCateMenuSortByTimeASCPaginate(start, totalPage, manufacturer_id, product_catid);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	/// catemenu sortby apha acs

	private StringBuffer sqlfindAllProductByCateMenuSortByAphaASC(int manufacturer_id, int product_catid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product  WHERE product_status = 1 AND  manufacturer_id = ");
		sql.append(" '" + manufacturer_id + "' ");
		sql.append(" AND product_catid = ");
		sql.append(" '" + product_catid + "' ORDER BY productname ASC ");
		return sql;
	}

	private String SqlProductByCateMenuSortByAphaASCPaginate(int start, int totalPage, int manufacturer_id,
			int product_catid) {
		StringBuffer sql = sqlfindAllProductByCateMenuSortByAphaASC(manufacturer_id, product_catid);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> findAllProductByCateMenuSortByAphaASC(int manufacturer_id, int product_catid) {
		String sql = sqlfindAllProductByCateMenuSortByAphaASC(manufacturer_id, product_catid).toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	public List<ProductEntity> GetDataProductByCateMenuSortByAphaASCPaginate(int start, int totalPage,
			int manufacturer_id, int product_catid) {
		StringBuffer sqlGetData = sqlfindAllProductByCateMenuSortByAphaASC(manufacturer_id, product_catid);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductByCateMenuSortByAphaASCPaginate(start, totalPage, manufacturer_id, product_catid);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	/// catemenu sortby apha desc

	private StringBuffer sqlfindAllProductByCateMenuSortByAphaDESC(int manufacturer_id, int product_catid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product  WHERE product_status = 1 AND  manufacturer_id = ");
		sql.append(" '" + manufacturer_id + "' ");
		sql.append(" AND product_catid = ");
		sql.append(" '" + product_catid + "' ORDER BY productname DESC ");
		return sql;
	}

	private String SqlProductByCateMenuSortByAphaDESCPaginate(int start, int totalPage, int manufacturer_id,
			int product_catid) {
		StringBuffer sql = sqlfindAllProductByCateMenuSortByAphaDESC(manufacturer_id, product_catid);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> findAllProductByCateMenuSortByAphaDESC(int manufacturer_id, int product_catid) {
		String sql = sqlfindAllProductByCateMenuSortByAphaDESC(manufacturer_id, product_catid).toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	public List<ProductEntity> GetDataProductByCateMenuSortByAphaDESCPaginate(int start, int totalPage,
			int manufacturer_id, int product_catid) {
		StringBuffer sqlGetData = sqlfindAllProductByCateMenuSortByAphaDESC(manufacturer_id, product_catid);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductByCateMenuSortByAphaDESCPaginate(start, totalPage, manufacturer_id, product_catid);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	/// product cate

	private StringBuffer sqlfindAllProductByCate(int product_catid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product WHERE product_status = 1 AND  product_catid = ");
		sql.append(" '" + product_catid + "' ");
		return sql;
	}

	public List<ProductEntity> findAllProductByCate(int product_catid) {
		String sql = sqlfindAllProductByCate(product_catid).toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	private String SqlProductByCatePaginate(int start, int totalPage, int product_catid) {
		StringBuffer sql = sqlfindAllProductByCate(product_catid);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductByCatePaginate(int start, int totalPage, int product_catid) {
		StringBuffer sqlGetData = sqlfindAllProductByCate(product_catid);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductByCatePaginate(start, totalPage, product_catid);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	/// product cate sortby price asc

	private StringBuffer sqlfindAllProductByCateSortByPriceASC(int product_catid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product WHERE product_status = 1 AND  product_catid = ");
		sql.append(" '" + product_catid + "' ORDER BY productpricesale ASC ");
		return sql;
	}

	public List<ProductEntity> findAllProductByCateSortByPriceASC(int product_catid) {
		String sql = sqlfindAllProductByCateSortByPriceASC(product_catid).toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	private String SqlProductByCateSortByPriceASCPaginate(int start, int totalPage, int product_catid) {
		StringBuffer sql = sqlfindAllProductByCateSortByPriceASC(product_catid);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductByCateSortByPriceASCPaginate(int start, int totalPage, int product_catid) {
		StringBuffer sqlGetData = sqlfindAllProductByCateSortByPriceASC(product_catid);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductByCateSortByPriceASCPaginate(start, totalPage, product_catid);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	/// product cate sortby price desc

	private StringBuffer sqlfindAllProductByCateSortByPriceDESC(int product_catid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product WHERE product_status = 1 AND product_catid = ");
		sql.append(" '" + product_catid + "' ORDER BY productpricesale DESC ");
		return sql;
	}

	public List<ProductEntity> findAllProductByCateSortByPriceDESC(int product_catid) {
		String sql = sqlfindAllProductByCateSortByPriceDESC(product_catid).toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	private String SqlProductByCateSortByPriceDESCPaginate(int start, int totalPage, int product_catid) {
		StringBuffer sql = sqlfindAllProductByCateSortByPriceDESC(product_catid);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductByCateSortByPriceDESCPaginate(int start, int totalPage,
			int product_catid) {
		StringBuffer sqlGetData = sqlfindAllProductByCateSortByPriceDESC(product_catid);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductByCateSortByPriceDESCPaginate(start, totalPage, product_catid);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	/// product cate sortby alpha asc

	private StringBuffer sqlfindAllProductByCateSortByAlphaASC(int product_catid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product WHERE product_status = 1 AND product_catid = ");
		sql.append(" '" + product_catid + "' ORDER BY productname ASC ");
		return sql;
	}

	public List<ProductEntity> findAllProductByCateSortByAlphaASC(int product_catid) {
		String sql = sqlfindAllProductByCateSortByAlphaASC(product_catid).toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	private String SqlProductByCateSortByAlphaASCPaginate(int start, int totalPage, int product_catid) {
		StringBuffer sql = sqlfindAllProductByCateSortByAlphaASC(product_catid);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductByCateSortByAlphaASCPaginate(int start, int totalPage, int product_catid) {
		StringBuffer sqlGetData = sqlfindAllProductByCateSortByAlphaASC(product_catid);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductByCateSortByAlphaASCPaginate(start, totalPage, product_catid);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	/// product cate sortby alpha desc

	private StringBuffer sqlfindAllProductByCateSortByAlphaDESC(int product_catid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product WHERE product_status = 1 AND product_catid = ");
		sql.append(" '" + product_catid + "' ORDER BY productname DESC ");
		return sql;
	}

	public List<ProductEntity> findAllProductByCateSortByAlphaDESC(int product_catid) {
		String sql = sqlfindAllProductByCateSortByAlphaDESC(product_catid).toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	private String SqlProductByCateSortByAlphaDESCPaginate(int start, int totalPage, int product_catid) {
		StringBuffer sql = sqlfindAllProductByCateSortByAlphaDESC(product_catid);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductByCateSortByAlphaDESCPaginate(int start, int totalPage,
			int product_catid) {
		StringBuffer sqlGetData = sqlfindAllProductByCateSortByAlphaDESC(product_catid);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductByCateSortByAlphaDESCPaginate(start, totalPage, product_catid);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}
	/// product cate sortby ctime asc

	private StringBuffer sqlfindAllProductByCateSortByTimeASC(int product_catid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product WHERE product_status = 1 AND product_catid = ");
		sql.append(" '" + product_catid + "' ORDER BY created_at ASC ");
		return sql;
	}

	public List<ProductEntity> findAllProductByCateSortByTimeASC(int product_catid) {
		String sql = sqlfindAllProductByCateSortByTimeASC(product_catid).toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	private String SqlProductByCateSortByTimeASCPaginate(int start, int totalPage, int product_catid) {
		StringBuffer sql = sqlfindAllProductByCateSortByTimeASC(product_catid);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductByCateSortByTimeASCPaginate(int start, int totalPage, int product_catid) {
		StringBuffer sqlGetData = sqlfindAllProductByCateSortByTimeASC(product_catid);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductByCateSortByTimeASCPaginate(start, totalPage, product_catid);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}

	/// product cate sortby ctime desc

	private StringBuffer sqlfindAllProductByCateSortByTimeDESC(int product_catid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product WHERE product_status = 1 AND product_catid = ");
		sql.append(" '" + product_catid + "' ORDER BY created_at DESC ");
		return sql;
	}

	public List<ProductEntity> findAllProductByCateSortByTimeDESC(int product_catid) {
		String sql = sqlfindAllProductByCateSortByTimeDESC(product_catid).toString();
		List<ProductEntity> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}

	private String SqlProductByCateSortByTimeDESCPaginate(int start, int totalPage, int product_catid) {
		StringBuffer sql = sqlfindAllProductByCateSortByTimeDESC(product_catid);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductEntity> GetDataProductByCateSortByTimeDESCPaginate(int start, int totalPage, int product_catid) {
		StringBuffer sqlGetData = sqlfindAllProductByCateSortByTimeDESC(product_catid);
		List<ProductEntity> listProduct = jdbcTemplate.query(sqlGetData.toString(), new ProductMapper());
		List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
		if (listProduct.size() > 0) {
			String sql = SqlProductByCateSortByTimeDESCPaginate(start, totalPage, product_catid);
			listProducts = jdbcTemplate.query(sql, new ProductMapper());
		}
		return listProducts;
	}
	//

	public boolean isNameExists(String name) {
		int count = 0;
		String sql = "SELECT count(*) FROM product WHERE productname = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);

		return count > 0;
	}

	public boolean isNameExists(String name, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM product WHERE productname = ? and product_id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name, id }, Integer.class);

		return count > 0;
	}

	public ProductEntity findProductBySlug(String slug) {
		String sql = "SELECT * FROM product WHERE product_slug = '" + slug + "' LIMIT 1 ";
		ProductEntity product = jdbcTemplate.queryForObject(sql, new ProductMapper());
		return product;
	}

	public boolean isSlugExists(String product_slug) {
		int count = 0;
		String sql = "SELECT count(*) FROM product WHERE product_slug = ? ";
		count = jdbcTemplate.queryForObject(sql, new Object[] { product_slug }, Integer.class);

		return count > 0;
	}

	public boolean isSlugExists(String product_slug, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM product  WHERE product_slug = ? and product_id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { product_slug, id }, Integer.class);
		return count > 0;
	}

}
