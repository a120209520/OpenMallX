package org.ppl.mall.model;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable{
	private long totalCount;
	private int pageCount;
	private List<SearchItem> itemList;
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
