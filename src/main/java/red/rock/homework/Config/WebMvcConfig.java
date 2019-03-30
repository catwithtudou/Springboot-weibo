package red.rock.homework.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author 郑煜
 * @Title: WebMvcConfig
 * @ProjectName homework_2
 * @Description: 将异常处理注册为bean
 * @date 2019/3/30 下午 08:46
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public AuthExceptionResolver authExceptionResolver(){
        return new AuthExceptionResolver();
    }
}
