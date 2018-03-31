<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Show books</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

	<div class="centred">
		<form action="Controller" method="get">
			<input type="submit" name="parserType" value="SAX">
		</form>
		<form action="Controller" method="get">
			<input type="submit" name="parserType" value="StaX" />
		</form>
		<form action="Controller" method="get">
			<input type="submit" name="parserType" value="DOM">
		</form>

	</div>


</body>

</html>