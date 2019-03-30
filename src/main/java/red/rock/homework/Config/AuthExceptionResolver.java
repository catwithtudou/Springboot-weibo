package red.rock.homework.Config;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 郑煜
 * @Title: AuthExceptionResolver
 * @ProjectName homework_2
 * @Description: 自定义异常处理类
 * @date 2019/3/30 下午 08:44
 */
public class AuthExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv=new ModelAndView(new MappingJackson2JsonView());
        Map<String,String> map=new HashMap<>();
        map.put("status","error");
        mv.addAllObjects(map);
        return mv;
    }
}
