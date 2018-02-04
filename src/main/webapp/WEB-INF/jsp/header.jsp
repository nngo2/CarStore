<%--
  Created by IntelliJ IDEA.
  User: nngo2
  Date: 1/31/2018
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar">
    <span>Luxury Cars Mate</span>
    <c:if test="${!empty sessionScope.user}">
        <a href="<c:url value='/logout'/>">Logout</a>
    </c:if>
    <c:if test="${!empty sessionScope.cart.items}">
        <a href="<c:url value='/cart'/>">Cart</a>
    </c:if>
    <a href="<c:url value='/product'/>">Catalog</a>
    <c:if test="${!empty sessionScope.welcome}">
       <span> | ${sessionScope.welcome}</span>
    </c:if>

</div>