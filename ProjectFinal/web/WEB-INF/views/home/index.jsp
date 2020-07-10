<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <link href="${context}/resources/css/site.css" type="text/css" rel="stylesheet">
        
        <!--Add css/js file-->
        <!--thêm \${pageContext.request.contextPath} trước đường dẫn--> 
        <!--vd \${pageContext.request.contextPath}/resources/css/site.css-->
    </head>
    <body>
        <!--các biến role home lấy từ HomeServlet -->
        <!-- do trong jsp tag lib chỉ có c:if không có c:else nên dùng cái này thay thế -->
        <c:choose> 
            <c:when test="${role == 'Owner'}">
                <!-- giống câu lệnh foreach trong java -->
                <c:forEach items="${rooms}" var="room"> 
                    <div class="card">
                        <h4>${room.getRoom().getName()}</h4>
                        <p>Số lượng người: ${room.getGuests().size()}</p>
                    </div>
                </c:forEach>
            </c:when>
            <c:when test="${role == 'Guest'}">
                <p>${room.getRoom().getName()}</p>
                <table>
                    <tr>
                        <th>Họ tên</th>
                        <th>Ngày sinh</th>
                        <th>Nghề nghiệp</th>
                        <th>Quê quán</th>
                    </tr>
                    <c:forEach items="${room.getGuests()}" var="guest">
                        <tr>
                            <td>${guest.getName()}</td>
                            <td>${guest.getBirthday()}</td>
                            <td>${guest.getOccupation()}</td>
                            <td>${guest.getHomeTown()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
        </c:choose>
    </body>
</html>
