package hc.test.web.service.demo;

import com.github.pagehelper.PageInfo;
import hc.test.web.entity.demo.TConference;
import hc.test.web.model.demo.Conference;

import java.util.List;
import java.util.Map;

public interface IConferenceService {

    /**
     * 新增会议室
     *
     * @param conference
     */
    void saveConference(TConference conference);

    /**
     * 根据id删除单条会议室数据
     *
     * @param id
     */
    void deleteConference(String id);

    /**
     * 更新会议室信息
     *
     * @param conference
     */
    boolean updateConference(TConference conference);

    /**
     * 根据传入的查询字段,分页，模糊查询会议室
     * @param conference
     * @return
     */
    PageInfo<TConference> pageAll(Conference conference);

    /**
     * 根据传入的会议室名称查询编号
     * @param name
     * @return
     */
    Integer findCodeByName(String name);

    /**
     * 根据id查询单条数据
     * @param id
     * @return
     */
    TConference findById(String id);

    Integer checkConferenceExist(Integer code, String name);
}



