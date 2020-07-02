package hc.test.web.model.demo;

import hc.test.web.model.base.PageModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @version V0.1
 * @项目名称：springBootDemo
 * @类名称：TestDemo
 * @类描述：
 * @创建人：justin
 * @创建时间：2018-05-10 11:05
 */
@Getter
@Setter
public class Conference extends PageModel {
    //会议室名称
    private String name;

    //会议室编号
    private Integer code;
}
