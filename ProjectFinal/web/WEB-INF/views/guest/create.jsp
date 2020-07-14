<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm khách trọ</title>
    </head>
    <body>
        <% 
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String today = formatter.format(date);
        %>
        <form action="./CreateNewGuest" method="POST">
            <table>
                <!-- Phòng -->
                <tr>
                    <td><label for="room">Phòng</label></td>
                    <td>
                        <select name="room">
                            <c:forEach items="${rooms}" var="room">
                                <option value="${room.getId()}">${room.getName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                
                <!-- Họ tên -->
                <tr>
                    <td><label for="name">Họ tên</label></td>
                    <td><input type="text" name="name"></td>
                    <td>
                        <c:if test="${!nameErrMsg.equals('')}">
                            <p>${nameErrMsg}</p>
                        </c:if>
                    </td>
                </tr>
                
                <!-- Ngày sinh -->
                <tr>
                    <td><label for="birthday">Ngày sinh</label></td>
                    <td><input type="date" name="birthday"></td>
                    <td>
                        <c:if test="${!birthdayErrMsg.equals('')}">
                            <p>${birthdayErrMsg}</p>
                        </c:if>
                    </td>
                </tr>
                
                <!-- Giới tính -->
                <tr>
                    <td><label for="gender">Giới tính</label></td>
                    <td>
                        <select name="gender">
                            <c:forEach items="${genders}" var="gender">
                                <option value="${gender.getId()}">${gender.getName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                
                <!-- Số CMND -->
                <tr>
                    <td><label for="identity_number">Số CMND</label></td>
                    <td><input type="text" name="identity_number"></td>
                    <td>
                        <c:if test="${!identityErrMsg.equals('')}">
                            <p>${identityErrMsg}</p>
                        </c:if>
                    </td>
                </tr>
                
                <!-- Quê quán -->
                <tr>
                    <td><label for="home_town">Quê quán</label></td>
                    <td><input type="text" name="home_town"></td>
                    <td>
                        <c:if test="${!homeTownErrMsg.equals('')}">
                            <p>${homeTownErrMsg}</p>
                        </c:if>
                    </td>
                </tr>
                
                <!-- Nghề nghiệp -->
                <tr>
                    <td><label for="occupation">Nghề nghiệp</label></td>
                    <td><input type="text" name="occupation"></td>
                    <td>
                        <c:if test="${!occupationErrMsg.equals('')}">
                            <p>${occupationErrMsg}</p>
                        </c:if>
                    </td>
                </tr>
                
                <!-- Ngày bắt đầu ở -->
                <tr>
                    <td><label for="start_date">Ngày bắt đầu ở</label></td>
                    <td><input type="date" name="start_date" value="<%= today %>"></td>
                    <td>
                        <c:if test="${!startDateErrMsg.equals('')}">
                            <p>${startDateErrMsg}</p>
                        </c:if>
                    </td>
                </tr>
                
                <tr>
                    <td><input type="submit" value="Thêm"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
