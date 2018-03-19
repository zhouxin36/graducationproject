$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'activity_list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', index: "id", width: 130, key: true , sortable:false},
			{ label: '图片', name: 'picId', width: 75, sortable:false,formatter: function(value, options, row){
                    var url_image = null;
                    $.ajax({
                        url:baseURL + "find_img_activity",
                        data:"id=" + value,
                        type:"get",
                        async: false,
                        success:function (r) {
                            if(r.code == 200){
                                url_image = r.data.pic.path;
                            }else{
                                alert(r.msg);
                                return;
                            }
                        }
                    });
                    return   "<image src='"+fileURL+url_image+"' style='width: 355px;height: 160px;'/>";
                }
			},
            { label: '详细介绍', name: 'remark', width: 60, sortable:false},
            { label: 'url', name: 'url', width: 130, sortable:false}
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
    $("#form_activity").ajaxForm(function(data){
        if(data.code== 200){
            alert("提交成功！");
            vm.reload(1);
        }else {
            alert("提交失败！");
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			name: null
		},
        formUrl:null,
		showList: true,
		title:null,
        app:{
            remark:null,
			deleteStatus:0
		}
	},
	methods: {
		query: function () {
			vm.reload(1);
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.app = {deleteStatus:0};

			var div = $("<div class=\"col-sm-2 control-label\" >图片:</div>");
			var inp = $("<input class=\"col-sm-10\" id=\"file-4\" name=\"file\" type=\"file\">");
			$("#image1").append(div).append(inp);
            $("#file-4").fileinput({
                overwriteInitial: true,
                maxFileSize: 15000,
                showClose: false,
                showCaption: false,
                showBrowse: true,
                showUpload:false,
                showRemove:false,
                browseOnZoneClick: true,
                msgErrorClass: 'alert alert-block alert-danger',
                initialPreview: "../images/1.jpg",
                initialPreviewAsData: true,
                allowedFileExtensions: ["jpg", "png", "gif","jpeg"]
            });
		},
		del: function () {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
                $.get(baseURL + "activity_delete","id="+id, function(r){
					if(r.code == 200){
						alert(r.msg, function(){
                            var page = $("#jqGrid").jqGrid('getGridParam','page');
						   vm.reload(page);
						});
					}else{
						alert(r.msg);
					}
                });
			});
		},
		saveOrUpdate: function () {
            $("#form_activity").submit();
            $("#image1").empty();
            // $.ajax({
			// 	type: "POST",
			//     url: baseURL + url,
             //    contentType: "application/json",
			//     data: JSON.stringify(vm.app),
			//     success: function(r){
             //        if (r.permissionCheck) {
             //            alert(r.msg);
             //            return;
             //        }
			//     	if(r.code === 0){
			// 			alert('操作成功', function(){
             //                var page = $("#jqGrid").jqGrid('getGridParam','page');
			// 				vm.reload(page);
			// 			});
			// 		}else{
			// 			alert(r.msg);
			// 		}
			// 	}
			// });
		},
		getApp: function(id){
			$.get(baseURL + "app/"+id+"/query", function(r){
				if (r.permissionCheck) {
                    alert(r.msg);
                    return;
				}
				vm.app = r.app;
			});
		},
		reload: function (p) {
			vm.showList = true;
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name},
                page:p
            }).trigger("reloadGrid");
            $("#image1").empty();
		},
        validator: function () {

        }
	}
});

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
