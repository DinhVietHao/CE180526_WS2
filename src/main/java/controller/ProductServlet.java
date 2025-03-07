/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Category;
import model.Product;

/**
 *
 * @author DVHao
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/Product"})
public class ProductServlet extends HttpServlet {

    DAO dao = new DAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("users");
        if (acc == null) {
            response.sendRedirect("Login");
            return;
        }

        String action = (String) request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        if (action.equalsIgnoreCase("list")) {
            List<Product> data = dao.getAllProducts();
            request.setAttribute("list", data);
            request.getRequestDispatcher("product.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("create")) {
            List<Category> list = dao.getAllCategories();
            request.setAttribute("list", list);
            request.getRequestDispatcher("create-product.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("delete")) {
            Product p = null;
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                p = dao.getProductById(id);
                if (p != null) {
                    request.setAttribute("p", p);
                    request.getRequestDispatcher("delete-product.jsp").forward(request, response);
                } else {
                    response.sendRedirect("Product");
                }
            } catch (ServletException | IOException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        } else if (action.equalsIgnoreCase("update")) {
            Product p = null;
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                p = dao.getProductById(id);
                if (p != null) {
                    List<Category> list = dao.getAllCategories();
                    request.setAttribute("list", list);
                    request.setAttribute("p", p);
                    request.getRequestDispatcher("update-product.jsp").forward(request, response);
                } else {
                    response.sendRedirect("Product");
                }
            } catch (ServletException | IOException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = (String) request.getParameter("action");
        if (action.equalsIgnoreCase("create")) {
            String pName = request.getParameter("pName");
            long pPrice = Long.parseLong(request.getParameter("pPrice"));
            int pQuantity = Integer.parseInt(request.getParameter("pQuantity"));
            String pDescription = request.getParameter("pDescription");
            int cId = Integer.parseInt(request.getParameter("cId"));
            if (dao.insert(pName, pPrice, pQuantity, pDescription, cId)) {
                response.sendRedirect("Product");
            } else {
                request.setAttribute("err", "<p style='color:red'>Create Failed</p>");
                request.getRequestDispatcher("create-product.jsp").forward(request, response);
            }
        } else if (action.equalsIgnoreCase("delete")) {
            try {
                int id = Integer.parseInt(request.getParameter("pId"));
                if (dao.delete(id)) {
                    response.sendRedirect("Product");
                } else {
                    request.setAttribute("err", "<p style='color:red'>Delete Failed</p>");
                    request.getRequestDispatcher("delete-product.jsp").forward(request, response);
                }
            } catch (ServletException | IOException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        } else if (action.equalsIgnoreCase("update")) {
            try {
                int pId = Integer.parseInt(request.getParameter("id"));
                String pName = request.getParameter("pName");
                long pPrice = Long.parseLong(request.getParameter("pPrice"));
                int pQuantity = Integer.parseInt(request.getParameter("pQuantity"));
                String pDescription = request.getParameter("pDescription");
                int cId = Integer.parseInt(request.getParameter("cId"));
                if (dao.update(pId, pName, pPrice, pQuantity, pDescription, cId)) {
                    response.sendRedirect("Product");
                } else {
                    request.setAttribute("err", "<p style='color:red'>Update Failed</p>");
                    request.getRequestDispatcher("update-product.jsp").forward(request, response);
                }
            } catch (ServletException | IOException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
