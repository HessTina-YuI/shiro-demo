package yui.hesstina.shirodemo.controller;

import java.util.Collections;
import java.util.HashSet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yui.hesstina.shirodemo.constant.UserInfo;
import yui.hesstina.shirodemo.pojo.User;

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
        String token = user.getName() + user.getPassword();
        UserInfo.USER_MAP.put(token, user);
        UserInfo.ROLES_MAP.put(user.getName(), new HashSet<>(Collections.singletonList("admin")));

        return "login";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
