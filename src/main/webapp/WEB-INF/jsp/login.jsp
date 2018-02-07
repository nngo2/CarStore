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
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/login.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/user.js'/>"></script>
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
            <fieldset>
                New user: <a id="create-user-action">Click here to register an account</a>
            </fieldset>
        </form>
    </section>
    <div id="popup"></div>
</body>
</html>
