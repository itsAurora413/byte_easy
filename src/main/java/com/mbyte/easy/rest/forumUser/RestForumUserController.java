package com.mbyte.easy.rest.forumUser;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.ref.entity.ForumUser;
import com.mbyte.easy.ref.service.IForumUserService;
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
@RequestMapping("rest/forumUser")
public class RestForumUserController extends BaseController  {

    @Autowired
    private IForumUserService forumUserService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param forumUser
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, ForumUser forumUser) {
        Page<ForumUser> page = new Page<ForumUser>(pageNo, pageSize);
        QueryWrapper<ForumUser> queryWrapper = new QueryWrapper<ForumUser>();

        if(forumUser.getForumId() != null  && !"".equals(forumUser.getForumId() + "")) {
            queryWrapper = queryWrapper.like("forum_id",forumUser.getForumId());
         }


        if(forumUser.getUserId() != null  && !"".equals(forumUser.getUserId() + "")) {
            queryWrapper = queryWrapper.like("user_id",forumUser.getUserId());
         }


        if(forumUser.getFlag() != null  && !"".equals(forumUser.getFlag() + "")) {
            queryWrapper = queryWrapper.like("flag",forumUser.getFlag());
         }

        IPage<ForumUser> pageInfo = forumUserService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  forumUser);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }


    /**
    * 添加
    * @param forumUser
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(ForumUser forumUser){
        return toAjax(forumUserService.save(forumUser));
    }

    /**
    * 添加
    * @param forumUser
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(ForumUser forumUser){
        return toAjax(forumUserService.updateById(forumUser));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(forumUserService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(forumUserService.removeByIds(ids));
    }

}

