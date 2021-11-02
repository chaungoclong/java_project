package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Product;
import dao.impl.ProductDAO;
import helper.Helper;

/**
 * Servlet implementation class ProductController
 */
public class ProductController extends HttpServlet implements IBaseController {
	private static final long serialVersionUID = 1L;
	private final ProductDAO dao = new ProductDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
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
		case "/product":
			this.index(request, response);
			break;
		case "/product/create":
			this.create(request, response);
			break;
		case "/product/store":
			this.store(request, response);
			break;
		case "/product/edit":
			this.edit(request, response);
			break;
		case "/product/update":
			this.update(request, response);
			break;
		case "/product/destroy":
			this.destroy(request, response);
			break;

		default:
			break;
		}
	}

	// index
	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> listProduct = (new ProductDAO()).all();
		for (Product product : listProduct) {
			System.out.println(product.toString());
		}
		request.setAttribute("listProduct", listProduct);

		this.view("products/index.jsp", request, response);

	}

	// create
	protected void create(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.view("products/create.jsp", request, response);
	}

	// store
	protected void store(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Product product = Helper.requestToBean(Product.class, request);

		if (this.dao.create(product) != -1) {
			response.sendRedirect(this.url("product"));
			return;
		} else {
			response.sendRedirect(this.url("product/create"));
			return;
		}
	}

	// edit
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		Product product = this.dao.find(id);

		request.setAttribute("product", product);

		this.view("products/edit.jsp", request, response);
	}

	// update
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Product product = Helper.requestToBean(Product.class, request);

		if (this.dao.update(product) > 0) {
			response.sendRedirect(this.url("product"));

			return;
		} else {
			response.sendRedirect(this.url("product/edit?id=" + product.getId()));

			return;
		}
	}

	// destroy
	protected void destroy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");

		this.dao.delete(id);
		response.sendRedirect(this.url("product"));

		return;
	}
}
