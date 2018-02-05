<%--
  Created by IntelliJ IDEA.
  User: nngo2
  Date: 1/31/2018
  Time: 8:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Billing</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>"/>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/checkout.js'/>"></script>
</head>
<body>
<section>
    <c:import url="header.jsp"></c:import>
</section>
<section class="main">
    <section id="billing">
        <form class="center-50" method="post" action="<c:url value='/checkout'/>">
            <fieldset>
                <legend>Billing Address</legend>
                <div>
                    <label>Name: <input name="billName" type="text" required/></label>
                </div>
                <div>
                    <label>Address Line1: <input name="billAddress1" type="text" required/></label>
                </div>
                <div>
                    <label>Address Line2: <input name="billAddress2" type="text"></label>
                </div>
                <div>
                    <label class="column-33">City: <input name="billCity" type="text" required></label>
                    <label class="column-33">State: <select name="billState" required>
                        <c:forEach var="s" items="${states}">
                            <option value="${s.value}" selected>${s.name}</option>
                        </c:forEach>
                    </select></label>
                    <label class="column-33">Zip: <input name="billZip" type="text" required pattern="\d{5}" placeholder="12345"></label>
                </div>
            </fieldset>
            <fieldset>
                <legend>Shipping Address</legend>
                <div>
                    <label>Name: <input name="shipName" type="text" required/></label>
                </div>
                <div>
                    <label>Address Line1: <input name="shipAddress1" type="text" required/></label>
                </div>
                <div>
                    <label>Address Line2: <input name="shipAddress2" type="text"></label>
                </div>
                <div>
                    <label class="column-33">City: <input name="shipCity" type="text" required></label>
                    <label class="column-33">State: <select name="shipState" required>
                        <c:forEach var="s" items="${states}">
                            <option value="${s.value}" selected>${s.name}</option>
                        </c:forEach>
                    </select></label>
                    <label class="column-33">Zip: <input name="shipZip" type="text" required pattern="\d{5}" placeholder="12345"></label>
                </div>
            </fieldset>
            <fieldset>
                <legend>Payment Information</legend>
                <div>
                    <label>Card Type: <select name="cardtype" required>
                        <option value="visa" selected>Visa</option>
                        <option value="master">Master</option>
                        <option value="discover">Discover</option>
                    </select></label>
                </div>
                <div>
                    <label>Name on Card <input name="cardowner" type="text" required/></label>
                </div>
                <div>
                    <label>Card Number: <input name="cardno" type="text" required pattern="\d{16}"/></label>
                </div>
                <div>
                    <label>Expired Date: <input name="cardexpire" type="text" pattern="\d{4}" placeholder="mmyy"/></label>
                </div>
                <div>
                    <label>CCV: <input name="cardccv" type="text" required pattern="\d{3})" placeholder="123"/></label>
                </div>
            </fieldset>
            <fieldset class="no-border">
                <input class="button" type="submit" value=" Confirm ">
            </fieldset>
        </form>
    </section>
</section>
</body>
</html>
