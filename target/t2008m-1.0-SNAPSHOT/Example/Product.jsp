<%@ page import="com.example.helloT2008M.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.helloT2008M.entity.ProductAss" %><%--
  Created by IntelliJ IDEA.
  User: letha
  Date: 4/11/2022
  Time: 12:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ProductAss> products = (List<ProductAss>) request.getAttribute("products");
%>
<html>

<head>
    <title>Title</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<body>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Amount</th>
        <th scope="col">Price</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (ProductAss product :
                products) {

    %>
    <tr>
        <th scope="row"><%=product.getName()%></th>
        <th scope="row"><%=product.getAmount()%></th>
        <th scope="row"><%=product.getPrice()%></th>
    </tr>
    <%}%>
    </tbody>
</table>
</body>
</html>
