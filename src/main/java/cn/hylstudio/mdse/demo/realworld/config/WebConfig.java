package cn.hylstudio.mdse.demo.realworld.config;

import cn.hylstudio.mdse.demo.realworld.config.interceptor.AuthInterceptor;
import cn.hylstudio.mdse.demo.realworld.config.interceptor.PublicInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private PublicInterceptor publicInterceptor;
    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(publicInterceptor).addPathPatterns("/**");
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/api/users/login",
                        "/api/users",
                        "/api/users/"
                );
    }
}
