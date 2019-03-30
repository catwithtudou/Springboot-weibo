package red.rock.homework.Config;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 郑煜
 * @Title: TimeConfig
 * @ProjectName homework_2
 * @Description: TODO
 * @date 2019/3/28 下午 10:07
 */

@Component
public class TimeConfig {

    public String getTimes(){
        Date date=new Date();
        SimpleDateFormat times=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timess=times.format(date);
        return timess;
    }
}
