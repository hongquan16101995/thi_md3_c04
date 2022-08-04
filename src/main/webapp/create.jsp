<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 08/04/2022
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        input {
            border: none;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="container">
<h1 style="text-align: center">Create new product</h1>
<form action="/product?action=create" method="post">
    <table class="table">
        <tr>
            <th><label for="name">Name</label></th>
            <td><input id="name" type="text" name="name"></td>
        </tr>
        <tr>
            <th><label for="price">Price</label></th>
            <td><input id="price" type="text" name="price"></td>
        </tr>
        <tr>
            <th><label for="quantity">Quantity</label></th>
            <td><input id="quantity" type="text" name="quantity"></td>
        </tr>
        <tr>
            <th><label for="color">Color</label></th>
            <td><input id="color" type="text" name="color"></td>
        </tr>
        <tr>
            <th>Category</th>
            <td><label>
                <select name="category" class="form-select">
                    <c:forEach items="${categories}" var="c">
                        <option value="${c.getId()}">${c.getName()}</option>
                    </c:forEach>
                </select>
            </label></td>
        </tr>
        <tr>
            <td></td>
            <td><button class="btn btn-primary" type="submit">Create</button></td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
