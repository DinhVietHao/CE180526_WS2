<%-- 
    Document   : movies
    Created on : Feb 24, 2025, 10:39:06 AM
    Author     : DVHao
--%>

<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Account acc = (Account) session.getAttribute("users");
    String name = (String) session.getAttribute("username");
    List<Product> list = (List) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="css/styleindex.css"/>
    </head>
    <body>
        <nav class="nav">
            <div class="container" >
                <div class="nav-content">
                    <ul>
                        <li class="nav-item">
                            <a href="#">Products</a>
                        </li>

                    </ul>
                    <ul class="nav-content">
                        <li class="nav-tilte">
                            <a href="#">Products</a>
                        </li>
                        <li class="nav-tilte">
                            <a href="#">Hi, <%= name%>, </a>
                        </li>
                        <li class="nav-tilte">
                            <a href="Logout">logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav> <br>
        <div class="container">
            <h1>Products list</h1>
            <div class="text-end">
                <a href="Product?action=create" class="btn btn-success"><i class="bi bi-file-earmark-plus"></i>Create</a>
            </div>
            <%
                if (list != null) {
            %>
            <table class="table table-striped table-hover">
                    <tr>
                        <td>ID</th>
                        <th>Product Name</th>
                        <th>Product Price</th>
                        <th>Product Quantity</th>
                        <th>Product Description</th>
                        <th>Category Name</th>
                        <th>Actions</th>
                    </tr>
                    <%
                        for (Product p : list) {
                    %>
                    <tr class="text-center">
                        <td><%= p.getProId()%></td>
                        <td class="text-start"><%= p.getProName()%></td> 
                        <td class="text-start"><%= p.getProPrice()%></td> 
                        <td><%= p.getProQuantity()%></td>
                        <td><%= p.getProDescription()%></td>
                        <td><%= p.getCat().getCatName()%></td>
                        <td>
                            <a href="Product?action=update&id=<%= p.getProId()%>" class="btn btn-primary"><i class="bi bi-tools"></i>Edit</a>
                            <a href="Product?action=delete&id=<%= p.getProId()%>" class="btn btn-danger"><i class="bi bi-trash"></i>Delete</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
            </table>
            <%
            } else {
            %>
            <p class="text-center text-muted">No Data!</p>
            <%
                }
            %>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
