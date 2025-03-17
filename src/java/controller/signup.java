/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.userDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.account;
import user.khachhang;

/**
 *
 * @author PC
 */
@WebServlet(name = "signup", urlPatterns = {"/signup"})
public class signup extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userDAO u = new userDAO();
        List<account> listacc = new ArrayList<>();
        HttpSession session = request.getSession(); 
        PrintWriter out = response.getWriter();
        try {
            listacc = u.getAllacc(); 
            String tk = request.getParameter("email");
            String mk = request.getParameter("matkhau");
            String name = request.getParameter("name");
            String nphone = request.getParameter("number");
            boolean empty = false;
            for(int i=0;i<listacc.size();i++){
                if(tk.equals(listacc.get(i).getEmail())){
                    empty=true;
                    
                }
            }
            khachhang kh = new khachhang(0, name, nphone, tk);
            account acc = new account(0, tk, mk, 3, u.makh(tk));
            
            
            
            if(empty==false){
                boolean cr1=u.create2(kh);
                boolean cr2=u.create1(acc);
                
                if(cr1==true&&cr2==true){
                session.setAttribute("mess", "Tạo tài khoản thành công !");
                response.sendRedirect("login");
                }else{
                   session.setAttribute("mess", "Lỗi !");
                response.sendRedirect("signup"); 
                }
            }else{
                session.setAttribute("mess", "Tài Khoản Đã Tồn Tại !");
                response.sendRedirect("signup"); 
                
            }    
        } catch (Exception e) {
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
