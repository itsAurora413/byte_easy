package com.mbyte.easy.forum.mapper;

import com.mbyte.easy.forum.entity.Forum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lp
 * @since 2020-10-31
 */
public interface ForumMapper extends BaseMapper<Forum> {

    Forum selectByTitile(@Param("test") String test);
}
