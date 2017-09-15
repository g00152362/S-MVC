var dataShow = dataShow || {};
Data = [] ;
currTime = 0; 

if(typeof valueType == "undefined"){
	valueType = {};
	valueType.light = 1;
	valueType.humidity = 2;
	valueType.accelerate = 3;
	valueType.gyroscope = 4;	
	
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
        var gyroscopeChart = echarts.init(document.getElementById('gyroscope'));        
        
        
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
      
      var gyroscopeOption = {
              title: {
                  text: 'Gyroscope',
              },
              yAxis: {
              	name : 'dps',
              },
          };          
      
      	lightChart.setOption(commOption);      
        lightChart.setOption(lightOption);
        humidityChart.setOption(commOption); 
        humidityChart.setOption(humidityOption);
        accelerateChart.setOption(multiOption);      
        accelerateChart.setOption(accelerateOption);     
        gyroscopeChart.setOption(multiOption);      
        gyroscopeChart.setOption(gyroscopeOption);         
        // tmp data request, time is stamp since 1970:0

 
        
        var dd= new Date("2017-09-15 22:05:00");
        var start = dd.getTime()/1000;
        var dd1 = new  Date("2017-09-15 22:07:00");
        var end = dd1.getTime()/1000;
    	var param = {mac:"12:D2:08:2D:07:98",
    				 type:'light',
    				 startTimestamp:start,
    				 endTimestamp:0
    				 };
         postData(lightChart,param,Data,valueType.light);
         
         param.type = 'humidity';
      	 postData(humidityChart,param,valueType.humidity);
    	
      	 param.type = 'accelerate';
         postData(accelerateChart,param,valueType.accelerate);
        
         //gyroscope
     	 param.type = 'gyroscope';
         postData(gyroscopeChart,param,valueType.gyroscope);         
         
    	currTime = end;       

		setInterval(function () {
			var param12 = {mac:"12:D2:08:2D:07:98",
					 type:'light',
					 startTimestamp:currTime,
					 endTimestamp:0};
	    	currTime =currTime +10;


	    	postNewData(lightChart,param12,true,valueType.light);	

	    	param12.type = 'humidity';
	    	postNewData(humidityChart,param12,false,valueType.humidity);	//		valueType.humidity
	    	
	    	param12.type = 'accelerate';
	    	postNewData(accelerateChart,param12,false,valueType.accelerate);
	    	
	    	param12.type = 'gyroscope';
	    	postNewData(gyroscopeChart,param12,false,valueType.gyroscope);	    	
	    	
		}, 2500);
	});
	


})(jQuery, window);



function postData(obj,param,dataArray,type){

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
					//if(Data[0].categories.length >15)
					{
						 Data[0].categories.shift();
					}
			       
			        Data[0].categories.push(data.data.categories[i]);				
				}
			}	
		//	console.log(Data[0].categories);			
			obj.setOption({
	 	        xAxis: {
	 	            data:  Data[0].categories,
	 	        }
	       	 });
			
		//	console.log(Data[type]);
			//console.log(type);
			var seriesArray=new Array();
			var i = 0;
			for(i=0;i <data.data.valueNumber;i++ ){
					var valuename = eval('data.data.value'+i);
					var obj1 = { data: valuename};
				//	console.log(obj1);
					for(var j = 0; j<obj1.data.length;j++){
						//if(Data[type][i].data.length > 15)
						{
							Data[type][i].data.shift();
						}
						Data[type][i].data.push(obj1.data[j]);
					}
					seriesArray.push(Data[type][i]);
			}
		//	console.log(Data[type]);
			obj.setOption({ series: seriesArray });	
				

		}

	});	

}	;




	