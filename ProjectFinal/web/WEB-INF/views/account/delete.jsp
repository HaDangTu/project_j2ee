<%@page import="MotelManagement.dto.ApplicationUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/site.css">
        <title>Xóa tài khoản</title>
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
                                <a href="./ChangePassword" class="dropdown-item">Manage account</a>
                                <a href="./Logout" class="dropdown-item">Logout</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <h2 class="text-center mt-3">Xóa thông tin tài khoản</h2>
            <hr>
            <a href="./ListAccount">Back to list</a>
            <div class="d-flex justify-content-center">
                <form action="./DeleteAccount?id=${account.getId()}" method="POST">

                    <div class="form-inline mt-1">
                        <label class="font-weight-bold mr-5">Tên đăng nhập</label>
                        <p class="mb-0">${account.getUsername()}</p>
                    </div>

                    <div class="form-inline mt-1">
                        <label class="font-weight-bold mr-5">Password</label>
                        <p class="mb-0">${account.getPassword()}</p>
                    </div>

                    <div class="form-inline mt-1">
                        <label class="font-weight-bold mr-5">Quyền truy cập</label>
                        <p class="mb-0">
                            ${account.getRole()}
                        </p>
                    </div>

                    <div class="d-flex justify-content-center mt-2">
                        <input type="submit" value="Xóa" class="btn btn-outline-primary">
                    </div>
                </form>  
            </div>
        </div>
    </body>
    <script src="${context}/resources/js/jquery-3.5.1.js"></script>
    <script src="${context}/resources/js/bootstrap.bundle.js"></script>
    <script src="${context}/resources/js/bootstrap.min.js"></script>
</html>
