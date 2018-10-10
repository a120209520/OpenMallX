package org.ppl.mall.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest {
	//@Test
	public void addDocument() throws Exception {
		//创建连接
		SolrServer server = new HttpSolrServer("http://192.168.25.133:8080/solr/collection1");
		//创建文档对象
		SolrInputDocument document = new SolrInputDocument();
		//向文档中添加域
		document.addField("id", "doc01");
		document.addField("item_title", "测试01");
		document.addField("item_price", 1000);
		//将文档写入索引库
		server.add(document);
		//提交
		server.commit();
	}
	
	//@Test
	public void deleteDocument() throws Exception {
		//创建连接
		SolrServer server = new HttpSolrServer("http://192.168.25.133:8080/solr/collection1");
		//删除文档
		//solrServer.deleteById("doc01");
		server.deleteByQuery("id:doc01");
		//提交
		server.commit();
	}
	
	//@Test
	public void queryIndex() throws Exception {
		//创建连接
		SolrServer server = new HttpSolrServer("http://192.168.25.133:8080/solr/collection1");
		//创建查询对象
		SolrQuery query = new SolrQuery();
		query.set("q", "*:*");
		QueryResponse res = server.query(query);
		SolrDocumentList list = res.getResults();
		System.out.println("page size="+list.size());
		System.out.println("total="+list.getNumFound());
		for (SolrDocument doc : list) {
			System.out.println(doc.get("id"));
		}
	}
	
	@Test
	public void queryIndex2 () throws Exception {
		//创建连接
		SolrServer server = new HttpSolrServer("http://192.168.25.133:8080/solr/collection1");
		//创建查询对象
		SolrQuery query = new SolrQuery();
		query.setQuery("手机");
		query.setStart(0);
		query.setRows(5);
		query.set("df", "item_title");
		QueryResponse res = server.query(query);
		SolrDocumentList list = res.getResults();
		System.out.println("page size="+list.size());
		System.out.println("total="+list.getNumFound());
		for (SolrDocument doc : list) {
			System.out.println(doc.get("id"));
		}
	}
}
