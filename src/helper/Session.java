package helper;

import javax.servlet.http.HttpServletRequest;

public class Session {
	public static void put(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
	}

	public static Object get(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}

	public static void remove(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}

	public static void main(String[] args) {

	}
}
