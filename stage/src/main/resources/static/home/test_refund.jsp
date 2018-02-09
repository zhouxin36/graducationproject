<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
	
	    $(function(){
	    	//退款必须ForderId,每次访问此界面先查看ForderId是否存在
	    	     var forderId =  $("#refund_forderId").val();
	             if(forderId==""){
	            	 layer.msg("错误访问！马上为您跳转主界面！", {
	 					icon : 5,
	 					time : 3000
	 				});

				 setTimeout(function(){
					 $(location).prop("href","../home/home.jsp");
	 							},3000);
	             }
	        //填充上方的表格
	        $.post("../../Order/selectSorderByForderId","id="+forderId,function(msg){
	        	if(msg.code==200){
	        		layer.msg("错误访问！马上为您跳转主界面！", {
						icon : 5,
						time : 3000
					});

	setTimeout(function(){
		 $(location).prop("href","../home/home.jsp");
								},3000);
	        	}else{
	        		
	        		//将ForderId写到退款申请的表单中
	        		$("#form_forderId").val(forderId);
	        		var allmoney = 0;
	        		
	        		
	        		var list = msg.map.list;
	        		 var all = "";
	        		 
	        		$.each(list,function(index,item){
	        			 all += "<tr><td>"  + forderId + "</td><td>"
	                         +  item.name    + "</td><td>"
	                         +  item.price + "</td><td>"
	                         + item.number + "</td><td>"
	                         + parseInt(item.price)*parseInt(item.number)+"</td></tr>";
	        		     allmoney += parseInt(item.price)*parseInt(item.number);
	        		});
	        		$("#refund-money").val(allmoney);
	        		$("#tbody").html(all);
	        	}
	        });
	             
	    });
	</script>
<title>退款界面</title>
</head>
<body>
	<input type="hidden" id="refund_forderId" value="${param.id }">
		<div class="center">
			<div class="col-main">
				<div class="main-wrap">
					<!--标题 -->
					<div class="am-cf am-padding">
						<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">退换货申请</strong> / <small>Apply&nbsp;for&nbsp;returns</small></div>
					</div>
					<hr/>
					<div class="comment-list">
				
					
					
						<div id="product_item" >
						 <table class="table table-striped">
									  <tr>
									   <th>订单编号</th>
									   <th>商品名</th>
									   <th>商品单价</th>
                                       <th>购买数量</th>
									   <th>合计</th>
									  
									  </tr>
									  <tbody id="tbody"></tbody>
									 </table>
						</div>

						<div class="refund-main">
							<div class="item-comment">
								<div class="am-form-group">
									<label for="refund-type" class="am-form-label">退款类型</label>
									<div class="am-form-content">
										<select data-am-selected="" id="select_one">
											<option value="1" selected>仅退款</option>
											<option value="2">退款/退货</option>
										</select>
									</div>
								</div>
								
								<div class="am-form-group">
									<label for="refund-reason" class="am-form-label">退款原因</label>
									<div class="am-form-content">
										<select data-am-selected="" id="select_two">
											<option value="1" selected>请选择退款原因</option>
											<option value="2">不想要了</option>
											<option value="3">买错了</option>
											<option value="4">没收到货</option>											
											<option value="5">与说明不符</option>
										</select>
									</div>
								</div>

								<div class="am-form-group">
									<label for="refund-money" class="am-form-label">退款金额<span>（不可修改）</span></label>
									<div class="am-form-content">
										<input type="text" id="refund-money" value="0" readonly="readonly">
									</div>
								</div>
								<div class="am-form-group">
									<label for="refund-info" class="am-form-label">退款说明<span>（可不填）</span></label>
									<div class="am-form-content">
										<input type="text" id="select_supplement" placeholder="请输入退款说明"/>
									</div>
								</div>
							<div class="info-btn">
								<div class="btn btn-danger" id="refund_button">提交申请</div>
							</div>
							</div>
							
							
							
							
							<form method="post"  id="refund_form">
							 <input type="hidden" id="form_forderId" name="forderid"  /><br>
							
							 <input type="hidden" id="form_type" name="type" /><br>
							 <input type="hidden" id="form_reason" name="reason"  /><br>
							 <input type="hidden" id="form_supplement" name="supplement"  /><br>
							</form>
						</div>
					</div>
					<div class="clear"></div>
				</div>

				<!--底部-->
				
			</div>

			
		</div>

	</body>
	<script type="text/javascript">
	     $(function(){
	    	 
	    	 $("#refund_button").click(function(){
	    		 
	    		 var select_one = $("#select_one  option:selected").val();
	    		 var select_two = $("#select_two  option:selected").val();
	    		 if(select_two==1){
	    			 layer.msg("请选择退款原因！", {
	 					icon : 5,
	 					time : 3000
	 				});
	    			 return ;
	    		 }
	    		 var select_three = $("#select_supplement").val();
	    		$("#form_type").val(select_one);
	    		$("#form_reason").val(select_two);
	    		$("#form_supplement").val(select_three);
	    		 
	    		//提交退款申请
	    		$.post("../../Refund/saveRefund",$("#refund_form").serialize(),function(msg){
	                if(msg.code==100)
	                	{

	                	layer.msg("申请成功！", {
	                						icon : 6,
	                						time : 3000
	                					});
	                	setTimeout(function(){
	                		$(location).prop("href","../test/test_main.jsp?action=change");
							},3000);
	                	}
	                else{
	                	

	                	layer.msg("申请失败,为保证系统安全，即将为您跳转主界面！", {
	                						icon : 5,
	                						time : 3000
	                					});

	                	setTimeout(function(){
	                		$(location).prop("href","../home/home.jsp");
	                								},3000);
	                }
	    		});
	    		
	    	 });
	    	 
	     });
	</script>
</html>