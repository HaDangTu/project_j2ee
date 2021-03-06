<%@page import="MotelManagement.dto.ApplicationUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/site.css">
        <title>Hóa đơn điện, nước</title>
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
                            <a class="nav-link active" href="./Home">Home</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle font-weight-bold active" data-toggle="dropdown">
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
                                <a href="./ChangePassword" class="dropdown-item">Đổi mật khẩu</a>

                                <a href="./Logout" class="dropdown-item">Logout</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <h2 class="text-center mt-3">Hóa đơn điện, nước</h2>
            <hr>
            <h5 class="text-center font-weight-bold mb-3 mt-3">
                ${room.getRoomName()}
            </h5>

            <div class="card-columns">
                <c:set var="index" value="1"/>
                <c:forEach var="powerInvoice" items="${room.getInvoices()}">
                    <div class="card mt-3 p-3">
                        <h6 class="text-center font-weight-bold">Tháng ${powerInvoice.getMonth()}</h6>

                        <p class="font-weight-bold mb-0 mt-3 font-weight-bold text-warning">Điện</p>

                        <div class="d-flex justify-content-between">
                            <p class="mb-0 font-weight-bold">Chỉ số cũ</p>
                            <p class="mb-0">
                                <fmt:formatNumber type="number" value="${powerInvoice.getOldElectricityIndex()}"/>
                            </p>
                            <p class="mb-0 font-weight-bold">Chỉ số mới</p>
                            <p class="mb-0">
                                <fmt:formatNumber type="number" value="${powerInvoice.getNewElectricityIndex()}"/>
                            </p>
                        </div>

                        <div class="row">
                            <p class="mb-0 col text-right font-weight-bold">Thành tiền</p>
                            <p class="mb-0 col text-right text-warning">
                                <fmt:formatNumber type="number" value="${powerInvoice.getElectricityMoney()}"/> VND
                            </p>
                        </div>

                        <p class="font-weight-bold mb-0 mt-3 text-primary">Nước</p>
                        <div class="d-flex justify-content-between">
                            <p class="mb-0 font-weight-bold">Chỉ số cũ</p>
                            <p class="mb-0">
                                <fmt:formatNumber type="number" value="${powerInvoice.getOldWaterIndex()}"/>
                            </p>
                            <p class="mb-0 font-weight-bold">Chỉ số mới</p>
                            <p class="mb-0">
                                <fmt:formatNumber type="number" value="${powerInvoice.getNewWaterIndex()}"/>
                            </p>
                        </div>

                        <div class="row">
                            <p class="mb-0 col text-right font-weight-bold">Thành tiền</p>
                            <p class="mb-0 col text-right text-primary">
                                <fmt:formatNumber type="number" value="${powerInvoice.getWaterMoney()}"/> VND
                            </p>
                        </div>

                        <div class="row mt-3">
                            <p class="mb-0 col text-right font-weight-bold text-danger">Tổng tiền</p>
                            <p class="mb-0 col text-right font-weight-bold text-danger">
                                <fmt:formatNumber type="number" value="${powerInvoice.getSumMoney()}"/> VND
                            </p>
                        </div>
                    </div>
                    <c:set var="index" value="${index + 1}"/>
                </c:forEach>
            </div>

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
