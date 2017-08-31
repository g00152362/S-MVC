package com.dot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dot.pojo.TbDeviceModel;
import com.dot.service.DeviceModelService;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

@Controller
public class DeviceModelController {
	
	@Autowired
	private DeviceModelService itemService;
	
	
	@RequestMapping("/deviceModel/list")
	@ResponseBody
	// page 和 rows 要和请求中一致
	public EUDataGridResult getDeviceGroupList(@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="30") Integer rows){
		EUDataGridResult item = itemService.getDeviceModelList(page, rows);

		return item;
	}
	
	@RequestMapping(value = "/deviceModel/add", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult addDeviceModel(TbDeviceModel dmInfo){
		
		itemService.createDeviceModel(dmInfo);
		return TaotaoResult.ok();
	}	

}
