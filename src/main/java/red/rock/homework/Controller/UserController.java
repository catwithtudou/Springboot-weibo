package red.rock.homework.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import red.rock.homework.Entity.TextEntity;
import red.rock.homework.Entity.UserEntity;
import red.rock.homework.Service.TextService;
import red.rock.homework.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 郑煜
 * @Title: UserController
 * @ProjectName homework_2
 * @Description: 用户web页面
 * @date 2019/3/28 下午 09:25
 */

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * 注册页面
     */
    @RequestMapping("/register")
    public String  register(@RequestParam(value = "username",required = false)String username,@RequestParam(value = "password",required = false) String password,@RequestParam(value = "nickname",required = false) String nickname, @RequestParam(value = "power",defaultValue = "-1",required = false) int power, HttpServletResponse response, HttpSession session)throws Exception{
        UserEntity userEntity=new UserEntity(username,password,nickname,power);
        boolean flag=userService.registerUser(userEntity);
        response.setContentType("text/html;charset=utf-8");
        if(flag){
            session.setAttribute("username",userEntity.getUsername());
            session.setAttribute("power",userEntity.getPower());
            response.setHeader("refresh","3;url='/de'");
            return "用户注册成功,将在3秒后跳转到登录页面";
        }
        return "用户注册失败";
    }



    /**
     * 登录Servlet
     */
    @RequestMapping("/login")
    public String login(@RequestParam(value = "username")String username,@RequestParam(value = "password") String password,  HttpSession session)throws Exception{
        UserEntity userEntity=new UserEntity(username, password);
        UserEntity user=userService.loginUser(userEntity);
        if(user!=null){
            session.setAttribute("username",user.getUsername());
            session.setAttribute("power",user.getPower());
            session.setAttribute("photo",user.getPhoto());
            return "用户登录成功";
        }
        return "用户登录失败";
    }

    /**
     * 注销Servlet
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        String username=(String)session.getAttribute("username");
        session.invalidate();
        return username+"注销成功";
    }

}
