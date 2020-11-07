package com.mbyte.easy.ref.service.impl;

import com.mbyte.easy.ref.entity.ForumUser;
import com.mbyte.easy.ref.mapper.ForumUserMapper;
import com.mbyte.easy.ref.service.IForumUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lp
 * @since 2020-11-03
 */
@Service
public class ForumUserServiceImpl extends ServiceImpl<ForumUserMapper, ForumUser> implements IForumUserService {

    @Override
    public boolean like(Integer forumId, Integer userId, Integer flag) {
        ForumUser forumUser = getBaseMapper().selectByMyIds(forumId, userId);
        if(forumUser == null){
            forumUser = new ForumUser(forumId,userId,flag);
            return save(forumUser);
        }else {
            if(flag == forumUser.getFlag()){
                return removeById(forumId);
            }else {
               forumUser.setFlag(flag);
               return updateById(forumUser);
            }
        }
    }
}
