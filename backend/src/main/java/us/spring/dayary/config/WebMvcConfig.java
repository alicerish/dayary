package us.spring.dayary.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import us.spring.dayary.common.filter.DayaryFilter;
import us.spring.dayary.common.interceptor.DayaryInterceptor;
import us.spring.dayary.common.resolver.AuthArgumentResolver;

import java.util.List;

@Configuration
@EnableWebMvc //WebMvcConfigurationSupport 빈으로 등록 -> WebMvcAutoConfiguration 비활성
public class WebMvcConfig {

    private final DayaryInterceptor dayaryInterceptor;

    public WebMvcConfig(DayaryInterceptor dayaryInterceptor) {
        this.dayaryInterceptor = dayaryInterceptor;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
                registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
                registry.addResourceHandler("/image/**").addResourceLocations("classpath:/static/image/");
                registry.addResourceHandler("/error/**").addResourceLocations("classpath:/templates/error/");
                registry.addResourceHandler("/robots.txt").addResourceLocations("classpath:/static/doc/");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(dayaryInterceptor)
                        .addPathPatterns("/**")
                        .excludePathPatterns(
                                "/",
                                "/robots.txt",
                                "/error/**",
                                "/logIn",
                                "/signUp"
                        );
            }

            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
                resolvers.add(new AuthArgumentResolver());
            }
        };
    }

    @Bean
    public FilterRegistrationBean getFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new DayaryFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
