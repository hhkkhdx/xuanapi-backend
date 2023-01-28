package com.example.xuanapigateway.filter;

import com.example.xuanapigateway.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;


@Component
@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
//        // 1. 请求日志 => RequestLogGlobalFilter
//        String path = request.getURI().getPath();
//        // 2. （黑白名单） todo: 查数据库
//
//        // 3. 用户鉴权（判断ak，sk是否合法）
//        String assessKey = request.getHeaders().getFirst("assessKey");
//        String timeStamp = request.getHeaders().getFirst("timeStamp");
//        String body = request.getHeaders().getFirst("body");
//        String sign = request.getHeaders().getFirst("sign");
//
//        // todo 根据 ak 查询数据库中的 sk 生成签名 与用户传递的进行比对
//        String dbSign = SignUtils.getSign(body, "abcdefgh");
//
//        // todo 数据库查 ak sk
//        if (!("xuan".equals(assessKey) && dbSign.equals(sign))) {
//            throw new RuntimeException("无权限");
//        }
//        long currentTime = System.currentTimeMillis() / 1000;
//        final long EXPIRE_TIME = 60 * 5L;
//        if ((currentTime - Long.parseLong(timeStamp)) > EXPIRE_TIME) {
//            throw new RuntimeException("已过期");
//        }


        // 4. 请求的模拟接口是否存在

        // 5. 请求转发，调用模拟接口
        Mono<Void> mono = chain.filter(exchange);


        // 6. 响应日志 => ResponseLogGlobalFilter

        // 7. 调用成功，接口调用次数 + 1

        // 8. 调用失败，返回一个规范的错误码

        return mono;
    }

    @Override
    public int getOrder() {
        return -1;
    }
}