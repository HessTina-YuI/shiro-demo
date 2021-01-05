package yui.hesstina.shirodemo.util;

import org.springframework.context.ApplicationContext;

/**
 * @package yui.hesstina.shirodemo.util
 * @class SpringUitls
 * @description
 * @author YuI
 * @create 2021/1/6 2:02 
 * @since
 **/
public class SpringUitls {

    private static ApplicationContext context;

    public static void setApplicationContext(ApplicationContext context) {
        SpringUitls.context = context;
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

}
