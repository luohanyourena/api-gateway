package com.letter.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 *
 * zuul 跨域配置
 * create:luohan
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowCredentials(true); //支持跨域
        corsConfiguration.setAllowedHeaders(Arrays.asList("*")); //支持所有公告头
        corsConfiguration.setAllowedMethods(Arrays.asList("*")); //所有方法
        corsConfiguration.setAllowedOrigins(Arrays.asList("*")); //所有原始域
        corsConfiguration.setMaxAge(300L); //缓存时间

        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(source);
    }

}
