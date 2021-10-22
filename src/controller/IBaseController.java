package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IBaseController {
	public static final String VIEW_PATH = "/views/";

	default void view(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		path = VIEW_PATH + path;
		request.getRequestDispatcher(path).forward(request, response);

		return;
	}
}
