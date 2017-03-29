package com.jobcenter.campus.web.controller.authority.role;

import com.jobcenter.campus.domin.page.Seed;
import com.jobcenter.campus.entity.SysRole;
import com.jobcenter.campus.model.Page;
import com.jobcenter.campus.service.authority.role.SysRoleService;
import com.jobcenter.campus.web.domin.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public APIResponse<Page<SysRole>> listRoleInfo(Seed seed) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setSuccess(true);
        apiResponse.setCode(APIResponse.SUCCESS);
        apiResponse.setData(sysRoleService.listSysRoleInfos(seed));
        return apiResponse;
    }
}
