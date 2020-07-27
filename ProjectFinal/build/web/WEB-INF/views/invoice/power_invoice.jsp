<%@page import="MotelManagement.dto.ApplicationUser"%>
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
        <title>Hóa đơn điện nước</title>
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
            <h2 class="text-center mt-3">Hóa đơn điện nước</h2>
            <hr>
            <div class="d-flex justify-content-center">
                <form class="col-5" action="./PayPowerInvoice?id=${id}&month=${month}&year=${year}" method="POST">
                    <input type="text" name="room-id" value="${invoice.getRoomId()}" hidden="hidden">
                    <h6 class="font-weight-bold text-center">${invoice.getRoomName()}</h6>
                    <div class="d-flex justify-content-between mt-2">
                        <p class="font-weight-bold">Từ ngày</p>
                        <p>
                            <fmt:formatDate value="${invoice.getFromDate()}"
                                            type="DATE"
                                            dateStyle="SHORT"
                                            pattern="dd/MM/yyyy"/>
                        </p>
                        <p class="font-weight-bold">Đến ngày</p>
                        <p>
                            <fmt:formatDate value="${invoice.getToDate()}"
                                            type="DATE"
                                            dateStyle="SHORT"
                                            pattern="dd/MM/yyyy"/>
                        </p>
                        <input type="date" hidden="hidden" name="date"
                               value="<fmt:formatDate value="${invoice.getFromDate()}"
                                               type="DATE"
                                               dateStyle="SHORT"
                                               pattern="yyyy-MM-dd"/>">
                    </div>

                    <div class="row mt-2">
                        <label for="collection-date" class="font-weight-bold col-sm-5">Ngày thu tiền</label>
                        <input type="date" name="collection-date" class="form-control col-sm" 
                               value="<fmt:formatDate value="${now}"
                                               type="DATE"
                                               dateStyle="SHORT"
                                               pattern="yyyy-MM-dd"/>">
                    </div>
                    <div class="row mt-2">

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
                                                          value="${invoice.getElectricMoney()}"/> VND
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
                    </div>
                    <div class="row mt-2">
                        <p class="font-weight-bold col-sm-5 mb-0">Tổng tiền</label>
                        <p class="col-sm p-0 mb-0"> 
                            <fmt:formatNumber type="number" value="${invoice.getDebt()}" /> VND
                        </p>
                        <input id="price" value="${invoice.getDebt()}" type="number" hidden="hidden">
                    </div>

                    <div class="row mt-2">
                        <p class="font-weight-bold col-sm-5 mb-0">Nợ trước</label>
                        <p class="col-sm p-0">
                            <fmt:formatNumber type="number" value="${invoice.getPreDebt()}"/> VND
                        </p>
                        <input id="pre-debt" value="${invoice.getPreDebt()}" type="number" hidden="hidden">
                    </div>

                    <div class="row mt-2">
                        <label for="guest-money" class="font-weight-bold col-sm-5 mb-0">Tiền khách đưa</label>
                        <div class="form-group col-sm p-0">
                            <input type="number" name="guest-money" class="form-control"
                                   onkeyup="onKeyUp(event)">
                            <c:if test="${!errProceedMsg.equals('')}">
                                <p class="mb-0 text-danger font-weight-bold">${errProceedMsg}</p>
                            </c:if>
                        </div>
                    </div>

                    <div class="row mt-2">
                        <p class="font-weight-bold col-sm-5 mb-0">Tiền thừa</label>
                        <p id="excess-cash-text" class="text-success col-sm p-0 mb-0"></p>
                        <input name="excess-cash" id="excess-cash" type="number" hidden="hidden">
                    </div>

                    <div class="row mt-2">
                        <p class="font-weight-bold col-sm-5 mb-0">Còn nợ</label>
                        <p id="debt-text" class="text-danger col-sm p-0 mb-0"></p>
                        <input name="debt" id="debt" type="number" hidden="hidden">
                    </div>

                    <div class="row justify-content-center mt-4">
                        <input type="submit" class="btn btn-outline-primary" value="Xuất hóa đơn">
                    </div>
                </form>
            </div>
        </div>
    </body>
    <script src="${context}/resources/js/jquery-3.5.1.js"></script>
    <script src="${context}/resources/js/bootstrap.bundle.js"></script>
    <script src="${context}/resources/js/bootstrap.min.js"></script>
    <script src="${context}/resources/js/calculating_money.js"></script>
</html>
