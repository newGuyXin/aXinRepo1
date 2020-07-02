package hc.test.web.service.demo;

import com.github.pagehelper.PageInfo;
import hc.test.web.entity.demo.TApply;
import hc.test.web.model.demo.Apply;

import java.util.List;
import java.util.Map;


public interface IApplyService {

    /**
     * 添加会议室申请
     *
     * @param apply
     */
    void saveApply(TApply apply);

    /**
     * 根据申请id撤销申请
     *
     * @param id
     */
    void deleteApply(String id);

    /**
     * 更新申请列表
     *
     * @param apply
     */
    void updateApply(TApply apply);

    /**
     * 根据传入的code查询所有apply对象
     *
     * @param code
     * @return
     */
    List<TApply> findByCode(Integer code);

    /**
     * 查询所有
     *
     * @return
     */
    List<TApply> findAllApply();

    /**
     * 根据id查询单条申请数据
     *
     * @param id
     * @return
     */
    TApply findById(String id);

    /**
     * 根据传入的字段进行模糊查询
     *
     * @param model
     * @return
     */
    PageInfo<TApply> findApply(Apply model);

    /**
     * 根据传入的时间段进行查询
     *
     * @param apply
     * @return
     */
    List<TApply> findByDate(Apply apply);

    /**
     *
     * @return
     */
    List<TApply> findByDate();

    List<Apply> findName();

}
