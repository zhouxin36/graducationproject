<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="js/libs/jquery-1.11.1.min.js"></script>
<title>Test Upload File</title>
<script type="text/javascript">
         $(function(){
        	      $("#upload").click(function(){
        	    	 // console.log($('#form').serialize());
        	    	  //console.log();
        	    	//  $("#file").val($("#file2").val());
        	    	  var form = new FormData(document.getElementById("form"));  
        	    	  
        	    	  
        	    	  $.ajax({
        	    		  type :"post",
        	    		// 告诉jQuery不要去处理发送的数据
        	    		  processData : false, 
        	    		  // 告诉jQuery不要去设置Content-Type请求头
        	    		  contentType : false,
        	    		  url : "../../upload" ,
        	    		  data :form,
        	    		  success:function(msg){
        	    			  alert(msg.code);
        	    		  }
        	    	  });
        	    	  
        	    	   /* $.post("../../upload",$('#form').serialize(),function(msg){
        	    		     alert(msg.code);
        	    	   }); */
        	      }); 
         });
</script>
</head>
<body>                                 
<form id = "form" enctype="multipart/form-data">
<input type="file" id="file"  name="file"><br>
<!-- <img  src="../../upload/未命名文件 (1).png" /> -->
<input type="text" id="name" name="name" /><br>

</form>
<input type="file"  id="file2" name="file2"><br>
<button id="upload">tijiao</button>
</body>
</html>