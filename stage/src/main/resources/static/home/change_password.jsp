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
<link href="../css/dlstyle.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./css/font.css">
		<link rel="stylesheet" href="./css/xadmin.css">
<script src="../../static/js/jquery-3.1.1.min.js"></script>
<script src="../../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="./lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="./js/xadmin.js"></script>
<script type="text/javascript">
	$(function() {
		var num = $("#sp_password")[0].offsetWidth;

		$("#change_password").click(function() {
			var i = 0;
			var password = $("#password").val();
			var passwordRepeat = $("#passwordRepeat").val();
			if(password == ""){
				i++;
				$("#sp_password").empty();
				$("#sp_password").append("密码不能为空");
			}
			if(passwordRepeat == ""){
				i++;
				$("#sp_passwordRepeat").empty();
				$("#sp_passwordRepeat").append("重复密码不能为空");
			}
			if($("#sp_password")[0].offsetWidth != num)
				return;
			if($("#sp_passwordRepeat")[0].offsetWidth != num)
				return;
			
			
			if(i==0){
				$.ajax({
				url : "../../User/changePass",
				data : "password="+password,
				type : "post",
				success : function(msg) {
					if(msg.code==100){
						 layer.msg("修改成功!马上为您跳转登录界面！", {
	    						icon : 1,
	    						time : 5000
	    					});
						 setTimeout(function(){
							 $(location).prop('href', '../home/login.jsp');
								},5000);
					}else{
						 layer.msg("操作失败！请正规操作！", {
	    						icon : 2,
	    						time : 3000
	    					});
					}
					
				},
				error : function() {
					layer.msg("error！", {
						icon : 2,
						time : 3000
					});

				}
			}); 
			}else{
			}
			
			
		});
		$("#password").change(function (){
			var v = this.value;
			if(null != v && "" != v){
				$("#sp_password").empty();
				$("#sp_password").append("&nbsp;");
			}
			
			$("#sp_passwordRepeat").empty();
			var pass = $("#password").val();
			var passes = $("#passwordRepeat").val();
			if(pass != passes){
				$("#sp_passwordRepeat").append("密码不一致");
			}else{
				$("#sp_passwordRepeat").empty();
				$("#sp_passwordRepeat").append("&nbsp;");
			}
		});

		
		$("#passwordRepeat").change(function(){
			$("#sp_passwordRepeat").empty();
			var pass = $("#password").val();
			var passes = $("#passwordRepeat").val();
			if(pass != passes){
				$("#sp_passwordRepeat").append("密码不一致");
			}else{
				$("#sp_passwordRepeat").empty();
				$("#sp_passwordRepeat").append("&nbsp;");
			}
			
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
						style="text-align: center; font-family: Georgia, serif; font-weight: bold; font-size: 30px;">修改密码</div>
					<div class="am-tabs-bd">
						<div class="am-tab-panel am-active">
							<form method="post">

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
								<span id="sp_passwordRepeat" style="font-size: 10px;color: red;">&nbsp;</span>
							</form>

							<div class="am-cf">
								<input type="submit" name="" value="修改密码" id="change_password"
									class="am-btn am-btn-primary am-btn-sm am-fl">
							</div>

						</div>


					</div>
				</div>

			</div>
		</div>

</body>
</html>