package red.rock.homework.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import red.rock.homework.Service.UserService;
import red.rock.homework.Utils.UploadUtils;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author 郑煜
 * @Title: UploadController
 * @ProjectName homework_2
 * @Description: 文件上传或下载
 * @date 2019/3/30 下午 08:06
 */


@Controller
public class UploadController {

    @Autowired
    private UserService userService;

    @PostMapping("/text/photo")
    public String upload(MultipartFile file, HttpSession sessio)throws IOException {
        //获取文件你内容
        InputStream is=file.getInputStream();
        String originFileName=file.getOriginalFilename();

        //生成随机名称
        String uuidFileName= UploadUtils.getRealUUIDName(originFileName);

        File fileDir=new File("D:/uploadfiles/",uuidFileName);
        //若文件夹不存在,则创建文件夹
        if(!fileDir.exists()){
            fileDir.mkdir();
        }
        //将文件输出到目标文件中
        file.transferTo(fileDir);

        //将路劲存入session及数据库
        String savePath=uuidFileName;
        String username= (String) sessio.getAttribute("username");
        sessio.setAttribute("photo",savePath);
        boolean flag=userService.updatePhoto(username,savePath);
        if(flag) {
            return "上传成功";
        }else{
            return "上传失败";
        }
    }
    //img th:src="|@{/get}${session.user.avatar}|"

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/get/{filename:.+}")
    public ResponseEntity get(
            @PathVariable String filename
    ){
        //根据用户名获取相应的图片
        Path path= Paths.get("D:/uploadfiles/",filename);
        //将文件加载进来
        Resource resourse=resourceLoader.getResource("file:"+path.toString());
        //返回相应实体
        return ResponseEntity.ok(resourse);
    }
}
