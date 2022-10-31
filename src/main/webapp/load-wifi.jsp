<%@ page import="com.example.web.service.PubService" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>와이파이 정보 구하기</title>
    </head>
    <body>
        <h1>
            <%
                PubService pubService = new PubService();
                pubService.reset();
                int total = pubService.getTotal();
                out.write(String.valueOf(total));
                pubService.insertData();
            %>
            개의 WIFI 정보를 정상적으로 저장하였습니다.
        </h1>
        <a href="index.jsp">홈으로 가기</a>
    </body>
</html>
