<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="MotelManagement.bus.GenderBus" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách khách trọ</title>
    </head>
    <body>
        <c:set var="index" value="1"/>
        <table>
            <tr>
                <th>STT</th>
                <th>Họ tên</th>
                <th>Giới tính</th>
                <th>Ngày Sinh</th>
                <th>Quê quán</th>
                <th>Nghề nghiệp</th>
            </tr>
            
            <c:forEach items="${guests}" var="guest">
                <tr>
                    <td>${index}</td>
                    <td>${guest.getName()}</td>
                    <td>${guest.getGender()}</td>
                    <td>${guest.getBirthday()}</td>
                    <td>${guest.getHomeTown()}</td>
                    <td>${guest.getOccupation()}</td>
                    <td><a href="./EditGuest?guest_id=${guest.getId()}">Edit</a>|
                        <a href="./DetailGuest?guest_id=${guest.getId()}">Detail</a>|
                        <a href="./DeleteGuest?guest_id=${guest.getId()}">Delete</a>
                    </td>
                </tr>
                <c:set var="index" value="${index + 1}"/>
            </c:forEach>
        </table>
    </body>
</html>
