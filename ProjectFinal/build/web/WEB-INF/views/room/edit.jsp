<%@page import="MotelManagement.dto.ApplicationUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/site.css">
        <title>Cập nhật thông tin phòng</title>
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
            <h2 class="text-center">Tạo phòng trọ mới</h2>
            <hr>
            <a href="./ListRoom">Back to list</a>
            <div class="d-flex justify-content-center">
                <form action="./EditRoom?room_id=${room.getId()}" method="POST">
                    <div class="form-group">
                        <label for="name" class="font-weight-bold">Tên phòng</label>
                        <input type="text" name="name" class="form-control"
                               value="${room.getName()}">

                        <c:if test="${!nameErrMsg.equals('')}">
                            <p class="text-danger mb-0">${nameErrMsg}</p>
                        </c:if>

                    </div>
                    <div class="form-group">
                        <label for="room-type" class="font-weight-bold">Loại phòng</label>

                        <select id="room-type-select" name="room-type"
                                class="form-control custom-select">
                            <c:forEach items="${roomTypes}" var="roomType">
                                <option value="${roomType.getId()}">${roomType.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="d-flex justify-content-center">
                        <input type="submit" value="Cập nhật">
                    </div>
                </form>
            </div>
        </div>
    </body>
    <script>
        document.getElementById("room-type-select").value = '${room.getRoomTypeId()}';
    </script>
    <script src="${context}/resources/js/jquery-3.5.1.js"></script>
    <script src="${context}/resources/js/bootstrap.bundle.js"></script>
    <script src="${context}/resources/js/bootstrap.min.js"></script>
</html>
