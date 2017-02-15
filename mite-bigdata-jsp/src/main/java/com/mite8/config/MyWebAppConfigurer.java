package com.mite8.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Author: blogchong
 * Time:  2016/12/14.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  加载外部资源
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter{
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/myres/**").addResourceLocations("file:C:/myres/");
        registry.addResourceHandler("/myres/**").addResourceLocations("file:/data/application/mite/myres/");
        super.addResourceHandlers(registry);
    }
}
