package org.ppl.mall.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.ppl.mall.pojo.TbCartItem;
import org.ppl.mall.pojo.TbCartItemExample;

public interface TbCartItemMapper {
    int countByExample(TbCartItemExample example);

    int deleteByExample(TbCartItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbCartItem record);

    int insertSelective(TbCartItem record);

    List<TbCartItem> selectByExample(TbCartItemExample example);

    TbCartItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbCartItem record, @Param("example") TbCartItemExample example);

    int updateByExample(@Param("record") TbCartItem record, @Param("example") TbCartItemExample example);

    int updateByPrimaryKeySelective(TbCartItem record);

    int updateByPrimaryKey(TbCartItem record);
}