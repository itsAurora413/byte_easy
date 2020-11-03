package com.mbyte.easy.admin.entity;

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
 * @since 2019-12-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_person")
public class Person extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String content;


}
