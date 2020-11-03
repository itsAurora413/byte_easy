package com.mbyte.easy.student.service.impl;

import com.mbyte.easy.student.entity.Student;
import com.mbyte.easy.student.mapper.StudentMapper;
import com.mbyte.easy.student.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lp
 * @since 2020-10-28
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
