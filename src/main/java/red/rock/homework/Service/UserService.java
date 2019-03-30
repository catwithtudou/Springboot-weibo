package red.rock.homework.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.rock.homework.Entity.UserEntity;
import red.rock.homework.Mapper.UserMapper;


/**
 * @author 郑煜
 * @Title: UserService
 * @ProjectName homework_2
 * @Description: 用户服务层
 * @date 2019/3/28 下午 09:40
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 注册用户服务
     * @param user
     * @return boolean
     */
    public boolean registerUser(UserEntity user){
        if(user.getUsername()==""||user.getNickname()==""||user.getPassword()==""||user.getPower()==-1||user.getPassword()==null||user.getUsername()==null||user.getNickname()==null){
            return false;
        }
        UserEntity userEntity=userMapper.getUser(user.getUsername());
        if (userEntity==null){
            boolean flag=userMapper.addUser(user);
            return flag;
        }
         return false;
    }

    /**
     * 得到用户信息
     * @param username
     * @return UserEntity
     */
    public UserEntity getUser(String username){
        UserEntity user = userMapper.getUser(username);
        if(user==null){
            return null;
        }
        return user;
    }


    /**
     * 登录验证服务
     * @param userEntity
     * @return booelan
     */
    public UserEntity loginUser(UserEntity userEntity){
        if(userEntity.getUsername()==""||userEntity.getPassword()==""||userEntity.getUsername()==null||userEntity.getPassword()==null){
            return  null;
        }
        UserEntity de=userMapper.getUser(userEntity.getUsername());
        if(de==null){
            return null;
        }
        if(userEntity.getPassword().equals(de.getPassword())){
            return de;
        }
        return null;
    }
    
    /**
     * 上传头像服务
     * @param username
     * @param photo
     * @return boolean
     */
    public boolean updatePhoto(String username,String photo){
        boolean flag=userMapper.updatePhoto(photo,username);
        if(!flag) {
            return false;
        }
        return true;
    }


}
