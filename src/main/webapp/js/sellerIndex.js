$(function () {

});

/**
 * 切换状态
 * @param id 内容ID
 */
function switchStatus(id, content) {
    layer.open({
        title: '提示',
        content: '确认'+ content + '吗？',
        btn: ['确定', '关闭'],
        yes: function () {
            var inventory = $("#inventory").html();
            if(inventory > 0) {
                var param = {"cid": id};
                console.log(param);
                $.ajax({
                    url: "/content/switchStatus",
                    type: "post",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        if(data.code == "200") {
                            layer.open({
                                title: '提示',
                                content: content + '成功',
                                btn: ['确定'],
                                icon: 1,
                                btn1: function (index) {
                                    window.location.reload(true);
                                }
                            });
                        } else {
                            layer.open({
                                title: '提示',
                                content: content + '失败',
                                icon: 2
                            });
                        }
                    },
                    error: function (data) {
                        console.log(data);
                    }
                });
            } else {
                layer.open({
                    title: '提示',
                    content: "库存数量为0，不能发布",
                    icon: 2
                });
            }
        },
        cancel: function (index) {
            layer.close(index);
        }
    });
}

/**
 * 进货
 * @param id 内容ID
 */
function addInventory(id) {
    layer.prompt({
        formType: 0,
        title: '请输入进货数量'
    }, function(value, index, elem){
        layer.open({
            title: '提示',
            content: '确认进货' + value + '件吗？',
            btn: ['确定', '关闭'],
            yes: function (index) {
                var param = {"cid": id, "num": value};
                $.ajax({
                    url: "/inventory/add",
                    type: "post",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        if(data.code == "200") {
                            layer.open({
                                title: '提示',
                                content: '进货成功',
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
        layer.close(index);
    });
};

/**
 * 删除内容
 * @param id 内容ID
 */
function del(id) {
    layer.open({
        title: '提示',
        content: '确认删除该内容吗？',
        btn: ['确定', '关闭'],
        yes: function (index) {
            var param = {"cid": id};
            console.log(param);
            $.ajax({
                url: "/content/delete",
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
}