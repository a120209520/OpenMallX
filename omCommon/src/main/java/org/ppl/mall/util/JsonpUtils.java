package org.ppl.mall.util;

import org.springframework.http.converter.json.MappingJacksonValue;

/**
 * Jsonp跨域包装工具
 * @author PPL
 */
public class JsonpUtils {
    /*********************Method**********************/
    /*--------------public static method-------------*/

    /**
     * 包装jsonp数据
     * @param result 返回结果
     * @param callback jsonp回调函数
     * @return jsonp包装后的返回结果
     */
    public static MappingJacksonValue jsonpResult(Object result, String callback) {
		MappingJacksonValue jValue = new MappingJacksonValue(result);
		jValue.setJsonpFunction(callback);
		return jValue;
    }
}
