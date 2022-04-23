package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.ProductImageEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.ProductImageMapper;

@Repository
@Transactional
public class ProductImageDAO extends BaseDAO {
	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product_image where status != 0 ORDER BY status ASC ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM product_image where status = 0 ");
		return sql;
	}

	private String SqlProductImagePaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlProductImageTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductImageEntity> GetDataProductImagePaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<ProductImageEntity> listProductImage = jdbcTemplate.query(sqlGetData.toString(), new ProductImageMapper());
		List<ProductImageEntity> listProductImages = new ArrayList<ProductImageEntity>();
		if (listProductImage.size() > 0) {
			String sql = SqlProductImagePaginate(start, totalPage);
			listProductImages = jdbcTemplate.query(sql, new ProductImageMapper());
		}
		return listProductImages;
	}

	public List<ProductImageEntity> GetDataProductImageTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<ProductImageEntity> listProductImageTrash = jdbcTemplate.query(sqlGetData.toString(),
				new ProductImageMapper());
		List<ProductImageEntity> listProductImageTrashs = new ArrayList<ProductImageEntity>();
		if (listProductImageTrash.size() > 0) {
			String sql = SqlProductImageTrashsPaginate(start, totalPage);
			listProductImageTrashs = jdbcTemplate.query(sql, new ProductImageMapper());
		}
		return listProductImageTrashs;
	}

	public List<ProductImageEntity> findAllProductImage() {
		String sql = "SELECT * FROM product_image where status != 0 ";
		List<ProductImageEntity> list = jdbcTemplate.query(sql, new ProductImageMapper());
		return list;
	}

	public List<ProductImageEntity> findAllProductImageShow() {
		String sql = "SELECT * FROM product_image where status = 1";
		List<ProductImageEntity> list = jdbcTemplate.query(sql, new ProductImageMapper());
		return list;
	}

	public ProductImageEntity findProductImageById(ProductImageEntity prodimg) {
		String sql = "SELECT * FROM product_image where id = " + prodimg.getId();
		List<ProductImageEntity> result = jdbcTemplate.query(sql, new ProductImageMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public List<ProductImageEntity> findTrashProductImage() {
		String sql = "SELECT * FROM product_image where status = 0";
		List<ProductImageEntity> list = jdbcTemplate.query(sql, new ProductImageMapper());
		return list;
	}

	public void deltrashProductImage(int id, UserEntity loginInfo) {
		String sql = "UPDATE product_image SET status = 0,updated_by = " + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void retrashProductImage(int id, UserEntity loginInfo) {
		String sql = "UPDATE product_image SET status = 2,updated_by = " + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void deleteProductImage(int id) {
		String sql = "DELETE FROM product_image WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void onOffProductImage(int id, UserEntity loginInfo) {
		String sql = "UPDATE product_image SET status = case WHEN status =1 then 2 when status=2 then 1 end,updated_by = "
				+ loginInfo.getUser_id() + ", updated_at = NOW() where id = " + id;
		jdbcTemplate.update(sql);
	}

	public void add(ProductImageEntity prodimg) {
		String sql = "INSERT INTO `product_image`(`product_id`, `img`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) "
				+ "VALUES (?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, prodimg.getProduct_id(), prodimg.getImg(), prodimg.getStatus(),
				prodimg.getCreated_at(), prodimg.getCreated_by(), prodimg.getUpdated_at(), prodimg.getUpdated_by());
	}

	public void update(ProductImageEntity prodimg) {
	}
}
