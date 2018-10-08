package org.ppl.mall.service.search;

import org.ppl.mall.model.SearchResult;
import org.ppl.mall.util.MsgResult;

public interface SearchItemService {
	MsgResult importAllItems();
    MsgResult importItem(long id);
    SearchResult searchItems(String key, int page, int rows);
}
