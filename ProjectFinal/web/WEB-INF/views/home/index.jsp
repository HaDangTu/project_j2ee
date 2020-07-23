<%@page import="MotelManagement.dto.ApplicationUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>

        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/site.css">
        <link href="${context}/resources/css/site.css" type="text/css" rel="stylesheet">

        <!--Add css/js file-->
        <!--thêm \${pageContext.request.contextPath} trước đường dẫn--> 
        <!--vd \${pageContext.request.contextPath}/resources/css/site.css-->
    </head>
    <body>
        <%
            ApplicationUser user = (ApplicationUser) session.getAttribute("user");
        %>
        <c:choose>
            <c:when test="${role == 'Owner'}">
                <nav class="navbar navbar-expand-sm navbar-dark bg-primary">
                    <div class="container">

                        <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarcolapse">
                            <span class="navbar-toggler-icon"></span>
                        </button>

                        <div class="collapse navbar-collapse" id="navbarcolapse">
                            <a class="navbar-brand" href="./Home">Mariot emotel</a>

                            <ul class="navbar-nav mr-auto">
                                <li class="nav-item">
                                    <a class="nav-link text-white font-weight-bold" href="./Home">Home</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="./ListGuest">Khách trọ</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="./ListRoom">Phòng trọ</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="./ListRoomType">Loại phòng trọ</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="./Invoice">Hóa đơn</a>
                                </li>

                                <li class="nav-item">
                                    <a class="nav-link text-white" href="./Report">Báo cáo</a>
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
            </c:when>
            <c:when test="${role == 'Guest'}">
                <nav class="navbar navbar-expand-sm navbar-dark bg-primary">
                    <div class="container">

                        <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarcolapse">
                            <span class="navbar-toggler-icon"></span>
                        </button>

                        <div class="collapse navbar-collapse" id="navbarcolapse">
                            <a class="navbar-brand" href="./Home">Mariot emotel</a>

                            <ul class="navbar-nav mr-auto">
                                <li class="nav-item">
                                    <a class="nav-link active font-weight-bold" href="./Home">Home</a>
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
            </c:when>
        </c:choose>




        <div class="container mt-4">
            <c:choose> 
                <c:when test="${role == 'Owner'}">
                    <div class="card-columns">
                        <c:forEach items="${rooms}" var="room"> 
                            <c:set var="guestNum" value="${room.getGuests().size()}"/>
                            <c:set var="maxGuest" value="${room.getMaxGuest()}"/>

                            <c:choose>
                                <c:when test="${guestNum == 0}">
                                    <div class="card shadow-sm border-success">
                                        <div class="card-body">
                                            <h4 class="card-title text-success text-center">${room.getRoom().getName()}</h4>
                                            <p class="card-text">Số lượng người: ${guestNum} / ${maxGuest}</p>
                                            <a href="./CreateNewGuest?id=${room.getRoom().getId()}" class="card-link">Thêm khách trọ</a>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${guestNum < maxGuest}">
                                    <div class="card shadow-sm border-warning">
                                        <div class="card-body">
                                            <h4 class="card-title text-warning text-center">${room.getRoom().getName()}</h4>
                                            <p class="card-text">Số lượng người: ${guestNum} / ${maxGuest}</p>
                                            <a href="./CreateNewGuest?id=${room.getRoom().getId()}" class="card-link">Thêm khách trọ</a>
                                            <a href="./ReturnRoom?id=${room.getRoom().getId()}" class="card-link">Trả phòng</a>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="card shadow-sm border-danger card-pointer">
                                        <div class="card-body">
                                            <h4 class="card-title text-danger text-center">${room.getRoom().getName()}</h4>
                                            <p class="card-text">Số lượng người: ${guestNum} / ${maxGuest}</p>
                                            <a href="./ReturnRoom?id=${room.getRoom().getId()}" class="card-link">Trả phòng</a>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                    </div>
                </c:when>
                <c:when test="${role == 'Guest'}">
                    <h4 class="text-center">${room.getRoom().getName()}</h4>
                    <table class="table table-striped table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th>Họ tên</th>
                                <th>Ngày sinh</th>
                                <th>Nghề nghiệp</th>
                                <th>Quê quán</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${room.getGuests()}" var="guest">
                                <tr>
                                    <td>${guest.getName()}</td>
                                    <td>${guest.getBirthday()}</td>
                                    <td>${guest.getOccupation()}</td>
                                    <td>${guest.getHomeTown()}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
            </c:choose>
        </div>
    </body>
    <script src="${context}/resources/js/jquery-3.5.1.js"></script>
    <script src="${context}/resources/js/bootstrap.bundle.js"></script>
    <script src="${context}/resources/js/bootstrap.min.js"></script>
</html>
