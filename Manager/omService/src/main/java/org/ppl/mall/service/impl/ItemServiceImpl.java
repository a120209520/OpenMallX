package org.ppl.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.ppl.mall.mapper.TbItemDescMapper;
import org.ppl.mall.mapper.TbItemMapper;
import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.pojo.TbItemDesc;
import org.ppl.mall.pojo.TbItemExample;
import org.ppl.mall.service.ItemService;
import org.ppl.mall.util.IDUtils;
import org.ppl.mall.util.MsgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 商品管理Service
 * @author Smith
 *
 */

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	//通过id查询单个商品
	@Override
	public TbItem getItemById(long itemId) {
		return itemMapper.selectByPrimaryKey(itemId);
	}
	
	@Override
	public TbItemDesc getItemDescById(long itemId) {
		return itemDescMapper.selectByPrimaryKey(itemId);
	}
	
	//获取商品列表
	@Override
	public DataGridResult<TbItem> getItemList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<TbItem> list = itemMapper.selectByExample(new TbItemExample());
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		DataGridResult<TbItem> dgResult = new DataGridResult<>();
		dgResult.setTotal(pageInfo.getTotal());
		dgResult.setRows(pageInfo.getList());
		return dgResult;
	}

	//添加商品
	@Override
	public MsgResult addItem(TbItem item, String desc) {
		Date curTime = new Date();
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus(TbItem.STATUS_NORMAL);
		item.setCreated(curTime);
		item.setUpdated(curTime);
		itemMapper.insert(item);
		
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(curTime);
		itemDesc.setUpdated(curTime);
		itemDescMapper.insert(itemDesc);
		return MsgResult.ok();
	}
}
