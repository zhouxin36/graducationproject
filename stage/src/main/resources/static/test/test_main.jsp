<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">
		<title>主界面</title>

		<link href="../AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
        <link href="../css/blstyle.css" rel="stylesheet" type="text/css">
        	<link href="../css/addstyle.css" rel="stylesheet" type="text/css">
        <link href="../css/infstyle.css" rel="stylesheet" type="text/css">
        <link href="../css/cmstyle.css" rel="stylesheet" type="text/css">
        <link href="../css/colstyle.css" rel="stylesheet" type="text/css">
        	<link href="../css/newstyle.css" rel="stylesheet" type="text/css">
        <link href="../css/orstyle.css" rel="stylesheet" type="text/css">
        <link href="../css/bostyle.css" rel="stylesheet" type="text/css">
        <link href="../css/cpstyle.css" rel="stylesheet" type="text/css">
        <link href="../css/lostyle.css" rel="stylesheet" type="text/css">
             <link href="../bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
 
 <link rel="stylesheet" href="./css/layer.css">
        	<link href="../css/personal.css" rel="stylesheet" type="text/css">
        <link href="../css/footstyle.css" rel="stylesheet" type="text/css">
		<link href="../css/refstyle.css" rel="stylesheet" type="text/css">
		<link href="../css_test/personal.css" rel="stylesheet" type="text/css">
		<link href="../css_test/addstyle.css" rel="stylesheet" type="text/css">
		<script src="../AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="../AmazeUI-2.4.2/assets/js/amazeui.js"></script>
<script type="text/javascript" src="./js/layer.js"></script>

<script type="text/javascript" src="../js/jquery.cityselect.js"></script>
<script src="../bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="../js/echarts.min.js"></script>

	</head>
<script type="text/javascript">
            $(function(){
	   
            	//页面一加载，检查URL是否有action
            	 var action = GetQueryString("action");
            	 if(action !=null && action.toString().length>1)
            	 {
            		 
            		 $("#jsp").load("test_"+action+".jsp",function(data,status){
 						
 					});
            	 }
            	 
            	 
            	 
            	
            	//页面一加载，判断用户是否登录，根据结果改变左上角的样式
          	  $.post("../../User/check_login",function(msg,status){
          		  if(msg.code==100){
          			  //登录成功
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
          		     layer.msg("您还没有登录，马上为您跳转到登录界面！", {
     					icon : 5,
     					time : 3000
     				});
          		   setTimeout(function(){
          			 $(location).prop('href', '../home/login.jsp');
						},3000);
          		       div.append(a).append(b);
          		  }
          	});
            	
            	
            	
            	
            	
            	
            	//获得URL的参数
            	
            function GetQueryString(name)
                {
           var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
           var r = window.location.search.substr(1).match(reg);
          if(r!=null)return  unescape(r[2]); return null;
        }
            	
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

					

			</article>
		</header>

		<div class="nav-table">
			<div class="long-title"><span class="all-goods">全部分类</span></div>
			<div class="nav-cont">

				<div class="nav-extra">
					<i class="am-icon-user-secret am-icon-md nav-user"></i><b></b>
						<a href="../test/test_main.jsp"  target="_blank">个人中心 </a>
					<i class="am-icon-angle-right" style="padding-left: 10px;"></i>
				</div>
			</div>
		</div>
		<b class="line"></b>

		<div class="center">
			<div class="col-main">
				<div class="main-wrap">

					<div class="user-address">
						<!--标题 -->
                    <div id="jsp"  style='  position:relative; left:-130px;'></div>
                     <div id="jsp2"  style='  position:relative; left:-130px;'></div>
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"></div>
						</div>
						
						<ul class="am-avg-sm-1 am-avg-md-3 am-thumbnails">

						<!-- 	<li class="user-addresslist defaultAddr">
								
								<div class="new-addr-btn">
					
								</div>
							</li>

							<li class="user-addresslist">
								
								
								
								<div class="new-addr-btn">
									
									<span class="new-addr-bar">|</span>
									
								</div>
							</li>
							<li class="user-addresslist">
					
								
								
								<div class="new-addr-btn">
									
								</div>
							</li> -->
						</ul>
						<div class="clear"></div>
						<a class="new-abtn-type" data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0}">添加新地址</a>
						<!--例子-->
						<div class="am-modal am-modal-no-btn" id="doc-modal-1">

							<div class="add-dress">

								<!--标题 -->
								<div class="am-cf am-padding">
									<div class="am-fl am-cf"></div>
								</div>
								

								<div class="am-u-md-12 am-u-lg-8" style="margin-top: 20px;">
									<form class="am-form am-form-horizontal">

										

										

										
										<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
												
						
											</div>
										</div>
									</form>
								</div>

							</div>

						</div>

					</div>

					<script type="text/javascript">
						$(document).ready(function() {	
							$("#comment").click(function() {
								//class="active"
								var a = $(this);
								$(".menu").find("li").each(function (){
									$(this).attr("class",null);
								});
								a.parent().addClass("active");
								$("#jsp").load("test_comment.jsp",function(data,status){
									
								});});
							$("#refund").click(function() {
								var a = $(this);
								$(".menu").find("li").each(function (){
									$(this).attr("class",null);
								});
								a.parent().addClass("active");
								$("#jsp").load("test_refund.jsp",function(data,status){
									
								});});
							
								$("#order").click(function() {
									var a = $(this);
									$(".menu").find("li").each(function (){
										$(this).attr("class",null);
									});
									a.parent().addClass("active");
								$("#jsp").load("test_order.jsp",function(data,status){
									
								});});
							
								
								$("#address").click(function() {
									var a = $(this);
									$(".menu").find("li").each(function (){
										$(this).attr("class",null);
									});
									a.parent().addClass("active");
									$("#jsp").load("test_address.jsp",function(data,status){
										
									});
								});
							$("#question").click(function() {
								var a = $(this);
								$(".menu").find("li").each(function (){
									$(this).attr("class",null);
								});
								a.parent().addClass("active");
								$("#jsp").load("test_question.jsp",function(data,status){
									
								});
							});
							
							$("#foot").click(function() {
								var a = $(this);
								$(".menu").find("li").each(function (){
									$(this).attr("class",null);
								});
								a.parent().addClass("active");
								$("#jsp").load("test_foot.jsp",function(data,status){
									
								});
							});
							
								$("#coupon").click(function() {
									var a = $(this);
									$(".menu").find("li").each(function (){
										$(this).attr("class",null);
									});
									a.parent().addClass("active");
								$("#jsp").load("test_coupon.jsp",function(data,status){
									
								});
							});
                            $("#information").click(function() {
                            	var a = $(this);
								$(".menu").find("li").each(function (){
									$(this).attr("class",null);
								});
								a.parent().addClass("active");
								$("#jsp").load("test_information.jsp",function(data,status){
									
								});
							});
                            
                            $("#collection").click(function() {
                            	var a = $(this);
								$(".menu").find("li").each(function (){
									$(this).attr("class",null);
								});
								a.parent().addClass("active");
								$("#jsp").load("test_collection.jsp",function(data,status){
									
								});
							});
                            $("#news").click(function() {
                            	var a = $(this);
								$(".menu").find("li").each(function (){
									$(this).attr("class",null);
								});
								a.parent().addClass("active");
								$("#jsp").load("test_news.jsp",function(data,status){
									
								});
							});
								$("#change").click(function() {
									var a = $(this);
									$(".menu").find("li").each(function (){
										$(this).attr("class",null);
									});
									a.parent().addClass("active");
								$("#jsp").load("test_change.jsp",function(data,status){
									
								});
							});
     							$("#test_bill").click(function() {
     								var a = $(this);
    								$(".menu").find("li").each(function (){
    									$(this).attr("class",null);
    								});
    								a.parent().addClass("active");
								$("#jsp").load("test_bill.jsp",function(data,status){
									
								});
							});
     							$("#test_mydeposit").click(function(){
     								var a = $(this);
    								$(".menu").find("li").each(function (){
    									$(this).attr("class",null);
    								});
    								a.parent().addClass("active");
									$("#jsp").load("test_mydeposit.jsp",
											function(data, status) {
											});
								});
     							$("#test_recharge").click(function(){
     								var a = $(this);
    								$(".menu").find("li").each(function (){
    									$(this).attr("class",null);
    								});
    								a.parent().addClass("active");
									$("#jsp").load("test_pay.jsp",
											function(data, status) {

											});
								});
					
							$(".new-option-r").click(function() {
								var a = $(this);
								$(".menu").find("li").each(function (){
									$(this).attr("class",null);
								});
								a.parent().addClass("active");
								$(this).parent('.user-addresslist').addClass("defaultAddr").siblings().removeClass("defaultAddr");
							});
							
							var $ww = $(window).width();
							if($ww>640) {
								$("#doc-modal-1").removeClass("am-modal am-modal-no-btn")
							}
							
						})
					</script>

					<div class="clear"></div>

				</div>
				
			</div>

			<aside class="menu">
				<ul>
				<br>
					<li class="">
						<b style="font-size: 30px;color: gray;">个人中心</b>
					</li>
					<br>
					<li class="">
						<b style="font-size: 20px;color: silver;">个人资料</b>
						<ul>
							<li> <a id="information">个人信息</a></li>
							<li> <a id="question" >安全设置</a></li>
							<li> <a id="address">收货地址</a></li>
						</ul>
					</li>
					<li class="">
						<b style="font-size: 20px;color: silver;">我的交易</b>
						<ul>
							<li><a id="order">订单管理</a></li>
							
							<li> <a id="change">退款售后</a></li>
						</ul>
					</li>
					<li class="">
						<b style="font-size: 20px;color: silver;">我的资产</b>
						<ul>
							<li> <a id="coupon">优惠券 </a></li>
						
							<li> <a id="test_bill" >账单明细</a></li>
							
							<li> <a id="test_recharge" >充值</a></li>
							
							<li><a id="test_mydeposit">我的充值记录</a></li>
						</ul>
					</li>

					<li class="">
						<b style="font-size: 20px;color: silver;">我的小窝</b>
						<ul>
							<!-- <li> <a id="collection">收藏</a></li> -->
							<li> <a id="foot">足迹</a></li>
							<li> <a id="comment">评价</a></li>
							<!-- <li> <a id="news">消息</a></li> -->
						</ul>
					</li>

				</ul>

			</aside>
		</div>

	</body>

</html>