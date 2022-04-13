<%--
  Created by IntelliJ IDEA.
  User: letha
  Date: 4/7/2022
  Time: 9:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8080/person/upload" method="post" enctype="multipart/form-data" >
    <label for="files">Select files:</label>
    <input type="file" id="files" name="files" multiple><br><br>
    <input type="submit">
</form>
</body>
</html>
