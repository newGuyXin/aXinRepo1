package hc.test.web.model.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @version V0.1
 * @项目名称：springBootDemo
 * @类名称：PageModel
 * @类描述：
 * @创建人：justin
 * @创建时间：2018-05-03 17:30
 */
@Getter
@Setter
public class PageModel implements Serializable {
    protected int pageNum;
    protected int pageSize;
}
