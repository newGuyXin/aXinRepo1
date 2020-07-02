package hc.test.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyIntercept extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //字符集拦截
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


//        logger.info("请求全路径：" + request.getRequestURL());
//        logger.info("请求项目路径：" + request.getRequestURI());
//        logger.info("请求参数：" + request.getQueryString());

        crossDomain(request, response);
        return super.preHandle(request, response,handler);

    }

    private void returnJson(HttpServletResponse response, String json) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            logger.info(json);
            writer.print(json);
        } catch (IOException e) {
            logger.error("response error", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private void crossDomain(HttpServletRequest request, HttpServletResponse response) {
        //支持跨域请求
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        //是否支持cookie跨域
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Authorization,Origin, X-Requested-With, Content-Type, Accept,Access-Token");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
//        logger.info("MyIntercept正在拦截");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);


    }


}
