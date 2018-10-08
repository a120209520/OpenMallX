package org.ppl.mall.util;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Web层通用结果类
 * @author Smith
 */
public class MsgResult implements Serializable{

    /*********************Field**********************/
    /*----------------static field-------------------*/
    public static final int SUCCESS = 200;
    public static final int REQUEST_ERROR = 400;
    public static final int SERVER_ERROR = 500;
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /*-------------------field-----------------------*/
    // 响应业务状态
    private Integer status;
    // 响应消息
    private String msg;
    // 响应中的数据
    private Object data;


    /*********************Method**********************/
    /*----------------constructor--------------------*/
    private MsgResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /*--------------public static method-------------*/
    /**
     * 创建对象
     * @param status
     * @param msg
     * @param data
     * @return
     */
    public static MsgResult build(Integer status, String msg, Object data) {
        return new MsgResult(status, msg, data);
    }

    /**
     * 创建对象
     * @param status
     * @param msg
     * @return
     */
    public static MsgResult build(Integer status, String msg) {
        return build(status, msg, null);
    }

    /**
     * 创建SUCCESS对象
     * @param data
     * @return
     */
    public static MsgResult ok(Object data) {
        return build(SUCCESS, "OK", data);
    }

    /**
     * 创建SUCCESS对象
     * @return
     */
    public static MsgResult ok() {
        return build(SUCCESS, "OK", null);
    }

    /**
     * 将json结果集转化为MsgResult对象
     *
     * @param jsonData json数据
     * @param cla MsgResult中的object类型
     * @return
     */
    public static MsgResult formatToPojo(String jsonData, Class<?> cla) {
        try {
            if (cla == null) {
                return MAPPER.readValue(jsonData, MsgResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isObject()) {
                obj = MAPPER.readValue(data.traverse(), cla);
            } else if (data.isTextual()) {
                obj = MAPPER.readValue(data.asText(), cla);
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static MsgResult format(String json) {
        try {
            return MAPPER.readValue(json, MsgResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param cla 集合中的类型
     * @return
     */
    public static MsgResult formatToList(String jsonData, Class<?> cla) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, cla));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }


    /*-----------------public method-----------------*/
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
