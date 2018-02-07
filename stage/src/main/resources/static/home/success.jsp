<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>付款成功页面</title>
<link rel="stylesheet"  type="text/css" href="../AmazeUI-2.4.2/assets/css/amazeui.css"/>
<link href="../AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
<link href="../basic/css/demo.css" rel="stylesheet" type="text/css" />

<link href="../css/sustyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../basic/js/jquery-1.7.min.js"></script>

</head>
<script type="text/javascript">
   $(function(){
	 //页面一加载，判断用户是否登录，根据结果改变左上角的样式
	  	  $.post("../../User/check_login",function(msg,status){
	  		  if(msg.code==100){
	  			  //登录成功
	  			  console.log(msg);
	  			  //alert(msg.map.name);
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
	  		  }else{
	  			  //登录失败
	  			  
	  			  var a = $("<a></a>").attr("href","../home/login.jsp").addClass("h").append("亲，请登录  ");
	  			  var b = $("<a></a>").attr("href","../home/register.jsp").append("免费注册");
	  		       var div = $("#login_status");
	  		       div.append(a).append(b);
	  		  }
	  	  });
	 
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
	 
   });
</script>



<body>



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
						<div class="menu-hd"><a href="../home/home.jsp" target="_top" class="h">商城首页</a></div>
					</div>
					<div class="topMessage my-shangcheng">
						<div class="menu-hd MyShangcheng"><a href="../test/test_main.jsp" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
					</div>
					<div class="topMessage mini-cart">
						<div class="menu-hd"><a id="mc-menu-hd" href="../home/shopcart.jsp" target="_top"><i class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong id="J_MiniCartNum" class="h">0</strong></a></div>
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



<div class="take-delivery">
 <div class="status">
   <h2>您已成功付款</h2>
   <div class="successInfo">
     <ul id="ul">
     </ul>
     <div class="option">
       <span class="info">您可以</span>
    
        <a href="../test/test_main.jsp?action=bill" class="J_MakePoint">查看<span>交易详情</span></a>
     </div>
    </div>
  </div>
</div>

<input type="hidden" value="${param.address}" id="addressId" />
<div class="footer" >
 <div class="footer-hd">
 <p>
 <a href="#">恒望科技</a>
 <b>|</b>
 <a href="#">商城首页</a>
 <b>|</b>
 <a href="#">支付宝</a>
 <b>|</b>
 <a href="#">物流</a>
 </p>
 </div>
 
</div>


</body>
<script type="text/javascript">
$(function (){
	show_message();
});

function show_message(){
	if($("#addressId").val()== "")
	{
		$(location).prop("href","../home/home.jsp");
	}
	$.post("../../Address/SelectAddressByAddressId","id="+$("#addressId").val(),function(data,status){
		    if(data.code==100){
		    	console.log(data);
		    	var address = data.map.address;
		    	var ul = $("#ul");
		    	var li = $("<li></li>");
		    	var em = $("<em></em>");
		    	li.append("付款金额").append(em);
		    	var div = $("<div></div>");
		    	div.addClass("user-info");
		    	var p1 = $("<p></p>");
		    	var p2 = $("<p></p>");
		    	var p3 = $("<p></p>");
		    	p1.append("收货人："+address.name);
		    	p2.append("联系电话:"+ address.phone);
		    	var reg= /-/g;
	              if(address.address!=null)
	            	  address.address = address.address.replace(reg," ");
		    	
		    	p3.append("收货地址：" + address.address);
		    	div.append(p1).append(p2).append(p3);
		    	ul.append(li).append(div).append(" 请认真核对您的收货信息，如有错误请联系客服");
		    }else{
		    	$(location).prop("../home/home.jsp");
		    }
	});
	
	
}
</script>
</html>
