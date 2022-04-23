package com.khoaluantotnghiep.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.khoaluantotnghiep.entity.TopicEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface ITopicService {
	public List<TopicEntity> findAllTopic();

	public List<TopicEntity> findAllTopicShow();

	public void addTopic(TopicEntity topic);

	public void updateTopic(TopicEntity topic);

	public void deleteTopic(int topic_id);

	public TopicEntity findTopicById(TopicEntity topic);

	public void deltrash(int topic_id, UserEntity loginInfo);

	public void retrash(int topic_id, UserEntity loginInfo);

	public List<TopicEntity> findTrashTopic();

	public void onOffTopic(int topic_id, UserEntity loginInfo);

	public List<TopicEntity> GetDataTopicPaginate(int start, int totalPage);

	public List<TopicEntity> GetDataTopicTrashPaginate(int start, int totalPage);

	public Map<Integer, String> mapTopic();
	
	public List<TopicEntity> findAllTopicShowFooter();

	public boolean isNameExists(String name);

	public boolean isNameExists(String name, int id);

	public boolean isSlugExists(String slug);

	public boolean isSlugExists(String slug, int id);

	public TopicEntity findTopicBySlug(String slug);

	public List<TopicEntity> findAllTopicNoFooter();
}
