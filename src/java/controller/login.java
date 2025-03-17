/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.userDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import user.account;

/**
 *
 * @author PC
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("logout".equals(action)) {
            // Hủy session để đăng xuất
            HttpSession session = request.getSession(false); // Không tạo session mới
            if (session != null) {
                session.invalidate(); // Xóa toàn bộ session
            }
            response.sendRedirect("login"); // Quay về trang đăng nhập
            return;
        }
        if ("signup".equals(action)) {
            
            response.sendRedirect("signup");
            return;
        }
        
        
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean log = false;
        PrintWriter out = response.getWriter();
        
        
         
        try {
            //Lay du lieu tu form edit
            
            
            String name = request.getParameter("email");
            String matkhau = request.getParameter("matkhau");

            userDAO us = new userDAO();
            if (name == "" || matkhau == "") {
                request.setAttribute("mess", "Plese enter email and password");
            } else {
                if (us.getacc(name) != null) {
                    account acc = us.getacc(name);
                    if (acc.getEmail().equals(name) && acc.getMatkhau().equals(matkhau)) {
                        log = true;
                        String getrole = "";
                        int role = acc.getRole();
                        if (role == 1) {
                            getrole = "Admin";
                        } else if (role == 2) {
                            getrole = "Nhân Viên";
                        } else if (role == 3) {
                            getrole = "Quý Khách";
                        }
                        
                        HttpSession session = request.getSession();
                        session.setAttribute("acc", acc);
                        session.setAttribute("role", role);
                        session.setAttribute("mess", "Login successfully !! Welcome " + getrole);
                        response.sendRedirect("home");
                        
                    } else {
                        request.setAttribute("mess", "Thông tin tài khoản hoặc mật khẩu không chính xác !");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("mess", "Wrong Information !");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
            

        } catch (Exception e) {
            request.setAttribute("mess", "Plese enter email and password");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
