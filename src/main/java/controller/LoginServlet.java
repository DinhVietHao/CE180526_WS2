/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author DVHao
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        AccountDAO accountDAO = new AccountDAO();
        HttpSession session = request.getSession();
        Account acc = accountDAO.verifyMD5(user, pass);
        if (acc.getId() != -1) {
            session.setAttribute("users", acc);
            session.setAttribute("username", acc.getUsername());
            response.sendRedirect("Product");
        } else {
            request.setAttribute("err", "<p style='color:red'>Username invalid, password invalid</p>");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
