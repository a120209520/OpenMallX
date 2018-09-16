package org.ppl.mall.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.ppl.mall.model.SearchItem;
import org.ppl.mall.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 商品搜索dao
 * @author Smith
 *
 */
@Repository
public class SearchDao {
	
	@Autowired
	private SolrServer solrServer;

	//搜索指定条件商品
	public SearchResult search(SolrQuery query) {
		SearchResult result = null;
		try {
			QueryResponse response = solrServer.query(query);
			SolrDocumentList list = response.getResults();
			long numFound = list.getNumFound();
			result = new SearchResult();
			result.setTotalCount(numFound);

			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<SearchItem> resList = new ArrayList<SearchItem>();
			for (SolrDocument doc : list) {
				SearchItem item = new SearchItem();
				item.setId((String) doc.get("id"));
				item.setCatName((String) doc.get("item_category_name"));
				item.setImage((String) doc.get("item_image"));
				item.setPrice((long) doc.get("item_price"));
				item.setSellPoint((String) doc.get("item_sell_point"));
				List<String> hList = highlighting.get(doc.get("id")).get("item_title");
				if(hList != null && !hList.isEmpty()) {
					item.setTitle(hList.get(0));
				} else {
					item.setTitle((String) doc.get("item_title"));
				}
				resList.add(item);
			}
			result.setItemList(resList);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return result;
	}
}
