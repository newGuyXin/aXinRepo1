package hc.test.web.dao.demo;

import hc.test.web.entity.demo.TConference;
import hc.test.web.model.demo.Conference;
import org.apache.ibatis.annotations.*;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

public interface IConferenceDao {

    /**
     * 新增会议室
     *
     * @param conference
     */
    @Insert("insert into t_conference (id,name,code,remark) values (#{id},#{name},#{code},#{remark})")
    void saveConference(TConference conference);

    /**
     * 根据id删除单条会议室数据
     *
     * @param id
     */
    @Delete("delete from t_conference where id = #{id}")
    void deleteConference(String id);

    /**
     * 更新会议室信息
     *
     * @param conference
     */
    void updateConference(TConference conference);

    /**
     * 根据id查询会议室信息
     *
     * @param id
     * @return
     */
    @Select("select * from t_conference where id = #{id}")
    TConference findById(String id);

    /**
     * 根据传入的查询字段对会议室进行查询
     *
     * @param conference
     * @return
     */
    List<TConference> findByConference(TConference conference);


    /**
     * 根据传入的查询字段模糊查询会议室
     *
     * @param conference
     * @return
     */
    List<TConference> pageAll(Conference conference);

    /**
     * 根据传入的门排号查询会议室名称
     *
     * @param name
     * @return
     */
    @Select("select code from t_conference where name = #{name} ")
    Integer findCodeByName(String name);

    /**
     * 根据传入的的会议编号或名称查询数量
     *
     * @param code
     * @param name
     * @return
     */
    @Select("select count(1) from t_conference where code=#{code} or name = #{name}")
    @ResultType(Integer.class)
    Integer checkConferenceExist(@Param("code") Integer code, @Param("name") String name);

    /**
     * 根据传入编号和名称查询所有
     *
     * @param code
     * @param name
     * @return
     */
    @Select("select * from t_conference where code=#{code} or name = #{name}")
    List<TConference> findByNameOrCode(@Param("code") Integer code, @Param("name") String name);
}
