package hc.test.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @version V0.1
 * @项目名称：hcmanager
 * @类名称：WebMvcConfig
 * @类描述：
 * @创建人：justin
 * @创建时间：2019-10-08 18:53
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 将自定义拦截器作为Bean写入配置
     *
     * @return
     */
    @Bean
    public MyIntercept myInterceptor() {
        return new MyIntercept();
    }

    //配置拦截的资源以及放行的资源
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor())
                // 拦截路径
                .addPathPatterns("/**")
                // 排除路径
                .excludePathPatterns("/", "/userLogin", "/logout", "/randCode")
                // 排除资源请求
                .excludePathPatterns("/static/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedHeaders("*").allowedOrigins("*").allowedMethods("*");
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Access-Control-Allow-Headers",
                        "Access-Control-Allow-Methods",
                        "Access-Control-Allow-Origin",
                        "Access-Control-Max-Age",
                        "X-Frame-Options")
                .allowCredentials(true).maxAge(14400);//单位s  14400s=60*60*4=4小时
    }

}
