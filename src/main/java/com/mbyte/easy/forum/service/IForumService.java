package com.mbyte.easy.forum.service;

import com.mbyte.easy.forum.entity.Forum;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lp
 * @since 2020-10-31
 */
public interface IForumService extends IService<Forum> {

    Forum getByTitile(String test);
}
