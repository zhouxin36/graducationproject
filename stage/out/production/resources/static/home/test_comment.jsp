<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>评论界面</title>
<script type="text/javascript">
  $(function(){
	 
	  //建立所有评论
	   $.post("../../Comment/getMyCommont",function(data,status){
		   build_table(1,data);
	   });
	  
	  
	 
	  
	  $("#tab1").click(function(){
		  $.post("../../Comment/getMyCommont",function(data,status){
			   build_table(1,data);
		   });
	  });
	  $("#tab2").click(function(){
		  $.post("../../Comment/getMyGoodComment",function(data,status){
			   build_table(2,data);
		   });
	  });
	  //建立表格
	  function build_table(number,msg){
		   var all = "";
		   $("#tab"+number+"_body").html(all);
		    if(msg.code==100){
		    	var list = msg.map.list;
		    	$.each(list,function(index,item){
		    		
		    		
		    		
		    		$.ajax({
		    			  type : "post" ,
		    			  data : "id=" + item.productId ,
		    			  async : "true",
		    			  url : "../../Product/SelectProductById",
		    			  dataType : "json",
		    			  success: function(products){
		    				  if(products.code ==200)
		    					  return ;
		    				  var product = products.map.product;
		    				
		    				  var newDate = new Date();
		    				  var timestamp = item.commentTime ;
		    				  newDate.setTime(timestamp);
		    				  
		    				  
		    				all += "<tr><td><a href='../home/introduction.jsp?id="+product.id+"'>" +  product.name +"</a></td><td>" ;
		    				  
		    				     if(item.favourComment == 3)
				              all  += "好评</td><td>" ;
				              else if(item.favourComment == 2)
				            	  all  += "中评</td><td>" ;
				            	  else
				            		  all  += "差评</td><td>" ;
				            		  
				               all +=    item.comment +"</td><td>" 
				                  + item.upvote +"</td><td>"
				                  + newDate.toLocaleDateString()
		    				  +"</td></tr>";
		    				  
		    				  $("#tab"+number+"_body").html(all);
		    			  }
		    			  
		    		});
		    		
		    		
		    		
		    		
		    		
		    		 
		    	});
		    	
		    	 // $("#tab"+number+"_body").html(all);
		    }
		  
		    else{
		    	layer.msg("好尴尬呀，你暂时还没有优秀的评论呢！", {
					icon : 0,
					time : 3000
				});
		    }
	  }
	  
	  
  });
</script>
</head>
<body>
		
		<div class="center">
			<div class="col-main">
				<div class="main-wrap">

					<div class="user-comment">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">评价管理</strong> / <small>Manage&nbsp;Comment</small></div>
						</div>
						<hr/>

						<div class="am-tabs am-tabs-d2 am-margin" data-am-tabs>

							<ul class="am-avg-sm-2 am-tabs-nav am-nav am-nav-tabs">
								<li class="am-active"><a href="#tab1" id="tab1">所有评价</a></li>
								<li><a href="#tab2" id="tab2">高质量评价</a></li>

							</ul>

							<div class="am-tabs-bd">
								<div class="am-tab-panel am-fade am-in am-active" id="tab1">

									<div class="comment-main">
							           <table class="table table-striped">
							           <tr>
							            <th>商品</th>
							            <th>品质</th>
							            <th>评论</th>
							            <th>点赞数</th>
							            <th>评论时间</th>
							           </tr>
							           <tbody id="tab1_body"></tbody>
							           </table>
									</div>

								</div>
								<div class="am-tab-panel am-fade" id="tab2">
									
									<div class="comment-main">
									<table class="table table-striped">
							           <tr>
							            <th>商品</th>
							            <th>品质</th>
							            <th>评论</th>
							            <th>点赞数</th>
							            <th>评论时间</th>
							           </tr>
							           <tbody id="tab2_body"></tbody>
							           </table>
									</div>									
									
								</div>
							</div>
						</div>

					</div>

				</div>
					</div>

				</div>
				
	</body>
</html>