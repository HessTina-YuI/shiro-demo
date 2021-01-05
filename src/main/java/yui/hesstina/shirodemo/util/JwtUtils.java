package yui.hesstina.shirodemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.Data;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * @package yui.hesstina.shirodemo.util
 * @class JwtUtils
 * @description
 * @author YuI
 * @create 2021/1/6 1:13 
 * @since
 **/
@UtilityClass
@Slf4j
public class JwtUtils {

    /**
     * 校验token是否正确
     * @param token Token
     * @return boolean 是否正确
     */
    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(generateAlgorithm())
                    .withIssuer("auth0")
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.error("JWTToken 解密失败:", e);
        }

        return false;
    }

    /**
     * 生成 jwt token
     * @return token
     */
    public static String generateToken() {
        try {
            return JWT.create()
                    .withIssuer("auth0")
                    .withIssuedAt(new Date())
                    .sign(generateAlgorithm());
        } catch (IllegalArgumentException | JWTCreationException e) {
            log.error("JWTToken 生成失败:", e);
        }

        return StringUtils.EMPTY_STRING;
    }

    private static Algorithm generateAlgorithm() {
        JwtProperties properties = SpringUitls.getBean(JwtProperties.class);
        if (ObjectUtils.isEmpty(properties)) {
            throw new RuntimeException("jwtHmac256Key 未加载，请检查配置文件");
        }
        return Algorithm.HMAC256(properties.jwtHmac256Key);
    }

    @Data
    @Component
    public static class JwtProperties {
        @Value("${jwt.hmac256.key}")
        private String jwtHmac256Key;
    }
}
