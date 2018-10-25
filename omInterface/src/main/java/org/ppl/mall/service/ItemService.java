package org.ppl.mall.service;

import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.pojo.TbItemDesc;
import org.ppl.mall.util.WebResult;

/**
 * 商品Service接口
 * @author PPL
 */
public interface ItemService {
	TbItem getItemById(long itemId);
	DataGridResult<TbItem> getItemByCatId(long catId, int pageNum, int pageSize);
	TbItemDesc getItemDescById(long itemId);
	DataGridResult<TbItem> getItemList(int pageNum, int pageSize);
	WebResult addItem(TbItem item, String desc);
	WebResult editItem(TbItem item, String desc);
	WebResult deleteItem(long itemId);
	WebResult unShelveItem(long itemId);
	WebResult reShelfItem(long itemId);
}
