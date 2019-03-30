package red.rock.homework.Utils;

import java.util.UUID;

/**
 * @author 郑煜
 * @Title: UploadUtils
 * @ProjectName homework_2
 * @Description: 文件上传相关工具
 * @date 2019/3/30 下午 08:11
 */
public class UploadUtils {

    /**
     * 获取文件真实名称
     * 由于浏览器的不同获取的名称可能为:c:/upload/1.jpg或者1.jpg
     * 最终获取的为  1.jpg
     * @param name
     * @return 真实名称
     */
    public static String getRealName(String name){
        //获取最后一个"/"
        int index=name.lastIndexOf("\\");
        return name.substring(index+1);
    }

    /**
     * 获取随机名称
     * @param realName
     * @return String
     */
    public static String getRealUUIDName(String realName){
        //realName可能是 is.jpg 也可能是 is
        //获取后缀名
        int index=realName.lastIndexOf(".");
        if(index==-1){
            return UUID.randomUUID().toString().replace("-","").toUpperCase();
        }else{
            return UUID.randomUUID().toString().replace("-","").toUpperCase()+realName.substring(index);
        }
    }


}
