package org.ppl.mall.model;

import java.io.Serializable;
import java.util.List;

/**
 * DataGrid结果类
 * @author PPL
 */
public class DataGridResult<T> implements Serializable{

    /*********************Field**********************/
    /*-------------------field-----------------------*/
	private long total;
	private List<T> rows;

    /*********************Method**********************/
    /*-----------------public method-----------------*/
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
