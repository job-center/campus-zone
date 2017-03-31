package com.jobcenter.campus.web.controller.authority.role;

import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.authority.role.SysRole;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.service.authority.role.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
