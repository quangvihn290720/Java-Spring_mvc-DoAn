package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.PageEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.PageMapper;

@Repository
@Transactional
public class PageDAO extends BaseDAO {

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM page where page_status != 0 ORDER BY page_status ASC ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM page where page_status = 0 ");
		return sql;
	}

	private String SqlPagesPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlPagesTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<PageEntity> GetDataPagePaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<PageEntity> listPage = jdbcTemplate.query(sqlGetData.toString(), new PageMapper());
		List<PageEntity> listPages = new ArrayList<PageEntity>();
		if (listPage.size() > 0) {
			String sql = SqlPagesPaginate(start, totalPage);
			listPages = jdbcTemplate.query(sql, new PageMapper());
		}
		return listPages;
	}

	public List<PageEntity> GetDataPageTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<PageEntity> listPageTrash = jdbcTemplate.query(sqlGetData.toString(), new PageMapper());
		List<PageEntity> listPageTrashs = new ArrayList<PageEntity>();
		if (listPageTrash.size() > 0) {
			String sql = SqlPagesTrashsPaginate(start, totalPage);
			listPageTrashs = jdbcTemplate.query(sql, new PageMapper());
		}
		return listPageTrashs;
	}

	public void addPage(PageEntity page) {
		String sql = "INSERT INTO page (page_title,page_slug," + "page_detail,page_img," + "page_metakey,page_metadesc,"
				+ "page_status,page_topic,created_by,"
				+ "updated_by,created_at,updated_at) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, page.getPage_title(), page.getPage_slug(), page.getPage_detail(), page.getPage_img(),
				page.getPage_metakey(), page.getPage_metadesc(), page.getPage_status(), page.getPage_topic(),
				page.getCreated_by(), page.getUpdated_by(), page.getCreated_at(), page.getUpdated_at());

	}

	public void deletePage(int page_id) {
		String sql = "DELETE FROM page WHERE page_id = " + page_id;
		jdbcTemplate.update(sql);
	}

	public void updatePage(PageEntity page) {
		String sql = "";
		if (page.getPage_img() != null && !page.getPage_img().isEmpty()) {
			sql = "UPDATE page SET page_title = ?, page_slug = ?, page_detail = ?, page_img = ?, page_metakey = ?, page_metadesc = ?"
					+ ",page_status = ?,page_topic = ?,  updated_by = ?, updated_at = ? WHERE page_id = ?";
			jdbcTemplate.update(sql, page.getPage_title(), page.getPage_slug(), page.getPage_detail(),
					page.getPage_img(), page.getPage_metakey(), page.getPage_metadesc(), page.getPage_status(),
					page.getPage_topic(), page.getUpdated_by(), page.getUpdated_at(), page.getPage_id());
		} else {
			sql = "UPDATE page SET page_title = ?, page_slug = ?, page_detail = ?, page_metakey = ?, page_metadesc = ?"
					+ ",page_status = ?, page_topic = ?,  updated_by = ?,  updated_at = ? WHERE page_id = ?";
			jdbcTemplate.update(sql, page.getPage_title(), page.getPage_slug(), page.getPage_detail(),
					page.getPage_metakey(), page.getPage_metadesc(), page.getPage_status(), page.getPage_topic(),
					page.getUpdated_by(), page.getUpdated_at(), page.getPage_id());
		}

	}

	public PageEntity findPageById(int page_id) {
		String sql = "SELECT * FROM page WHERE page_id = ?";
		return jdbcTemplate.queryForObject(sql, new PageMapper(), page_id);
	}

	public List<PageEntity> findAllPage() {
		String sql = "SELECT * FROM page where page_status != 0";
		List<PageEntity> list = jdbcTemplate.query(sql, new PageMapper());
		return list;
	}

	public List<PageEntity> findAllPageShow() {
		String sql = "SELECT * FROM page where page_status = 1";
		List<PageEntity> list = jdbcTemplate.query(sql, new PageMapper());
		return list;
	}

	public void onOffPage(int page_id, UserEntity loginInfo) {
		String sql = "UPDATE page SET page_status = case when  page_status =1 then 2 when  page_status =2 then 1 end,updated_by =  "
				+ +loginInfo.getUser_id() + ", updated_at = NOW() where  page_id =" + page_id;
		jdbcTemplate.update(sql);
	}

	public void delTrash(int page_id, UserEntity loginInfo) {
		String sql = "UPDATE page SET page_status = 0,updated_by = " + +loginInfo.getUser_id()
				+ ", updated_at = NOW() where  page_id =" + page_id;
		jdbcTemplate.update(sql);
	}

	public void reTrash(int page_id, UserEntity loginInfo) {
		String sql = "UPDATE page SET page_status = 2,updated_by = " + loginInfo.getUser_id()
				+ ", updated_at = NOW() where  page_id =" + page_id;
		jdbcTemplate.update(sql);
	}

	public List<PageEntity> findTrashPage() {
		String sql = "select * from page where page_status = 0";
		List<PageEntity> list = jdbcTemplate.query(sql, new PageMapper());
		return list;
	}

	public boolean isTitledExists(String title) {
		int count = 0;
		String sql = "SELECT count(*) FROM page WHERE page_title = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { title }, Integer.class);

		return count > 0;
	}

	public boolean isTitledExists(String title, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM page WHERE page_title = ? and page_id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { title, id }, Integer.class);

		return count > 0;
	}

	public boolean isSlugExists(String slug) {
		int count = 0;
		String sql = "SELECT count(*) FROM page WHERE page_slug = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { slug }, Integer.class);

		return count > 0;
	}

	public boolean isSlugExists(String slug, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM page WHERE page_slug = ? and page_id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { slug, id }, Integer.class);

		return count > 0;
	}

	public PageEntity findPageBySlug(String slug) {
		String sql = "SELECT * FROM page WHERE page_slug = ?";
		return jdbcTemplate.queryForObject(sql, new PageMapper(), slug);
	}
}
