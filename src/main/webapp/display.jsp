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
</head>
<body>
<div class="container">

    <h1 style="text-align: center">List product</h1>
    <div class="row">
        <div class="col-6">
            <button class="btn btn-primary">
                <a style="text-decoration:none; color: white" href="/product?action=create">Create new product</a>
            </button>
        </div>
        <div class="col-6" style="text-align: right">
            <form action="/product?action=search" method="post">
                <label><input type="text" name="search"></label>
                <button class="btn btn-primary" type="submit">Search</button>
            </form>
        </div>
    </div>


    <table class="table table-striped" style="margin: 20px auto auto;">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Color</th>
            <th>Category</th>
            <th colspan="2" style="text-align: center">Action</th>
        </tr>
        <c:forEach items="${products}" var="p">
            <tr>
                <td>${p.getId()}</td>
                <td>${p.getName()}</td>
                <td>${p.getPrice()}</td>
                <td>${p.getQuantity()}</td>
                <td>${p.getColor()}</td>
                <td>${p.getCategory().getName()}</td>
                <td>
                    <button class="btn btn-warning">
                        <a style="text-decoration:none; color: white; font-weight: bold"
                           href="/product?action=update&id=${p.getId()}">Update</a>
                    </button>
                </td>
                <td>
                    <button class="btn btn-danger">
                        <a style="text-decoration:none; color: white; font-weight: bold"
                           href="/product?action=delete&id=${p.getId()}">Delete</a>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
