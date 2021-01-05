package yui.hesstina.shirodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import yui.hesstina.shirodemo.util.SpringUitls;

@SpringBootApplication
public class ShiroDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ShiroDemoApplication.class, args);

        SpringUitls.setApplicationContext(context);
    }

}
