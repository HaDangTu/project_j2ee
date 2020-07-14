<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo phòng mới</title>
    </head>
    <body>
        <form action="./CreateNewRoom" method="POST">
            <table>
                <tr>
                    <td><label for="name">Tên phòng</label></td>
                    <td><input type="text" name="name"></td>
                    <td>
                        <c:if test="${!nameErrMsg.equals('')}">
                              <p>${nameErrMsg}</p>
                        </c:if>
                </tr>
                <tr>
                    <td><label for="room-type">Loại phòng</label></td>
                    <td>
                        <select name="room-type">
                            <c:forEach items="${roomTypes}" var="roomType">
                                <option value="${roomType.getId()}">${roomType.getName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="Thêm"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
