package com.mbyte.easy.security.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.security.entity.SysResource;
import com.mbyte.easy.security.entity.SysRole;
import com.mbyte.easy.security.entity.SysRoleResources;
import com.mbyte.easy.security.mapper.SysResourceMapper;
import com.mbyte.easy.security.mapper.SysRoleMapper;
import com.mbyte.easy.security.mapper.SysRoleResourcesMapper;
import com.mbyte.easy.security.mapper.SysUserRolesMapper;
import com.mbyte.easy.security.service.IResourceService;
import com.mbyte.easy.security.service.IRoleService;
import com.mbyte.easy.util.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制类
 * 
 * @author 王震
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private String prefix = "security/";

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IResourceService resourceService;

	@ModelAttribute("resourceList")
	public List<SysResource> resourceList() {
		List<SysResource> resourceList = resourceService.selectByResource(null);
		return resourceList;
	}

	/**
	 * 进入角色管理界面
	 * 
	 * @return
	 */
	@PreAuthorize("hasAuthority('/role')")
	@RequestMapping
	public String index(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		SysRole role = new SysRole(); 
		if (name != null && !"".equals(name.trim())) {
			role.setName(name.trim());
			model.addAttribute("name", name.trim());
		}
		Page<SysRole> page = new Page<SysRole>(pageNo, pageSize);
		IPage<SysRole> pageInfo = roleService.selectByRoleForPage(page, name);

		model.addAttribute("pageInfo", new PageInfo<SysRole>(pageInfo));
		return this.prefix + "admin-role";
	}

	/**
	 * 添加角色
	 * 
	 * @param model
	 * @param role
	 * @return
	 */
	@PreAuthorize("hasAuthority('/role/add-role')")
	@RequestMapping(value = "/add-role")
	public String addRoleBefore(Model model, @ModelAttribute(value = "role") SysRole role) {
		return this.prefix + "admin-role-add";
	}


	/**
	 * @Author 王震
	 * @Description 更新角色和绑定角色权限
	 * @Date 15:57 2019/4/14
	 * @Param [model, role, roleResources]
	 * @return java.lang.String
	 **/
	@ResponseBody
	@RequestMapping(value = "/add-role", params = "save=true")
	public String addRole(Model model, @ModelAttribute(value = "role") SysRole role,
			@RequestParam(required = false) String roleResources) {
        return roleService.addRoleAndResource(role, roleResources) + "";
	}


	/**
	 * @Author 王震
	 * @Description 编辑角色权限
	 * @Date 15:59 2019/4/14
	 * @Param [model, role, id]
	 * @return java.lang.String
	 **/
	@PreAuthorize("hasAuthority('/role/editor-role')")
	@RequestMapping(value = "/editor-role/{id}")
	public String editorRole(Model model, @ModelAttribute(value = "role") SysRole role, @PathVariable("id") Long id) {
		role = roleService.selectByPrimaryKey(id);
		model.addAttribute("role", role);
		// 获取所有的资源
		List<SysResource> resourceList = resourceService.selectByResource(null);
		model.addAttribute("resourceList", resourceList);
		// 获取选中资源
		List<SysResource> resources = resourceService.selectResourceByRoleId(role.getId());
		model.addAttribute("resources", resources);
		return this.prefix + "admin-role-editor";
	}

	/**
	 * @Author 王震
	 * @Description 更新角色信息
	 * @Date 16:13 2019/4/14
	 * @Param [model, role, roleResources]
	 * @return java.lang.String
	 **/
	@ResponseBody
	@RequestMapping(value = "/editor-role", params = "save=true")
	public String editRole(Model model, @ModelAttribute(value = "role") SysRole role,
			@RequestParam(required = false) String roleResources) {

       return this.roleService.updateRoleAndResource(role,roleResources) + "";
	}

	/**
	 * @Author 王震
	 * @Description 删除角色
	 * @Date 16:18 2019/4/14
	 * @Param [ids]
	 * @return java.lang.Integer
	 **/
	@PreAuthorize("hasAuthority('/role/delete')")
	@ResponseBody
	@RequestMapping(value = "/delete", produces = "application/json", consumes = "application/json")
	public Integer delete(@RequestBody Long[] ids) {
		if (ids.length != 0 && ids != null) {
			for (long id : ids) {
				// 删除角色时候，删除资源和用户对应的角色
                this.roleService.removeRole(id);
			}
			return 1;
		}
		return 0;
	}

}
