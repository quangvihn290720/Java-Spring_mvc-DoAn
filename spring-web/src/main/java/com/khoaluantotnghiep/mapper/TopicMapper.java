package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.TopicEntity;

public class TopicMapper implements RowMapper<TopicEntity> {

	@Override
	public TopicEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		TopicEntity topic = new TopicEntity();
		topic.setTopic_id(rs.getInt("topic_id"));
		topic.setTopic_name(rs.getString("topic_name"));
		topic.setTopic_slug(rs.getString("topic_slug"));
		topic.setTopic_metakey(rs.getString("topic_metakey"));
		topic.setTopic_metadesc(rs.getString("topic_metadesc"));
		topic.setShowfooter(rs.getInt("showfooter"));
		topic.setTopic_status(rs.getInt("topic_status"));
		topic.setCreated_by(rs.getInt("created_by"));
		topic.setUpdated_by(rs.getInt("updated_by"));
		topic.setCreated_at(rs.getDate("created_at"));
		topic.setUpdated_at(rs.getDate("updated_at"));
		return topic;
	}

}
