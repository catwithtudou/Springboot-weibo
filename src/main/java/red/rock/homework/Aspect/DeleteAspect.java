package red.rock.homework.Aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import red.rock.homework.Entity.TextEntity;
import red.rock.homework.Entity.UserEntity;
import red.rock.homework.Service.TextService;
import red.rock.homework.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 郑煜
 * @Title: RbacAspect
 * @ProjectName homework_2
 * @Description: 管理接口(删除留言)
 * @date 2019/3/30 上午 12:23
 */
@Component
@Aspect
@Order(2)
public class DeleteAspect {

    @Autowired
    private TextService textService;

    @Autowired
    private UserService userService;

    @Around("@annotation(red.rock.homework.Annotation.Permission)")
    public Object around(ProceedingJoinPoint point)throws Throwable{
        ServletRequestAttributes attr=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=(HttpServletRequest) attr.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        HttpServletResponse response=(HttpServletResponse)attr.getResponse();
        HttpSession session=request.getSession();
        String id= request.getParameter("id");
        //得到要删除的id
        int Id=Integer.parseInt(id);
        //若是-1则直接返回
        response.setHeader("Content-type","application/json; charset=UTF-8");
        OutputStream outputStream=response.getOutputStream();
        Map<String,String> resultMsg=new HashMap<String,String>();
        if(Id==-1){
            resultMsg.put("msg", "you must input the id");
            outputStream.write(new ObjectMapper().writeValueAsString(resultMsg).getBytes("UTF-8"));
            return null;
        }
        //得到该用户的用户名从而获取power值
        String username=(String)session.getAttribute("username");
        UserEntity user = userService.getUser(username);
        int power=user.getPower();
        //通过id获取该文章信息
        TextEntity textEntity=textService.getText(Id);
        if(textEntity==null){
            resultMsg.put("msg", "it has been deleted");
            outputStream.write(new ObjectMapper().writeValueAsString(resultMsg).getBytes("UTF-8"));
            return null;
        }
        //与文章的username和用户username比较
        if(username.equals(textEntity.getUsername())){
            //若相等则拥有权限
             return point.proceed();
        }
        //可删除自己留言的评论--找到改文章的父节点的username若是自己的则可以删除
        int pid=textService.getPidText(Id);
        TextEntity pidText=textService.getText(pid);
        if(username.equals(pidText.getUsername())){
           return point.proceed();
        }
        //若不相等则比较Power
        //power=1可删除所有文章
        //power=0仅可删除自己文章
        if(power==1){
          return point.proceed();
        }
        resultMsg.put("msg", "Not allowed to pass, you do not have the power");
        outputStream.write(new ObjectMapper().writeValueAsString(resultMsg).getBytes("UTF-8"));
        return null;
    }
}
