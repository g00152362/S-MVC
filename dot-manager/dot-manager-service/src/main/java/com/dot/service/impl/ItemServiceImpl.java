package com.dot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dot.mapper.TbItemDescMapper;
import com.dot.mapper.TbItemMapper;
import com.dot.pojo.TbItem;
import com.dot.pojo.TbItemDesc;
import com.dot.pojo.TbItemExample;
import com.dot.pojo.TbItemExample.Criteria;
import com.dot.service.ItemService;
import com.dot.utils.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import dot.com.common.pojo.EUDataGridResult;
import dot.com.common.result.TaotaoResult;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper ItemMapper;
	@Autowired
	private TbItemDescMapper ItemDescMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		
		TbItemExample ex = new TbItemExample();
		Criteria cr = ex.createCriteria();
		// TODO Auto-generated method stub
		//可以用主键ID来查询，通用方法用条件查询
	//	TbItem item = ItemMapper.selectByPrimaryKey(itemId);
		
		cr.andIdEqualTo(itemId);
		
		List<TbItem> list = ItemMapper.selectByExample(ex);
		if(list != null && list.size()>0){
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}
	@Override
	public EUDataGridResult getItemList(Integer page,Integer rows){
		
		TbItemExample ex = new TbItemExample();
		PageHelper.startPage(page, rows);
		
		List<TbItem> list = ItemMapper.selectByExample(ex);

		
		EUDataGridResult result = new EUDataGridResult();

		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		int total = (int) pageInfo.getTotal();
		result.setTotal( Integer.valueOf(total));
		result.setRows(list);		
		return result;
		
	}

	@Override
	public TaotaoResult createItem(TbItem item, String desc) throws Exception{
		Long itemid = IDUtils.genItemId();
		item.setId(itemid);
		item.setStatus((byte) 1);
		item.setImage("aaa");
		item.setCreated(new Date());
		item.setUpdated(new Date());
		
		ItemMapper.insert(item);
		//事务必须在一个函数完成，如果插入失败，抛出异常，SPRINGMVC 会自动进行回滚
		
		TaotaoResult result = insertItemDesc(itemid,desc);
		if(result.getStatus() != 200){
			throw new Exception();
		}
		
		return TaotaoResult.ok();
		
	}
	
	public TaotaoResult insertItemDesc(long itemId,String description){
		TbItemDesc item = new TbItemDesc();
		item.setItemId(itemId);
		item.setItemDesc(description);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		
		ItemDescMapper.insert(item);
		
		return TaotaoResult.ok();
	}
}
