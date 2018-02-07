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
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/product_detail.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/thank.js'/>"></script>
</head>
<body>
<section>
    <c:import url="header.jsp"></c:import>
</section>
<section class="main">
    <div id="popup"></div>
    <div id="cart">
        <p class="center-50">Thank you for choosing our products. Below is your confirmation.</p>
        <table id="cart-container" class="center-50">
            <tr>
                <th>Product</th>
                <th>Price</th>
            </tr>
            <c:forEach var="i" items="${confirmedcart.items}">
                <tr class="item" id="item_${i.productId}">
                    <td>${i.productName} <br>
                        <button id="view_${i.productId}" class="buttonDetails" data-productid = "${i.productId}"> View Details</button>
                    </td>
                    <td class="price">
                        <fmt:formatNumber value="${i.price}" type="currency" currencySymbol="$"/>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="2">
                    Total Price: <span id="price">${confirmedcart.totalPrice}</span>
                </td>
            </tr>
        </table>
    </div>

</section>
</body>
</html>
