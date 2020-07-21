<%@page import="MotelManagement.dto.ApplicationUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/site.css">
        <title>Hóa đơn tiền phòng</title>
    </head>
    <body>
        <%
            ApplicationUser user = (ApplicationUser) session.getAttribute("user");
        %>
        <nav class="navbar navbar-expand-sm navbar-dark bg-primary">
            <div class="container">

                <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarcolapse">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarcolapse">
                    <a class="navbar-brand" href="./Home">Mariot emotel</a>

                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="./Home">Home</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle active" data-toggle="dropdown">
                                Hóa đơn
                            </a>
                            <div class="dropdown-menu dropdown-menu-left">
                                <a href="./GuestRoomInvoice" class="dropdown-item">Hóa đơn phòng</a>
                                <a href="./GuestPowerInvoice" class="dropdown-item">Hóa đơn điện nước</a>
                            </div>
                        </li>
                    </ul>

                    <ul class="navbar-nav my-2 my-lg-0">
                        <li class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle active" data-toggle="dropdown">
                                Hello! <%= user.getUsername()%>
                            </a>

                            <div class="dropdown-menu dropdown-menu-right">
                                <!--<a href="./ManageAccount" class="dropdown-item">Manage account</a>-->

                                <a href="./Logout" class="dropdown-item">Logout</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <h2 class="text-center">Hóa đơn tiền phòng</h2>
            <hr>
            <h5 class="text-center font-weight-bold mb-3 mt-3">
                ${room.getRoomName()}
            </h5>

            <c:forEach var="roomInvoice" items="${room.getInvoices()}">
                <div class="card ml-auto mr-auto p-3 mt-2">
                    <div class="d-flex justify-content-between">
                        <p class="font-weight-bold mb-0">Tháng ${roomInvoice.getMonth()}</p>
                        <p class="font-weight-bold text-danger mb-0">
                            <fmt:formatNumber type="number" value="${roomInvoice.getMoney()}"/> VND
                        </p>
                    </div>
                </div>
            </c:forEach>

            <div class="ml-auto mr-auto mt-3">
                <div class="d-flex justify-content-end">
                    <a href="./Home">Back to home</a>
                </div>
            </div>
        </div>
    </body>
    
    <script src="${context}/resources/js/jquery-3.5.1.js"></script>
    <script src="${context}/resources/js/bootstrap.bundle.js"></script>
    <script src="${context}/resources/js/bootstrap.min.js"></script>
</html>
