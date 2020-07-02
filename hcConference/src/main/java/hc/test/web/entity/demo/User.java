package hc.test.web.entity.demo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import hc.test.web.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class User extends BaseEntity {

    private String username;
    private String realname;

    private String password;


    /*//用户部门
    @Excel(name = "用户工号", orderNum = "1")
    private String emp_code;*/
}
