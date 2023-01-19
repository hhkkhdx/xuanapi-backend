package com.example.xuanapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.xuanapiclientsdk.model.User;
import com.example.xuanapiclientsdk.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 调用API客户端
 * @author: xuan 
 * @date: 2023/1/19 15:58
 **/
public class XuanApiClient {

    private String assessKey;

    private String secretKey;

    public XuanApiClient(String assessKey, String secretKey) {
        this.assessKey = assessKey;
        this.secretKey = secretKey;
    }


    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> map = new HashMap<>();
        map.put("assessKey", assessKey);
        // 一定不能直接发送
//        map.put("secretKey", secretKey);
        map.put("nonce", RandomUtil.randomNumbers(4));
        map.put("body", body);
        map.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
        String sign = SignUtils.getSign(body, secretKey);
        map.put("sign", sign);
        return map;
    }



    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        return HttpUtil.get("http://localhost:8081/api/name/", paramMap);
    }

    public String getNameByPost(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        return HttpUtil.post("http://localhost:8081/api/name/", paramMap);
    }

    public String getUserNameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        return HttpRequest.post("http://localhost:8081/api/name/user").body(json).addHeaders(getHeaderMap(json)).execute().body();
    }


}
