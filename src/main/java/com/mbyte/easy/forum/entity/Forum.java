package com.mbyte.easy.forum.entity;

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
 * @since 2020-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_forum")
public class Forum extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String forum;


}
