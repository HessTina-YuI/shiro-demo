package yui.hesstina.shirodemo.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.Data;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

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
     * 根据 token 获得签发人的信息
     *
     * @param token token
     * @return 签发人名字 {@link String}
     */
    public static String getIssuer(String token) {
        DecodedJWT jwt = verifierToken(token);

        return jwt != null ? jwt.getIssuer() : "";
    }

    /**
     * 根据 token 获得签发时间
     *
     * @param token token
     * @return 签发时间 {@link LocalDate}
     */
    public static LocalDate getIssuerDate(String token) {
        DecodedJWT jwt = verifierToken(token);
        if (jwt != null) {
            Date date = jwt.getIssuedAt();
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        return null;
    }

    /**
     * 生成 jwt token
     * @return token
     */
    public static String generateToken(String userName) {
        try {
            return JWT.create()
                    .withIssuer(userName)
                    .withIssuedAt(new Date())
                    .sign(generateAlgorithm());
        } catch (IllegalArgumentException | JWTCreationException e) {
            log.error("JWTToken 生成失败:", e);
        }

        return StringUtils.EMPTY_STRING;
    }

    private static DecodedJWT verifierToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(generateAlgorithm()).build();
            jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            log.info("jwt 解码失败, token: {}", token, e);
        }

        return jwt;
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
