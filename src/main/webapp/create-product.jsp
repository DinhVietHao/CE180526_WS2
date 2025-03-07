<%-- 
    Document   : create-movie
    Created on : Feb 24, 2025, 11:42:31 AM
    Author     : DVHao
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Account acc = (Account) session.getAttribute("users");
    String name = (String) session.getAttribute("username");
    if (acc == null) {
        response.sendRedirect("login.jsp");
        return;
    }
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
        </nav>
        <div class="container">
            <h1>Create new product</h1>
            <form method="post" action="Product?action=create">
                <div class="mb-3">
                    <label class="form-label">Product Name</label>
                    <input type="text" class="form-control" name="pName" id="pName" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Product Price</label>
                    <input type="number" class="form-control" name="pPrice" id="pPrice" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Product Quantity</label>
                    <input type="number" class="form-control" name="pQuantity" id="pQuantity" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Product Description</label>
                    <input type="text" class="form-control" name="pDescription" id="pDescription" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Category Name</label>
                    <select class="form-select" name="cId" id="cId" aria-label="Default select example">
                        <%
                            List<Category> list = (ArrayList) request.getAttribute("list");
                            for (Category cat : list) {
                        %>
                        <option value="<%= cat.getCatId()%>"><%= cat.getCatName()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <a href="Product" class="btn btn-secondary" id="back"><i class="bi bi-arrow-return-left"></i>Back</a>
                <button type="submit" class="btn btn-success" id="submit"><i class="bi bi-file-earmark-plus"></i>Create</button>
            </form>
            <%
                String err = (String) request.getAttribute("err");
                if (err != null) {
                    out.println(err);
                }
            %>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>