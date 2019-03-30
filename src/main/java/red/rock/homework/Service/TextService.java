package red.rock.homework.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.rock.homework.Entity.TextEntity;
import red.rock.homework.Mapper.TextMapper;

import java.util.List;

/**
 * @author 郑煜
 * @Title: TextService
 * @ProjectName homework_2
 * @Description: 留言及评论服务层
 * @date 2019/3/28 下午 09:50
 */

@Service
public class TextService {

    @Autowired
    private TextMapper textMapper;

    /**
     * 发表一条留言或评论
     * @param textEntity
     * @return boolean
     */
    public boolean pushText(TextEntity textEntity){
        boolean flag=textMapper.addText(textEntity);
        return flag;
    }

    /**
     * 点赞服务
     * @param id
     * @return boolean
     */
    public boolean starText(int id){
         boolean flag =textMapper.addLike(id);
         return flag;
    }
    
    /**
     * 得到所有的留言
     * @param  username
     * @return List<TextEntity></TextEntity>
     */
    public List<TextEntity> getAllText(String username){
        List<TextEntity> list=textMapper.getTextByPid(0);
        for(TextEntity textEntity:list){
            if(textEntity.getToname()!=null){
                if(!textEntity.getToname().equals(username)&&!textEntity.getUsername().equals(username)){
                     textEntity.setInformation(null);
                     textEntity.setUsername(null);
                     continue;
                }
            }
            List<TextEntity> childList=getChildText(textEntity);
            textEntity.setChildText(childList);
        }
        return list;
    }


    /**
     * 得到留言及其评论(从上往下)
     * @param text
     * @return List<Entity></Entity>
     */
    private List<TextEntity> getChildText(TextEntity text){
        List<TextEntity> list=textMapper.getTextByPid(text.getId());

        for(TextEntity textEntity:list){
            List<TextEntity> childList = getChildText(textEntity);
            textEntity.setChildText(childList);
        }
        return list;
    }

    /**
     * 得到该评论的父节点的id(从下往上)
     * @param id
     * @return int
     */
    public int getPidText(int id){
        if(textMapper.getTextByPid(id)==null){
            return -1;
        }
        int preresult=textMapper.getTextPid(id);
        int result=id;
        while(preresult!=0){
            result=preresult;
            preresult=textMapper.getTextPid(preresult);
        }
        return result;
    }


    /**
     * 删除留言及其评论
     * @param  id
     * @return boolean
     */
    public boolean deleteText(int id){
        TextEntity textEntity=new TextEntity();
        textEntity.setId(id);
        List<TextEntity> list=getChildText(textEntity);
        boolean flag=true;
        for(TextEntity text:list){
            if(text==null){
                return false;
            }
            if(!textMapper.deleteTextById(text.getId())){
                flag=false;
            }
            List<TextEntity> textEntities=text.getChildText();
            for(TextEntity retext:textEntities){
                if(!textMapper.deleteTextById(retext.getId())){
                    flag=false;
                }
            }
        }
        flag=textMapper.deleteTextById(id);
        return flag;
    }

    /**
     * 删除某一条留言或者评论
     * @param id
     * @return boolean
     */
    public boolean deleteSingleText(int id){
        boolean flag=textMapper.deleteTextById(id);
        return flag;
    }

    /**
     * 得到一条留言或评论信息
     * @param id
     * @return TextEntity
     */
    public TextEntity getText(int id){
        TextEntity flag=textMapper.getTextById(id);
        if(flag==null){
            return null;
        }
        return flag;
    }



}
