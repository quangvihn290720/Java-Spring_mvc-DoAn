package com.khoaluantotnghiep.controller.admin;

import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.khoaluantotnghiep.dto.PaginateDTO;
import com.khoaluantotnghiep.service.impl.AccountServiceImpl;
import com.khoaluantotnghiep.service.impl.NoteServiceImpl;
import com.khoaluantotnghiep.service.impl.PaginatesServiceImpl;

@Controller(value = "noteControllerOfAdmin")
public class NoteController extends BaseController {
	@Autowired
	PaginatesServiceImpl paginateService;
	@Autowired
	NoteServiceImpl noteService;
	@Autowired
	AccountServiceImpl accountService;

	private int totalDataPage = 8;

	@RequestMapping(value = "/quan-tri/web/nhat-ky", method = RequestMethod.GET)
	public ModelAndView page() {
		Map<Integer, String> mapUser = accountService.mapUser();
		_mvShare.addObject("mapUser", mapUser);
		int totalData = noteService.findAllNote().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("notePaginate", noteService.GetDataNotePaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/note/note");
		return _mvShare;

	}

	@RequestMapping(value = "/quan-tri/web/nhat-ky/{currentPage}", method = RequestMethod.GET)
	public ModelAndView page(@PathVariable String currentPage) {
		Map<Integer, String> mapUser = accountService.mapUser();
		_mvShare.addObject("mapUser", mapUser);
		int totalData = noteService.findAllNote().size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage,
				Integer.parseInt(currentPage));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("notePaginate", noteService.GetDataNotePaginate(paginateInfo.getStart(), totalDataPage));
		_mvShare.setViewName("admin/note/note");
		return _mvShare;

	}

	@RequestMapping(value = "/quan-tri/web/nhat-ky/delete/{id}", method = RequestMethod.GET)
	public String deletaPage(@PathVariable int id, HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {
		try {
			noteService.deleteNote(id);
			redirectAttributes.addFlashAttribute("msg", "Thao tác thành công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgfail", "Thao tác không thành công");
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@InitBinder
	public void intDate(WebDataBinder dataBinder) {
		dataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
	}

	@GetMapping(value = "/quan-tri/web/nhat-ky/search")
	public ModelAndView viewsearch(@RequestParam(value = "page", required = true) String page,
			HttpServletRequest request) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		String to = request.getParameter("to");
		String from = request.getParameter("from");
		System.out.println(to);
		System.out.println(from);
		_mvShare.addObject("to", to);
		_mvShare.addObject("from", from);
		int totalData = noteService.findNoteByTimeBetween(to, from).size();
		PaginateDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalDataPage, Integer.parseInt(page));
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("totalData", totalData);
		_mvShare.addObject("notebytimePaginate",
				noteService.GetDataNoteByTimePaginate(paginateInfo.getStart(), totalDataPage, to, from));
		_mvShare.setViewName("admin/note/notefilter");
		return _mvShare;
	}
}
