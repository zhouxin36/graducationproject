<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>搜索页面</title>
<link rel="stylesheet"
	href="../bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet"
	type="text/css" />
<link href="../AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet"
	type="text/css" />

<link href="../basic/css/demo.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="./css/font.css">
<link rel="stylesheet" href="./css/xadmin.css">

<link href="../css/seastyle.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../basic/js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="../js/script.js"></script>
<script src="./lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="./js/xadmin.js"></script>
</head>
<script type="text/javascript">
	function show_view(pageNum) {
		var category = $("#category1").val();
		var brand = $("#brand1").val();
		var down = $("#down1").val();
		var up = $("#up1").val();
		var sort = $("#sort1").val();
		
		var name = $("#searchInput").val();
		$.post("../../Product/SelectProductByName", "name=" + name + "&category=" + category + "&brand=" + brand
				+ "&down=" + down + "&up=" + up + "&sort=" + sort + "&pn=" + pageNum, function(
				msg, status) {
			if (msg.code == 100) {
				//将搜索出的信息显示
				build_search_table(msg);
				//建立右下角的分页
				build_product_nav(msg);
				to_page_info(msg);
				console.log(msg);
				build_price(msg);
				build_category();
				build_brand(1);
				//建立经典搭配
				$.post("../../Product/selectClassifyProduct", "name=" + name,
						function(msg, status) {
							build_classic_Collocation(msg);
						});
			} else {
				layer.msg("查询失败，没有该商品！", {
					icon : 2,
					time : 2000
				});
			}
		});

	}
	function build_category(){
		var li = $("#li_0");
		li.empty();
		var dl2 = $("<dl></dl>");
		dl2.attr("id","select2");
		var dt2 = $("<dt></dt>");
		dt2.addClass("am-badge am-round");
		dt2.append("类别");
		var div2 = $("<div></div>");
		div2.addClass("dd-conent");
		var dd2_1 = $("<dd></dd>");
		dd2_1.addClass("select-all selected category");
		dd2_1.attr("category",null);
		var  a2_1= $("<a></a>");
		a2_1.attr("href","#");
		a2_1.append("全部");
		dd2_1.append(a2_1);
		div2.append(dd2_1);
		$.ajax({
			url:"../../Product/select_category",
			type:"post",
			async:false,
			success:function(msg){
				for(var i=0; msg.map.category[i]!=null;i++){
					var dd2_2 = $("<dd></dd>");
					dd2_2.attr("category",msg.map.category[i].id);
					dd2_2.addClass("category");
					var  a2_2= $("<a></a>");
					a2_2.attr("href","#");
					a2_2.append(msg.map.category[i].type);
					dd2_2.append(a2_2);
					div2.append(dd2_2);
				}
			
			},
			error:function(){
				layer.msg("类型查找失败！", {
					icon : 2,
					time : 2000
				});
			}
			
		});
		dl2.append(dt2).append(div2);
		li.append(dl2);
		$(".category").click(function (){
			var t = $(this);
			$("#category1").val(t.attr("category"));
			li.find("dd").each(function (){
				$(this).attr("class","category");
			});
			t.attr("class","select-all selected category");
			to_page(1);
		});
	}
	
	
	
	function build_price(msg){
		//价格 
		var li2 = $("#li_2");
		li2.empty();
		var dl2 = $("<dl></dl>");
		dl2.attr("id","select2");
		var dt2 = $("<dt></dt>");
		dt2.addClass("am-badge am-round");
		dt2.append("价格");
		var div2 = $("<div></div>");
		div2.addClass("dd-conent");
		var dd2_1 = $("<dd></dd>");
		dd2_1.addClass("select-all selected price_price");
		dd2_1.attr("down",null);
		dd2_1.attr("up",null);
		var  a2_1= $("<a></a>");
		a2_1.attr("href","#");
		a2_1.append("全部");
		dd2_1.append(a2_1);
		div2.append(dd2_1);
		if(msg.map.max-1000>msg.map.min){
			var num = Math.ceil((msg.map.max-msg.map.min)/5);
			for(var i = 0; i<5;i++){
				var front = msg.map.min + num*i;
				var behind = msg.map.min + num*(i+1);
				var dd2_2 = $("<dd></dd>");
				dd2_2.attr("down",front);
				dd2_2.attr("up",behind);
				dd2_2.addClass("price_price");
				var  a2_2= $("<a></a>");
				a2_2.attr("href","#");
				a2_2.append(front+"-"+behind);
				dd2_2.append(a2_2);
				div2.append(dd2_2);
			}
			
		}else{
			var dd2_2 = $("<dd></dd>");
			dd2_2.attr("down",msg.map.min);
			dd2_2.attr("up",msg.map.max);
			dd2_2.addClass("price_price");
			var  a2_2= $("<a></a>");
			a2_2.attr("href","#");
			a2_2.append(msg.map.min+"-"+msg.map.max);
			dd2_2.append(a2_2);
			div2.append(dd2_2);
		}
		dl2.append(dt2).append(div2);
		li2.append(dl2);
		
		$(".price_price").click(function (){
			var t = $(this);
			$("#down1").val(t.attr("down"));
			$("#up1").val(t.attr("up"));
			var li2 = $("#li_2");
			li2.find("dd").each(function (){
					$(this).attr("class","price_price");
				});
			t.attr("class","select-all selected price_price");
			to_page(1);
			});
			
	}
	
	//根据页码跳转
	function to_page(pn) {
		var category = $("#category1").val();
		var brand = $("#brand1").val();
		var down = $("#down1").val();
		var up = $("#up1").val();
		var sort = $("#sort1").val();
		
		var name = $("#searchInput").val();
		if (name == " ")
			name = productname;
		$.post("../../Product/SelectProductByName", "name=" + name + "&category=" + category + "&brand=" + brand
				+ "&down=" + down + "&up=" + up + "&sort=" + sort + "&pn=" + pn, function(msg, status) {
			console.log(msg);
			if(msg.code == 100){
				//将搜索出的信息显示
				build_search_table(msg);
				//建立右下角的分页
				build_product_nav(msg);
				to_page_info(msg);
			}else{
				layer.msg("没有该查询结果！", {
					icon : 2,
					time : 2000
				});
			}
			
		});
	}
	
	//页码
	function to_page_info(msg) {
		$("#page_info").empty();
		$("#page_info").append(
				"当前第" + msg.map.pageInfo.pageNum + "页,一共有"
						+ msg.map.pageInfo.pages + "页,一共有"
						+ msg.map.pageInfo.total + "条数据");
		pages = msg.map.pageInfo.pages + 1;
	}
	
	//解析并显示分页条信息
	function build_product_nav(msg) {
		$("#page_nav").empty();
		//nav
		var nav = $("<nav></nav>");

		//ul
		var ul = $("<ul></ul>").addClass("pagination");

		//首页
		var firstPageLi = $("<li></li>").append(
				$("<a></a>").append("首页").attr("href", "#"));
		ul.append(firstPageLi);
		firstPageLi.click(function() {
			$("#page_message").val(1);
			to_page(1);
		});
		//上一页
		var prePageLi = $("<li></li>").append(
				$("<a></a>").append("&laquo;").attr("href", "#"));
		ul.append(prePageLi);
		if (msg.map.pageInfo.hasPreviousPage == false) {
			firstPageLi.addClass("disabled");
			prePageLi.addClass("disabled");
		} else {
			prePageLi.click(function() {
				$("#page_message").val(msg.map.pageInfo.pageNum - 1);
				to_page(msg.map.pageInfo.pageNum - 1);
			});
		}
		//页码
		$.each(msg.map.pageInfo.navigatepageNums, function(index, item) {
			var numLi = $("<li></li>").append(
					$("<a></a>").append(item).attr("href", "#"));
			if (msg.map.pageInfo.pageNum == item) {
				numLi.addClass("active");
			}
			numLi.click(function() {
				$("#page_message").val(item);
				to_page(item);
			});
			ul.append(numLi);
		});

		//下一页
		var nextPageLi = $("<li></li>").append(
				$("<a></a>").append("&raquo;").attr("href", "#"));
		ul.append(nextPageLi);
		//末页
		var lastPageLi = $("<li></li>").append(
				$("<a></a>").append("末页").attr("href", "#"))
		ul.append(lastPageLi);
		if (msg.map.pageInfo.hasNextPage == false) {
			nextPageLi.addClass("disabled");
			lastPageLi.addClass("disabled");
		} else {
			nextPageLi.click(function() {
				$("#page_message").val(msg.map.pageInfo.pageNum + 1);
				to_page(msg.map.pageInfo.pageNum + 1);
			});
		}
		lastPageLi.click(function() {
			$("#page_message").val(msg.map.pageInfo.pages);
			to_page(msg.map.pageInfo.pages);
		});
		//
		nav.append(ul).appendTo("#page_nav");
	}
	//将搜索出的信息显示
	function build_search_table(msg) {
		$("#main_box").empty();

		var list = msg.map.pageInfo.list;
		//		 console.log("方便测试");
		//		  console.log(list);
		for (var i = 0; i < list.length; i++) {
			var li = $("<li></li>");
			var div = $("<div></div>").addClass("i-pic limit");
			// 点击商品图片跳转到商品的介绍界面，记得要在JSP的最后面加上商品的ID
			var img = "";
			$.ajax({
				type : "post",
				dataType : "json",
				url : "../../Pic/selectPicByProductType",
				data : "id=" + list[i].id + "&type=1",
				async : false,
				success : function(msg) {
					if (msg.code == 100) {
						img = $("<img></img>").attr("src",
								msg.map.path + msg.map.pic_list[0].path);
					} else {
						img = $("<img></img>").attr("src",
								msg.map.path + "1.jpg");
					}
				}
			});
			var a = $("<a></a>").attr("href",
					"../home/introduction.jsp?id=" + list[i].id).attr("target", "_blank");
			a.append(img);
			var p1 = $("<p></p>").addClass("title fl").append(list[i].name);
			var p2 = $("<p></p>").addClass("price fl").append("<b>¥</b>")
					.append("<strong></strong>").append(list[i].price);
			var p3 = $("<p></p>").addClass("number fl").append("销量").append(
					"<span></span>").append(list[i].monthSale);
			div.append(a).append(p1).append(p2).append(p3);
			li.append(div);
			$("#main_box").append(li);
		}
	}
	
	//建立经典搭配模块
	function build_classic_Collocation(msg) {
		var div = $("#classic_Collocation2");
		div.empty();
		var list = msg.map.List;
		if (list.length == 0)
			return;
		for (var i = 0; i < 3; i++) {
			var li = $("<li></li>");
			var div_son = $("<div></div>").addClass("i-pic check");
			// var img = $("<img></img>").attr("src","../images/cp.jpg");
			var img = "";
			$.ajax({
				type : "post",
				dataType : "json",
				url : "../../Pic/selectPicByProductType",
				data : "id=" + list[i].id + "&type=1",
				async : false,
				success : function(msg) {
					if (msg.code == 100) {
						img = $("<img></img>").attr("src",
								msg.map.path + msg.map.pic_list[0].path);
					} else {
						img = $("<img></img>").attr("src",
								msg.map.path + "1.jpg");
					}
				}
			});
			var name = list[i].name.split('/');
			var p1 = $("<p></p>").addClass("check-title").append(name[0]);
			var p2 = $("<p></p>").addClass("price fl").append("<b>¥</b>")
					.append(list[i].price);
			var p3 = $("<p></p>").addClass("number fl").append("销量").append(
					"<span></span>").append(list[i].sale);
			div_son.append(img).append(p1).append(p2).append(p3);
			li.append(div_son);
			div.append(li);
		}
	}
	//建立上方的种类 ，品牌 ，选购热点
	function build_brand(number) {
		var cate = "";
		if (number == 1) {
			cate = "品牌";
		}
		var name = $("#searchInput").val();
		if (name == "") {
			name = $("#productName2").val();
		}
		$
				.ajax({
					type : "post",
					data : "name=" + name,
					url : "../../Product/SelectCategory",
					async : false,
					dataType : "json",
					success : function(msg) {
						console.log(msg);
						var list = msg.map.list;
						var list_select_list = $("#li_" + number);
						list_select_list.empty();
						var dl = $("<dl></dl>").attr("id", "select1");
						var dt = $("<dt></dt>").addClass("am-badge am-round")
								.append(cate);
						var div_dd_conent = $("<div></div>").addClass(
								"dd-conent");
						var dd_all = $("<dd></dd>").addClass(
								"select-all selected brand_brand").append(
								"<a href='javascript:void(0);'>全部</a>");
						dd_all.attr("brand",null);
						div_dd_conent.append(dd_all);
						var arr = new Array();
						$
								.each(
										list,
										function(index, item) {
											if (number == 1) {
												var name = item.name.split('/');
												for (var i = 0; i < 10; i++) {
													if (null == arr[i]) {
														arr[i] = name[0];
														var a = $(
																"<a href='javascript:void(0);'></a>")
																.append(name[0]);
														var dd = $("<dd></dd>").append(a).addClass("brand_brand");
														dd.attr("brand",name[0]);
														div_dd_conent.append(dd);
														break;
													} else {
														if (arr[i] == name[0])
															break;
														else
															continue;
													}
												}
											} 
										});
						/* else if (number == 2)
							var a = $(
									"<a href='javascript:void(0);'></a>")
									.append(
											item.price
													+ "-"
													+ (parseInt(item.price) + parseInt("300"))); */
						
						dl.append(dt).append(div_dd_conent);
						list_select_list.append(dl);

					}
				});
		$(".brand_brand").click(function (){
			var t = $(this);
			$("#brand1").val(t.attr("brand"));
			var list_select_list = $("#li_" + number);
			list_select_list.find("dd").each(function (){
				$(this).attr("class","brand_brand");
			});
			t.attr("class","select-all selected brand_brand");
			to_page(1);
		});
	}
	//右侧导航条的Jquery
	function right_dao() {
		//跳转购物车
		$("#Navigation_right_shopcart").click(function() {
			$(location).prop('href', '../home/shopcart.jsp');
		});
		//跳转我的资产
		$("#Navigation_right_assets").click(function() {
			$(location).prop('href', '../test/test_main.jsp?action=bill');
		});
		//跳转我的充值
		$("#broadcast").click(function() {
			$(location).prop('href', '../test/test_main.jsp?action=pay');
		});
		//跳转我的足迹
		$("#Navigation_right_foot").click(function() {
			$(location).prop('href', '../test/test_main.jsp?action=foot');
		});
		//跳转我的收藏
		$("#Navigation_right_collection").click(
				function() {
					$(location).prop('href',
							'../test/test_main.jsp?action=collection');
				});
	}
	$(function() {
		show_view(1);
		right_dao();
		$("#ai-topsearch").click(function() {
			show_view(1);
		});
		$("#sale_Sort").click(function (){
			var sort = $("#sort1").val();
			if(3 == sort){
				$("#sort1").val("4");
			}else {
				$("#sort1").val("3");
			}
			to_page(1);
		});
		$("#price_Sort").click(function (){
			var sort = $("#sort1").val();
			if(1 == sort){
				$("#sort1").val("2");
			}else{
				$("#sort1").val("1");
			}
			to_page(1);
		});
		
		
	});
	$(function() {
		//获得URL的参数
		function getUrlParam(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
			var r = window.location.search.substr(1).match(reg); //匹配目标参数
			if (r != null)
				return unescape(r[2]);
			return null; //返回参数值
		}
		//页面一加载，判断用户是否登录，根据结果改变左上角的样式
		$.post("../../User/check_login", function(msg, status) {
			if (msg.code == 100) {
				//登录成功
				var a = $("<a></a>").attr("href", "#").addClass("h").append(
						"欢迎你! " + msg.map.name);
				var logout = $("<a></a>").attr("href", "#").append(
						"&nbsp;  &nbsp;  退出登录");
				var div = $("#login_status");
				div.append(a).append(logout);
				//点击退出登录
				logout.click(function() {
					/* if (confirm("确定退出登录?")) {
						$.post("../../User/logout", function(data) {
							$(location).prop("href", "../home/home.jsp");
						});
					} */
					 layer.msg('确定退出登录?', {
						  time: 0 //不自动关闭
						  ,btn: ['确定', '取消']
						  ,yes: function(index){
							  $.post("../../User/logout",function(data){
						           $(location).prop("href","../home/home.jsp");
					              });
							  layer.close(index);
						  }
						});
				});
				//改变购物车的数字
				$.post("../../Order/getSorderById", function(data, status) {

					$("#J_MiniCartNum").html(data.map.list.length);
					$("#shop_cart_number").html(data.map.list.length);
				});
			} else {
				//登录失败
				var a = $("<a></a>").attr("href", "../home/login.jsp")
						.addClass("h").append("亲，请登录  ");
				var b = $("<a></a>").attr("href", "../home/register.jsp")
						.append("免费注册");
				var div = $("#login_status");
				div.append(a).append(b);
			}
		});
	});
</script>
<body>
	<input type="hidden" id="category1" value="">
	<input type="hidden" id="brand1" value="">
	<input type="hidden" id="down1" value="">
	<input type="hidden" id="up1" value="">
	<input type="hidden" id="sort1" value="1">

	<input id="productName" type="hidden" value="${param.productname}" />
	<input id="productName2" type="hidden" value="${param.productname}" />
	<!--顶部导航条 -->
	<div class="am-container header">
		<ul class="message-l">
			<div class="topMessage">
				<div class="menu-hd" id="login_status">
					<!-- <a href="#"  class="h">亲，请登录</a>
							<a href="#" >免费注册</a> -->
				</div>
			</div>
		</ul>
		<ul class="message-r">
			<div class="topMessage home">
				<div class="menu-hd">
					<a href="../home/home.jsp" target="_top" class="h">商城首页</a>
				</div>
			</div>
			<div class="topMessage my-shangcheng">
				<div class="menu-hd MyShangcheng">
					<a href="../test/test_main.jsp" target="_top"><i
						class="am-icon-user am-icon-fw"></i>个人中心</a>
				</div>
			</div>
			<div class="topMessage mini-cart">
				<div class="menu-hd">
					<a id="mc-menu-hd" href="../home/shopcart.jsp" target="_top"><i
						class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong
						id="J_MiniCartNum" class="h">0</strong></a>
				</div>
			</div>
			<div class="topMessage favorite">
				<!-- <div class="menu-hd"><a href="../test/test_collection.jsp" target="_top"><i class="am-icon-heart am-icon-fw"></i><span>收藏夹</span></a></div> -->
		</ul>
	</div>


	<!--悬浮搜索框-->

	<div class="nav white">
		<div class="logo">
			<img src="../images/logo.png" />
		</div>
		<div class="logoBig">
			<li><img src="../images/logobig.png" /></li>
		</div>

		<div class="search-bar pr">


			<input id="searchInput" type="text" placeholder="搜索"
				autocomplete="off" value="${param.productname }">
			<button id="ai-topsearch" class="submit am-btn" index="1"
				type="submit">搜索</button>

		</div>
	</div>

	<div class="clear"></div>
	<b class="line"></b>
	<div class="search">
		<div class="search-list">
			<div class="nav-table">
				<div class="long-title">
					<span class="all-goods">全部分类</span>
				</div>
				<div class="nav-cont">

					<div class="nav-extra">
						<i class="am-icon-user-secret am-icon-md nav-user"></i><b></b> <a
							href="../test/test_main.jsp" target="_blank">个人中心 </a> <i
							class="am-icon-angle-right" style="padding-left: 10px;"></i>
					</div>
				</div>
			</div>


			<div class="am-g am-g-fixed">
				<div class="am-u-sm-12 am-u-md-12">
					<div class="theme-popover">
						<div id="correlation_Search" class="searchAbout"></div>
						<ul class="select">
							<p class="title font-normal"></p>
							<div class="clear"></div>
							<li class="select-result">
								<dl>
									<dt>已选</dt>
									<dd class="select-no"></dd>
									<p class="eliminateCriteria">清除</p>
								</dl>
							</li>
							<div class="clear"></div>
							<li  id="li_0" class="select-list">
							</li>
							<li id="li_1" class="select-list">
								<!--<dl id="select1">
										<dt class="am-badge am-round">品牌</dt>	
									
										 <div class="dd-conent">										
											<dd class="select-all selected"><a href="#">全部</a></dd>
											<dd><a href="#">百草味</a></dd>
											<dd><a href="#">良品铺子</a></dd>
											<dd><a href="#">新农哥</a></dd>
											<dd><a href="#">楼兰蜜语</a></dd>
											<dd><a href="#">口水娃</a></dd>
											<dd><a href="#">考拉兄弟</a></dd>
										 </div>
						
									</dl> -->
							</li>
							<li id="li_2" class="select-list">
							</li>
							<!-- <li id="li_3" class="select-list">
									<dl id="select3">
										<dt class="am-badge am-round">选购热点</dt>
										<div class="dd-conent">
											<dd class="select-all selected"><a href="#">全部</a></dd>
											<dd><a href="#">手剥松子</a></dd>
											<dd><a href="#">薄壳松子</a></dd>
											<dd><a href="#">进口零食</a></dd>
											<dd><a href="#">有机零食</a></dd>
										</div>
									</dl>
								</li> -->

						</ul>
						<div class="clear"></div>
					</div>
					<div class="search-content">
						<div class="sort">
							<!-- <li class="first"><a id="comprehensive_Sort" title="综合" href="javascript:void(0)">综合排序</a></li> -->
							<li><a title="销量" id="sale_Sort" href="javascript:void(0)">销量排序</a></li>
							<li><a title="价格" id="price_Sort" href="javascript:void(0)">价格优先</a></li>
							<!-- <li class="big"><a  id="comment_Sort" title="评价" href="javascript:void(0)" >评价为主</a></li> -->
						</div>
						<div class="page">
							<div id="page_info"></div>
							<div id="page_nav"></div>
						</div>
						<div class="clear"></div>

						<ul id="main_box"
							class="am-avg-sm-2 am-avg-md-3 am-avg-lg-4 boxes">

						</ul>

					</div>
					<div id="classic_Collocation" class="search-side">

						<div class="side-title">经典搭配</div>
						<div id="classic_Collocation2"></div>
						<!-- <li>classic_Collocation
									<div class="i-pic check">
										<img src="../images/cp.jpg" />
										<p class="check-title">萨拉米 1+1小鸡腿</p>
										<p class="price fl">
											<b>¥</b>
											<strong>29.90</strong>
										</p>
										<p class="number fl">
											销量<span>1110</span>
										</p>
									</div>
								</li>
								<li>
									<div class="i-pic check">
										<img src="../images/cp2.jpg" />
										<p class="check-title">ZEK 原味海苔</p>
										<p class="price fl">
											<b>¥</b>
											<strong>8.90</strong>
										</p>
										<p class="number fl">
											销量<span>1110</span>
										</p>
									</div>
								</li>
								<li>
									<div class="i-pic check">
										<img src="../images/cp.jpg" />
										<p class="check-title">萨拉米 1+1小鸡腿</p>
										<p class="price fl">
											<b>¥</b>
											<strong>29.90</strong>
										</p>
										<p class="number fl">
											销量<span>1110</span>
										</p>
									</div>
								</li>
 -->
					</div>
					<div class="clear"></div>


				<!-- 	<div id="page_nav" class="am-pagination am-pagination-right">

					</div> -->


				</div>
			</div>
		</div>

	</div>

	<!--引导 -->
	<div class="navCir">
		<li><a href="home.html"><i class="am-icon-home "></i>首页</a></li>
		<li><a href="sort.html"><i class="am-icon-list"></i>分类</a></li>
		<li><a href="shopcart.html"><i
				class="am-icon-shopping-basket"></i>购物车</a></li>
		<li><a href="../person/index.html"><i class="am-icon-user"></i>我的</a></li>
	</div>

	<!--菜单 -->
	<div class=tip>
		<div id="sidebar">
			<div id="wrap">
				<div id="prof" class="item "></div>
				<div id="shopCart " class="item ">
					<a href="../home/shopcart.jsp"> <span class="message "></span>
					</a>
					<div id="Navigation_right_shopcart">
						<p>购物车</p>
						<p class="cart_num " id="shop_cart_number">0</p>
					</div>
				</div>
				<div id="Navigation_right_assets">
					<div id="asset " class="item ">

						<a href="# "> <span class="view "></span>
						</a>
						<div class="mp_tooltip ">
							我的资产 <i class="icon_arrow_right_black "></i>
						</div>
					</div>
				</div>
				<div id="Navigation_right_foot">
					<div id="foot " class="item ">
						<a href="# "> <span class="zuji "></span>
						</a>
						<div class="mp_tooltip ">
							我的足迹 <i class="icon_arrow_right_black "></i>
						</div>
					</div>
				</div>
				<div id="Navigation_right_collection">
					<div id="brand " class="item ">
						<a href="#"> <span class="wdsc "><img
								src="../images/wdsc.png " /></span>
						</a>
						<div class="mp_tooltip ">
							我的收藏 <i class="icon_arrow_right_black "></i>
						</div>
					</div>
				</div>
				<div id="broadcast" class="item ">
					<a href="# "> <span class="chongzhi "><img
							src="../images/chongzhi.png " /></span>
					</a>
					<div class="mp_tooltip ">
						我要充值 <i class="icon_arrow_right_black "></i>
					</div>
				</div>

				<div class="quick_toggle ">
					<li class="qtitem "><a href="# "><span class="kfzx "></span></a>
						<div class="mp_tooltip ">
							客服中心<i class="icon_arrow_right_black "></i>
						</div></li>
					<!--二维码 -->
					<li class="qtitem "><a href="#none "><span
							class="mpbtn_qrcode "></span></a>
						<div class="mp_qrcode " style="display: none;">
							<img src="../images/weixin_code_145.png " /><i
								class="icon_arrow_white "></i>
						</div></li>
					<li class="qtitem "><a href="#" class="return_top "><span
							class="top "></span></a></li>
				</div>

				<!--回到顶部 -->
				<div id="quick_links_pop" class="quick_links_pop hide"></div>

			</div>

		</div>
		<div id="prof-content" class="nav-content">
			<div class="nav-con-close">
				<i class="am-icon-angle-right am-icon-fw"></i>
			</div>
			<div>我</div>
		</div>
		<div id="shopCart-content" class="nav-content">
			<div class="nav-con-close">
				<i class="am-icon-angle-right am-icon-fw"></i>
			</div>
			<div>购物车</div>
		</div>
		<div id="asset-content" class="nav-content">
			<div class="nav-con-close">
				<i class="am-icon-angle-right am-icon-fw"></i>
			</div>
			<div>资产</div>

			<div class="ia-head-list">
				<a href="#" target="_blank" class="pl">
					<div class="num">0</div>
					<div class="text">优惠券</div>
				</a> <a href="#" target="_blank" class="pl">
					<div class="num">0</div>
					<div class="text">红包</div>
				</a> <a href="#" target="_blank" class="pl money">
					<div class="num">￥0</div>
					<div class="text">余额</div>
				</a>
			</div>

		</div>
		<div id="foot-content" class="nav-content">
			<div class="nav-con-close">
				<i class="am-icon-angle-right am-icon-fw"></i>
			</div>
			<div>足迹</div>
		</div>
		<div id="brand-content" class="nav-content">
			<div class="nav-con-close">
				<i class="am-icon-angle-right am-icon-fw"></i>
			</div>
			<div>收藏</div>
		</div>
		<div id="broadcast-content" class="nav-content">
			<div class="nav-con-close">
				<i class="am-icon-angle-right am-icon-fw"></i>
			</div>
			<div>充值</div>
		</div>
	</div>
	<script type="text/javascript" src="../basic/js/quick_links.js"></script>

	<div class="theme-popover-mask"></div>
</body>
</html>