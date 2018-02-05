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
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/product.js'/>"></script>
</head>
<body>
<section>
    <c:import url="header.jsp"></c:import>
</section>
<div class="main"></div>
    <div id="product-container">
    </div>
    <div>
        <input id="back" class="paging-button" type="button" value=" < ">
        <input id="next" class="paging-button" type="button" value=" > ">
    </div>
</body>
</html>
