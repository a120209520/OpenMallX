package org.ppl.mall.service;

import java.util.List;

import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.pojo.TbContent;
import org.ppl.mall.util.MsgResult;

public interface ContentService {
	DataGridResult<TbContent> getContentList(Long catId, Integer pageNum, Integer pageSize);
	List<TbContent> getContentList(Long catId);
	MsgResult addContent(TbContent content);
	MsgResult deleteContents(Long id);
	MsgResult editContent(TbContent content);
}
