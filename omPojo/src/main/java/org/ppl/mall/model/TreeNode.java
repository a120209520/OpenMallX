package org.ppl.mall.model;

import java.io.Serializable;

/**
 * 树状表视图类
 * @author PPL
 */
public class TreeNode implements Serializable {

    /*********************Field**********************/
    /*-------------------field-----------------------*/
	private long id;
	private String text;
	private String state;


    /*********************Method**********************/
    /*-----------------public method-----------------*/
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
