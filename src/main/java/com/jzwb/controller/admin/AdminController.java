package com.jzwb.controller.admin;

import com.jzwb.common.Pageable;
import com.jzwb.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Controller - 管理员
 */
@Controller("adminAdminController")
@RequestMapping("/admin/admin")
public class AdminController {

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    /**
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model) {
        model.addAttribute("page", adminService.findPage(pageable));
        return "/admin/admin/list";
    }
}
