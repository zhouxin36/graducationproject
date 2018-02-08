$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'activity_list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', index: "id", width: 130, key: true },
			{ label: '图片', name: 'picId', width: 75, sortable:false,formatter: function(value, options, row){
                return value === 0 ?
              '<span class="label label-success">正常</span>' :
            '<span class="label label-danger">禁用</span>';
                }
			},
            { label: '详细介绍', name: 'remark', width: 60, sortable:false}
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

            var btnCust = '<button type="button" class="btn btn-secondary" title="Add picture tags" ' +
                'onclick="alert(\'Call your custom code here.\')">' +
                '<i class="glyphicon glyphicon-tag"></i>' +
                '</button>';
            $("#file-4").fileinput({
                overwriteInitial: true,
                maxFileSize: 15000,
                showClose: false,
                showCaption: false,
                showBrowse: false,
                browseOnZoneClick: true,
                removeLabel: '',
                removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
                removeTitle: 'Cancel or reset changes',
                elErrorContainer: '#kv-avatar-errors-2',
                msgErrorClass: 'alert alert-block alert-danger',
                defaultPreviewContent: '<img src="../images/1.jpg" alt="Your Avatar"><h6 class="text-muted">Click to select</h6>',
                layoutTemplates: {main2: '{preview} ' +  btnCust + ' {remove} {browse}'},
                allowedFileExtensions: ["jpg", "png", "gif","jpeg"]
            });
		},
		update: function () {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
			
			vm.getApp(id);
		},
		del: function () {
			var id = getSelectedRows();
			if(id == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
                $.get(baseURL + "app/"+id+"/del", function(r){
                    if (r.permissionCheck) {
                        alert(r.msg);
                        return;
                    }
					if(r.code == 0){
						alert('操作成功', function(){
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
            if(vm.validator()){
                return ;
            }
            $("#form_activity").submit();
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
