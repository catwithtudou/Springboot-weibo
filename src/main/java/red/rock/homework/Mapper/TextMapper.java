package red.rock.homework.Mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import red.rock.homework.Entity.TextEntity;

import java.util.List;

/**
 * @author 郑煜
 * @Title: TextMapper
 * @ProjectName homework_2
 * @Description: 文章信息访问
 * @date 2019/3/28 下午 08:50
 */

@Repository
@Mapper
public interface TextMapper {

    /**
     * 增加一条留言
     * @param text
     * @return boolean
     */
    @Insert("INSERT INTO text_info (username,information,times,pid,toname) values(#{username},#{information},#{times},#{pid},#{toname})")
    boolean addText(TextEntity text);

    /**
     * 增加一次点赞数
     * @param id
     * @return boolean
     */
    @Insert("UPDATE text_info SET likes=likes+1 WHERE id=#{id}")
    boolean addLike(int id);

    /**
     * 得到文章信息的集合
     * @param pid
     * @return List<TextEntity></TextEntity>
     */
    @Select("SELECT * FROM text_info WHERE pid=#{pid}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "information",column = "information"),
            @Result(property = "likes",column = "likes"),
            @Result(property = "times",column ="times"),
            @Result(property = "pid",column = "pid")
    })
    List<TextEntity> getTextByPid(int pid);

    /**
     * 得到文章信息
     * @param id
     * @return TextEntity
     */
    @Select("SELECT * FROM text_info WHERE id=#{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "information",column = "information"),
            @Result(property = "likes",column = "likes"),
            @Result(property = "times",column ="times"),
            @Result(property = "pid",column = "pid")
    })
    TextEntity getTextById(int id);

    /**
     * 得到文章pid
     * @param id
     * @return int
     */
    @Select("SELECT pid FROM text_info WHERE id=#{id}")
    @Results({
            @Result(property = "pid",column = "pid")
    })
    Integer getTextPid(int id);

    /**
     * 删除一条文章
     * @param id
     * @return boolean
     */
    @Delete("DELETE FROM text_info where id=#{id}")
    boolean deleteTextById(int id);

//    /**
//     * 删除一条文章
//     * @param pid
//     * @return boolean
//     */
//    @Delete("DELETE FROM text_info where pid=#{pid}")
//    boolean deleteTextByPid(int pid);

    /**
     * 增加一条私密文章
     * @param text
     * @return boolean
     */
    @Insert("INSERT INTO text_info (username,information,times,pid,toname) values(#{username},#{information},#{times},#{pid},#{toname})")
    boolean addSeText(TextEntity text);
}

