<%@page import="MotelManagement.dto.ApplicationUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin chỉ số điện nước</title>
        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/site.css">
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
                            <a class="nav-link active font-weight-bold" href="./UpdatePower">Điện nước</a>
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
            <h2 class="text-center mt-3">Thông tin điện nước</h2>
            <hr>
            <c:if test="${!roomErrMsg.equals('')}">
                <label class="text-danger">${roomErrMsg}</label>
            </c:if>
            <div class="d-flex justify-content-center">
                <form action="./UpdatePower" method="POST">
                    <div class="form-inline justify-content-center">
                        <h5 id="room-name"></h5>
                        <input name="room-id" id="room-id" hidden="hidden">

                    </div>
                    <div class="form-inline">
                        <label for="from-date" class="font-weight-bold">Từ ngày</label>
                        <p id="from-date" name="from-date" class="ml-3 mb-0"></p>
                        <label for="to-date" class="ml-5 font-weight-bold">Đến ngày</label>
                        <p id="to-date" class="ml-3 mb-0"></p>
                        <input type="text" id="to-date-value" hidden="hidden" name="to-date">
                    </div>

                    <h6 class="mt-3">Chỉ số điện</h6>
                    <div class="form-inline">
                        <label for="old-electric-index" class="font-weight-bold">Cũ</label>
                        <input id="old-electric" type="number" name="old-electric-index" 
                               class="form-control ml-3" readonly="true">
                        <label for="new-electric-index" class="ml-5 font-weight-bold">Mới</label>
                        <input type="number" name="new-electric-index" class="form-control ml-3">
                        <c:if test="${!electricErrMsg.equals('')}">
                            <label class="text-danger ml-3">${electricErrMsg}</label>
                        </c:if>
                    </div>

                    <h6 class="mt-3">Chỉ số nước</h6>
                    <div class="form-inline">
                        <label for="old-water-index" class="font-weight-bold">Cũ</label>
                        <input id="old-water" type="number" name="old-water-index" 
                               class="form-control ml-3" readonly="true">
                        <label for="new-water-index" class="ml-5 font-weight-bold">Mới</label>
                        <input type="number" name="new-water-index" class="form-control ml-3">
                        <c:if test="${!waterErrMsg.equals('')}">
                            <label class="text-danger ml-3">${waterErrMsg}</label>
                        </c:if>
                    </div>

                    <div class="form-inline mt-4 justify-content-center">
                        <input type="submit" value="Cập nhật" class="btn btn-outline-primary">
                    </div>
                </form>
            </div>

            <div class="mt-4">
                <table class="table table-bordered mb-0">
                    <thead class="thead-dark text-center">
                        <tr class="d-flex">
                            <th class="col-1">STT</th>
                            <th class="col-3">Phòng</th>
                            <th class="col-2">Ngày</th>
                            <th class="col-3">Chỉ số điện</th>
                            <th class="col-3">Chỉ số nước</th>
                        </tr>
                    </thead>
                </table>
                <div class="scroll-table mt-0">
                    <table id="table-info" class="table table-hover table-striped" onclick="onRowClick(event)">
                        <tbody class="text-center">
                            <c:set var="index" value="1"/>
                            <c:forEach items="${powerInfos}" var="powerInfo">
                                <tr class="d-flex">
                                    <td class="col-1">${index}</td>
                                    <td class="col-3">${powerInfo.getRoomName()}</td>
                                    <td class="col-2">${powerInfo.getDate()}</td>
                                    <td class="col-3">${powerInfo.getElectricityIndex()}</td>
                                    <td class="col-3">${powerInfo.getWaterIndex()}</td>
                                    <td hidden="hidden">${powerInfo.getRoomId()}</td>
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
    <script src="${context}/resources/js/get_room_power_info.js"></script>
</html>
