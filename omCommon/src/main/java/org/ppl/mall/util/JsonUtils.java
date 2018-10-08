package org.ppl.mall.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Json和对象转换
 * @author PPL
 */
public class JsonUtils {

    /*********************Field**********************/
    /*----------------static field-------------------*/
    private static final ObjectMapper MAPPER = new ObjectMapper();


    /*********************Method**********************/
    /*--------------public static method-------------*/

    /**
     * object ---> json
     * @param data object
     * @return json
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * json ---> pojo
     * @param json json
     * @param beanType pojo类型
     * @return pojo
     */
    public static <T> T jsonToPojo(String json, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(json, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * json ---> List
     * @param json json
     * @param beanType List中pojo类型
     * @return List<Pojo>
     */
    public static <T>List<T> jsonToList(String json, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(json, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
}
