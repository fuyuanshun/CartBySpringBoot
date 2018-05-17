<%@page isELIgnored="false"%>
<%@   page contentType="text/html;charset=utf-8" %>
<%@ page pageEncoding="utf-8" %>
<%request.setCharacterEncoding("utf-8");%>
<html>
<head>
    <title>用户登陆</title>
    <script type="text/javascript" src="/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/js/login.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jq22.css" type="text/css"/>
</head>
<body>
<div id="login">
    <div class="wrapper">
        <div class="login">
            <form action="" method="post" class="container offset1 loginform" id="loginform">
                <div id="owl-login">
                    <div class="hand"></div>
                    <div class="hand hand-r"></div>
                    <div class="arms">
                        <div class="arm"></div>
                        <div class="arm arm-r"></div>
                    </div>
                </div>
                <div class="pad">
                    <input type="hidden" name="_csrf" value="9IAtUxV2CatyxHiK2LxzOsT6wtBE6h8BpzOmk="/>
                    <div class="control-group">
                        <div class="controls">
                            <label for="username" class="control-label fa fa-envelope"></label>
                            <input id="username" type="text" name="username" placeholder="请输入您的用户名" tabindex="1" autofocus="autofocus" class="form-control input-medium"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <label for="password" class="control-label fa fa-asterisk"></label>
                            <input id="password" type="password" name="password" placeholder="请输入密码" tabindex="2" class="form-control input-medium" />
                        </div>
                    </div>
                </div>
                <div class="form-actions"><a href="${pageContext.request.contextPath}/forgetPassword" tabindex="5" class="btn pull-left btn-link text-muted">忘记密码?</a><a href="${pageContext.request.contextPath}/register" tabindex="6" class="btn btn-link text-muted">注册</a>
                    <input type="button" tabindex="4" class="btn btn-success" value="登陆" id="submit"/>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- end -->
<script type="text/javascript">
    $("#login #password").focus(function() {
        $("#owl-login").addClass("password");
    }).blur(function() {
        $("#owl-login").removeClass("password");
    });
</script>
</body>
</html>
