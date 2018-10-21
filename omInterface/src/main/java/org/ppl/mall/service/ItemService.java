package org.ppl.mall.service;

import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.pojo.TbItemDesc;
import org.ppl.mall.util.MsgResult;

/**
 * 商品Service接口
 * @author PPL
 */
public interface ItemService {
	TbItem getItemById(long itemId);
	DataGridResult<TbItem> getItemByCatId(long catId, int pageNum, int pageSize);
	TbItemDesc getItemDescById(long itemId);
	DataGridResult<TbItem> getItemList(int pageNum, int pageSize);
	MsgResult addItem(TbItem item, String desc);
	MsgResult editItem(TbItem item, String desc);
	MsgResult deleteItem(long itemId);
	MsgResult unShelveItem(long itemId);
	MsgResult reShelfItem(long itemId);
}
