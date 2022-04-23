package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.PostEntity;

public class PostMapper implements RowMapper<PostEntity> {

	@Override
	public PostEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		PostEntity post = new PostEntity();
		post.setPost_id(rs.getInt("post_id"));
		post.setPost_topicid(rs.getInt("post_topicid"));
		post.setPost_title(rs.getString("post_title"));
		post.setPost_slug(rs.getString("post_slug"));
		post.setPost_detail(rs.getString("post_detail"));
		post.setPost_img(rs.getString("post_img"));
		post.setPost_status(rs.getInt("post_status"));
		post.setPost_metakey(rs.getString("post_metakey"));
		post.setPost_metadesc(rs.getString("post_metadesc"));
		post.setCreated_by(rs.getInt("created_by"));
		post.setUpdated_by(rs.getInt("updated_by"));
		post.setCreated_at(rs.getDate("created_at"));
		post.setUpdated_at(rs.getDate("updated_at"));
		return post;
	}

}
