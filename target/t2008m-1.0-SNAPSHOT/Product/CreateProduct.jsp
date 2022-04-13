<%@ page import="java.util.HashMap" %>
<%@ page import="com.example.helloT2008M.entity.Product" %>
<%@ page import="com.example.helloT2008M.ultil.Constants.Constants" %><%--
  Created by IntelliJ IDEA.
  User: letha
  Date: 4/11/2022
  Time: 12:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors");
    System.out.println(errors);
    if (errors == null) {
        errors = new HashMap<>();
    }
%>
<%
    Product product = (Product) request.getAttribute("updateProduct");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Title</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<body>

<form action="<%=(product!=null?"/products/update":"/products/create")%>" class="col-md-9 m-auto" method="post" name="product-form">
    <%
        if (product != null) {%>
    <div class="form-group" style="opacity: 0">
        <label for="id">Id</label>
        <input type="text" value="<%=product.getId()%>" class="form-control" name="id"
               id="id" placeholder="id">
    </div>
    <%}%>
    <div class="form-group">
        <label for="name">Name</label>
        <input value="<%=(product!=null?product.getName():"")%>" type="text" class="form-control" name="name" id="name"
               placeholder="Product name">
        <%
            if (errors.containsKey("name")) {%>
        <span style="color: red"><%=errors.get("name")%></span>
        <%}%>
    </div>
    <div class="form-group">
        <label for="category">Category</label>
        <select value class="form-select" name="category" id="category" aria-label="category">
            <option selected><%=(product != null ? product.getCategory() : "Category")%>
            </option>
            <option value="Món nướng">Món nướng</option>
            <option value="Món luộc">Món luộc</option>
            <option value="Món chay">Món chay</option>
            <option value="Đồ uống">Đồ uống</option>
        </select>
        <%
            if (errors.containsKey("category")) {%>
        <span style="color: red"><%=errors.get("category")%></span>
        <%}%>
    </div>
    <div class="form-group">
        <label for="description">Description</label>
        <textarea class="form-control" name="description" id="description" rows="3">
            <%=(product != null ? product.getDescription() : "")%>
        </textarea>
        <%
            if (errors.containsKey("description")) {%>
        <span style="color: red"><%=errors.get("description")%></span>
        <%}%>
    </div>
    <div class="form-group">
        <label for="image">Image</label>
        <button type="button" id="upload_widget" class="cloudinary-button">Upload files</button>
        <img src="" class="hide" id="image-preview"/>
        <input type="hidden" value="" class="form-control" name="image"
               id="image" placeholder="Image">
        <%
            if (errors.containsKey("image")) {%>
        <span style="color: red"><%=errors.get("image")%></span>
        <%}%>
    </div>
    <div class="form-group">
        <label for="price">Price</label>
        <input type="number" value="<%=(product!=null?product.getPrice():0)%>" class="form-control" name="price"
               id="price" placeholder="Price">
        <%
            if (errors.containsKey("price")) {%>
        <span style="color: red"><%=errors.get("price")%></span>
        <%}%>
    </div>
    <%
        if (product != null) {%>
    <div class="form-group">
        <label for="status">Status</label>
        <select class="form-select" name="status" id="status" aria-label="status">
            <option selected><%=(product != null ? product.getStatus() : "Category")%></option>
            <option value="<%=Constants.STATUS_STOP_SELLING%>"><%=Constants.STATUS_STOP_SELLING%></option>
            <option value="<%=Constants.STATUS_DELETED%>"><%=Constants.STATUS_DELETED%></option>
        </select>
        <%
            if (errors.containsKey("status")) {%>
        <span style="color: red"><%=errors.get("status")%></span>
        <%}%>
    </div>
    <%}%>

    <%
        if (product != null) {%>
    <div class="form-group" style="opacity: 0">
        <label for="createAt">Create At</label>
        <input type="text" value="<%=product.getCreateAt()%>" class="form-control" name="createAt"
               id="createAt" placeholder="createAt">
    </div>
    <%}%>
    <button type="submit" class="btn btn-primary w-100 mt-4">Submit</button>
</form>

<script src="https://upload-widget.cloudinary.com/global/all.js" type="text/javascript"></script>

<style>
    .hide{
        display: none;
    }
    #image-preview{
        width: 250px;
    }
</style>

<script type="text/javascript">
    var myWidget = cloudinary.createUploadWidget({
            cloudName: 'ledat123',
            uploadPreset: 'f64vudk6'}, (error, result) => {
            if (!error && result && result.event === "success") {
                console.log('Done! Here is the image info: ', result.info);
                const img = document.getElementById("image-preview");
                img.classList.remove("hide");
                img.src= result.info.url;
                document.forms["product-form"]['image'].value = result.info.url;
            }
        }
    )

    document.getElementById("upload_widget").addEventListener("click", function(){
        myWidget.open();
    }, false);
</script>
</body>
</html>
