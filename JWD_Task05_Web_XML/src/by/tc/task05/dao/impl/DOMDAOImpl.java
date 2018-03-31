package by.tc.task05.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.tc.task05.bean.Book;
import by.tc.task05.dao.DAO;
import by.tc.task05.dao.DAOException;
import by.tc.task05.dao.ResourceManager;

public class DOMDAOImpl implements DAO {

	@Override
	public List<Book> getBooks() throws DAOException {

		List<Book> bookList = new ArrayList<>();
		File file = new File(ResourceManager.getFilePath());
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);

			NodeList bookNodeList = document.getElementsByTagName("book");

			for (int i = 0; i < bookNodeList.getLength(); i++) {

				Element bookElement = (Element) bookNodeList.item(i);
				Book book = new Book();

				book.setAuthor(getChildNode(bookElement, "author").getTextContent().trim());
				book.setGenre(getChildNode(bookElement, "genre").getTextContent().trim());
				book.setPublisher(getChildNode(bookElement, "publisher").getTextContent().trim());
				book.setTitle(getChildNode(bookElement, "title").getTextContent().trim());
				book.setISBN(getChildNode(bookElement, "ISBN").getTextContent().trim());
				book.setYear(Integer.parseInt(getChildNode(bookElement, "year").getTextContent().trim()));

				bookList.add(book);

			}

			return bookList;
		} catch (SAXException | IOException | ParserConfigurationException e) {
			throw new DAOException(e);
		}

	}

	private Element getChildNode(Element element, String childNodeName) {
		NodeList childNode = element.getElementsByTagName(childNodeName);
		return (Element) childNode.item(0);
	}

	@Override
	public int getBookCounter() throws DAOException {
		int bookCounter = getBooks().size();
		return bookCounter;
	}

}
