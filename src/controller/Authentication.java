package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.impl.UserDAO;
import helper.Session;

/**
 * Servlet implementation class Authentication
 */
public class Authentication extends HttpServlet implements IBaseController {
	private static final long serialVersionUID = 1L;
	private final UserDAO dao = new UserDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authentication() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String uri = request.getServletPath();

		switch (uri) {
		case "/login/form":
			this.showFormLogIn(request, response);
			break;

		case "/login":
			this.logIn(request, response);
			break;

		case "/logout":
			this.logOut(request, response);
			break;

		default:
			break;
		}
	}

	protected void showFormLogIn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.view("auth/login.jsp", request, response);
	}

	protected void logIn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = this.dao.login(email, password);

		if (user != null) {
//			HttpSession session = request.getSession();
//			session.setAttribute("user", user);

			Session.put(request, "user", user);
			Session.put(request, "success", "login success");

			response.sendRedirect("/PROJECT/product");

			return;
		} else {
			response.sendRedirect("/PROJECT/login/form");

			return;
		}

	}

	protected void logOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession(false);
//
//		if (session != null) {
//			session.removeAttribute("user");
//			
//			response.sendRedirect("/PROJECT/login/form");
//
//			return;
//		}
		Session.remove(request, "user");

		response.sendRedirect("/PROJECT/login/form");

		return;
	}

}
