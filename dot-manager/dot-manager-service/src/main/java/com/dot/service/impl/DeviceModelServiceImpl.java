package com.dot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dot.mapper.TbDeviceModelMapper;
import com.dot.pojo.TbDeviceGroup;
import com.dot.pojo.TbDeviceModel;
import com.dot.pojo.TbDeviceModelExample;
import com.dot.service.DeviceModelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

@Service
public class DeviceModelServiceImpl implements DeviceModelService{
	
	@Autowired
 	private TbDeviceModelMapper itemMapper;	

	@Override
	public TaotaoResult createDeviceModel(TbDeviceModel deviceModel) {
		itemMapper.insert(deviceModel); 		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateDeviceModel(TbDeviceModel deviceModel) {
		// TODO Auto-generated method stub
		itemMapper.updateByPrimaryKey(deviceModel);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteDeviceModelById(String modelId) {
		itemMapper.deleteByPrimaryKey(modelId);
		return TaotaoResult.ok();
	}


	@Override
	public EUDataGridResult getDeviceModelList(Integer page, Integer rows) {
		
		TbDeviceModelExample ex = new TbDeviceModelExample();
		PageHelper.startPage(page, rows);
		List<TbDeviceModel> list = itemMapper.selectByExample(ex);
		// update the count of Model
/*  customized here
		TbDeviceModel dg= null;
		for(int i=0;i<list.size();i++)
		{
			dg = list.get(i);
			
		}
	*/	
		
		EUDataGridResult result = new EUDataGridResult();
		PageInfo<TbDeviceModel> pageInfo = new PageInfo<>(list);
		int total = (int) pageInfo.getTotal();
		result.setTotal( Integer.valueOf(total));
		result.setRows(list);			

		return result;
	}

	@Override
	public TaotaoResult getDeviceModelDetailById(String modelId) {
		// TODO Auto-generated method stub
		TaotaoResult result = new TaotaoResult();
		TbDeviceModel item =itemMapper.selectByPrimaryKey(modelId);
		
		result.setData(item);
		result.setStatus(TaotaoResult.SUCCESS);		
		return result;
	}



}
