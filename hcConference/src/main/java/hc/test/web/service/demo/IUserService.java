package hc.test.web.service.demo;

import hc.test.web.entity.demo.User;

import java.util.List;

public interface IUserService {

    /**
     * 查询所有用户
     */
    List<User> findAll();

    Boolean findPasswordByUserName(String username, String password);

    User findUser(String username);
}
