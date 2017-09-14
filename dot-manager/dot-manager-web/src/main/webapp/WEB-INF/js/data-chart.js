var dataShow = dataShow || {};
Data = [] ;
currTime = 0; 

if(typeof valueType == "undefined"){
	valueType = {};
	valueType.light = 1;
	valueType.humidity = 2;
	valueType.accelerate = 3;
};


(function($, window, undefined){
	dataShow.$lData ; 
	dataShow.$hData ; 
	"use strict";
	$(document).ready(function()
	{
        var lightChart = echarts.init(document.getElementById('light'));
        var humidityChart = echarts.init(document.getElementById('humidity'));
        var accelerateChart = echarts.init(document.getElementById('accelerate'));
        
        // 指定图表的配置项和数据
        var commOption = {
            title: {
            	
                x: 'center'
            },
            tooltip: {},
            legend: {
                data:['value0'],
                x: 'left',
                show: false
             },
            xAxis: {
            	type:'category',
                data: []
            },
            yAxis: {
            	type : 'value',
            },
            dataZoom: [{
                type: 'inside',
                start: 50,
                end: 100
            }, {
                start: 50,
                end: 100,
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            }],        
            series: [{
                type: 'line',            	
                name: 'value0',
                smooth:true, 	
                data: []
            }]
        };       
        
        // 指定图表的配置项和数据
        var multiOption = {
            title: {
                x: 'center'
            },
            tooltip: {},
            legend: {
                data:['X','Y','Z'],
                x: 'right',
                show:true,

             },
            xAxis: {
            	type:'category',
                data: []
            },
            yAxis: {
            	type : 'value',
            },
            dataZoom: [{
                type: 'inside',
                start: 50,
                end: 100
            }, {
                start: 50,
                end: 100,
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            }],        
            series: [
             {
                type: 'line',            	
                name: 'X',
                smooth:true, 	
                data: []
            },
            {
                type: 'line',            	
                name: 'Y',
                smooth:true, 	
                data: []
            },
            {
                type: 'line',            	
                name: 'Z',
                smooth:true, 	
                data: []
            },           
            ]
        };               

        // 指定图表的配置项和数据
        var lightOption = {
            title: {
                text: 'light',
            },
            yAxis: {
            	name : 'lux',
            },
        };
        
      var humidityOption = {
                title: {
                    text: 'Humidity',
                },
                yAxis: {
                	name : '%',
                },
            }; 
      var accelerateOption = {
              title: {
                  text: 'Accelerate',
              },
              yAxis: {
              	name : 'g/s',
              },
          };       
      
      	lightChart.setOption(commOption);      
        lightChart.setOption(lightOption);
        humidityChart.setOption(commOption); 
        humidityChart.setOption(humidityOption);
        accelerateChart.setOption(multiOption);      
        accelerateChart.setOption(accelerateOption);        
        // tmp data request, time is stamp since 1970:0

 
        
        var dd= new Date("2017-09-13 23:30:00");
        var start = dd.getTime()/1000;
        var dd1 = new  Date("2017-09-13 23:35:00");
        var end = dd1.getTime()/1000;
    	var param = {mac:"12:D2:08:2D:07:98",
    				 type:'light',
    				 startTimestamp:start,
    				 endTimestamp:end};
       //  postData(lightChart,param,Data);
         
       


        
        
    	var param1 = {mac:"12:D2:08:2D:07:98",
				 type:'humidity',
				 startTimestamp:start,
				 endTimestamp:end};
    	
    	postData(humidityChart,param1);
    	

    	
    	
    	var param2 = {mac:"12:D2:08:2D:07:98",
				 type:'accelerate',
				 startTimestamp:start,
				 endTimestamp:end};
  //     postData(accelerateChart,param2);
        
    	currTime = end;       

		setInterval(function () {
	    	var param3 = {mac:"12:D2:08:2D:07:98",
					 type:'humidity',
					 startTimestamp:currTime,
					 endTimestamp:currTime +10};
	    	currTime =currTime +10;
//	    	console.log(param3);
   	
	    	postNewData(humidityChart,param3,true,1);	//		valueType.humidity
		}, 2500);
	});
	


})(jQuery, window);



function postData(obj,param,dataArray){

	$.post("/sensorData/getdata",param, function(data){
		if(data.status == 200){
			obj.setOption({
	 	        xAxis: {
	 	            data: data.data.categories
	 	        }
	       	 });
			//save data
			if(Data.length == 0)
			{
				Data.push({categories:data.data.categories});
			}			

			var seriesArray=new Array();
			var i = 0;
			for(i=0;i <data.data.valueNumber;i++ ){
					var valuename = eval('data.data.value'+i);
					var obj1 = { data: valuename};
					seriesArray.push(obj1);
			}
		
			
			
			obj.setOption({ series: seriesArray });	
			// save the data
			Data.push(seriesArray);

		}

	});	

}	;

function postNewData(obj,param,bc,type){
	//console.log(Data[0].categories);
	$.post("/sensorData/getdata",param, function(data){
		if(data.status == 200){
			//save data
			if(bc == true)
			{
				//console.log(data.data.categories.length);
				for (var i = 0 ;i <data.data.categories.length;i++){
			        Data[0].categories.shift();
			        Data[0].categories.push(data.data.categories[i]);				
				}
			}	
		//	console.log(Data[0].categories);			
			obj.setOption({
	 	        xAxis: {
	 	            data:  Data[0].categories,
	 	        }
	       	 });
			
			//console.log(type);
			var seriesArray=new Array();
			var i = 0;
			for(i=0;i <data.data.valueNumber;i++ ){
					var valuename = eval('data.data.value'+i);
					var obj1 = { data: valuename};
				//	console.log(obj1);
					for(var j = 0; j<obj1.data.length;j++){
						Data[type][i].data.shift();
						Data[type][i].data.push(obj1.data[j]);
					}
					seriesArray.push(Data[type][i]);
			}
			
			obj.setOption({ series: seriesArray });	
				

		}

	});	

}	;




	