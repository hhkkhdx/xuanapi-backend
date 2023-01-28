package com.example.xuanapigateway.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @description: 签名工具
 * @author: xuan
 * @date: 2023/1/19 17:12
 **/
public class SignUtils {

    /**
     * @description: 生成签名
     * @author: xuan
     * @date: 2023/1/19 17:03
     **/
    public static String getSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String context = body + "." + secretKey;
        return md5.digestHex(context);
    }

}
