package org.ppl.mall.service.search.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.ppl.mall.mapper.TbItemMapper;
import org.ppl.mall.model.SearchItem;
import org.ppl.mall.service.search.SearchItemService;
import org.ppl.mall.util.MsgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public MsgResult importAllItems() {
		try {
			List<SearchItem> list = itemMapper.getSearchItemList();
			SolrInputDocument document = new SolrInputDocument();
			for(SearchItem item:list) {
				document.clear();
				document.addField("id", item.getId());
				document.addField("item_title", item.getTitle());
				document.addField("item_sell_point", item.getSellPoint());
				document.addField("item_price", item.getPrice());
				document.addField("item_image", item.getImage());
				document.addField("item_category_name", item.getCatName());
				solrServer.add(document);
			}
			solrServer.commit();
			return MsgResult.ok();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return MsgResult.build(500, "import failed!");
	}
}
