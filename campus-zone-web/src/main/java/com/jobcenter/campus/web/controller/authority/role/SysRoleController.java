package com.jobcenter.campus.web.controller.authority.role;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.jobcenter.campus.common.common.ResultEnum;
import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.authority.role.SysRole;
import com.jobcenter.campus.entity.authority.user.SysUserRole;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.service.authority.role.SysRoleService;
import com.jobcenter.campus.service.authority.role.SysUserRoleService;
import com.jobcenter.campus.web.domin.APIResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * <br>==========================
 * <br> company：job-center
 * <br> system：campus-zone
 * <br> User：lzy.clement
 * <br> Date：29/03/2017
 * <br>==========================
 */
@Controller
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @RequestMapping(value = "v1/roleinfos", method = RequestMethod.GET)
    public ModelAndView listRoleInfo(Seed seed, ModelAndView modelAndView) {
        Page<SysRole> sysRolePage = sysRoleService.listSysRoleInfos(seed);
        seed.setResult(sysRolePage.getResult());
        seed.setActionPath("");
        seed.setTotalSize(sysRolePage.getTotal());
        modelAndView.setViewName("/sys/sysRoleList");
        modelAndView.addObject("seed", seed);
        return modelAndView;
    }

    @RequestMapping(value = "/v1/addrole",method = RequestMethod.POST)
    @ResponseBody
    public APIResponse createSysRole(SysRole sysRole){
        Assert.notNull(sysRole,"添加角色不能为空");
        boolean result = sysRoleService.createSysRole(sysRole);
        APIResponse apiResponse = new APIResponse(ResultEnum.parseResultEnum(result));
        return apiResponse;
    }

    @RequestMapping(value = "v1/roleinfos/batchdelete/{roleIds}",method = RequestMethod.DELETE)
    @ResponseBody
    public APIResponse removeSysRole(@PathVariable(value = "roleIds") String roleIds) {
        if (StringUtils.isNotBlank(roleIds)){
            List<String> roleList = Splitter.on(",").splitToList(roleIds);
            if (CollectionUtils.isNotEmpty(roleList)){
                List<SysRole> sysUserRoles = roleList.stream().map(m -> {
                    SysRole sysRole = new SysRole();
                    sysRole.setIsDeleted((byte) 1);
                    sysRole.setId(NumberUtils.toInt(m,0));
                    return  sysRole;
                }).collect(Collectors.toList());
            }
            return new APIResponse(ResultEnum.SUCCESS);
        }else{
            return new APIResponse(ResultEnum.FAIL).setMsg("要删除的角色信息不能为空");
        }
    }

    @RequestMapping(value = "v1/sysuser/role", method = RequestMethod.POST)
    @ResponseBody
    public APIResponse createSysUserRole(SysUserRole sysUserRole) {
        Assert.notNull(sysUserRole, "添加的角色信息不能为空");
        boolean result = sysUserRoleService.createSysUserRole(sysUserRole);
        APIResponse apiResponse = new APIResponse(ResultEnum.parseResultEnum(result));
        return apiResponse;
    }

    @RequestMapping(value = "v1/sysuser/{sysuserId}/role/batch",method = RequestMethod.DELETE)
    @ResponseBody
    public APIResponse removeSysUserRole(HttpServletRequest request,@PathVariable(value = "sysuserId",required = true)int sysuserId,
                                         @RequestParam(value="sysuserRoleIds", required=false) String sysuserRoleIds){
        if (StringUtils.isNotBlank(sysuserRoleIds)){
            List<String> roleIds = Splitter.on(",").splitToList(sysuserRoleIds);
            if (CollectionUtils.isNotEmpty(roleIds)){
                List<SysUserRole> sysUserRoles = Lists.newArrayList();
                roleIds.forEach(x->{
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setIsDeleted((byte) 1);
                    sysUserRole.setRoleId(NumberUtils.toInt(x,0));
                    sysUserRole.setUserId(sysuserId);
                    sysUserRoles.add(sysUserRole);
                });
            }
            return new APIResponse(ResultEnum.SUCCESS);
        }else{
            return new APIResponse(ResultEnum.FAIL).setMsg("要删除的角色信息不能为空");
        }
    }

}
