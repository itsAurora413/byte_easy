package com.mbyte.easy.student.entity;

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
 * @since 2020-10-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_student")
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 个人简介
     */
    private String studentText;


}
