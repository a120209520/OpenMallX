package org.ppl.mall.service;

import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	DataGridResult<TbItem> getItemList(int pageNum, int pageSize);
}
