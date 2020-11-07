package com.mbyte.easy.forum.service.impl;

import com.mbyte.easy.forum.entity.Forum;
import com.mbyte.easy.forum.mapper.ForumMapper;
import com.mbyte.easy.forum.service.IForumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lp
 * @since 2020-10-31
 */
@Service
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum> implements IForumService {

    @Override
    public Forum getByTitile(String test) {
        Forum forum = getBaseMapper().selectByTitile(test);
        forum.setForum(forum.getForum().substring(0,10));
        return forum;
    }
}
