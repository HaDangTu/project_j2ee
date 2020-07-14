<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách phòng</title>
    </head>
    <body>
        <table>
            <tr>
                <th>STT</th>
                <th>Tên phòng</th>
                <th>Loại phòng</th>
                <th>Giá thuê</th
            </tr>

            <c:set var="index" value="1"/>
            <c:forEach items="${rooms}" var="room">
                <tr>
                    <td>${index}</td>
                    <td>${room.getName()}</td>
                    <td>${room.getRoomType()}</td>
                    <td>${room.getPrice()}</td>
                    <td>
                        <a href="./EditRoom?room_id=${room.getId()}">Edit</a>| 
                        <a href="./DetailRoom?room_id=${room.getId()}">Detail</a>|
                        <a href="./DeleteRoom?room_id=${room.getId()}">Delete</a>
                    </td>
                    <c:set var="index" value="${index + 1}"/>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
