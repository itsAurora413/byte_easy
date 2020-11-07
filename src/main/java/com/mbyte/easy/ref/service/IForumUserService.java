package com.mbyte.easy.ref.service;

import com.mbyte.easy.ref.entity.ForumUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lp
 * @since 2020-11-03
 */
public interface IForumUserService extends IService<ForumUser> {

    boolean like(Integer forumId, Integer userId, Integer flag);
}
