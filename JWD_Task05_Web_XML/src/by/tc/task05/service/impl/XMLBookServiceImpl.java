package by.tc.task05.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.tc.task05.bean.Book;
import by.tc.task05.dao.DAO;
import by.tc.task05.dao.DAOException;
import by.tc.task05.dao.DAOFactory;
import by.tc.task05.service.BookService;
import by.tc.task05.service.ServiceException;

public class XMLBookServiceImpl implements BookService {
	private final DAO DAOinstance;

	public XMLBookServiceImpl(String parserType) {
		super();
		DAOFactory factory = DAOFactory.getInstance();
		DAOinstance = factory.getDAO(parserType);
	}

	@Override
	public List<Book> getBooksByPageNumber(int pageNumber, int bookPerPage) throws ServiceException {
		try {
			List<Book> bookList = DAOinstance.getBooks();

			int firstBookOnPageNumber = (pageNumber - 1) * bookPerPage;
			int lastBookOnPageNumber = pageNumber * bookPerPage -1;
			List<Book> bookSubList = getBookSubList(bookList, firstBookOnPageNumber, lastBookOnPageNumber);
			return bookSubList;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int getMaxPageNumber(int bookPerPage) throws ServiceException {
		try {
			int maxPageNumber;
			int bookCounter = DAOinstance.getBookCounter();
			maxPageNumber = bookCounter/bookPerPage;
			if(bookCounter % maxPageNumber != 0 ){
				maxPageNumber++;
			}
			return maxPageNumber;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	private List<Book> getBookSubList(List<Book> bookList, int startElement, int endElement) throws ServiceException {
		if (startElement > bookList.size() || endElement > bookList.size()) {
			throw new ServiceException();
		}
		List<Book> bookSubList = new ArrayList<>();
		for (int i = startElement; i <= endElement; i++) {
			bookSubList.add(bookList.get(i));
		}
		return bookSubList;
	}

}
