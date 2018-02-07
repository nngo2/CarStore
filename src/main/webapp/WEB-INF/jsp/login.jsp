<%--
  Created by IntelliJ IDEA.
  User: nngo2
  Date: 1/30/2018
  Time: 12:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>" type="text/css"/>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/login.js'/>"></script>
</head>
<body>
    <section>
        <c:import url="header.jsp"></c:import>
    </section>
    <section id="login" class="main">
        <form id="loginForm" method="post" action="<c:url value='/login'/>">
            <p class="${css.loginErrorMessageClass}">${css.loginErrorMessage}</p>
            <fieldset>
                <div>
                    <label>User Name: <input name="username" type="text" placeholder="username" required value="${usermodel.username}"></label>
                </div>
                <div>
                    <label>Password: <input name="password" type="password" placeholder="password" required></label>
                </div>
                <div>
                    <input type="submit" value=" Login ">
                </div>
                <div>
                    <span>Remember me  <input type="checkbox" name="rememberme" ${usermodel.rememberme}></span>
                </div>
            </fieldset>
        </form>
    </section>
</body>
</html>
