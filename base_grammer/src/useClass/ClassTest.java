package useClass;

/**
 * Class类是java runtime 对对象进行的运行时类型标识；
 * 封装了一个对象和接口运行时的状态，当装载类时，Class类对象自动创建。
 */
public class ClassTest {

    private String username;
    private String id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ClassTest{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
