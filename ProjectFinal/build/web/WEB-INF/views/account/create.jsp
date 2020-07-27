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
        <title>Tạo tài khoản mới</title>
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
            <h2 class="text-center mt-3">Tạo tài khoản mới</h2>
            <hr>
            <a href="./ListAccount">Back to list</a>
            <div class="d-flex justify-content-center">
                <form action="./CreateNewAccount" method="POST">

                    <div class="form-group">
                        <label for="username" class="font-weight-bold">Tên đăng nhập</label>
                        <input type="text" name="username" class="form-control">
                        <c:if test="${!usernameErrMsg.equals('')}">
                            <p class="text-danger mb-0">${usernameErrMsg}</p>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="password" class="font-weight-bold">Mật khẩu</label>
                        <input type="password" name="password" class="form-control">
                        <c:if test="${!passErrMsg.equals('')}">
                            <p class="text-danger mb-0">${passErrMsg}</p>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="re-enter-pass" class="font-weight-bold">Nhập lại mật khẩu</label>
                        <input type="text" name="re-enter-pass" class="form-control">

                    </div>

                    <div class="form-group">
                        <label for="role-select" class="font-weight-bold">Quyền truy cập</label>
                        <select name="role-select" class="form-control custom-select">
                            <c:forEach items="${roles}" var="role">
                                <option value="${role.getId()}">${role.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="d-flex justify-content-center"> 
                        <input type="submit" value="Tạo tài khoản" class="btn btn-outline-primary">
                    </div>

                </form>
            </div>
        </div>
    </body>
    <script src="${context}/resources/js/jquery-3.5.1.js"></script>
    <script src="${context}/resources/js/bootstrap.bundle.js"></script>
    <script src="${context}/resources/js/bootstrap.min.js"></script>
</html>
