package red.rock.homework.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 郑煜
 * @Title: Permission
 * @ProjectName homework_2
 * @Description: 权限管理
 * @date 2019/3/30 上午 12:22
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
}
