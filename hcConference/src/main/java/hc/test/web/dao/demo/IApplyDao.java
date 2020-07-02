package hc.test.web.dao.demo;

import hc.test.web.entity.demo.TApply;
import hc.test.web.model.demo.Apply;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IApplyDao {

    /**
     * 添加会议室申请
     *
     * @param apply
     */
    @Insert("insert into t_apply (id,title,name,code,user,startDate,endDate,participant,remark,state) values " +
            "(#{id},#{title},#{name},#{code},#{user},#{startDate},#{endDate},#{participant},#{remark},#{state})")
    void saveApply(TApply apply);

    /**
     * 根据申请id撤销申请
     *
     * @param id
     */
    @Delete("delete from t_apply where id = #{id}")
    void deleteApply(String id);

    /**
     * 更新申请列表
     *
     * @param apply
     */
    void updateApply(TApply apply);

    /**
     * 根据传入的code查询所有未过期的apply对象
     *
     * @param code
     * @return
     */
    @Select("select * from t_apply where code = #{code} and state = 1")
    List<TApply> findByCode(Integer code);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from t_apply")
    List<TApply> findAllApply();

    /**
     * 根据传入的字段进行模糊查询
     *
     * @param model
     * @return
     */
    List<TApply> findApply(Apply model);

    /**
     * 根据id查询单条信息
     *
     * @param id
     * @return
     */
    @Select("select * from t_apply where id = #{id}")
    TApply findById(String id);

    /**
     * 更改会议室状态
     *
     * @return
     */
    @Select("select * from t_apply where state = 1")
    List<TApply> findByDate();

    /**
     * 添加会议申请时，查询所有会议室的名称编号
     *
     * @return
     */
    @Select("select * from t_conference order by code asc")
    List<Apply> findName();

    @Update("update t_apply set state = 0 where id = #{id}")
    void updateState(String id);
}
