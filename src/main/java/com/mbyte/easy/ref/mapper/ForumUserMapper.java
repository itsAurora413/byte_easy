package com.mbyte.easy.ref.mapper;

import com.mbyte.easy.ref.entity.ForumUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lp
 * @since 2020-11-03
 */
public interface ForumUserMapper extends BaseMapper<ForumUser> {

    ForumUser selectByMyIds(@Param("forumId") Integer forumId, @Param("userId") Integer userId);

    ForumUser selectByForumId(@Param("forumId") Integer forumId);

//    @Select("select * from t_login where uid = #{id}")
//    @Results({
//            @Result(property="ForumEntity",column="fid",one=@One(select="com.brucewee.demo.Mapper.InfoMapper.getInfoEntityByInfoId"))
//    })
//    public User getUserWithInfo(int userId);
}
