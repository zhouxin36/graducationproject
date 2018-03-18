$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'product_list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', index: "id", width: 130, key: true },
            { label: '图片', name: 'id', width: 90, sortable:false,formatter: function(value, options, row){
                    var url_image = null;
                    $.ajax({
                        url:baseURL + "find_img",
                        data:"id=" + value,
                        type:"get",
                        async: false,
                        success:function (r) {
                            if(r.code == 200){
                                url_image = r.list1[0].path;
                            }else{
                                alert(r.msg);
                                return;
                            }
                        }
                    });
                    return   "<image src='"+fileURL+url_image+"' style='width: 160px;height: 80px;'/>";
                }
            },
			{ label: '商品名称', name: 'name', width: 75, sortable:false},
			{ label: '商品单价', name: 'price', width: 90,sortable:false },
			{ label: '商品库存', name: 'stock', width: 90,sortable:false },
            { label: '单位', name: 'spec', width: 60, sortable:false},
            { label: '月销量', name: 'monthSale', width: 60, sortable:false},
            { label: '总销量', name: 'sale', width: 60, sortable:false},
			{ label: '是否上架', name: 'open', width: 50, formatter: function(value, options, row){
				return value === 1 ?
					'<span class="label label-success">上架</span>' :
					'<span class="label label-danger">下架</span>';
			},sortable:false},
			{ label: '操作', name: 'operation', width:50,formatter: operateMenu,sortable:false}
        ],
		viewrecords: true,
        height: screen.height * 0.55,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    add_category();
});

var vm;
vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            name: null
        },
        showList: false,
        showup: true,
        showImage: false,
        url1: null,
        title: null,
        category: null,
        app: {
            deleteStatus: 0
        },
        list_category: null
    },
    methods: {
        product_open:function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            $.get(baseURL + "product_open", "id=" + id, function (r) {
                var page = $("#jqGrid").jqGrid('getGridParam', 'page');
                vm.reload(page);
            });
        },
        product_down:function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            $.get(baseURL + "product_down", "id=" + id, function (r) {
                var page = $("#jqGrid").jqGrid('getGridParam', 'page');
                vm.reload(page);
            });
        },
        upload_img: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.title = id;
            vm.showList = false;
            vm.showup = false;
            vm.showImage = true;
            var list1 = new Array();
            var list2 = new Array();
            var list3 = new Array();
            $.ajax({
                url: baseURL + "find_img",
                data: "id=" + id,
                type: "get",
                async: false,
                success: function (r) {
                    if (r.code == 200) {
                        list1 = r.list1;
                        list2 = r.list2;
                        list3 = r.list3;
                    } else {
                        alert(r.msg);
                        return;
                    }
                }
            });
            var arr1 = [];
            var arr1_ = [];
            var arr2 = [];
            var arr2_ = [];
            var arr3 = [];
            var arr3_ = [];
            for (var i = 0; i < list1.length; i++) {
                var outImage = {
                    caption: null,
                        downloadUrl: null,
                        width: "120px",
                        key: null
                }
                var url = fileURL + list1[i].path;
                outImage.downloadUrl = url;
                // noinspection JSValidateTypes
                outImage.caption = list1[i].path.substring(37);
                outImage.key = list1[i].path;
                arr1.push(url);
                arr1_.push(outImage);
            }
            for (var i = 0; i < list2.length; i++) {
                var outImage = {
                    caption: null,
                    downloadUrl: null,
                    width: "120px",
                    key: null,
                }
                var url = fileURL + list2[i].path;
                outImage.downloadUrl = url;
                // noinspection JSValidateTypes
                outImage.caption = list2[i].path.substring(37);
                outImage.key = list2[i].path;
                arr2.push(url);
                arr2_.push(outImage);
            }
            for (var i = 0; i < list3.length; i++) {
                var outImage = {
                    caption: null,
                    downloadUrl: null,
                    width: "120px",
                    key: null,
                }
                var url = fileURL + list3[i].path;
                outImage.downloadUrl = url;
                // noinspection JSValidateTypes
                outImage.caption = list3[i].path.substring(37);
                outImage.key = list3[i].path;
                arr3.push(url);
                arr3_.push(outImage);
            }
            $("#file-4").empty();
            $("#file-4").fileinput({
                uploadExtraData: {id: id, type: 1},
                uploadUrl: baseURL + 'upload',
                allowedFileExtensions: ['jpg', 'png', 'gif', 'jpeg'],
                previewFileType: "image",
                browseClass: "btn btn-success",
                browseLabel: "Pick Image",
                browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
                removeClass: "btn btn-danger",
                removeLabel: "Delete",
                removeIcon: "<i class=\"glyphicon glyphicon-trash\"></i> ",
                uploadClass: "btn btn-info",
                uploadLabel: "Upload",
                uploadIcon: "<i class=\"glyphicon glyphicon-upload\"></i> ",
                initialPreview: arr1,
                initialPreviewAsData: true,
                initialPreviewConfig: arr1_,
                deleteUrl: baseURL + "delete_pic"
            });
            $("#file-zh1").empty();
            $("#file-zh1").fileinput({
                uploadExtraData: {id: id, type: 2},
                uploadUrl: baseURL + 'upload',
                allowedFileExtensions: ['jpg', 'png', 'gif', 'jpeg'],
                initialPreview: arr2,
                initialPreviewAsData: true,
                initialPreviewConfig: arr2_,
                deleteUrl: baseURL + "delete_pic"
            });
            $("#file-zh2").empty();
            $("#file-zh2").fileinput({
                uploadExtraData: {id: id, type: 3},
                uploadUrl: baseURL + 'upload',
                allowedFileExtensions: ['jpg', 'png', 'gif', 'jpeg'],
                initialPreview: arr3,
                initialPreviewAsData: true,
                initialPreviewConfig: arr3_,
                deleteUrl: baseURL + "delete_pic"
            });
        },
        upload_image: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
        },
        query: function () {
            vm.reload(1);
        },
        add: function () {
            vm.showList = true;
            vm.showup = false;
            vm.showImage = false;
            vm.title = "新增";
            vm.app = {deleteStatus: 0};
        },
        update: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = true;
            vm.showup = false;
            vm.showImage = false;
            vm.title = "修改";
            vm.getApp(id);

        },
        del: function () {
            var id = getSelectedRows();
            if (id == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.get(baseURL + "product_delete", "id=" + id, function (r) {
                    if (r.code != 200) {
                        alert(r.msg);
                        return;
                    }
                    if (r.code == 200) {
                        alert(r.msg);
                        var page = $("#jqGrid").jqGrid('getGridParam', 'page');
                        vm.reload(page);
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
            vm.app.addDate = null;
            vm.app.lastTime = null;
            var url = vm.app.id == null ? "product_add" : "update_product";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.app),
                success: function (r) {
                    if (r.code != 200) {
                        alert(r.msg);
                        return;
                    }
                    if (r.code == 200) {
                        alert(r.msg, function () {
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
            $.get(baseURL + "select_product", "id=" + id, function (r) {
                if (r.code != 200) {
                    alert(r.msg);
                    return;
                }
                vm.app = r.app;
                vm.category = r.cateType;
                if (vm.app.open == 1)
                    vm.app.open = "上架";
                if (vm.app.open == 2)
                    vm.app.open = "下架";
                if (vm.app.addDate != null)
                    vm.app.addDate = formatDate(vm.app.addDate);
                if (vm.app.lastTime != null)
                    vm.app.lastTime = formatDate(vm.app.lastTime);
            });
        },
        reload: function (p) {
            vm.showup = true;
            vm.showList = false;
            vm.showImage = false;
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
                page: p
            }).trigger("reloadGrid");
        },
        validator: function () {
            if (isBlank(vm.app.name)) {
                alert("商品名称不能为空");
                return true;
            }
        }
    }
});

function add_category() {
    $.get(baseURL + "product_select_category", function(r){
        if (r.code != 200) {
            alert(r.msg);
            return;
        }
        vm.list_category = r.list;
    });
}
/**
 * DataGrid行操作
 * */
function operateMenu(cellvalue, options, rowObject) {
    var aid = '\"'+rowObject.id+'\"';
    var result = "";
    var detailBtn = "<a class='btn btn-default' data-toggle='modal' data-target='#modal_app_info' onclick='openDetail(" + aid + ")'>详情</a>";
    result += detailBtn;
    return result;
}

function openDetail(aid) {
    vm.getApp(aid);
}

function formatDate(arr, options, rowObject) {
    if(arr != null){
        var str = "";
        for(var i = 0; i< arr.length; i++){
            if(i<2){
                str += arr[i]+'-';
            }
            if(i==2){
                str += arr[i]+' ';
            }
            if(i>2 && i<5){
                str += arr[i]+':';
            }
            if(i == 5){
                str += arr[i];
            }
        }
    }

    return str;
}

function formatURL(value, options, rowObject) {
    var token = localStorage.getItem('token');
    var result = value + '?token=' + token + '&setToken=true';
    if(!(value.startsWith("http://") || (value.startsWith("https://"))) ) result="http://"+result;
    return '<a href="' + result + '" target="_blank">' + value + '</a>';
}
