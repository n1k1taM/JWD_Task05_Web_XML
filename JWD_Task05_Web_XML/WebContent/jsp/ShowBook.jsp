<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  uri="/WEB-INF/tld/pagination.tld" prefix="p"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<a href="${pageContext.request.contextPath}">На главную</a>
	<div>
		<table border="1">
			<tr>
				<th>Author</th>
				<th>Title</th>
				<th>Genre</th>
				<th>Year</th>
				<th>ISBN</th>
				<th>Publisher</th>
				

			</tr>
			<jsp:useBean id="Book" class="by.tc.task05.bean.Book" />
			<c:forEach var="book" items="${books}">
				<tr>
					<td><c:out value="${book.author}" /></td>
					<td><c:out value="${book.title}" /></td>
					<td><c:out value="${book.genre}" /></td>
					<td><c:out value="${book.year}" /></td>
					<td><c:out value="${book.ISBN}" /></td>
					<td><c:out value="${book.publisher}" /></td>
				</tr>
			</c:forEach>


		</table>
	</div>



	<div class="pagination">
		<p:pagination parsType="${parserType}" currentPage="${pageNumber}"
			maxPage="${maxPageNumber}" />
	</div>


</body>
</html>