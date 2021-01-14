package com.haocp.declare.web.controller.system;

import com.haocp.declare.core.domain.AjaxResult;
import com.haocp.declare.constant.*;
import com.haocp.declare.utils.ServletUtils;
import com.haocp.declare.web.model.system.SysMenu;
import com.haocp.declare.web.model.system.SysUser;
import com.haocp.declare.web.model.system.user.LoginBody;
import com.haocp.declare.web.model.system.user.LoginUser;
import com.haocp.declare.web.service.system.ISysMenuService;
import com.haocp.declare.web.service.system.SysLoginService;
import com.haocp.declare.web.service.system.SysPermissionService;
import com.haocp.declare.web.service.system.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @Auther: HPGT
 * @Date: 2020/12/28 09:20
 * @Description:
 */
@RestController
public class SysLoginController {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private ISysMenuService menuService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }

    /**
     * 测试
     *
     * @return 路由信息
     */
    @GetMapping("getHello")
    public AjaxResult getHello()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return AjaxResult.success(loginUser.getUsername()+"hhhhh成功！");
    }
}
