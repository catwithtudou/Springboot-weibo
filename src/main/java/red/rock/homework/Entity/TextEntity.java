package red.rock.homework.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author 郑煜
 * @Title: TextEntity
 * @ProjectName homework_2
 * @Description: 文章类
 * @date 2019/3/28 下午 08:37
 */
@Data
public class TextEntity {

    private int id;
    private String username;
    private String information;
    private int likes;
    private String times;
    @JsonIgnore
    private int pid;
    @JsonIgnore
    private String toname;
    private List<TextEntity> childText;

    public TextEntity(String username,String information,String times,int pid){
        this.username = username;
        this.information = information;
        this.times = times;
        this.pid = pid;
    }

    public TextEntity(String username,String information,String times,int pid,String toname){
        this.username = username;
        this.information = information;
        this.times = times;
        this.pid = pid;
        this.toname=toname;
    }

    public TextEntity(){

    }

    public String getToname() {
        return toname;
    }

    public void setToname(String toname) {
        this.toname = toname;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public List<TextEntity> getChildText() {
        return childText;
    }

    public void setChildText(List<TextEntity> childText) {
        this.childText = childText;
    }

    @Override
    public String toString() {
        return "Text{" +
                "id=" + id +
                ", username=" + username +
                ", information=" + information +
                ", likes=" + likes +
                ", times=" + times +
                ", pid="  + pid+
                '}';
    }
}
