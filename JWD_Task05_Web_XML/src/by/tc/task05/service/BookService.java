package by.tc.task05.service;

import java.util.List;



import by.tc.task05.bean.Book;

public interface BookService {
	List<Book> getBooksByPageNumber(int pageNumber,int bookPerPage) throws ServiceException;
	int getMaxPageNumber(int bookPerPage) throws ServiceException;
}
