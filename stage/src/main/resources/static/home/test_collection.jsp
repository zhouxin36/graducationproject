<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">

		<!-- <link href="../AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">

		<link href="../css/personal.css" rel="stylesheet" type="text/css">-->
	<!-- 	<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script> -->
		<link href="../css/colstyle.css" rel="stylesheet" type="text/css"> 
<title>我的收藏</title>
<script type="text/javascript">
  $(function(){
	  build_main_table();
	  function build_main_table(){
		  
		  $.post("../../Favorite/getMyFavorite",function(data,status){
			  
			  console.log(data);
			  
			  if(data.code==100){
				  var div_content = $("#div_content");
				   div_content.empty();
				   
				   var list = data.map.list;
				   $.each(list,function(index,product){
					   var div_s_pic = $("<div></div>").addClass("s-pic");
					   var img  = $("<img class='s-pic-img s-guess-item-img'></img>")
					   .attr("src","../images/0-item_pic.jpg_220x220.jpg");
					   var a_s_pic_link = $("<a class='s-pic-link'></a>").append(img);
					   
					   div_s_pic.append(a_s_pic_link);
					   
					   var div_s_info = $("<div></div>").addClass("s-info");
					   
					   var  a = $("<a href='../home/introduction.jsp'></a>").append(product.name);
					    var div_s_title = $("<div></div>").addClass("s-title").append(a);
					   
			
					    var em1  = $("<em class='s-value'></em>").append(parseInt(product.price));
					    var span1 = $("<span></span>").addClass("s-price")
					    .append("<em class='s-price-sign'>¥</em>").append(em1);
					    var em2 = $("<em class='s-value'></em>").append(parseInt(product.price)+100);
					    var span2 =  $("<span></span>").addClass("s-history-price")
					    .append("<em class='s-price-sign'>¥</em>").append(em2);
					    var div_s_price_box = $("<div></div>").addClass("s-price-box")
					    .append(span1).append(span2);
					   
					    
					    var span3  =  $("<span></span>").addClass("s-comment").append("好评: 99.03%");
					    var span4 =   $("<span></span>").addClass("s-sales").append("月销:"+product.monthSale);
					    var div_s_extra_box = $("<div></div>").addClass("s-extra-box").append(span3).append(span4);
					    
					    div_s_info.append(div_s_title).append(div_s_price_box).append(div_s_extra_box);
				 
					    var join_shopcart = $("<span class='ui-btn-loading-before buy'>加入购物车</span>");
				          join_shopcart.click(function(){
				        	 
				        	  $.post("../../Order/addProductToSorder","productId="+product.id,function(data,status){
				        		if(data.code==100)
				        			layer.msg("加入购物车成功！", {
				        								icon : 6,
				        								time : 3000
				        							});
				        		 console.log(data); 
				        	  });
				        	 
				          }); 
					    var div_s_tp = $("<div></div>").append("<a href='../home/search.jsp?productname="+product.name+"'><span class='ui-btn-loading-before'>找相似</span></a>").
				                       append("<i class='am-icon-shopping-cart'></i>").
				                       append(join_shopcart);
				          
				  
				          
				       var  div_s_item  = $("<div></div>").addClass("s-item");
				       div_s_item.append(div_s_pic).append(div_s_info).append(div_s_tp);
				       var  div_s_item_wrap  = $("<div></div>")
				       .addClass("s-item-wrap").append(div_s_item);
				         
				       div_content.append(div_s_item_wrap);
				   });
			  }
			  
		  })  
	  }
	   
	   
	   
  });
</script>







</head>
	<body>
		
		<div class="center">
			<div class="col-main">
				<div class="main-wrap">

					<div class="user-collection">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">我的收藏</strong> / <small>My&nbsp;Collection</small></div>
						</div>
						<hr/>

						<div class="you-like">
							<div class="s-bar">
								我的收藏
								<a class="am-badge am-badge-danger am-round">降价</a>
								<a class="am-badge am-badge-danger am-round">下架</a>
							</div>
							<div class="s-content" id="div_content">
								<div class="s-item-wrap">
									<div class="s-item">

										
									</div>
								</div>

								



							</div>

							<div class="s-more-btn i-load-more-item" data-screen="0"><i class="am-icon-refresh am-icon-fw"></i>更多</div>

						</div>

					</div>

				</div>
					</div>	</div>
	</body>
</html>