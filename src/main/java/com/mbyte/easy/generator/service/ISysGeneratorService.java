package com.mbyte.easy.generator.service;

import com.mbyte.easy.generator.entity.SysGenerator;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @since 2019-04-15
 */
public interface ISysGeneratorService extends IService<SysGenerator> {

    /**
     * @Date 11:21 2019/4/16
     * @return java.util.List<java.lang.String>
     **/
    List<String> getTables();

}
