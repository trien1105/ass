/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.nhanvienDAO;
import dto.nhanvienDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.nhanvien;

/**
 *
 * @author Dell
 */
@WebServlet(name = "nhanvienController", urlPatterns = {"/nhanvien"})
public class nhanvienController extends HttpServlet {

    nhanvienDAO nvdao = new nhanvienDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Lấy session hiện tại (không tạo mới)
        HttpSession session = req.getSession(false);
        Integer role = null;

        // Kiểm tra nếu session tồn tại và có thuộc tính "role"
        if (session != null) {
            Object roleObj = session.getAttribute("role");
            if (roleObj instanceof Integer) {
                role = (Integer) roleObj;
            }
        }

        // Kiểm tra nếu role chưa được gán hoặc không phải admin (role != 1)
        if (role == null || role != 1) {
            resp.sendRedirect("login.jsp"); // Chuyển hướng về trang login nếu không có quyền
            return;
        }
        try {
            String action = req.getParameter("action");

            if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));

                nhanvien existEmployee = nvdao.getEmployee(id);

                if (existEmployee != null) {
                    req.setAttribute("existEmployee", existEmployee);
                    req.getRequestDispatcher("editEmployee.jsp").forward(req, resp);
                }

            }

            if ("create".equals(action)) {
                req.getRequestDispatcher("createnhanvien.jsp").forward(req, resp);

            }

            if ("phancong".equals(action)) {
                List<nhanvienDTO> phancong = nvdao.getDanhSachPhanCong();
                req.setAttribute("danhsachphancong", phancong);
                req.getRequestDispatcher("phancong.jsp").forward(req, resp);
                return;
            }

            if ("taocalam".equals(action)) {
                List<nhanvien> nv = nvdao.getAllEmployees();
                req.setAttribute("listnv", nv);
                req.getRequestDispatcher("taocalam.jsp").forward(req, resp);
                return; //
            }

            if ("Return".equals(action)) {
                List<nhanvien> list = nvdao.getAllEmployees();
                req.setAttribute("list", list);
                req.getRequestDispatcher("employeemanagement.jsp").forward(req, resp);
                return;
            }
            //Trở lại trang phancong,jsp
            if ("returnphancong".equals(action)) {
                List<nhanvienDTO> phancong = nvdao.getDanhSachPhanCong();
                req.setAttribute("danhsachphancong", phancong);
                req.getRequestDispatcher("phancong.jsp").forward(req, resp);
                return; //
            }
            
            //Trở lại trang home của admin
            if ("ReturnAdmin".equals(action)) {
                req.getRequestDispatcher("home.jsp").forward(req, resp);
            }

            List<nhanvien> list = nvdao.getAllEmployees();
            req.setAttribute("list", list);
            req.getRequestDispatcher("employeemanagement.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console để debug
            req.setAttribute("errorMes", "Error: " + e.getMessage());
            req.getRequestDispatcher("employeemanagement.jsp").forward(req, resp);
            req.getRequestDispatcher("phancong.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Lấy session hiện tại (không tạo mới)
        HttpSession session = req.getSession(false);
        Integer arole = null;

        // Kiểm tra nếu session tồn tại và có thuộc tính "role"
        if (session != null) {
            Object roleObj = session.getAttribute("role");
            if (roleObj instanceof Integer) {
                arole = (Integer) roleObj;
            }
        }

        // Kiểm tra nếu role chưa được gán hoặc không phải admin (role != 1)
        if (arole == null || arole != 1) {
            resp.sendRedirect("login"); // Chuyển hướng về trang login nếu không có quyền
            return;
        }

        try {
            String action = req.getParameter("action");

            if ("edit".equals(action)) {
                int idemp = Integer.parseInt(req.getParameter("nIDEmp"));
                String name = req.getParameter("nName");
                String role = req.getParameter("nRole");
                String email = req.getParameter("nEmail");
                String pnumber = req.getParameter("nPNumber");

                // Lấy dữ liệu ngày từ JSP (chuỗi "YYYY-MM-DD")
                String ngayVaoLam = req.getParameter("nSDate");

                // Chuyển đổi chuỗi thành kiểu Date trong Java
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date sdate = sdf.parse(ngayVaoLam);

                int idAdmin = Integer.parseInt(req.getParameter("nIDAdmin"));

                nhanvien editEmployee = new nhanvien(idemp, name, role, email, pnumber, sdate, idAdmin);
                if (nvdao.upDateEmployee(editEmployee) == true) {
//                    List<nhanvien> employees = nvdao.getAllEmployees();
//                    req.setAttribute("employees", employees);
                    req.setAttribute("editMessage", "Edit Successfully!");
                    List<nhanvien> list = nvdao.getAllEmployees();
                    req.setAttribute("list", list);
                    req.getRequestDispatcher("employeemanagement.jsp").forward(req, resp);
                } else {
                    req.setAttribute("editMessage", "Edit Fail!");
                }

            }
            //Add nhân viên
            if ("add".equals(action)) {
                String tennv = req.getParameter("nName");
                String chucvu = req.getParameter("nRole");
                String email = req.getParameter("nEmail");
                String sodienthoai = req.getParameter("nPNumber");
                // Lấy dữ liệu ngày từ JSP (chuỗi "YYYY-MM-DD")
                String ngayVaoLam = req.getParameter("nSDate");

                // Chuyển đổi chuỗi thành kiểu Date trong Java
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date sdate = sdf.parse(ngayVaoLam);
                Date today = new Date();

                // Kiểm tra ngày nhập phải lớn hơn ngày hiện tại
                if (!sdate.after(today)) {
                    req.setAttribute("addnv", "Ngày làm phải lớn hơn ngày hiện tại!");
                    req.getRequestDispatcher("createnhanvien.jsp").forward(req, resp);
                    return; // Dừng xử lý nếu ngày không hợp lệ
                }

                int idAdmin = Integer.parseInt(req.getParameter("nIDAdmin"));

                if (nvdao.addEmployee(tennv, chucvu, email, sodienthoai, sdate, idAdmin) == true) {
                    req.setAttribute("editMessage", "Add Employee Successfully!");
                    List<nhanvien> list = nvdao.getAllEmployees();
                    req.setAttribute("list", list);
                    req.getRequestDispatcher("employeemanagement.jsp").forward(req, resp);
                }

            }
            //Tạo ca làm
            if ("taocalam".equals(action)) {
                try {
                    int manv = Integer.parseInt(req.getParameter("id")); // Kiểm tra nếu ID là số

                    int calam = Integer.parseInt(req.getParameter("calam"));
                    String ngaylam = req.getParameter("ngaylam");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date sdate = sdf.parse(ngaylam);
                    Date today = new Date();

                    // Kiểm tra ngày nhập phải lớn hơn ngày hiện tại
                    if (!sdate.after(today)) {
                        req.setAttribute("taocalamMessage", "Ngày làm phải lớn hơn ngày hiện tại!");
                        List<nhanvien> dscl = nvdao.getAllEmployees(); //
                        req.setAttribute("listnv", dscl);   //
                        req.getRequestDispatcher("taocalam.jsp").forward(req, resp);
                        return; // Dừng xử lý nếu ngày không hợp lệ
                    }

                    boolean isDuplicate = nvdao.kiemTraTrungCaLam(calam, sdate);
                    if (isDuplicate) {
                        req.setAttribute("taocalamMessage", "Đã có nhân viên đã đăng ký ca làm này vào ngày này!");
                        List<nhanvien> dscl = nvdao.getAllEmployees(); //
                        req.setAttribute("listnv", dscl);   //
                        req.getRequestDispatcher("taocalam.jsp").forward(req, resp);
                        return;
                    }

                    if (nvdao.addCaLamNgayLam(manv, calam, sdate)) {
                        req.setAttribute("taocalamMessage", "Create Successfully!");
                        List<nhanvienDTO> dscl = nvdao.getDanhSachPhanCong();
                        req.setAttribute("danhsachphancong", dscl);
                        req.getRequestDispatcher("phancong.jsp").forward(req, resp);
                    }
                } catch (NumberFormatException e) {
                    req.setAttribute("taocalamMessage", "ID nhân viên không hợp lệ! Vui lòng nhập số.");
                    req.getRequestDispatcher("taocalam.jsp").forward(req, resp);
                } catch (ParseException e) {
                    req.setAttribute("taocalamMessage", "Ngày làm không hợp lệ!");
                    req.getRequestDispatcher("taocalam.jsp").forward(req, resp);
                }
            }

        } catch (Exception e) {
            String error = e.getMessage();
            req.setAttribute("errorEditMes", error);
        }

    }

}
