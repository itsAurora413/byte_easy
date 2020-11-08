package com.mbyte.easy.h5.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.forum.entity.Forum;
import com.mbyte.easy.forum.service.IForumService;
import com.mbyte.easy.ref.entity.ForumUser;
import com.mbyte.easy.ref.service.IForumUserService;
import com.mbyte.easy.util.PageInfo;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* <p>
* 前端控制器
* </p>
* @author lp
* @since 2019-03-11
*/
@Controller
@RequestMapping("/")
public class H5IndexController extends BaseController  {

    @Autowired
    private IForumService forumService;
    @Autowired
    private IForumUserService forumUserService;

    private String prefix = "H5/";
//    @RequestMapping("/toH5")
//    @ResponseBody
//    public AjaxResult toIndex(){
////        forumService.list();
////        forumService.save(new Forum());
////        forumService.getById();
////        forumService.removeById();
////        forumService.updateById();
//
//        Forum title = forumService.getByTitile("测试");
//
//
//
//        return this.success(title);
//    }
//    @RequestMapping("/like")
//    @ResponseBody
//    public AjaxResult like(@RequestParam("forumId")Integer forumId,@RequestParam("userId")Integer userId,@RequestParam("flag")Integer flag){
//        boolean like = forumUserService.like(forumId, userId, flag);
////        forumUserService.listNumber(forumId);
//        return this.success();
//    }


    @RequestMapping
    public String index(Model model) {
        List<Forum> list = forumService.list();
        model.addAttribute("forumList", list);
        return "/H5/index";
    }

    @RequestMapping("/flag")
    @ResponseBody
    public String flag(Model model){

        List<ForumUser> list = forumUserService.list();
        model.addAttribute("forumUserList",list);

        return "/H5/index";
    }


}
