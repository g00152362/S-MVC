var dataShow = dataShow || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		
		dataShow.$chart =  $('#main');
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'Environment in Huawei park'
            },
            tooltip: {},
            legend: {
                data:['humidity']
            },
            xAxis: {
            	type:'category',
                data: []
            },
            yAxis: {
            	
            	type : 'value',

            },
            series: [{
                type: 'line',            	
                name: 'humidity',
                smooth:true, 	
                data: []
            }]
        };
        myChart.setOption(option);
        // todo tmp data request
		var param = {mac:"12:D2:08:2D:07:98",
					 type:'light',
					 dateFrom:'2017-09-10',
					 dateEnd:'2017-09-10',
					 timeFrom:'16:40:00',
					 timeEnd:'17:00:00'};
		$.post("/sensorData/getdata",param, function(data){
		if(data.status == 200){
	      	 myChart.setOption({
	 	        xAxis: {
	 	            data: data.data.categories
	 	        },
	 	        series: [{
	 	            name: 'humidity',

	 	            
	 	            data: data.data.value
	 	        	}]
	       	 });
			}
		});
       

   
        
	//	dataShow.$canvas =  $('#myChart');
			
/*
		var data = {
				labels : ["January","February","March","April","May","June","July"],
				datasets : [
					{
						fillColor : "rgba(220,220,220,0.5)",
						strokeColor : "rgba(220,220,220,1)",
						pointColor : "rgba(220,220,220,1)",
						pointStrokeColor : "#fff",
						data : [65,59,90,81,56,55,40]
					},
					{
						fillColor : "rgba(151,187,205,0.5)",
						strokeColor : "rgba(151,187,205,1)",
						pointColor : "rgba(151,187,205,1)",
						pointStrokeColor : "#fff",
						data : [28,48,40,19,96,27,100]
					}
				]
			};


		 var ctx = dataShow.$canvas.get(0).getContext("2d");
		  var myNewChart = new Chart(ctx).Line(data);
*/		 	

	});

})(jQuery, window);


	