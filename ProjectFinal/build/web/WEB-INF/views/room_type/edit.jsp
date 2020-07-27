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
        <title>Cập nhật thông tin loại phòng</title>
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
                            <a class="nav-link active font-weight-bold" href="./ListRoomType">Loại phòng trọ</a>
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
            <h2 class="text-center mt-3">Cập nhật thông tin loại phòng trọ</h2>
            <hr>
            <a href="./ListRoomType">Back to list</a>
            <div class="d-flex justify-content-center">
                <form action="./EditRoomType?id=${roomType.getId()}" method="POST">

                    <div class="form-group">
                        <label for="room-type-name" class="font-weight-bold">Tên loại phòng</label>
                        <input type="text" name="room-type-name" class="form-control"
                               value="${roomType.getName()}">

                        <c:if test="${!nameErrMsg.equals('')}">
                            <p class="text-danger mb-0">${nameErrMsg}</p>
                        </c:if>

                    </div>

                    <div class="form-group">
                        <label for="num-of-guest" class="font-weight-bold">Số lượng người</label>
                        <input type="text" name="num-of-guest" class="form-control"
                               value="${roomType.getNumOfGuest()}">

                        <c:if test="${!numErrMsg.equals('')}">
                            <p class="text-danger mb-0">${numErrMsg}</p>
                        </c:if>

                    </div>

                    <div class="form-group">
                        <label for="price" class="font-weight-bold">Giá thuê</label>
                        <input type="text" name="price" class="form-control"
                               value="${roomType.getPrice()}">

                        <c:if test="${!priceErrMsg.equals('')}">
                            <p class="text-danger mb-0">${priceErrMsg}</p>
                        </c:if>

                    </div>


                    <div class="d-flex justify-content-center"> 
                        <input type="submit" value="Cập nhật" class="btn btn-outline-primary">
                    </div>

                </form>
            </div>
        </div>

    </body>
    <script src="${context}/resources/js/jquery-3.5.1.js"></script>
    <script src="${context}/resources/js/bootstrap.bundle.js"></script>
    <script src="${context}/resources/js/bootstrap.min.js"></script>
</html>
