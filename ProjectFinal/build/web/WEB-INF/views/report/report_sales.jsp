<%@page import="MotelManagement.dto.ApplicationUser"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/site.css">
        <title>Báo cáo</title>
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
                            <a class="nav-link active" href="./Invoice">Hóa đơn</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="./UpdatePower">Điện nước</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active font-weight-bold" href="./Report">Báo cáo</a>
                        </li>
                    </ul>

                    <ul class="navbar-nav my-2 my-lg-0">
                        <li class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle active" data-toggle="dropdown">
                                Hello! <%= user.getUsername()%>
                            </a>

                            <div class="dropdown-menu dropdown-menu-right">
                                <a href="./ListAccount" class="dropdown-item">Manage account</a>

                                <a href="./Logout" class="dropdown-item">Logout</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <h2 class="text-center">Báo cáo</h2>
            <hr>
            <div id="accordion">
                <div class="card">
                    <div class="card-header">
                        <a class="card-link dropdown-toggle" href="#month-report" data-toggle="collapse">
                            Tháng
                        </a>
                    </div>

                    <div id="month-report" class="collapse" data-parent="#accordion">
                        <div class="card-body pt-2 pb-2">
                            <form class="form-inline" action="./MonthReport" method="POST">
                                <label for="month" class="font-weight-bold mr-4">Tháng</label>
                                <select name="month" class="custom-select">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                </select>
                                <label for="year" class="font-weight-bold mr-4 ml-5">Năm</label>
                                <select name="year" class="custom-select">
                                    <c:forEach items="${years}" var="year">
                                        <option value="${year}">${year}</option>
                                    </c:forEach>
                                </select>

                                <input type="submit" value="Submit" class="btn btn-outline-primary ml-5">
                            </form>
                        </div>
                    </div>
                </div>

                <div class="card">
                    <div class="card-header">
                        <a href="#quarter-report" class="card-link dropdown-toggle" data-toggle="collapse">
                            Qúy
                        </a>

                    </div>

                    <div id="quarter-report" class="collapse" data-parent="#accordion">
                        <div class="card-body pt-2 pb-2">
                            <form action="./QuarterReport" method="POST">
                                <div class="custom-control custom-checkbox custom-control-inline">
                                    <input type="checkbox" class="custom-control-input" name="quarter" id="quarter-1" value="quarter-1">
                                    <label class="custom-control-label font-weight-bold" for="quarter-1">Qúy I</label>
                                </div>
                                <div class="custom-control custom-checkbox custom-control-inline">
                                    <input type="checkbox" class="custom-control-input" name="quarter" id="quarter-2" value="quarter-2">
                                    <label class="custom-control-label font-weight-bold" for="quarter-2">Qúy II</label>
                                </div>
                                <div class="custom-control custom-checkbox custom-control-inline">
                                    <input type="checkbox" class="custom-control-input" name="quarter" id="quarter-3" value="quarter-3">
                                    <label class="custom-control-label font-weight-bold" for="quarter-3">Qúy III</label>
                                </div>
                                <div class="custom-control custom-checkbox custom-control-inline">
                                    <input type="checkbox" class="custom-control-input" name="quarter" id="quarter-4" value="quarter-4">
                                    <label class="custom-control-label font-weight-bold" for="quarter-4">Qúy VI</label>
                                </div>
                                <div class="form-inline mt-3">
                                    <label for="year" class="font-weight-bold mr-3">Năm</label>
                                    <select name="year" class="custom-select">
                                        <c:forEach items="${years}" var="year">
                                            <option value="${year}">${year}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-inline mt-3">
                                    <input type="submit" value="Submit" class="btn btn-outline-primary">
                                </div>
                            </form>
                        </div>

                    </div>
                </div>

                <div class="card">
                    <div class="card-header">
                        <a class="card-link dropdown-toggle" href="#advance-report" data-toggle="collapse">
                            Nâng cao
                        </a>
                    </div>

                    <div id="advance-report" class="collapse" data-parent="#accordion">
                        <div class="card-body p-2">
                            <form class="form-inline" action="./AdvanceReport" method="POST">
                                <label for="from-date" class="font-weight-bold mr-4">Từ ngày</label>
                                <input type="date" name="from-date" class="form-control">
                                <label for="to-date" class="font-weight-bold mr-4 ml-5">Đến ngày</label>
                                <input name="to-date" class="form-control" type="date">

                                <input type="submit" value="Submit" class="btn btn-outline-primary ml-5">
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="mt-4">
                <table class="table table-striped table-bordered mb-0">
                    <thead class="thead-dark text-center">
                        <tr class="d-flex">
                            <th class="col-sm-1 ">STT</th>
                            <th class="col-sm-3">Phòng</th>
                            <!--<th class="col-sm-2">Số lượt thuê</th>-->
                            <th class="col-sm">Tổng số tiền</th>
                        </tr>
                    </thead>
                </table>

                <div class="scroll-table">
                    <table class="table table-hover table-striped">
                        <tbody class="text-center">
                            <c:set var="index" value="1"/>
                            <c:forEach items="${table}" var="row">
                                <tr class="d-flex">
                                    <td class="col-sm-1">${index}</td>
                                    <td class="col-sm-3">${row.getRoomName()}</td>
                                    <!--<td class="col-sm-2">\${row.getRentedNum()}</td>-->
                                    <td class="col-sm">
                                        <fmt:formatNumber value="${row.getMoney()}" type="number"/> VND
                                    </td>
                                </tr>
                                <c:set var="index" value="${index + 1}"/>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
    <script src="${context}/resources/js/jquery-3.5.1.js"></script>
    <script src="${context}/resources/js/bootstrap.bundle.js"></script>
    <script src="${context}/resources/js/bootstrap.min.js"></script>
    <script src="${context}/resources/js/get_room_power_info.js"></script>
</html>
