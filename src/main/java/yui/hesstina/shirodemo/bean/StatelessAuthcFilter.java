package yui.hesstina.shirodemo.bean;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.util.ObjectUtils;

/**
 * 拦截器
 *
 * @package yui.hesstina.shirodemo.bean
 * @class StatelessAuthcFilter
 * @author YuI
 * @create 2021/1/4 23:40 
 * @since
 **/
public class StatelessAuthcFilter extends AccessControlFilter {

    private static final String HEAD_TOKEN = "X-Access-Token";

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String tokenValue = httpServletRequest.getHeader(HEAD_TOKEN);

        if (ObjectUtils.isEmpty(tokenValue)) {
            onLoginFail(servletResponse);
            return false;
        }

        StatelessToken token = new StatelessToken();
        token.setToken(tokenValue);

        Subject subject = getSubject(servletRequest, servletResponse);
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            onLoginFail(servletResponse);
            return false;
        }

        // 权限判断是否能使用资源，先这么写，看看能不能改成 aop 或者别的方式
        if (subject.hasRole("admin")) {
            System.out.println("admin");
        }

        return true;
    }

    /**
     * 错误时同意返回的 http 内容，不确定
     *
     * @param response
     */
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        httpResponse.setCharacterEncoding("utf-8");
        wrapCorsResponse(httpResponse);
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("token失效");
    }

    /**
     * 添加 cors 支持
     *
     * @param response
     */
    private void wrapCorsResponse(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Max-Age", "1800");
    }
}
