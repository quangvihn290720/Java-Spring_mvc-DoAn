package com.khoaluantotnghiep.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.TopicDAO;
import com.khoaluantotnghiep.entity.TopicEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.ITopicService;

@Service
public class TopicServiceImpl implements ITopicService {

	@Autowired
	TopicDAO topicDAO;

	@Override
	public List<TopicEntity> findAllTopic() {
		return topicDAO.findAllTopic();
	}

	@Override
	public void addTopic(TopicEntity topic) {
		topicDAO.addTopic(topic);
	}

	@Override
	public void updateTopic(TopicEntity topic) {
		topicDAO.updateTopic(topic);
	}

	@Override
	public void deleteTopic(int topic_id) {
		topicDAO.deleteTopic(topic_id);
	}

	@Override
	public TopicEntity findTopicById(TopicEntity topic) {
		return topicDAO.findTopicById(topic);
	}

	@Override
	public void deltrash(int topic_id ,UserEntity loginInfo) {
		topicDAO.deltrash(topic_id,  loginInfo);
	}

	@Override
	public void retrash(int topic_id ,UserEntity loginInfo) {
		topicDAO.retrash(topic_id,  loginInfo);
	}

	@Override
	public List<TopicEntity> findTrashTopic() {
		return topicDAO.findTrashTopic();
	}

	@Override
	public List<TopicEntity> findAllTopicShow() {
		return topicDAO.findAllTopicShow();
	}

	@Override
	public void onOffTopic(int topic_id ,UserEntity loginInfo) {
		topicDAO.onOffTopic(topic_id, loginInfo);
	}

	@Override
	public List<TopicEntity> GetDataTopicPaginate(int start, int totalPage) {
		return topicDAO.GetDataTopicPaginate(start, totalPage);
	}

	@Override
	public List<TopicEntity> GetDataTopicTrashPaginate(int start, int totalPage) {
		return topicDAO.GetDataTopicTrashPaginate(start, totalPage);
	}

	@Override
	public Map<Integer, String> mapTopic() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<TopicEntity> list = topicDAO.findAllTopic();
		for (TopicEntity tp : list) {
			map.put(tp.getTopic_id(), tp.getTopic_name());
		}
		return map;
	}

	@Override
	public List<TopicEntity> findAllTopicShowFooter() {
		return topicDAO.findAllTopicShowFooter();
	}

	@Override
	public boolean isNameExists(String name) {
		return topicDAO.isNameExists(name);
	}

	@Override
	public boolean isNameExists(String name, int id) {
		return topicDAO.isNameExists(name, id);
	}

	@Override
	public boolean isSlugExists(String slug) {
		return topicDAO.isSlugExists(slug);
	}

	@Override
	public boolean isSlugExists(String slug, int id) {
		return topicDAO.isSlugExists(slug, id);
	}
	@Override
	public TopicEntity findTopicBySlug(String slug) {
		return topicDAO.findTopicBySlug(slug);
	}
	@Override
	public List<TopicEntity> findAllTopicNoFooter() {
		return topicDAO.findAllTopicNoFooter();
	}
}
