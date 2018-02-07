<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>商品页面</title>


<link type="text/css" href="../css/optstyle.css" rel="stylesheet" />
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet"
	type="text/css" />
<link href="../basic/css/demo.css" rel="stylesheet" type="text/css" />
<link href="../AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="../../static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.cityselect.js"></script>
<script src="../../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="../../static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript" src="../js/script.js"></script>
<script src="./lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="./js/xadmin.js"></script>
<script type="text/javascript">
	$(function() {

		right_dao();
		$("#city").citySelect({
			nodata : "none",
			required : false
		});
		 //改变购物车的数字
       	$.post("../../Order/getSorderById",function(data,status){
       		
       		$("#J_MiniCartNum").html(data.map.list.length);
       		$("#shop_cart_number").html(data.map.list.length);
       	});
		
		
//获得URL的参数
    	
        function GetQueryString(name)
            {
       var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
       var r = window.location.search.substr(1).match(reg);
      if(r!=null)return  unescape(r[2]); return null;
    }
		
		
		// build_right_top();

		//页面一加载，判断用户是否登录，根据结果改变左上角的样式
		$.post("../../User/check_login", function(msg, status) {
			if (msg.code == 100) {
				
				//用户登录成功后，检查URL是否有id
			   	 var id = GetQueryString("id");
			   	 if(id!=null && id.toString().length>=1)
			   	 {
			   		//ID不为空，可以添加到用户的足迹
			   		    $.post("../../FootMark/checkTodayFootMark","productId="+id,function(msg){
			                    //允许添加
			   		    	if(msg.code==100){
			   		    		   $.post("../../FootMark/saveFootMark","productId="+id,function(msg){
			   		    			 
			   		    		   });
			   		    	} 
			   		    	
			   		    });
			   
			}
				
				
				
				
				
				
				//登录成功
				console.log(msg);
				
				
				
				
				
				//alert(msg.map.name);
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

		var id = $("#request_product_id").val();
		if(id=="")
			id=1;
		$.ajax({
			url : "../../Product/SelectProductById",
			data : "id=" + id,
			type : "post",
			success : function(msg) {
				show_view(msg);
				$.ajax({
					url:"../../Product/guessyoulike",
					data:"type="+msg.map.product.categoryId,
					type:"post",
					success:function(msg){
						//猜你喜欢
						guess_like(msg.map.msg1.map.pageInfo);
						look_and_look(msg.map.msg2.map.pageInfo);
					},
					error:function(){
						layer.msg("请求失败！", {
							icon : 5,
							time : 2000
						});
					}
				});
			},
			error : function(msg) {
				layer.msg("请求失败！", {
					icon : 5,
					time : 2000
				});
			}
		});
		
		request_comment(0, id, 1);

	});
	
	
	//右侧导航条的Jquery
	function right_dao(){
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
		$("#Navigation_right_collection").click(function() {
					$(location).prop('href','../test/test_main.jsp?action=collection');
		});
	}
	function show_view(msg) {
		$
				.ajax({
					url : "../../Pic/selectPicByProductId",
					data : "id=" + msg.map.product.id,
					type : "post",
					async : false,
					success : function(ms) {
						//全屏图片
						var ul = $("#thumblist");
						//中屏图片
						var ul2 = $(".slides");
						if (ms.code == 100) {
							var j = 0;
							for (var i = 0; ms.map.list_pic[i] != null; i++) {
								if (ms.map.list_pic[i].type == 2) {
									var li = $("<li></li>");
									var li2 = $("<li></li>");
									var img2 = $("<img/>");
									img2.attr("src", ms.map.p
											+ ms.map.list_pic[i].path);
									if (j == 0) {
										img2.attr("title", "pic");
										var div = $("#abjlsdfh");
										li.addClass("tb-selected");
										j++;
										var a_a = $("<a></a>");
										a_a.attr("href", ms.map.p
												+ ms.map.list_pic[i].path);
										var img_img = $("<img/>");
										img_img.attr("src", ms.map.p
												+ ms.map.list_pic[i].path);
										img_img.attr("rel", ms.map.p
												+ ms.map.list_pic[i].path);
										img_img.attr("alt", "细节展示放大镜特效");
										img_img.addClass("jqzoom");
										a_a.append(img_img);
										div.append(a_a);
									}
									li2.append(img2);
									ul2.append(li2);
									var div = $("<div></div>");
									div.addClass("tb-pic tb-s40");
									var a = $("<a></a>");
									a.attr("href", "#");
									var img = $("<img/>");
									img.attr("src", ms.map.p
											+ ms.map.list_pic[i].path);
									img.attr("mid", ms.map.p
											+ ms.map.list_pic[i].path);
									img.attr("big", ms.map.p
											+ ms.map.list_pic[i].path);
									a.append(img);
									div.append(a);
									li.append(div);
									ul.append(li);
								}
								var xiang = $(".twlistNews");
								if (ms.map.list_pic[i].type == 3) {
									var img = $("<img/>");
									img.attr("src", ms.map.p
											+ ms.map.list_pic[i].path);
									xiang.append(img);
								}

							}
						} else {
							var li2 = $("<li></li>");
							var img2 = $("<img/>");
							img2.attr("src", ms.map.p + "1.jpg");
							img2.attr("title", "pic");
							li2.append(img2);
							ul2.append(li2);
							var div = $("#abjlsdfh");
							var li = $("<li></li>");
							li.addClass("tb-selected");
							j++;
							var a_a = $("<a></a>");
							a_a.attr("href", ms.map.p + "1.jpg");
							var img_img = $("<img/>");
							img_img.attr("src", ms.map.p + "1.jpg");
							img_img.attr("rel", ms.map.p + "1.jpg");
							img_img.attr("alt", "细节展示放大镜特效");
							img_img.addClass("jqzoom");
							a_a.append(img_img);
							div.append(a_a);
							var div = $("<div></div>");
							div.addClass("tb-pic tb-s40");
							var a = $("<a></a>");
							a.attr("href", "#");
							var img = $("<img/>");
							img.attr("src", ms.map.p + "1.jpg");
							img.attr("mid", ms.map.p + "1.jpg");
							img.attr("big", ms.map.p + "1.jpg");
							a.append(img);
							div.append(a);
							li.append(div);
							ul.append(li);
							var xiang = $(".twlistNews");
							var img = $("<img/>");
							img.attr("src", ms.map.p + "1.jpg");
							xiang.append(img);
						}
						fangdajing();
					},
					error : function(msg) {
						layer.msg('查找图片失败!', {
							icon : 2,
							time : 2000
						});
					}
				});
		var remark = msg.map.product.remark.split('/');
		var xremark = msg.map.product.xremark.split('/');
		for(var i = 0;remark[i] != null;i++){
			var li_tittle = $("<li></li>");
			li_tittle.append(remark[i]);
			$("#J_AttrUL").append(li_tittle);
		}
		for(var i = 0;xremark[i] != null;i++){
			var li_tittle = $("<li></li>");
			li_tittle.append(xremark[i]);
			$("#J_AttrUL").append(li_tittle);
		}
		
		$("#c1").append(msg.map.product.name);
		$("#c3").append(msg.map.product.price);
		$("#c5").append(msg.map.product.sale);
		$("#c6").append(msg.map.product.monthSale);
		$('.flexslider').flexslider({
			animation : "slide",
			start : function(slider) {
				$('body').removeClass('loading');
			}
		});

	}

	function fangdajing() {
		$(".jqzoom").imagezoom();
		$("#thumblist li a").click(
				function() {
					$(this).parents("li").addClass("tb-selected").siblings()
							.removeClass("tb-selected");
					$(".jqzoom").attr('src', $(this).find("img").attr("mid"));
					$(".jqzoom").attr('rel', $(this).find("img").attr("big"));
				});
	}

	//评论列表
	function product_comments(msg) {
		var ul = $("#product_comments");
		ul.empty();
           
		for (var i = 0; msg.list[i] != null; i++) {
			var li = $("<li></li>");
			li.addClass("am-comment");

			//商品头像
			var a1 = $("<a></a>");
			a1.attr("href", "#");
			var img1 = $("<img/>");
			img1.addClass("am-comment-avatar");
			img1.attr("src", "../images/hwbn40x40.jpg");
			a1.append(img1);
			li.append(a1);

			var div1 = $("<div></div>");
			div1.addClass("am-comment-main");

			var header = $("<header></header>");
			header.addClass("am-comment-meta");
			var div2 = $("<div></div>");
			div2.addClass("am-comment-meta");
			var a2 = $("<a></a>");
			a2.attr("href", "#link-to-user");
			a2.addClass("am-comment-author");
			if(msg.list[i].anonymous == 1){
				a2.append("匿名");
				a2.addClass("btn-danger");
			}else{
				$.ajax({
					url : "../../Comment/request_product",
					data : "id=" + msg.list[i].userId,
					type : "post",
					async : false,
					success : function(msg) {
						a2.append(msg.map.user.name);
						a2.addClass("btn-info");
					},
					error : function(msg) {
						layer.msg("请求失败！", {
							icon : 2,
							time : 2000
						});

					}
				});
			}
				
			var time = $("<time></time>");
			time.append(getMyDate(msg.list[i].commentTime));
			div2.append(a2).append("评论于").append(time);
			header.append(div2);
			div1.append(header);

			var div3 = $("<div></div>");
			div3.addClass("am-comment-bd");
			var div4 = $("<div></div>");
			div4.addClass("tb-rev-item");
			div4.attr("data-id", "255776406962");
			var div5 = $("<div></div>");
			div5.addClass("J_TbcRate_ReviewContent tb-tbcr-content");
			var div6 = $("<div></div>");
			div6.addClass("tb-r-act-bar");
			div5.append(msg.list[i].comment);
			//div6.append("颜色分类：柠檬黄&nbsp;&nbsp;尺码：S");
			div4.append(div5)/* .append(div6) */;
			div3.append(div4);
			div1.append(div3);
			li.append(div1);
			ul.append(li);

		}

	}
	//显示评价个数
	function comment_num(msg) {
		
		var ulc = $("#comment_num");
		ulc.empty();
		var total = msg.list[2]+msg.list[1]+msg.list[0];
		var num = $("#request_favour_comment").val();
		$("#good_comment_point").empty();
		$("#good_comment_point").append(Math.round(msg.list[2]/total*100)+"%");
		var lic1 = $("<li></li>");
		lic1.addClass("tb-taglist-li");
		var divc1 = $("<div></div>");
		divc1.addClass("comment-info");
		if(num == 0)
			divc1.addClass("btn-warning");
		var spc1 = $("<a></a>");
		spc1.append("全部评价 "+total);
		spc1.addClass("all_comment");
		spc1.attr("val", 0);
		divc1.append(spc1);
		lic1.append(divc1);
		ulc.append(lic1);

		var lic2 = $("<li></li>");
		lic2.addClass("tb-taglist-li");
		var divc2 = $("<div></div>");
		divc2.addClass("comment-info");
		if(num == 3)
			divc2.addClass("btn-warning");
		var spc2 = $("<a></a>");
		spc2.append("好评  "+msg.list[2]);
		spc2.addClass("all_comment");
		spc2.attr("val", 3);
		divc2.append(spc2);
		lic2.append(divc2);
		ulc.append(lic2);

		var lic3 = $("<li></li>");
		lic3.addClass("tb-taglist-li");
		var divc3 = $("<div></div>");
		divc3.addClass("comment-info");
		if(num == 2)
			divc3.addClass("btn-warning");
		var spc3 = $("<a></a>");
		spc3.append("中评 "+msg.list[1]);
		spc3.addClass("all_comment");
		spc3.attr("val", 2);
		divc3.append(spc3);
		lic3.append(divc3);
		ulc.append(lic3);

		var lic4 = $("<li></li>");
		lic4.addClass("tb-taglist-li");
		var divc4 = $("<div></div>");
		divc4.addClass("comment-info");
		if(num == 1)
			divc4.addClass("btn-warning");
		var spc4 = $("<a></a>");
		spc4.append("差评 "+msg.list[0]);
		spc4.addClass("all_comment");
		spc4.attr("val", 1);
		divc4.append(spc4);
		lic4.append(divc4);
		ulc.append(lic4);

		$(".all_comment").each(function() {
			var v = $(this).attr("val");
			$(this).click(function() {
				request_comment(v ,$("#request_product_id").val() ,1);
			});
		});
	}

	//请求评分
	function request_comment(v, id, pagenum) {
		$("#request_favour_comment").val(v);
		$.ajax({
			url : "../../Comment/request_comment",
			data : "id=" + id + "&favour=" + v + "&pagenum=" + pagenum,
			type : "post",
			success : function(msg) {
				/* if(msg.code==200){
					return ;
				} */
				product_comments(msg.map.pageInfo);
				comment_num(msg.map);
				to_page_info(msg);
				to_page_nav(msg); 
			},
			error : function(msg) {
				layer.msg("请求失败！", {
					icon : 2,
					time : 2000
				});

			}
		});
	}

	//猜你喜欢
	function guess_like(msg) {
		var ul = $(".boxes");
		for (var i = 0; msg.list[i] != null; i++) {
			if($("#request_product_id").val() == msg.list[i].id){
				continue;
			}
				
			var li = $("<li></li>");
			var div = $("<div></div>");
			div.addClass("i-pic limit");
			var img = $("<img/>");
			img.attr("style","width:200px;height:160px;")
			$.ajax({
				url:"../../Pic/selectPicByProductType",
				data:"id="+msg.list[i].id+"&type="+1,
				type:"post",
				async:false,
				success:function(ms){
					if(ms.code == 100)
						img.attr("src", ms.map.path+ms.map.pic_list[0].path);
					else 
						img.attr("src", ms.map.path+"1.jpg");
				},
				error:function(msg){
					img.attr("src", ms.map.path+"1.jpg");
				}
			});
			var p1 = $("<p></p>");
			p1.append(msg.list[i].name);
			var a1 = $("<a></a>");
			a1.attr("href","introduction.jsp?id="+msg.list[i].id);
			var p2 = $("<p></p>");
			p2.append(msg.list[i].price+"元");
			p2.addClass("price fl");
			a1.append(p1);
			div.append(img).append(a1).append(p2);
			li.append(div);
			ul.append(li);
		}
	}
	
	
	/* 
	<li>
	<div class="p-img">
		<a href="#"> <img class="" src="../images/browse1.jpg">
		</a>
	</div>
	<div class="p-name">
		<a href="#"> 【三只松鼠_开口松子】零食坚果特产炒货东北红松子原味 </a>
	</div>
	<div class="p-price">
		<strong>￥35.90</strong>
	</div>
	</li> */
	//看了又看
	function look_and_look(msg) {
		var ul = $("#look_and_look");
		for (var i = 0; msg.list[i] != null; i++) {
			if($("#request_product_id").val() == msg.list[i].id)
				continue;
			var li = $("<li></li>");
			var div = $("<div></div>");
			div.addClass("p-img");
			var img = $("<img/>");
			img.attr("style","width:200px;height:160px;")
			$.ajax({
				url:"../../Pic/selectPicByProductType",
				data:"id="+msg.list[i].id+"&type="+1,
				type:"post",
				async:false,
				success:function(ms){
					if(ms.code == 100)
						img.attr("src", ms.map.path+ms.map.pic_list[0].path);
					else 
						img.attr("src", ms.map.path+"1.jpg");
				},
				error:function(msg){
					img.attr("src", ms.map.path+"1.jpg");
				}
			});
			var p1 = $("<p></p>");
			p1.append(msg.list[i].name);
			p1.addClass("p-name");
			var a1 = $("<a></a>");
			a1.attr("href","introduction.jsp?id="+msg.list[i].id);
			var p2 = $("<p></p>");
			p2.append(msg.list[i].price+"元");
			p2.addClass("p-price");
			a1.append(p1);
			div.append(img).append(a1).append(p2);
			li.append(div);
			ul.append(li);
		}
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
	//页码
	function to_page_nav(msg) {
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
			request_comment($("#request_favour_comment").val(), $("#request_product_id").val(), 1);
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
				request_comment($("#request_favour_comment").val(), $("#request_product_id").val(), msg.map.pageInfo.pageNum - 1);
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
				request_comment($("#request_favour_comment").val(), $("#request_product_id").val(), item);
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
				request_comment($("#request_favour_comment").val(), $("#request_product_id").val(), msg.map.pageInfo.pageNum + 1);
			});
		}
		lastPageLi.click(function() {
			request_comment($("#request_favour_comment").val(), $("#request_product_id").val(), msg.map.pageInfo.pages);
		});
		//
		nav.append(ul).appendTo("#page_nav");
	}
	
	//时间格式转换
	function getMyDate(str) {
		var oDate = new Date(str), oYear = oDate.getFullYear(), oMonth = oDate
				.getMonth() + 1, oDay = oDate.getDate(), oHour = oDate
				.getHours(), oMin = oDate.getMinutes(), oSen = oDate
				.getSeconds(), oTime = oYear + '-' + getzf(oMonth) + '-'
				+ getzf(oDay) + ' ' + getzf(oHour) + ':' + getzf(oMin) + ':'
				+ getzf(oSen);//最后拼接时间  
		return oTime;
	};
	//补0操作  
	function getzf(num) {
		if (parseInt(num) < 10) {
			num = '0' + num;
		}
		return num;
	}
</script>


<script type="text/javascript" src="../basic/js/quick_links.js"></script>

<script type="text/javascript"
	src="../AmazeUI-2.4.2/assets/js/amazeui.js"></script>
<script type="text/javascript" src="../js/jquery.imagezoom.min.js"></script>
<script type="text/javascript" src="../js/jquery.flexslider.js"></script>
<script type="text/javascript" src="../js/list.js"></script>


</head>
<body>
	<input type="hidden" value="${param.id }" id="request_product_id" />
	<input type="hidden" value="1" id="request_favour_comment" />

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



	<ol class="am-breadcrumb am-breadcrumb-slash">
		<li><a href="../home/home.jsp">首页</a></li>
		<li><a href="../home/search.jsp">搜索</a></li>
		<li class="am-active">内容</li>
	</ol>
	<div class="scoll">
		<div class="flexslider">
			<ul class="slides">
			</ul>
		</div>
	</div>

	<!--放大镜-->

	<div class="item-inform">
		<div class="clearfixLeft" id="clearcontent">

			<div class="box">

				<div class="tb-booth tb-pic tb-s310" id="abjlsdfh">
					<!-- <a href="../images/01.jpg"><img src="../images/01_mid.jpg"
						alt="细节展示放大镜特效" rel="../images/01.jpg" class="jqzoom" /></a> -->
				</div>
				<ul class="tb-thumb" id="thumblist">

				</ul>
			</div>

			<div class="clear"></div>
		</div>

		<div class="clearfixRight">

			<!--规格属性-->
			<!--名称-->
			<div class="tb-detail-hd">
				<h1 id="c1"></h1>
			</div>
			<div class="tb-detail-list">
				<!--价格-->
				<div class="tb-detail-price">
					<li class="price iteminfo_price">
						<dt>价格</dt>
						<dd>
							<em>¥</em><b id="c3" class="sys_item_price"></b>
						</dd>
					</li>
					<div class="clear"></div>
				</div>

				<!-- --地址
				<dl class="iteminfo_parameter freight">
					<dt>配送至</dt>
					<div class="iteminfo_freprice">
						<div class="am-form-content address">
							<div id="city">
								<select class="prov"></select> <select class="city"
									disabled="disabled"></select> <select class="dist"
									disabled="disabled"></select>
							</div>
						</div>
						<div class="pay-logis">
							快递<b class="sys_item_freprice">10</b>元
						</div>
					</div>
				</dl> -->
				<div class="clear"></div>

				<!--销量-->
				<ul class="tm-ind-panel">
					<li class="tm-ind-item tm-ind-sellCount canClick">
						<div class="tm-indcon">
							<span class="tm-label">月销量</span><span id="c5" class="tm-count"></span>
						</div>
					</li>
					<li class="tm-ind-item tm-ind-sumCount canClick">
						<div class="tm-indcon">
							<span class="tm-label">累计销量</span><span id="c6" class="tm-count"></span>
						</div>
					</li>
				</ul>
				<div class="clear"></div>

				<!--各种规格-->
				<dl class="iteminfo_parameter sys_item_specpara">
					<dt class="theme-login">
						<div class="cart-title">
							可选规格<span class="am-icon-angle-right"></span>
						</div>
					</dt>
					<dd>
						<!--操作页面-->

						<div class="theme-popover-mask"></div>

						<div class="theme-popover">
							<div class="theme-span"></div>
							<div class="theme-poptit">
								<a href="javascript:;" title="关闭" class="close">×</a>
							</div>
							<div class="theme-popbod dform">
								<form class="theme-signin" name="loginform" action=""
									method="post">

									<div class="theme-signin-left">

										<!-- <div class="theme-options">
											<div id="c8" class="cart-title">型号</div>
											<ul>
												<li class="sku-line selected">原味<i></i></li>
												<li class="sku-line">奶油<i></i></li>
												<li class="sku-line">炭烧<i></i></li>
												<li class="sku-line">咸香<i></i></li>
											</ul>
										</div>
										<div class="theme-options">
											<div class="cart-title">包装</div>
											<ul>
												<li class="sku-line selected">手袋单人份<i></i></li>
												<li class="sku-line">礼盒双人份<i></i></li>
												<li class="sku-line">全家福礼包<i></i></li>
											</ul>
										</div> -->
										<div class="theme-options">
											<div class="cart-title number">数量</div>
											<dd>
												<input id="min" class="am-btn am-btn-default" name=""
													type="button" value="-" /> <input id="text_box" name=""
													type="text" value="1" style="width: 30px;" /> <input
													id="add" class="am-btn am-btn-default" name=""
													type="button" value="+" /> 
											</dd>

										</div>
										<div class="clear"></div>

										<div class="btn-op">
											<div class="btn am-btn am-btn-warning">确认</div>
											<div class="btn close am-btn am-btn-warning">取消</div>
										</div>
									</div>
									<div class="theme-signin-right">
										<div class="img-info">
											<img src="../images/songzi.jpg" />
										</div>
										<div class="text-info">
											<span class="J_Price price-now">¥39.00</span> <span
												id="Stock" class="tb-hidden">库存<span class="stock">1000</span>件
											</span>
										</div>
									</div>

								</form>
							</div>
						</div>

					</dd>
				</dl>
				<div class="clear"></div>
			</div>

			<div class="pay">
				<div class="pay-opt">
					<a href="home.html"><span class="am-icon-home am-icon-fw">首页</span></a>
					<a><span class="am-icon-heart am-icon-fw">收藏</span></a>

				</div>
				<li>
					<div class="clearfix tb-btn tb-btn-buy theme-login">
					
					</div>
				</li>
				<li>
					<div class="clearfix tb-btn tb-btn-basket theme-login">
						<a id="LikBasket" title="加入购物车" href="javasript:(0);"><i></i>加入购物车</a>
					</div>
				</li>
			</div>

		</div>

		<div class="clear"></div>

	</div>


	<div class="clear"></div>


	<!-- introduce-->

	<div class="introduce">
		<div class="browse">
			<div class="mc">
				<ul id="look_and_look">
					<div class="mt">
						<h2>看了又看</h2>
					</div>

					

				</ul>
			</div>
		</div>
		<div class="introduceMain">
			<div class="am-tabs" data-am-tabs>
				<ul class="am-avg-sm-3 am-tabs-nav am-nav am-nav-tabs">
					<li class="am-active"><a href="#"> <span
							class="index-needs-dt-txt">宝贝详情</span></a></li>

					<li><a href="#"> <span class="index-needs-dt-txt">全部评价</span></a>

					</li>

					<li><a href="#"> <span class="index-needs-dt-txt">猜你喜欢</span></a>
					</li>
				</ul>

				<div class="am-tabs-bd">

					<div class="am-tab-panel am-fade am-in am-active">
						<div class="J_Brand">

							<div class="attr-list-hd tm-clear">
								<h4>产品参数：</h4>
							</div>
							<div class="clear"></div>
							<ul id="J_AttrUL">
							<!-- 	<li title="">产品类型:&nbsp;烘炒类</li>
								<li title="">原料产地:&nbsp;巴基斯坦</li>
								<li title="">产地:&nbsp;湖北省武汉市</li>
								<li title="">配料表:&nbsp;进口松子、食用盐</li>
								<li title="">产品规格:&nbsp;210g</li>
								<li title="">保质期:&nbsp;180天</li>
								<li title="">产品标准号:&nbsp;GB/T 22165</li>
								<li title="">生产许可证编号：&nbsp;QS4201 1801 0226</li>
								<li title="">储存方法：&nbsp;请放置于常温、阴凉、通风、干燥处保存</li>
								<li title="">食用方法：&nbsp;开袋去壳即食</li> -->
							</ul>
							<div class="clear"></div>
						</div>

						<div class="details">
							<div class="attr-list-hd after-market-hd">
								<h4>商品细节</h4>
							</div>
							<div class="twlistNews"></div>
						</div>
						<div class="clear"></div>

					</div>

					<div class="am-tab-panel am-fade">

						<div class="actor-new">
							<div class="rate">
								<strong id="good_comment_point"></strong><br> <span>好评度</span>
							</div>
							<!-- <dl>
								<dt>买家印象</dt>
								<dd class="p-bfc">
									<q class="comm-tags"><span>味道不错</span><em>(2177)</em></q> <q
										class="comm-tags"><span>颗粒饱满</span><em>(1860)</em></q> <q
										class="comm-tags"><span>口感好</span><em>(1823)</em></q> <q
										class="comm-tags"><span>商品不错</span><em>(1689)</em></q> <q
										class="comm-tags"><span>香脆可口</span><em>(1488)</em></q> <q
										class="comm-tags"><span>个个开口</span><em>(1392)</em></q> <q
										class="comm-tags"><span>价格便宜</span><em>(1119)</em></q> <q
										class="comm-tags"><span>特价买的</span><em>(865)</em></q> <q
										class="comm-tags"><span>皮很薄</span><em>(831)</em></q>
								</dd>
							</dl> -->
						</div>
						<div class="clear"></div>
						<div class="tb-r-filter-bar">
							<ul class=" tb-taglist am-avg-sm-4" id="comment_num">

							</ul>
						</div>
						<div class="clear"></div>

						<ul class="am-comments-list am-comments-list-flip"
							id="product_comments">

						</ul>

						<div class="clear"></div>

						<!--分页 -->
						<div class="page">
							<div id="page_info"></div>
							<div id="page_nav"></div>
						</div>
						<div class="clear"></div>

						<div class="tb-reviewsft">
							<div class="tb-rate-alert type-attention">
								购买前请查看该商品的 <a href="#" target="_blank">购物保障</a>，明确您的售后保障权益。
							</div>
						</div>

					</div>

					<div class="am-tab-panel am-fade">
						<div class="like">
							<ul class="am-avg-sm-2 am-avg-md-3 am-avg-lg-4 boxes">

							</ul>
						</div>
						<div class="clear"></div>


					</div>

				</div>

			</div>

			<div class="clear"></div>

			
		</div>

	</div>
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
				<div id="broadcast" class="item">
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
				<div>
					我
				</div>
			</div>
			<div id="shopCart-content" class="nav-content">
				<div class="nav-con-close">
					<i class="am-icon-angle-right am-icon-fw"></i>
				</div>
				<div>
					购物车
				</div>
			</div>
			<div id="asset-content" class="nav-content">
				<div class="nav-con-close">
					<i class="am-icon-angle-right am-icon-fw"></i>
				</div>
				<div>
					资产
				</div>

				<div class="ia-head-list">
					<a href="#" target="_blank" class="pl">
						<div class="num">0</div>
						<div class="text">优惠券</div>
					</a>
					<a href="#" target="_blank" class="pl">
						<div class="num">0</div>
						<div class="text">红包</div>
					</a>
					<a href="#" target="_blank" class="pl money">
						<div class="num">￥0</div>
						<div class="text">余额</div>
					</a>
				</div>

			</div>
			<div id="foot-content" class="nav-content">
				<div class="nav-con-close">
					<i class="am-icon-angle-right am-icon-fw"></i>
				</div>
				<div>
					足迹
				</div>
			</div>
			<div id="brand-content" class="nav-content">
				<div class="nav-con-close">
					<i class="am-icon-angle-right am-icon-fw"></i>
				</div>
				<div>
					收藏
				</div>
			</div>
			<div id="broadcast-content" class="nav-content">
				<div class="nav-con-close">
					<i class="am-icon-angle-right am-icon-fw"></i>
				</div>
				<div>
					充值
				</div>
			</div>
		</div>

</body>
<script type="text/javascript">
$(function(){
	
	//点击加入购物车
	    $("#LikBasket").click(function(){
	    	
	    	$.post("../../User/check_login",function(msg){
	    		if(msg.code==200){
	    			layer.msg("您还没有登录！", {
						icon : 2,
						time : 2000
					});

	    			return ;
	    		}else{
	    		
	    			var number = $("#text_box").val();
	    			var id = $("#request_product_id").val();
	    			if(id=="")
	    				id=1;
	    			
	    			
	    		 $.post("../../Order/addProductToSorder","productId="+id+"&number="+number,function(data,status){
		        		if(data.code==100)
		        			layer.msg("成功加入购物车,请到购物车查看", {
		    					icon : 1,
		    					time : 2000
		    				});
		        	  }); 
	    			
	    	    	
	    		}
	    	});
	    	
	    	
	    	
	    });

	
	
});
</script>
</html>