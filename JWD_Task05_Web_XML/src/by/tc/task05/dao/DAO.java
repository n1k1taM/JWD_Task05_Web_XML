package by.tc.task05.dao;

import java.util.List;

import by.tc.task05.bean.Book;

public interface DAO {
	List<Book> getBooksByPageNumber(int pageNumber);
	int getTotalPageNumber(int booksPerPage);
}
