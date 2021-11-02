package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Const;
import helper.Helper;

public interface IBaseController {
	
	// load view
	default void view(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		path = Const.VIEW_PATH + path;
		request.getRequestDispatcher(path).forward(request, response);

		return;
	}
	
	// tạo đường dẫn chính xác
	default String url(String path) {
		return Helper.path(path);
	}
}
