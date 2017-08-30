package com.dot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dot.service.DeviceModelService;

import dot.com.common.pojo.EUDataGridResult;

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

}
