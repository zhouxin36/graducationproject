$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'home_list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', index: "id", width: 130, key: true, sortable: false},
            {label: '商品类型', name: 'categoryName', width: 75, sortable: false},
            {
                label: '排序', name: 'sort', width: 75, sortable: false, formatter: function (value, options, row) {
                    if (value =="0") {
                        return "添加时间降序";
                    }
                    if (value =="1") {
                        return "添加时间升序";
                    }
                    if (value =="2") {
                        return "总销量升序";
                    }
                    if (value =="3") {
                        return "总销量降序";
                    }
                    if (value =="4") {
                        return "价格升序";
                    }
                    if (value =="5") {
                        return "价格降序";
                    }
                }
            }
        ],
        viewrecords: true,
        height: screen.height * 0.55,
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
    add_category();
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            name: null
        },
        showList: true,
        title: null,
        category: null,
        list_category: null,
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

            confirm('确定要删除选中的记录？', function () {
                $.get(baseURL + "app/" + id + "/del", function (r) {
                    if (r.permissionCheck) {
                        alert(r.msg);
                        return;
                    }
                    if (r.code == 0) {
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

            $.ajax({
                type: "POST",
                url: baseURL + "home_add",
                contentType: "application/json",
                data: JSON.stringify(vm.app),
                success: function (r) {
                    if (r.code === 200) {
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
            $.get(baseURL + "app/" + id + "/query", function (r) {
                if (r.permissionCheck) {
                    alert(r.msg);
                    return;
                }
                vm.app = r.app;
            });
        },
        reload: function (p) {
            vm.showList = true;
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
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

function add_category() {
    $.get(baseURL + "product_select_category", function (r) {
        if (r.code != 200) {
            alert(r.msg);
            return;
        }
        vm.list_category = r.list;
    });
}