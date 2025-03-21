/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DonHangDAO;
import dal.mathangDAO;
import dal.nhanvienDAO;
import dal.userDAO;
import dto.Donhangdto;
import dto.mathangdhctdto;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.mathang;
import models.nhanvien;
import user.account;

/**
 * @author PC

 * 
=======
 *

 */
@WebServlet(name = "home", urlPatterns = {"/home"})
public class home extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String action = null;
        HttpSession session = request.getSession();
        int role = 0;
        account acc = null;
        try {
            action = request.getParameter("action");
            acc = (account) session.getAttribute("acc");
            Integer roleObj = (Integer) session.getAttribute("role"); // Lấy từ session
            role = (roleObj != null) ? roleObj : 0;
        } catch (Exception e) {
        }
        if (action == null || action.equals("")) {
            String mess = request.getParameter("mess");
            if (role == 0) {
                response.sendRedirect("login");
                //trang chính admin
            } else if (role == 1) {
                DonHangDAO hd = new DonHangDAO();
                List<Donhangdto> money = new ArrayList<>();
                String thongbao = "";
                String thongbao2 = "";
                money = hd.thunhap1();
                LocalDate today = LocalDate.now();          // Lấy ngày hôm nay
                LocalDate yesterday = today.minusDays(1);   // Lấy ngày hôm qua
                if (money.size() == 0) {
                    thongbao = "Hôm qua và hôm nay đã không bán hàng";
                } else if (money.get(0).getNgaymua().toString().equals(today.toString()) && money.size() == 1) {
                    thongbao = "Hôm qua đã không bán hàng";
                    thongbao2 = "Thu nhập hôm nay là: " + money.get(0).getTongtien();
                } else if (money.get(0).getNgaymua().toString().equals(yesterday.toString()) && money.size() == 1) {
                    thongbao = "Hôm nay chưa bán hàng";
                    thongbao2 = "Thu nhập hôm qua là: " + money.get(0).getTongtien();
                } else {
                    double doanhthu = (money.get(0).getTongtien() - money.get(1).getTongtien()) / money.get(1).getTongtien();
                    if (doanhthu < 0) {
                        thongbao = "Thu nhập so với hôm qua giảm " + (-1) * doanhthu * 100 + "%";
                    }
                    if (doanhthu > 0) {
                        thongbao = "Thu nhập so với hôm qua tăng " + doanhthu * 100 + "%";
                    } else if (doanhthu == 0) {
                        thongbao = "Thu nhập hôm nay bằng hôm qua ";
                    }

                    thongbao2 = "Thu nhập hôm nay là: " + money.get(0).getTongtien() + " VND Thu nhập hôm qua là: " + money.get(1).getTongtien() + " VND";

                }

                mathangDAO mh = new mathangDAO();
                List<mathang> spbanchay = new ArrayList<>();
                spbanchay = mh.spbanchay();

                request.setAttribute("spbanchay", spbanchay);
                request.setAttribute("role", role);
                request.setAttribute("ma", acc.getManguoidung());
                request.setAttribute("thongbao", thongbao);
                request.setAttribute("thongbao2", thongbao2);
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("admin.jsp").forward(request, response);
                //trang nhan vien
            } else if (role == 2) {
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("nhanvien.jsp").forward(request, response);
                //trang nguoi dung
            } else if(role==3){
            mathangDAO mh = new mathangDAO();
            List<mathang> spbanchay= new ArrayList<>();
            spbanchay = mh.spbanchay();
            request.setAttribute("idkh", acc.getManguoidung());
            request.setAttribute("spbanchay", spbanchay);
            request.setAttribute("mess", mess);
            request.getRequestDispatcher("home.jsp").forward(request, response);

            }
            //tạo nhân viên chỗ này

        } else if ("quanlynhanvien".equals(action) && role == 1) {
            nhanvienDAO nv = new nhanvienDAO();
            List<nhanvien> list = new ArrayList<>();
            list = nv.getAllEmployees();
            request.setAttribute("list", list);
            request.getRequestDispatcher("employeemanagement.jsp").forward(request, response);
            //danh sách các mặt hàng

        } else if ("products".equals(action) && role == 1) {
            mathangDAO d = new mathangDAO();
            List<mathang> products = new ArrayList<>();
            try {
                products = d.getAllProducts();
            } catch (Exception ex) {

            }
            request.setAttribute("products", products);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } else if ("home".equals(action)) {
            response.sendRedirect("home");
            //xóa
        } else if ("delete".equals(action) && role == 1) {
            int idp = Integer.parseInt(request.getParameter("id"));
            out.print("xoa me may di");
            //edit
        } else if ("edit".equals(action) && role == 1) {
            int idp = Integer.parseInt(request.getParameter("id"));
            out.print("xoa me may di");
            //thong tin nguoi dung
        } else if ("information".equals(action)) {

            out.println(acc.getEmail());
            //muahang
        } else if ("payproducts".equals(action) && role == 3) {
            mathangDAO d = new mathangDAO();
            List<mathang> products = new ArrayList<>();
            try {
                products = d.getAllProducts();
            } catch (Exception ex) {

            }
            request.setAttribute("idkh", acc.getManguoidung());
            request.setAttribute("products", products);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else if ("payed".equals(action) && role == 3) {
            mathangDAO d = new mathangDAO();
            List<mathangdhctdto> products = new ArrayList<>();
            try {
                products = d.spdamua(acc.getManguoidung());
            } catch (Exception ex) {

            }
            request.setAttribute("idkh", acc.getManguoidung());
            request.setAttribute("payed", products);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else if ("order".equals(action) && role == 3) {
            mathangDAO d = new mathangDAO();
            DonHangDAO dh = new DonHangDAO();
            List<mathang> products = new ArrayList<>();
            try {
                products = d.hangtrongio(acc.getManguoidung());
            } catch (Exception ex) {

            }
            request.setAttribute("idkh", acc.getManguoidung());
            request.setAttribute("gia", "Số tiền cần thanh toán là :" + dh.totalpricegiohang(acc.getManguoidung()) + " VND");
            request.setAttribute("gio", products);
            request.getRequestDispatcher("home.jsp").forward(request, response);

        } else if ("pay".equals(action) && role == 3) {
            mathangDAO data = new mathangDAO();
            if (data.hangtrongio(acc.getManguoidung()).size() < 1) {
                session.setAttribute("mess", "Không có mặt hàng để thanh toán");
                response.sendRedirect("home");
            }

            DonHangDAO mh = new DonHangDAO();
            boolean check = mh.inserttoday(acc.getManguoidung());
            if (check == true) {
                session.setAttribute("mess", "đã thanh toán");
                response.sendRedirect("home");
            }
        } else if (role != 1) {
            session.setAttribute("warning", "Mày không phải admin? Cút!");
            response.sendRedirect("home");

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
