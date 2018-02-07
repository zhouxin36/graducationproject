<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人资料</title>
</head>
				<script type="text/javascript">
				   $(function(){
	
					   information_init();
					
					   set_year();
					
					   //点击修改按钮修改个人信息
					   $("#save_change_information").click(function(){
						 
						  
						   
						  $("#user_sex_input").val($('#user_sex input:radio:checked').val());
						  var year = $("#select_year option:selected").text() + "-";
						  var month = $("#select_month option:selected").text() + "-";
						  var day = $("#select_day option:selected").text();
						  if(day == "")
							  day = 10;
						  $("#user_bitrhday").val(year+month+day);
						 var form = new FormData(document.getElementById("user_informtion"));  
					 $.ajax({
							 type :"post",
		        	    		// 告诉jQuery不要去处理发送的数据
		        	    		  processData : false, 
		        	    		  // 告诉jQuery不要去设置Content-Type请求头
		        	    		  contentType : false,
		        	    		  url : "../../User/updateUserById" ,
		        	    		  data :form,
		        	    		  success:function(msg){
		        	    			  if(msg.code==100){
		        	    				  information_init();
		        	    			  }else{

		        	    				  layer.msg("修改失败！", {
		        	    				  					icon : 5,
		        	    				  					time : 3000
		        	    				  				});
		        	    			  }
		        	    		  }
						 }); 
						 
						 /*  $.post("../../User/updateUserById",$("#user_informtion").serialize(),function(data,status){
							 
							  if(data.code==100){
								   information_init();
							   }
							   
						   });   */
						   
					   });
					
					   
					   function getMyDate(str){  
				            var oDate = new Date(str),  
				           
				            oYear = oDate.getFullYear(),  
				            oMonth = oDate.getMonth()+2,  
				            oDay = oDate.getDate(),  
				           
				            oTime = oYear +'-'+oMonth +'-'+ oDay ;//最后拼接时间  
				           
				            return oTime;  
				        };  
					   
					   
					   
					   function information_init(/*msg*/){
						   
						   $.post("../../User/getMyInformtion",function(message,status){
							    
							   
							   
							   if(message.code==100){  
								   
								   
								  
								   
								   var data = message.map.user ;

								   var da = getMyDate(data.birthday);
								
								   if(data.avatar!=""&&data.avatar!=null){
								   $("#touxiang").attr("src","../../uploads/"+data.avatar);
								  
								   }
								   else
									   $("#touxiang").attr("src","../images/getAvatar.do.jpg");
									   
								   
								   var arr = da.split('-');
								   for(var j = 0; j < arr.length ; j++){
									  
									   switch(j){
									   case 0: $("#select_year").val(arr[j]);    break;
									   case 1: $("#select_month").val(arr[j]); break;
									   case 2: $("#select_day").val(arr[j]);  break;
									   }
								   }
								   $("#user_name").html(data.name);
								   var memberLevel = "";
								   if(data.memberLevel==0)
									   memberLevel = "铜牌会员" ;
									   else if(data.memberLevel == 1)
										   memberLevel = "银牌会员" ;
									   else
										   memberLevel = "金牌会员" ;
								   $("#member_level").html(memberLevel);
							       $("#safety_level").html(data.accountSecurity+"分");
							       
							       $("#nick_name").val(data.nickname);
							       
							       $("#yue").html(data.accountBalance+"元");
							       $("#name").val(data.name);
							       if(data.sex==0){
							       $("#sex_female").attr("checked","checked");}
							       else if(data.sex==1){
							    	   $("#sex_male").attr("checked","checked");
							       }else{
							    	   $("#sex_secret").attr("checked","checked");
							       }
							       $("#user-phone").val(data.phone);
							       $("#user-email").val(data.email);   
							      
							   }
							   
							   
							   console.log(data);
						   });	
						   
						 
					   }
					   
					   function set_year(){
						   var selyear = $("#select_year");
				            var selmonth = $("#select_month");
				            var selday = $("#select_day");
				            var d = new Date();
				            var vMonth = d.getMonth() + 1;
				            var vDay = d.getDate();
				            var vYear = d.getYear(); //当前年  
				            vYear += (vYear < 2000) ? 1900 : 0;


				            for (var i = vYear - 68; i < vYear; i++) {
				                var option = $("<option>").val(i).text(i);
				                selyear.append(option);
				            };
				            for (var i = 1; i < 13; i++) {
				                var option = $("<option>").val(i).text(i);
				                selmonth.append(option);
				            };
				            selmonth.change(function () {
				                var p1 = $(this).children('option:selected').val(); //这就是selected的值 
				                var p2 = selyear.val(); //获取本页面其他标签的值 


				                var day = new Date(p2, p1, 0); //年 月
				                var daycount = day.getDate(); //获取天数


				                for (var i = 1; i <= daycount; i++) {
				                    var option = $("<option>").val(i).text(i);
				                    selday.append(option);
				                }
				            })
					   }
					

					  
					   
				   });
				
				</script>
<body>

            
		<div class="center">
			<div class="col-main">
				<div class="main-wrap">

					<div class="user-info">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">个人资料</strong> / <small>Personal&nbsp;information</small></div>
						</div>
						<hr/>

						<!--头像 -->
						<div class="user-infoPic">

							<div class="filePic">
								
								<img id="touxiang" width='160px' height='160px' class="am-circle am-img-thumbnail" src="../images/getAvatar.do.jpg" alt="../images/getAvatar.do.jpg" />
							</div>

							<p class="am-form-help">头像</p>

							<div class="info-m">
								<div><b>用户名：<i id="user_name">小叮当</i></b></div>
								<div class="u-level">
									<span class="rank r2">
							             <s class="vip1"></s><a class="classes"  id="member_level" href="#">铜牌会员</a>
						            </span>
								</div>
								<div class="u-safety">
									<a href="#" >
									 账户安全
									<span class="u-profile"><i  id="safety_level" class="bc_ee0000" style="width: 60px;" width="0">60分</i></span>
									</a>
								</div>
								<div>
									账户余额：<span class="u-profile"><i  id="yue" class="bc_ee0000" style="width: 60px;" width="0">60分</i></span>
								</div>
							</div>
						</div>

						<!--个人信息 -->
						<div class="info-main">
							<form id="user_informtion" class="am-form am-form-horizontal">
                                
                            <div class="am-form-group">
									<label for="user-name2" class="am-form-label">点击上传头像</label>
									<div class="am-form-content">
										<input type="file" id="tou" name="file" allowexts="gif,jpeg,jpg,png,bmp" accept="image/*">

									</div>
								</div> 
                                
                                
								<div class="am-form-group">
									<label for="user-name2" class="am-form-label">昵称</label>
									<div class="am-form-content">
										<input type="text" name="nickname" id="nick_name" placeholder="nickname">

									</div>
								</div>

								<div class="am-form-group">
									<label for="user-name" class="am-form-label">姓名</label>
									<div class="am-form-content">
										<input type="text"  id="name" name="name" placeholder="name">

									</div>
								</div>

								<div class="am-form-group">
									<label class="am-form-label">性别</label>
										<input type="hidden" id="user_sex_input" name="sex" />
									<div class="am-form-content sex" id="user_sex">
								
										<label class="am-radio-inline">
											<input type="radio" name="radio10" id="sex_male" value="1" data-am-ucheck> 男
										</label>
										<label class="am-radio-inline">
											<input type="radio" name="radio10" id="sex_female" value="0" data-am-ucheck> 女
										</label>
										<label class="am-radio-inline">
											<input type="radio" name="radio10"  id="sex_secret" value="-1" data-am-ucheck> 保密
										</label>
									</div>
								</div>

								<div class="am-form-group">
									<label for="user-birth" class="am-form-label">生日</label>
									<div class="am-form-content birth"  >
										<input type="hidden" id="user_bitrhday" name="birthday" />
										<div class="birth-select">
											<select data-am-selected id="select_year">
												<!-- <option value="a">2015</option>
												<option value="b">1987</option> -->
											</select>
											<em>年</em>
										</div>
										<div class="birth-select2">
											<select data-am-selected id="select_month">
												<!-- <option value="a">12</option>
												<option value="b">8</option> -->
											</select>
											<em>月</em></div>
										<div class="birth-select2">
											<select data-am-selected id="select_day">
												<!-- <option value="a">21</option>
												<option value="b">23</option>  -->
											</select>
											<em>日</em></div>
									</div>
							
								</div>
								<div class="am-form-group">
									<label for="user-phone" class="am-form-label">电话</label>
									<div class="am-form-content">
										<input id="user-phone" name="phone" placeholder="telephonenumber" type="tel">

									</div>
								</div>
								<div class="am-form-group">
									<label for="user-email" class="am-form-label">电子邮件</label>
									<div class="am-form-content">
										<input name="sademail" id="user-email" placeholder="Email" type="email">

									</div>
								</div>
								<div class="am-form-group address">
									<label for="user-address" class="am-form-label">收货地址</label>
									<div class="am-form-content address">
										<a href="address.html">
											<p class="new-mu_l2cw">
												<span class="province">湖北</span>省
												<span class="city">武汉</span>市
												<span class="dist">洪山</span>区
												<span class="street">雄楚大道666号(中南财经政法大学)</span>
												<span class="am-icon-angle-right"></span>
											</p>
										</a>

									</div>
								</div>
								<div class="am-form-group safety">
									<label for="user-safety" class="am-form-label">账号安全</label>
									<div class="am-form-content safety">
										<a href="safety.html">

											<span class="am-icon-angle-right"></span>

										</a>

									</div>
								</div>
								<div class="info-btn">
									
								</div>

							</form>
							<center><div class="am-btn am-btn-danger" id="save_change_information">保存修改</div>
						</center>
						</div>

					</div>

				</div>
				
		</div>
</body>
</html>