package com.dot.service;

import com.dot.pojo.TbGatewayInfo;
import com.dot.pojo.TbGatewayInfoStat;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

public interface GatewayInfoService {
	TaotaoResult createGatewayInfoItem(TbGatewayInfo item);
	EUDataGridResult getGatewayList(Integer page,Integer rows) ;
	/**
	 * only update the filed from jms message, include
	 *     software_version,hardware_version,reboot time, reboot type
	 * <p>Title: updateRunGatewayInfo</p>
	 * <p>Description: </p>
	 * @param record
	 * @return
	 */
	TaotaoResult updateRunGatewayInfo(TbGatewayInfo record);
	
	/**
	 * only update the filed from input form, include
	 *     position,device_name, type 
	 * <p>Title: updateStaticGatewayInfo</p>
	 * <p>Description: </p>
	 * @param record
	 * @return
	 */	
	
	TaotaoResult updateStaticGatewayInfo(TbGatewayInfo record);
	
	TaotaoResult deleteGatewayBySeriesNumber(String esn);
	
	TbGatewayInfo getGatewayBySeriesNumber(String esn);
	
	TbGatewayInfoStat getGatewayStatusStat();
	
	TbGatewayInfoStat getGatewayStatusStatByGroupName(String groupName);
	
}
