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

    /**
     * 登录的时候，存入缓存？
     * key: 生成的 token
     * value: 用户信息
     */
    public static final Map<String, User> USER_MAP = new HashMap<>();

    /**
     * 登录后，从数据库中获得权限，存入缓存？
     * key: 用户名
     * value: 权限（可以有多个）
     */
    public static final Map<String, Set<String>> ROLES_MAP = new HashMap<>();

}
