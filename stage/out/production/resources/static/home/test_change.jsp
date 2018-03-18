<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退款售后</title>
<script type="text/javascript">
       $(function(){
    	   
    	   var all = "";
    	    $.post("../../Refund/userGetRefund",function(msg){
    	    	if(msg.code==100){
    	    		console.log(msg);
    	    		var list = msg.map.list;
    	    		$.each(list,function(index,item){
    	    			$.post("../../Order/selectSorderByForderId","id="+item.forderid,function(msg_two){
                             var list_two = msg_two.map.list;
                                    
                                 $.each(list_two,function(index_two,item_two){
                                	  all += "<tr><td>" + item_two.forderId +"</td><td>"
                                	      +   item_two.name   +"</td><td>"
                                	      +   item_two.price   +"</td><td>"
                                	      +   item_two.number  +"</td><td>"
                                	      +  parseInt(item_two.price)*parseInt(item_two.number)  +"</td><td>"
                                	      +  "等待商家审批" +"</td></tr>";
                                 });
                             
                                 $("#tbody").html(all);
                                
    	    					     				
    	    			});
    	    		});
    	    	         
    	    	
    	    	}
    	    });
    	   
       });

</script>
</head>
<body>
		
		<div class="center">
			<div class="col-main">
				<div class="main-wrap">

					<div class="user-order">

						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">退换货管理</strong> / <small>Change</small></div>
						</div>
						<hr/>

						<div class="am-tabs am-tabs-d2 am-margin" data-am-tabs>

							<ul class="am-avg-sm-2 am-tabs-nav am-nav am-nav-tabs">
								<li class="am-active"><a href="#tab12">退款管理</a></li>
							

							</ul>
                           <table class="table table-striped">
									  <tr>
									   <th>订单编号</th>
									   <th>商品名</th>
									   <th>商品单价</th>
                                       <th>购买数量</th>
									   <th>合计</th>
									   <th>状态</th>
									  </tr>
									  <tbody id="tbody"></tbody>
									 </table>
							

						</div>
					</div>

				</div>
				
	</body>
</html>