/* ============== UAS =================*/

function writeUasToken(uasToken) {
    if(uasToken) {
        localStorage.setItem("token", uasToken);
    }
}
function setCookie(name,value,days)
{
    var exp = new Date();
    exp.setTime(exp.getTime() + days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

var setToken = getUrlParameter("setToken");
if(setToken === "true") {
    var tokenFromUrl = getUrlParameter("token");
    if(tokenFromUrl) {
        setCookie("uas_token",tokenFromUrl,1);
        writeUasToken(tokenFromUrl);
    }
}

function checkNoLoginStatus(jqXHR, textStatus, errorThrown){
    //用户没有登录信息, 重定向到指定页面
    if(jqXHR.status == 494){
        parent.location.href = jqXHR.responseText;
    }
}

/* ============== UAS =================*/

//jqGrid的配置信息
// $.jgrid.defaults.width = 1000;
// $.jgrid.defaults.responsive = true;
// $.jgrid.defaults.styleUI = 'Bootstrap';

//工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
};
T.p = url;

//请求前缀
var baseURL = "/graducation/";

var fileURL = "http://localhost:8080/images/";
// var fileURL = "http://localhost:10000/graducation/images/";
//退款Id
var refundId = null;

//登录token
var token = localStorage.getItem("token");

// //jquery全局配置
// $.ajaxSetup({
// 	dataType: "json",
// 	cache: false,
//     headers: {
//         "token": token
//     },
//     error: checkNoLoginStatus
// });
//
// //jqgrid全局配置
// $.extend($.jgrid.defaults, {
//     ajaxGridOptions : {
//         headers: {
//             "token": token
//         },
//         error: checkNoLoginStatus
//     }
// });


//重写alert
window.alert = function(msg, callback){
    $('.panel-tool-close').hide();
	parent.layer.alert(msg, function(index){
		parent.layer.close(index);
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}


//重写confirm式样框
window.confirm = function(msg, callback){
	parent.layer.confirm(msg, {btn: ['确定','取消']},
	function(){//确定事件
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//选择一条记录
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("请选择一条记录");
    	return ;
    }
    
    var selectedIDs = grid.getGridParam("selarrrow");
    if(selectedIDs.length > 1){
    	alert("只能选择一条记录");
    	return ;
    }
    
    return selectedIDs[0];
}

//选择多条记录
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
        alert("请选择一条记录");
        return ;
    }

    var selectedIDs = grid.getGridParam("selarrrow");
    if(selectedIDs.length > 1){
        alert("只能选择一条记录");
        return ;
    }

    return selectedIDs[0];
}

//判断是否为空
function isBlank(value) {
    return !value || !/\S/.test(value)
}