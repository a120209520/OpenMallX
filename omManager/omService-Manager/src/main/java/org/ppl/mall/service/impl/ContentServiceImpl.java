package org.ppl.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.ppl.mall.jedis.JedisClient;
import org.ppl.mall.mapper.TbContentMapper;
import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.pojo.TbContent;
import org.ppl.mall.pojo.TbContentExample;
import org.ppl.mall.pojo.TbContentExample.Criteria;
import org.ppl.mall.service.ContentService;
import org.ppl.mall.util.JsonUtils;
import org.ppl.mall.util.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 内容Service
 * @author PPL
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class ContentServiceImpl implements ContentService {

	/*********************Field**********************/
	/*-------------------field-----------------------*/
	@Autowired
	private TbContentMapper contentMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${redis.content_list}")
	private String CONTENT_LIST;


    /*********************Method**********************/
    /*-----------------public method-----------------*/

    /**
     * 分页查询内容列表
     * @param catId 内容ID
     * @param pageNum 页号
     * @param pageSize 页长
     * @return DataGrid格式结果集
     */
	@Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
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

    /**
     * 查询内容列表
     * @param catId 内容ID
     * @return List<TbContent>
     */
	@Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<TbContent> getContentList(Long catId) {
		//取redis缓存
		String json = jedisClient.hget(CONTENT_LIST, catId.toString());
		if(StringUtils.isNotBlank(json)) {
			List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
			return list;
		}
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(catId);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);

		//保存redis缓存
		jedisClient.hset(CONTENT_LIST, catId.toString(), JsonUtils.objectToJson(list));
		return list;
	}

    /**
     * 添加内容
     * @param content pojo
     * @return 添加是否成功
     */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public WebResult addContent(TbContent content) {
		Date curTime = new Date();
		content.setCreated(curTime);
		content.setUpdated(curTime);
		contentMapper.insertSelective(content);
		//更新数据后删除redis
		jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		return WebResult.ok();
	}

    /**
     * 删除内容
     * @param id 内容ID
     * @return 删除是否成功
     */
	@Override
    @Transactional(propagation=Propagation.REQUIRED)
	public WebResult deleteContents(Long id) {
		//删除前保留cid用于删除缓存
		TbContent content = contentMapper.selectByPrimaryKey(id);
		Long cid = content.getCategoryId();
		contentMapper.deleteByPrimaryKey(id);
		//更新数据后删除redis
		jedisClient.hdel(CONTENT_LIST, cid.toString());
		return WebResult.ok();
	}

    /**
     * 编辑内容
     * @param content pojo
     * @return 编辑是否成功
     */
	@Override
    @Transactional(propagation=Propagation.REQUIRED)
	public WebResult editContent(TbContent content) {
		content.setUpdated(new Date());
		contentMapper.updateByPrimaryKeySelective(content);
		//更新数据后删除redis
		jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		return WebResult.ok();
	}
}
