<%-- 
    Document   : createnhanvien
    Created on : Mar 10, 2025, 2:21:37 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add New Employee</h1>
        <div>${addnv}</div>
        <form action="nhanvien" method="post" name="create">
            <input type="hidden" name="action" value="add"> 
            
            <label>Name</label>
            <input type="text" pattern="[a-zA-ZÀ-ỹ\s]+" name="nName" value="${param.nName}" required title="Tên không đúng định dạng"> <br/>
            <label>Role</label>
            <input type="text" name="nRole" pattern="[a-zA-ZÀ-ỹ\s]+" value="${param.nRole}" required> <br/>
            <label>Email</label>
            <input type="text" name="nEmail" pattern="[a-zA-Z0-9]+@gmail\.com" value="${param.nEmail}" required title="Email phải có dạng @gmail.com"> <br/>
            <label>Phone Number</label>
            <input type="text"  name="nPNumber" pattern="[0-9]+" value="${param.nPNumber}" required title="Số điện thoại không đúng định dạng"> <br/>
            <label>Starting Date</label>
            <input type="date"  name="nSDate" value="${param.nSDate}" required> <br/>
            <label>ID Admin</label>
            <input type="text" pattern="[0-9]+"  name="nIDAdmin" value="${param.nIDAdmin}" required title="ID không đúng định dạng"> <br/>
            
            <input type="submit" name="Save">
             <input type="button" value="Cancel" onclick="window.history.back();">
        </form>
        
        
        
    </body>
</html>
