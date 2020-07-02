package hc.test.web.model.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @version V0.1
 * @项目名称：springBootDemo
 * @类名称：AjaxJson
 * @类描述：
 * @创建人：justin
 * @创建时间：2018-05-03 15:46
 */
@Setter
@Getter
public class AjaxJson implements Serializable{
    /**
     * 成功与否标识
     */
    private boolean success;
    /**
     * 消息代码
     */
    private String code;
    /**
     * 消息文本
     */
    private String msg;
    /**
     * 消息内容
     */
    private Object obj;

}
