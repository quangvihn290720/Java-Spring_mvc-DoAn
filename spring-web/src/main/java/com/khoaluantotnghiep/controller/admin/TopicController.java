package com.khoaluantotnghiep.controller.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.khoaluantotnghiep.dto.PaginateDTO;
import com.khoaluantotnghiep.entity.NoteEntity;
import com.khoaluantotnghiep.entity.TopicEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;
import com.khoaluantotnghiep.service.impl.TopicServiceImpl;

@Controller(value = "topicControllerOfAdmin")
public class TopicController extends BaseController {
	@Autowired
	TopicServiceImpl topicSevice;
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	NoteServiceImpl noteService;

	private int totalDataPage = 5;

	@RequestMapping(value = "/quan-tri/chu-de", method = RequestMethod.GET)
	public ModelAndView Topic(ModelMap modelMap) {
		int totalData = topicSevice.findAllTopic().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("topicPaginate", topicSevice.GetDataTopicPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/topic/topic");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/chu-de/{currentPage}", method = RequestMethod.GET)
	public ModelAndView Topic(@PathVariable String currentPage, ModelMap modelMap) {
		int totalData = topicSevice.findAllTopic().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("topicPaginate", topicSevice.GetDataTopicPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/topic/topic");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/chu-de/thung-rac", method = RequestMethod.GET)
	public ModelAndView trashTopic(@ModelAttribute("topic") TopicEntity topic) {
		int totalData = topicSevice.findTrashTopic().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("topicPaginate",
				topicSevice.GetDataTopicTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/topic/trashtopic");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/chu-de/thung-rac/{currentPage}", method = RequestMethod.GET)
	public ModelAndView trashTopic(@PathVariable String currentPage, @ModelAttribute("topic") TopicEntity topic) {
		int totalData = topicSevice.findTrashTopic().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("topicPaginate",
				topicSevice.GetDataTopicTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/topic/trashtopic");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/chu-de/add", method = RequestMethod.GET)
	public ModelAndView addTopic(@ModelAttribute("topic") TopicEntity topic) {
		_mvShare.setViewName("admin/topic/addtopic");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/chu-de/save", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String saveTopic(HttpSession session, HttpServletRequest request, @ModelAttribute("topic") TopicEntity topic,
			ModelMap modelMap, final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		topic.setTopic_metadesc(request.getParameter("topic_metadesc"));
		boolean check = true;
		if (topicSevice.isNameExists(topic.getTopic_name())) {
			redirectAttributes.addFlashAttribute("msgTitle", "T??n ch??? ????? ???? t???n t???i");
			check = false;
		}
		if (topicSevice.isSlugExists(topic.getTopic_slug())) {
			redirectAttributes.addFlashAttribute("msgSlug", "Slug ???? t???n t???i");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", topic);
			return "redirect:/quan-tri/chu-de/add";
		}
		try {
			topic.setCreated_at(new Date());
			topic.setUpdated_at(new Date());
			topic.setUpdated_by(loginInfo.getUser_id());
			topic.setCreated_by(loginInfo.getUser_id());
			topicSevice.addTopic(topic);
			redirectAttributes.addFlashAttribute("msg", "Th??m th??nh c??ng!");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? th??m ch??? ????? " + topic.getTopic_name());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Th??m kh??ng th??nh c??ng");
		}

		return "redirect:/quan-tri/chu-de";
	}

	@RequestMapping(value = "/quan-tri/chu-de/edit/{topic_id}", method = RequestMethod.GET)
	public ModelAndView editTopic(@ModelAttribute("topic") TopicEntity topic, @PathVariable int topic_id) {
		TopicEntity topicitem = topicSevice.findTopicById(topic);
		_mvShare.addObject("topicitem", topicitem);
		_mvShare.setViewName("admin/topic/edittopic");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/chu-de/editsave", method = RequestMethod.POST)
	public String editsaveTopic(HttpServletRequest request, HttpSession session,
			@ModelAttribute("topic") TopicEntity topic, ModelMap modelMap,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		boolean check = true;
		if (topicSevice.isNameExists(topic.getTopic_name(), topic.getTopic_id())) {
			redirectAttributes.addFlashAttribute("msgTitle", "T??n ch??? ????? ???? t???n t???i");
			check = false;
		}
		if (topicSevice.isSlugExists(topic.getTopic_slug(), topic.getTopic_id())) {
			System.out.println("Sai slug");
			redirectAttributes.addFlashAttribute("msgSlug", "Slug ???? t???n t???i");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", topic);
			return "redirect:/quan-tri/chu-de/edit/" + topic.getTopic_id();
		}
		try {
			topic.setUpdated_at(new Date());
			topic.setUpdated_by(loginInfo.getUser_id());
			topic.setTopic_metadesc(request.getParameter("topic_metadesc"));
			topic.setShowfooter(0);
			topicSevice.updateTopic(topic);
			redirectAttributes.addFlashAttribute("msg", "C???p nh???t th??nh c??ng");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? ch???nh s???a ch??? ????? " + topic.getTopic_id());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "C???p nh???t kh??ng th??nh c??ng");
		}
		return "redirect:/quan-tri/chu-de";
	}

	@RequestMapping(value = "/quan-tri/chu-de/deletetopic/{topic_id}", method = RequestMethod.GET)
	public String deleteTopic(@PathVariable int topic_id, ModelMap modelMap,
			final RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			topicSevice.deleteTopic(topic_id);
			redirectAttributes.addFlashAttribute("msg", "X??a th??nh c??ng!");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? x??a v??nh vi???n ch??? ????? " + topic_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao t??c kh??ng th??nh c??ng!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/chu-de/trash/{topic_id}", method = RequestMethod.GET)
	public String delTopic(@PathVariable int topic_id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			topicSevice.deltrash(topic_id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao t??c th??nh c??ng!");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? x??a t???m th???i ch??? ????? " + topic_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao t??c kh??ng th??nh c??ng!");
		}

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/chu-de/retrash/{topic_id}", method = RequestMethod.GET)
	public String retrashTopic(@PathVariable int topic_id, ModelMap modelMap,
			final RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			topicSevice.retrash(topic_id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao t??c th??nh c??ng!");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? b??? x??a t???m th???i ch??? ????? " + topic_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao t??c kh??ng th??nh c??ng!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/chu-de/status/{topic_id}", method = RequestMethod.GET)
	public String onOffTopic(@PathVariable int topic_id, ModelMap modelMap, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			topicSevice.onOffTopic(topic_id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao t??c th??nh c??ng!");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin ???? thay ?????i tr???ng th??i ch??? ????? " + topic_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao t??c kh??ng th??nh c??ng!");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

}
