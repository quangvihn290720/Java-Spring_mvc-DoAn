package com.khoaluantotnghiep.controller.admin;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.khoaluantotnghiep.dto.PaginateDTO;
import com.khoaluantotnghiep.entity.NoteEntity;
import com.khoaluantotnghiep.entity.PostEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;
import com.khoaluantotnghiep.service.impl.PostServiceImpl;
import com.khoaluantotnghiep.service.impl.TopicServiceImpl;

@Controller(value = "postControllerOfAdmin")
public class PostController extends BaseController {
	@Autowired
	PostServiceImpl postService;
	@Autowired
	TopicServiceImpl topicSevice;
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	NoteServiceImpl noteService;

	private int totalDataPage = 5;

	@GetMapping(value = "/quan-tri/bai-viet")
	public ModelAndView Post() {
		if (topicSevice.findAllTopic() != null && topicSevice.findAllTopic().size() != 0) {
			_mvShare.addObject("topics", topicSevice.findAllTopic());
			Map<Integer, String> mapTopic = topicSevice.mapTopic();
			_mvShare.addObject("mapTopic", mapTopic);
			int totalData = postService.findAllPost().size();
			PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
			_mvShare.addObject("paginateInfo", paginateInfo);
			_mvShare.addObject("totalData", totalData);
			_mvShare.addObject("postPaginate", postService.GetDataPostPaginate(paginateInfo.getStart(), totalDataPage));
		}
		_mvShare.setViewName("admin/post/post");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/bai-viet/{currentPage}")
	public ModelAndView Post(@PathVariable String currentPage) {
		if (topicSevice.findAllTopic() != null && topicSevice.findAllTopic().size() != 0) {
			_mvShare.addObject("topics", topicSevice.findAllTopic());
			Map<Integer, String> mapTopic = topicSevice.mapTopic();
			_mvShare.addObject("mapTopic", mapTopic);
			int totalData = postService.findAllPost().size();
			int availPage = (totalData + totalDataPage - 1) / totalDataPage;
			if (Integer.parseInt(currentPage) > availPage) {
				currentPage = availPage + "";
				return new ModelAndView("redirect:/quan-tri/bai-viet/" + currentPage);
			}
			PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
					Integer.parseInt(currentPage));
			_mvShare.addObject("paginateInfo", paginateInfo);
			_mvShare.addObject("totalData", totalData);
			_mvShare.addObject("currentPage", currentPage);
			_mvShare.addObject("postPaginate", postService.GetDataPostPaginate(paginateInfo.getStart(), totalDataPage));
		}
		_mvShare.setViewName("admin/post/post");
		return _mvShare;
	}

	@GetMapping(value = "/quan-tri/bai-viet/add")
	public ModelAndView addSlide(@ModelAttribute("post") PostEntity post) {
		_mvShare.setViewName("admin/post/addpost");
		if (topicSevice.findAllTopic() != null && topicSevice.findAllTopic().size() != 0) {
			_mvShare.addObject("topics", topicSevice.findAllTopic());
		}
		return _mvShare;
	}

	@PostMapping(value = "/quan-tri/bai-viet/save", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String saveSlide(HttpSession session, HttpServletRequest request, @ModelAttribute("post") PostEntity post,
			ModelMap modelMap, @RequestParam(value = "image", required = false) MultipartFile photo,
			final RedirectAttributes redirectAttributes) {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		post.setPost_detail(request.getParameter("post_detail"));
		post.setPost_metadesc(request.getParameter("post_metadesc"));
		boolean check = true;
		if (postService.isTitledExists(post.getPost_title())) {
			redirectAttributes.addFlashAttribute("msgTitle", "TiÃªu Ä‘á»� Ä‘Ã£ tá»“n táº¡i");
			check = false;
		}
		if (postService.isSlugExists(post.getPost_slug())) {
			redirectAttributes.addFlashAttribute("msgSlug", "Slug Ä‘Ã£ tá»“n táº¡i");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", post);
			return "redirect:/quan-tri/bai-viet/add";
		}
		try {
			post.setPost_img(saveFile(photo));
			post.setCreated_by(loginInfo.getUser_id());
			post.setUpdated_by(loginInfo.getUser_id());
			post.setCreated_at(new Date());
			post.setUpdated_at(new Date());
			postService.addPage(post);
			redirectAttributes.addFlashAttribute("msg", "ThÃªm thÃ nh cÃ´ng");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin Ä‘Ã£ thÃªm bÃ i viáº¿t " + post.getPost_id());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "ThÃªm khÃ´ng thÃ nh cÃ´ng");
		}
		return "redirect:/quan-tri/bai-viet";
	}

	@RequestMapping(value = "/quan-tri/bai-viet/status/{post_id}", method = RequestMethod.GET)
	public String onOffPage(@PathVariable int post_id, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			postService.onOffPost(post_id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tÃ¡c thÃ nh cÃ´ng");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin Ä‘Ã£ thay Ä‘á»•i tráº¡ng thÃ¡i bÃ i viáº¿t " + post_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tÃ¡c khÃ´ng thÃ nh cÃ´ng");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/bai-viet/thung-rac", method = RequestMethod.GET)
	public ModelAndView trashPage() {
		_mvShare.addObject("topics", topicSevice.findAllTopic());
		Map<Integer, String> mapTopic = topicSevice.mapTopic();
		_mvShare.addObject("mapTopic", mapTopic);
		int totalData = postService.findTrashPost().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("postTrashPaginate",
				postService.GetDataPostTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/post/trashpost");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/bai-viet/thung-rac/{currentPage}", method = RequestMethod.GET)
	public ModelAndView trashPage(@PathVariable String currentPage) {
		_mvShare.addObject("topics", topicSevice.findAllTopic());
		Map<Integer, String> mapTopic = topicSevice.mapTopic();
		_mvShare.addObject("mapTopic", mapTopic);
		int totalData = postService.findTrashPost().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("postTrashPaginate",
				postService.GetDataPostTrashPaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/post/trashpost");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/bai-viet/trash/{post_id}", method = RequestMethod.GET)
	public String delTrashPage(@PathVariable int post_id, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			postService.delTrash(post_id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tÃ¡c thÃ nh cÃ´ng");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin Ä‘Ã£ xÃ³a táº¡m thá»�i bÃ i viáº¿t " + post_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tÃ¡c khÃ´ng thÃ nh cÃ´ng");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/bai-viet/retrash/{post_id}", method = RequestMethod.GET)
	public String reTrash(@PathVariable int post_id, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			postService.reTrash(post_id, loginInfo);
			redirectAttributes.addFlashAttribute("msg", "Thao tÃ¡c thÃ nh cÃ´ng");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin Ä‘Ã£ bá»� xÃ³a táº¡m thá»�i bÃ i viáº¿t " + post_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tÃ¡c khÃ´ng thÃ nh cÃ´ng");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/bai-viet/deletepage/{post_id}", method = RequestMethod.GET)
	public String deletaPage(@PathVariable int post_id, final RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session) {
		try {
			UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
			postService.deletePage(post_id);
			redirectAttributes.addFlashAttribute("msg", "XÃ³a thÃ nh cÃ´ng");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin Ä‘Ã£ vÄ©nh viá»…n bÃ i viáº¿t " + post_id);
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "XÃ³a khÃ´ng thÃ nh cÃ´ng");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/quan-tri/bai-viet/edit/{post_id}", method = RequestMethod.GET)
	public ModelAndView editPage(@ModelAttribute("post") PostEntity post, @PathVariable int post_id) {
		PostEntity postitem = postService.findPostById(post_id);
		_mvShare.addObject("postitem", postitem);
		_mvShare.setViewName("admin/post/editpost");
		return _mvShare;
	}

	@RequestMapping(value = "/quan-tri/bai-viet/editsave", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String editsavePage(HttpSession session, HttpServletRequest request, @ModelAttribute("post") PostEntity post,
			ModelMap modelMap, @RequestParam(value = "image", required = false) MultipartFile photo,
			final RedirectAttributes redirectAttributes) throws Exception {
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		post.setPost_detail(request.getParameter("post_detail"));
		post.setPost_metadesc(request.getParameter("post_metadesc"));
		boolean check = true;
		if (postService.isTitledExists(post.getPost_title(), post.getPost_id())) {
			redirectAttributes.addFlashAttribute("msgTitle", "TiÃªu Ä‘á»� Ä‘Ã£ tá»“n táº¡i");
			check = false;
		}
		if (postService.isSlugExists(post.getPost_slug(), post.getPost_id())) {
			System.out.println("Sai slug");
			redirectAttributes.addFlashAttribute("msgSlug", "Slug Ä‘Ã£ tá»“n táº¡i");
			check = false;
		}
		if (!check) {
			redirectAttributes.addFlashAttribute("oldvalue", post);
			return "redirect:/quan-tri/bai-viet/edit/" + post.getPost_id();
		}
		try {
			post.setPost_img(saveFile(photo));
			post.setUpdated_at(new Date());
			post.setUpdated_by(loginInfo.getUser_id());
			post.setPost_detail(request.getParameter("post_detail"));
			postService.updatePage(post);
			redirectAttributes.addFlashAttribute("msg", "Cáº­p nháº­t thÃ nh cÃ´ng");
			// them note de quan ly
			NoteEntity noteEntity = new NoteEntity();
			noteEntity.setContent("Admin Ä‘Ã£ chá»‰nh sá»­a bÃ i viáº¿t " + post.getPost_id());
			noteEntity.setCreated_at(new Date());
			noteEntity.setCreated_by(loginInfo.getUser_id());
			noteService.addNote(noteEntity);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Cáº­p nháº­t KhÃ´ng thÃ nh cÃ´ng");
			throw e;
		}
		return "redirect:/quan-tri/bai-viet";
	}
}
