$(function () {
    $(".button").click(function () {
        /*var json = $(".myform").serialize();*/
        var pid = $(this).attr("pid");
        var num = $("input.num[pid="+pid+"]").val();
        
        $.ajax({
            url : "/addOrderItem",
            type: "POST",
            async : true,
            data : {"num": num, "pid":pid},
            success : function (data, textState) {
                alert(data);
            },
            error : function () {
                alert("服务器出了点小问题呢~~ 请稍后再试");
            }
        })
    })
})