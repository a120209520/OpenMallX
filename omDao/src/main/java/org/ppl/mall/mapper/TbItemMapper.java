package org.ppl.mall.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.ppl.mall.model.SearchItem;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.pojo.TbItemExample;

public interface TbItemMapper {
    int countByExample(TbItemExample example);

    int deleteByExample(TbItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    List<TbItem> selectByExample(TbItemExample example);

    TbItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByExample(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);
    
    /* 自定义接口 */
    List<SearchItem> getSearchItemList();
    SearchItem getSearchItemById(long id);
}