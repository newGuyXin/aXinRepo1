package hc.test.web.controller.demo;

import com.github.pagehelper.PageInfo;
import hc.test.web.controller.base.BaseController;
import hc.test.web.entity.demo.TApply;
import hc.test.web.entity.demo.TConference;
import hc.test.web.model.base.AjaxJson;
import hc.test.web.model.demo.Conference;
import hc.test.web.service.demo.IConferenceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/conference")
public class ConferenceController extends BaseController {

    @Autowired
    private IConferenceService conferenceService;

    /**
     * 保存单个用户信息
     *
     * @param conference
     * @param response
     */
    @PostMapping("/saveConference")
    public void saveConference(@RequestBody TConference conference, HttpServletResponse response) {
        AjaxJson json = new AjaxJson();
        try {
            if (StringUtils.isNotEmpty(conference.getName()) && conference.getCode() != null) {
                //判断输入的会议室编号和名称是否重复
                Integer count = conferenceService.checkConferenceExist(conference.getCode(), conference.getName());
                if (count > 0) {
                    json.setSuccess(false);
                    json.setMsg("保存失败！会议室编码或名称已经存在！");
                } else {
                    conferenceService.saveConference(conference);
                    json.setSuccess(true);
                    json.setMsg("新增会议室成功!");
                }
            } else {
                json.setSuccess(false);
                json.setMsg("保存失败！会议室编码和名称不能为空！");
            }
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg("系统异常" + e.getMessage());
            e.printStackTrace();
            logger.error("系统异常", e);
        }
        writeJson(json, response);
    }

    /**
     * 通过用户名删除用户信息
     *
     * @param id
     * @param response
     */
    @GetMapping("/deleteConference")
    public void deleteConference(@RequestParam("id") String id, HttpServletResponse response) {
        AjaxJson json = new AjaxJson();
        try {
            conferenceService.deleteConference(id);
            json.setSuccess(true);
            json.setMsg("删除成功");
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg("系统异常" + e.getMessage());
            e.printStackTrace();
            logger.error("系统异常", e);
        }
        writeJson(json, response);
    }

    /**
     * 通过传入的参数更新会议室信息
     *
     * @param conference
     * @param response
     */
    @PostMapping("/updateConference")
    public void updateConference(@RequestBody TConference conference, HttpServletResponse response) {
        AjaxJson json = new AjaxJson();
        try {


            boolean flag = conferenceService.updateConference(conference);

            if (!flag) {
                json.setSuccess(false);
                json.setMsg("输入的会议室名称错误或已存在，编号必须为数字");

            } else {
                json.setMsg("更新成功");
                json.setSuccess(true);
            }
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg("系统异常" + e.getMessage());
            e.printStackTrace();
            logger.error("系统异常", e);
        }
        writeJson(json, response);
    }

    /**
     * 根据传入的查询字段,分页，模糊查询会议室
     *
     * @param request
     * @param response
     */
    @GetMapping("/pageAll")
    public void pageAll(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson json = new AjaxJson();
        try {
            Map<String, Object> param = buildRequestMap(request);
            Conference conference = new Conference();
            mapToObject(conference,param);

            String msg = validatePageParam(conference.getPageNum(), conference.getPageSize());

            if (StringUtils.isNotEmpty(msg)) {
                json.setMsg(msg);
                json.setSuccess(false);

            } else {
                PageInfo<TConference> tConferencePageInfo = conferenceService.pageAll(conference);
                json.setObj(tConferencePageInfo);
                json.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setSuccess(false);
            logger.error("系统异常", e);
            json.setMsg("系统异常" + e.getMessage());
        }
        writeJson(json, response);
    }

    @GetMapping("findById")
    public void findById(@RequestParam("id") String id, HttpServletResponse response) {
        AjaxJson json = new AjaxJson();
        try {
            TConference byId = conferenceService.findById(id);
            json.setSuccess(true);
            json.setObj(byId);
            json.setMsg("查询成功");
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg("系统异常" + e.getMessage());
            e.printStackTrace();
            logger.error("系统异常", e);
        }
        writeJson(json, response);
    }

}
