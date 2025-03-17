/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DonHangDAO;
import dal.mathangDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.mathang;

/**
 *
 * @author PC
 */
@WebServlet(name = "productdetail", urlPatterns = {"/productdetail"})
public class productdetail extends HttpServlet {

   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {
            mathangDAO DAOmathang =  new mathangDAO();
            String action = request.getParameter("action");
            int idkh = Integer.parseInt( session.getAttribute("idkh").toString());
            int id = Integer.parseInt(request.getParameter("id"));
            if(action.equals("buy")){
                 mathang exist = DAOmathang.getproduct(id);
            if(exist!=null){
                request.setAttribute("makh", idkh);
                request.setAttribute("mathang", exist);
               request.getRequestDispatcher("productdetail.jsp").forward(request, response);
            }else{
               out.print("ko co mat hang nay");
            }
            }
             ;
            
        } catch (Exception e) {
           response.sendRedirect("home");
        }
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        int idp=0;
        try {
            idp =Integer.parseInt( request.getParameter("mamh"));
        } catch (Exception e) {
            out.print("Không tồn tại");
        }
        try {
            String sub = request.getParameter("sub");
           
           
             int idkh=0;
            idkh =Integer.parseInt( request.getParameter("idkh"));
            
            if(idkh==0){
                 response.sendRedirect("login");
             }
            if(request.getParameter("number")==null||request.getParameter("number")==""){
                request.setAttribute("tb", "Nhập số lượng >0");
            }
            
            int sl =Integer.parseInt( request.getParameter("number"));
            if(sl==0){
                out.print("vui lòng nhập số lượng");
            }
            if(sub.equals("Thêm vào giỏ")){
             DonHangDAO  dh = new DonHangDAO();
            int a = 0;
            a=dh.madhdatenull(idkh);
            if(a==0){
 
                boolean ins=dh.taodonhang(idkh);
                a=dh.madhdatenull(idkh);
                 boolean tctmh = dh.taochitietdonhang(a, idp, sl);
                 
            if(ins==true&&tctmh==true){
                boolean tf = dh.updategiahaibang();
                
                if(tf==true){
                    session.setAttribute("role", 3);
                    session.setAttribute("mess", "Đã thêm vào giỏ");
                    response.sendRedirect("home");
                }
            }
            }else if(a!=0&&idp==dh.mamhdatenull(idkh)){
                
                boolean check =dh.capNhatSoLuongTon(idp, sl);
                if(check==true){
                    session.setAttribute("role", 3);
                    session.setAttribute("mess", "Đã thêm vào giỏ");
                    response.sendRedirect("home");
                }
                
            }else{
               
                a=dh.madhdatenull(idkh);
                 boolean tctmh = dh.taochitietdonhang(a, idp, sl);
                 
            if(tctmh==true){
                boolean tf = dh.updategiahaibang();
                
                if(tf==true){
                    session.setAttribute("role", 3);
                     session.setAttribute("mess", "Đã thêm vào giỏ");
                     response.sendRedirect("home");
                }
            }
            }   
            }else if(sub.equals("Mua ngay"))   {
            DonHangDAO  dh = new DonHangDAO();
            boolean ins=dh.taodonhangDate(idkh);
            int a = dh.mamhnvnull(idkh);
            boolean tctmh = dh.taochitietdonhang(a, idp, sl);
            
            if(ins==true&&tctmh==true){
                boolean tf = dh.updategiahaibang();
                if(tf==true){
                    
                    session.setAttribute("tbmua",dh.totalprice(idkh)+" VND");
                    session.setAttribute("mess", "Đã mua sản phẩm với giá");
                    response.sendRedirect("home");
                }
            } 
            }
            
            
                    
        } catch (Exception e) {
            session.setAttribute("tb", "vui lòng nhập số lượng");
           response.sendRedirect("productdetail?action=buy&id=" +idp);
        }
        
    }


}
