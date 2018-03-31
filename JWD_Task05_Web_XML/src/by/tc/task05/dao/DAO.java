package by.tc.task05.dao;

import java.util.List;

import by.tc.task05.bean.Book;

public interface DAO {
	List<Book> getBooks() throws DAOException;
	int getBookCounter()throws DAOException;
}
