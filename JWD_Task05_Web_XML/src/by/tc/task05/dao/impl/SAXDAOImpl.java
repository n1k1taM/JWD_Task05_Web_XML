package by.tc.task05.dao.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import by.tc.task05.bean.Book;
import by.tc.task05.dao.DAO;
import by.tc.task05.dao.DAOException;
import by.tc.task05.dao.ResourceManager;

public class SAXDAOImpl implements DAO {

	@Override
	public List<Book> getBooks() throws DAOException {

		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();

			List<Book> bookList = new ArrayList<>();

			SAXBookHandler handler = new SAXBookHandler(bookList);
			InputSource inputSourse = new InputSource(new FileInputStream(ResourceManager.getFilePath()));

			reader.setContentHandler(handler);

			reader.parse(inputSourse);

			return bookList;
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			throw new DAOException();
			
		}

	}

	class SAXBookHandler extends DefaultHandler {
		private StringBuilder buffer;
		private Book book;
		private List<Book> bookList;

		public SAXBookHandler(List<Book> bookList) {
			this.bookList = bookList;
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			buffer = new StringBuilder();
			if (qName.equals("book")) {
				book = new Book();
			}
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			buffer.append(ch, start, length);
		}

		@SuppressWarnings("incomplete-switch")
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			TagName tag = TagName.valueOf(qName.toUpperCase().replace("-", "_"));
			
			switch (tag) {
			case TITLE:
				book.setTitle(buffer.toString().trim());
				break;
			case AUTHOR:
				book.setAuthor(buffer.toString().trim());
				break;
			case ISBN:
				book.setISBN(buffer.toString().trim());
				break;
			case GENRE:
				book.setGenre(buffer.toString().trim());
				break;
			case PUBLISHER:
				book.setPublisher(buffer.toString().trim());
				break;
			case YEAR:
				book.setYear(Integer.parseInt(buffer.toString().trim()));
				break;
			case BOOK:
				bookList.add(book);
			}

		}

	}

	@Override
	public int getBookCounter() throws DAOException {
		List<Book> books = getBooks();
		return books.size();
	}

}
