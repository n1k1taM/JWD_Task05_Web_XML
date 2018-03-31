package by.tc.task05.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.tc.task05.bean.Book;
import by.tc.task05.service.BookService;
import by.tc.task05.service.ServiceException;
import by.tc.task05.service.ServiceFactory;

public class Controller extends HttpServlet {

	private final static int BOOKS_PER_PAGE = 2;

	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pageName = "/index.jsp";
		ServiceFactory factory = ServiceFactory.getInstance();
		String parserType = request.getParameter("parserType");
		
		BookService service = factory.getService(parserType);

		int pageNumber = 1;
		if (request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}

		try {
			List<Book> books = service.getBooksByPageNumber(pageNumber, BOOKS_PER_PAGE);
			int maxPageNumber = service.getMaxPageNumber(BOOKS_PER_PAGE);

			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("books", books);
			request.setAttribute("maxPageNumber", maxPageNumber);
			request.setAttribute("parserType", parserType);
			
			pageName = "/jsp/ShowBook.jsp";

		} catch (ServiceException e) {
			request.setAttribute("errorMassage", "Ошибка парсинга");
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pageName);
		dispatcher.forward(request, response);
	}
}
