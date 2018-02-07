<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>优惠券</title>
<script type="text/javascript">

$(function(){
	build_coupon(0);
	
	$("#tab2").click(function(){
		
		build_coupon(1);
	});
	//建立可用优惠券
    function build_coupon(statu){
    	
		$.post("../../Coupon/getMyCoupon","status="+statu,function(data,status){
			   if(data.code==100){
				   
				 console.log(data);
				       var list =  data.map.list ;
			    	   var main ;
			    	    if(statu==0)   
			    	    	 main =  $("#coupon-items");
			    	    else
			    	    	main =   $("#coupon-items_pass");
			      	main.empty();
			    	    for( var i = 0 ; i< list.length; i++){
			 	   var div_c_type_one = $("<div></div>").addClass("c-type");
			 	   var div_c_clas_one =  $("<div></div>").addClass("c-class").append("<strong>购物券</strong>");
			 	   var div_c_price_one = $("<div></div>").addClass("c-price").append("<strong>￥8</strong>");
			 	  var div_c_price_two = $("<div></div>").addClass("c-limit").append("只能LJD使用");
			        var div_c_time_one = $("<div></div>").addClass("c-time").append("<span>使用期限</span>").append("2015-12-21--2015-12-31");
			        var div_c_type_top_one = $("<div></div>").addClass("c-type-top");
			        var div_c_type_bottom_one = $("<div></div>").addClass("c-type-bottom");
			       
			         var span_number = $("<span class='txt'>35730144</span>").append(list[i].id);
			        div_c_type_one.append(div_c_clas_one).append(div_c_price_one).append(div_c_price_two)
			        .append(div_c_time_one).append(div_c_type_top_one).append(div_c_type_bottom_one);
			       
			          var div_range_item_one = $("<div></div>").addClass("range-item")
			          .append("<span class='label'>券&nbsp;编&nbsp;号：</span>")
			          .append(span_number);
			         
			         
			          var div_range_all_one = $("<div></div>").addClass("range-all");
			          var div_c_range_one = $("<div></div>").addClass("c-range");
			       
			          div_range_all_one.append(div_range_item_one);div_c_range_one.append(div_range_all_one);
			      
			            var  op_btns_one =  $("<div></div>").addClass("op-btns").append("	<a href='../home/home.jsp' class='btn'><span class='txt'>立即使用</span><b></b></a>");
			        
			            var c_msg = $("<div></div>").addClass("c-msg").append(div_c_range_one).append(op_btns_one);
			         
			                 var coupon_list = $("<div></div>").addClass("coupon-list").append(div_c_type_one).append(c_msg);
			                 var coupon_item = $("<div></div>").addClass("coupon-item coupon-item-d").append(coupon_list);
			               
			                  main.append(coupon_item);
			     }
			       }else
			    	   {
			    	  
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

					<div class="user-coupon">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">优惠券</strong> / <small>Coupon</small></div>
						</div>
						<hr/>

						<div class="am-tabs-d2 am-tabs  am-margin" data-am-tabs>

							<ul class="am-avg-sm-2 am-tabs-nav am-nav am-nav-tabs">
								<li class="am-active"><a href="#tab1">可用优惠券</a></li>
								<li><a href="#tab2" id="tab2">已用/过期优惠券</a></li>

							</ul>

							<div class="am-tabs-bd">
								<div class="am-tab-panel am-fade am-in am-active" id="tab1">
									<div class="coupon-items" id="coupon-items">
										
										
									</div>

								</div>
								<div class="am-tab-panel am-fade" id="tab2">
									<div class="coupon-items" id="coupon-items_pass">
									
									
									</div>
									
								</div>
							</div>

						</div>

					</div>

				</div>
				

	</body>
</html>