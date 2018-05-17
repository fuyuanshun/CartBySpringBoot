<%@ page import="com.fys.cart.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品列表</title>
    <script type="text/javascript" src="/js/jquery/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css" />
</head>
<%
    User user = (User) request.getSession().getAttribute("user");
    if(null != user) {
%>
<span class="container">欢迎您登陆! <%=user.getName()%>&nbsp;&nbsp;<a href="/logout">退出</a> </span>
<%}%>
<body>
<table class="table table-hover">
    <tr class="active">
        <th>商品id</th>
        <th>商品名称</th>
        <th>数量</th>
        <th>商品单价</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${productList}" var="product">
        <tr>
            <th>${product.id}</th>
            <th>${product.name}</th>
            <th><input type="text" value="1" class="form-control"></th>
            <th>${product.price}</th>
            <th><a href="#?id=${product.id}">加入购物车</a></th>
        </tr>
    </c:forEach>
</table>
</body>
</html>
