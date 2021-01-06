package yui.hesstina.shirodemo.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import yui.hesstina.shirodemo.pojo.User;

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
     * 生成的 token
     *
     * key: 生成的 token
     * value: 用户信息
     */
    public static final Map<String, User> USER_MAP = new HashMap<>();

    /**
     * 角色
     *
     * key: 用户名
     * value: 角色
     */
    public static final Map<String, Set<String>> ROLES_MAP = new HashMap<>();

    /**
     * 权限控制
     *
     * key: 用户名
     * value: 权限
     */
    public static final Map<String, Set<String>> PERMISSION_MAP = new HashMap<>();
}
