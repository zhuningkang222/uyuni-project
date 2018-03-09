package com.uyuni.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * Dubbo配置类
 * @author zhanghailin
 *
 */
@Configuration
@PropertySource("classpath:dubbo.properties")
@ImportResource({"classpath:dubbo/*.xml"})
public class DubboConfiguration {

}
