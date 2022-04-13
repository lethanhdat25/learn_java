<%@ page import="com.example.helloT2008M.entity.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: letha
  Date: 4/11/2022
  Time: 12:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
%>
<html>

<head>
    <title>Title</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<%
    String error = (String) request.getAttribute("error");
    if (error != null){

%>
<script>
    window.alert("<%=error%>");
</script>
<%}%>
<body>
<a href="/products/create" class="btn btn-primary">New Product</a>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">First</th>
        <th scope="col">Last</th>
        <th scope="col">Handle</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Product product :
                products) {

    %>
    <tr>
        <th scope="row"><img style="width: 150px" src=<%=product.getImage()%>></th>
        <th scope="row"><%=product.getName()%></th>
        <th scope="row"><%=product.getPrice()%></th>
        <th scope="row"><%=product.getCategory()%></th>
        <th scope="row"><%=product.getDescription()%></th>
        <th scope="row"><%=product.getCreateAt()%></th>
        <th scope="row"><%=product.getUpdateAt()%></th>
        <th scope="row"><%=product.getStatus()%></th>
        <th>
            <a href="/products/update?id=<%=product.getId()%>" class="btn btn-primary">Update</a>
            <button type="button" class="btn btn-danger">Delete</button>
        </th>
    </tr>
    <%}%>
    </tbody>
</table>
</body>
</html>
