$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'admin_list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', index: "id", width: 130, key: true },
			{ label: '登录名', name: 'login', width: 75, sortable:false},
			{ label: '上次登录ip', name: 'lastIp', width: 90,sortable:false },
            { label: '上次登录时间', name: 'lastTime', width: 100, sortable:false, formatter: formatDate},
			{ label: '身份', name: 'system', width: 30, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-success">普通管理员</span>' :
					'<span class="label label-danger">系统管理员</span>';
			},sortable:false},
			{ label: '注册时间', name: 'regTime', width: 100,formatter:formatDate}
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
            root: "page.list"
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
                $.get(baseURL + "admin_delete","id="+id ,function(r){
                    if (r.code != 200) {
                        alert(r.msg);
                        return;
                    }
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
            if(vm.validator()){
                return ;
            }
            var url = vm.app.id == null ? "admin_add" : "update_admin";
			$.ajax({
				type: "POST",
			    url: baseURL +url,
                contentType: "application/json",
			    data: JSON.stringify(vm.app),
			    success: function(r){
                    if (r.code != 200) {
                        alert(r.msg);
                        return;
                    }
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
			$.get(baseURL + "admin_query","id="+id, function(r){
				if (r.code != 200) {
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
            if(isBlank(vm.app.password)){
                alert("密码不能为空");
                return true;
            }
            if(isBlank(vm.app.login)){
                alert("登陆名不能为空");
                return true;
            }
            if(isBlank(vm.app.system)){
                alert("身份不能为空");
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
    var aid = rowObject.id;
    var result = "";
    var detailBtn = "<a class='btn btn-default' data-toggle='modal' data-target='#modal_app_info' onclick='openDetail(" + aid + ")'>详情</a>";
    result += detailBtn;
    return result;
}

function openDetail(aid) {
    vm.getApp(aid);
}

function formatDate(arr, options, rowObject) {
    var str = "";
    if(arr != null){
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
