package com.khoaluantotnghiep.controller.web;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.khoaluantotnghiep.entity.PostEntity;
import com.khoaluantotnghiep.entity.TopicEntity;
import com.khoaluantotnghiep.service.impl.AccountServiceImpl;
import com.khoaluantotnghiep.service.impl.PostServiceImpl;
import com.khoaluantotnghiep.service.impl.TopicServiceImpl;

@Controller(value = "postControllerOfWeb")
public class PostController extends BaseController {
	@Autowired
	PostServiceImpl postServiceImpl;
	@Autowired
	TopicServiceImpl topicService;
	@Autowired
	AccountServiceImpl accoutService;
	
	@RequestMapping(value = "/bai-viet", method = RequestMethod.GET)
	public ModelAndView Page() {
		_mvShare.addObject("listpost", postServiceImpl.findAllPostShow());
		_mvShare.addObject("listtopicshow", topicService.findAllTopicNoFooter());
		_mvShare.setViewName("web/news/topic");
		return _mvShare;
	}

	@RequestMapping(value = "/bai-viet/{slug}", method = RequestMethod.GET)
	public ModelAndView Post(@PathVariable String slug) {
		if (postServiceImpl.findPostBySlug(slug) != null) {
			PostEntity itempost = postServiceImpl.findPostBySlug(slug);
			_mvShare.addObject("itempost", itempost);
			int topic_id = itempost.getPost_topicid();
			int id = itempost.getPost_id();
			_mvShare.addObject("listpostrelated", postServiceImpl.findAllPostRelated(topic_id, id));
			_mvShare.setViewName("web/news/post");
		} else if (topicService.findTopicBySlug(slug) != null) {
			TopicEntity topicEntity = topicService.findTopicBySlug(slug);
			_mvShare.addObject("topicEntity", topicEntity);
			int idtopic = topicEntity.getTopic_id();
			_mvShare.addObject("listpostbytopic", postServiceImpl.findAllPostByTopic(idtopic));
			Map<Integer, String> mapTopic = topicService.mapTopic();
			_mvShare.addObject("mapTopic", mapTopic);
			_mvShare.addObject("listtopicshow", topicService.findAllTopicNoFooter());
			_mvShare.setViewName("web/news/topicpost");
		}
		Map<Integer, String> mapUser = accoutService.mapUser();
		_mvShare.addObject("mapUser", mapUser);
		return _mvShare;
	}
}

