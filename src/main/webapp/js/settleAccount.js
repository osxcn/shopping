$(function () {
    // 初始情况下不可结算
    $("#Account").prop("disabled","disabled");

    // 焦点离开购买数量输入框时执行更新记录操作
    $("input[name='allNum']").on("blur",function (e) {
        console.log(e);
        var num = e.currentTarget.value;
        var id = $("#" + e.currentTarget.id).attr("tid");
        editNum(id, num);
    });

    // 限制购买数量输入框只能输入数字
    $("input[name='allNum']").on("keypress", function (event) {
        var eobj = event || e;
        var keyCode = eobj.keyCode || eobj.charCode || e.which;
        if(keyCode >= 48 && keyCode <= 57) {
            eobj.returnValue = true;
        } else {
            eobj.preventDefault();
        }
    });

    // 全选事件
    $("#allCheck").on("change", function () {
        if($("#allCheck").is(":checked")) {
            $("#allCheck").prop("checked", "checked");
            $("input[name='check']").prop("checked", "checked");
            $("#Account").removeAttr("disabled");
        } else {
            $("#allCheck").removeAttr("checked");
            $("input[name='check']").removeAttr("checked");
            $("#Account").prop("disabled","disabled");
        }
    });

    // 复选框按钮事件
    $("input[name='check']").on("change", function () {
        var len = $("input[name='check']:checked").length;
        if(len > 0) {
            $("#Account").removeAttr("disabled");
            if($("input[name='check']").length == len) {
                $("#allCheck").prop("checked", "checked");
            } else {
                $("#allCheck").removeAttr("checked");
            }
        } else {
            $("#allCheck").removeAttr("checked");
            $("#Account").prop("disabled","disabled");
        }
    });

    // 购买商品
    $("#Account").on("click", buy);
});

// 数量-1
function plusNum(id) {
    var num = $("#allNum" + id).val();
    if(num > 1) {
        num--;
        editNum(id, num);
    }
}

// 数量+1
function addNum(id) {
    var num = $("#allNum" + id).val();
    var inventory = $("#inventory" + id).val();
    if(num < inventory) {
        num++;
        editNum(id, num);
    }
}

/**
 * 修改内容数量
 * @param id 购物车ID
 * @param num 购买数量
 */
function editNum(id, num) {
    var param = {"id": id, "num": num};
    $.ajax({
        url: "/settleAccount/edit",
        type: "post",
        data: param,
        success: function (data) {
            console.log(data);
            if(data.code == "200") {
                $("#allNum" + id).val(num);
                var price = $("#price" + id).text();
                var payment = price * num;
                $("#payment" + id).html(payment);
            } else {
                layer.open({
                    title: '提示',
                    content: data.message,
                    icon: 2
                });
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}

/**
 * 删除购物车记录
 * @param id
 */
function del(id) {
    layer.open({
        title: '提示',
        content: '确认删除该内容吗？',
        btn: ['确定', '关闭'],
        yes: function (index) {
            var param = {"id": id};
            console.log(param);
            $.ajax({
                url: "/settleAccount/delete",
                type: "post",
                data: param,
                success: function (data) {
                    console.log(data);
                    if(data.code == "200") {
                        layer.open({
                            title: '提示',
                            content: '删除成功',
                            btn: ['确定'],
                            icon: 1,
                            btn1: function (index) {
                                window.location.reload(true);
                            }
                        });
                    } else {
                        layer.open({
                            title: '提示',
                            content: data.message,
                            icon: 2
                        });
                    }
                },
                error: function (data) {
                    console.log(data);
                }
            });
        },
        cancel: function (index) {
            layer.close(index);
        }
    });
};

/**
 * 购物车结算
 */
function buy() {
    var tidList = "";
    $("input[name='check']:checked").each(function () {
        tidList += $(this).attr("tid") + ",";
    });
    var param = {"tidList": tidList};
    $.ajax({
        url: "/settleAccount/buy",
        type: "post",
        data: param,
        success: function (data) {
            console.log(data);
            if(data.code == "200") {
                layer.open({
                    title: '提示',
                    content: '结算成功',
                    btn: ['继续结算','返回首页'],
                    icon: 1,
                    btn1: function (index) {
                        window.location.reload(true);
                    },
                    btn2: function (index) {
                        window.location.href = "/";
                    }
                });
            } else {
                layer.open({
                    title: '提示',
                    content: data.message,
                    icon: 2
                });
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}