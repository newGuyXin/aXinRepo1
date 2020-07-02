package hc.test.web.entity.demo;

import hc.test.web.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 会议室实体类
 */
@Getter
@Setter
public class TConference extends BaseEntity {

    //会议室名称
    private String name;

    //会议室编号
    private Integer code;

    //备注信息
    private String remark;

}
