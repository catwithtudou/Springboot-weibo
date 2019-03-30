package red.rock.homework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import red.rock.homework.Config.TimeConfig;
import red.rock.homework.Entity.TextEntity;
import red.rock.homework.Entity.UserEntity;
import red.rock.homework.Mapper.TextMapper;
import red.rock.homework.Mapper.UserMapper;
import red.rock.homework.Service.TextService;

import javax.annotation.Resource;
import javax.xml.soap.Text;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@MapperScan("red.rock.homework.Mapper")
@SpringBootTest
public class HomeworkApplicationTests {


    @Autowired
    private TextMapper textMapper;

    @Autowired
    private TimeConfig times;

    @Autowired
    private TextService textService;

    @Test
    public void contextLoads() {
       // UserEntity userEntity=new UserEntity("tudou","9498","a",0);
        TextEntity textEntity=new TextEntity("2","2",times.getTimes(),1);
        TextEntity textEntity1=new TextEntity("3","3",times.getTimes(),2);
        TextEntity textEntity2=new TextEntity("4","4",times.getTimes(),0);
//        boolean flag=textMapper.addText(textEntity);
//        boolean flag1=textMapper.addText(textEntity1);
//        boolean flag2=textMapper.addText(textEntity2);
        //TextEntity demo=textMapper.getTextById(1);
//        System.out.println(demo);
//        boolean flag=textMapper.addLike(1);
        boolean textEntities=textService.deleteText(1);
//        TextEntity textEntit=new TextEntity();
//        textEntit.setId(1);
//        List<TextEntity> childList =textService.getChildText(textEntit);
//        System.out.println(childList.size());

//        for(TextEntity text:childList){
//            List<TextEntity> childList1 =text.getChildText();
//            for(TextEntity te:childList1){
//                System.out.println(te.toString());
//            }
//        }


    }

}
