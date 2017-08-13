package com.dot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dot.pojo.TbGatewayInfo;
import com.dot.service.GatewayInfoService;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

@Controller

public class GatewayInfoController {
	@Autowired
	private GatewayInfoService itemService;
		
	/**
	 * 网关信息录入添加 controller
	 * <p>Title: addGateway</p>
	 * <p>Description: </p>
	 * @param gwInfo
	 * @return
	 */
	@RequestMapping(value = "/gateway/add", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult addGateway(TbGatewayInfo gwInfo){
		
		itemService.createGatewayInfoItem(gwInfo);
		return TaotaoResult.ok();
	}
	
	@RequestMapping(value = "/gateway/delete", method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult deleteGateway(@RequestParam("ids[]")String[] ids){

		for (int i = 0; i < ids.length; i++) {
			itemService.deleteGatewayBySeriesNumber(ids[i]);		
		//	System.out.println(ids[i]);
		}
		return TaotaoResult.ok();
	}	
	
	/**
	 * 网关列表显示controller
	 * <p>Title: getGatewayList</p>
	 * <p>Description: </p>
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/gateway/list")
	@ResponseBody
	// page 和 rows 要和请求中一致
	public EUDataGridResult getGatewayList(@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="30") Integer rows){
		EUDataGridResult item = itemService.getGatewayList(page, rows);
		return item;
	}	
	
	

	
	@RequestMapping(value = "/rest/gateway/update",method = RequestMethod.POST)	
	@ResponseBody
	public TaotaoResult updateGatewayStaticInfo(TbGatewayInfo gwInfo){
		
		TaotaoResult result = itemService.updateStaticGatewayInfo(gwInfo);
		result.setStatus(200);
		return result;
		
	}

	
	
	
	@RequestMapping("/gateway/listid")
	@ResponseBody
	public TaotaoResult getGatewayListid( String esn){
		TaotaoResult result = itemService.getGatewayBySeriesNumber(esn);
		return result;
	}
	
	/*
	public TaotaoResult deleteGateway(@RequestBody String bodyString){

		//ids=2102113374P0B4000046%2C2232157374R0B4000088 怎么解析?
		String esnString = bodyString.substring(bodyString.indexOf('=')+1);

		String[] esn = esnString.split("%2C");

		for (int i = 0; i < esn.length; i++) {
			itemService.deleteGatewayBySeriesNumber(esn[i]);		
		}

		return TaotaoResult.ok();
	}
		*/
}
