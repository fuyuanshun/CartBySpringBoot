<%@ page import="com.fys.cart.model.OrderItem" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    /**
     *  订单总金额
     */
    float countPrice = 0;
    List<OrderItem> orderItemList = (List<OrderItem>) request.getSession().getAttribute("orderItems");
    if(null != orderItemList) {
        for (OrderItem orderItem : orderItemList) {
            countPrice += orderItem.getNum() * orderItem.getProduct().getPrice();
        }
    }
%>
<html>
<head>
    <title class="text-center">购物车</title>
    <link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css" />
</head>
<body>
<div class="text-center">
    <h2>购物车</h2>
</div>
    <table class="table table-hover">
        <tr class="text-danger">
            <th>商品名称</th>
            <th>商品数量</th>
            <th>商品单价</th>
            <th>小计</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${orderItems}" var="orderItem">
            <tr>
                <th>${orderItem.product.name}</th>
                <th>${orderItem.num}</th>
                <th>${orderItem.product.price}</th>
                <th>${orderItem.num*orderItem.product.price}元</th>
                <th><a href="/delOrderItem?id=${orderItem.product.id}">移除</a></th>
            </tr>
        </c:forEach>
        <tr>
            <th></th>
            <th></th>
            <th></th>
            <th>总计:<%=countPrice%>元</th>
            <c:if test="${!empty orderItems}">
                <th class="right-pill"><a href="/createOrderItem">生成订单</a></th>
            </c:if>
        </tr>
        <tr>
           <th colspan="5"><a href="/productList">返回商品列表</a> </th>
        </tr>
    </table>
</body>
</html>
