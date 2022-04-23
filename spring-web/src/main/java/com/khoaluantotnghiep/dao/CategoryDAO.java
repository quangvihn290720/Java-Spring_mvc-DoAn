package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.CategoryEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.CategoryMapper;

@Repository
@Transactional
public class CategoryDAO extends BaseDAO {
	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM category where status != 0 ORDER BY status ASC ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM category where status = 0 ");
		return sql;
	}

	public List<CategoryEntity> findAllCate() {
		String sql = "SELECT * FROM category where status != 0 ";
		List<CategoryEntity> list = jdbcTemplate.query(sql, new CategoryMapper());
		return list;
	}

	public List<CategoryEntity> findAllCategoryShow() {
		String sql = "SELECT * FROM category where status = 1";
		List<CategoryEntity> list = jdbcTemplate.query(sql, new CategoryMapper());
		return list;
	}

	public void addCategory(CategoryEntity category) {
		String sql = "INSERT INTO category ( `name`, `slug`,`metadesc`,`metakey`, `status`, `created_at`, `updated_at`, `created_by`, `updated_by`) VALUES (?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, category.getName(), category.getSlug(), category.getMetadesc(), category.getMetakey(),
				category.getStatus(), category.getCreated_at(), category.getUpdated_at(), category.getCreated_by(),
				category.getUpdated_by());
	}

	public void updateCategory(CategoryEntity category) {
		String sql = "UPDATE category SET name = ?, slug = ?, metadesc = ?, metakey = ?, status = ?, updated_at = ?,updated_by = ? WHERE id = ?";
		jdbcTemplate.update(sql, category.getName(), category.getSlug(), category.getMetadesc(), category.getMetakey(),
				category.getStatus(), category.getUpdated_at(), category.getUpdated_by(), category.getId());
	}

	public void deleteCategory(int id) {
		String sql = "DELETE FROM category WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public CategoryEntity findCategoryById(CategoryEntity category) {
		String sql = "SELECT * FROM category where id = " + category.getId();
		List<CategoryEntity> result = jdbcTemplate.query(sql, new CategoryMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public CategoryEntity findAllCateBySlug(String slug) {
		String sql = "SELECT * FROM category where slug = '" + slug + "'";
		List<CategoryEntity> result = jdbcTemplate.query(sql, new CategoryMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public List<CategoryEntity> findTrashCategory() {
		String sql = "SELECT * FROM category where status = 0";
		List<CategoryEntity> list = jdbcTemplate.query(sql, new CategoryMapper());
		return list;
	}

	public void deltrash(int id, UserEntity loginInfo) {
		String sql = "UPDATE category SET status = 0,updated_by = " + loginInfo.getUser_id()
				+ ", updated_at = NOW()  WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void retrash(int id, UserEntity loginInfo) {
		String sql = "UPDATE category SET status = 2,updated_by = " + loginInfo.getUser_id()
				+ ", updated_at = NOW()  WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void onOffCategory(int id, UserEntity loginInfo) {
		String sql = "UPDATE category SET status = case WHEN status =1 then 2 when status=2 then 1 end,updated_by = "
				+ loginInfo.getUser_id() + ", updated_at = NOW()  WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	private String SqlCategorysPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlCategoryTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<CategoryEntity> GetDataCategoryPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<CategoryEntity> listCategory = jdbcTemplate.query(sqlGetData.toString(), new CategoryMapper());
		List<CategoryEntity> listCategorys = new ArrayList<CategoryEntity>();
		if (listCategory.size() > 0) {
			String sql = SqlCategorysPaginate(start, totalPage);
			listCategorys = jdbcTemplate.query(sql, new CategoryMapper());
		}
		return listCategorys;
	}

	public List<CategoryEntity> GetDataCategoryTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<CategoryEntity> listCategoryTrash = jdbcTemplate.query(sqlGetData.toString(), new CategoryMapper());
		List<CategoryEntity> listCategoryTrashs = new ArrayList<CategoryEntity>();
		if (listCategoryTrash.size() > 0) {
			String sql = SqlCategoryTrashsPaginate(start, totalPage);
			listCategoryTrashs = jdbcTemplate.query(sql, new CategoryMapper());
		}
		return listCategoryTrashs;
	}

	public boolean isNameExists(String name) {
		int count = 0;
		String sql = "SELECT count(*) FROM category WHERE name = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);

		return count > 0;
	}

	public boolean isNameExists(String name, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM category WHERE name = ? and id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name, id }, Integer.class);

		return count > 0;
	}

	public boolean isSlugExists(String slug) {
		int count = 0;
		String sql = "SELECT count(*) FROM category WHERE slug = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { slug }, Integer.class);

		return count > 0;
	}

	public boolean isSlugExists(String slug, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM category WHERE slug = ? and id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { slug, id }, Integer.class);

		return count > 0;
	}

}
