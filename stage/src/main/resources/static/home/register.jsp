<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="stylesheet"
	href="../AmazeUI-2.4.2/assets/css/amazeui.min.css" />
<link rel="stylesheet"
	href="../../static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="./css/font.css">
		<link rel="stylesheet" href="./css/xadmin.css">
<link href="../css/dlstyle.css" rel="stylesheet" type="text/css">
<script src="../../static/js/jquery-3.1.1.min.js"></script>
<script src="../../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="./lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="./js/xadmin.js"></script>
<script type="text/javascript">
	$(function() {
		var sp = $("#sp");
		var num = sp[0].offsetWidth;

		$("#zhuce").click(function() {
			var i = 0;
			var sp = $("#sp");
			var sp1 = $("#sp1");
			var sp2 = $("#sp2");
			var sp_name = $("#sp_name");
			var sp_phone = $("#sp_phone");
			var sp_password = $("#sp_password");
			var name = $("#name").val();
			var email = $("#email").val();
			var phone = $("#phone").val();
			var password = $("#password").val();
			var passwordRepeat = $("#passwordRepeat").val();
			var pcode = $("#pcode").val();
			if(name == ""){
				i++;
				sp_name.empty();
				sp_name.append("用户名不能为空");
			}
			if(email == ""){
				i++;
				sp.empty();
				sp.append("邮箱不能为空");
			}
			if(phone == ""){
				i++;
				sp_phone.empty();
				sp_phone.append("电话不能为空");
			}
			if(password == ""){
				i++;
				sp_password.empty();
				sp_password.append("密码不能为空");
			}
			if(passwordRepeat == ""){
				i++;
				sp1.empty();
				sp1.append("重复密码不能为空");
			}
			if(pcode == ""){
				i++;
				sp2.empty();
				sp2.append("验证码不能为空");
			}
			if(sp[0].offsetWidth != num)
				return;
			if(sp_password[0].offsetWidth != num)
				return;
			if(sp_phone[0].offsetWidth != num)
				return;
			if(sp_name[0].offsetWidth != num)
				return;
			if(sp1[0].offsetWidth != num)
				return;
			if(sp2[0].offsetWidth != num)
				return;
			
			
			if(i==0){
				 layer.msg("注册中，请稍等", {
						icon : 1,
						time : 5000
					});
				$.ajax({
				url : "${pageContext.request.contextPath }/User/zhuce",
				data : $("form").serialize(),
				type : "post",
				
				success : function(msg) {
					if(msg.code==100){
						 layer.msg("注册成功！马上为您跳转登录界面！", {
	    						icon : 1,
	    						time : 5000
	    					});
						setTimeout(function(){
							$(location).prop("href","login.jsp");
							},5000);
						
					}else{
						 layer.msg("注册失败！", {
	    						icon : 2,
	    						time : 5000
	    					});
					}
					
				},
				error : function() {
					 layer.msg("error！", {
 						icon : 2,
 						time : 5000
 					});
				}
			}); 
			}else{
			}
			
			
		});
		
		$("#name").change(function (){
			var v = this.value;
			if(null != v && "" != v){
				$("#sp_name").empty();
				$("#sp_name").append("&nbsp;");
			}
		});
		
		$("#phone").change(function (){
			var v = this.value;
			if(null != v && "" != v){
				$("#sp_phone").empty();
				$("#sp_phone").append("&nbsp;");
			}
		});
		
		$("#password").change(function (){
			var v = this.value;
			if(null != v && "" != v){
				$("#sp_password").empty();
				$("#sp_password").append("&nbsp;");
			}
		});

		$("#email").change(
				function() {
					var email = this.value;
					if("" == email){
						$("#sp").empty();
						$("#sp").append("&nbsp;");
						return;
					}
					$.ajax({
						url : "${pageContext.request.contextPath }/User/validateEmail",
						data : "email=" + email,
						type : "post",
						async : false,
						success : function(msg) {
							$("#sp").empty();
							if (msg.code == 200) {
								$("#sp").append(msg.map.msg);
							} else{
								$("#sp").append("&nbsp;");
							}
						},
						error : function(msg) {
							layer.msg("error！", {
		 						icon : 2,
		 						time : 5000
		 					});
						}

					});
				});
		
		$("#passwordRepeat").change(function(){
			$("#sp1").empty();
			var pass = $("#password").val();
			var passes = $("#passwordRepeat").val();
			if(pass != passes){
				$("#sp1").append("密码不一致");
			}else{
				$("#sp1").append("&nbsp;");
			}
		});
		
		
		$("#pcode").change(function(){
			var pcode = $("#pcode").val();
			$.ajax({
				url : "${pageContext.request.contextPath }/User/docode",
				data : "pcode=" + pcode,
				type : "post",
				success : function(msg) {
					$("#sp2").empty();
					if (msg.code == 200) {
					$("#sp2").append(msg.map.msg);
					$("#pcode").val("");
					document.getElementById('imgVcode').src='${pageContext.request.contextPath }/User/pcode?'+new Date();
					}else{
						$("#sp2").append("&nbsp;");
					}
				},
				error : function(msg) {
					layer.msg("error！", {
 						icon : 2,
 						time : 5000
 					});
				}

			});
		});
	});
	
	
</script>
</head>

<body>

	<div class="login-boxtitle">
		<a href="home/demo.html"><img alt="" src="../images/logobig.png" /></a>
	</div>

	<div class="res-banner">
		<div class="res-main">
			<div class="login-banner-bg">
				<span></span><img src="../images/big.jpg" />
			</div>
			<div class="login-box">

				<div class="am-tabs" id="doc-my-tabs">
					<div
						style="text-align: center; font-family: Georgia, serif; font-weight: bold; font-size: 30px;">注册</div>
					<div class="am-tabs-bd">
						<div class="am-tab-panel am-active">
							<form method="post">
								<div class="user-pass">
									<label for="password"><i
										class="glyphicon glyphicon-user"
										style="margin: 10px 0px 15px 5px;"></i></label> <input type="text"
										name="name" id="name" placeholder="用户名">
								</div>
								<span id="sp_name" style="font-size: 10px;color: red;">&nbsp;</span>

								<div class="user-email">
									<label for="email"><i
										class="glyphicon glyphicon-envelope"
										style="margin: 10px 0px 15px 5px;"></i></label> <input type="email"
										name="email" id="email" placeholder="请输入邮箱账号"> 
								</div>
								<span id="sp" style="font-size: 10px;color: red;">&nbsp;</span>
								<div class="user-phone">
									<label for="phone"><i
										class="am-icon-mobile-phone am-icon-md"></i></label> <input type="tel"
										name="phone" id="phone" placeholder="请输入手机号">
								</div>
								<span id="sp_phone" style="font-size: 10px;color: red;">&nbsp;</span>

								<div class="user-pass">
									<label for="password"><i class="am-icon-lock"
										style="margin: 10px 0px 15px 5px;"></i></label> <input type="password"
										name="password" id="password" placeholder="设置密码">
								</div>
								<span id="sp_password" style="font-size: 10px;color: red;">&nbsp;</span>

							</form>
							<form method="post">
								<div class="user-pass">
									<label for="passwordRepeat"><i class="am-icon-lock"
										style="margin: 10px 0px 15px 5px;"></i></label> <input type="password"
										name="passwordRepeat" id="passwordRepeat" placeholder="确认密码">
								</div>
								<span id="sp1" style="font-size: 10px;color: red;">&nbsp;</span>
								<div class="user-email">
									<label for="pcode"><i class="glyphicon glyphicon-picture"
								style="margin: 10px 0px 0px 5px;"></i></label> <input type="text"
										name="pcode" id="pcode" placeholder="请输入验证码"
										style="width: 40%" /> <a href="JavaScript:;"
										onclick="document.getElementById('imgVcode').src='${pageContext.request.contextPath }/User/pcode?'+new Date()">
										<!-- 单击图片都可以换下一张 --> <img id='imgVcode'
										src="${pageContext.request.contextPath }/User/pcode" />
									</a>
								</div>
								<span id="sp2" style="font-size: 10px;color: red;">&nbsp;</span>
							</form>

							<div class="am-cf">
								<input type="submit" name="" value="注册" id="zhuce"
									class="am-btn am-btn-primary am-btn-sm am-fl">
							</div>

						</div>


					</div>
				</div>

			</div>
		</div>

</body>
</html>