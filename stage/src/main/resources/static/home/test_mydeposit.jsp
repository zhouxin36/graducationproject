<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="../bootstrap-3.3.7-dist/css/bootstrap.css">
<script type="text/javascript"
	src="../bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	function getMyDate(str) {
		var oDate = new Date(str), oYear = oDate.getFullYear(), oMonth = oDate
				.getMonth() + 1, oDay = oDate.getDate(), oTime = oYear + '-' + getzf(oMonth) + '-'
				+ getzf(oDay);//最后拼接时间  
		return oTime;
	};
	//补0操作  
	function getzf(num) {
		if (parseInt(num) < 10) {
			num = '0' + num;
		}
		return num;
	}

	//定义全局变量
	var total;
	var pageNum;

	$(function() {
		to_page(1);
	});

	function to_page(pn) {
		$.ajax({
			url : "../../deposit/depositSelect",
			data : "pn=" + pn,
			type : "post",
			success : function(msg) {
				//解析并显示员工数据
				build_person_table(msg);
				//解析并显示分页信息
				build_person_info(msg);
				//解析并显示分页条信息
				build_person_nav(msg);
			},
			error : function() {

			}
		});
	}

	function build_person_table(msg) {
		//清空table
		$("#table tbody").empty();

		var list = msg.map.pageInfo.list;
		console.log(list);
		$.each(list, function(index, deposit) {
			var id = $("<td></td>").append(deposit.id);
			var rechargeMoney = $("<td></td>").append(deposit.rechargeMoney);
			var rechargeDate = $("<td></td>").append( getMyDate(deposit.rechargeDate));
			var issuccess = $("<td></td>").append(
					deposit.issuccess == 1 ? "成功" : "失败");
			var tr = $("<tr></tr>");
			tr.append(id).append(rechargeMoney).append(rechargeDate).append(
					issuccess).appendTo("#table tbody");
		})
	}

	function build_person_info(msg) {
		//清空page_info
		$("#page_info").empty();
		$("#page_info").append(
				"当前第" + msg.map.pageInfo.pageNum + "页，一共有"
						+ msg.map.pageInfo.pages + "页,一共有"
						+ msg.map.pageInfo.total + "条数据")
		total = msg.map.pageInfo.total;
		pageNum = msg.map.pageInfo.pageNum;
	}

	function build_person_nav(msg) {
		//清空nav
		$("#page_nav").empty();

		//nav
		var nav = $("<nav></nav>");

		//ul
		var ul = $("<ul></ul>").addClass("pagination");

		//首页
		var firstPageLi = $("<li></li>").append(
				$("<a></a>").append("首页").attr("href", "#"));
		//上一页
		var prePagelLi = $("<li></li>").append($("<a></a>").append("&laquo;"))
				.attr("href", "#");

		//判断是否为首页
		if (!msg.map.pageInfo.hasPreviousPage) {
			//禁用li
			firstPageLi.addClass("disable");
			prePagelLi.addClass("disable");
		} else {
			firstPageLi.click(function() {
				to_page(1);
			});
			prePagelLi.click(function() {
				to_page(msg.map.pageInfo.pageNum - 1);
			});
		}

		ul.append(firstPageLi);
		ul.append(prePagelLi);

		//页码
		$.each(msg.map.pageInfo.navigatepageNums, function(index, item) {
			var numLi = $("<li></li>").append(
					$("<a></a>").append(item).attr("href", "#"));
			if (msg.map.pageInfo.pageNum == item) {
				numLi.addClass("active");
			}
			numLi.click(function() {
				to_page(item);
			});
			ul.append(numLi);
		});

		//下一页
		var nextPagelLi = $("<li></li>").append($("<a></a>").append("&raquo;"))
				.attr("href", "#");
		//末页
		var lastPageLi = $("<li></li>").append(
				$("<a></a>").append("末页").attr("href", "#"));

		//判断是否为末页
		if (!msg.map.pageInfo.hasNextPage) {//禁用li
			firstPageLi.addClass("disable");
			nextPagelLi.addClass("disable");
		} else {
			nextPagelLi.click(function() {
				to_page(msg.map.pageInfo.pageNum + 1);
			});
			lastPageLi.click(function() {
				to_page(msg.map.pageInfo.pages);
			});
		}

		ul.append(nextPagelLi);
		ul.append(lastPageLi);
		nav.append(ul).appendTo("#page_nav");
	}
</script>

</head>
<body>
	<!-- 	<table class="table table-striped" style=" margin-top:75px; margin-left: 140px;">
		<colgroup>
			<col style="width: 15%;" />
			<col style="width: 25%;"/>
			<col style="width: 35%;"/>
			<col style="width: 25%;"/>
		</colgroup>
		<thead>
			<tr>
				<td>id</td>
				<td>充值金额</td>
				<td >充值日期</td>
				<td>充值状态</td>
			</tr>
		</thead>
		<tbody>
			<tr>
			    <td>1</td>
				<td>1000</td>
				<td>2017-10-28</td>
				<td>成功</td>
			</tr>
			<tr>
			    <td>1</td>
				<td>1000</td>
				<td>2017-10-28</td>
				<td>成功</td>
			</tr>
			<tr>
			    <td>1</td>
				<td>1000</td>
				<td>2017-10-28</td>
				<td>成功</td>
			</tr>
			<tr>
			    <td>1</td>
				<td>1000</td>
				<td>2017-10-28</td>
				<td>成功</td>
			</tr>
			<tr>
			    <td>1</td>
				<td>1000</td>
				<td>2017-10-28</td>
				<td>成功</td>
			</tr>
		</tbody>
	</table>
	 -->

	<div class="container" style="margin-left: 150px">
		<div class="row">
			<div class="col-md-4 ">
				<h1>我的充值记录</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table class="table table-striped" id="table">
					<thead>
						<tr>
							<td>充值记录ID</td>
							<td>充值金额</td>
							<td>充值日期</td>
							<td>充值状态</td>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6" id="page_info"></div>
			<div class="col-md-6" id="page_nav"></div>
		</div>

	</div>
</body>
</html>