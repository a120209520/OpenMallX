package org.ppl.mall.service;

import java.util.List;

import org.ppl.mall.model.TreeNode;
import org.ppl.mall.pojo.TbItemCat;

/**
 * 商品分类Service接口
 * @author PPL
 */
public interface ItemCatService {
	List<TreeNode> getItemCatTreeList(long parentId);
    List<TbItemCat> getItemCatList(long parentId);
	List<TreeNode> getRootItemCatTreeList();
	List<TbItemCat> getRootItemCatList();
}
