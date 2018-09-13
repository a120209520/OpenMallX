package org.ppl.mall.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest {
	//@Test
	public void addDocument() throws Exception {
		//创建连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.133:8080/solr/collection1");
		//创建文档对象
		SolrInputDocument document = new SolrInputDocument();
		//向文档中添加域
		document.addField("id", "doc01");
		document.addField("item_title", "测试01");
		document.addField("item_price", 1000);
		//将文档写入索引库
		solrServer.add(document);
		//提交
		solrServer.commit();
	}
	
	@Test
	public void deleteDocument() throws Exception {
		//创建连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.133:8080/solr/collection1");
		//删除文档
		//solrServer.deleteById("doc01");
		solrServer.deleteByQuery("id:doc01");
		//提交
		solrServer.commit();
	}
}
