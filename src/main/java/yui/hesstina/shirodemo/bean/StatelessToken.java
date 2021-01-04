package yui.hesstina.shirodemo.bean;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * 自定义 AuthenticationToken
 *
 * @package yui.hesstina.shirodemo.bean
 * @class StatelessToken
 * @author YuI
 * @create 2021/1/5 2:08 
 * @since
 **/
@Data
public class StatelessToken implements AuthenticationToken {

    private String userName;
    private String token;

    @Override
    public Object getPrincipal() {
        return this.userName;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
