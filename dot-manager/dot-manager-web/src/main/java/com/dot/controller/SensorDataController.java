package com.dot.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dot.pojo.TbSensorData;
import com.dot.pojo.reqDataCondition;
import com.dot.service.SensorCatService;
import com.dot.service.SensorDataService;

import dot.com.common.result.TaotaoResult;
import net.sf.json.JSONObject;


@Controller
public class SensorDataController {
	@Autowired
	private SensorDataService dataService;	
	
	@Autowired
	private SensorCatService catService;
	
	@RequestMapping(value = "/sensorData/getdata", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult getSensorData(reqDataCondition dataCond){
		
		//get type_id from type_name
		long typeId = catService.getSensorCatIdByName(dataCond.getType());
		
		// get result according condition
		List<TbSensorData> result = dataService.getSensorDataListByCond(dataCond, typeId);
		
		// get the time and value
		TbSensorData sd;
		ArrayList<String>  time = new ArrayList<String> ();
		ArrayList<Float>  value = new ArrayList<Float> ();		

		for(int i=0; i<result.size();i++){
			sd = result.get(i);
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
		
			time.add(sdf.format(sd.getTimestamp()));
			value.add(sd.getValue());
		}
		JSONObject resp = new JSONObject();
		resp.put("categories", time);
		resp.put("value", value);
		
		TaotaoResult r = new TaotaoResult();
		r.setStatus(TaotaoResult.SUCCESS);
		r.setData(resp);
		
		//how to response??
		//System.out.println(dataCond);
		//itemService.createDeviceGroup(dgInfo);
		return r;
	}	

}
