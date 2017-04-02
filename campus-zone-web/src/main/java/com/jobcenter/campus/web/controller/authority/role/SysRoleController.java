package com.jobcenter.campus.web.controller.authority.role;

import com.jobcenter.campus.common.common.ResultEnum;
import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.authority.role.SysRole;
import com.jobcenter.campus.entity.authority.user.SysUserRole;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.service.authority.role.SysRoleService;
import com.jobcenter.campus.service.authority.role.SysUserRoleService;
import com.jobcenter.campus.web.domin.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "v1/sysuser/role", method = RequestMethod.POST)
    @ResponseBody
    public APIResponse createSysUserRole(SysUserRole sysUserRole) {
        Assert.notNull(sysUserRole, "添加的角色信息不能为空");
        boolean result = sysUserRoleService.createSysUserRole(sysUserRole);
        APIResponse apiResponse = new APIResponse(ResultEnum.parseResultEnum(result));
        return apiResponse;
    }
}
