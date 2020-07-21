<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${context}/resources/css/site.css">
        <title>Login Page</title>
    </head>
    <body>
        <div class="container">
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 400px;">
                    <div class="card-body">
                        <h3 class="text-center text-primary">Login</h3>
                        <hr>
                        <form action="./Login" method="POST">
                            <div class="form-group">
                                <label for="username" class="font-weight-bold">Tên đăng nhập</label>
                                <input type="text" class="form-control" name="username" placeholder="Username">
                            </div>

                            <div class="form-group">
                                <label for="password" class="font-weight-bold">Mật khẩu</label>
                                <input type="password" class="form-control" name="password" placeholder="Password">
                            </div>

                            <div class="form-group">
                                <c:if test="${errMsg != null}">
                                    <p class="text-danger">${errMsg}</p>
                                </c:if>

                            </div>

                            <div class=form-group">
                                <div class="d-flex">

                                    <input type="submit" value="Login" class="btn btn-outline-primary col-sm">
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="${context}/resources/js/jquery-3.5.1.js"></script>
    <script src="${context}/resources/js/bootstrap.bundle.js"></script>
    <script src="${context}/resources/js/bootstrap.min.js"></script>
</html>
