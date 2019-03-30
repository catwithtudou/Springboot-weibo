package red.rock.homework.Mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import red.rock.homework.Entity.UserEntity;

/**
 * @author 郑煜
 * @Title: UserMapper
 * @ProjectName homework_2
 * @Description: 用户数据访问
 * @date 2019/3/28 下午 08:44
 */

@Repository
@Mapper
public interface UserMapper {

    /**
     * 增加用户
     * @param user
     * @return boolean
     */
    @Insert("insert into user_info(username,password,nickname,power) values(#{username},#{password},#{nickname},#{power})")
    @Options(useGeneratedKeys = true,keyProperty ="id",keyColumn = "id")
    boolean addUser(UserEntity user);

    /**
     * 得到用户信息
     * @param username
     * @return UserEntity
     */
    @Select("SELECT * FROM user_info where username=#{username}")
    @Results({
            @Result(property = "username",column ="username"),
            @Result(property = "password",column ="password"),
            @Result(property = "nickname",column ="nickname"),
            @Result(property ="power",column ="power"),
            @Result(property ="photo",column ="photo")
    })
    UserEntity getUser(String username);

    /**
     * 保存用户头像信息
     * @param photo
     * @param username
     * @return boolean
     */
    @Update("UPDATE user_info SET photo=#{photo} WHERE username=#{username}")
    boolean updatePhoto(String photo,String username);


}
