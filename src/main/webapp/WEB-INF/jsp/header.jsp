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
    <span>Luxury Cars Center</span>
    <a id="loginButton" href="<c:url value='/login'/>">Login</a>
    <a id="logoutButton" style="display : none" href="<c:url value='/logout'/>">Logout</a>
    <a id="cartButton" style="display : none" href="<c:url value='/cart'/>">Cart</a>
    <a id="catalogButton" href="<c:url value='/product'/>">Catalog</a>
</div>