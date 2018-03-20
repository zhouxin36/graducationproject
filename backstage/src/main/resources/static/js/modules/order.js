$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'forder_list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', index: "id", width: 150, key: true, sortable: false},
            {
                label: '物流', name: 'logistics', width: 130, sortable: false, formatter: function (value, options, row) {
                if (value == 0) {
                    return "圆通";
                }
                if (value == 1) {
                    return "申通";
                }
                if (value == 2) {
                    return "韵达";
                }
                if (value == 3) {
                    return "中通";
                }
                if (value == 4) {
                    return "顺丰";
                }
                if(value == undefined){
                    return "";
                }
            }
            },
            {
                label: '支付方式', name: 'payment', width: 130, sortable: false, formatter: function (value, options, row) {
                if (value == 1) {
                    return "余额支付";
                }
                if(value == undefined){
                    return "";
                }
                if (value == 2) {
                    return "支付宝支付";
                }
            }
            },
            {
                label: '订单状态', name: 'status', width: 130, sortable: false, formatter: function (value, options, row) {
                if (value == 0) {
                    return "未支付";
                }
                if(value == undefined){
                    return "";
                }
                if (value == 1) {
                    return "待发货";
                }
                if (value == 2) {
                    return "待收货";
                }
                if (value == 3) {
                    return "待评价";
                }
                if (value == 4) {
                    return "已完成";
                }
            }
            },
            {label: '总额', name: 'total', width: 75, sortable: false},
            {label: '添加时间', name: 'addDate', width: 75, sortable: false, formatter: formatDate},
            {
                label: '完成时间',
                name: 'successTime',
                width: 75,
                sortable: false,
                formatter: function (value, options, row) {
                    if (value == undefined || value == null) {
                        return "未完成"
                    } else {
                        return formatDate(value);
                    }
                }
            },
            {label: '操作', name: 'operation', width: 50, formatter: operateMenu, sortable: false}
        ],
        viewrecords: true,
        height: screen.height * 0.55,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            name: null
        },
        showList: true,
        title: null,
        app: {
            deleteStatus: 0
        }
    },
    methods: {
        query: function () {
            vm.reload(1);
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.app = {deleteStatus: 0};
        },
        update: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getApp(id);
        },
        del: function () {
            var id = getSelectedRows();
            if (id == null) {
                return;
            }

            confirm('确定要发货吗', function () {
                $.get(baseURL + "forder_deliver?id=" + id, function (r) {
                    if (r.code == 200) {
                        alert('操作成功', function () {
                            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
                            vm.reload(page);
                        });
                    } else {
                        alert(r.msg);
                    }
                });
            });
        },
        saveOrUpdate: function () {
            if (vm.validator()) {
                return;
            }

            var url = vm.app.id == null ? "app/add" : "app/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.app),
                success: function (r) {
                    if (r.permissionCheck) {
                        alert(r.msg);
                        return;
                    }
                    if (r.code === 0) {
                        alert('操作成功', function () {
                            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
                            vm.reload(page);
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        getApp: function (id) {
            $.get(baseURL + "request_forder?id=" + id, function (r) {
                vm.app = r.data.forder;
                vm.app.addDate = formatDate(vm.app.addDate);
                if (vm.app.successTime == undefined || vm.app.successTime == null) {
                    vm.app.successTime = "未完成";
                } else {
                    vm.app.successTime = formatDate(vm.app.successTime);
                }
                if (vm.app.logistics == undefined || vm.app.logistics == null) {
                    vm.app.logistics = "";
                }
                if (vm.app.logistics == 0) {
                    vm.app.logistics = "圆通";
                }
                if (vm.app.logistics == 1) {
                    vm.app.logistics = "申通";
                }
                if (vm.app.logistics == 2) {
                    vm.app.logistics = "韵达";
                }
                if (vm.app.logistics == 3) {
                    vm.app.logistics = "中通";
                }
                if (vm.app.logistics == 4) {
                    vm.app.logistics = "顺丰";
                }
                if (vm.app.status == 0) {
                    vm.app.status = "未支付";
                }
                if (vm.app.status == 1) {
                    vm.app.status = "待发货";
                }
                if (vm.app.status == 2) {
                    vm.app.status = "待收货";
                }
                if (vm.app.status == 3) {
                    vm.app.status = "待评价";
                }
                if (vm.app.status == 4) {
                    vm.app.status = "已完成";
                }
                if (vm.app.payment == undefined || vm.app.payment == null) {
                    vm.app.payment = "";
                }
                if (vm.app.payment == 1) {
                    vm.app.payment = "余额支付";
                }
                if (vm.app.payment == 2) {
                    vm.app.payment = "支付宝支付";
                }
                if (r.data.userAddress != null) {
                    vm.app.name = r.data.userAddress.name;
                    vm.app.phone = r.data.userAddress.phone;
                    vm.app.address = r.data.userAddress.address;
                }
                vm.app.userId = r.data.user.id;
                vm.app.userName = r.data.user.name;

                var tbody = $("<tbody></tbody>");
                tbody.empty();
                var th1 = $("<tr></tr>");
                var td1_1 = $("<th></th>");
                td1_1.append("商品Id");
                var td1_2 = $("<th></th>");
                td1_2.append("商品图像");
                var td1_3 = $("<th></th>");
                td1_3.append("商品名称");
                var td1_4 = $("<th></th>");
                td1_4.append("购买单价");
                var td1_5 = $("<th></th>");
                td1_5.append("购买数量");
                th1.append(td1_1).append(td1_2).append(td1_3).append(td1_4).append(td1_5);
                tbody.append(th1);
                for (var i = 0; r.data.listSorder[i] != null; i++) {
                    $.ajax({
                        url: baseURL + "find_img",
                        data: "id=" + r.data.listSorder[i].productId,
                        type: "get",
                        async: false,
                        success: function (r) {
                            if (r.code == 200) {
                                url_image = r.list1[0].path;
                            } else {
                                alert(r.msg);
                                return;
                            }
                        }
                    });
                    var img = "<image src='" + fileURL + url_image + "' style='width: 160px;height: 80px;'/>";
                    var tr2 = $("<tr></tr>");
                    var td1 = $("<td></td>");
                    td1.append(r.data.listSorder[i].id);
                    var td2 = $("<td></td>");
                    td2.append(r.data.listSorder[i].name);
                    var td3 = $("<td></td>");
                    td3.append(r.data.listSorder[i].price);
                    var td4 = $("<td></td>");
                    td4.append(r.data.listSorder[i].number);
                    var td5 = $("<td></td>");
                    td5.append(img);
                    tr2.append(td1).append(td5).append(td2).append(td3).append(td4);
                    tbody.append(tr2);
                }
                var ta = $("<table></table>");
                ta.addClass("table table-hover");
                ta.append(tbody);
                $("#sorders").append(ta);
            });
        },
        reload: function (p) {
            vm.showList = true;
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'logistics': vm.q.logistics
                    , 'status': vm.q.status
                    , 'payment': vm.q.payment
                    , 'start': vm.q.start
                    , 'end': vm.q.end
                },
                page: p
            }).trigger("reloadGrid");
        },
        validator: function () {

        }
    }
});

/**
 * DataGrid行操作
 * */
function operateMenu(cellvalue, options, rowObject) {
    var aid = '\"' + rowObject.id + '\"';
    var result = "";
    var detailBtn = "<a class='btn btn-default' data-toggle='modal' data-target='#modal_app_info' onclick='openDetail(" + aid + ")'>详情</a>";
    result += detailBtn;
    return result;
}

function openDetail(aid) {
    vm.getApp(aid);
}

function formatDate(arr, options, rowObject) {
    if (arr != null) {
        var str = "";
        for (var i = 0; i < arr.length; i++) {
            if (i < 2) {
                str += arr[i] + '-';
            }
            if (i == 2) {
                str += arr[i] + ' ';
            }
            if (i > 2 && i < 5) {
                str += arr[i] + ':';
            }
            if (i == 5) {
                str += arr[i];
            }
        }
    }

    return str;
}

function formatURL(value, options, rowObject) {
    var token = localStorage.getItem('token');
    var result = value + '?token=' + token + '&setToken=true';
    if (!(value.startsWith("http://") || (value.startsWith("https://")))) result = "http://" + result;
    return '<a href="' + result + '" target="_blank">' + value + '</a>';
}
