package org.ppl.mall.service.search.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.ppl.mall.dao.SearchDao;
import org.ppl.mall.mapper.TbItemMapper;
import org.ppl.mall.model.SearchItem;
import org.ppl.mall.model.SearchResult;
import org.ppl.mall.service.search.SearchItemService;
import org.ppl.mall.util.MsgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品搜索Service
 * @author PPL
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class SearchItemServiceImpl implements SearchItemService {

    /*********************Field**********************/
    /*-------------------field-----------------------*/
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private SearchDao searchDao;
	
	@Autowired
	private SolrServer solrServer;



    /*********************Method**********************/
    /*-----------------public method-----------------*/

    /**
     * 导入所有商品
     * @return 导入是否成功
     */
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
		return MsgResult.build(MsgResult.SERVER_ERROR, "import failed!");
	}


    /**
     * 添加单个商品
     * @param id 商品ID
     * @return 添加是否成功
     */
	@Override
	public MsgResult importItem(long id) {
        SearchItem item = itemMapper.getSearchItemById(id);
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", item.getId());
        document.addField("item_title", item.getTitle());
        document.addField("item_sell_point", item.getSellPoint());
        document.addField("item_price", item.getPrice());
        document.addField("item_image", item.getImage());
        document.addField("item_category_name", item.getCatName());
        try {
            solrServer.add(document);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return MsgResult.ok();
	}

    /**
     * 搜索商品
     * @param key solr: key
     * @param page solr: page
     * @param rows solr: rows
     * @return
     */
	@Override
	public SearchResult searchItems(String key, int page, int rows) {
		SolrQuery query = new SolrQuery();
		query.setQuery(key);
		if(page <= 0)
			page = 1;
		query.setStart((page-1)*rows);
		query.setRows(rows);
		query.set("df", "item_title");
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		SearchResult result = searchDao.search(query);
		result.setPageCount((int) Math.ceil(result.getTotalCount() * 1.0 / rows));
		return result;
	}
}
