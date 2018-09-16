package org.ppl.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.ppl.mall.jedis.JedisClient;
import org.ppl.mall.mapper.TbItemDescMapper;
import org.ppl.mall.mapper.TbItemMapper;
import org.ppl.mall.model.DataGridResult;
import org.ppl.mall.pojo.TbContent;
import org.ppl.mall.pojo.TbItem;
import org.ppl.mall.pojo.TbItemDesc;
import org.ppl.mall.pojo.TbItemExample;
import org.ppl.mall.service.ItemService;
import org.ppl.mall.service.message.ItemAddMessageDispatcher;
import org.ppl.mall.util.IDUtils;
import org.ppl.mall.util.JsonUtils;
import org.ppl.mall.util.MsgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品管理Service
 * @author Smith
 *
 */

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
    private ItemAddMessageDispatcher itemAddMessageDispatcher;

	@Autowired
    private JedisClient jedisClient;
	@Value("${redis.item.prefix}")
    private String ITEM_PREFIX;
	@Value("${redis.item_desc.prefix}")
    private String ITEM_DESC_PREFIX;

	//redis存活时间
	private static final int ITEM_CACHE_TIMEOUT = 3600;
    private static final int ITEM_DESC_CACHE_TIMEOUT = 3600;

	//通过id查询单个商品
	@Override
	public TbItem getItemById(long itemId) {
        //优先查询redis缓存
        String json = jedisClient.get(ITEM_PREFIX+itemId);
        if(StringUtils.isNotBlank(json)) {
            TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
            System.out.println("get data from Redis!!!");
            return item;
        }

        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        System.out.println("get data from Mysql!!!");

        //从数据库查询后，存入redis缓存
        if(item != null) {
            jedisClient.set(ITEM_PREFIX+itemId, JsonUtils.objectToJson(item));
            jedisClient.expire(ITEM_PREFIX+itemId, ITEM_CACHE_TIMEOUT);
        }

		return item;
	}
	
	@Override
	public TbItemDesc getItemDescById(long itemId) {
        //优先查询redis缓存
        String json = jedisClient.get(ITEM_DESC_PREFIX+itemId);
        if(StringUtils.isNotBlank(json)) {
            TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
            System.out.println("get data from Redis!!!");
            return itemDesc;
        }

        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        System.out.println("get data from Mysql!!!");

        //从数据库查询后，存入redis缓存
        if(itemDesc != null) {
            jedisClient.set(ITEM_DESC_PREFIX+itemId, JsonUtils.objectToJson(itemDesc));
            jedisClient.expire(ITEM_DESC_PREFIX+itemId, ITEM_DESC_CACHE_TIMEOUT);
        }

		return itemDesc;
	}
	
	//获取商品列表
	@Override
	public DataGridResult<TbItem> getItemList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<TbItem> list = itemMapper.selectByExample(new TbItemExample());
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		DataGridResult<TbItem> dgResult = new DataGridResult<>();
		dgResult.setTotal(pageInfo.getTotal());
		dgResult.setRows(pageInfo.getList());
		return dgResult;
	}

	//添加商品
	@Override
	public MsgResult addItem(TbItem item, String desc) {
		Date curTime = new Date();
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus(TbItem.STATUS_NORMAL);
		item.setCreated(curTime);
		item.setUpdated(curTime);
		itemMapper.insert(item);
		
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(curTime);
		itemDesc.setUpdated(curTime);
		itemDescMapper.insert(itemDesc);

        itemAddMessageDispatcher.sendMsg(itemId);

		return MsgResult.ok();
	}

	//编辑商品
	@Override
	public MsgResult editItem(TbItem item, String desc) {
		itemMapper.updateByPrimaryKeySelective(item);
		
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(new Date());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		
		return MsgResult.ok();
	}
	
	//删除商品
	@Override
	public MsgResult deleteItem(long itemId) {
		itemMapper.deleteByPrimaryKey(itemId);
		itemDescMapper.deleteByPrimaryKey(itemId);
		return MsgResult.ok();
	}

	//下架商品
	@Override
	public MsgResult unShelveItem(long itemId) {
		TbItem item = new TbItem();
		item.setId(itemId);
		item.setStatus(TbItem.STATUS_SOLDOUT);
		itemMapper.updateByPrimaryKeySelective(item);
		return MsgResult.ok();
	}

	//上架商品
	@Override
	public MsgResult reShelfItem(long itemId) {
		TbItem item = new TbItem();
		item.setId(itemId);
		item.setStatus(TbItem.STATUS_NORMAL);
		itemMapper.updateByPrimaryKeySelective(item);
		return MsgResult.ok();
	}
}
