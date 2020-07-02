package hc.test.web.model.demo;

import hc.test.web.model.base.PageModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Apply extends PageModel {

    private String name;

    private Integer code;

    private String user;

    private String title;

    private Date startDate;

    private Date endDate;

    private Boolean status;
}
