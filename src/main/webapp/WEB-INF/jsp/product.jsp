<%--
  Created by IntelliJ IDEA.
  User: nngo2
  Date: 1/31/2018
  Time: 1:48 PM
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
<div class="main"></div>
    <div id="product-container">
        <c:forEach var="p" items="${products}">
            <form method="post" action="<c:url value='/additem'/>">
                <div class="product">
                    <img src="<c:url value='/image/${p.image}'/>" alt="product image"/>
                    <p class="title">${p.name}</p>
                    <p>${p.description}</p>
                    <p class="price"><fmt:formatNumber value="${p.unitPrice}" type="currency" currencySymbol="$"/></p>
                    <p>
                        <input type="hidden" name="productid" value="${p.id}">
                        <input class="button" type="submit" value=" Add To Cart ">
                    </p>
                </div>
            </form>
            <div class="clear"></div>
        </c:forEach>
    </div>
</body>
</html>
