<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật thông tin phòng</title>
    </head>
    <body>
        <form action="./EditRoom?room_id=${room.getId()}" method="POST">
            <table>
                <tr>
                    <td><label for="name">Tên phòng</label></td>
                    <td><input type="text" name="name" value="${room.getName()}"></td>
                    <td>
                        <c:if test="${!nameErrMsg.equals('')}">
                              <p>${nameErrMsg}</p>
                        </c:if>
                </tr>
                <tr>
                    <td><label for="room-type">Loại phòng</label></td>
                    <td>
                        <select id="room-type-select" name="room-type">
                            <c:forEach items="${roomTypes}" var="roomType">
                                <option value="${roomType.getId()}">${roomType.getName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="Cập nhật"></td>
                </tr>
            </table>
        </form>
    </body>
    <script>
        document.getElementById("room-type-select").value= '${room.getRoomTypeId()}';
    </script>
</html>
