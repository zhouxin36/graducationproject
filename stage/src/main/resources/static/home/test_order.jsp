<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单管理</title>
        <link rel="stylesheet" href="bootstrap-star/css/font-awesome.min.css">
    <link rel="stylesheet" href="bootstrap-star/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
<script src="bootstrap-star/js/star-rating.js" type="text/javascript"></script>
<script type="text/javascript">
function getMyDate(str) {
	var oDate = new Date(str), oYear = oDate.getFullYear(), oMonth = oDate
			.getMonth() + 1, oDay = oDate.getDate(), oTime = oYear + '-' + getzf(oMonth) + '-'
			+ getzf(oDay);//最后拼接时间  
	return oTime;
};
//补0操作  
function getzf(num) {
	if (parseInt(num) < 10) {
		num = '0' + num;
	}
	return num;
}
//确认收货
function Confirmreceipt(id,ele){
	
	
	   /* if( confirm("确认收货吗?")){
		   $.ajax({
			   type : "post" ,
			   data : "id="+id ,
			   dataType : "json" ,
			   url : "../../Order/Confirmreceipt" ,
			   async : false,
			   success : function(msg){
				   if(msg.code==200)
					   layer.msg("非法操作！", {
							icon : 5,
							time : 3000
						});
				   else{
					   
					   build_order(-1);
				   }
			   }
		   });
	   } */
	   
	 layer.msg('确认收货吗?', {
		  time: 0 //不自动关闭
		  ,btn: ['确定', '取消']
		  ,yes: function(index){
			  $.ajax({
				   type : "post" ,
				   data : "id="+id ,
				   dataType : "json" ,
				   url : "../../Order/Confirmreceipt" ,
				   async : false,
				   success : function(msg){
					   if(msg.code==200)
						   layer.msg("非法操作！", {
								icon : 5,
								time : 3000
							});
					   else{
						   ele.remove();
						  // build_order(-1);
					   }
				   }
			   });
			  layer.close(index);
		  }
		});
 }
 
 function comment(v,id){
	  $.ajax({
			url : "../../Forder/request_forder",
			data : "id=" + v,
			type : "post",
			success : function(msg) {
				console.log(msg);
				if (msg.code == 100) {
					var form = $("#form_forder1");
					form.empty();
					
					/* <input type="radio" value="1" name="anonymous">匿名
					<input type="radio" value="0" name="anonymous">不匿名 */
					var radio1 = $("<input/>");
					radio1.attr("type","radio");
					radio1.val(1);
					radio1.attr("name","anonymous");
					radio1.attr("checked",true);
					
					var radio2 = $("<input/>");
					radio2.attr("type","radio");
					radio2.val(0);
					radio2.attr("name","anonymous");
					
					var hide = $("<input/>")
					hide.attr("type","hidden");
					hide.val(id);
					hide.attr("name","id");
					form.append(radio1).append("匿名").append(radio2).append("不匿名").append(hide);
					
					var div10 = $("<div></div>");
					div10.addClass("form-group");
					var ta = $("<table></table>");
					ta.addClass("layui-table table table-hover");
					var tr1 = $("<tr></tr>");
					var th1 = $("<th></th>");
					th1.append("商品号");
					var th5 = $("<th></th>");
					th5.append("商品图像");
					var th2 = $("<th></th>");
					th2.append("商品名");
					var th3 = $("<th></th>");
					th3.append("商品评价");
					tr1.append(th1).append(th5).append(th2).append(th3);
					ta.append(tr1);
					var tbody = $("<tbody></tbody>");
					for(var i = 0;msg.map.listSorder[i] != null; i++ ){
						var tr2 = $("<tr></tr>");
						var td1 = $("<td></td>");
						td1.attr("style","width:50px;")
						td1.append(msg.map.listSorder[i].id);
						var td2 = $("<td></td>");
						var td5 = $("<td></td>");
						td5.attr("style","width:90px;height:90px;")
						var img = $("<img/>");
						img.attr("style","width:80px;height:80px;")
						$.ajax({
							url:"../../Pic/selectPicByProductType",
							data:"id="+msg.map.listSorder[i].productId+"&type="+1,
							type:"post",
							async:false,
							success:function(ms){
								if(ms.code == 100)
									img.attr("src", ms.map.path+ms.map.pic_list[0].path);
								else 
									img.attr("src", ms.map.path+"1.jpg");
							},
							error:function(msg){
								img.attr("src", ms.map.path+"1.jpg");
							}
						});
						td5.append(img);
						
						td2.append(msg.map.listSorder[i].name);
						var td3 = $("<td></td>");
						td2.attr("style","width:200px;")
						var inp = $("<input/>");
						inp.addClass("rb-rating");
						inp.attr("name","comment"+i);
						var inp2 = $("<input/>");
						inp2.attr("type","text");
						inp2.attr("name","content"+i);
						
						var inp3 = $("<input/>");
						inp3.attr("type","hidden");
						inp3.attr("name","product"+i);
						inp3.val(msg.map.listSorder[i].productId);
						td3.append(inp).append(inp2).append(inp3);
						//td3.append(form);
						inp.rating({'showCaption':true, 'stars':'3', 'min':'0', 'max':'3', 'step':'1', 'size':'xs', 'starCaptions': {0:'未评价', 1:'差评', 2:'中评', 3:'好评'}});
						inp.on("rating.change",function (){
							//alert($(this).val());
						});
						tr2.append(td1).append(td5).append(td2).append(td3);
						tbody.append(tr2);
					}
					ta.append(tbody);
					div10.append(ta);
					form.append(div10);
					
					
					
					
					
				}
			},
			error: function (){
				
			}
			}); 
 }

//所有订单
function build_order(number/*msg*/){
		$("#tab"+number+"_tbody").empty();
	     $.post("../../Order/getOrderById","number="+number,function(data,status){
	  if(data.code==100){
	    		  var list  = data.map.list;
	    		  var oper ;
	    		  switch(number){
	    		  case -1: oper = "所有订单";break;
	    		  case 0:oper = "待付款";break;
	    		  case 1:oper = "待发货";break;
	    		  case 2:oper = "待收货";break;
	    		  case 3:oper = "待评价";break;
	    		  case 4:oper = "已完成";break;
	    		  }
	    	  for(var i = 0 ; i < list.length ; i++){
	    		  var tr = $("<tr></tr>");
	    		  var td1 = $("<td></td>");
	    		  td1.append(list[i].id);
	    		  var td2 = $("<td></td>");
	    		  td2.append(list[i].total);
	    		  var td3 = $("<td></td>");
	    		  td3.append(oper);
	    		  tr.append(td1).append(td2).append(td3);
	    		  var td4 = $("<td></td>");
	    		  var btn_look = $("<button></button>");
  				btn_look.addClass("btn btn-success look");
  				btn_look.attr("type", "button");
  				btn_look.attr("val", list[i].id);
  				btn_look.append("查看详细订单");
  				
  				 var btn_refund = $("<button></button>");
  				btn_refund.addClass("btn btn-danger refund");
  				btn_refund.attr("type", "button");
  				btn_refund.attr("val", list[i].id);
  				btn_refund.append("退货");
  				
  				 var btn_comment = $("<button></button>");
  				btn_comment.addClass("btn btn-warning comment");
  				btn_comment.attr("type", "button");
  				btn_comment.attr("val", list[i].id);
  				btn_comment.append("评价");
  				
  				btn_comment.click(function (){
  					comment($(this).attr("val"),$(this).attr("val"));
  					$("#myModal1").modal("show");
  				});
  				
  				btn_refund.click(function(){
		    	   //alert($(this).attr("val"));
		    	    $("#jsp").load("test_refund.jsp","id=" + $(this).attr("val"),function(data,status){
					}); 
		    	  	
  				});
		    	   td4.append(btn_refund);
	             var forderId = list[i].id;
	             if(forderId != 0){
	            	 $.ajax({
	            		 type: "POST",
	            	     url : "../../Order/selectForderStatusById",
	            	     data : "id=" + forderId ,
	            	     dataType : "Json" ,
	            	     async: false,
	            	     success:function(datas,status){
	            	    	if(datas.code==100){
	            	    		if(datas.map.Forder.status!=0)
	            	    			td4.append(btn_look);
	            	    		if(datas.map.Forder.status == 3)
	            	    			td4.append(btn_comment);
		            			 if(datas.map.Forder.status==0){
		            				 var a = $("<a></a>");
		            				 a.attr("href","../home/pay.jsp?forderId="+forderId+"");
		            				 a.addClass("btn btn-success");
		            				 a.append("点我付款");
		            				 td4.append(a);
		            				//all += "<a href='../home/pay.jsp?forderId="+forderId+"' class='btn btn-success'>点我付款</a>" ;
		            	
		            			 }else if(datas.map.Forder.status==2){
		            				 var a = $("<a></a>");
		            				 a.addClass("btn btn-success");
		            				 a.append("确认收货");
		            				 a.click(function (){
		            					 Confirmreceipt(forderId,$(this).parent().parent());
		            				 });
		            				 td4.append(a);
		            				//all +="<a onclick='Confirmreceipt("+forderId+")'class='btn btn-success' >确认收货</a>" ;
		            			 }
		            			 tr.append(td4);
		            			 $("#tab"+number+"_tbody").append(tr);
		            			 
		            			 
		            			 
		            			 
		            			 
		            			 
		            			 $(".look").each(function() {
		            					var v = $(this).attr("val");
		            					$(this).click(function() {
		            						$.ajax({
		            							url : "../../Forder/request_forder",
		            							data : "id=" + v,
		            							type : "post",
		            							success : function(msg) {
		            								console.log(msg);
		            								if (msg.code == 100) {
		            									var form = $("#form_forder");
		            									form.empty();
		            									
		            									var div1 = $("<div></div>");
		            									div1.addClass("form-group");
		            									var la1 = $("<label></label>");
		            									la1.addClass("col-sm-4 control-label");
		            									la1.attr("style","padding-top: 5px; text-align: right; font-size: 18px; font-family: 楷体;");
		            									la1.append("订单号:");
		            									var div1_1 = $("<div></div>");
		            									div1_1.addClass("col-sm-6");
		            									var input1 = $("<input/>");
		            									input1.attr("type","text");
		            									input1.addClass("form-control");
		            									input1.attr("placeholder","订单号");
		            									input1.attr("readonly",true);
		            									input1.val(msg.map.forder.id);
		            									div1_1.append(input1);
		            									div1.append(la1).append(div1_1);
		            									form.append(div1);
		            									
		            									var div2 = $("<div></div>");
		            									div2.addClass("form-group");
		            									var la2 = $("<label></label>");
		            									la2.addClass("col-sm-4 control-label");
		            									la2.attr("style","padding-top: 5px; text-align: right; font-size: 18px; font-family: 楷体;");
		            									la2.append("用户名:");
		            									var div2_1 = $("<div></div>");
		            									div2_1.addClass("col-sm-6");
		            									var input2 = $("<input/>");
		            									input2.attr("type","text");
		            									input2.addClass("form-control");
		            									input2.attr("placeholder","用户名");
		            									input2.attr("readonly",true);
		            									input2.val(msg.map.user.name);
		            									div2_1.append(input2);
		            									div2.append(la2).append(div2_1);
		            									form.append(div2);
		            									
		            									
		            									var div3 = $("<div></div>");
		            									div3.addClass("form-group");
		            									var la3 = $("<label></label>");
		            									la3.addClass("col-sm-4 control-label");
		            									la3.attr("style","padding-top: 5px; text-align: right; font-size: 18px; font-family: 楷体;");
		            									la3.append("收货人:");
		            									var div3_1 = $("<div></div>");
		            									div3_1.addClass("col-sm-6");
		            									var input3 = $("<input/>");
		            									input3.attr("type","text");
		            									input3.addClass("form-control");
		            									input3.attr("placeholder","收货人");
		            									input3.attr("readonly",true);
		            									input3.val(msg.map.userAddress.name);
		            									div3_1.append(input3);
		            									div3.append(la3).append(div3_1);
		            									form.append(div3);
		            									
		            									
		            									var div4 = $("<div></div>");
		            									div4.addClass("form-group");
		            									var la4 = $("<label></label>");
		            									la4.addClass("col-sm-4 control-label");
		            									la4.attr("style","padding-top: 5px; text-align: right; font-size: 18px; font-family: 楷体;");
		            									la4.append("收货电话:");
		            									var div4_1 = $("<div></div>");
		            									div4_1.addClass("col-sm-6");
		            									var input4 = $("<input/>");
		            									input4.attr("type","text");
		            									input4.addClass("form-control");
		            									input4.attr("placeholder","收货电话");
		            									input4.attr("readonly",true);
		            									input4.val(msg.map.userAddress.phone);
		            									div4_1.append(input4);
		            									div4.append(la4).append(div4_1);
		            									form.append(div4);
		            									
		            									
		            									var div5 = $("<div></div>");
		            									div5.addClass("form-group");
		            									var la5 = $("<label></label>");
		            									la5.addClass("col-sm-4 control-label");
		            									la5.attr("style","padding-top: 5px; text-align: right; font-size: 18px; font-family: 楷体;");
		            									la5.append("收货地址:");
		            									var div5_1 = $("<div></div>");
		            									div5_1.addClass("col-sm-6");
		            									var input5 = $("<textarea></textarea>");
		            									input5.addClass("form-control");
		            									input5.attr("placeholder","收货地址");
		            									input5.attr("readonly",true);
		            									input5.val(msg.map.userAddress.address);
		            									div5_1.append(input5);
		            									div5.append(la5).append(div5_1);
		            									form.append(div5);
		            									
		            									
		            								/* 	var div6 = $("<div></div>");
		            									div6.addClass("form-group");
		            									var la6 = $("<label></label>");
		            									la6.addClass("col-sm-4 control-label");
		            									la6.attr("style","padding-top: 5px; text-align: right; font-size: 18px; font-family: 楷体;");
		            									la6.append("邮政编码:");
		            									var div6_1 = $("<div></div>");
		            									div6_1.addClass("col-sm-6");
		            									var input6 = $("<input/>");
		            									input6.attr("type","text");
		            									input6.addClass("form-control");
		            									input6.attr("placeholder","邮政编码");
		            									input6.attr("readonly",true);
		            									input6.val(msg.map.userAddress.post);
		            									div6_1.append(input6);
		            									div6.append(la6).append(div6_1);
		            									form.append(div6); */
		            									
		            									var div7 = $("<div></div>");
		            									div7.addClass("form-group");
		            									var la7 = $("<label></label>");
		            									la7.addClass("col-sm-4 control-label");
		            									la7.attr("style","padding-top: 5px; text-align: right; font-size: 18px; font-family: 楷体;");
		            									la7.append("订单状态:");
		            									var div7_1 = $("<div></div>");
		            									div7_1.addClass("col-sm-6");
		            									var input7 = $("<input/>");
		            									input7.attr("type","text");
		            									input7.addClass("form-control");
		            									input7.attr("placeholder","订单状态");
		            									input7.attr("readonly",true);
		            									if(msg.map.forder.status == 0)
		            										input7.val("未支付");
		            									else if(msg.map.forder.status == 1)
		            										input7.val("待发货");
		            									else if(msg.map.forder.status == 2)
		            										input7.val("待收货");
		            									else if(msg.map.forder.status == 3)
		            										input7.val("待评价");
		            									else if(msg.map.forder.status == 4)
	            											input7.val("已完成");
		            									else
		            										input7.val("未知状态");
		            									div7_1.append(input7);
		            									div7.append(la7).append(div7_1);
		            									form.append(div7);
		            									
		            									
		            									
		            									/* var div8 = $("<div></div>");
		            									div8.addClass("form-group");
		            									var la8 = $("<label></label>");
		            									la8.addClass("col-sm-4 control-label");
		            									la8.attr("style","padding-top: 5px; text-align: right; font-size: 18px; font-family: 楷体;");
		            									la8.append("支付方式:");
		            									var div8_1 = $("<div></div>");
		            									div8_1.addClass("col-sm-6");
		            									var input8 = $("<input/>");
		            									input8.attr("type","text");
		            									input8.addClass("form-control");
		            									input8.attr("placeholder","支付方式");
		            									input8.attr("readonly",true);
		            									if(msg.map.forder.payment == 0)
		            										input8.val("银联");
		            									else if(msg.map.forder.payment == 1)
		            										input8.val("微信支付");
		            									else if(msg.map.forder.payment == 2)
		            										input8.val("支付宝支付");
		            									else
		            										input8.val("未知状态");
		            									div8_1.append(input8);
		            									div8.append(la8).append(div8_1);
		            									form.append(div8); */
		            									
		            									
		            									
		            									var div9 = $("<div></div>");
		            									div9.addClass("form-group");
		            									var la9 = $("<label></label>");
		            									la9.addClass("col-sm-4 control-label");
		            									la9.attr("style","padding-top: 5px; text-align: right; font-size: 18px; font-family: 楷体;");
		            									la9.append("物流方式:");
		            									var div9_1 = $("<div></div>");
		            									div9_1.addClass("col-sm-6");
		            									var input9 = $("<input/>");
		            									input9.attr("type","text");
		            									input9.addClass("form-control");
		            									input9.attr("placeholder","物流方式");
		            									input9.attr("readonly",true);
		            									if(msg.map.forder.logistics == 0)
		            										input9.val("圆通");
		            									else if(msg.map.forder.logistics == 1)
		            										input9.val("申通");
		            									else if(msg.map.forder.logistics == 2)
		            										input9.val("韵达");
		            									else if(msg.map.forder.logistics == 3)
		            										input9.val("中通");
		            									else if(msg.map.forder.logistics == 4)
		            										input9.val("申通");
		            									else
		            										input9.val("其他物流");
		            									div9_1.append(input9);
		            									div9.append(la9).append(div9_1);
		            									form.append(div9);
		            									
		            									var div10 = $("<div></div>");
		            									div10.addClass("form-group");
		            									var ta = $("<table></table>");
		            									ta.addClass("layui-table table table-hover");
		            									var tr1 = $("<tr></tr>");
		            									var th1 = $("<th></th>");
		            									th1.append("商品号");
		            									var th5 = $("<th></th>");
		            									th5.append("商品图像");
		            									var th2 = $("<th></th>");
		            									th2.append("商品名");
		            									var th3 = $("<th></th>");
		            									th3.append("商品价格");
		            									var th4 = $("<th></th>");
		            									th4.append("商品数量");
		            									tr1.append(th1).append(th5).append(th2).append(th3).append(th4);
		            									ta.append(tr1);
		            									var tbody = $("<tbody></tbody>");
		            									for(var i = 0;msg.map.listSorder[i] != null; i++ ){
		            										var tr2 = $("<tr></tr>");
		            										var td1 = $("<td></td>");
		            										td1.append(msg.map.listSorder[i].id);
		            										var td2 = $("<td></td>");
		            										
		            										var td5 = $("<td></td>");
		            										var img = $("<img/>");
		            										img.attr("style","width:80px;height:80px;")
		            										$.ajax({
		            											url:"../../Pic/selectPicByProductType",
		            											data:"id="+msg.map.listSorder[i].productId+"&type="+1,
		            											type:"post",
		            											async:false,
		            											success:function(ms){
		            												if(ms.code == 100)
		            													img.attr("src", ms.map.path+ms.map.pic_list[0].path);
		            												else 
		            													img.attr("src", ms.map.path+"1.jpg");
		            											},
		            											error:function(msg){
		            												img.attr("src", ms.map.path+"1.jpg");
		            											}
		            										});
		            										td5.append(img);
		            										
		            										td2.append(msg.map.listSorder[i].name);
		            										var td3 = $("<td></td>");
		            										td3.append(msg.map.listSorder[i].price);
		            										var td4 = $("<td></td>");
		            										td4.append(msg.map.listSorder[i].number);
		            										tr2.append(td1).append(td5).append(td2).append(td3).append(td4);
		            										tbody.append(tr2);
		            									}
		            									ta.append(tbody);
		            									div10.append(ta);
		            									form.append(div10);
		            									
		            									
		            									var div12 = $("<div></div>");
		            									div12.addClass("form-group");
		            									var la12 = $("<label></label>");
		            									la12.addClass("col-sm-4 control-label");
		            									la12.attr("style","padding-top: 5px; text-align: right; font-size: 18px; font-family: 楷体;");
		            									la12.append("订单生成时间:");
		            									var div12_1 = $("<div></div>");
		            									div12_1.addClass("col-sm-6");
		            									var input12 = $("<input/>");
		            									input12.attr("type","text");
		            									input12.addClass("form-control");
		            									input12.attr("placeholder","订单生成时间");
		            									input12.attr("readonly",true);
		            									input12.val(getMyDate(msg.map.forder.addDate));
		            									div12_1.append(input12);
		            									div12.append(la12).append(div12_1);
		            									form.append(div12);
		            									
		            									if(msg.map.forder.successTime !=null){
		            										var div13 = $("<div></div>");
			            									div13.addClass("form-group");
			            									var la13 = $("<label></label>");
			            									la13.addClass("col-sm-4 control-label");
			            									la13.attr("style","padding-top: 5px; text-align: right; font-size: 18px; font-family: 楷体;");
			            									la13.append("订单完成时间:");
			            									var div13_1 = $("<div></div>");
			            									div13_1.addClass("col-sm-6");
			            									var input13 = $("<input/>");
			            									input13.attr("type","text");
			            									input13.addClass("form-control");
			            									input13.attr("placeholder","订单完成时间");
			            									input13.attr("readonly",true);
			            									input13.val(getMyDate(msg.map.forder.successTime));
			            									div13_1.append(input13);
			            									div13.append(la13).append(div13_1);
			            									form.append(div13);
		            									}
		            								
		            									
		            									
		            									
		            									
		            									
		            									var div11 = $("<div></div>");
		            									div11.addClass("form-group");
		            									var la11 = $("<label></label>");
		            									la11.addClass("col-sm-4 control-label");
		            									la11.attr("style","padding-top: 5px; text-align: right; font-size: 18px; font-family: 楷体;");
		            									la11.append("总金额:");
		            									var div11_1 = $("<div></div>");
		            									div11_1.addClass("col-sm-6");
		            									var input11 = $("<input/>");
		            									input11.attr("type","text");
		            									input11.addClass("form-control");
		            									input11.attr("placeholder","总金额");
		            									input11.attr("readonly",true);
		            									input11.val(msg.map.forder.total+"元");
		            									div11_1.append(input11);
		            									div11.append(la11).append(div11_1);
		            									form.append(div11);
		            								} else {
		            									layer.msg('请求失败!', {
		            										icon : 2,
		            										time : 2000
		            									});
		            								}
		            							},
		            							error : function(msg) {
		            								layer.msg('请求失败!', {
		            									icon : 2,
		            									time : 2000
		            								});
		            							}
		            						});
		            						$("#myModal").modal("show");
		            					});
		            				});
		            		     
		            			 
		            			 
		            			 
		            			 
		            			 
		            			 
		            			 
		            			 
		            			 
		            		 }
	            	     }
	            	 });
	             }else{
	                    	 all += "</td><td>" +
 		                    "<a href='../home/shopcart.jsp'>结算</a>" + "</td></tr>" ;
	             }
	       }   
	    	  }   
	     }); 
	     
	     
	     
	     
	     
	     
	     
}




      $(function(){
    	  
    	  //页面加载时就显示所有订单
    	   build_order(-1);
    	  
    	  
    	  
    	
    	
    	 
    	  $("#tab1_button").click(function(){
    		  build_order(-1);
    	  });
    	  
    	  $("#tab2_button").click(function(){
    		  build_order(0);
    	  });
    	  $("#tab3_button").click(function(){
    		  build_order(1);
    	  });
    	  $("#tab4_button").click(function(){
    		  build_order(2);
    	  });
    	  $("#tab5_button").click(function(){
    		  build_order(3);
    	  });
    	  $("#tab6_button").click(function(){
    		  build_order(4);
    	  });
    	
    	  $("#save1").click(function (){
    		  var a = 0;
    		  $(".rb-rating").each(function (){
    			  if(0 == $(this).val()){
    				  a = 1;
    				 
    			  }
    		  });
    		  if(1 == a){
    			  layer.msg("请评价完!!", {
						icon : 0,
						time : 2000
					});
    			  return;
    		  }
    			 
    		  var jsonstr = $("#form_forder1").serialize();
        	 // var formData = new FormData($("#form_forder1")[0]);
        	  console.log(jsonstr);
    		    $.ajax({
    			 url:"../../Comment/comment",
    			 type:"post",
    			 data:jsonstr,
    			 success:function(){
    				 $("#myModal1").modal("hide");
    				 layer.msg("评价成功!!", {
  						icon : 6,
  						time : 2000
  					});
    			 },
    			 error:function(){
    				 layer.msg("评价失败!!", {
 						icon : 5,
 						time : 2000
 					});
    			 }
    		  });  
    	  });
    	  
    	
      });

      
     
 	  
</script>
</head>
	<body>
		
			<b class="line"></b>
		<div class="center">
			<div class="col-main">
				<div class="main-wrap">

					<div class="user-order">

						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">订单管理</strong> / <small>Order</small></div>
						</div>
						<hr/>

						<div class="am-tabs am-tabs-d2 am-margin" data-am-tabs>

							<ul class="am-avg-sm-5 am-tabs-nav am-nav am-nav-tabs">
								<li class="am-active"><a href="#tab1" id="tab1_button">所有订单</a></li>
								<li><a href="#tab2" id="tab2_button">待付款</a></li>
								<li><a href="#tab3" id="tab3_button">待发货</a></li>
								<li><a href="#tab4" id="tab4_button">待收货</a></li>
								<li><a href="#tab5" id="tab5_button">待评价</a></li>
								<li><a href="#tab6" id="tab6_button">已完成</a></li>
							</ul>

							<div class="am-tabs-bd">
								<div class="am-tab-panel am-fade am-in am-active" id="tab1">
									 <table class="table table-striped">
									  <tr>
									   <th>订单id</th>
									  <th>合计</th>
									   <th>订单归属</th>
									   <th>交易操作</th>
									  </tr>
									  <tbody id="tab-1_tbody"></tbody>
									 </table>
									
									

								</div>
								<div class="am-tab-panel am-fade" id="tab2">
								    <table class="table table-striped">
									  <tr>
									     <th>订单id</th>
									  <th>合计</th>
									   <th>订单归属</th>
									   <th>交易操作</th>
									  </tr>
									  <tbody id="tab0_tbody"></tbody>
									 </table>
									
								</div>
								<div class="am-tab-panel am-fade" id="tab3">
								<table class="table table-striped">
									  <tr>
									     <th>订单id</th>
									  <th>合计</th>
									   <th>订单归属</th>
									   <th>交易操作</th>
									  </tr>
									  <tbody id="tab1_tbody"></tbody>
									 </table>
									
								</div> 
								<div class="am-tab-panel am-fade" id="tab4">
								<table class="table table-striped">
									  <tr>
									     <th>订单id</th>
									  <th>合计</th>
									   <th>订单归属</th>
									   <th>交易操作</th>
									  </tr>
									  <tbody id="tab2_tbody"></tbody>
									 </table>
									
								</div> 

								<div class="am-tab-panel am-fade" id="tab5">
								<table class="table table-striped">
									  <tr>
									    <th>订单id</th>
									  <th>合计</th>
									   <th>订单归属</th>
									   <th>交易操作</th>
									  </tr>
									  <tbody id="tab3_tbody"></tbody>
									 </table>
									

								</div>
								
								<div class="am-tab-panel am-fade" id="tab6">
								<table class="table table-striped">
									  <tr>
									    <th>订单id</th>
									  <th>合计</th>
									   <th>订单归属</th>
									   <th>交易操作</th>
									  </tr>
									  <tbody id="tab4_tbody"></tbody>
									 </table>
									

								</div>
							</div>

						</div>
					</div>
				</div>
				
<!-- 查看模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">查看详细订单</h4>
				</div>
				<div class="modal-body">
					<form id="form_forder" class="form-horizontal">
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 评价模态框 -->
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">评价</h4>
				</div>
				<div class="modal-body">
					<form id="form_forder1" class="form-horizontal">
					
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="save1">Save
						changes</button>
				</div>
			</div>
			
		</div>
	</div>
	</body>
</html>