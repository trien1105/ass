<%-- 
    Document   : editEmployee
    Created on : Mar 14, 2025, 4:51:49 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit Employee</h1>
        <form action="nhanvien" method="post">
            
            <input type="hidden" name="action" value="edit"> 
            
            <label>Name</label>
            <input type="text" value="${existEmployee.hoTen}" name="nName"> <br/>
            <label>Role</label>
            <input type="text" value="${existEmployee.chucVu}" name="nRole"> <br/>
            <label>Email</label>
            <input type="text" value="${existEmployee.email}" name="nEmail"> <br/>
            <label>Phone Number</label>
            <input type="text" value="${existEmployee.soDienThoai}" name="nPNumber"> <br/>
            <label>Starting Date</label>
            <input type="date" value="${existEmployee.ngayVaoLam}" name="nSDate"> <br/>
            <label>ID Admin</label>
            <input type="text" value="${existEmployee.maAdmin}" name="nIDAdmin"> <br/>
            
            <input type="submit" name="Save">
            <input type="button" value="Cancel" onclick="window.history.back();">
            <input type="hidden" value="${existEmployee.maNV}" name="nIDEmp">
        </form>
    </body>
</html>
