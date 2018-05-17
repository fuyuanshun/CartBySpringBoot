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
    <script type="text/javascript" src="/js/jquery/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css" />
</head>

<script type="text/javascript">
    $(function () {
        $(".createItem").click(function () {
            $.ajax({
                url : "/createOrderItem",
                async : false,
                success : function(data, textState) {
                    if(data == "success") {
                        alert("订单生成成功!");
                        window.location.reload();
                    } else alert("服务器出错, 订单生成失败!")
                },
                error : function () {
                    alert("服务器出错啦。。请稍后重试");
                }
            })
        })
    })
</script>

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
                <th class="right-pill">
                    <input type="button" value="生成订单" class="createItem"/>
                </th>
            </c:if>
        </tr>
        <tr>
           <th colspan="5"><a href="/productList">返回商品列表</a> </th>
        </tr>
    </table>
</body>
</html>
