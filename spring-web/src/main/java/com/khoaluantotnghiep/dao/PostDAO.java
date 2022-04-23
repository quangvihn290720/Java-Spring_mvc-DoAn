package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.PostEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.PostMapper;

@Repository
@Transactional
public class PostDAO extends BaseDAO {

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM post where post_status != 0 ORDER BY post_status ASC ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM post where post_status = 0 ");
		return sql;
	}

	private String SqlPostsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlPostsTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<PostEntity> GetDataPostPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<PostEntity> listPost = jdbcTemplate.query(sqlGetData.toString(), new PostMapper());
		List<PostEntity> listPosts = new ArrayList<PostEntity>();
		if (listPost.size() > 0) {
			String sql = SqlPostsPaginate(start, totalPage);
			listPosts = jdbcTemplate.query(sql, new PostMapper());
		}
		return listPosts;
	}

	public List<PostEntity> GetDataPostTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<PostEntity> listPostTrash = jdbcTemplate.query(sqlGetData.toString(), new PostMapper());
		List<PostEntity> listPostsTrashs = new ArrayList<PostEntity>();
		if (listPostTrash.size() > 0) {
			String sql = SqlPostsTrashsPaginate(start, totalPage);
			listPostsTrashs = jdbcTemplate.query(sql, new PostMapper());
		}
		return listPostsTrashs;
	}

	public void addPost(PostEntity post) {
		String sql = "INSERT INTO post (post_topicid,post_title,post_slug,post_detail,post_img,post_status,"
				+ "post_metakey,post_metadesc,created_by,updated_by,created_at,updated_at) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, post.getPost_topicid(), post.getPost_title(), post.getPost_slug(),
				post.getPost_detail(), post.getPost_img(), post.getPost_status(), post.getPost_metakey(),
				post.getPost_metadesc(), post.getCreated_by(), post.getUpdated_by(), post.getCreated_at(),
				post.getUpdated_at());
	}

	public void deletePost(int post_id) {
		String sql = "DELETE FROM post WHERE post_id = " + post_id;
		jdbcTemplate.update(sql);
	}

	public void updatePost(PostEntity post) {
		String sql = "";
		if (post.getPost_img() != null && !post.getPost_img().isEmpty()) {
			sql = "UPDATE post SET post_topicid = ?, post_title = ?, post_slug = ?, post_detail = ?, post_img = ?, post_status = ?,"
					+ "post_metakey = ?, post_metadesc = ?, updated_by = ?, updated_at = ? WHERE post_id = ?";
			jdbcTemplate.update(sql, post.getPost_topicid(), post.getPost_title(), post.getPost_slug(),
					post.getPost_detail(), post.getPost_img(), post.getPost_status(), post.getPost_metakey(),
					post.getPost_metadesc(), post.getUpdated_by(), post.getUpdated_at(), post.getPost_id());
		} else {
			sql = "UPDATE post SET post_topicid = ?, post_title = ?, post_slug = ?, post_detail = ?,  post_status = ?,"
					+ "post_metakey = ?, post_metadesc = ?, updated_by = ?, updated_at = ? WHERE post_id = ?";
			jdbcTemplate.update(sql, post.getPost_topicid(), post.getPost_title(), post.getPost_slug(),
					post.getPost_detail(), post.getPost_status(), post.getPost_metakey(), post.getPost_metadesc(),
					post.getUpdated_by(), post.getUpdated_at(), post.getPost_id());
		}
	}

	public List<PostEntity> findAllPost() {
		String sql = "SELECT * FROM post where post_status != 0";
		List<PostEntity> list = jdbcTemplate.query(sql, new PostMapper());
		return list;
	}

	public List<PostEntity> findAllPostShow() {
		String sql = "SELECT * FROM post where post_status = 1 ";
		List<PostEntity> list = jdbcTemplate.query(sql, new PostMapper());
		return list;
	}

	public PostEntity findPostBySlug(String post_slug) {
		String sql = "SELECT * FROM post where post_slug = '" + post_slug + "'";
		List<PostEntity> result = jdbcTemplate.query(sql, new PostMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public PostEntity findPostById(int post_id) {
		String sql = "SELECT * FROM post where post_id = " + post_id;
		List<PostEntity> result = jdbcTemplate.query(sql, new PostMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public void onOffTPost(int post_id, UserEntity loginInfo) {
		String sql = "UPDATE post SET post_status = case when post_status =1 then 2 when post_status =2 then 1 end,updated_by = "
				+ loginInfo.getUser_id() + ", updated_at = NOW() where post_id =" + post_id;
		System.out.println(sql);
		jdbcTemplate.update(sql);
	}

	public List<PostEntity> findTrashPost() {
		String sql = "SELECT * FROM post where post_status = 0";
		List<PostEntity> list = jdbcTemplate.query(sql, new PostMapper());
		return list;
	}

	public void delTrash(int post_id, UserEntity loginInfo) {
		String sql = "UPDATE post SET post_status = 0, updated_by =" + +loginInfo.getUser_id()
				+ ", updated_at = NOW() where  post_id =" + post_id;
		jdbcTemplate.update(sql);
	}

	public void reTrash(int post_id, UserEntity loginInfo) {
		String sql = "UPDATE post SET post_status = 2, updated_by = " + +loginInfo.getUser_id()
				+ ", updated_at = NOW()  where  post_id =" + post_id;
		jdbcTemplate.update(sql);
	}

	public boolean isTitledExists(String title) {
		int count = 0;
		String sql = "SELECT count(*) FROM post WHERE post_title = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { title }, Integer.class);

		return count > 0;
	}

	public boolean isTitledExists(String title, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM post WHERE post_title = ? and post_id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { title, id }, Integer.class);

		return count > 0;
	}

	public boolean isSlugExists(String slug) {
		int count = 0;
		String sql = "SELECT count(*) FROM post WHERE post_slug = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { slug }, Integer.class);

		return count > 0;
	}

	public boolean isSlugExists(String slug, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM post WHERE post_slug = ? and post_id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { slug, id }, Integer.class);

		return count > 0;
	}

	public List<PostEntity> findAllPostByTopic(int post_topicid) {
		String sql = "SELECT * FROM post where post_status = 1 and post_topicid = " + post_topicid;
		List<PostEntity> list = jdbcTemplate.query(sql, new PostMapper());
		return list;
	}

	public List<PostEntity> findAllPostRelated(int topic_id, int id) {
		String sql = "SELECT * FROM post where post_status = 1 and post_topicid = " + topic_id + " and post_id <> "
				+ id;
		List<PostEntity> list = jdbcTemplate.query(sql, new PostMapper());
		return list;
	}
}
