package hc.test.web.entity.demo;

import hc.test.web.entity.base.BaseEntity;
import hc.test.web.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class TApply extends BaseEntity {


    private String title;    //会议室标题
    private String name;    //会议室名称
    private Integer code;    //会议室编号
    private String user;    //申请人
    private Date startDate;    //开始时间
    private Date endDate;    //结束时间
    private String participant;    //参与人员
    private String remark;    //申请备注
    private Boolean state;    //会议室状态

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    private String cid;      //会议室id

    private List<String> users;

    public Date getStartDate() {

        return startDate;
    }

    public void setStartDate(String startDate) throws ParseException {
        Date date = DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm");
        this.startDate = date;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) throws ParseException {
        Date date = DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm");
        this.endDate = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
