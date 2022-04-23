package com.khoaluantotnghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.PostDAO;
import com.khoaluantotnghiep.entity.PostEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.IPostService;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	PostDAO postDAO;

	@Override
	public List<PostEntity> findAllPost() {
		return postDAO.findAllPost();
	}

	@Override
	public PostEntity findPostById(int post_id) {
		return postDAO.findPostById(post_id);
	}

	@Override
	public void addPage(PostEntity post) {
		postDAO.addPost(post);
	}

	@Override
	public void deletePage(int post_id) {
		postDAO.deletePost(post_id);
	}

	@Override
	public void updatePage(PostEntity post) {
		postDAO.updatePost(post);
	}

	@Override
	public void onOffPost(int post_id,UserEntity loginInfo) {
		postDAO.onOffTPost(post_id, loginInfo);
	}

	@Override
	public List<PostEntity> findTrashPost() {
		return postDAO.findTrashPost();
	}

	@Override
	public void delTrash(int post_id,UserEntity loginInfo) {
		postDAO.delTrash(post_id, loginInfo);
	}

	@Override
	public void reTrash(int post_id,UserEntity loginInfo) {
		postDAO.reTrash(post_id, loginInfo);

	}

	@Override
	public List<PostEntity> GetDataPostPaginate(int start, int totalPage) {
		return postDAO.GetDataPostPaginate(start, totalPage);
	}

	@Override
	public List<PostEntity> GetDataPostTrashPaginate(int start, int totalPage) {
		return postDAO.GetDataPostTrashPaginate(start, totalPage);
	}

	@Override
	public PostEntity findPostBySlug(String post_slug) {
		return postDAO.findPostBySlug(post_slug);
	}

	@Override
	public List<PostEntity> findAllPostShow() {
		return postDAO.findAllPostShow();
	}
	@Override
	public boolean isTitledExists(String title) {
		return postDAO.isTitledExists(title);
	}

	@Override
	public boolean isTitledExists(String title, int id) {
		return postDAO.isTitledExists(title, id);
	}

	@Override
	public boolean isSlugExists(String slug) {
		return postDAO.isSlugExists(slug);
	}

	@Override
	public boolean isSlugExists(String slug, int id) {
		return postDAO.isSlugExists(slug, id);
	}
	@Override
	public List<PostEntity> findAllPostRelated(int topic_id, int id) {
		return postDAO.findAllPostRelated(topic_id,id);
	}
	@Override
	public List<PostEntity> findAllPostByTopic(int post_topicid){
		return postDAO.findAllPostByTopic(post_topicid);
	}
}
