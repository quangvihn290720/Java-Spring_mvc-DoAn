package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.ProductOptionsEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.ProductOptionMapper;

@Repository
@Transactional
public class ProductOptionDAO extends BaseDAO {

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM productoptions where status != 0 ORDER BY `product_id` ASC ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM productoptions where status = 0 ORDER BY `product_id` ASC ");
		return sql;
	}

	private String SqlProdOptionPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlProdOptionTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductOptionsEntity> GetDataProdOptionPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<ProductOptionsEntity> listProdOption = jdbcTemplate.query(sqlGetData.toString(),
				new ProductOptionMapper());
		List<ProductOptionsEntity> listProdOptions = new ArrayList<ProductOptionsEntity>();
		if (listProdOption.size() > 0) {
			String sql = SqlProdOptionPaginate(start, totalPage);
			listProdOptions = jdbcTemplate.query(sql, new ProductOptionMapper());
		}
		return listProdOptions;
	}

	public List<ProductOptionsEntity> GetDataProdOptionTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<ProductOptionsEntity> listProdOptionTrash = jdbcTemplate.query(sqlGetData.toString(),
				new ProductOptionMapper());
		List<ProductOptionsEntity> listProdOptionTrashs = new ArrayList<ProductOptionsEntity>();
		if (listProdOptionTrash.size() > 0) {
			String sql = SqlProdOptionTrashsPaginate(start, totalPage);
			listProdOptionTrashs = jdbcTemplate.query(sql, new ProductOptionMapper());
		}
		return listProdOptionTrashs;
	}

	public List<ProductOptionsEntity> findAllProdOption() {
		String sql = "SELECT * FROM productoptions where status != 0 ";
		List<ProductOptionsEntity> list = jdbcTemplate.query(sql, new ProductOptionMapper());
		return list;
	}

	public List<ProductOptionsEntity> findAllProdOptionShow() {
		String sql = "SELECT * FROM productoptions where status = 1 ";
		List<ProductOptionsEntity> list = jdbcTemplate.query(sql, new ProductOptionMapper());
		return list;
	}

	public List<ProductOptionsEntity> findAllTrashProdOption() {
		List<ProductOptionsEntity> list = new ArrayList<ProductOptionsEntity>();
		String sql = "SELECT * FROM productoptions where status = 0 ";
		list = jdbcTemplate.query(sql, new ProductOptionMapper());
		return list;
	}

	public ProductOptionsEntity findProdOptionById(ProductOptionsEntity prodop) {
		String sql = "SELECT * FROM productoptions WHERE productoptions_id = " + prodop.getProductoptions_id();
		List<ProductOptionsEntity> result = jdbcTemplate.query(sql, new ProductOptionMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public void addProdOption(ProductOptionsEntity prodop) {
		String sql = "INSERT INTO `productoptions`(`product_id`, `optiongroup_id`, `option_id`, `metadesc`, `metakey`,"
				+ " `status`, `created_by`, `updated_by`, `created_at`, `updated_at`) VALUES (?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, prodop.getProduct_id(), prodop.getOptiongroup_id(), prodop.getOption_id(),
				prodop.getMetadesc(), prodop.getMetakey(), prodop.getStatus(), prodop.getCreated_by(),
				prodop.getUpdated_by(), prodop.getCreated_at(), prodop.getUpdated_at());
	}

	public void updateProdOption(ProductOptionsEntity prodop) {
		String sql = "UPDATE `productoptions` SET 	product_id = ?, optiongroup_id = ?, option_id = ?, metadesc = ?, `metakey` = ?,"
				+ " `status` = ?, `updated_by` = ?, `updated_at` = ? WHERE productoptions_id = ?";
		jdbcTemplate.update(sql, prodop.getProduct_id(), prodop.getOptiongroup_id(), prodop.getOption_id(),
				prodop.getMetadesc(), prodop.getMetakey(), prodop.getStatus(), prodop.getUpdated_by(),
				prodop.getUpdated_at(), prodop.getProductoptions_id());
	}

	public void deleteProdOption(int productoptions_id) {
		String sql = "DELETE FROM productoptions WHERE productoptions_id = " + productoptions_id;
		jdbcTemplate.update(sql);
	}

	public void deltrash(int productoptions_id, UserEntity loginInfo) {
		String sql = "UPDATE productoptions SET status = 0,updated_by = " + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE productoptions_id = " + productoptions_id;
		jdbcTemplate.update(sql);
	}

	public void retrash(int productoptions_id, UserEntity loginInfo) {
		String sql = "UPDATE productoptions SET status = 2,updated_by = " + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE productoptions_id = " + productoptions_id;
		jdbcTemplate.update(sql);
	}

	public void onOffProdOption(int productoptions_id, UserEntity loginInfo) {
		String sql = "UPDATE productoptions SET status = case WHEN status =1 then 2 when status=2 then 1 end,updated_by = "
				+ loginInfo.getUser_id() + ", updated_at = NOW() WHERE productoptions_id = " + productoptions_id;
		jdbcTemplate.update(sql);
	}
}
