package org.ppl.mall.service;

import java.util.List;

import org.ppl.mall.model.TreeNode;

public interface ContentCatService {
	List<TreeNode> getContentCatList(long parentId);
}
