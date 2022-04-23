package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.TopicEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.TopicMapper;

@Repository
@Transactional
public class TopicDAO extends BaseDAO {

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM topic where topic_status != 0 ORDER BY topic_status ASC ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM topic where topic_status = 0 ");
		return sql;
	}

	public List<TopicEntity> findAllTopic() {
		String sql = "SELECT * FROM topic where topic_status != 0 ORDER BY topic_status ASC ";
		List<TopicEntity> list = jdbcTemplate.query(sql, new TopicMapper());
		return list;
	}

	public List<TopicEntity> findAllTopicShow() {
		String sql = "SELECT * FROM topic where topic_status = 1";
		List<TopicEntity> list = jdbcTemplate.query(sql, new TopicMapper());
		return list;
	}

	public List<TopicEntity> findAllTopicShowFooter() {
		String sql = "SELECT * FROM topic where topic_status = 1 and showfooter = 1";
		List<TopicEntity> list = jdbcTemplate.query(sql, new TopicMapper());
		return list;
	}

	public void addTopic(TopicEntity topic) {
		String sql = "INSERT INTO topic (topic_name,topic_slug,topic_metakey,topic_metadesc,showfooter"
				+ ",topic_status,created_by,updated_by,created_at,updated_at) VALUES (?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, topic.getTopic_name(), topic.getTopic_slug(), topic.getTopic_metakey(),
				topic.getTopic_metadesc(), topic.getShowfooter(), topic.getTopic_status(), topic.getCreated_by(),
				topic.getUpdated_by(), topic.getCreated_at(), topic.getUpdated_at());
	}

	public void updateTopic(TopicEntity topic) {
		String sql = "UPDATE topic SET topic_name = ?, topic_slug = ?, topic_metakey = ?, topic_metadesc= ?,showfooter = ?,topic_status = ?, "
				+ " updated_by = ?, updated_at = ? WHERE topic_id = ?";
		jdbcTemplate.update(sql, topic.getTopic_name(), topic.getTopic_slug(), topic.getTopic_metakey(),
				topic.getTopic_metadesc(), topic.getShowfooter(), topic.getTopic_status(), topic.getUpdated_by(),
				topic.getUpdated_at(), topic.getTopic_id());
	}

	public void deleteTopic(int topic_id) {
		String sql = "DELETE FROM topic WHERE topic_id = " + topic_id;
		jdbcTemplate.update(sql);
	}

	public TopicEntity findTopicById(TopicEntity topic) {
		String sql = "SELECT * FROM topic where topic_id = " + topic.getTopic_id();
		List<TopicEntity> result = jdbcTemplate.query(sql, new TopicMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public List<TopicEntity> findTrashTopic() {
		String sql = "SELECT * FROM topic where topic_status = 0";
		List<TopicEntity> list = jdbcTemplate.query(sql, new TopicMapper());
		return list;
	}

	public void deltrash(int topic_id, UserEntity loginInfo) {
		String sql = "UPDATE topic SET topic_status = 0,updated_by = " + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE topic_id = " + topic_id;
		jdbcTemplate.update(sql);
	}

	public void retrash(int topic_id, UserEntity loginInfo) {
		String sql = "UPDATE topic SET topic_status = 2,updated_by = " + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE topic_id = " + topic_id;
		jdbcTemplate.update(sql);
	}

	public void onOffTopic(int topic_id, UserEntity loginInfo) {
		String sql = "UPDATE topic SET topic_status = case WHEN topic_status =1 then 2 when topic_status=2 then 1 end,updated_by = "
				+ loginInfo.getUser_id() + ", updated_at = NOW() WHERE topic_id = " + topic_id;
		jdbcTemplate.update(sql);
	}

	private String SqlTopicsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlTopicTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<TopicEntity> GetDataTopicPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<TopicEntity> listTopic = jdbcTemplate.query(sqlGetData.toString(), new TopicMapper());
		List<TopicEntity> listTopics = new ArrayList<TopicEntity>();
		if (listTopic.size() > 0) {
			String sql = SqlTopicsPaginate(start, totalPage);
			listTopics = jdbcTemplate.query(sql, new TopicMapper());
		}
		return listTopics;
	}

	public List<TopicEntity> GetDataTopicTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<TopicEntity> listTopicTrash = jdbcTemplate.query(sqlGetData.toString(), new TopicMapper());
		List<TopicEntity> listTopicTrashs = new ArrayList<TopicEntity>();
		if (listTopicTrash.size() > 0) {
			String sql = SqlTopicTrashsPaginate(start, totalPage);
			listTopicTrashs = jdbcTemplate.query(sql, new TopicMapper());
		}
		return listTopicTrashs;
	}

	public boolean isNameExists(String name) {
		int count = 0;
		String sql = "SELECT count(*) FROM topic WHERE topic_name = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);

		return count > 0;
	}

	public boolean isNameExists(String name, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM topic WHERE topic_name = ? and topic_id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name, id }, Integer.class);

		return count > 0;
	}

	public boolean isSlugExists(String slug) {
		int count = 0;
		String sql = "SELECT count(*) FROM topic WHERE topic_slug = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { slug }, Integer.class);

		return count > 0;
	}

	public boolean isSlugExists(String slug, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM topic WHERE topic_slug = ? and topic_id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { slug, id }, Integer.class);

		return count > 0;
	}

	public TopicEntity findTopicBySlug(String slug) {
		String sql = "SELECT * FROM topic where topic_status = 1 and topic_slug = '" + slug + "'";
		List<TopicEntity> result = jdbcTemplate.query(sql, new TopicMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public List<TopicEntity> findAllTopicNoFooter() {
		String sql = "SELECT * FROM topic where topic_status = 1 and showfooter = 0";
		List<TopicEntity> list = jdbcTemplate.query(sql, new TopicMapper());
		return list;
	}
}
