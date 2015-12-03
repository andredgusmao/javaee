package br.com.blog.javaee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/", "/tag/*"})
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = -4484238567627139238L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(pathInfo.equals(null)) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		request.setAttribute("tag", pathInfo);
		request.getRequestDispatcher("/tag.jsp").forward(request, response);
	}
}
