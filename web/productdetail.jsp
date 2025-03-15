<%-- 
    Document   : productdetail
    Created on : Mar 15, 2025, 9:01:52 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/productdetail.css">
        <title>JSP Page</title>
    </head>
    
</head>
<body>
    
    ${idkh}
    <form action="productdetail" method="post">
        <div class="cokk">
        <div class="product-image">
            <img src="${mathang.img}" alt="Hình ảnh sản phẩm">
        </div>
        
        <div class="product-info">
            <div class="product-title">${mathang.tenmh}</div>
            <div class="product-price">${mathang.gia} VND</div>
            <div class="product-details">Số lượng tồn: ${mathang.soluongton}</div>
            <div class="product-details">Hạn sử dụng: ${mathang.hsd}</div>
            <div class="product-details">Mô tả: ${mathang.mota}</div>
            <div class="buttons">
                <input type="number" name="number" value="" max="${mathang.soluongton}" step="1"> 
                <input type="submit" class="btn btn-cart" value="Thêm vào giỏ" name="sub">
                <input type="submit" class="btn btn-buy" value="Mua ngay" name="sub">
            </div>
              <input type="hidden" value="${mathang.mamh}" name="mamh">   
               <input type="hidden" value="${idkh}" name="idkh">   
        </div>
            
           
    </div>
        
    </form>
    
            <div style="display: flex;justify-content: center">  <a class="btn btn-cart" href="home?action=payproducts">trở lại</a>  </div>
            <h1 style="text-align: center;color: red ;font-weight: bold">${tb}</h1>
             <%
            session.removeAttribute("tb");
            %>
</body>
</html>
