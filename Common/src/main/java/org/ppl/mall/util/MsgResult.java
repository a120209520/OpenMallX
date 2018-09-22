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

    public static final int SUCCESS = 200;
    public static final int REQUEST_ERROR = 400;
    public static final int SERVER_ERROR = 500;

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    private MsgResult() {}
    
    private MsgResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private MsgResult(Object data) {
        this.status = SUCCESS;
        this.msg = "OK";
        this.data = data;
    }
    
    public static MsgResult build(Integer status, String msg, Object data) {
        return new MsgResult(status, msg, data);
    }

    public static MsgResult ok(Object data) {
        return new MsgResult(data);
    }

    public static MsgResult ok() {
        return new MsgResult(null);
    }

    public static MsgResult build(Integer status, String msg) {
        return new MsgResult(status, msg, null);
    }

    
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
            if (cla != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), cla);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), cla);
                }
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

}
