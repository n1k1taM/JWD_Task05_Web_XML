package by.tc.task05.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.tc.task05.controller.command.Command;
import by.tc.task05.controller.command.CommandFactory;
import by.tc.task05.service.ServiceException;

public class Controller extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CommandFactory commandFactory = CommandFactory.getInstance();
		String pageName = "index.jsp";
		
		try {
			Command command = commandFactory.getCommand(request);
			pageName = command.execute(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
			// LOGGER
			// } catch (CommandException e) {
			// e.printStackTrace();
			// //LOGGER
			// }

			// responseMessage ????
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pageName);
		dispatcher.forward(request, response);
	}
}
