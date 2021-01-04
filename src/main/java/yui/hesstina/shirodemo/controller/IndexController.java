package yui.hesstina.shirodemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String login() {
        return "login";
    }

    @GetMapping("/in")
    public String in() {
        return "in";
    }

}
