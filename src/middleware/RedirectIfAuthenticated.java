package middleware;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.impl.UserDAO;
import helper.Helper;
import helper.Session;

/**
 * Servlet Filter implementation class RedirectIfAuthenticated
 */
@WebFilter("/RedirectIfAuthenticated")
public class RedirectIfAuthenticated implements Filter {

	/**
	 * Default constructor.
	 */
	public RedirectIfAuthenticated() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		UserDAO dao = new UserDAO();

		// kiểm tra có lưu đăng nhập
		Cookie[] cookies = req.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userid")) {
					String userId = cookie.getValue();

					User user = dao.find(userId);

					if (user != null) {
						Session.put(req, "user", user);

						res.sendRedirect(Helper.path("product"));

						return;
					} else {
						break;
					}
				}
			}
		}

		// kiểm tra session
		HttpSession session = req.getSession(false);

		if (session != null && session.getAttribute("user") != null) {
			res.sendRedirect(Helper.path("product"));

			return;
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
