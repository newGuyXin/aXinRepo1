package hc.test.web.service.impl.demo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.bind.v2.model.core.ID;
import hc.test.web.dao.demo.IConferenceDao;
import hc.test.web.entity.demo.TConference;
import hc.test.web.model.base.AjaxJson;
import hc.test.web.model.demo.Conference;

import hc.test.web.service.demo.IConferenceService;
import hc.test.web.util.UUIDUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


@Service
@Transactional
public class ConferenceServiceImpl implements IConferenceService {

    @Autowired
    private IConferenceDao conferenceDao;

    @Override
    public Integer checkConferenceExist(Integer code, String name) {
        return conferenceDao.checkConferenceExist(code, name);
    }

    @Override
    public void saveConference(TConference conference) {
        conference.setId(UUIDUtil.get32UUID());
        conferenceDao.saveConference(conference);
    }

    @Override
    public void deleteConference(String id) {
        conferenceDao.deleteConference(id);
    }

    @Override
    public boolean updateConference(TConference conference) {

        //判断更新输入的会议室编号是否存在
        if (StringUtils.isEmpty(conference.getName())) {
            return false;
        }
        if (conference.getCode() == null) {
            return false;
        }
        //校验更新编号是否是数字
//        Pattern pattern = Pattern.compile("[0-9]*");
//        if (!pattern.matcher(conference.getCode().toString()).matches()){
//            return false;
//        }

        List<TConference> byNameOrCode = conferenceDao.findByNameOrCode(conference.getCode(), conference.getName());

        if (byNameOrCode.size() > 1) {
            return false;
        }
        if (byNameOrCode.size() == 1) {

            if (StringUtils.equals(byNameOrCode.get(0).getId(), conference.getId())) {
                conferenceDao.updateConference(conference);
                return true;
            }
            return false;
        }

        conferenceDao.updateConference(conference);
        return true;
    }

    @Override
    public PageInfo<TConference> pageAll(Conference conference) {

        PageHelper.startPage(conference.getPageNum(), conference.getPageSize());

        if (StringUtils.isNotEmpty(conference.getName())) {
            conference.setName( "%" + conference.getName() + "%");
        }
        List<TConference> list = conferenceDao.pageAll(conference);
        PageInfo<TConference> result = new PageInfo<>(list);
        return result;
    }

    @Override
    public Integer findCodeByName(String name) {
        return conferenceDao.findCodeByName(name);
    }

    @Override
    public TConference findById(String id) {
        return conferenceDao.findById(id);
    }


}
