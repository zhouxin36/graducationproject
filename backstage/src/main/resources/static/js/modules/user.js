$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'user_list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', index: "id", width: 130, key: true },
			{ label: '用户名', name: 'name', width: 75, sortable:false},
			{ label: '性别', name: 'sex', width: 30,sortable:false,formatter:function (value, options, row) {
                    return value === 0 ?
                        '<span class="label label-success">男</span>' :
                        '<span class="label label-danger">女</span>';
                } },
            { label: '用户手机号', name: 'phone', width: 60, sortable:false},
            { label: '邮箱', name: 'email', width: 75, sortable:false},
            { label: '上次登录时间', name: 'lastTime',index: "last_time", width: 75, sortable:false,formatter:formatDate},
			{ label: '状态', name: 'isabled', width: 30, formatter: function(value, options, row){
				return value === 1 ?
					'<span class="label label-success">正常</span>' :
					'<span class="label label-danger">禁用</span>';
			},sortable:false},
			{ label: '操作', name: 'operation', width:30,formatter: operateMenu,sortable:false}
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
		showList: true,
		title:null,
        app:{
		}
	},
	methods: {
		query: function () {
			vm.reload(1);
		},
        update_password: function(){
			vm.showList = false;
			vm.title = "修改密码";
			vm.app.id= getSelectedRows()+"";
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
                $.get(baseURL + "user_delete","id="+id, function(r){
                    if (r.code == 500) {
                        alert(r.msg);
                        return;
                    }
                    alert('操作成功', function(){
                        var page = $("#jqGrid").jqGrid('getGridParam','page');
                        vm.reload(page);
                    });
                });
			});
		},
        disable: function () {
            var id = getSelectedRows();
            if(id == null){
                return ;
            }

            confirm('确定要禁用选中的用户？', function(){
                $.get(baseURL + "user_disable","id="+id, function(r){
                    if (r.code == 500) {
                        alert(r.msg);
                        return;
                    }
                    alert('操作成功', function(){
                        var page = $("#jqGrid").jqGrid('getGridParam','page');
                        vm.reload(page);
                    });
                });
            });
        },
        able: function () {
            var id = getSelectedRows();
            if(id == null){
                return ;
            }

            confirm('确定要启用选中的用户？', function(){
                $.get(baseURL + "user_able","id="+id, function(r){
                    if (r.code == 500) {
                        alert(r.msg);
                        return;
                    }
                    alert('操作成功', function(){
                        var page = $("#jqGrid").jqGrid('getGridParam','page');
                        vm.reload(page);
                    });
                });
            });
        },
		saveOrUpdate: function () {
            if(vm.validator()){
                return ;
            }

			$.ajax({
				type: "POST",
			    url: baseURL + "update_password",
                contentType: "application/json",
			    data: JSON.stringify(vm.app),
			    success: function(r){
			    	if(r.code == 200){
						alert(r.msg, function(){
                            var page = $("#jqGrid").jqGrid('getGridParam','page');
							vm.reload(page);
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		getApp: function(id){
			$.get(baseURL + "user/"+id+"/query", function(r){
			    if(r.code == 500){
			        alert(r.msg);
			        return;
                }
				vm.app = r.app;
			    if(vm.app.birthday != null)
                    vm.app.birthday = formatDate(vm.app.birthday,null,null);
                if(vm.app.regTime != null)
                    vm.app.regTime = formatDate(vm.app.regTime,null,null);
                if(vm.app.lastTime != null)
                    vm.app.lastTime = formatDate(vm.app.lastTime,null,null);
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
            if(isBlank(vm.app.password)){
                alert("密码不能为空");
                return true;
            }
            if(isBlank(vm.app.repassword)){
                alert("重复密码不能为空");
                return true;
            }

            if(vm.app.password != vm.app.repassword){
                alert("密码不一致");
                return true;
            }

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
