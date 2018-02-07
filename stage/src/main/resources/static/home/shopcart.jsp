<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <link href="../bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="./css/layer.css">
<script src="../../static/js/jquery-3.1.1.min.js"></script>
<script src="../bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/layer.js"></script>
<script type="text/javascript">
	$(function() {
		
		//上面的提示优惠(优惠券)
		   $.post("../../Coupon/getMyCoupon",function(msg){
			    if(msg.code==200){
			    	$("#tip_coupon").html("电器商城大酬宾！买满5000即送无门槛优惠券!");
			    }else{
			    	$("#tip_coupon").html("您的优惠券已享优惠:省￥58.50");
			    }
		   });
		
		
		//设置点击结算按钮事件
		$("#J_Go").click(function(){
		
			if(now == 0){
				layer.msg("请选择商品！", {
					icon : 5,
					time : 2000
				});
				return;
			}
			
			$.post("../../Order/addSorderToForder","id="+array,function(data,status){
			    if(data.code==100)
				$(location).prop("href","../home/pay.jsp?allmoney="+now+"&forderId="+data.map.forderId);
			});
			

				//$("#J_Go").attr("href","../home/pay.jsp?allmoney="+now);
		});
		
		
		
		
		//页面一加载，判断用户是否登录，根据结果改变左上角的样式
    	  $.post("../../User/check_login",function(msg,status){
    		  if(msg.code==100){
    			  //登录成功
    			
    			  var a  = $("<a></a>").attr("href","#").addClass("h").append("欢迎你! "+msg.map.name);
    				var logout  = $("<a></a>").attr("href" , "#").append("&nbsp;  &nbsp;  退出登录");
					var div = $("#login_status");
					div.append(a).append(logout);
					
					//点击退出登录
					 logout.click(function(){
						/*  if(confirm("确定退出登录?")){
							 $.post("../../User/logout",function(data){
						           $(location).prop("href","../home/home.jsp");
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
                   	$.post("../../Order/getSorderById",function(data,status){
                   		
                   		$("#J_MiniCartNum").html(data.map.list.length);
                   	});
   		    getMyShopCart();
   		  
   			
    		  }else{
    			  //登录失败
    			 
    			  var a = $("<a></a>").attr("href","../home/login.jsp").addClass("h").append("亲，请登录  ");
    			  var b = $("<a></a>").attr("href","../home/register.jsp").append("免费注册");
    		       var div = $("#login_status");
    		        layer.msg('您还没有登录，马上为您跳转到登录界面！', {icon: 5,time : 2000});
    		       setTimeout(function(){
    		    	   $(location).prop('href', '../home/login.jsp');
						},2000);
    		       div.append(a).append(b);
    		  }
    	});
		
		
		//请求拿到自己的购物车
		function getMyShopCart(){
			//POST 请求拿到自己的购物车
		       $.post("../../Order/getSorderById",function(mess,status){
		    	
		    	   
		    	  //加载购物车列表   
  			      item_list(mess);
		       });
		}
		
	
		
		
		//复选框事件单选事件
		var div = $("#show_product_message");
		var input = $("#quan").children("input");
		div.find(".cart-checkbox").each(function() {
			if ($(this).children("input").attr("id") != input.attr("id")) {
				$(this).children("input").click(function() {
					singular_event();
				});
			}
		});

		//复选框事件全选事件
		/* input.click(function() {
			complex_event();
		}); */
	});
	var total = 0;
	var now = 0;
	
	//改变总的金钱
	function change_money(){
		$("#J_Total").html(now);
		//$("#J_Go").attr("href","../home/pay.jsp?allmoney="+now);
	}
	
	
	
	//复选框事件全选事件
	function complex_event() {
		var div = $("#show_product_message");
		var input = $("#quan").children("input");
		if (input.attr("checked") == "checked") {
			div.find(".cart-checkbox").each(function() {
				if ($(this).children("input").attr("id") == input.attr("id")) {

				} else {
					$(this).children("input").attr("checked", "checked");
				}
			});
			now = total;
			
		} else {
			div.find(".cart-checkbox").each(function() {
				if ($(this).children("input").attr("id") == input.attr("id")) {

				} else {
					$(this).children("input").removeAttr("checked");
				}
			});
			now = 0;
		}
		change_money();
	}

	

	//复选框事件单选事件
	function singular_event() {

		var i = 0;
		var div = $("#show_product_message");
		var input = $("#quan").children("input");
		div.find(".cart-checkbox").each(function() {
			if ($(this).children("input").attr("id") == input.attr("id")) {
            
			} else {

				if ("checked" != $(this).children("input").attr("checked")) {
					i++;
				}
			}
		});
		
		if (i == 0) {
			input.attr("checked", "checked");
			layer.msg("点击单选！", {
				icon : 0,
				time : 2000
			});
		} else {
			input.removeAttr("checked");
			layer.msg("点击不单选！", {
				icon : 0,
				time : 2000
			});
		}

	}


	var array = new Array();
	var length = 0;
	function item_list(msg) {
		var i = 0;
		var div = $("#show_product_message");
		div.empty();
		$.each(msg.map.list,function(index ,item){
			
			
			$.post("../../Product/SelectProductById","id="+item.productId,function(products,status){
				
				var product = products.map.product;
				var d = $("<div></div>");
				d.addClass("clear");

				var li1 = $("<li></li>");
				li1.addClass("td td-item");
				var div1_1 = $("<div></div>");
				var div1_2 = $("<div></div>");
				var div1_3 = $("<div></div>");
				div1_1.addClass("item-pic");
				div1_2.addClass("item-info");
				div1_3.addClass("item-basic-info");
				var a1 = $("<a></a>");
				var a2 = $("<a></a>");
				a1.addClass("J_MakePoint").attr("href", "#");
				a2.addClass("item-title J_MakePoint").attr("href", "introduction.jsp?id="+product.id).attr(
						"data-point", "tbcart.8.11");
				var img1 = $("<img/>");
				img1.addClass("itempic J_ItemImg");
				img1.attr("style","width:80px;height:80px;")
				$.ajax({
					url:"../../Pic/selectPicByProductType",
					data:"id="+product.id+"&type="+1,
					type:"post",
					success:function(ms){
						if(ms.code == 100)
							img1.attr("src", ms.map.path+ms.map.pic_list[0].path);
						else 
							img1.attr("src", ms.map.path+"1.jpg");
					},
					error:function(msg){
						img1.attr("src", ms.map.path+"1.jpg");
					}
				});
				/* img1.addClass("itempic J_ItemImg").attr("src",
						"../images/kouhong.jpg_80x80.jpg"); */
				a1.append(img1);
				a2.append(product.name);
				div1_1.append(a1);
				div1_3.append(a2);
				div1_2.append(div1_3);
				li1.append(div1_1).append(div1_2);

				var li2 = $("<li></li>");
				;
				li2.addClass("td td-info");
				var div2 = $("<div></div>");
				div2.addClass("item-props");
				var sp2_1 = $("<span></span>");
				var sp2_2 = $("<span></span>");
				sp2_1.addClass("sku-line");
				sp2_2.addClass("sku-line");
				sp2_1.append("型号:"+product.spec);
				sp2_2.append("");
				div2.append(sp2_1).append(sp2_2);
				li2.append(div2);

				var li3 = $("<li></li>");
				li3.addClass("td td-price");
				var div3_1 = $("<div></div>");
				var div3_2 = $("<div></div>");
				div3_1.addClass("item-price price-promo-promo");
				div3_2.addClass("price-content");
				var em3 = $("<em></em>");
				em3.addClass("J_Price price-now");
				em3.append(product.price);
				div3_2.append(em3);
				div3_1.append(div3_2);
				li3.append(div3_1);

				var div_phone = $("<div></div>");
				div_phone.addClass("pay-phone");
				div_phone.append(li1).append(li2).append(li3);

				var li4 = $("<li></li>");
				li4.addClass("td td-amount");
				var div4_1 = $("<div></div>");
				var div4_2 = $("<div></div>");
				var div4_3 = $("<div></div>");
				div4_1.addClass("amount-wrapper");
				div4_2.addClass("item-amount");
				div4_3.addClass("sl");
			
				
			
				var input4_1 = $("<input/>");
				var input4_2 = $("<input/>");
				var input4_3 = $("<input/>");
				input4_1.addClass("min am-btn ").attr("name", "").attr("type", "button")
				.attr("value", "-");
		input4_2.addClass("text_box").attr("name", "").attr("type", "text")
				.attr("value", item.number).attr("style", "width:30px;").attr("v",item.id);
		input4_3.addClass("add am-btn").attr("name", "").attr("type", "button")
				.attr("value", "+");
		
		var em5 = $("<em></em>");
		em5.addClass("J_ItemSum number").attr("tabindex", "0");
		em5.append(parseInt(input4_2.val())*parseInt(product.price));
		
		var input = $("<input/>");

		input.addClass("check").attr("id", "J_CheckBox_"+i).attr(
				"name", "items").attr("value", "170037950254").attr("type",
				"checkbox");
		
		var nb = 0;
			input4_2.change(function (){
				nb = input4_2.val();
				if(nb<=0)return ;
				 input4_2.val(nb);
				 em5.empty();
				 em5.append(parseInt(input4_2.val())*parseInt(product.price));
				 if($("#J_Total").html()!=0&&input.is(':checked')==true)
					 $("#J_Total").html(parseInt($("#J_Total").html())-parseInt(product.price));
				updatenum($(this));
			});
		input4_1.click(function(){
			nb = input4_2.val();
			nb -- ; 
			if(nb<=0)return ;
			 input4_2.val(nb);
			 em5.empty();
			 em5.append(parseInt(input4_2.val())*parseInt(product.price));
			 if($("#J_Total").html()!=0&&input.is(':checked')==true)
				 $("#J_Total").html(parseInt($("#J_Total").html())-parseInt(product.price));
			 
			 // $("#al_m").val(  parseInt($("#al_m").val()) - parseInt(product.price));
			// $("#J_ActualFee").html( $("#al_m").val() );
			updatenum($(this).next());
		});
		input4_3.click(function(){
			nb = input4_2.val();
			nb ++ ; 
			if(nb<=0)return ;
			 input4_2.val(nb);
			 em5.empty();
			 em5.append(parseInt(input4_2.val())*parseInt(product.price));
			 if($("#J_Total").html()!=0&&input.is(':checked')==true)
				 $("#J_Total").html(parseInt($("#J_Total").html())+parseInt(product.price));
			// $("#al_m").val(  parseInt($("#al_m").val()) + parseInt(product.price));
			// $("#J_ActualFee").html( $("#al_m").val() );
			updatenum($(this).prev());
		});

						div4_3.append(input4_1).append(input4_2).append(input4_3);
						div4_2.append(div4_3);
						div4_1.append(div4_2);
						li4.append(div4_1);

				var li5 = $("<li></li>");
				li5.addClass("td td-sum");
				var div5 = $("<div></div>");
				div5.addClass("td-inner");
				
				
				
				total += parseInt(input4_2.val())*parseInt(product.price);
				div5.append(em5);
				li5.append(div5);

				var li6 = $("<li></li>");
				li6.addClass("td td-chk");
				var div6 = $("<div></div>");
				div6.addClass("cart-checkbox");
				
				   input.click(function(){
					   
					   if(input.is(':checked')==true){
						    now += parseInt(input4_2.val())*parseInt(product.price);
					      array[length++] = item.id;
						    
					   }else{
						    now -= parseInt(input4_2.val())*parseInt(product.price);
						    for(var k = 0 ; k < length ;k++){
						    	if(array[k] == item.id){
						    		array[k] = -100;
						    		break;
						    	}
						    }
						   
					   }
					   change_money();
				});
				
				
				//当下面的CheckBox选满的时候上面的CheckBox自动勾选
				/* $(document).on("click",".check_item",function(){
				  var flag = $(".check_item:checked").length== $(".check_item").length;
				  if(flag==true){
					  $('#check_all').prop("checked",true);
				  }else{
					  $('#check_all').prop("checked",false);
				  }
				}); */
				
				
				
				
				var la = $("<label></label>");
				la.attr("for", "J_CheckBox_170037950254");
				div6.append(input).append(la);
				li6.append(div6);

				var li7 = $("<li></li>");
				li7.addClass("td td-op");
				var div7 = $("<div></div>");
				div7.addClass("td-inner");
				
				var a7_2 = $("<button></button>");
				
				a7_2.addClass("delete btn btn-danger");
			
				a7_2.attr("data-point-url", "#").attr("href", "javascript:;");
				
				a7_2.append("删除");
				
				a7_2.click(function(){
				
					$.post("../../Order/deleteSorderById","id="+item.id,function(delete_message,status){
					 if(delete_message.code==100)
						 
						//删除成功之后重新加载购物车列表   
				       $.post("../../Order/getSorderById",function(mess,status){
				    	  
		  			      item_list(mess);
				       });
					});
				});
				
				div7.append(a7_2);
				li7.append(div7);
         
				var ul = $("<ul></ul>");

				ul.addClass("item-content clearfix").attr("style",
						"width:998px;height:115px;");
				ul.append(div_phone).append(li4).append(li5).append(li6).append(li7);

				var div = $("#show_product_message");
				
				
				div.append(ul);
				i++;
			});
			
			});
			
		
	}
	
	function updatenum(ele){
		var v = ele.val();
		$.ajax({
			url:"../../Forder/updatenum",
			type:"post",
			data:"id="+ele.attr("v")+"&num="+v,
			success:function(){
				
			},
			error:function(){
				
			}
		});
	}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>购物车页面</title>

<link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet"
	type="text/css" />
<link href="../basic/css/demo.css" rel="stylesheet" type="text/css" />
<link href="../css/cartstyle.css" rel="stylesheet" type="text/css" />
<link href="../css/optstyle.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../js/jquery.js"></script>

</head>

<body>

	<!--顶部导航条 -->
	<div class="am-container header">
		<ul class="message-l">
			<div class="topMessage">
				<div class="menu-hd" id="login_status">
					
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
					<a href="../test/test_main.jsp" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a>
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
				<div class="menu-hd">
					
				</div>
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
			<a name="index_none_header_sysc" href="#"></a>
			<form>
				<input id="searchInput" name="index_none_header_sysc" type="text"
					placeholder="搜索" autocomplete="off"> <input
					id="ai-topsearch" class="submit am-btn" value="搜索" index="1"
					type="submit">
			</form>
		</div>
	</div>

	<div class="clear"></div>

	<!--购物车 -->
	<div class="concent">
		<div id="cartTable">
			<div class="cart-table-th">
				<div class="wp">
					<div class="th th-chk">
						<div id="J_SelectAll1" class="select-all J_SelectAll"></div>
					</div>
					<div class="th th-item">
						<div class="td-inner">商品信息</div>
					</div>
					<div class="th th-price">
						<div class="td-inner">单价</div>
					</div>
					<div class="th th-amount">
						<div class="td-inner">数量</div>
					</div>
					<div class="th th-sum">
						<div class="td-inner">金额</div>
					</div>
					<div class="th th-op">
						<div class="td-inner">操作</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>

			<tr class="item-list">
				<div class="bundle  bundle-last ">
					<div class="bundle-hd">
						<div class="bd-promos">
							<div class="bd-has-promo" id="tip_coupon">
								
							</div>
							<div class="act-promo">
							
							</div>
							<span class="list-change theme-login">编辑</span>
						</div>
					</div>
					<div class="clear"></div>
					<div class="bundle-main" id="show_product_message"></div>
				</div>
			</tr>
			<div class="clear"></div>

			<div class="clear"></div>

			<div class="float-bar-wrapper">
			<!-- 	<div id="J_SelectAll2" class="select-all J_SelectAll">
					<div class="cart-checkbox" id="quan">
						<input class="check-all check" id="J_SelectAllCbx2"
							name="select-all" value="true" type="checkbox"> <label
							for="J_SelectAllCbx2"></label>
					</div>
					<span>全选</span>
				</div> -->
				
				<div class="float-bar-right">
					<div class="amount-sum">
						
						<div class="arrow-box">
							<span class="selected-items-arrow"></span> <span class="arrow"></span>
						</div>
					</div>
					<div class="price-sum">
						<span class="txt">合计:</span> <strong class="price">¥<em
							id="J_Total">0.00</em></strong>
					</div>
					<div class="btn-area">
						<a  id="J_Go"
							class="submit-btn submit-btn-disabled"
							aria-label="请注意如果没有选择宝贝，将无法结算"> <span>结&nbsp;算</span></a>
					</div>
				</div>

			</div>


		</div>

		<!--操作页面-->
<!-- 
		<div class="theme-popover-mask"></div>

		<div class="theme-popover">
			<div class="theme-span"></div>
			<div class="theme-poptit h-title">
				<a href="javascript:;" title="关闭" class="close">×</a>
			</div>
			<div class="theme-popbod dform">
				<form class="theme-signin" name="loginform" action="" method="post">

					<div class="theme-signin-left">

						<li class="theme-options">
							<div class="cart-title">颜色：</div>
							<ul>
								<li class="sku-line selected">12#川南玛瑙<i></i></li>
								<li class="sku-line">10#蜜橘色+17#樱花粉<i></i></li>
							</ul>
						</li>
						<li class="theme-options">
							<div class="cart-title">包装：</div>
							<ul>
								<li class="sku-line selected">包装：裸装<i></i></li>
								<li class="sku-line">两支手袋装（送彩带）<i></i></li>
							</ul>
						</li>
						<div class="theme-options">
							<div class="cart-title number">数量</div>
							<dd>
								<input class="min am-btn am-btn-default" name="" type="button"
									value="-" /> <input class="text_box" name="" type="text"
									value="1" style="width: 30px;" /> <input
									class="add am-btn am-btn-default" name="" type="button"
									value="+" /> <span class="tb-hidden">库存<span
									class="stock">1000</span>件
								</span>
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
							<img src="../images/kouhong.jpg_80x80.jpg" />
						</div>
						<div class="text-info">
							<span class="J_Price price-now">¥39.00</span> <span id="Stock"
								class="tb-hidden">库存<span class="stock">1000</span>件
							</span>
						</div>
					</div>

				</form>
			</div>
		</div> -->
		<!--引导 -->
		<div class="navCir">
			<li><a href="home.html"><i class="am-icon-home "></i>首页</a></li>
			<li><a href="sort.html"><i class="am-icon-list"></i>分类</a></li>
			<li class="active"><a href="shopcart.html"><i
					class="am-icon-shopping-basket"></i>购物车</a></li>
			<li><a href="../person/index.html"><i class="am-icon-user"></i>我的</a></li>
		</div>
</body>

</html>