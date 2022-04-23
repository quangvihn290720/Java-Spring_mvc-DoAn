package com.khoaluantotnghiep.filter;

import java.io.IOException;

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

import com.khoaluantotnghiep.entity.UserEntity;

@WebFilter(urlPatterns = {"/quan-tri/lien-he*", "/quan-tri/lien-he/*"})
public class AdminContactFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resq = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("LoginInfo");
		UserEntity user = (UserEntity) obj;
		if (user != null && user.getRole().equals("ROLE_ADMIN") && user.getRoleEntity().getCode().equals("ADMIN_ALL")
				|| user != null && user.getRole().equals("ROLE_ADMIN")
						&& user.getRoleEntity().getCode().equals("ADMIN_CONTACT")) {
			chain.doFilter(request, response);
			return; //
		} else {
			resq.sendRedirect(req.getContextPath() + "/dang-nhap?error2=not_permission");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
