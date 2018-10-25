package org.ppl.mall.service;

import java.util.List;

import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.pojo.TbContent;
import org.ppl.mall.util.WebResult;

/**
 * 内容Service接口
 * 用于Web端展示广告、活动等内容
 * @author PPL
 */
public interface ContentService {
	DataGridResult<TbContent> getContentList(Long catId, Integer pageNum, Integer pageSize);
	List<TbContent> getContentList(Long catId);
	WebResult addContent(TbContent content);
	WebResult deleteContents(Long id);
	WebResult editContent(TbContent content);
}
