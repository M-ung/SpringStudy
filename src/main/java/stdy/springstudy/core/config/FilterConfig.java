package stdy.springstudy.core.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stdy.springstudy.core.filter.MyFilter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<MyFilter> filter() {
        FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<>(new MyFilter());
        bean.addUrlPatterns("/**");
        bean.setOrder(0);
        return bean;
    }
}