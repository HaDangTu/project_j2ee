<%@page import="MotelManagement.dto.ApplicationUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/site.css">
        <title>Loại phòng trọ</title>
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
                                Hello! <%= user.getUsername() %>
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
            <h2 class="text-center">Phòng trọ</h2>
            <hr>
            <a href="./CreateNewRoomType">Thêm loại phòng trọ mới</a>

            <div class="mt-4">
                <c:set var="index" value="1"/>
                <table class="table table-bordered mb-0">
                    <thead class="thead-dark text-center">
                        <tr class="d-flex">
                            <th class="col-sm-1">STT</th>
                            <th class="col-sm-3">Tên loại phòng</th>
                            <th class="col-sm-3">Số lượng người</th>
                            <th class="col-sm-3">Giá thuê</th>
                            <th class="col-sm"></th>
                        </tr>
                    </thead>
                </table>
                <div class="scroll-table" style="height: 400px;">
                    <table class="table table-hover table-striped">
                        <tbody class="text-center">
                            <c:forEach var="roomType" items="${roomTypes}">
                                <tr class="d-flex">
                                    <td class="col-sm-1">${index}</td>
                                    <td class="col-sm-3">${roomType.getName()}</td>
                                    <td class="col-sm-3">${roomType.getNumOfGuest()}</td>
                                    <td class="col-sm-3">
                                        <fmt:formatNumber value="${roomType.getPrice()}" type="number"/> VND
                                    </td>
                                    <td class="col-sm">
                                        <a href="./EditRoomType?id=${roomType.getId()}" class="small">Edit</a> |  
                                        <a href="./DeleteRoomType?id=${roomType.getId()}" class="small">Delete</a>
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
</html>
