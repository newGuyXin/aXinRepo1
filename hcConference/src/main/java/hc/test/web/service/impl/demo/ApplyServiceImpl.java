package hc.test.web.service.impl.demo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hc.test.web.dao.demo.IApplyDao;
import hc.test.web.entity.demo.TApply;
import hc.test.web.model.demo.Apply;
import hc.test.web.service.demo.IApplyService;
import hc.test.web.util.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ApplyServiceImpl implements IApplyService {
    @Autowired
    private IApplyDao applyDao;


    @Override
    public void saveApply(TApply apply) {
        apply.setId(UUIDUtil.get32UUID());
        apply.setState(true);
        applyDao.saveApply(apply);
    }

    @Override
    public void deleteApply(String id) {
        applyDao.deleteApply(id);
    }

    @Override
    public void updateApply(TApply apply) {
        applyDao.updateApply(apply);
    }

    @Override
    public List<TApply> findByCode(Integer code) {
        return applyDao.findByCode(code);
    }

    @Override
    public List<TApply> findAllApply() {
        return applyDao.findAllApply();
    }

    @Override
    public TApply findById(String id) {
        return applyDao.findById(id);
    }

    @Override
    public PageInfo<TApply> findApply(Apply model) {
        PageHelper.startPage(model.getPageNum(), model.getPageSize());

        if (StringUtils.isNotEmpty(model.getName())) {
            model.setName("%" + model.getName() + "%");
        }
        if (StringUtils.isNotEmpty(model.getUser())) {
            model.setUser("%" + model.getUser() + "%");
        }
        if (StringUtils.isNotEmpty(model.getTitle())) {
            model.setTitle("%" + model.getTitle() + "%");
        }


        List<TApply> list = applyDao.findApply(model);
        PageInfo<TApply> result = new PageInfo<>(list);
        return result;


    }

    @Override
    public List<TApply> findByDate(Apply apply) {

        return applyDao.findApply(apply);

    }

    @Override
    public List<TApply> findByDate() {
        long sta = System.currentTimeMillis();
        List<TApply> byDate = applyDao.findByDate();
        for (TApply apply : byDate) {
            if (apply.getEndDate().getTime() < sta) {
                applyDao.updateState(apply.getId());
            }

        }
        return applyDao.findByDate();
    }

    @Override
    public List<Apply> findName() {
        return applyDao.findName();
    }

}
