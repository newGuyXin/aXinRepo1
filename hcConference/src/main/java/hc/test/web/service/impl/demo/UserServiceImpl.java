package hc.test.web.service.impl.demo;

import hc.test.web.dao.demo.IUserDao;
import hc.test.web.entity.demo.User;
import hc.test.web.service.demo.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Boolean findPasswordByUserName(String username, String password) {
        User user = userDao.findByUserName(username);

        if (user == null) {
            return false;
        }
        if (!StringUtils.equals(user.getPassword(), password)) {
            return false;
        }
        return true;
    }

    @Override
    public User findUser(String username) {
        return userDao.findByUserName(username);
    }
}
