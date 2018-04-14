package Serializable_uid;

import java.io.Serializable;

/**
 * 如果没有定义 serialVersionUID = 1L; 这个类添加address成员变量前后默认对象不兼容；
 * 则无法从没address的Person实例的序列化对象，还原成带address的Person实例。
 */
public class Person implements Serializable {
    //如果没有指定serialVersionUID，系统会自动生成一个
    private static final long serialVersionUID = 1L;

    private String name;
    //添加这么一个成员变量
    private String address;   //序列化后如果之前版本没有，就为null

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
