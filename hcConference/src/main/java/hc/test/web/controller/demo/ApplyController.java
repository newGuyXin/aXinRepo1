package hc.test.web.controller.demo;

import com.github.pagehelper.PageInfo;
import hc.test.web.controller.base.BaseController;
import hc.test.web.entity.demo.TApply;
import hc.test.web.model.base.AjaxJson;
import hc.test.web.model.demo.Apply;
import hc.test.web.service.demo.IApplyService;
import hc.test.web.service.demo.IConferenceService;
import hc.test.web.util.DateUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;

@Controller
@RequestMapping("/apply")
public class ApplyController extends BaseController {

    @Autowired
    private IApplyService applyService;

    @Autowired
    private IConferenceService conferenceService;

    /**
     * 新增会议申请接口
     *
     * @param apply
     * @param response
     */
    @PostMapping("/saveApply")
    public void saveApply(@RequestBody TApply apply, HttpServletResponse response) {
        AjaxJson json = new AjaxJson();
        try {
            //操作数据库具体逻辑
            String msg = checkData(apply);

            if (StringUtils.isNotEmpty(msg)) {
                json.setSuccess(false);
                json.setMsg(msg);
            } else {
                //如果格时间正确没冲突，设置参与人员
                judgementUsers(apply);
                //校验参与人员会议时间是否冲突
                String s = judgementParticipant(apply);
                if (StringUtils.isNotEmpty(s)) {
                    applyService.saveApply(apply);
                    json.setSuccess(true);
                    json.setMsg("申请成功,不过你勾选的参与人员" + s + "在此会议时间还有其他会议哦~");
                } else {
                    applyService.saveApply(apply);
                    json.setSuccess(true);
                    json.setMsg("申请成功");
                }
            }
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg("系统异常" + e.getMessage());
            e.printStackTrace();
            logger.error("系统异常", e);
        }
        writeJson(json, response);
    }

    private String checkData(TApply apply) {
        String msg = "";
        if (StringUtils.isEmpty(apply.getTitle())) {
            msg = "会议标题不能为空";
            return msg;
        }
        if (StringUtils.isEmpty(apply.getName())) {
            msg = "请选择会议室名称";
            return msg;
        }
        if (apply.getStartDate() == null || apply.getEndDate() == null ) {
            msg = "请选择会议时间";
            return msg;
        }
        if (apply.getUsers().size() <= 0) {
            msg = "请选择参与人员";
            return msg;
        }
        //判断申请会议时间是否冲突
        if ((apply.getEndDate().getTime() - apply.getStartDate().getTime()) <= 60000) {
            msg = "请输入大于一分钟的时间段";
            return msg;
        }
        if (DateUtils.dateSubtraction(apply.getStartDate(), apply.getEndDate()) >= 1) {
            msg = "会议时间不能超过一天";
            return msg;
        }
        if (!judgementDate(apply)) {
            msg = "此会议室申请的时间段有冲突，请重新选择";
            return msg;
        }
        return msg;
    }


    /**
     * 通过用户名删除用户信息
     *
     * @param id
     * @param response
     */
    @GetMapping("/deleteApply")
    public void deleteApply(@RequestParam("id") String id, HttpServletResponse response) {
        AjaxJson json = new AjaxJson();
        try {
            applyService.deleteApply(id);
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
     * 更新申请列表接口
     *
     * @param apply
     * @param response
     */
    @PostMapping("/updateApply")
    public void updateApply(@RequestBody TApply apply, HttpServletResponse response) {
        AjaxJson json = new AjaxJson();
        try {

            String msg = checkData(apply);
            if (StringUtils.isNotEmpty(msg)) {
                json.setSuccess(false);
                json.setMsg(msg);
            } else {
                //如果格时间正确没冲突，设置参与人员
                judgementUsers(apply);
                //设置之后校验参与人员会议时间是否冲突
                String s = judgementParticipant(apply);
                if (StringUtils.isNotEmpty(s)) {
                    applyService.updateApply(apply);
                    json.setMsg("修改成功,不过你勾选的参与人员" + s + "在此会议时间还有其他会议哦~");
                    json.setSuccess(true);
                } else {
                    applyService.updateApply(apply);
                    json.setMsg("修改成功");
                    json.setSuccess(true);
                }
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
     * 分页模糊查询传入字段列表接口
     *
     * @param request
     * @param response
     */
    @GetMapping("/findApply")
    public void findApply(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson json = new AjaxJson();
        try {
            //封装请求参数
            Map<String, Object> param = buildRequestMap(request);
            //Apply obj = map1ToObject(param, Apply.class);

            //将map转对象，支持转Date类型
            Apply obj = new Apply();
            mapToObject(obj, param);

            //校验分页设置是否正确
            String msg = validatePageParam(obj.getPageNum(), obj.getPageSize());

            if (StringUtils.isNotEmpty(msg)) {
                json.setMsg(msg);
                json.setSuccess(false);
            } else {
                PageInfo<TApply> tConferencePageInfo = applyService.findApply(obj);
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
            TApply byId = applyService.findById(id);
            //获取string的参与人员封装到List中
            String[] split = byId.getParticipant().split(",");
            List<String> users = Arrays.asList(split);

            byId.setUsers(users);
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

    @GetMapping("/findName")
    public void findName(HttpServletResponse response) {
        AjaxJson json = new AjaxJson();
        try {
            List<Apply> name = applyService.findName();
            json.setObj(name);
            json.setSuccess(true);
            json.setMsg("查询成功");
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg("系统异常" + e.getMessage());
            e.printStackTrace();
            logger.error("系统异常", e);
        }
        writeJson(json, response);
    }


    /**
     * 验证申请时间是否冲突
     *
     * @param apply
     * @return
     */
    private Boolean judgementDate(TApply apply) {
        long star = apply.getStartDate().getTime();
        long end = apply.getEndDate().getTime();
        List<TApply> byCode = applyService.findByCode(apply.getCode());
        //遍历同一会议室未过期的apply对象
        for (TApply result : byCode) {
            //如果传入的id和遍历的id相同，是更新操作，直接进行下一次循环
            String s1 = result.getId();
            String s2 = apply.getId();
            if (StringUtils.equals(s1, s2)) {
                continue;
            }
            //如果传入开始时间小于遍历的结束时间 并且 传入结束时间大于遍历的开始时间
            if (star < result.getEndDate().getTime() && end > result.getStartDate().getTime()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 设置string参与人员
     *
     * @param apply
     * @return
     */
    private void judgementUsers(TApply apply) {
        if (apply.getUsers() != null) {
            //将List对象封装到string中，用逗号分隔
            apply.setParticipant(String.join(",", apply.getUsers()));
        }
    }

    /**
     * 校验参与人员是否冲突
     *
     * @param model
     * @return
     */
    private String judgementParticipant(TApply model) {
        Apply apply = new Apply();
        apply.setStartDate(model.getStartDate());
        apply.setEndDate(model.getEndDate());
        List<TApply> byDate = applyService.findByDate(apply);

        String s = "";
        String id1 = model.getId();
        outer:
        //遍历修改的参与人员
        for (String user : model.getUsers()) {
            //遍历获取到有时间有冲突，但会议室不同的申请列表
            for (TApply tApply : byDate) {
                String id2 = tApply.getId();
                //如果修改的id和查询id相同，进行下一次循环
                if (StringUtils.equals(id1, id2)) {
                    continue;
                } else {
                    //id不同，遍历参与人员，比较
                    String[] split = tApply.getParticipant().split(",");
                    for (String s1 : split) {
                        if (StringUtils.equals(s1, user)) {
                            s = s + user + "，";
                            continue outer;
                        }

                    }
                }
            }
        }

        return s;
    }
}