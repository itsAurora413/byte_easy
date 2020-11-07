package com.mbyte.easy.ref.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mbyte.easy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lp
 * @since 2020-11-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ref_forum_user")
public class ForumUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 博客id
     */
    private Integer forumId;

    /**
     * 用户名
     */
    private Integer userId;

    private Integer flag;


    public ForumUser(Integer forumId, Integer userId, Integer flag) {
        this.forumId =forumId;
        this.flag = flag;
        this.userId = userId;
    }
}
