<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="./DeleteRoom?room_id=${room.getId()}&user_id=${room.getUserId()}" method="POST">
            <table>
                <tr>
                    <td><label>Tên phòng</label></td>
                    <td><p>${room.getName()}</p></td>
                </tr>
                <tr>
                    <td><label>Loại phòng</label></td>
                    <td><td><p>${room.getRoomType()}</p></td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="Xóa"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
