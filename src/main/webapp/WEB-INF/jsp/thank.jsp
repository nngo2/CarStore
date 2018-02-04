<%--
  Created by IntelliJ IDEA.
  User: nngo2
  Date: 1/31/2018
  Time: 8:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Shopping Center</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>"/>
</head>
<body>
<section>
    <c:import url="header.jsp"></c:import>
</section>
<section class="main">
    <div id="cart">
        <p class="center-50">Thank you for choosing our products. Below is your confirmation.</p>
        <table id="cart-container" class="center-50">
            <tr>
                <th>Product</th>
                <th>Price</th>
            </tr>
            <c:forEach var="i" items="${confirmedcart.items}">
                <tr class="item">
                    <td>${i.productName}</td>
                    <td class="price"><fmt:formatNumber value="${i.price}" type="currency" currencySymbol="$"/></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="2" class="price">Total price: <fmt:formatNumber value="${confirmedcart.totalPrice}" type="currency" currencySymbol="$"/></td>
            </tr>
        </table>
    </div>

</section>
</body>
</html>
