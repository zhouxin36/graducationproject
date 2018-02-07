<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>地址管理</title>

</head>
                     <script type="text/javascript">
  $(function(){
      
      //用户新建地址
      $("#save_address").click(function(){
    	 
    	  $.post("../../Address/selectAddressById",function(data,status){
    		  if(data.code==100){
    			  var list = data.map.Address;
    			   //如果用户的地址超过3个，则不允许再添加
    			  if(list.length>=3){
    				  layer.msg("每个用户的地址上限为3个，请删除后再添加！", {
    						icon : 2,
    						time : 2000
    					});
    			  }else{
    				
    				 var province = $("#province  option:selected").text()+"-";
    			     var city = $("#city2  option:selected").text()+"-";
    				 var street = $("#street  option:selected").text()+"-";
    				 
    				 
    					if(province == "请选择-")
    				  		province = "";
    				  	if(city == "请选择-")
    				  		city = "";
    				  	if(street == "请选择-")
    				  		street = "";
    				 
    			     var name = $("#user-name").val();
    				 var phone = $("#user-phone").val();
    				 var detailed_address = $("#detailed_address").val();
    				 $("#user_address").val(province+city+street+detailed_address);
    				
    				  $.post("../../Address/insertAddress",$('#address_form').serialize(),function(data,status){
                                layer.msg(status, {
                					icon : 1,
                					time : 2000
                				});
                                if(data.code==100){
                                	build_address();
                                }
     				 }); 
    				  
    				  
    			  }
    		  }	else{
    				 var province = $("#province  option:selected").text()+"-";
    			     var city = $("#city2  option:selected").text()+"-";
    				 var street = $("#street  option:selected").text()+"-";
    				 
    					if(province == "请选择-")
    				  		province = "";
    				  	if(city == "请选择-")
    				  		city = "";
    				  	if(street == "请选择-")
    				  		street = "";
    				 
    			     var name = $("#user-name").val();
    				 var phone = $("#user-phone").val();
    				 var detailed_address = $("#detailed_address").val();
    				 $("#user_address").val(province+city+street+detailed_address);
    			
    				  $.post("../../Address/insertAddress",$('#address_form').serialize(),function(data,status){
                               
                                if(data.code==100){
                                	build_address();
                                }
     				 }); 
    		  }
    		  
    	  });
    	  
      });
	  
	  
	  
	  
	  
	  build_address();
	  
	  

		$("#city").citySelect({
			nodata:"none",
			required:false
		}); 
		$("#edit_city").citySelect({
			nodata:"none",
			required:false
		});
		
	  
	  
	  function build_address(/*msg*/){
		  
		  $.post("../../Address/selectAddressById",function(data,status){
	         
			  if(data.code==100){
				  $("#address_ul").empty();
			  console.log(data);
	              var list = data.map.Address;
	             $.each(list, function(index, addres) {
			 
			        var ul = $("#address_ul");
			        //defaultAddr
			         var li  =  $("<li></li>");
			        if(addres.selected == 1){
			        	  li.addClass("user-addresslist defaultAddr");
			        }else{
			        	   li.addClass("user-addresslist");
			        }
			        li.attr("address_id",addres.id);
		 	
		         var i_one = $("<i></i>").addClass("am-icon-check-circle");
		        var out_span = $("<span></span>").addClass("new-option-r").
		        append(i_one).append("默认地址");
		        
		        li.append(out_span);
		       
		        var in_span_one = $("<span></span>").addClass("new-txt")
		        .append(addres.name);
		        var in_span_two = $("<span></span>").addClass("new-txt-rd2")
		        .append(addres.phone);
		        var out_p = $("<p></p>").addClass("new-tit new-p-re").
		         append(in_span_one).append(in_span_two);
		        
		        li.append(out_p);
		        
		        var in_span_three = $("<span></span>").addClass("title").append("地址:");
		        
		        var str = addres.address;
		        var reg= /-/g;
		              if(str!=null)
		        str = str.replace(reg," ");
		        
		        var in_span_four = $("<span></span>").addClass("province").
		        append(str);
		        var in_p_one =  $("<p></p>").addClass("new-mu_l2cw")
		        .append(in_span_three).append(in_span_four);
		        var out_div_one = $("<div></div>").addClass("new-mu_l2a new-p-re").append(in_p_one);
		        
		        li.append(out_div_one);
		        
		        var  in_i_three = $("<i></i>").addClass("am-icon-edit");
		       
		         var a_one = $("<a></a>").attr("href","#").append(in_i_three).append("编辑");
		        a_one.click(function(/*msg*/){
		        	//每次打开模态框都清空数据
					reset_form($("#myModal form"));
					
					//通过JS调用模态框
					$("#myModal").modal({
						backdrop:"static"
					});
					//初始化模态框的数据
					 build_motai(addres);
					
					
		        });
		        var in_span_five =  $("<span></span>").addClass("new-addr-bar").append("|");
		        
		        var  in_i_four = $("<i></i>").addClass("am-icon-trash");
		        var a_two = $("<a></a>").attr("href","#").append(in_i_four).append("删除");
		        a_two.click(function(/*msg*/){
		        	$.post("../../Address/deleteAddressById","id="+addres.id,function(data,status){
		        		build_address();
		        	});
		        });
		        var out_div_two = $("<div></div>").addClass("new-addr-btn").
		        append(a_one).append(in_span_five).append(a_two);
		        li.append(out_div_two);
		        ul.append(li);});
			  }else{
				  $("#address_ul").empty();
			  }
			  $(".user-addresslist").click(function (){
				  var v = $(this);
				  $.ajax({
					  url:"../../Address/setDefaultAddress",
					  data:"id="+v.attr("address_id"),
					  type:"post",
					  async:false,
					  success:function(msg){
						 /*  if(msg.code == 200)
							  layer.msg("修改失败！！！", {
		          					icon : 2,
		          					time : 2000
		          				}); */
					  },
					  error:function(){
						  layer.msg("修改失败！！", {
          					icon : 2,
          					time : 2000
          				});
					  }
				  });
					  $("#address_ul").find("li").each(function (){
						  $(this).attr("class","user-addresslist");
					  });
					  v.addClass("defaultAddr");
				  });
		  });	
		 
	   }         
	 
             
	  //初始化模态框
	  	function build_motai(address){
		  $("#edit_user-name").val(address.name);
		  $("#edit_user-phone").val(address.phone);
		  $("#edit_address_id").val(address.id);
	  }
	  
	  //点击模态框里面的修改按钮
	  $("#address_edit_btn").click(function(){
		  
		  var province = $("#edit_province option:selected").text()+"-";
		     var city = $("#edit_city2  option:selected").text()+"-";
			 var street = $("#edit_street  option:selected").text()+"-";
		  	if(province == "请选择-")
		  		province = "";
		  	if(city == "请选择-")
		  		city = "";
		  	if(street == "请选择-")
		  		street = "";
		  
			 var detailed_address = $("#edit_detailed_address").val();
			 $("#edit_user_address").val(province+city+street+detailed_address);
		  
		  
		  $.post("../../Address/updateAddressById",$('#myModal form').serialize(),function(data,status){
			  if(data.code==100){
				  layer.msg("修改成功", {
						icon : 1,
						time : 2000
					});
				  build_address();
			  }else
				  {
				  layer.msg("修改失败！", {
						icon : 2,
						time : 2000
					});
				  }
		  });
		 
		  $("#myModal ").modal("hide");
	  });
	  
	  //重置模态框
	   function reset_form(ele){
			//清除input输入内容
			
			$(ele)[0].reset();
			
			//清除父容器DIV样式
			$(ele).find("*").removeClass("has-success has-error");
			//清除span内容
			$(ele).find(".help-block").text("");
		}
	  
	  
     });
  
  
          </script>
                     



          
<body>
		

		<div class="center">
			<div class="col-main">
				<div class="main-wrap">

					<div class="user-address">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">地址管理</strong> / <small>Address&nbsp;list</small></div>
						</div>
						<hr/>
						<ul id="address_ul" class="am-avg-sm-1 am-avg-md-3 am-thumbnails">

							
						</ul>
						<div class="clear"></div>
						<a class="new-abtn-type" data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0}">添加新地址</a>
						<!--例子-->
						<div class="am-modal am-modal-no-btn" id="doc-modal-1">

							<div class="add-dress">

								<!--标题 -->
								<div class="am-cf am-padding">
									<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">新增地址</strong> / <small>Add&nbsp;address</small></div>
								</div>
								<hr/>

								<div class="am-u-md-12 am-u-lg-8" style="margin-top: 20px;">
									<form id="address_form" class="am-form am-form-horizontal">
                                    <input type="hidden" id="user_address" name="address" />
										<div class="am-form-group">
											<label for="user-name" class="am-form-label">收货人</label>
											<div class="am-form-content">
												<input type="text" id="user-name" name="name" placeholder="收货人">
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-phone"  class="am-form-label">手机号码</label>
											<div class="am-form-content">
												<input id="user-phone" name='phone' placeholder="手机号必填" type="email">
											</div>
										</div>
										<div class="am-form-group">
											<label for="user-address" class="am-form-label">所在地</label>
											<div id="city" class="am-form-content address">
											
											<select id="province" class="prov" ></select>  
	<select id="city2" class="city" disabled="disabled" ></select> 
	<select id="street" class="dist" disabled="disabled" ></select> 	
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-intro" class="am-form-label">详细地址</label>
											<div class="am-form-content">
												<textarea  id="detailed_address" class="" rows="3" id="user-intro" placeholder="输入详细地址"></textarea>
												<small>100字以内写出你的详细地址...</small>
											</div>
										</div>

										<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
											
												<!-- <a class="am-btn am-btn-danger"></a>
												<a href="javascript: void(0)" class="am-close am-btn am-btn-danger" data-am-modal-close>取消</a> -->
											</div>
										</div>
									</form>
									<button  id="save_address" class="am-btn am-btn-danger" >保存</button>
								</div>

							</div>

						</div>

					</div>

					<script type="text/javascript">
						$(document).ready(function() {							
							
							var $ww = $(window).width();
							if($ww>640) {
								$("#doc-modal-1").removeClass("am-modal am-modal-no-btn")
							}
							
						})
					</script>

					<div class="clear"></div>

				</div>
				
				
					<!-- 模态框 -->
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">修改地址</h4>
      </div>
      <div class="modal-body">
       <!-- 添加一个表单 -->
     <form id="edit_address_form" class="am-form am-form-horizontal">
                                    <input type="hidden" id="edit_user_address" name="address" />
										<div class="am-form-group">
											<label for="user-name" class="am-form-label">收货人</label>
											<div class="am-form-content">
												<input type="text" id="edit_user-name" name="name" placeholder="收货人">
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-phone"  class="am-form-label">手机号码</label>
											<div class="am-form-content">
												<input id="edit_user-phone" name='phone' placeholder="手机号必填" type="email">
											</div>
										</div>
										<div class="am-form-group">
											<label for="user-address" class="am-form-label">所在地</label>
											<div id="edit_city" class="am-form-content address">
											
												<select id="edit_province" class="prov" ></select>  
												<select id="edit_city2" class="city" disabled="disabled" ></select> 
												<select id="edit_street" class="dist" disabled="disabled" ></select> 	
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-intro" class="am-form-label">详细地址</label>
											<div class="am-form-content">
												<textarea  id="edit_detailed_address" class="" rows="3"  placeholder="输入详细地址"></textarea>
												<small>100字以内写出你的详细地址...</small>
											</div>
										</div>

										<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
											
												<!-- <a class="am-btn am-btn-danger"></a>
												<a href="javascript: void(0)" class="am-close am-btn am-btn-danger" data-am-modal-close>取消</a> -->
											</div>
										</div>
										<input type="hidden" id="edit_address_id" name="id" />
									</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="address_edit_btn">修改</button>
      </div>
    </div>
  </div>
</div>
				
	</body>
	
</html>