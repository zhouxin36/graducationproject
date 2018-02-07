<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>安全问题</title>
		<script type="text/javascript">
	           $(function(){
	        	  
	        	   build_main_question();
	        	   
	        	   //点击按钮修改或者插入问题
	        	   $("#update_question_btn").click(function(){
	        		   if( $("#question_one option:selected").val()=="1")
	        			  {
	        			   layer.msg("问题1不允许为空！", {
	       					icon : 5,
	       					time : 3000
	       				});
	        			   return ;
	        			  }
	        		   if($("#user-answer1").val()==""){
	        			   layer.msg("问题1的答案不允许为空！", {
		       					icon : 5,
		       					time : 3000
		       				});
	        			   return ;
	        		   }
	        		   
	        		   $.post("../../question/getQuestionById",function(data,status){
	        			  
	        			   $("#user_questionType").val( $("#question_one option:selected").val());
	        			   if(data.code==100){
	        			    	
	        			    	
	        			    	//修改问题答案
	        			    	$.post("../../question/updateUserQuestion",$("#question_form").serialize(),function(data,status){
	        			    		
	        			    	});
	        			    }else{
	        			    	//添加问题
                                $.post("../../question/insertUserQuestion",$("#question_form").serialize(),function(data,status){
	        			    		
	        			    	});
	        			    }
	        			   layer.msg("成功！", {
	       					icon : 6,
	       					time : 3000
	       				});
	        		   });
	        		   
	        	   });
	        	   
	        	   
	        	   //建立问题主框架
	        	function   build_main_question(){
	        		              
	        		   $.post("../../question/getQuestionById",function(data,status){
	        			    if(data.code==100){
	        			    	console.log(data);
	        			    	var list = data.map.list[0];
	        			    	 $("#question_one ").val(list.questionType);
	        			    	
	        			    	 $("#user-answer1").val(list.questionAnswer);
	        			    
	        			    }else{
	        			    	layer.msg("你还没有设置任何安全问题！", {
	        						icon : 5,
	        						time : 3000
	        					});
	        			    }
	        		   });
	        	   }
	        	   
	           });
		
</script>


</head>

	<body>
		
		<div class="center">
			<div class="col-main">
				<div class="main-wrap">

					<div class="am-cf am-padding">
						<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">设置安全问题</strong> / <small>Set&nbsp;Safety&nbsp;Question</small></div>
					</div>
					<hr/>
					<!--进度条-->
					<div class="m-progress">
						<div class="m-progress-list">
							<span class="step-1 step">
                                <em class="u-progress-stage-bg"></em>
                               <!--  <i class="u-stage-icon-inner">1<em class="bg"></em></i>
                                <p class="stage-name">设置安全问题</p>
                            </span>
							<span class="step-2 step">
                                <em class="u-progress-stage-bg"></em>
                                <i class="u-stage-icon-inner">2<em class="bg"></em></i>
                                <p class="stage-name">完成</p>
                            </span> -->
							<span class="u-progress-placeholder"></span>
						</div>
						<div class="u-progress-bar total-steps-2">
							<div class="u-progress-bar-inner"></div>
						</div>
					</div>
					<form class="am-form am-form-horizontal" id="question_form">
						<div class="am-form-group select">
							<label for="user-question1" class="am-form-label">问题一</label>
							<div class="am-form-content">
								<select data-am-selected id="question_one">
									<option value="1" selected>请选择安全问题</option>
									<option value="2">您最喜欢的颜色是什么？</option>
									<option value="3">您的故乡在哪里？</option>
									<option value="4">您最喜欢的老师的名字？</option>
									<option value="5">您父亲的名字？</option>
									<option value="6">您母亲的名字？</option>
								</select>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-answer1" class="am-form-label">答案</label>
							<div class="am-form-content">
								<input type="text" id="user-answer1" name="questionAnswer" placeholder="请输入安全问题答案">
							</div>
						</div>
				          <input type="hidden" id="user_questionType" name="questionType" />
						

					</form>
<center><div class="am-btn am-btn-danger" id="update_question_btn">保存修改</div></center>
				</div>
	</body>

</html>