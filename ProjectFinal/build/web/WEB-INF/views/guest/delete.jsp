<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Xóa thông tin khách trọ</title>
    </head>
    <body>
        <form action="./DeleteGuest?guest_id=${guest.getId()}" method="POST">
            <table>
                <tr>
                    <td><h6>Họ tên</h6></td>
                    <td><p>${guest.getName()}</p></td>
                </tr>
                
                <tr>
                    <td><h6>Ngày sinh</h6></td>
                    <td><p>${guest.getBirthday()}</p></td>
                </tr>
                
                <tr>
                    <td><h6>Quê quán</h6></td>
                    <td><p>${guest.getHomeTown()}</p></td>
                </tr>
                
                <tr>
                    <td><h6>Nghề nghiệp</h6></td>
                    <td><p>${guest.getOccupation()}</p></td>
                </tr>
                
                <tr>
                    <td></td>
                    <td><input type="submit" value="Xóa"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
