package com.nsl.august.web.util;

import com.google.common.collect.Maps;

import java.util.Map;

public class ResponseUtil {



    public static Object success(Object data) {

        Map<String, Object> returnMap = Maps.newHashMap();

        returnMap.put("code", "000000");
        returnMap.put("msg", "操作成功");
        returnMap.put("data", data);
        return returnMap;
    }
    public static Object success() {

        Map<String, Object> returnMap = Maps.newHashMap();

        returnMap.put("code", "000000");
        returnMap.put("msg", "操作成功");
        return returnMap;
    }

    public static Object fail() {
        Map<String, Object> returnMap = Maps.newHashMap();

        returnMap.put("code", "999999");
        returnMap.put("msg", "操作失败");
        return returnMap;
    }

    public static Object fail(Object data) {
        Map<String, Object> returnMap = Maps.newHashMap();

        returnMap.put("code", "999999");
        returnMap.put("msg", "操作失败");
        returnMap.put("data", data);
        return returnMap;
    }
}
