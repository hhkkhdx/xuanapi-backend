package com.example.xuanapiclientsdk;

import com.example.xuanapiclientsdk.client.XuanApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("xuanapi.client")
@Data
@ComponentScan
public class XuanApiClientConfig {

    private String assessKey;

    private String secretKey;

    @Bean
    public XuanApiClient xuanApiClient() {
        return new XuanApiClient(assessKey, secretKey);
    }

}
