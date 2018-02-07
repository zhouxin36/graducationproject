<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>

<link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet"
	type="text/css" />
<link href="../AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet"
	type="text/css" />
 <link rel="stylesheet" href="./css/layer.css">
<link href="../basic/css/demo.css" rel="stylesheet" type="text/css" />

<link href="../css/hmstyle.css" rel="stylesheet" type="text/css" />
<link href="../css/skin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./js/layer.js"></script>
<script type="text/javascript">
	var t = null;
	$(function () {
		
		//页面一加载，判断用户是否登录，根据结果改变左上角的样式...
		$
				.post(
						"../../User/check_login",
						function(msg, status) {
							if (msg.code == 100) {
								//登录成功
								console.log(msg);
								var a = $("<a></a>").attr("href", "#")
										.addClass("h").append(
												"欢迎你! " + msg.map.name);
								
								var logout  = $("<a></a>").attr("href" , "#").append("&nbsp;  &nbsp;  退出登录");
								var div = $("#login_status");
								div.append(a).append(logout);
								
								//点击退出登录
								 logout.click(function(){
								/* 	 if(confirm("确定退出登录?")){
										 $.post("../../User/logout",function(data){
									           $(location).prop("href","../home/home.jsp");
								              });
										}
									  */
									 
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
			                   	$.post("../../Order/getSorderById",function(data,status){
			                   		
			                   		$("#J_MiniCartNum").html(data.map.list.length);
			                   		$("#shop_cart_number").html(data.map.list.length);
			                   		
			                   	});
								
							} else {
								//登录失败

								var a = $("<a></a>").attr("href",
										"../home/login.jsp").addClass("h")
										.append("亲，请登录  ");
								var b = $("<a></a>").attr("href",
										"../home/register.jsp").append("免费注册");
								var div = $("#login_status");
								div.append(a).append(b);
							}

							

							
							
							//加载界面信息
							$.ajax({
								url:"../../Product/home_message",
								type:"post",
								async : false,
								success:function(msg){
									//轮播
									carousel(msg.map);
									for(var i = 0; msg.map.category_list[i] != null;i++){
										category_list(msg.map.category_list[i]);
									}
									var ul = $("#js_climit_li");
									ul.children("li").each(function() {
										$(this).mouseover(function() {
											$(this).attr("class","appliance js_toggle relative hover");
											$(this).children(".top").attr("style","display:block;");
											});
										$(this).mouseout(function() {
											$(this).attr("class","appliance js_toggle relative");
											$(this).children(".top").attr("style","display:none;");
											});
										});
									//商品展示
									show_p(msg.map,$("#f1"));
									show_p_two(msg.map,$("#f2"));
									
								},
								error:function(){
									
								}
							});
							
							

							
							var i = 0;
							//无限计时
							timeout();
							//下标点选择图片轮转
							$("#div_dd").children("div").each(function() {
								$(this).mouseover(function() {
									clearTimeout(t);
									$("#div_dd").children("div").each(function() {
										$(this).text("●");
										$(this).attr("id","left1");		
										});
									$(".am-slides").children("li").each(function() {
										$(this).attr("style","width: 1519px; float: left; display: none;");
										});
									$(this).text("○");var a = $(this).attr("class");
									var b = ".ban" + a;
									$(this).attr("id","right1");
									$(b).attr("style","width: 1519px; float: left; display: block;");
									});
								$(this).mouseout(function() {t = setTimeout(timeout,3000);});
								});
							//今日推荐
							today_rem();
							//热门活动
							//hot_activity();
							
							
						});
         //零食板块 （上面的板块）
		function show_p(msg,p) {
			//第一部分
			var div1 = $("<div></div>");
			div1.addClass("am-container");
			var div1_1 = $("<div></div>");
			div1_1.addClass("shopTitle");
			var h1_1 = $("<h4></h4>");
			var h1_2 = $("<h3></h3>");
			var div1_2 = $("<div></div>");
			div1_2.addClass("today-brands");
			for (var i = 0; msg.msg1_3.map.pageInfo.list[i] != null; i++) {
				var a1 = $("<a></a>");
				var name = msg.msg1_3.map.pageInfo.list[i].name.split('/');
				a1.append(name[0]);
				a1.attr("href", "introduction.jsp?id="+msg.msg1_3.map.pageInfo.list[i].id);
				div1_2.append(a1);
			}
			var sp = $("<span></span>");
			sp.addClass("more");
			var a1_1 = $("<a></a>");
			var i1 = $("<i></i>");
			i1.addClass("am-icon-angle-right");
			i1.attr("style", "padding-left: 10px;");
			sp.append("更多美味").append(i1);
			h1_1.append(msg.category_list[0].type);
			h1_2.append("每一个商品都有一个故事");
			div1_1.append(h1_1).append(h1_2).append(div1_2);
			div1.append(div1_1);
			p.append(div1);

			//第二部分
			var div2 = $("<div></div>");
			div2.addClass("am-g am-g-fixed floodFour");
			//第一小部分
			var div2_1 = $("<div></div>");
			div2_1.addClass("am-u-sm-5 am-u-md-4 text-one list ");
			var div2_1_1 = $("<div></div>");
			div2_1_1.addClass("word");
			for (var i = 0; msg.msg1_2.map.pageInfo.list[i] != null; i++) {
				var a2_1_1 = $("<a></a>");
				a2_1_1.addClass("outer").attr("href", "introduction.jsp?id="+msg.msg1_2.map.pageInfo.list[i].id);
				var sp2_1_1 = $("<span></span>");
				sp2_1_1.addClass("inner");
				var b2_1_1 = $("<b></b>");
				b2_1_1.addClass("text");
				var name = msg.msg1_2.map.pageInfo.list[i].name.split('/');
				b2_1_1.append(name[0]);
				//b2_1_1.append(msg.msg1_2.map.pageInfo.list[i].name);
				sp2_1_1.append(b2_1_1);
				a2_1_1.append(sp2_1_1);
				div2_1_1.append(a2_1_1);
			}
			var a2_1_2 = $("<a></a>");
			a2_1_2.attr("href", "#");
			var div2_1_2 = $("<div></div>");
			div2_1_2.addClass("outer-con");
			var div2_1_3 = $("<div></div>");
			div2_1_3.addClass("title");
			var div2_1_4 = $("<div></div>");
			div2_1_4.addClass("sub-title");
			div2_1_3.append("开抢啦！");
			div2_1_4.append("零食大礼包");
			var img2_1 = $("<img/>");
			img2_1.attr("src", "../images/act1.png");
			div2_1_2.append(div2_1_3).append(div2_1_4);
			a2_1_2.append(div2_1_2).append(img2_1);
			var div2_1_5 = $("<div></div>");
			div2_1_5.addClass("triangle-topright");
			div2_1.append(div2_1_1).append(a2_1_2).append(div2_1_5);
			div2.append(div2_1);

			//第二小部分
			var div2_2_2 = $("<div></div>");
			div2_2_2.addClass("am-u-sm-7 am-u-md-4 text-two sug");
			show_product1(msg.msg1_1.map.pageInfo.list[0],div2_2_2);
			div2.append(div2_2_2);
			var div2_2_3 = $("<div></div>");
			div2_2_3.addClass("am-u-sm-7 am-u-md-4 text-two");
			show_product1(msg.msg1_1.map.pageInfo.list[1],div2_2_3);
			div2.append(div2_2_3);
			var div2_2_4 = $("<div></div>");
			div2_2_4.addClass("am-u-sm-3 am-u-md-2 text-three big");
			show_product1(msg.msg1_1.map.pageInfo.list[2],div2_2_4);
			div2.append(div2_2_4);
			var div2_2_5 = $("<div></div>");
			div2_2_5.addClass("am-u-sm-3 am-u-md-2 text-three sug");
			show_product1(msg.msg1_1.map.pageInfo.list[3],div2_2_5);
			div2.append(div2_2_5);
			var div2_2_6 = $("<div></div>");
			div2_2_6.addClass("am-u-sm-3 am-u-md-2 text-three ");
			show_product1(msg.msg1_1.map.pageInfo.list[4],div2_2_6);
			div2.append(div2_2_6);
			var div2_2_7 = $("<div></div>");
			div2_2_7.addClass("am-u-sm-3 am-u-md-2 text-three last big");
			show_product1(msg.msg1_1.map.pageInfo.list[5],div2_2_7);
			div2.append(div2_2_7);

			//第三部分
			var div3 = $("<div></div>");
			div3.addClass("clear");

			p.append(div2).append(div3);
		}

		function show_p_two(msg,p) {
			//第一部分
			var div1 = $("<div></div>");
			div1.addClass("am-container");
			var div1_1 = $("<div></div>");
			div1_1.addClass("shopTitle");
			var h1_1 = $("<h4></h4>");
			var h1_2 = $("<h3></h3>");
			var div1_2 = $("<div></div>");
			div1_2.addClass("today-brands");
			for (var i = 0; msg.msg2_3.map.pageInfo.list[i] != null; i++) {
				var a1 = $("<a></a>");
				a1.attr("href", "introduction.jsp?id="+msg.msg2_3.map.pageInfo.list[i].id);
				var name = msg.msg2_3.map.pageInfo.list[i].name.split('/');
				a1.append(name[0]);
				div1_2.append(a1);
			}
			var sp = $("<span></span>");
			sp.addClass("more");
			var a1_1 = $("<a></a>");
			var i1 = $("<i></i>");
			i1.addClass("am-icon-angle-right");
			i1.attr("style", "padding-left: 10px;");
			sp.append("更多美味").append(i1);
			h1_1.append(msg.category_list[1].type);
			h1_2.append("每一个商品都有一个故事");
			div1_1.append(h1_1).append(h1_2).append(div1_2);
			div1.append(div1_1);
			p.append(div1);

			//第二部分
			var div2 = $("<div></div>");
			div2.addClass("am-g am-g-fixed floodFour");
			//第一小部分
			var div2_1 = $("<div></div>");
			div2_1.addClass("am-u-sm-5 am-u-md-4 text-one list ");
			var div2_1_1 = $("<div></div>");
			div2_1_1.addClass("word");
			for (var i = 0; msg.msg2_2.map.pageInfo.list[i] != null; i++) {
				var a2_1_1 = $("<a></a>");
				a2_1_1.addClass("outer").attr("href", "introduction.jsp?id="+msg.msg2_2.map.pageInfo.list[i].id);
				var sp2_1_1 = $("<span></span>");
				sp2_1_1.addClass("inner");
				var b2_1_1 = $("<b></b>");
				b2_1_1.addClass("text");
				var name = msg.msg2_2.map.pageInfo.list[i].name.split('/');
				b2_1_1.append(name[0]);
				sp2_1_1.append(b2_1_1);
				a2_1_1.append(sp2_1_1);
				div2_1_1.append(a2_1_1);
			}
			var a2_1_2 = $("<a></a>");
			a2_1_2.attr("href", "#");
			var div2_1_2 = $("<div></div>");
			div2_1_2.addClass("outer-con");
			var div2_1_3 = $("<div></div>");
			div2_1_3.addClass("title");
			var div2_1_4 = $("<div></div>");
			div2_1_4.addClass("sub-title");
			div2_1_3.append("开抢啦！");
			div2_1_4.append("零食大礼包");
			var img2_1 = $("<img/>");
			img2_1.attr("src", "../images/act1.png");
			div2_1_2.append(div2_1_3).append(div2_1_4);
			a2_1_2.append(div2_1_2).append(img2_1);
			var div2_1_5 = $("<div></div>");
			div2_1_5.addClass("triangle-topright");
			div2_1.append(div2_1_1).append(a2_1_2).append(div2_1_5);
			div2.append(div2_1);

			//第二小部分
			var div2_2_2 = $("<div></div>");
			div2_2_2.addClass("am-u-sm-7 am-u-md-4 text-two sug");
			show_product1(msg.msg2_1.map.pageInfo.list[0],div2_2_2);
			div2.append(div2_2_2);
			var div2_2_3 = $("<div></div>");
			div2_2_3.addClass("am-u-sm-7 am-u-md-4 text-two");
			show_product1(msg.msg2_1.map.pageInfo.list[1],div2_2_3);
			div2.append(div2_2_3);
			var div2_2_4 = $("<div></div>");
			div2_2_4.addClass("am-u-sm-3 am-u-md-2 text-three big");
			show_product1(msg.msg2_1.map.pageInfo.list[2],div2_2_4);
			div2.append(div2_2_4);
			var div2_2_5 = $("<div></div>");
			div2_2_5.addClass("am-u-sm-3 am-u-md-2 text-three sug");
			show_product1(msg.msg2_1.map.pageInfo.list[3],div2_2_5);
			div2.append(div2_2_5);
			var div2_2_6 = $("<div></div>");
			div2_2_6.addClass("am-u-sm-3 am-u-md-2 text-three ");
			show_product1(msg.msg2_1.map.pageInfo.list[4],div2_2_6);
			div2.append(div2_2_6);
			var div2_2_7 = $("<div></div>");
			div2_2_7.addClass("am-u-sm-3 am-u-md-2 text-three last big");
			show_product1(msg.msg2_1.map.pageInfo.list[5],div2_2_7);
			div2.append(div2_2_7);

			//第三部分
			var div3 = $("<div></div>");
			div3.addClass("clear");

			p.append(div2).append(div3);
		}
		
		
		
		//加载后生成产品列表函数
		function show_product1(msg,pl) {
			if(msg == undefined)
				return;
			var name = msg.name.split('/');
			var div_name = $("<div></div>").addClass("title").append(name[0]);
			var div_pri = $("<div></div>").addClass("sub-title")
					.append(msg.price+"元");
			var i_addcart = $("<i></i>").addClass(
					"am-icon-shopping-basket am-icon-md  seprate").append("");
			var a_pic = $("<a></a>").attr("href", "introduction.jsp?id="+msg.id);
			var img = $("<img/>");
			$.ajax({
				url:"../../Pic/selectPicByProductType",
				data:"id="+msg.id+"&type="+1,
				type:"post",
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
			a_pic.append(img);
			var div_outer = $("<div></div>").addClass("outer-con").append(
					div_name).append(div_pri);
			pl.append(div_outer).append(a_pic);
		}
		//热门活动
		function hot_activity() {
			var div_mian = $("#hot_activity");
			var div1 = $("<div></div>");
			div1.addClass("shopTitle");
			var h3 = $("<h3></h3>");
			var h4 = $("<h4></h4>");
			var sp = $("<span></span>");
			sp.addClass("more");
			var a3 = $("<a></a>");
			var i = $("<i></i>");
			i.attr("class", "am-icon-angle-right").attr("style",
					"padding-left: 10px;");
			a3.append("全部活动").append(i);
			sp.append(a3);
			h3.append("每期活动 优惠享不停");
			h4.append("活动");
			div1.append(h4).append(h3).append(sp);
			div_mian.append(div1);
			var div1_1 = $("<div></div>");
			div1_1.addClass("am-g am-g-fixed");

			for (var i = 0; i < 4; i++) {
				var div2 = $("<div></div>");
				div2.addClass("am-u-sm-3");
				var div3 = $("<div></div>");
				div3.addClass("icon-sale one");
				var div4 = $("<div></div>");
				div4.addClass("activityMain");
				var div5 = $("<div></div>");
				div5.addClass("info");
				var h1 = $("<h3></h3>");
				var h2 = $("<h4></h4>");
				h1.append("春节送礼优选");
				h2.append("秒杀");
				div3.append(h1).append(h2);
				var img2 = $("<img/>");
				img2.attr("src", "../images/activity1.jpg ");
				div4.append(img2);
				div5.append(h1);
				div2.append(div3).append(h2).append(div4).append(div5);
				div1_1.append(div2);
			}
			div_mian.append(div1_1);
		}

		//今日推荐
		function today_rem() {
			 
			$.ajax({
				type : "post" ,
				dataType : "json" ,
				url : "../../Product/selectProductDes" ,
				async : false ,
				success : function(msg){
					console.log("before 今日推荐");
					console.log(msg);
					
					var div_mian = $("#today_re");
					var div1 = $("<div></div>");
					div1.addClass("clock am-u-sm-3");
					var img1 = $("<img/>");
					var p1 = $("<p></p>");
					img1.attr("src", "../images/2016.png");
					p1.append("今日<br>推荐");
					div1.append(img1).append(p1);
					div_mian.append(div1);
                    
					var list = msg.map.list ;
					
					for (var i = 0; i < 3; i++) {
						var div2 = $("<div></div>");
						div2.addClass("am-u-sm-4 am-u-lg-3");
						var div3 = $("<div></div>");
						div3.addClass("info");
						var div4 = $("<div></div>");
						div4.addClass("recommendationMain one");
						var h1 = $("<h3></h3>");
						var h2 = $("<h4></h4>");
						h1.append("今日大酬宾");
						var name = list[i].name.split('/');
						h2.append(name[0]);
						div3.append(h1).append(h2);
						var a = $("<a></a>");
						a.attr("href", "../home/introduction.jsp?id="+list[i].id);
						var img2 = $("<img/>");
						$.ajax({
							url:"../../Pic/selectPicByProductType",
							data:"id="+list[i].id+"&type="+1,
							type:"post",
							async : false,
							success:function(ms){
								if(ms.code == 100)
									img2.attr("src", ms.map.path+ms.map.pic_list[0].path);
								else 
									img2.attr("src", ms.map.path+"1.jpg");
							},
							error:function(msg){
								img2.attr("src", ms.map.path+"1.jpg");
							}
						});
						//img2.attr("src", "../images/tj.png");
						a.append(img2);
						div4.append(a);
						div2.append(div3).append(div4);
						div_mian.append(div2);
					}
					
					
					
					
				}
			});
	
		}

		//无限计时
		function timeout() {
			var right = null;
			if ($("#right1").next().length != 0) {
				right = $("#right1").next().attr("class");
			} else {
				right = "ner0";
			}
			$("#div_dd").children("div").each(function() {
				$(this).text("●");
				$(this).attr("id", "left");
			});
			$(".am-slides").children("li").each(
					function() {
						$(this).attr("style",
								"width: 1519px; float: left; display: none;");
					});
			var b = ".ban" + right;
			right = "." + right;
			$(right).text("○");
			$(right).attr("id", "right1");
			$(b).attr("style", "width: 1519px; float: left; display: block;");
			t = setTimeout(timeout, 3000);

		}
		//轮播
		function carousel(msg) {
			var ul = $("<ul></ul>");
			ul.addClass("am-slides");
			var div_d = $("<div></div>");
			div_d.attr("id", "div_dd");
			//轮播图片数量
			for (var i = 0; msg.activity_list[i] != null; i++) {
				var li = $("<li></li>");
				li.addClass("banner" + i);
				li.attr("style", "width: 1519px; float: left; display: none;");
				var a = $("<a><a/>");
				a.attr("href", "http://www.baidu.com");
				var img = $("<img/>");
				img.attr("style","width:1010px;height:455px;");
				$.ajax({
					url : "../../MallActivity/find_img_activity",
					data : "id=" + msg.activity_list[i].picId,
					type : "post",
					async : false,
					success : function(ms) {
						if (ms.code == 100) {
							//类型列表
							img.attr("src", ms.map.p+ms.map.pic.path);
						} else {
							
							img.attr("src", ms.map.p+"1.jpg");
						}
					},
					error : function(ms) {
						layer.msg("error！", {
											icon : 5,
											time : 3000
										});
					}
				});
				/* img.attr("src", msg.path + msg.activity_list[i]); */
				var div_dot = $("<div></div>");
				div_dot.text("●");
				var left = 400 + i * 20;
				div_dot.addClass("ner" + i);
				div_dot.attr("id", "left1");
				div_dot.attr("style",
						"text-align:center;width:20px;height:20px;position: absolute; left: "
								+ left + "px; top: 400px;")

				a.append(img);
				li.append(a);
				ul.append(li);
				div_d.append(div_dot);
			}
			var div_a = $("<div></div>");
			div_a.addClass("am-slider am-slider-default scoll");
			div_a.attr("data-am-flexslider", "data-am-flexslider").attr("id",
					"demo-slider-0");
			div_a.append(ul);
			var d = $("<div></div>");
			d.addClass("clear");
			var div_l = $("#lunbo");
			div_l.append(div_a).append(d).append(div_d);
		}

		//类别列表
		function category_list(msg) {
			var ul = $("#js_climit_li");
			var li = $("<li></li>");
			li.addClass("appliance js_toggle relative");
			
			

			//div1
			var div1 = $("<div></div>");
			div1.addClass("category-info");
			var h = $("<h3></h3>");
			h.addClass("category-name b-category-name");
			var i = $("<i></i>");
			var img = $("<img/>");
			img.attr("src", "../images/cake.png");
			var a = $("<a></a>");
			a.addClass("ml-22");
			a.attr("title", msg.type);
			a.append(msg.type);
			i.append(img);
			h.append(i).append(a);
			var em = $("<em></em>");
			em.append("&gt;");
			div1.append(h).append(em);

			//sort-side
			var dl_s = $("<dl></dl>");
			dl_s.addClass("dl-sort");
			var dt_s = $("<dt></dt>");
			var sp_s_1 = $("<span></span>");
			sp_s_1.attr("title", msg.type);
			sp_s_1.append(msg.type);
			dt_s.append(sp_s_1);
			dl_s.append(dt_s);
			$.ajax({
				url:"../../Product/selectProductByCategoryId",
				type:"post",
				data:"id="+msg.id,
				async : false,
				success:function(ms){
					if(ms.code == 100){
						for (var i = 0; ms.map.pageInfo.list[i] != null; i++) {
							var dd_s = $("<dd></dd>");
							var a_s = $("<a></a>");
							a_s.attr("title", ms.map.pageInfo.list[i].name)
								.attr("href", "http://localhost:8080/ECC/muban/home/introduction.jsp?id="+ms.map.pageInfo.list[i].id);
							var sp_s_2 = $("<span></span>");
							sp_s_2.append(ms.map.pageInfo.list[i].name);
							a_s.append(sp_s_2);
							dd_s.append(a_s);
							dl_s.append(dd_s);
						}
					}else{
					}
					
				},
				error:function(){
					layer.msg("error！", {
						icon : 5,
						time : 3000
					});
				}
			});
			

			//brand-side
			/* var dl_b = $("<dl></dl>");
			dl_b.addClass("dl-sort");
			var dt_b = $("<dt></dt>");
			var sp_b = $("<span></span>");
			sp_b.append("实力商家");
			dt_b.append(sp_b);
			dl_b.append(dt_b);
			for (var i = 0; i < 10; i++) {
				var dd_b = $("<dd></dd>");
				var a_b = $("<a></a>");
				a_b.attr("title", "杭派女装批发网").attr("href", "#").attr("rel",
						"nofollow").attr("target", "_blank");
				var sp_b_2 = $("<span></span>");
				sp_b_2.addClass("red");
				sp_b_2.append("杭派女装批发网");
				a_b.append(sp_b_2);
				dd_b.append(a_b);
				dl_b.append(dd_b);
			} */

			//div2
			var div2 = $("<div></div>");
			div2.addClass("menu-item menu-in top");
			var div2_1 = $("<div></div>");
			div2_1.addClass("area-in");
			var div2_2 = $("<div></div>");
			div2_2.addClass("area-bg");
			var div2_3 = $("<div></div>");
			div2_3.addClass("menu-srot");
			var div2_4 = $("<div></div>");
			div2_4.addClass("sort-side");
			for (var i = 0; i < 2; i++) {
				div2_4.append(dl_s);
			}
			var div2_5 = $("<div></div>");
			div2_5.addClass("brand-side");
			//div2_5.append(dl_b);
			div2_3.append(div2_4).append(div2_5);
			div2_2.append(div2_3);
			div2_1.append(div2_2);
			div2.append(div2_1);

			var b = $("<b></b>");
			b.addClass("arrow");
			li.append(div1).append(div2).append(b);
			ul.append(li);
			
			
		}

		//点击搜索按钮，新建一个超链接跳转到商品搜索界面
		$("#ai-topsearch").click(
				function() {
					var productname = $("#searchInput").val();
					var span = $("<span></span>").append("搜索界面");
					var a = $("<a></a>").attr("href",
							"../home/search.jsp?productname=" + productname)
							.attr("target", "_blank").append(span);
					span.click();
				});

	})

	//右侧导航条的Jquery
	$(function() {

		//客服中心 填写反馈意见
		$("#kefu").click(function(){
		      $(location).prop("href","../Feedback/FeedBacks.jsp");
		});
		
		//跳转购物车
		$("#Navigation_right_shopcart").click(function() {
			$(location).prop('href', '../home/shopcart.jsp');
		});
		//跳转我的资产
		$("#Navigation_right_assets").click(function() {
			$(location).prop('href', '../test/test_main.jsp?action=bill');
		});
		//跳转我的足迹
		$("#Navigation_right_foot").click(function() {
			$(location).prop('href', '../test/test_main.jsp?action=foot');
		});
		//跳转我的充值
		$("#broadcast").click(function() {
			$(location).prop('href', '../test/test_main.jsp?action=pay');
		});
		
		//跳转我的收藏
		$("#Navigation_right_collection").click(
				function() {

					$(location).prop('href',
							'../test/test_main.jsp?action=collection');
				});
	});
</script>
<script src="../AmazeUI-2.4.2/assets/js/amazeui.min.js"></script>



</head>

<body>
	<div class="hmtop">
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
				<!-- <a name="index_none_header_sysc" href="#"></a> -->

				<input id="searchInput" name="" type="text" placeholder="搜索"
					autocomplete="off">
				<button id="ai-topsearch" class="submit am-btn" index="1">搜索</button>

			</div>
		</div>



		<div class="clear"></div>
	</div>
	<div class="banner" id="lunbo"></div>
	<div class="shopNav">
		<div class="slideall">

			<div class="long-title">
				<span class="all-goods">全部分类</span>
			</div>
			<div class="nav-cont">
				
				<div class="nav-extra">
					<i class="am-icon-user-secret am-icon-md nav-user"></i><b></b><a href='../test/test_main.jsp'>
					个人中心</a>
					<i class="am-icon-angle-right" style="padding-left: 10px;"></i>
				</div>
			</div>

			<!--侧边导航 -->
			<div id="nav" class="navfull">
				<div class="area clearfix">
					<div class="category-content" id="guide_2">

						<div class="category">
							<ul class="category-list" id="js_climit_li">
							</ul>
						</div>
					</div>

				</div>
			</div>





			<!--小导航 -->
			<div class="am-g am-g-fixed smallnav">
				<div class="am-u-sm-3">
					<a href="sort.html"><img src="../images/navsmall.jpg" />
						<div class="title">商品分类</div> </a>
				</div>
				<div class="am-u-sm-3">
					<a href="#"><img src="../images/huismall.jpg" />
						<div class="title">大聚惠</div> </a>
				</div>
				<div class="am-u-sm-3">
					<a href="#"><img src="../images/mansmall.jpg" />
						<div class="title">个人中心</div> </a>
				</div>
				<div class="am-u-sm-3">
					<a href="#"><img src="../images/moneysmall.jpg" />
						<div class="title">投资理财</div> </a>
				</div>
			</div>

			<!--走马灯 -->

		
			<div class="clear"></div>
		</div>
		<script type="text/javascript">
			if ($(window).width() < 640) {
				function autoScroll(obj) {
					$(obj).find("ul").animate({
						marginTop : "-39px"
					}, 500, function() {
						$(this).css({
							marginTop : "0px"
						}).find("li:first").appendTo(this);
					})
				}
				$(function() {
					setInterval('autoScroll(".demo")', 3000);
				})
			}
		</script>
	</div>
	<div class="shopMainbg">
		<div class="shopMain" id="shopmain">

			<!--今日推荐 -->

			<div id="today_re" class="am-g am-g-fixed recommendation"></div>
			<div class="clear "></div>
			<!--热门活动 -->

			


			<div id="f1">
				<!--甜点-->

			</div>


			<div id="f2">
				<!--坚果-->
			</div>







		</div>
	</div>
	<!--引导 -->
	<div class="navCir">
		<li class="active"><a href="home.html"><i
				class="am-icon-home "></i>首页</a></li>
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
					<a href="#"> <span class="chongzhi "><img
							src="../images/chongzhi.png " /></span>
					</a>
					<div class="mp_tooltip ">
						我要充值 <i class="icon_arrow_right_black "></i>
					</div>
				</div>

				<div class="quick_toggle ">
					<li class="qtitem "><a href="# "><span class="kfzx " id="kefu"></span></a>
						<div class="mp_tooltip ">
							点击反馈<i class="icon_arrow_right_black "></i>
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

				<div id="quick_links_pop " class="quick_links_pop hide "></div>

			</div>

		</div>
		<div id="prof-content " class="nav-content ">
			<div class="nav-con-close ">
				<i class="am-icon-angle-right am-icon-fw "></i>
			</div>
			<div>我</div>
		</div>
		<div id="shopCart-content " class="nav-content ">
			<div class="nav-con-close ">
				<i class="am-icon-angle-right am-icon-fw "></i>
			</div>
			<div>购物车</div>
		</div>
		<div id="asset-content " class="nav-content ">
			<div class="nav-con-close ">
				<i class="am-icon-angle-right am-icon-fw "></i>
			</div>
			<div>资产</div>

			<div class="ia-head-list ">
				<a href="# " target="_blank " class="pl ">
					<div class="num ">0</div>
					<div class="text ">优惠券</div>
				</a> <a href="# " target="_blank " class="pl ">
					<div class="num ">0</div>
					<div class="text ">红包</div>
				</a> <a href="# " target="_blank " class="pl money ">
					<div class="num ">￥0</div>
					<div class="text ">余额</div>
				</a>
			</div>

		</div>
		<div id="foot-content " class="nav-content ">
			<div class="nav-con-close ">
				<i class="am-icon-angle-right am-icon-fw "></i>
			</div>
			<div>足迹</div>
		</div>
		<div id="brand-content " class="nav-content ">
			<div class="nav-con-close ">
				<i class="am-icon-angle-right am-icon-fw "></i>
			</div>
			<div>收藏</div>
		</div>
		<div id="broadcast-content " class="nav-content ">
			<div class="nav-con-close ">
				<i class="am-icon-angle-right am-icon-fw "></i>
			</div>
			<div>充值</div>
		</div>
	</div>
	<script>
		window.jQuery
				|| document
						.write('<script src="basic/js/jquery.min.js "><\/script>');
	</script>
	<script type="text/javascript " src="../basic/js/quick_links.js "></script>
</body>

</html>