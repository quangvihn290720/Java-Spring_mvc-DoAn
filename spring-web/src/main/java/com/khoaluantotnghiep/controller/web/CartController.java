package com.khoaluantotnghiep.controller.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aspose.pdf.Document;
import com.aspose.pdf.HtmlLoadOptions;
import com.khoaluantotnghiep.dto.CartDTO;
import com.khoaluantotnghiep.entity.BilldetailEntity;
import com.khoaluantotnghiep.entity.BillsEntity;
import com.khoaluantotnghiep.entity.CouponEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.BillsServiceImpl;
import com.khoaluantotnghiep.service.impl.CartServiceImpl;
import com.khoaluantotnghiep.service.impl.CouponServiceImpl;

@Controller
public class CartController extends BaseController {

	@Autowired
	private CartServiceImpl cartService = new CartServiceImpl();
	@Autowired
	private CouponServiceImpl couponService = new CouponServiceImpl();
	@Autowired
	private BillsServiceImpl billsService = new BillsServiceImpl();
	@Autowired
	public JavaMailSender emailSender;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/AddCart/{id}")
	public String AddCart(HttpServletRequest request, HttpSession session, @PathVariable Integer id,
			final RedirectAttributes redirectAttributes) {
		HashMap<Integer, CartDTO> cart = (HashMap<Integer, CartDTO>) session.getAttribute("Cart");
		if (cart == null) {
			cart = new HashMap<Integer, CartDTO>();
		}
		cart = cartService.addCart(id, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalQuantyCart", cartService.totalQuanty(cart));
		session.setAttribute("TotalPriceCart", cartService.totalPrice(cart));
		redirectAttributes.addFlashAttribute("msgAddCart", "Thêm sản phẩm thành công!");
		return "redirect:" + request.getHeader("Referer");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/EditCart/{id}/{quanty}")
	public String EditCart(HttpServletRequest request, HttpSession session, @PathVariable Integer id,
			@PathVariable Integer quanty, final RedirectAttributes redirectAttributes) {
		HashMap<Integer, CartDTO> cart = (HashMap<Integer, CartDTO>) session.getAttribute("Cart");
		if (cart == null) {
			cart = new HashMap<Integer, CartDTO>();
		}
		cart = cartService.editCart(id, quanty, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalQuantyCart", cartService.totalQuanty(cart));
		session.setAttribute("TotalPriceCart", cartService.totalPrice(cart));
		redirectAttributes.addFlashAttribute("msgUpdateCart", "Cập nhật sản phẩm thành công!");
		return "redirect:" + request.getHeader("Referer");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/DeleteCart/{id}")
	public String DeleteCart(HttpServletRequest request, HttpSession session, @PathVariable Integer id,
			final RedirectAttributes redirectAttributes) {
		HashMap<Integer, CartDTO> cart = (HashMap<Integer, CartDTO>) session.getAttribute("Cart");
		if (cart == null) {
			cart = new HashMap<Integer, CartDTO>();
		}
		cart = cartService.deleteCart(id, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalQuantyCart", cartService.totalQuanty(cart));
		session.setAttribute("TotalPriceCart", cartService.totalPrice(cart));
		redirectAttributes.addFlashAttribute("msgDeleteCart", "Xóa sản phẩm thành công!");
		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping(value = "/gio-hang", method = RequestMethod.GET)
	public ModelAndView Cart() {
		_mvShare.setViewName("web/cart/cart");
		return _mvShare;
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView CheckOut(HttpServletRequest request, HttpSession session,
			@ModelAttribute("bills") BillsEntity bills) {
		if (session.getAttribute("Cart") == null) {
			return new ModelAndView("redirect:/gio-hang");
		}
		_mvShare.setViewName("web/checkout/checkout");
		BillsEntity bill = new BillsEntity();
		UserEntity loginInfo = (UserEntity) session.getAttribute("LoginInfo");
		if (loginInfo != null) {
			bill.setDisplay_name(loginInfo.getUser_fullname());
			bill.setEmail(loginInfo.getUser_email());
			bill.setPhone(loginInfo.getUser_phone());
		}
		_mvShare.addObject("bill", bill);
		return _mvShare;
	}

	@RequestMapping(value = "/deletecart", method = RequestMethod.GET)
	public String DeleteAllCart(HttpSession session) {
		try {
			session.removeAttribute("Cart");
		} catch (Exception e) {

		}
		return "redirect:/gio-hang";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkout", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public ModelAndView CheckOutBill(HttpServletRequest request, HttpSession session,
			@ModelAttribute("bills") BillsEntity bills, final RedirectAttributes redirectAttributes) {
		bills.setQuanty((int) session.getAttribute("TotalQuantyCart"));
		bills.setTotal((double) session.getAttribute("TotalPriceCart") + 40000);
		bills.setStatus(0);
		String uuid = UUID.randomUUID().toString();
		// Remove dashes
		String uuid2 = uuid.replaceAll("-", "");
		bills.setCode(uuid2);
		String code = request.getParameter("reductionCode");
		CouponEntity codei = couponService.checkAvailCode(code);
		double priceafterpromotion;
		if (codei != null) {
			priceafterpromotion = codei.getPricesale();
			bills.setCoupon_id(codei.getId());
		} else {
			priceafterpromotion = 0;
			bills.setCoupon_id(1);
		}
		bills.setCoupon(codei != null);
		if (billsService.AddBills(bills, priceafterpromotion) > 0) {
			HashMap<Integer, CartDTO> carts = (HashMap<Integer, CartDTO>) session.getAttribute("Cart");
			try {
				billsService.AddBillsDetail(carts);
				if (codei != null) {
					couponService.subVailable(code);
				}
				// Create a Simple MailMessage.
				MimeMessage message = emailSender.createMimeMessage();
				boolean multipart = true;
				MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
				String content = null;
				URLConnection connection = null;
				try {
					connection = new URL("http://localhost:8080/spring-web/gio-hang/sendmail/" + uuid2)
							.openConnection();
					connection.setDoOutput(true); // Triggers POST.
					connection.setRequestProperty("Accept-Charset", "UTF-8");
					connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
					Scanner scanner = new Scanner(connection.getInputStream());
					scanner.useDelimiter("\\Z");
					content = scanner.next();
					scanner.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				helper.setTo(bills.getEmail());
				helper.setSubject("Cảm ơn bạn đã đặt hàng!");
				helper.setText(content, true);
				for (Map.Entry<Integer, CartDTO> itemCart : carts.entrySet()) {
					helper.addInline("image" + itemCart.getValue().getProductEntity().getProduct_id(),
							new File("C:/Users/Huy/Downloads/apache-tomcat-8.5.76/apache-tomcat-8.5.76/images/"
									+ itemCart.getValue().getProductEntity().getProductimg()));
				}
				helper.addInline("maillogo", new File(
						"C:/Users/Huy/Downloads/apache-tomcat-8.5.76/apache-tomcat-8.5.76/images/maillogo.png"));
				// Send Message!
				this.emailSender.send(message);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// ... cleanup that will execute whether or not an error occurred ...
			}
		}
		session.removeAttribute("Cart");
		redirectAttributes.addFlashAttribute("msg", "Đặt hàng thành công!");
		return new ModelAndView("redirect:/checkout/thankyou/" + bills.getCode());
	}

	@GetMapping("/gio-hang/sendmail/{code}")
	public ModelAndView viewmailcart(@PathVariable String code, HttpServletRequest request) {
		BillsEntity billitem = billsService.findBillsConfirm(code);
		List<BilldetailEntity> billdetail = billsService.getBillsDetail(billitem.getId());
		double total = 0;
		for (BilldetailEntity it : billdetail) {
			total += it.getTotal();
		}
		CouponEntity couponEntity = couponService.findCouponById(billitem.getCoupon_id());
		_mvShare.addObject("couponEntity", couponEntity);
		_mvShare.addObject("bill", billitem);
		_mvShare.addObject("billdetail", billdetail);
		_mvShare.addObject("total", total);
		_mvShare.setViewName("web/cart/mail");
		return _mvShare;
	}

	@GetMapping("checkout/thankyou/{code}")
	public ModelAndView viewConfirm(@PathVariable String code) {
		try {
			_mvShare.setViewName("web/checkout/confirmcheckout");
			BillsEntity billitem = billsService.findBillsConfirm(code);
			List<BilldetailEntity> billdetail = billsService.getBillsDetail(billitem.getId());
			double total = 0;
			for (BilldetailEntity it : billdetail) {
				total += it.getTotal();
			}
			CouponEntity couponEntity = couponService.findCouponById(billitem.getCoupon_id());
			_mvShare.addObject("couponEntity", couponEntity);
			_mvShare.addObject("bill", billitem);
			_mvShare.addObject("billdetail", billdetail);
			_mvShare.addObject("total", total);
		} catch (Exception e) {
			return new ModelAndView("redirect:/trang-chu");
		}
		return _mvShare;
	}

	@GetMapping("checkout/exportpdf")
	public void exportpdf(HttpServletRequest request) throws IOException {
		// Create and initialize URL
		URL oracleURL = new URL("https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html");

		// Get web page as input stream
		InputStream is = oracleURL.openStream();

		// Initialize HTML load options
		HtmlLoadOptions htmloptions = new HtmlLoadOptions();

		// Load stream into Document object
		Document pdfDocument = new Document(is, htmloptions);

		// Save output as PDF format
		pdfDocument.save("HTML-to-PDF.pdf");
	}
}
