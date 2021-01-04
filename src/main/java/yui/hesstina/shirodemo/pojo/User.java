package yui.hesstina.shirodemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息
 *
 * @package yui.hesstina.shirodemo.pojo
 * @class User
 * @author YuI
 * @create 2021/1/5 1:58 
 * @since
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;

    private String password;

}
