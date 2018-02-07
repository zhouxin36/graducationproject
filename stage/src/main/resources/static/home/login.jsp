<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="stylesheet" href="../AmazeUI-2.4.2/assets/css/amazeui.css" />
<link href="../css/dlstyle.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./css/font.css">
		<link rel="stylesheet" href="./css/xadmin.css">
<script src="../../static/js/jquery-3.1.1.min.js"></script>
<script src="../../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="../../static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<script src="./lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="./js/xadmin.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		var num = $("#sp_email")[0].offsetWidth
		$("#login_button").click(function() {
			var i = 0;
			var email = $("#login_email").val();
			var password = $("#login_password").val();
			var pcode = $("#pcode").val();
			if (email == "") {
				i++;
				$("#sp_email").empty();
				$("#sp_email").append("邮箱不能为空");
			}
			if (password == "") {
				i++;
				$("#sp_password").empty();
				$("#sp_password").append("密码不能为空");
			}
			if (pcode == "") {
				i++;
				$("#sp_pcode").empty();
				$("#sp_pcode").append("验证码不能为空");
			}
			if($("#sp_email")[0].offsetWidth != num)
				return;
			if($("#sp_password")[0].offsetWidth != num)
				return;
			if($("#sp_pcode")[0].offsetWidth != num)
				return;
			if (i == 0) {
				
				
				      $.post("../../User/login",$("#login_form").serialize(),function(msg,status){
				    	   //登录成功
				    	   console.log(msg);
				    	  if(msg.code==100){
				    	 $(location).prop('href', '../home/home.jsp');
				    	 }//登录失败
				    	 else{
				    		 $("#sp_lgoin_message").empty();
				    		 $("#change_image").click();
				    		 $("#sp_lgoin_message").append(msg.map.login_message);
				    	 }
				    	 
				    	 });
				  
					
			} else {
			}

		});
		$("#login_email").change(function (){
			var v = this.value;
			if(null != v && "" != v){
				$("#sp_email").empty();
				$("#sp_email").append("&nbsp;");
			}
		});
		
		$("#login_password").change(function (){
			var v = this.value;
			if(null != v && "" != v){
				$("#sp_password").empty();
				$("#sp_password").append("&nbsp;");
			}
		});
		$("#btn_forget").click(function() {
			//清空模态框数据
			reset_form($("#myModal form"));
			$("#myModal").modal("show");
		});
		function reset_form(ele) {
			//清除input输入内容
			$(ele)[0].reset();
			//清除父容器DIV样式
			$(ele).find("*").removeClass("has-success has-error");
			//清除span内容
			$(ele).find(".help-block").text("");
		}
		$("#email_m").change(
				function() {
					var email = this.value;
					$.ajax({
						url : "../../User/validateEmail",
						data : "email=" + email,
						type : "post",
						success : function(msg) {
							var sp = $("#email_m").next();
							sp.empty();
							if(msg.code == 100){
								sp.append("邮箱不存在");
							}
							if (msg.code == 200) {
								if(msg.map.msg !="邮箱存在"){
								sp.append(msg.map.msg);
								}
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
		$("#save1").click(function() {
			
			var sp2 = $("#email_m").next().text();
			var email = $("#email_m").val();
			var i = 0;
			var msg1 = "";
			if (sp2 != "") {
				i++;
				msg1 += "邮箱错误;";
			}
			
			if(i!=0){
				 layer.msg(msg1, {
						icon : 2,
						time : 5000
					});
			}else{
				 layer.msg("申请中，请稍后！", {
						icon : 1,
						time : 5000
					});
				$.ajax({
					url : "../../User/sendEmail",
					data : "email=" + email,
					type : "post",
					success : function(msg) {
						 layer.msg("申请成功，请到邮箱查看！", {
	    						icon : 1,
	    						time : 5000
	    					});
						$("#myModal").modal("hide");
					},
					error : function(msg) {
						 layer.msg("error！", {
	    						icon : 2,
	    						time : 5000
	    					});
					}

				});
			}
			
		});
		$("#pcode")
				.change(
						function() {
							var pcode = $("#pcode").val();
							$
									.ajax({
										url : "../../User/docode",
										data : "pcode=" + pcode,
										type : "post",
										success : function(msg) {
											$("#sp2").empty();
											if (msg.code == 200) {
												$("#sp_pcode").empty();
												$("#sp_pcode").append(msg.map.msg);
												$("#pcode").val("");
												document
														.getElementById('imgVcode').src = '${pageContext.request.contextPath }/User/pcode?'
														+ new Date();
											}else{
												$("#sp_pcode").empty();
												$("#sp_pcode").append("&nbsp;");
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
<body>
<!-- 模态框 -->
	<div id="myModal" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">找回密码</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="form1">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">email:</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="email_m" name="email"
									placeholder="email"> <span class="help-block"></span>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="save1">Save
						changes</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- 模态框 -->
	<div class="login-boxtitle">
		<a href="home.html"><img alt="logo" src="../images/logobig.png" /></a>
	</div>

	<div class="login-banner">
		<div class="login-main">
			<div class="login-banner-bg">
				<span></span><img src="../images/big.jpg" />
			</div>
			<div class="login-box">

				<h3 class="title">登录商城</h3>

				<div class="clear"></div>

				<div class="login-form">
				<span id="sp_lgoin_message" style="font-size: 10px; color: red;"></span>
					<form id="login_form">
						<div class="user-name">
							<label for="user"><i class="am-icon-user"
								style="margin: 10px 0px 15px 5px;"></i></label> <input type="text"
								name="email" id="login_email" placeholder="邮箱">
						</div>
						<span id="sp_email" style="font-size: 10px; color: red;">&nbsp;</span>
						<div class="user-pass">
							<label for="password"><i class="am-icon-lock"
								style="margin: 10px 0px 15px 5px;"></i></label> <input type="password"
								name="password" id="login_password" placeholder="请输入密码">
						</div>
						<span id="sp_password" style="font-size: 10px; color: red;">&nbsp;</span>
						<div class="user-email">
							<label for="pcode"><i class="glyphicon glyphicon-picture"
								style="margin: 10px 0px 0px 5px;"></i></label> <input type="text"
								name="pcode" id="pcode" placeholder="请输入验证码" style="width: 40%" />
							<a href="JavaScript:;" id="change_image"
								onclick="document.getElementById('imgVcode').src='${pageContext.request.contextPath }/User/pcode?'+new Date()">
								<!-- 单击图片都可以换下一张 --> <img id='imgVcode'
								src="${pageContext.request.contextPath }/User/pcode" />
							</a>
						</div>
						<span id="sp_pcode" style="font-size: 10px; color: red;">&nbsp;</span>
						<div class="clear"></div>

						<div class="login-form"></div>
				</form>
				</div>

				<div class="login-links">
					  <a
						href="../home/register.jsp" class="zcnext am-fr am-btn-default">注册</a>
				<button class="am-fr btn btn-info" id="btn_forget">忘记密码</button>
					<br />
				</div>

				<div class="am-cf">
					<input id="login_button" type="submit" name="" value="登 录"
						class="am-btn am-btn-primary am-btn-sm">
				</div>

			</div>
		</div>
	</div>


</body>

</html>