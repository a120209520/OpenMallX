package org.ppl.mall.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie 工具类
 * @author PPL
 */
public final class CookieUtils {

    /*********************Field**********************/
    /*----------------static field-------------------*/
    public static final String LOCALHOST = "localhost";


    /*********************Method**********************/
    /*--------------public static method-------------*/

    /**
     * 得到Cookie的值
     * @param request 从Controller传入的request
     * @param cookieName 需要获取的cookie名
     * @param isDecoder 是否需要解码
     * @return cookie值
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(cookieName)) {
                    if (isDecoder) {
                        retValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } else {
                        retValue = cookie.getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 得到Cookie的值, 不解码
     * @param request 从Controller传入的request
     * @param cookieName 需要获取的cookie名
     * @return cookie值
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    /**
     * 设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码
     * @param request 从Controller传入的request
     * @param response response
     * @param cookieName cookieName
     * @param cookieValue cookieName
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }

    /**
     * 设置Cookie的值 在指定时间内生效,但不编码
     * @param request 从Controller传入的request
     * @param response response
     * @param cookieName cookieName
     * @param cookieValue cookieValue
     * @param cookieMaxage cookie存活秒数
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, int cookieMaxage) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
    }

    /**
     * 设置Cookie的值 不设置生效时间,但编码
     * @param request 从Controller传入的request
     * @param response response
     * @param cookieName cookieName
     * @param cookieValue cookieValue
     * @param isEncode 是否编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数
     * @param request 从Controller传入的request
     * @param response response
     * @param cookieName cookieName
     * @param cookieValue cookieValue
     * @param cookieMaxage cookie存活秒数
     * @param isEncode 是否编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, int cookieMaxage, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数(指定编码)
     * @param request 从Controller传入的request
     * @param response response
     * @param cookieName cookieName
     * @param cookieValue cookieValue
     * @param cookieMaxage cookie存活秒数
     * @param encodeType 编码格式
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, int cookieMaxage, String encodeType) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, encodeType);
    }

    /**
     * 删除Cookie带cookie域名
     * @param request 从Controller传入的request
     * @param response response
     * @param cookieName 需要获取的cookie名
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
            String cookieName) {
        doSetCookie(request, response, cookieName, "", -1, false);
    }


    /*-----------------private method----------------*/

    //设置Cookie的值，并使其在指定时间内生效
    //cookieMaxage 存活时间, 秒数
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
            String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0) {
                cookie.setMaxAge(cookieMaxage);
            }
            // 设置域名的cookie
            if (null != request) {
            	String domainName = getDomainName(request);
                if (!LOCALHOST.equals(domainName)) {
                	cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
        	 e.printStackTrace();
        }
    }

    //设置Cookie的值，并使其在指定时间内生效
    //cookieMaxage 存活时间, 秒数
    private static void doSetCookie(HttpServletRequest request, HttpServletResponse response,
            String cookieName, String cookieValue, int cookieMaxage, String encodeType) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeType);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0) {
                cookie.setMaxAge(cookieMaxage);
            }
            // 设置域名的cookie
            if (null != request) {
            	String domainName = getDomainName(request);
                if (!LOCALHOST.equals(domainName)) {
                	cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
        	 e.printStackTrace();
        }
    }

    //得到cookie的域名
    private static String getDomainName(HttpServletRequest request) {
        String domainName;

        String serverName = request.getRequestURL().toString();
        if ("".equals(serverName)) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {
                // www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len > 1) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        if (domainName.indexOf(":") > 0) {
            String[] arr = domainName.split(":");
            domainName = arr[0];
        }
        return domainName;
    }
}
