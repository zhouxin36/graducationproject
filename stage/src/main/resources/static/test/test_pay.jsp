<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>充值页面</title>
</head>

<script type="text/javascript">
	$(function() {
		$.post("../../User/check_login", function(msg, status) {

			if (msg.code == 200) {
				layer.msg("请先登录,马上为你跳转登录界面！", {
					icon : 5,
					time : 3000
				});

				setTimeout(function() {
					window.location.href = "../home/login.jsp";
				}, 3000);

			}
		});

	});
</script>

<body>

	<div style="width: 1200px; height: 660px">
		<form id="orderForm" action="/ECC/deposit/payOrder" method="post">

			<h1 style="color: black; margin-top: 130px; margin-left: 150px">选择银行：</h1>
			<br /> <img src="../images/bank_img/icbc.bmp"
				style="width: 154px; height: 33px; margin-left: 150px"
				align="middle" /> <input type="radio" name="pd_FrpIds"
				value="ICBC-NET-B2C" checked="checked" />工商银行&nbsp;&nbsp;&nbsp;&nbsp;

			<img src="../images/bank_img/bc.bmp"
				style="width: 154px; height: 33px; margin-left: 50px" align="middle" />
			<input type="radio" name="pd_FrpIds" value="BOC-NET-B2C" />中国银行&nbsp;&nbsp;&nbsp;&nbsp;

			<img src="../images/bank_img/abc.bmp" align="middle"
				style="width: 154px; height: 33px; margin-left: 50px" /> <input
				type="radio" name="pd_FrpIds" value="ABC-NET-B2C" style="" />农业银行 <br />
			<br /> <img src="../images/bank_img/bcc.bmp" align="middle"
				style="width: 154px; height: 33px; margin-left: 150px" /> <input
				type="radio" name="pd_FrpIds" value="BOCO-NET-B2C" />交通银行&nbsp;&nbsp;&nbsp;&nbsp;

			<img src="../images/bank_img/pingan.bmp" align="middle"
				style="width: 154px; height: 33px; margin-left: 50px" /> <input
				type="radio" name="pd_FrpIds" value="PINGANBANK-NET" />平安银行&nbsp;&nbsp;&nbsp;&nbsp;

			<img src="../images/bank_img/ccb.bmp" align="middle"
				style="width: 154px; height: 33px; margin-left: 50px" /> <input
				type="radio" name="pd_FrpIds" value="CCB-NET-B2C" />建设银行 <br /> <br />

			<img src="../images/bank_img/guangda.bmp" align="middle"
				style="width: 154px; height: 33px; margin-left: 150px" /> <input
				type="radio" name="pd_FrpIds" value="CEB-NET-B2C" />光大银行&nbsp;&nbsp;&nbsp;&nbsp;

			<img src="../images/bank_img/cmb.bmp" align="middle"
				style="width: 154px; height: 33px; margin-left: 50px" /> <input
				type="radio" name="pd_FrpIds" value="CMBCHINA-NET-B2C" />招商银行
			<!-- <h1 style="color:black;margin-top: 30px;margin-left: 150px; " >支付宝支付：</h1><br/>
						<br/>
						
 -->
			<button type="submit" class="btn btn-success" style="width: 154px; height: 33px; margin-left: 50px"> 充值</button>
		</form>
	</div>



</body>
</html>