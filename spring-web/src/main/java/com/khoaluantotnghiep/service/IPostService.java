package com.khoaluantotnghiep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.PostEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface IPostService {
	public List<PostEntity> findAllPost();

	public PostEntity findPostById(int post_id);

	public void addPage(PostEntity post);

	public void deletePage(int post_id);

	public void updatePage(PostEntity post);

	public void onOffPost(int post_id,UserEntity loginInfo);

	public List<PostEntity> findTrashPost();

	public void delTrash(int post_id,UserEntity loginInfo);

	public void reTrash(int post_id,UserEntity loginInfo);

	public List<PostEntity> GetDataPostPaginate(int start, int totalPage);

	public List<PostEntity> GetDataPostTrashPaginate(int start, int totalPage);
	
	public PostEntity findPostBySlug(String post_slug);
	
	public List<PostEntity> findAllPostShow();

	public boolean isTitledExists(String title);

	public boolean isTitledExists(String title, int id);

	public boolean isSlugExists(String slug);

	public boolean isSlugExists(String slug, int id);

	public List<PostEntity> findAllPostRelated(int topic_id, int id);

	public List<PostEntity> findAllPostByTopic(int post_topicid);
}
