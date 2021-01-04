package yui.hesstina.shirodemo.constant;

import yui.hesstina.shirodemo.pojo.User;

import java.util.*;

/**
 * 自测时写死，实际会用缓存替代
 *
 * @package yui.hesstina.shirodemo.constant
 * @class UserInfo
 * @author YuI
 * @create 2021/1/5 2:32 
 * @since
 **/
public class UserInfo {

    public static final Map<String, User> USER_MAP;

    public static final Map<String, Set<String>> ROLES;

    static {
        USER_MAP = new HashMap<>();
        USER_MAP.put("login", new User("yui", "123465"));

        ROLES = new HashMap<>();
        ROLES.put("yui", new HashSet<>(Collections.singletonList("admin")));
    }

}
