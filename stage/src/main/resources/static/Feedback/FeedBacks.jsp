<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		
		<title>意见反馈</title>
		<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="css/froala_editor.min.css" rel="stylesheet" type="text/css">
		<style type="text/css">
			#preview {
				margin-left: 40px;
				width: 200px;
				height: 200px;
				border: 1px solid #E1E1E1;
			}
			
			#imghead {
				filter: progid: DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);
			}
			
			header {
				width: 100%;
				border: 1px solid #e1e1e1;
				margin: 0 auto;
				background: #fff;
			}
			
			
			h2 {
				margin: 60px;
				color: #7c7c7c;
			}
			
			.left {
				float: left;
				width: 65%;
				border-right: 1px dashed #ccc;
			}
			
			.right {
				float: right;
				width: 30%;
				text-align: center;
				border-radius: 14px;
			}
			
			.right a {
				margin-top: 20px;
				margin-right: 90px;
				float: right;
				width: 70%;
				font-size: 18px;
				letter-spacing: 3px;
				padding: 2px;
			}
			
			#but {
				float: right;
				margin-top: 10px;
				margin-right: 25%;
				background: #7C7C7C;
				color: #fff;
				width: 250px;
				height: 50px;
				line-height: 50px;
				text-align: center;
				border-radius: 14px;
			}
			
			p {
				height: 340px;
				width: 500px;
				color: #7C7C7C;
			}
			
			.hover {
				background: #134364;
				color: #fff;
			}
			
			.hover:hover {
				background: #000;
			}
		</style>
			<script src="js/libs/jquery-1.11.1.min.js"></script>
	<script src="js/froala_editor.min.js"></script>
	<script src="js/plugins/tables.min.js"></script>
	<script src="js/plugins/lists.min.js"></script>
	<script src="js/plugins/colors.min.js"></script>
	<script src="js/plugins/media_manager.min.js"></script>
	<script src="js/plugins/font_family.min.js"></script>
	<script src="js/plugins/font_size.min.js"></script>
	<script src="js/plugins/block_styles.min.js"></script>
	<script src="js/plugins/video.min.js"></script>
		<script type="text/javascript">
		$(function(){
			$("#submit_form").click(function(){
				if($("#edit p:last").html() == "<br>")
					{
					alert("您填写的反馈内容为空，请认真对待！");
					 return ;
					}
				if($("#QQ_Email").val() == " "||$("#QQ_Email").val() == "优先使用QQ，方便在线沟通")
				{
					alert("您还没有填写您的联系方式，这使得我们无法在第一时间回复您，请填写联系方式后再提交");
					
					 return ;	
					}
				
				var qq = $("#QQ_Email").val();
				var comment = $("#edit p:last").html();
				//将信息提交给后台
				$.post("../../FeedBack/SubmitFeedBack","qq="+qq+"&comment="+comment,function(msg){
					alert("你的意见我们将认真审查！");
					$(location).prop("href","../home/home.jsp");
				});
				
				
				
			
			});
			
			//页面一加载，判断用户是否登录，根据结果改变左上角的样式
	    	  $.post("../../User/check_login",function(msg,status){
	    		  if(msg.code==200){
	    			  //登录失败
	    		    alert("您还没有登录，马上为您跳转到登录界面");
	    	// $(location).prop('href', '../home/login.jsp');
	    		     
	    		  }
	    	});
			
			
			
			
			
		});
		</script>
		
	</head>

	<body>
		<header>
			<img src="img/banner.jpg" style="width: 100%;height: 130px;" />
			<div class="left">
				<h2>反馈内容:</h2>
				<section id="editor" style="width: 90%;margin: auto;text-align: left;">
					<div id='edit' style="margin-top: 30px;">
					
					</div>
				</section>
			
				</section>
				
			&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;请留下您的联系方式:
			
				
			E-Mail OR QQ		<input name="searchkey" type="text" id="QQ_Email" value="优先使用QQ，方便在线沟通" onFocus="this.value=''" style="width: 450px;height: 40px;margin: 50px;color: #7c7c7c;"  /><br />
					<input type="submit" id="submit_form" class="hover" value="提交" style="border-radius: 14px;margin-left: 50px;width: 150px;height: 50px;" />
				
			</div>
			<div class="right">
				
				<a style="color: #7C7C7C;">如果您在使用的过程中遇到疑问，或者有任何的意见或者建议，欢迎随时向我们反馈，我们会尽快回复您的问题，并依据您的反馈，不断完善。</a>
			</div>
		</header>

	</body>

	<script type="text/javascript">
		function previewImage(file) {
			var MAXWIDTH = 200;
			var MAXHEIGHT = 200;
			var div = document.getElementById('preview');
			if (file.files && file.files[0]) {
				div.innerHTML = '<img id=imghead>';
				var img = document.getElementById('imghead');
				img.onload = function() {
					var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
					img.width = rect.width;
					img.height = rect.height;
					//                 img.style.marginLeft = rect.left+'px';
					img.style.marginTop = rect.top + 'px';
				}
				var reader = new FileReader();
				reader.onload = function(evt) {
					img.src = evt.target.result;
				}
				reader.readAsDataURL(file.files[0]);
			} else //兼容IE
			{
				var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
				file.select();
				var src = document.selection.createRange().text;
				div.innerHTML = '<img id=imghead>';
				var img = document.getElementById('imghead');
				img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
				var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
				status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
				div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;" + sFilter + src + "\"'></div>";
			}
		}

		function clacImgZoomParam(maxWidth, maxHeight, width, height) {
			var param = {
				top: 0,
				left: 0,
				width: width,
				height: height
			};
			if (width > maxWidth || height > maxHeight) {
				rateWidth = width / maxWidth;
				rateHeight = height / maxHeight;
				if (rateWidth > rateHeight) {
					param.width = maxWidth;
					param.height = Math.round(height / rateWidth);
				} else {
					param.width = Math.round(width / rateHeight);
					param.height = maxHeight;
				}
			}
			param.left = Math.round((maxWidth - param.width) / 2);
			param.top = Math.round((maxHeight - param.height) / 2);
			return param;
		}
		$(function() {
			$('#edit').editable({
				inlineMode: false,
				alwaysBlank: true
			})
		});
	</script>
</html>