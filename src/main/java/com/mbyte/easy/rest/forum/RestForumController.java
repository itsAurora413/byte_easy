package com.mbyte.easy.rest.forum;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.forum.entity.Forum;
import com.mbyte.easy.forum.service.IForumService;
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
@RequestMapping("rest/forum")
public class RestForumController extends BaseController  {

    @Autowired
    private IForumService forumService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param forum
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Forum forum) {
        Page<Forum> page = new Page<Forum>(pageNo, pageSize);
        QueryWrapper<Forum> queryWrapper = new QueryWrapper<Forum>();

        if(forum.getTitle() != null  && !"".equals(forum.getTitle() + "")) {
            queryWrapper = queryWrapper.like("title",forum.getTitle());
         }


        if(forum.getForum() != null  && !"".equals(forum.getForum() + "")) {
            queryWrapper = queryWrapper.like("forum",forum.getForum());
         }

        IPage<Forum> pageInfo = forumService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  forum);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }


    /**
    * 添加
    * @param forum
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(Forum forum){
        return toAjax(forumService.save(forum));
    }

    /**
    * 添加
    * @param forum
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(Forum forum){
        return toAjax(forumService.updateById(forum));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(forumService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(forumService.removeByIds(ids));
    }

}

