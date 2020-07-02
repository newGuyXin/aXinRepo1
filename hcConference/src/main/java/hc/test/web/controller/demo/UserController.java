package hc.test.web.controller.demo;


import hc.test.web.controller.base.BaseController;
import hc.test.web.entity.demo.User;
import hc.test.web.model.base.AjaxJson;
import hc.test.web.service.demo.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @GetMapping("/findAll")
    public void findAll(HttpServletResponse response){
        AjaxJson json = new AjaxJson();
        try {
            List<User> all = userService.findAll();
            json.setObj(all);
            json.setSuccess(true);
            json.setMsg("查询用户成功");
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg("系统异常" + e.getMessage());
            e.printStackTrace();
            logger.error("系统异常", e);
        }
        writeJson(json, response);

    }

    /**
     * 登录校验
     *
     * @param response
     */
    @PostMapping("/verify")
    public void verify(@RequestBody User user, HttpServletResponse response) {
        AjaxJson json = new AjaxJson();
        try {

            Boolean flag = userService.findPasswordByUserName(user.getUsername(), user.getPassword());
            if (!flag) {

                json.setSuccess(false);
                json.setMsg("用户名/密码错误");
            } else {
                User user1 = userService.findUser(user.getUsername());
                json.setObj(user1);
                json.setSuccess(true);
                json.setMsg("登录成功!");
            }
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg("系统异常" + e.getMessage());
            System.out.println("系统异常");
            e.printStackTrace();
            logger.error("系统异常", e);
        }
        writeJson(json, response);
    }
}
