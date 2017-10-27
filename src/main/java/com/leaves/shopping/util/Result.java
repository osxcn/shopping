package com.leaves.shopping.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 异步数据返回格式，统一返回内容
 * @author 偶是小菜鸟
 * Create Time: 2017-10-27 19:24
 */
public class Result {

    private static Map<String,Object> resultMap = new HashMap<String,Object>(16);

    /**
     * 成功
     * @return 成功
     */
    public static Map<String, Object> success() {
        resultMap.put("code", 200);
        resultMap.put("message", "success");
        resultMap.put("result", "true");
        return resultMap;
    }

    /**
     * 返回结果
     * @param result 结果
     * @return 结果
     */
    public static Map<String, Object> returnResult(String result) {
        resultMap.put("code", 200);
        resultMap.put("message", "success");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 失败
     * @return 返回失败
     */
    public static Map<String, Object> failed() {
        resultMap.put("code", 201);
        resultMap.put("message", "failed");
        resultMap.put("result", "false");
        return resultMap;
    }

    /**
     * 返回失败
     * @param message 响应信息
     * @return 失败
     */
    public static Map<String, Object> failed(String message) {
        resultMap.put("code", 201);
        resultMap.put("message", message);
        resultMap.put("result", "false");
        return resultMap;
    }

    /**
     * 自定义返回
     * @param code 响应码
     * @param message 响应信息
     * @param result 结果
     * @return 返回map
     */
    public static Map<String, Object> custom(Integer code, String message, String result) {
        resultMap.put("code", code);
        resultMap.put("message", message);
        resultMap.put("result", result);
        return resultMap;
    }
}
