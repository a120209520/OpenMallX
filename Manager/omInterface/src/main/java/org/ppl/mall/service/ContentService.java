package org.ppl.mall.service;

import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.pojo.TbContent;
import org.ppl.mall.util.MsgResult;

public interface ContentService {
	DataGridResult<TbContent> getContentList(Long catId, Integer pageNum, Integer pageSize);
	MsgResult addContent(TbContent content);
	MsgResult deleteContents(long id);
	MsgResult editContent(TbContent content);
}
