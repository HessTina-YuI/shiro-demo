package yui.hesstina.shirodemo.controller;

import java.util.Collections;
import java.util.HashSet;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yui.hesstina.shirodemo.constant.UserInfo;
import yui.hesstina.shirodemo.pojo.User;
import yui.hesstina.shirodemo.util.JwtUtils;

/**
 * @package yui.hesstina.shirodemo.controller
 * @class IndexController
 * @description
 * @author YuI
 * @create 2021/1/5 1:52 
 * @since
 **/
@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/login")
    public String login(User user) {
        // 替换成 JWT
        String token = JwtUtils.generateToken(user.getName());
        UserInfo.USER_MAP.put(token, user);
        UserInfo.PERMISSION_MAP.put(user.getName(), new HashSet<>(Collections.singleton("sys:test:*")));

        return token;
    }

    @RequiresPermissions("sys:test:admin")
    @GetMapping("/testAdmin")
    public String testAdmin() {
        return "test";
    }


    @RequiresPermissions("sys:test:user")
    @GetMapping("/testUser")
    public String testUser() {
        return "test";
    }

    @RequiresPermissions("sys:audience")
    @GetMapping("/audience")
    public String testAudience() {
        return "test";
    }
}
