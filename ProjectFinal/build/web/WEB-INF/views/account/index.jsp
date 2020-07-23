<%@page import="MotelManagement.dto.ApplicationUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/site.css">
        <title>Tài khoản</title>
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
                            <a class="nav-link active" href="./Report">Báo cáo</a>
                        </li>
                    </ul>

                    <ul class="navbar-nav my-2 my-lg-0">
                        <li class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle active font-weight-bold" data-toggle="dropdown">
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
            <h2 class="text-center mt-3">Tài khoản</h2>
            <hr>

            <a href="./CreateNewAccount">Tạo tài khoản mới</a>

            <div class="mt-4">
                <c:set var="index" value="1"/>
                <table class="table table-bordered mb-0">
                    <thead class="thead-dark text-center small">
                        <tr class="d-flex">
                            <th class="col-sm-1">STT</th>
                            <th class="col-sm-3">Tên đăng nhập</th>
                            <th class="col-sm-4">Mật khẩu</th>
                            <th class="col-sm-2">Quyền truy cập</th>
                            <th class="col-sm"></th>
                        </tr>
                    </thead>
                </table>

                <div class="scroll-table" style="height: 400px;">
                    <table class="table table-hover table-striped">
                        <tbody class="text-center small">
                            <c:set var="index" value="1"/>
                            <c:forEach items="${accounts}" var="account">
                                <tr class="d-flex">
                                    <td class="col-sm-1">${index}</td>
                                    <td class="col-sm-3">${account.getUsername()}</td>
                                    <td class="col-sm-4">${account.getPassword()}</td>
                                    <td class="col-sm-2">${account.getRole()}</td>
                                    <td class="col-sm">
                                        <a href="./EditAccount?id=${account.getId()}">Edit</a> | 
                                        <a href="./DeleteAccount?id=${account.getId()}">Delete</a>
                                    </td>
                                    <c:set var="index" value="${index + 1}"/>
                                </tr>
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
