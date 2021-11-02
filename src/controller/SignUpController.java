package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.UserDAO;

/**
 * Servlet implementation class SignUpController
 */
public class SignUpController extends HttpServlet implements IBaseController {
	private static final long serialVersionUID = 1L;
	private final UserDAO dao = new UserDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpController() {
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
		System.out.println(uri);

		switch (uri) {
		case "/signup/form":
			this.showFormSignUp(request, response);
			break;
		case "/signup":
			this.signUp(request, response);
			break;

		default:
			break;
		}
	}

	protected void showFormSignUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.view("auth/signup.jsp", request, response);
	}

	protected void signUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String[]> mapData = request.getParameterMap();
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("email", mapData.get("email")[0]);
		data.put("password", mapData.get("password")[0]);

		if (this.dao.create(data) != -1) {
			response.sendRedirect(this.url("login/form"));

			return;
		} else {
			response.sendRedirect(this.url("signup/form"));

			return;
		}
	}

}
