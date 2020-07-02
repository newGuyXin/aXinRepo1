package hc.test.web.dao.demo;

import hc.test.web.entity.demo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有用户
     */
    @Select("select * from t_user_info")
    List<User> findAll();

    @Select("select * from t_user_info where username =#{username}")
    User findByUserName(String username);
}
