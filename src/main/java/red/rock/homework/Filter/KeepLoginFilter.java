package red.rock.homework.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 郑煜
 * @Title: KeepLoginFilter
 * @ProjectName homework_2
 * @Description: 保持登录状态
 * @date 2019/3/29 下午 08:35
 */


@WebFilter(urlPatterns ={"/text/*"},filterName = "keepLogin")
@Order(1)
public class KeepLoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig)throws ServletException{
        System.out.println("过滤器启动");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=request.getSession();
        String username= (String) session.getAttribute("username");
        if(session==null||username==null){
            response.getWriter().print("请登录再试");
        }else{
            filterChain.doFilter(request,response);
        }
    }
}
