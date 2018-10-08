package org.ppl.mall.model;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索结果类
 * @author PPL
 */
public class SearchResult implements Serializable{

    /*********************Field**********************/
    /*-------------------field-----------------------*/
	private long totalCount;
	private int pageCount;
	private List<SearchItem> itemList;

    /*********************Method**********************/
    /*-----------------public method-----------------*/
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<SearchItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
}
