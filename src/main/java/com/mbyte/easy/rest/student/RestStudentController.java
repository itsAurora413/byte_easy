package com.mbyte.easy.rest.student;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.student.entity.Student;
import com.mbyte.easy.student.service.IStudentService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
* <p>
* 前端控制器
* </p>
* @author lp
* @since 2019-03-11
*/
@RestController
@RequestMapping("rest/student")
public class RestStudentController extends BaseController  {

    @Autowired
    private IStudentService studentService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param student
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Student student) {
        Page<Student> page = new Page<Student>(pageNo, pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<Student>();

        if(student.getUserName() != null  && !"".equals(student.getUserName() + "")) {
            queryWrapper = queryWrapper.like("user_name",student.getUserName());
         }


        if(student.getSex() != null  && !"".equals(student.getSex() + "")) {
            queryWrapper = queryWrapper.like("sex",student.getSex());
         }


        if(student.getAge() != null  && !"".equals(student.getAge() + "")) {
            queryWrapper = queryWrapper.like("age",student.getAge());
         }


        if(student.getStudentText() != null  && !"".equals(student.getStudentText() + "")) {
            queryWrapper = queryWrapper.like("student_text",student.getStudentText());
         }

        IPage<Student> pageInfo = studentService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  student);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }


    /**
    * 添加
    * @param student
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(Student student){
        return toAjax(studentService.save(student));
    }

    /**
    * 添加
    * @param student
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(Student student){
        return toAjax(studentService.updateById(student));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(studentService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(studentService.removeByIds(ids));
    }

}

