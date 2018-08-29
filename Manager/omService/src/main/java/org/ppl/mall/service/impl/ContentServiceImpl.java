package org.ppl.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.ppl.mall.mapper.TbContentMapper;
import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.pojo.TbContent;
import org.ppl.mall.pojo.TbContentExample;
import org.ppl.mall.pojo.TbContentExample.Criteria;
import org.ppl.mall.service.ContentService;
import org.ppl.mall.util.MsgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	//分页查询内容列表
	@Override
	public DataGridResult<TbContent> getContentList(Long catId, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(catId);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		DataGridResult<TbContent> dgResult = new DataGridResult<>();
		dgResult.setTotal(pageInfo.getTotal());
		dgResult.setRows(pageInfo.getList());
		return dgResult;
	}
	
	//查询内容列表
	@Override
	public List<TbContent> getContentList(Long catId) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(catId);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		return list;
	}
	
	//添加内容
	@Override
	public MsgResult addContent(TbContent content) {
		Date curTime = new Date();
		content.setCreated(curTime);
		content.setUpdated(curTime);
		contentMapper.insertSelective(content);
		return MsgResult.ok();
	}

	//删除内容
	@Override
	public MsgResult deleteContents(long id) {
		contentMapper.deleteByPrimaryKey(id);
		return MsgResult.ok();
	}

	//编辑内容
	@Override
	public MsgResult editContent(TbContent content) {
		content.setUpdated(new Date());
		contentMapper.updateByPrimaryKeySelective(content);
		return MsgResult.ok();
	}

}
