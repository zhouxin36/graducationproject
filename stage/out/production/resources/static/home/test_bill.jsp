<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人账单</title>

	</head>
 <script type="text/javascript">

   $(function(/*msg*/){
	   $("#main_ul").empty();
	   var all = 0;
	   $.post("../../Order/selectPaiedForder",function(data,status){
		   
		  
		   
		   
		   $.each(data.map.list,function(index,items){
				  var li = $("<li></li>").addClass("ng-scope  delete-false");
			       var div = $("<div></div>").addClass("  ng-scope");
			       var a = $("<a></a>").addClass("text fn-left ").attr("title","sssss");
			      /*  var refund = $("<a href='javascript:(0);'></a>").append("退款");
			       refund.click(function(){
			    	   $("#refund_forderId").val(item.forderId);
			    	   $("#jsp").load("test_refund.jsp",function(data,status){
							
						});
			       }); */
			       var span1 = $("<span></span>").addClass("emoji-span ng-binding").append(items.id+"")/* .append(refund) */;
		         
		           var span2 = $("<span></span>").addClass("amount fn-right ng-binding").append(items.total);
		          
		           all += items.total;
		           a.append(span1).append(span2); div.append(a); li.append(div);
		           $("#main_ul").append(li);
			  
		   });
		   $("#all_pay_money").html(all);
	   });
	   
	   
	   });
   
  
 </script>
	<body>
			
         
		

		<div class="center">
			<div class="col-main">
				<div class="main-wrap">

					<div class="user-bill">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">账单</strong> / <small>Electronic&nbsp;bill</small></div>
						</div>
						<hr/>

						<div class="ebill-section">
							<div class="ebill-title-section">
								<h2 class="trade-title section-title">
                                                                                                                                     交易
                            <span class="desc">（金额单位：元）</span>
                        </h2>

								<div class=" ng-scope">
									<div class="trade-circle-select  slidedown-">
										<a href="javascript:void(0);" class="current-circle ng-binding"></a>

									</div>
									<span class="title-tag"><i class="num ng-binding">17</i>年</span>
								</div>
							</div>

							<div class="module-income ng-scope">
								<div class="income-slider ">
									<div class="block-income block  fn-left">
										<h3 class="income-title block-title">
                                                                                                          支出
                                      <span class="num ng-binding" id="all_pay_money">
                                         
                                       </span>
                                             </h3>

										<div ng-class="shoppingChart" class="catatory-details  fn-hide shopping">
											<div class="catatory-chart fn-left fn-hide">
												<div class="title">类型</div>
												<ul>


												</ul>
											</div>
											<div class="catatory-detail fn-left">
												<div class="title ng-binding">
													订单id
												</div>
												<ul id="main_ul">
												
													<li class="ng-scope  delete-false">

														<div class="  ng-scope">
															<a href="#" class="text fn-left " title="呢子大衣">
																<span class="emoji-span ng-binding">呢子大衣</span>
																<span class="amount fn-right ng-binding">349.00</span>
															</a>
														</div>
													</li>
												</ul>
											</div>
										</div>
									</div>
									<div class="block-expense block  fn-left">
										<div class="slide-button right"></div>
									</div>
									<div class="clear"></div>
								</div>
                              <div id="main" style="width: 700px;height:400px;"></div>

								<script type="text/javascript">
								  var myChart = echarts.init(document.getElementById('main'));
								  var option = {
									         tooltip: {
									             show: true
									         },
									         legend: {
									             data:['最近消费']
									         },
									         xAxis : [
									             {
									                 type : 'category',
									                 
									             }
									         ],
									         yAxis : [
									             {
									                 type : 'value'
									             }
									         ],
									         series : [
									             {
									                 "name":"最近消费",
									                 "type":"bar",
									                 
									             }
									         ]
									     };
									  
									 

								 
								   make_table();
								       function make_table(){
								    	    $.ajax({
								    	    	type : "post" ,
								    	    	dataType : "json" ,
								    	    	async : "false" ,
								    	    	url : "../../Order/selectPaiedForder" ,
								    	    	success : function(msg){
								    	    		
								    	    		
								    	    		
								    	    		var list = msg.map.list;
								    	    		console.log(msg);
								    	    		//初始化option.xAxis[0]中的data
								    	    		//option.xAxis[0] = data ;
						        	                option.xAxis[0].data=[];
								    	    		for(var i = 0 ; i< list.length; i++){
								    	    			  option.xAxis[0].data.push(list[i].id);
								    	    		}
								    	    		//初始化option.series[0]中的data
						        	                option.series[0].data=[];
						        	                for(var i=0;i<list.length;i++){
						        	                	
						        	                  option.series[0].data.push(list[i].total);
						        	                }
						        	                myChart.setOption(option);
								    	    	}
								    	    });
								       }
								  
								</script>

							</div>

						</div>

					</div>
				</div>
				

			</div>

			
		</div>

	</body>
</html>