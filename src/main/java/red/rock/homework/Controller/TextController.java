package red.rock.homework.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import red.rock.homework.Annotation.Permission;
import red.rock.homework.Config.TimeConfig;
import red.rock.homework.Entity.TextEntity;
import red.rock.homework.Entity.UserEntity;
import red.rock.homework.Service.TextService;
import red.rock.homework.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.parser.Entity;
import javax.xml.soap.Text;
import java.util.List;

/**
 * @author 郑煜
 * @Title: TextService
 * @ProjectName homework_2
 * @Description: 留言及评论服务器
 * @date 2019/3/29 下午 08:47
 */

@RestController
public class TextController {

    @Autowired
    private TextService textService;

    @Autowired
    private TimeConfig timeConfig;

    @Autowired
    private UserService userService;
    /**
     * 发表留言Servlet
     */
    @RequestMapping("/text/first")
    public String pushText(@RequestParam(value = "information") String information, HttpServletRequest request, HttpSession session){
        if(information==null||information==""){
            return "请输入内容";
        }
        String username= (String) session.getAttribute("username");
        TextEntity textEntity=new TextEntity(username,information, timeConfig.getTimes(),0);
        boolean flag=textService.pushText(textEntity);
        if(flag){
            return username+"发表成功";
        }
        return username+"发表失败";
    }

    /**
     * 发表评论或回复评论Servlet
     */
    @RequestMapping("/text/second")
    public String pushReText(@RequestParam(value = "information")String information, @RequestParam(value = "id",defaultValue = "-1")int id, HttpSession session){
        if(information==null||id==-1||information==""){
            return "请输入内容";
        }
        String username = (String) session.getAttribute("username");
        TextEntity textEntity=new TextEntity(username,information,timeConfig.getTimes(),id);
        boolean flag=textService.pushText(textEntity);
        if(flag){
            return username+"发表成功";
        }
        return username+"发表失败";
    }

    /**
     * 点赞Servlet
     */
    @RequestMapping("/text/star")
    public String StarText(@RequestParam(value = "id",defaultValue = "-1")int id,HttpServletRequest request,HttpSession session){
        if(id==-1){
            return "请输入内容";
        }
        boolean flag=textService.starText(id);
        if(flag){
            return "点赞成功";
        }
        return "点赞失败";
    }

    /**
     * 得到全部留言及评论Servlet
     * 在有私密留言的时候,通过判断Pid=0的留言是否有toname,通过判断toname=username,若是则可以出现及评论child_content,若不是则看不见名字及内容
     */
    @RequestMapping("/alltext")
    public List<TextEntity> AllText(HttpSession session){
        String username= (String) session.getAttribute("username");
        List<TextEntity> textEntities=textService.getAllText(username);
        if(textEntities==null){
            return null;
        }
        return textEntities;
    }

    /**
     * 删除文章Servlet
     */
    @Permission
    @RequestMapping("/text/delete")
    public String deleteText(@RequestParam(value = "id",defaultValue = "-1")int id){
        if(id==-1){
            return "请输入参数";
        }
        boolean flag =textService.deleteText(id);
        if(flag){
            return "删除成功";
        }
        return "删除失败";
    }


    /**
     * 发表匿名留言Servlet
     */
    @RequestMapping("/text/hide")
    public String pushHideText(@RequestParam(value = "information") String information, HttpServletRequest request, HttpSession session){
        if(information==null||information==""){
            return "请输入内容";
        }
        TextEntity textEntity=new TextEntity(null,information, timeConfig.getTimes(),0);
        boolean flag=textService.pushText(textEntity);
        if(flag){
            return "发表成功";
        }
        return "发表失败";
    }


    /**
     * 发表私密留言Servlet
     */
    @RequestMapping("/text/private")
    public String pushPrivateText(@RequestParam(value = "information")String information,@RequestParam(value = "toname")String toname,HttpSession session){
        if (information==""||information==null||toname==""||toname==null){
            return "请输入内容";
        }
        UserEntity touserEntity=userService.getUser(toname);
        if(touserEntity==null){
            return "此人并不存在";
        }
        String username= (String) session.getAttribute("username");
        TextEntity textEntity=new TextEntity(username,information,timeConfig.getTimes(),0,toname);
        boolean flag=textService.pushText(textEntity);
        if(flag){
            return "发表成功";
        }
        return "发表失败";
    }


    /**
     * 测试Servlet
     */
    @RequestMapping("/text/ss")
    public String test(HttpSession ssession){
        String username=(String)ssession.getAttribute("username");
        return username+"111";
    }
}
