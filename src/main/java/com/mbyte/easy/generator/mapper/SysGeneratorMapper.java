package com.mbyte.easy.generator.mapper;

import com.mbyte.easy.generator.entity.SysGenerator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @since 2019-04-15
 */
public interface SysGeneratorMapper extends BaseMapper<SysGenerator> {

    /**
     * @Date 11:40 2019/4/16
     * @return java.util.List<java.lang.String>
     **/
    List<String> getTables();
}
