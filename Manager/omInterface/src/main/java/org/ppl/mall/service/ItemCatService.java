package org.ppl.mall.service;

import java.util.List;

import org.ppl.mall.model.TreeNode;

public interface ItemCatService {
	List<TreeNode> getItemCatList(long parentId);
}
