package by.tc.task05.dao;

import java.util.HashMap;
import java.util.Map;

import by.tc.task05.dao.impl.DOMDAOImpl;
import by.tc.task05.dao.impl.SAXDAOImpl;
import by.tc.task05.dao.impl.StAXDAOImpl;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	private Map<String, DAO> DAOProvider = new HashMap<String, DAO>();

	private DAOFactory() {
		super();
		DAOProvider.put("sax", new SAXDAOImpl());
		DAOProvider.put("stax", new StAXDAOImpl());
		DAOProvider.put("dom", new DOMDAOImpl());

	}

	public static DAOFactory getInstance() {
		return instance;
	}

	public DAO getDAO(String parserType) {
		DAO DAOInstance = DAOProvider.get(parserType.toLowerCase());
		if (DAOInstance == null) {
			DAOInstance = DAOProvider.get("sax");
		}
		return DAOInstance;
	}

}
