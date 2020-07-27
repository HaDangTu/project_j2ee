<%@page import="MotelManagement.dto.ApplicationUser"%>
<%@page import="MotelManagement.dto.GuestInvoice"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/site.css">
        <title>Hóa đơn</title>
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
                        <li class="nav-item">
                            <a class="nav-link active" href="./ListGuest">Khách trọ</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="./ListRoom">Phòng trọ</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="./ListRoomType">Loại phòng trọ</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active font-weight-bold" href="./Invoice">Hóa đơn</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="./UpdatePower">Điện nước</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="./Report">Báo cáo</a>
                        </li>
                    </ul>

                    <ul class="navbar-nav my-2 my-lg-0">
                        <li class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle active" data-toggle="dropdown">
                                Hello! <%= user.getUsername()%>
                            </a>

                            <div class="dropdown-menu dropdown-menu-right">
                                <a href="./ListAccount" class="dropdown-item">Manage account</a>
                                <a href="./ChangePassword" class="dropdown-item">Đổi mật khẩu</a>
                                <a href="./Logout" class="dropdown-item">Logout</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>        
        <div class="container">
            <h2 class="text-center mt-3">Hóa đơn</h2>
            <hr>
            <div class="row">
                <div class="col-sm-4">
                    <h5 class="text-center">Danh sách phòng</h5>
                    <table class="table table-bordered mb-0 mt-3">
                        <thead class="thead-dark text-center">
                            <tr class="d-flex">
                                <th class="col-sm-3">STT</th>
                                <th class="col-sm">Phòng</th>
                            </tr>
                        </thead>
                    </table>
                    <div class="scroll-table">
                        <form id="submit-room-form" method="GET">
                            <input type="text" name="room-id" id="room-id" hidden="hidden">
                            <table id="table-rooms" class="table table-striped table-hover"
                                   onclick="onRowClick(event)">
                                <tbody class="text-center">
                                    <c:set var="index" value="1"/>
                                    <c:forEach var="room" items="${rooms}">
                                        <tr class="d-flex">
                                            <td hidden="hidden">${room.getId()}</td>
                                            <td class="col-sm-3">${index}</td>
                                            <td class="col-sm">${room.getName()}</td>
                                        </tr>
                                        <c:set var="index" value="${index + 1}"/>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
                <div class="col-sm">
                    <h5 class="text-center">${guest.getRoomName()}</h5>

                    <div id ="accordion" class="mt-3">
                        <!--Card room-->
                        <div class="card">
                            <div class="card-header">
                                <a class="card-link dropdown-toggle" data-toggle="collapse" href="#room-invoice">Phòng</a>
                            </div>

                            <div id="room-invoice" class="collapse" data-parent="#accordion">
                                <div class="card-body scroll-card-body">
                                    <c:forEach items="${guest.getRoomInvoices()}" var="invoice">
                                        <div class="custom-card d-flex justify-content-between p-3 mt-2">
                                            <h6 class="mb-0">Tháng ${invoice.getMonth()}/${invoice.getYear()}</h6>
                                            <p class="mb-0 font-weight-bold text-danger">
                                                <fmt:formatNumber type="number" value="${invoice.getMoney()}"/> VND
                                            </p>
                                            <a class="mb-0 card-link" 
                                               href="./PayRoomInvoice?id=${guest.getId()}&month=${invoice.getMonth()}&year=${invoice.getYear()}">
                                                Thanh toán
                                            </a>
                                        </div>
                                    </c:forEach>
                                    <!--                                    <div class="d-flex justify-content-end mt-2">
                                                                            <a class="mb-0 card-link" 
                                                                               href="./PayAllRoomInvoice?id=\${guest.getId()}&ammount=\${guest.getRoomInvoices().size()}">
                                                                                Thanh toán toàn bộ
                                                                            </a>
                                                                        </div>-->
                                </div>
                            </div>
                        </div>

                        <!--Card power-->
                        <div class="card">
                            <div class="card-header">
                                <a class="card-link dropdown-toggle" data-toggle="collapse" 
                                   href="#power-invoice">Điện nước</a>
                            </div>

                            <div id="power-invoice" class="collapse" data-parent="#accordion">
                                <div class="card-body scroll-card-body">
                                    <c:forEach items="${guest.getPowerInvoices()}" var="invoice">
                                        <div class="custom-card p-3 mt-2">
                                            <h6>Tháng ${invoice.getMonth()}/${invoice.getYear()}</h6>
                                            <table class="table table-hover">
                                                <thead class="thead-dark text-center">
                                                    <tr>
                                                        <th></th>
                                                        <th>Chỉ số cũ</th>
                                                        <th>Chỉ số mới</th>
                                                        <th>Thành tiền</th>
                                                    </tr>
                                                </thead>
                                                <tbody class="text-center">
                                                    <tr>
                                                        <th class="text-warning">Điện</th>
                                                        <th>${invoice.getOldElectricityIndex()}</th>
                                                        <th>${invoice.getNewElectricityIndex()}</th>
                                                        <th class="text-danger">
                                                            <fmt:formatNumber type="number"
                                                                              value="${invoice.getElectricityMoney()}"/> VND
                                                        </th>
                                                    </tr>

                                                    <tr>
                                                        <th class="text-primary">Nước</th>
                                                        <th>${invoice.getOldWaterIndex()}</th>
                                                        <th>${invoice.getNewWaterIndex()}</th>
                                                        <th class="text-danger">
                                                            <fmt:formatNumber 
                                                                type="number"          
                                                                value="${invoice.getWaterMoney()}"/> VND
                                                        </th>
                                                    </tr>
                                                </tbody>
                                            </table>

                                            <div class="row justify-content-end">
                                                <p class="font-weight-bold text-danger text-right col-sm mb-0">Tổng tiền</p>
                                                <p class="font-weight-bold text-danger text-right col-sm-4 mb-0">
                                                    <fmt:formatNumber 
                                                        type="number"
                                                        value="${invoice.getSumMoney()}"/> VND
                                                </p>
                                            </div>
                                            <div class="d-flex justify-content-end m-3">
                                                <a class="card-link mb-0" 
                                                   href="./PayPowerInvoice?id=${guest.getId()}&month=${invoice.getMonth()}&year=${invoice.getYear()}">Thanh toán</a>
                                            </div>
                                        </div>

                                    </c:forEach>

                                    <!--                                    <div class="d-flex justify-content-end mt-2">
                                                                            <a class="mb-0 card-link" href="./PayAllPowerInvoice?id=\${guest.getId()}">Thanh toán toàn bộ</a>
                                                                        </div>-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="${context}/resources/js/jquery-3.5.1.js"></script>
    <script src="${context}/resources/js/bootstrap.bundle.js"></script>
    <script src="${context}/resources/js/bootstrap.min.js"></script>
    <script src="${context}/resources/js/get_room_invoices.js"></script>
</html>
