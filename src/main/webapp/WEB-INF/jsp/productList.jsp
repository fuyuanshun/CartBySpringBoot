<%@ page import="com.fys.cart.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品列表</title>
    <script type="text/javascript" src="/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/js/productList.js"></script>
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
        <th>商品单价</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${productList}" var="product">
        <tr>
            <th>${product.id}</th>
            <th>${product.name}</th>
            <th>${product.price}</th>
            <th>
                <form action="" method="post">
                    <span class="span">数量:</span>
                    <input type="text" value="1" class="input-group-sm num" name="num" pid="${product.id}"/>
                    <input type="button" value="加入购物车" class="button" pid="${product.id}"/>
                </form>
            </th>
        </tr>
    </c:forEach>
    <tr class="container">
        <th colspan="4"><a href="/orderItemList">查看购物车</a></th>
    </tr>
</table>
</body>
</html>
