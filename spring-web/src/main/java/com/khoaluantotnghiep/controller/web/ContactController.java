package com.khoaluantotnghiep.controller.web;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.khoaluantotnghiep.entity.ContactEntity;
import com.khoaluantotnghiep.service.impl.ContactServiceImpl;

@Controller(value = "contactControllerOfWeb")
public class ContactController extends BaseController {
	@Autowired
	ContactServiceImpl contactService;
	@Autowired
	public JavaMailSender emailSender;

	@RequestMapping(value = "/lien-he", method = RequestMethod.GET)
	public ModelAndView Page(@ModelAttribute("contact") ContactEntity contact) {
		_mvShare.setViewName("web/contact/contact");
		return _mvShare;
	}

	@RequestMapping(value = "/lien-he/send", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String send(@ModelAttribute("contact") ContactEntity contact, ModelMap modelMap,
			final RedirectAttributes redirectAttributes) {
		contact.setCreated_at(new Date());
		contact.setStatus(1);
		String email = contact.getEmail();
		String nameString = contact.getName();
		try {
			contactService.addContact(contact);
			redirectAttributes.addFlashAttribute("msgSs", "Gửi liên hệ thành công! Cảm ơn bạn đã góp ý.");
			MimeMessage message = emailSender.createMimeMessage();
			boolean multipart = true;
			MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
			String htmlMsg = "<p>Kính gửi khách hàng " + nameString + ",</p></br> \r\n" + "\r\n"
					+ "<p>Cảm ơn bạn đã gửi liên hệ! Chúng tôi rất vui khi được nghe ý kiến ​​của bạn..</p>\r\n <p> Hãy nói chuyện, gửi tin nhắn cho chúng tôi để đóng góp, giải đáp bất cứ lúc nào bạn muốn.</p>\r\n"
					+ "\r\n" + "Thân ái!</p>";
			message.setContent(htmlMsg, "text/html; charset=UTF-8");
			helper.setTo(email);
			helper.setSubject("Cảm ơn về phản hồi!");
			this.emailSender.send(message);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgSs", "Gửi liên hệ không thành công! Vui lòng thử lại sau.");
		}
		return "redirect:/lien-he";
	}
}
