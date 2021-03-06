package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.*;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;

/**
 * @author dingshh
 * @since 1.7.1
 */
@Configuration
public class NacosConfig {
    @Bean
    public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder(){return JSON::toJSONString; }

    @Bean
    public Converter<List<DegradeRuleEntity>, String> degradeRuleEntityEncoder(){return JSON::toJSONString; }

    @Bean
    public Converter<List<ParamFlowRuleEntity>, String> paramFlowRuleEntityEncoder(){return JSON::toJSONString; }

    @Bean
    public Converter<List<SystemRuleEntity>, String> systemRuleEntityEncoder(){return JSON::toJSONString; }

    @Bean
    public Converter<List<AuthorityRuleEntity>, String> authorityRuleEntityEncoder(){return JSON::toJSONString; }

    @Bean
    public Converter<List<GatewayFlowRuleEntity>, String> gatewayFlowRuleEntityEncoder(){return JSON::toJSONString; }

    @Bean
    public Converter<List<ApiDefinitionEntity>, String> apiDefinitionEntityEncoder(){return JSON::toJSONString; }

    @Bean
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, FlowRuleEntity.class);
    }

    @Bean
    public Converter<String, List<DegradeRuleEntity>> degradeRuleEntityDecoder() {
        return s -> JSON.parseArray(s, DegradeRuleEntity.class);
    }

    @Bean Converter<String, List<ParamFlowRuleEntity>> paramFlowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, ParamFlowRuleEntity.class);
    }

    @Bean
    public Converter<String,List<SystemRuleEntity>> systemRuleEntityDecoder() {
        return s -> JSON.parseArray(s, SystemRuleEntity.class);
    }

    @Bean
    public Converter<String,List<AuthorityRuleEntity>> authorityRuleEntityDecoder() {
        return s -> JSON.parseArray(s, AuthorityRuleEntity.class);
    }

    @Bean
    public Converter<String,List<GatewayFlowRuleEntity>> gatewayFlowRuleEntityDecoder(){
        return s -> JSON.parseArray(s, GatewayFlowRuleEntity.class);
    }

    @Bean
    public Converter<String,List<ApiDefinitionEntity>> apiDefinitionEntityDecoder(){
        return s -> JSON.parseArray(s, ApiDefinitionEntity.class);
    }

    @Value("${nacos.server-addr}")
    private String serverAddr;
    @Bean
    public ConfigService nacosConfigService() throws Exception {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR,serverAddr);
//        properties.put(PropertyKeyConst.NAMESPACE, "130e71fa-97fe-467d-ad77-967456f2c16d");
        return ConfigFactory.createConfigService(properties);
    }
}
