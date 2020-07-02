package hc.test.web.controller.base;

import com.alibaba.fastjson.JSON;
import hc.test.web.entity.demo.TApply;
import hc.test.web.model.base.AjaxJson;
import hc.test.web.model.demo.Apply;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V0.1
 * @项目名称：springBootDemo
 * @类名称：BaseController
 * @类描述：
 * @创建人：justin
 * @创建时间：2018-05-04 11:13
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @desc：返回json格式的字符串
     * @author：justin
     * @date：2018-05-04 11:43
     */
    public String returnJson(AjaxJson json) {
        logger.info("返回的json格式：" + JSON.toJSONString(json));
        return JSON.toJSONString(json);
    }

    /**
     * 描述：返回json格式数据到前端页面
     *
     * @author justin
     * @创建时间 2017年6月14日 下午3:24:20
     */
    public void writeJson(Object object, HttpServletResponse response) {
        try {
            String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
            response.setContentType("text/html;charset=utf-8");
            // response.setContentType("application/json");
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Map<String, Object> buildRequestMap(HttpServletRequest request) {
        Map<String, Object> requestMap = new HashMap<>();
        for (Enumeration<?> e = request.getParameterNames(); e.hasMoreElements(); ) {
            String key = e.nextElement().toString();
            Object value = request.getParameter(key);
            requestMap.put(key, value.toString());

        }
        return requestMap;
    }

    /**
     * @description：验证分页参数pageNum和pageSize
     * @author：justin
     * @date：2019-10-14 10:08
     */
    public String validatePageParam(int pageNum, int pageSize) {
        String msg = "";
        if (pageNum <= 0 || pageSize <= 0) {
            msg = "参数【pageNum】或【pageSize】异常！必须是大于0的整数！";
        }
        return msg;
    }

    //BeanUtils不能封装Date类型
    public static <T> T map1ToObject(Map<String, Object> map, Class<T> beanClass) throws Exception {
        T obj = null;
        if (map == null)
            return null;

        obj = beanClass.newInstance();
        BeanUtils.populate(obj, map);
        return obj;


    }

    public void mapToObject(Object obj, Map<String, Object> param) throws InvocationTargetException, IllegalAccessException {

        //解决BeanUtils不能封装Date类型
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class clazz, Object value) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date parse = null;
                try {

                    //if (value.equals("") || value == null) {
                    //value = "2018-05-25 ";}

                    parse = format.parse(value.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return parse;
            }
        }, Date.class);
        BeanUtils.populate(obj, param);
    }
}
