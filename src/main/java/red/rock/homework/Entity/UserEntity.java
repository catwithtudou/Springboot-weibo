package red.rock.homework.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 郑煜
 * @Title: UserEntity
 * @ProjectName homework_2
 * @Description: 用户类
 * @date 2019/3/28 下午 08:36
 */
@Data
public class UserEntity implements Serializable {

    private int id;
    private String username;
    private String password;
    private String nickname;
    private String photo;
    @JsonIgnore
    private int power;

    public UserEntity(String username,String password,String nickname, int power){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.power = power;
    }

    public UserEntity(String username,String password){
        this.username = username;
        this.password = password;
    }
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public UserEntity() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", nickname=" + nickname +
                '}';
    }


}
