package com.mbyte.easy.ref.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.ref.entity.ForumUser;
import com.mbyte.easy.ref.service.IForumUserService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ObjectUtils;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* <p>
* 前端控制器
* </p>
* @author lp
* @since 2019-03-11
*/
@Controller
@RequestMapping("/ref/forumUser")
public class ForumUserController extends BaseController  {

    private String prefix = "ref/forumUser/";

    @Autowired
    private IForumUserService forumUserService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param forumUser
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, ForumUser forumUser) {
        Page<ForumUser> page = new Page<ForumUser>(pageNo, pageSize);
        QueryWrapper<ForumUser> queryWrapper = new QueryWrapper<ForumUser>();
        if(!ObjectUtils.isEmpty(forumUser.getForumId())) {
            queryWrapper = queryWrapper.like("forum_id",forumUser.getForumId());
         }
        if(!ObjectUtils.isEmpty(forumUser.getUserId())) {
            queryWrapper = queryWrapper.like("user_id",forumUser.getUserId());
         }
        if(!ObjectUtils.isEmpty(forumUser.getFlag())) {
            queryWrapper = queryWrapper.like("flag",forumUser.getFlag());
         }
        IPage<ForumUser> pageInfo = forumUserService.page(page, queryWrapper);
        model.addAttribute("searchInfo", forumUser);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"list";
    }

    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("addBefore")
    public String addBefore(){
        return prefix+"add";
    }
    /**
    * 添加
    * @param forumUser
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(ForumUser forumUser){
        return toAjax(forumUserService.save(forumUser));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("forumUser",forumUserService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param forumUser
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(ForumUser forumUser){
        return toAjax(forumUserService.updateById(forumUser));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(forumUserService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(forumUserService.removeByIds(ids));
    }

}

