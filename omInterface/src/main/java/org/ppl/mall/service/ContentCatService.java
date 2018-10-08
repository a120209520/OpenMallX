package org.ppl.mall.service;

import java.util.List;

import org.ppl.mall.model.TreeNode;
import org.ppl.mall.util.MsgResult;

/**
 * Web内容分类Service接口
 * 用于Web端展示广告、活动等内容
 * @author PPL
 */
public interface ContentCatService {
	List<TreeNode> getContentCatList(long parentId);
	MsgResult addContentCat(long parentId, String name);
	MsgResult updateContentCat(Long id, String name);
	MsgResult deleteContentCat(Long id);
}
