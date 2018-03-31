package by.tc.task05.service;

import java.util.HashMap;
import java.util.Map;

import by.tc.task05.service.impl.XMLBookServiceImpl;

public class ServiceFactory {
	private static ServiceFactory instanse = new ServiceFactory();
	private Map<String, BookService> services = new HashMap<String, BookService>();

	private ServiceFactory() {
		super();
		services.put("sax", new XMLBookServiceImpl("sax"));
		services.put("stax", new XMLBookServiceImpl("stax"));
		services.put("dom", new XMLBookServiceImpl("dom"));

	}

	public static ServiceFactory getInstance() {
		return instanse;
	}

	public BookService getService(String parserType) {
		return services.get(parserType.toLowerCase());

	}
}
