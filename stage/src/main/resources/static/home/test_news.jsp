<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息</title>
<script type="text/javascript">
$(document).ready(function(){
	build_shop_action();
	
	//建立
	function build_shop_action(){
		
		$.ajax({
			   type : "post",
			   async : "false",
			   url : "../../MallActivity/selectMallActivity",
			   dataType : "json",
			   success:function(msg){
				   if(msg.code==200)
					   return ;
				   var list = msg.map.list;
				   $.each(list,function(index,item){
					   //主要的修改代码
					   var div_one = $("<div></div>").addClass("s-msg-item s-msg-temp i-msg-downup");
				       
					   var span_one = $("<span></span>").addClass("s-name");
					   var h6 = $("<h6></h6>").append(span_one).append("每日新鲜事");
					   
					   div_one.append(h6);
					
					   var div_two = $("<div></div>").addClass("s-msg-content i-msg-downup-wrap");
					   var div_three = $("<div></div>").addClass("i-msg-downup-con");
				       var a_i_markRead = $("<a></a>").addClass("i-markRead").attr("href","http://www.baidu.com").attr("target","_blank");
			           var img_one = $("<img></img>").attr("src","../images/TB102.jpg");
				       var p_s_main_content = $("<p></p>").addClass("s-main-content").append(item.remark);
				       var p_two =   $("<p></p>").addClass("s-row s-main-content")
				       var i_am_icon_angle_right = $("<i></i>").addClass("am-icon-angle-right");
				       var a_two = $("<a></a>").attr("href","../home/search.jsp?productname=华硕").append("了解详情").append(i_am_icon_angle_right);
				       p_two.append(a_two);
				       a_i_markRead.append(img_one).append(p_s_main_content).append(p_two);
				       div_three.append(a_i_markRead);
				       div_two.append(div_three);
				       div_one.append(div_two);
				       
				       var final_i = $("<i></i>").addClass("am-icon-close am-icon-fw");
				       var final_a = $("<a></a>").addClass("i-btn-forkout").attr("href","http://www.baidu.com").append(final_i);
				       div_one.append(final_a);
				       $("#huodong").append(div_one);
				   });
				  
				   
				   
			   },
			   error:function(){
				   
			   }
		});
		
		  
	}
	

	
	
	
	
	
	//修改成今天的日期
	 $("#today").html(getNowFormatDate());

	//得到当前日期
	  function getNowFormatDate() {
        var date = new Date();
		var seperator1 = "-";
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		var currentdate = month +  seperator1 +  strDate ;
		return currentdate;
}
	
	
	
	
	
	
});
</script>
</head>

	<body>
		
		<div class="center">
			<div class="col-main">
				<div class="main-wrap">

					<div class="user-news">

						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">我的消息</strong> / <small>News</small></div>
						</div>
						<hr/>

						<div class="am-tabs am-tabs-d2" data-am-tabs>
							<ul class="am-avg-sm-3 am-tabs-nav am-nav am-nav-tabs">
								<li class="am-active"><a href="#tab1" id="shop_action">商城活动</a></li>
								

							</ul>

							<div class="am-tabs-bd">
								<div class="am-tab-panel am-fade am-in am-active" id="tab1">

									<div class="news-day">
										<div class="goods-date" data-date="2015-12-21">
											<span><i class="month-lite" id="today">12.21</i>	<i class="date-desc">今天</i></span>
										</div>

                                      <div id="huodong"></div>
							<!-- 			
										<div class="s-msg-item s-msg-temp i-msg-downup">
											<h6 class="s-msg-bar"><span class="s-name">每日新鲜事</span></h6>
											<div class="s-msg-content i-msg-downup-wrap">
												<div class="i-msg-downup-con">
													<a class="i-markRead" target="_blank" href="blog.html">
														<img src="../images/TB102.jpg">
														<p class="s-main-content">
															最特色的湖北年货都在这儿 ~快来囤年货啦！
														</p>
														<p class="s-row s-main-content">
															<a href="blog.html">
															阅读全文 <i class="am-icon-angle-right"></i>
															</a>
														</p>
													</a>
												</div>
											</div>
											<a class="i-btn-forkout" href="#"><i class="am-icon-close am-icon-fw"></i></a>
										</div> -->
									</div>
								</div>

								<div class="am-tab-panel am-fade" id="tab2">
									<!--消息 -->
										<div class="s-msg-item s-msg-temp i-msg-downup">
											<h6 class="s-msg-bar"><span class="s-name">订单已签收</span></h6>
											<div class="s-msg-content i-msg-downup-wrap">
												<div class="i-msg-downup-con">
													<a class="i-markRead"  href="#">
													<div class="m-item">	
														<div class="item-pic">															
																	<img src="../images/kouhong.jpg_80x80.jpg" class="itempic J_ItemImg">
														</div>
														<div class="item-info">
															您购买的美康粉黛醉美唇膏已签收，
															快递单号:373269427868
														</div>
																											
                                                    </div>	

													<p class="s-row s-main-content">
															<a id="check_exp">查看详情</a> <i class="am-icon-angle-right"></i>
													</p>
													</a>
												</div>
											</div>
											<a class="i-btn-forkout" href="#"><i class="am-icon-close am-icon-fw"></i></a>
										</div>
								</div>

								<div class="am-tab-panel am-fade" id="tab3">
									<!--消息 -->
										<div class="s-msg-item s-msg-temp i-msg-downup">
											<h6 class="s-msg-bar"><span class="s-name">卖家已退款&nbsp;¥12.90元</span></h6>
											<div class="s-msg-content i-msg-downup-wrap">
												<div class="i-msg-downup-con">
													<a class="i-markRead" target="_blank" href="record.html">
													<div class="m-item">	
														<div class="item-pic">															
																	<img src="../images/kouhong.jpg_80x80.jpg" class="itempic J_ItemImg">
														</div>
														<div class="item-info">
															<p class="item-comment">您购买的美康粉黛醉美唇膏卖家已退款</p>
															<p class="item-time">2015-12-21&nbsp;17:38:29</p>
														</div>
																											
                                                    </div>	

													<p class="s-row s-main-content">
															<a href="record.html">钱款去向</a> <i class="am-icon-angle-right"></i>
													</p>
													</a>
												</div>
											</div>
											<a class="i-btn-forkout" href="#"><i class="am-icon-close am-icon-fw"></i></a>
										</div>
								</div>
							</div>
						</div>
					</div>

				</div>
	

	</body>
</html>