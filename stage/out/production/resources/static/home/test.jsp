<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="../../static/js/jquery-3.1.1.min.js"></script>
<script src="../js/echarts.min.js"></script>

</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
    
</body>
<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        
        
        
        
        // 指定图表的配置项和数据
       /* var option = {
            title: {
                text: 'ECharts 入门示例'
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋"]
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [5000, 20000, 360, 10000, 10]
            }]
        }; */

 var option = {
         tooltip: {
             show: true
         },
         legend: {
             data:['销量']
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
                 "name":"销量",
                 "type":"bar",
                 
             }
         ]
     };
  
 
 
tex();
 
        
        
       function tex(){
        	
        	$.ajax({
        		 type: "post",
        		 async : "false",
        		 url : "../../Product/SelectProductByName",
        		 data : "name=华硕" ,
        		 dataType : "json",
        		 success: function(data){
        			 var list = data.map.pageInfo.list;
                 	  
        	          	//初始化option.xAxis[0]中的data
        	          	
        	                option.xAxis[0].data=[];
        	               
        	                for(var i=0;i<list.length;i++){
        	                  option.xAxis[0].data.push(list[i].name);
        	                  
        	                }
        	                //初始化option.series[0]中的data
        	                option.series[0].data=[];
        	                for(var i=0;i<list.length;i++){
        	                  option.series[0].data.push(list[i].price);
        	                }
        	             // 使用刚指定的配置项和数据显示图表。
        	                myChart.setOption(option);
        	          	  
        		 }
        	});	 
         } 
        
        
    </script>
    
</html>