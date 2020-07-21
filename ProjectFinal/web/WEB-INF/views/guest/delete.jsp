<%@page import="MotelManagement.dto.ApplicationUser"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/site.css">
        <title>Xóa thông tin khách trọ</title>
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
            <h2 class="text-center">Xóa thông tin khách trọ</h2>
            <hr>
            <a href="./ListGuest">Back to list</a>
            <div class="d-flex justify-content-center">
                <form class="col-7" action="./DeleteGuest?guest_id=${guest.getId()}" method="POST">
                    <div class="row">
                        <label class="col-sm-2 font-weight-bold">Phòng</label>
                        <p class="mb-0 col-sm">${guest.getRoom()}</p>
                    </div>

                    <div class="row">
                        <label class="font-weight-bold col-sm-2">Họ tên</label>
                        <p class="mb-0 col-sm-4">${guest.getName()}</p>
                        <label class="font-weight-bold col-sm-3">Giới tính</label>
                        <p class="mb-0 col-sm">${guest.getGender()}</p>
                    </div>

                    <div class="row">
                        <label class="font-weight-bold col-sm-6">Ngày sinh</label>
                        <p class="mb-0 col-sm">
                            <fmt:formatDate value="${guest.getBirthday()}"
                                            type="DATE"
                                            dateStyle="SHORT"
                                            pattern="dd/MM/yyyy"/>
                        </p>
                    </div>

                    <div class="row">
                        <label class="font-weight-bold col-sm-6">Số CMND/căn cước</label>
                        <p class="mb-0 col-sm">${guest.getIndentityNumber()}</p>
                    </div>

                    <div class="row">
                        <label class="font-weight-bold col-sm-6">Quê quán</label>
                        <p class="mb-0 col-sm">${guest.getHomeTown()}</p>
                    </div>

                    <div class="row">
                        <label class="font-weight-bold col-sm-6">Nghề nghiệp</label>
                        <p class="mb-0 col-sm">${guest.getOccupation()}</p>
                    </div>

                    <div class="row">
                        <label class="font-weight-bold col-sm-6">Ngày bắt đầu</label>
                        <p class="mb-0 col-sm">
                            <fmt:formatDate value="${guest.getStartDate()}"
                                            type="DATE"
                                            dateStyle="SHORT"
                                            pattern="dd/MM/yyyy"/>
                        </p>
                    </div>

                    <div class="d-flex justify-content-center mt-3">
                        <input type="submit" class="btn btn-outline-primary" value="Xóa khách trọ">
                    </div>
                </form>
            </div>
        </div>
    </body>
    <script src="${context}/resources/js/jquery-3.5.1.js"></script>
    <script src="${context}/resources/js/bootstrap.bundle.js"></script>
    <script src="${context}/resources/js/bootstrap.min.js"></script>
</html>
