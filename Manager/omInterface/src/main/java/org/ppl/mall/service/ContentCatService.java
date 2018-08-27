package org.ppl.mall.service;

import java.util.List;

import org.ppl.mall.model.TreeNode;
import org.ppl.mall.util.MsgResult;

public interface ContentCatService {
	List<TreeNode> getContentCatList(long parentId);
	MsgResult addContentCat(long parentId, String name);
}
