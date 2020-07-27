<%@page import="MotelManagement.dto.ApplicationUser"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/site.css">
        <title>Thêm khách trọ</title>
    </head>
    <body>
        <%
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String today = formatter.format(date);
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
                            <a class="nav-link active font-weight-bold" href="./ListGuest">Khách trọ</a>
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
            <h2 class="text-center mt-3">Tạo khách trọ mới</h2>
            <hr>
            <a href="./ListGuest">Back to list</a>
            <div class="d-flex justify-content-center">
                <form action="./CreateNewGuest" method="POST">
                    <div class="form-group">
                        <label for="room">Phòng</label>
                        <select id="room-select" name="room" class="form-control custom-select">
                            <c:forEach items="${rooms}" var="room">
                                <option value="${room.getId()}">${room.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="row">
                        <div class="form-group col-sm-8">
                            <label for="name">Họ tên</label>
                            <input type="text" name="name" class="form-control">
                            <c:if test="${!nameErrMsg.equals('')}">
                                <p class="text-danger mb-0">${nameErrMsg}</p>
                            </c:if>
                        </div>

                        <div class="form-group col-sm">
                            <label for="gender">Giới tính</label>
                            <select name="gender" class="form-control custom-select">
                                <c:forEach items="${genders}" var="gender">
                                    <option value="${gender.getId()}">${gender.getName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-sm-6">
                            <label for="birthday">Ngày sinh</label>
                            <input name="birthday" type="date" class="form-control">
                            <c:if test="${!birthdayErrMsg.equals('')}">
                                <p class="mb-0 text-danger">${birthdayErrMsg}</p>
                            </c:if>
                        </div>

                        <div class="form-group col-sm">
                            <label for="identity_number">Số CMND/căn cước</label>
                            <input name="identity_number" type="number" class="form-control">
                            <c:if test="${!identityErrMsg.equals('')}">
                                <p class="mb-0 text-danger">${identityErrMsg}</p>
                            </c:if>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-sm">
                            <label for="home_town">Quê quán</label>
                            <input type="text" name="home_town" class="form-control">
                            <c:if test="${!homeTownErrMsg.equals('')}">
                                <p class="mb-0 text-danger">${homeTownErrMsg}</p>
                            </c:if>
                        </div>

                        <div class="form-group col-sm">
                            <label for="occupation">Nghề nghiệp</label>
                            <input type="text" name="occupation" class="form-control">
                            <c:if test="${!occupationErrMsg.equals('')}">
                                <p class="mb-0 text-danger">${occupationErrMsg}</p>
                            </c:if>
                        </div>


                    </div>

                    <div class="form-group">
                        <label for="start-date">Ngày bắt đầu</label>
                        <input type="date" name="start_date" class="form-control"
                               value="<%= today%>">
                        <c:if test="${!startDateErrMsg.equals('')}">
                            <p class="mb-0 text-danger">${startDateErrMsg}</p>
                        </c:if>
                    </div>

                    <div class="d-flex justify-content-center mt-3">
                        <input type="submit" class="btn btn-outline-primary" value="Thêm khách trọ">
                    </div>
                </form>
            </div>
        </div>

    </body>
    <script>
        <c:if test="${roomId != null}">
        document.getElementById("room-select").value = '${roomId}';
        </c:if>
    </script>
    <script src="${context}/resources/js/jquery-3.5.1.js"></script>
    <script src="${context}/resources/js/bootstrap.bundle.js"></script>
    <script src="${context}/resources/js/bootstrap.min.js"></script>
</html>
