package by.tc.task05.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.tc.task05.service.ServiceException;

public interface Command {
	String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
