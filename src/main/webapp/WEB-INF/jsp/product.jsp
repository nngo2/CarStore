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
    <title>Luxury Cars</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/product.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/product_detail.js'/>"></script>
</head>
<body>
<section>
    <c:import url="header.jsp"></c:import>
</section>
<div id="main" class="main">
    <div id="popup"></div>
    <div id="spinner"><img src="<c:url value='/image/spinner.gif'/>"></div>
    <div id="product-container">
    </div>
    <div>
        <input id="back" class="paging-button" type="button" value=" < ">
        <input id="next" class="paging-button" type="button" value=" > ">
    </div>
</div>
</body>
</html>
