package org.ppl.mall.service.search;

import org.ppl.mall.model.SearchResult;
import org.ppl.mall.util.MsgResult;

/**
 * 商品搜索Service接口
 * @author PPL
 */
public interface SearchItemService {
	MsgResult importAllItems();
    MsgResult importItem(long id);
    SearchResult searchItems(String key, int page, int rows);
}
