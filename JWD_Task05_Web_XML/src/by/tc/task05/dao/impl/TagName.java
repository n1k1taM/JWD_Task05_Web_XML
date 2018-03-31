package by.tc.task05.dao.impl;

import by.tc.task05.dao.ConstantEnumNotFoundException;

public enum TagName {
	TITLE, AUTHOR, ISBN, GENRE, PUBLISHER, YEAR, BOOK, BOOK_COLLECTION;

	public static TagName getElementTagName(String element) throws ConstantEnumNotFoundException {
		switch (element) {
		case "title":
			return TITLE;
		case "author":
			return AUTHOR;
		case "ISBN":
			return ISBN;
		case "genre":
			return GENRE;
		case "publisher":
			return PUBLISHER;
		case "year":
			return YEAR;
		case "book":
			return BOOK;
		case "book-collection" :
			return BOOK_COLLECTION;
		default:
			throw new ConstantEnumNotFoundException();
		}
	}

}
