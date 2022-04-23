package com.khoaluantotnghiep.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.khoaluantotnghiep.entity.ConfigwebEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.impl.ConfigwebServiceImpl;

@WebFilter(urlPatterns = "/*")
public class MaintenanceFilter implements Filter {
	@Autowired
	ConfigwebServiceImpl configwebService;
	private static final List<String> ALLOWED_PATHS = Arrays.asList("/bao-tri", "/quan-tri", "/dang-nhap", "/images",
			"/dang-xuat", "/template");

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resq = (HttpServletResponse) response;
		String path = req.getRequestURI();
		ConfigwebEntity configweb = configwebService.findConfigweb();
		boolean allowedPath = false;
		String ctxPath = req.getContextPath();
		path = path.replace(ctxPath, "");
		for (String e : ALLOWED_PATHS) {
			if (path.contains(e)) {
				allowedPath = true;
				break;
			}
		}

		if (configweb.getStatus() == 0 && !allowedPath) {
			HttpSession session = req.getSession();
			Object obj = session.getAttribute("LoginInfo");
			UserEntity user = (UserEntity) obj;
			if (user != null && user.getRole().equals("ROLE_ADMIN")) {
				chain.doFilter(request, response);
			} else {
				resq.sendRedirect(req.getContextPath() + "/bao-tri");
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
