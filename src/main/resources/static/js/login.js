$(function () {
    $("#submit").click(function () {
        var json = $("#loginform").serialize();

        $.ajax({
            url : "/login",
            type : "POST",
            data : json,
            async : true,
            success : function (data, textStatus) {
                if(data == "success") {
                    $(window).attr("location", "/productList");
                } else {
                    alert("用户名密码不匹配")
                }
            },
            error : function () {
                alert("服务器出了点小问题~~~请稍后重试啦");
            }
        })
    })
})