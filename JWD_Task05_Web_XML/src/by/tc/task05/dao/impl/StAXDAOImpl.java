package by.tc.task05.dao.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.tc.task05.bean.Book;
import by.tc.task05.dao.ConstantEnumNotFoundException;
import by.tc.task05.dao.DAO;
import by.tc.task05.dao.DAOException;
import by.tc.task05.dao.ResourceManager;

public class StAXDAOImpl implements DAO {

	@Override
	public List<Book> getBooks() throws DAOException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try {
			InputStream inputStream = new FileInputStream(ResourceManager.getFilePath());
			XMLStreamReader reader = factory.createXMLStreamReader(inputStream);
			List<Book> bookList = parse(reader);
			return bookList;

		} catch (IOException | XMLStreamException | ConstantEnumNotFoundException cause) {
			cause.printStackTrace();
			throw new DAOException(cause);
		}

	}

	@Override
	public int getBookCounter() throws DAOException {
		List<Book> bookList = getBooks();
		return bookList.size();
	}

	@SuppressWarnings("incomplete-switch")
	private List<Book> parse(XMLStreamReader reader) throws XMLStreamException, ConstantEnumNotFoundException {
		List<Book> bookList = new ArrayList<>();
		Book book = null;
		TagName elementName = null;
		while (reader.hasNext()) {
			int type = reader.next();

			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = TagName.getElementTagName(reader.getLocalName());
				switch (elementName) {
				case BOOK:
					book = new Book();
					break;
				}
				break;
			case XMLStreamConstants.CHARACTERS:
				String text = reader.getText().trim();
				if (text.isEmpty()) {
					break;
				}
				switch (elementName) {
				case TITLE:
					book.setTitle(text);
					break;
				case AUTHOR:
					book.setAuthor(text);
					break;
				case GENRE:
					book.setGenre(text);
					break;
				case PUBLISHER:
					book.setPublisher(text);
					break;
				case ISBN:
					book.setISBN(text);
					break;
				case YEAR:
					book.setYear(Integer.parseInt(text));
					break;

				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				elementName = TagName.getElementTagName(reader.getLocalName());
				switch (elementName) {
				case BOOK:
					bookList.add(book);
					break;
				}

			}
		}
		return bookList;
	}
}
