<%-- 
    Document   : delete-movie
    Created on : Feb 24, 2025, 1:49:08 PM
    Author     : DVHao
--%>
<%@page import="model.Product"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Account acc = (Account) session.getAttribute("users");
    String name = (String) session.getAttribute("username");
    if (acc == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    Product p = (Product) request.getAttribute("p");
    if (p == null) {
        response.sendRedirect("Product");
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
        </nav> <br>
        <div class="container">
            <h1>Delete product</h1>
            <form method="POST" action="Product?action=delete">
                <p>Are you sure to delete product <b class="p_name"><%=p.getProName()%></b> with id <b class="p_id"><%=p.getProId()%></b>?</p>
                <input name="pId" id="pId" value="<%= p.getProId()%>" hidden disable>
                <div class="mb-3">
                    <label class="form-label">Product Name</label>
                    <input type="text" class="form-control bg-light" name="pName" id="pName" value="<%= p.getProName()%>" disable readonly>
                </div>
                <div class="mb-3">
                    <label class="form-label">Product Price</label>
                    <input type="number" class="form-control bg-light" name="pPrice" id="pPrice" value="<%= p.getProPrice()%>" disable readonly>
                </div>
                <div class="mb-3">
                    <label class="form-label">Product Quantity</label>
                    <input type="number" class="form-control bg-light" name="pQuantity" id="pQuantity" value="<%= p.getProQuantity()%>" disable readonly>
                </div>
                <div class="mb-3">
                    <label class="form-label">Product Description</label>
                    <input type="text" class="form-control bg-light" name="pDescription" id="pDescription" value="<%= p.getProDescription()%>" disable readonly>
                </div>
                <div class="mb-3">
                    <label class="form-label">Category Name</label>
                    <select class="form-select bg-light" name="cId" id="cId" aria-label="Default select example" disable>
                        <%
                            switch (p.getCat().getCatId()) {
                                case 1:
                                    out.println("<option value='1'>Apple</option>");
                                    break;
                                case 2:
                                    out.println("<option value='2'>Samsung</option>");
                                    break;
                                case 3:
                                    out.println("<option value='3'>Xiaomi</option>");
                                    break;
                            }
                        %>
                    </select>
                </div>
                <a href="Product" class="btn btn-secondary" id="back"><i class="bi bi-arrow-return-left"></i>Back</a>
                <button type="submit" class="btn btn-danger" id="submit"><i class="bi bi-trash"></i>Delete</button>
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
