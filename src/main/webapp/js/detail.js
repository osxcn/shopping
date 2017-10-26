$(function () {

});

$("#plusNum").on("click", function () {
    var num = $("#allNum").val();
    if(num > 1)
        num--;
    $("#allNum").val(num);
});

$("#addNum").on("click", function () {
    var num = $("#allNum").val();
    var inventory = $("#inventory").html();
    if(num < inventory) {
        num++;
        $("#allNum").val(num);
    }
});

$("#allNum").on("keypress", function (event) {
    var eobj = event || e;
    var keyCode = eobj.keyCode || eobj.charCode || e.which;
    if(keyCode >= 48 && keyCode <= 57) {
        eobj.returnValue = true;
    } else {
        eobj.preventDefault();
    }
});

// 直接购买
$("#buy").on("click", function () {
    $.ajax({
        url: "/api/isLogin",
        type: "get",
        success: function (data) {
            console.log(data);
            if(data.code == "200") {
                layer.open({
                    title: '提示',
                    content: '确认购买吗？',
                    btn: ['确定', '取消'],
                    yes: function (index) {
                        var cid = $("#cid").val();
                        var num = $("#allNum").val();
                        var param = {"cid": cid, "num": num};
                        console.log(param);
                        $.ajax({
                            url: "/settleAccount/buy",
                            type: "post",
                            data: param,
                            success: function (data) {
                                console.log(data);
                                if(data.code == "200") {
                                    layer.open({
                                        title: '提示',
                                        content: '购买成功',
                                        btn: ['继续购买','返回首页'],
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
                    },
                    cancel: function (index) {
                        layer.close(index);
                    }
                });
            } else {
                layer.open({
                    title: '提示',
                    time: 10000,// 10秒后自动关闭
                    icon: 0,
                    content: '登录后才可购买，请登录'
                });
            }
        },
        error: function (data) {
            console.log(data);
            layer.open({
                title: '提示',
                time: 10000,// 10秒后自动关闭
                icon: 0,
                content: '网络故障，请重试'
            });
        }
    });
});

// 加入购物车
$("#add").on("click", function () {
    $.ajax({
        url: "/api/isLogin",
        type: "get",
        success: function (data) {
            console.log(data);
            if(data.code == "200") {
                layer.open({
                    title: '提示',
                    content: '确认加入购物车吗？',
                    btn:['确定','取消'],
                    yes: function (index, layero) {
                        var cid = $("#cid").val();
                        var num = $('#allNum').val();
                        var param = {"cid": cid, "num": num};
                        $.ajax({
                            url: "/settleAccount/add",
                            type: "post",
                            data: param,
                            success: function (data) {
                                console.log(data);
                                if(data.code == "200") {
                                    layer.open({
                                        title: '提示',
                                        content: '加入购物车成功',
                                        btn: ['前往购物车','继续购买','返回首页'],
                                        icon: 1,
                                        btn1: function(index) {
                                            window.location.href = "/settleAccount/list";
                                        },
                                        btn2: function (index) {
                                            window.location.reload(true);
                                        },
                                        btn3: function (index) {
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
                                layer.open({
                                    title: '提示',
                                    content: data.message,
                                    icon: 2
                                });
                            }
                        });
                    },
                    cancel: function (index) {
                        layer.close(index);
                    }
                });
            } else {
                layer.open({
                    title: '提示',
                    time: 10000,// 10秒后自动关闭
                    icon: 0,
                    content: '登录后才可购买，请登录'
                });
            }
        },
        error: function (data) {
            console.log(data);
            layer.open({
                title: '提示',
                time: 10000,// 10秒后自动关闭
                icon: 0,
                content: '网络故障，请重试'
            });
        }
    });
});