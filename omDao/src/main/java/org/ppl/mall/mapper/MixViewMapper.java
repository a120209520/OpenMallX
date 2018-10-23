package org.ppl.mall.mapper;

import org.ppl.mall.model.NewProduct;

import java.util.List;

public interface MixViewMapper {
    //根据商品分类cid查询该分类和其子分类下的商品和分类信息，并按更新时间排序
    List<NewProduct> selectNewProductByCid(Long cid);

    //根据所有商品和分类信息，并按更新时间排序
    List<NewProduct> selectNewProduct();
}